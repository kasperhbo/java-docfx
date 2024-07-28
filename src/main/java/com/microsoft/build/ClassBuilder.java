/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoft.build;

import com.google.docfx.doclet.RepoMetadata;
import com.microsoft.lookup.ClassItemsLookup;
import com.microsoft.lookup.ClassLookup;
import com.microsoft.lookup.PackageLookup;
import com.microsoft.model.*;
import com.microsoft.util.ElementUtil;
import com.microsoft.util.Utils;

import javax.lang.model.element.*;
import javax.lang.model.util.ElementFilter;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.microsoft.build.BuilderUtil.*;

/**
 * The type Class builder.
 */
class ClassBuilder {

    private static final String           OPEN_TABLE = "<td><a href=\"";
    private final        ElementUtil      elementUtil;
    private final        ClassLookup      classLookup;
    private final        ClassItemsLookup classItemsLookup;
    private final        String           outputPath;
    private final        PackageLookup    packageLookup;
    private final        ReferenceBuilder referenceBuilder;

    /**
     * Instantiates a new Class builder.
     *
     * @param elementUtil      the element util
     * @param classLookup      the class lookup
     * @param classItemsLookup the class items lookup
     * @param outputPath       the output path
     * @param packageLookup    the package lookup
     * @param referenceBuilder the reference builder
     */
    ClassBuilder(
            ElementUtil elementUtil,
            ClassLookup classLookup,
            ClassItemsLookup classItemsLookup,
            String outputPath,
            PackageLookup packageLookup,
            ReferenceBuilder referenceBuilder) {
        this.elementUtil      = elementUtil;
        this.classLookup      = classLookup;
        this.classItemsLookup = classItemsLookup;
        this.outputPath       = outputPath;
        this.packageLookup    = packageLookup;
        this.referenceBuilder = referenceBuilder;
    }

    /**
     * Build files for package list.
     *
     * @param pkg                the pkg
     * @param classMetadataFiles the class metadata files
     * @param repoMetadata       the repo metadata
     * @return the list
     */
    List<TocItem> buildFilesForPackage(
            PackageElement pkg, List<MetadataFile> classMetadataFiles, RepoMetadata repoMetadata) {
        if (packageLookup.isApiVersionPackage(pkg) && containsAtLeastOneClient(pkg)) {
            // API Version package organization is a nested list organized by GAPIC concepts
            ApiVersionPackageToc apiVersionPackageToc = new ApiVersionPackageToc();
            buildFilesForApiVersionPackage(pkg, apiVersionPackageToc, classMetadataFiles, repoMetadata);
            return apiVersionPackageToc.toList();

        } else if (packageLookup.isApiVersionStubPackage(pkg)) {
            StubPackageToc stubPackageToc = new StubPackageToc();
            buildFilesForStubPackage(pkg, stubPackageToc, classMetadataFiles);
            return stubPackageToc.toList();

        } else {
            // Standard package organization is a flat list organized by Java type
            TocTypeMap typeMap = new TocTypeMap();
            buildFilesForStandardPackage(pkg, typeMap, classMetadataFiles);
            return typeMap.toList();
        }
    }

    private void buildFilesForApiVersionPackage(
            Element element,
            ApiVersionPackageToc apiVersionPackageToc,
            List<MetadataFile> classMetadataFiles,
            RepoMetadata repoMetadata) {
        for (TypeElement classElement : elementUtil.extractSortedElements(element)) {
            String  uid     = classLookup.extractUid(classElement);
            String  name    = classLookup.extractTocName(classElement);
            String  status  = classLookup.extractStatus(classElement);
            TocItem tocItem = new TocItem(uid, name, status);

            // The order of these checks matter!
            // Ex: a paging response class would change its category if "isPagingClass" is checked first.
            if (classElement.getKind() == ElementKind.INTERFACE) {
                apiVersionPackageToc.addInterface(tocItem);
            } else if (isClient(classElement)) {
                apiVersionPackageToc.addClient(tocItem);
            } else if (name.endsWith("Response") || name.endsWith("Request")) {
                apiVersionPackageToc.addRequestOrResponse(tocItem);
            } else if (name.endsWith("Settings")) {
                apiVersionPackageToc.addSettings(tocItem);
            } else if (name.endsWith("Builder")) {
                apiVersionPackageToc.addBuilder(tocItem);
            } else if (classElement.getKind() == ElementKind.ENUM) {
                apiVersionPackageToc.addEnum(tocItem);
            } else if (name.endsWith("Exception")) {
                apiVersionPackageToc.addException(tocItem);
            } else if (isGeneratedMessage(classElement)) {
                apiVersionPackageToc.addMessage(tocItem);
            } else if (isPagingClass(classElement)) {
                apiVersionPackageToc.addPaging(tocItem);
            } else if (isResourceName(classElement)) {
                apiVersionPackageToc.addResourceName(tocItem);
            } else {
                apiVersionPackageToc.addUncategorized(tocItem);
            }

            // Client classes have custom overview
//            if (isClient(classElement)) {
//                classMetadataFiles.add(buildClientClassYmlFile(classElement, repoMetadata));
//            } else {
//            }
            classMetadataFiles.add(buildClassYmlFile(classElement));
            buildFilesForApiVersionPackage(
                    classElement, apiVersionPackageToc, classMetadataFiles, repoMetadata);
        }
    }

