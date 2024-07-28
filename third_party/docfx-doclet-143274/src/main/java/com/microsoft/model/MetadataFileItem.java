package com.microsoft.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.microsoft.build.PackageOverviewFile.PackageChildSummary;
import org.apache.commons.lang3.RegExUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * MetadataFileItem class.
 */
//@formatter:off
@JsonPropertyOrder({
  "uid",
  "id",
  "parent",
  "children",
  "href",
  "langs",
  "isExternal",
  "name",
  "nameWithType",
  "fullName",
  "overload",
  "overridden",
  "type",
  "javatype",

  "package",
  "namespace",

  "summary",
  "syntax",
  "inheritance",
  "implements",
  "exceptions",
  "spec.java",
  "inheritedMembers",
  "status"
})
//@formatter:on
public class MetadataFileItem implements Comparable<MetadataFileItem> {

    private final String       uid;
    private       String       id;
    private       String       parent;

    private final List<String> children = new ArrayList<>();
    private final HashMap<String, String[]> childrenSummaries = new HashMap<>();


    private final List<PackageChildSummary> packageChildrenSummaries = new ArrayList<>();
    private       String                    href;
    private String[]                  langs;
    private String                    name;
    private String                    nameWithType;
    private String                    fullName;
    private String                    overload;
    private String                    overridden;
    private String                    type;
    private String                    javatype;

    @JsonProperty("package")
    private String packageName;

    @JsonProperty("namespace")
    private String namespace;

    private String       summary;
    private Syntax       syntax;
    private List<String> inheritance;

    @JsonProperty("implements")
    private List<String> interfaces;

    private List<ExceptionItem> exceptions;
    private boolean             isExternal;

    @JsonProperty("spec.java")
    private List<SpecViewModel> specForJava = new ArrayList<>();

    @JsonProperty("inheritedMembers")
    private List<String> inheritedMethods = new ArrayList<>();

    private String status;

    /**
     * Instantiates a new Metadata file item.
     *
     * @param langs the langs
     * @param uid   the uid
     */
    public MetadataFileItem(String[] langs, String uid) {
        this(uid);
        this.langs = langs;
    }

    /**
     * Instantiates a new Metadata file item.
     *
     * @param uid the uid
     */
    public MetadataFileItem(String uid) {
        this.uid = uid;
    }

    /**
     * Instantiates a new Metadata file item.
     *
     * @param uid        the uid
     * @param name       the name
     * @param isExternal the is external
     */
    public MetadataFileItem(String uid, String name, boolean isExternal) {
        this(uid);
        this.name         = name;
        this.nameWithType = name;
        this.fullName     = uid;
        this.isExternal   = isExternal;
    }

    /**
     * Instantiates a new Metadata file item.
     *
     * @param uid   the uid
     * @param specs the specs
     */
    public MetadataFileItem(String uid, List<SpecViewModel> specs) {
        this(uid);
        this.specForJava = specs;
    }

    @Override
    public int compareTo(MetadataFileItem item) {
        return this.getUid().compareTo(item.getUid());
    }