    private void buildFilesForStubPackage(
            Element element, StubPackageToc packageToc, List<MetadataFile> classMetadataFiles) {
        for (TypeElement classElement : elementUtil.extractSortedElements(element)) {
            String  uid     = classLookup.extractUid(classElement);
            String  name    = classLookup.extractTocName(classElement);
            String  status  = classLookup.extractStatus(classElement);
            TocItem tocItem = new TocItem(uid, name, status);

            if (name.endsWith("Stub")) {
                packageToc.addStub(tocItem);
            } else if (name.contains("Settings")) {
                packageToc.addSettings(tocItem);
            } else if (name.endsWith("CallableFactory")) {
                packageToc.addCallableFactory(tocItem);
            } else {
                packageToc.addUncategorized(tocItem);
            }

            classMetadataFiles.add(buildClassYmlFile(classElement));
            buildFilesForStubPackage(classElement, packageToc, classMetadataFiles);
        }
    }

    /**
     * Contains at least one client boolean.
     *
     * @param pkg the pkg
     * @return the boolean
     */
    boolean containsAtLeastOneClient(PackageElement pkg) {
        return elementUtil.extractSortedElements(pkg).stream().anyMatch(this::isClient);
    }

    /**
     * Is client boolean.
     *
     * @param classElement the class element
     * @return the boolean
     */
    boolean isClient(TypeElement classElement) {
        return classLookup.extractTocName(classElement).endsWith("Client");
    }

    /**
     * Is resource name boolean.
     *
     * @param classElement the class element
     * @return the boolean
     */
    boolean isResourceName(TypeElement classElement) {
        return classElement.getInterfaces().stream()
                .anyMatch(i -> String.valueOf(i).contains("ResourceName"));
    }

    /**
     * Is generated message boolean.
     *
     * @param classElement the class element
     * @return the boolean
     */
    boolean isGeneratedMessage(TypeElement classElement) {
        return String.valueOf(classElement.getSuperclass()).contains("GeneratedMessage");
    }

    /**
     * Is paging class boolean.
     *
     * @param classElement the class element
     * @return the boolean
     */
    boolean isPagingClass(TypeElement classElement) {
        return String.valueOf(classElement.getSuperclass()).contains(".paging.");
    }

    /**
     * Build files for standard package.
     *
     * @param element            the element
     * @param tocTypeMap         the toc type map
     * @param classMetadataFiles the class metadata files
     */
    void buildFilesForStandardPackage(
            Element element, TocTypeMap tocTypeMap, List<MetadataFile> classMetadataFiles) {
        for (TypeElement classElement : elementUtil.extractSortedElements(element)) {
            String uid    = classLookup.extractUid(classElement);
            String name   = classLookup.extractTocName(classElement);
            String status = classLookup.extractStatus(classElement);

            if (tocTypeMap.get(classElement.getKind().name()) != null) {
                if (classElement.getKind().name().equals(ElementKind.CLASS.name())
                    && name.contains("Exception")) {
                    tocTypeMap.get("EXCEPTION").add(new TocItem(uid, name, status));
                } else {
                    tocTypeMap.get(classElement.getKind().name()).add(new TocItem(uid, name, status));
                }
            } else {
                tocTypeMap.get(ElementKind.CLASS.name()).add(new TocItem(uid, name, status));
            }

            classMetadataFiles.add(buildClassYmlFile(classElement));
            buildFilesForStandardPackage(classElement, tocTypeMap, classMetadataFiles);
        }
    }


    /**
     * Builds a metadata file for a client class. This method is deprecated and should be replaced
     *
     * @param classElement The class element to build the metadata file for
     * @param repoMetadata Metadata for the repository
     * @return MetadataFile
     * @deprecated Use buildFilesForPackage instead
     */
    @Deprecated(since = "15-SNAPSHOT") // Use buildFilesForPackage instead
    private MetadataFile buildClientClassYmlFile(
            TypeElement classElement, RepoMetadata repoMetadata) {
        String       fileName          = classLookup.extractHref(classElement);
        MetadataFile classMetadataFile = new MetadataFile(outputPath, fileName);
        addClientClassInfo(classElement, classMetadataFile, repoMetadata);
        addConstructorsInfo(classElement, classMetadataFile);
        addMethodsInfo(classElement, classMetadataFile);
        addFieldsInfo(classElement, classMetadataFile);
        referenceBuilder.addReferencesInfo(classElement, classMetadataFile);
        applyPostProcessing(classMetadataFile);
        return classMetadataFile;
    }

    private MetadataFile buildClassYmlFile(TypeElement classElement) {
        String       fileName          = classLookup.extractHref(classElement);
        MetadataFile classMetadataFile = new MetadataFile(outputPath, fileName);
        addClassInfo(classElement, classMetadataFile);
        addConstructorsInfo(classElement, classMetadataFile);
        addMethodsInfo(classElement, classMetadataFile);
        addFieldsInfo(classElement, classMetadataFile);
        referenceBuilder.addReferencesInfo(classElement, classMetadataFile);
        applyPostProcessing(classMetadataFile);
        return classMetadataFile;
    }

    // Does not set Inherited Methods or Inheritance as that information for Client classes is
    // superfluous
    // Sets updated Client summary with table of links
    private void addClientClassInfo(
            TypeElement classElement, MetadataFile classMetadataFile, RepoMetadata repoMetadata) {
        MetadataFileItem classItem = new MetadataFileItem(LANGS, classLookup.extractUid(classElement));
        classItem.setId(classLookup.extractId(classElement));
        classItem.setParent(classLookup.extractParent(classElement));
        addChildren(classElement, classItem.getChildren());
        populateItemFieldsForClients(classItem, classLookup, classElement);

        classItem.setPackageName(classLookup.extractPackageName(classElement));
        classItem.setNamespace(classLookup.extractNamespace(classElement));

        classItem.setTypeParameters(classLookup.extractTypeParameters(classElement));
        classItem.setInheritance(classLookup.extractSuperclass(classElement));

        String summary      = classLookup.extractSummary(classElement);
        String summaryTable = createClientOverviewTable(classElement, repoMetadata);
        classItem.setSummary(summaryTable + summary);
        classItem.setStatus(classLookup.extractStatus(classElement));
        classMetadataFile.getItems().add(classItem);
    }

    private String createClientOverviewTable(TypeElement classElement, RepoMetadata repoMetadata) {
        String clientURI = classLookup.extractUid(classElement).replaceAll("\\.", "/");
        String githubSourceLink =
                repoMetadata.getGithubLink()
                + "/"
                + repoMetadata.getArtifactId()
                + "/src/main/java/"
                + clientURI
                + ".java";

        StringBuilder tableBuilder = new StringBuilder();
        tableBuilder
                .append("<table>")
                .append("<tr>")
                .append(OPEN_TABLE)
                .append(githubSourceLink)
                .append("\">GitHub Repository</a></td>")
                .append(OPEN_TABLE)
                .append(repoMetadata.getProductDocumentationUri())
                .append("\">Product Reference</a></td>");
        repoMetadata
                .getRestDocumentationUri()
                .ifPresent(
                        restDocumentationURI ->
                                tableBuilder
                                        .append(OPEN_TABLE)
                                        .append(restDocumentationURI)
                                        .append("\">REST Documentation</a></td>"));
        repoMetadata
                .getRpcDocumentationUri()
                .ifPresent(
                        rpcDocumentationURI ->
                                tableBuilder
                                        .append(OPEN_TABLE)
                                        .append(rpcDocumentationURI)
                                        .append("\">RPC Documentation</a></td>"));
        tableBuilder.append("</tr></table>\n\n");
        return tableBuilder.toString();
    }

    private void addClassInfo(TypeElement classElement, MetadataFile classMetadataFile) {
        MetadataFileItem classItem = new MetadataFileItem(LANGS, classLookup.extractUid(classElement));
        classItem.setId(classLookup.extractId(classElement));
        classItem.setParent(classLookup.extractParent(classElement));
        addChildren(classElement, classItem.getChildren());
        populateItemFields(classItem, classLookup, classElement);

        classItem.setPackageName(classLookup.extractPackageName(classElement));
        classItem.setNamespace(classLookup.extractNamespace(classElement));

        classItem.setTypeParameters(classLookup.extractTypeParameters(classElement));
        classItem.setInheritance(classLookup.extractSuperclass(classElement));
        classItem.setInterfaces(classLookup.extractInterfaces(classElement));
        classItem.setInheritedMethods(classLookup.extractInheritedMethods(classElement));
        classItem.setSummary(classLookup.extractSummary(classElement));
        classItem.setStatus(classLookup.extractStatus(classElement));

        classMetadataFile.getItems().add(classItem);
    }