    /**
     * Gets uid.
     *
     * @return the uid
     */
    public String getUid() {
        return uid;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets parent.
     *
     * @return the parent
     */
    public String getParent() {
        return parent;
    }

    /**
     * Sets parent.
     *
     * @param parent the parent
     */
    public void setParent(String parent) {
        this.parent = parent;
    }

    /**
     * Gets children.
     *
     * @return the children
     */
    public List<String> getChildren() {
        Collections.sort(children);
        return children;
    }

    /**
     * Gets children summaries.
     *
     * @return the children summaries
     */
    public HashMap<String, String[]> getChildrenSummaries() {
        return childrenSummaries;
    }

    /**
     * Gets package children summaries.
     *
     * @return the package children summaries
     */
    public List<PackageChildSummary> getPackageChildrenSummaries() {
        return packageChildrenSummaries;
    }

    /**
     * Gets href.
     *
     * @return the href
     */
    public String getHref() {
        return href;
    }

    /**
     * Sets href.
     *
     * @param href the href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * Get langs string [ ].
     *
     * @return the string [ ]
     */
    public String[] getLangs() {
        return langs;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets name with type.
     *
     * @return the name with type
     */
    public String getNameWithType() {
        return nameWithType;
    }

    /**
     * Sets name with type.
     *
     * @param nameWithType the name with type
     */
    public void setNameWithType(String nameWithType) {
        this.nameWithType = nameWithType;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets full name.
     *
     * @param fullName the full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets overload.
     *
     * @return the overload
     */
    public String getOverload() {
        return overload;
    }

    /**
     * Sets overload.
     *
     * @param overload the overload
     */
    public void setOverload(String overload) {
        this.overload = handleGenericForOverLoad(overload);
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets java type.
     *
     * @return the java type
     */
    public String getJavaType() {
        return javatype;
    }

    /**
     * Sets java type.
     *
     * @param javaType the java type
     */
    public void setJavaType(String javaType) {
        this.javatype = javaType;
    }

    /**
     * Gets package name.
     *
     * @return the package name
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Sets package name.
     *
     * @param packageName the package name
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Gets namespace.
     *
     * @return the namespace
     */
    public String getNamespace() {
        return namespace;
    }

    /**
     * Sets namespace.
     *
     * @param namespace the namespace
     */
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * Gets summary.
     *
     * @return the summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Sets summary.
     *
     * @param summary the summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Gets syntax.
     *
     * @return the syntax
     */
    public Syntax getSyntax() {
        return syntax;
    }

    /**
     * Sets syntax.
     *
     * @param syntax the syntax
     */
    public void setSyntax(Syntax syntax) {
        this.syntax = syntax;
    }

    /**
     * Gets inheritance.
     *
     * @return the inheritance
     */
    public List<String> getInheritance() {
        return inheritance;
    }

    /**
     * Sets inheritance.
     *
     * @param superclass the superclass
     */
    public void setInheritance(List<String> superclass) {
        this.inheritance = (superclass == null) ? null : superclass;
    }

    /**
     * Gets interfaces.
     *
     * @return the interfaces
     */
    public List<String> getInterfaces() {
        return interfaces;
    }

    /**
     * Sets interfaces.
     *
     * @param interfaces the interfaces
     */
    public void setInterfaces(List<String> interfaces) {
        this.interfaces = interfaces;
    }

    /**
     * Gets inherited methods.
     *
     * @return the inherited methods
     */
    public List<String> getInheritedMethods() {
        return inheritedMethods;
    }

    /**
     * Sets inherited methods.
     *
     * @param inheritedMethods the inherited methods
     */
    public void setInheritedMethods(List<String> inheritedMethods) {
        this.inheritedMethods = (inheritedMethods == null) ? null : inheritedMethods;
    }

    /**
     * Gets spec for java.
     *
     * @return the spec for java
     */
    public List<SpecViewModel> getSpecForJava() {
        return specForJava;
    }

    /**
     * Gets exceptions.
     *
     * @return the exceptions
     */
    public List<ExceptionItem> getExceptions() {
        return exceptions;
    }

    /**
     * Sets exceptions.
     *
     * @param exceptions the exceptions
     */
    public void setExceptions(List<ExceptionItem> exceptions) {
        this.exceptions = exceptions;
    }

    /**
     * Sets content.
     *
     * @param content the content
     */
    public void setContent(String content) {
        if (syntax == null) {
            syntax = new Syntax();
        }
        syntax.setContent(content);
    }

    /**
     * Sets type parameters.
     *
     * @param typeParameters the type parameters
     */
    public void setTypeParameters(List<TypeParameter> typeParameters) {
        if (syntax == null) {
            syntax = new Syntax();
        }
        syntax.setTypeParameters(typeParameters);
    }

    /**
     * Sets parameters.
     *
     * @param parameters the parameters
     */
    public void setParameters(List<MethodParameter> parameters) {
        if (syntax == null) {
            syntax = new Syntax();
        }
        syntax.setParameters(parameters);
    }

    /**
     * Sets return.
     *
     * @param returnValue the return value
     */
    public void setReturn(Return returnValue) {
        if (syntax == null) {
            syntax = new Syntax();
        }
        syntax.setReturnValue(returnValue);
    }

    /**
     * Gets overridden.
     *
     * @return the overridden
     */
    public String getOverridden() {
        return overridden;
    }

    /**
     * Sets overridden.
     *
     * @param overridden the overridden
     */
    public void setOverridden(String overridden) {
        this.overridden = overridden;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MetadataFileItem that = (MetadataFileItem) o;

        return uid.equals(that.uid);
    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }

    /**
     * Gets is external.
     *
     * @return the is external
     */
    public Boolean getIsExternal() {
        return isExternal ? true : null;
    }

    /**
     * Sets is external.
     *
     * @param external the external
     */
    public void setIsExternal(boolean external) {
        isExternal = external;
    }

    /**
     * Handle generic for over load string.
     *
     * @param value the value
     * @return the string
     */
    public String handleGenericForOverLoad(String value) {
        return RegExUtils.removeAll(value, "<\\w+(,\\s*\\w+)*>");
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