    /**
     * Add constructors info.
     *
     * @param classElement      the class element
     * @param classMetadataFile the class metadata file
     */
    void addConstructorsInfo(TypeElement classElement, MetadataFile classMetadataFile) {
        for (ExecutableElement constructorElement :
                ElementFilter.constructorsIn(elementUtil.getEnclosedElements(classElement))) {
            MetadataFileItem constructorItem = buildMetadataFileItem(constructorElement);
            constructorItem.setOverload(classItemsLookup.extractOverload(constructorElement));
            constructorItem.setContent(classItemsLookup.extractConstructorContent(constructorElement));
            constructorItem.setParameters(classItemsLookup.extractParameters(constructorElement));
            classMetadataFile.getItems().add(constructorItem);

            referenceBuilder.addParameterReferences(constructorItem, classMetadataFile);
            referenceBuilder.addOverloadReferences(constructorItem, classMetadataFile);
        }
    }

    private void addMethodsInfo(TypeElement classElement, MetadataFile classMetadataFile) {
        ElementFilter.methodsIn(elementUtil.getEnclosedElements(classElement)).stream()
                .filter(methodElement -> !Utils.isPrivateOrPackagePrivate(methodElement))
                .forEach(
                        methodElement -> {
                            MetadataFileItem methodItem = buildMetadataFileItem(methodElement);
                            methodItem.setOverload(classItemsLookup.extractOverload(methodElement));
                            methodItem.setContent(classItemsLookup.extractMethodContent(methodElement));
                            methodItem.setExceptions(classItemsLookup.extractExceptions(methodElement));
                            methodItem.setParameters(classItemsLookup.extractParameters(methodElement));
                            methodItem.setReturn(classItemsLookup.extractReturn(methodElement));
                            methodItem.setOverridden(classItemsLookup.extractOverridden(methodElement));

                            classMetadataFile.getItems().add(methodItem);
                            referenceBuilder.addExceptionReferences(methodItem, classMetadataFile);
                            referenceBuilder.addParameterReferences(methodItem, classMetadataFile);
                            referenceBuilder.addReturnReferences(methodItem, classMetadataFile);
                            referenceBuilder.addOverloadReferences(methodItem, classMetadataFile);
                        });
    }

    private void addFieldsInfo(TypeElement classElement, MetadataFile classMetadataFile) {
        ElementFilter.fieldsIn(elementUtil.getEnclosedElements(classElement)).stream()
                .filter(fieldElement -> !Utils.isPrivateOrPackagePrivate(fieldElement))
                .forEach(
                        fieldElement -> {
                            MetadataFileItem fieldItem = buildMetadataFileItem(fieldElement);
                            fieldItem.setContent(classItemsLookup.extractFieldContent(fieldElement));
                            fieldItem.setReturn(classItemsLookup.extractReturn(fieldElement));
                            classMetadataFile.getItems().add(fieldItem);
                            referenceBuilder.addReturnReferences(fieldItem, classMetadataFile);
                        });
    }

    private void applyPostProcessing(MetadataFile classMetadataFile) {
        referenceBuilder.expandComplexGenericsInReferences(classMetadataFile);
    }

    private MetadataFileItem buildMetadataFileItem(Element element) {
        return new MetadataFileItem(LANGS, classItemsLookup.extractUid(element)) {
            {
                String name = classItemsLookup.extractName(element);
                setId(classItemsLookup.extractId(element));
                setParent(classItemsLookup.extractParent(element));
                setName(name);
                setNameWithType(classItemsLookup.extractNameWithType(element));
                setFullName(classItemsLookup.extractFullName(element));
                setType(classItemsLookup.extractType(element));
                setJavaType(classItemsLookup.extractJavaType(element));
                setPackageName(classItemsLookup.extractPackageName(element));
                setNamespace(classItemsLookup.extractNamespace(element));
                setSummary(classItemsLookup.extractSummary(element));
                setStatus(classItemsLookup.extractStatus(element));
            }
        };
    }

    private void addChildren(TypeElement classElement, List<String> children) {
        collect(classElement, children, ElementFilter::constructorsIn, classItemsLookup::extractUid);
        collect(classElement, children, ElementFilter::methodsIn, classItemsLookup::extractUid);
        collect(classElement, children, ElementFilter::fieldsIn, classItemsLookup::extractUid);
        collect(classElement, children, ElementFilter::typesIn, String::valueOf);
    }

    private void collect(
            TypeElement classElement,
            List<String> children,
            Function<Iterable<? extends Element>, List<? extends Element>> selectFunc,
            Function<? super Element, String> mapFunc) {

        List<? extends Element> elements =
                selectFunc.apply(elementUtil.getEnclosedElements(classElement));
        children.addAll(
                filterPrivateElements(elements).stream().map(mapFunc).collect(Collectors.toList()));
    }

    private List<? extends Element> filterPrivateElements(List<? extends Element> elements) {
        return elements.stream()
                .filter(element -> !Utils.isPrivateOrPackagePrivate(element))
                .collect(Collectors.toList());
    }
}
