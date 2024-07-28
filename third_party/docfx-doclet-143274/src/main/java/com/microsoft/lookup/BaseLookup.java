package com.microsoft.lookup;

import com.microsoft.lookup.model.ExtendedMetadataFileItem;
import com.microsoft.model.*;
import com.microsoft.util.YamlUtil;
import com.sun.source.doctree.*;
import jdk.javadoc.doclet.DocletEnvironment;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The type Base lookup.
 *
 * @param <T> the type parameter
 */
public abstract class BaseLookup<T extends Element> {

    private static final int    INITIAL_CAPACITY = 500000;
    private static final Logger logger           = LoggerFactory.getLogger(BaseLookup.class);

    /**
     * The Element kind lookup.
     */
    protected final Map<ElementKind, String>         elementKindLookup =
            new HashMap<>() {
                {
                    put(ElementKind.PACKAGE, "Namespace");
                    put(ElementKind.CLASS, "Class");
                    put(ElementKind.ENUM, "Enum");
                    put(ElementKind.ENUM_CONSTANT, "Field");
                    put(ElementKind.INTERFACE, "Interface");
                    put(ElementKind.ANNOTATION_TYPE, "Interface");
                    put(ElementKind.CONSTRUCTOR, "Constructor");
                    put(ElementKind.METHOD, "Method");
                    put(ElementKind.FIELD, "Field");
                }
            };
    /**
     * The Environment.
     */
    protected final DocletEnvironment                environment;
    /**
     * The Map.
     */
    protected       Map<T, ExtendedMetadataFileItem> map;

    /**
     * Instantiates a new Base lookup.
     *
     * @param environment the environment
     */
    protected BaseLookup(DocletEnvironment environment) {
        this.environment = environment;
        this.map         = new HashMap<>(INITIAL_CAPACITY);
    }

    /**
     * Resolve extended metadata file item.
     *
     * @param key the key
     * @return the extended metadata file item
     */
    protected ExtendedMetadataFileItem resolve(T key) {
        map.computeIfAbsent(key, this::buildMetadataFileItem);
        return map.get(key);
    }

    /**
     * Build metadata file item extended metadata file item.
     *
     * @param key the key
     * @return the extended metadata file item
     */
    protected abstract ExtendedMetadataFileItem buildMetadataFileItem(T key);

    /**
     * Extract package name string.
     *
     * @param key the key
     * @return the string
     */
    public String extractPackageName(T key) {
        logger.debug("Extracting package name for key: {}", key);
        String packageName = resolve(key).getPackageName();

        logger.debug("Extracted package name: {}", packageName);
        return packageName;
    }

    /**
     * Extract namespace string.
     *
     * @param key the key
     * @return the string
     */
    public String extractNamespace(T key) {
        logger.debug("Extracting namespace for key: {}", key);
        String namespace = resolve(key).getNamespace();
        logger.debug("Extracted namespace: {}", namespace);
        return namespace;
    }

    /**
     * Extract full name string.
     *
     * @param key the key
     * @return the string
     */
    public String extractFullName(T key) {
        return resolve(key).getFullName();
    }

    /**
     * Extract name string.
     *
     * @param key the key
     * @return the string
     */
    public String extractName(T key) {
        return resolve(key).getName();
    }

    /**
     * Extract href string.
     *
     * @param key the key
     * @return the string
     */
    public String extractHref(T key) {
        return resolve(key).getHref();
    }

    /**
     * Extract parent string.
     *
     * @param key the key
     * @return the string
     */
    public String extractParent(T key) {
        return resolve(key).getParent();
    }

    /**
     * Extract id string.
     *
     * @param key the key
     * @return the string
     */
    public String extractId(T key) {
        return resolve(key).getId();
    }

    /**
     * Extract uid string.
     *
     * @param key the key
     * @return the string
     */
    public String extractUid(T key) {
        return resolve(key).getUid();
    }

    /**
     * Extract name with type string.
     *
     * @param key the key
     * @return the string
     */
    public String extractNameWithType(T key) {
        return resolve(key).getNameWithType();
    }

    /**
     * Extract method content string.
     *
     * @param key the key
     * @return the string
     */
    public String extractMethodContent(T key) {
        return resolve(key).getMethodContent();
    }

    /**
     * Extract field content string.
     *
     * @param key the key
     * @return the string
     */
    public String extractFieldContent(T key) {
        return resolve(key).getFieldContent();
    }

    /**
     * Extract constructor content string.
     *
     * @param key the key
     * @return the string
     */
    public String extractConstructorContent(T key) {
        return resolve(key).getConstructorContent();
    }

    /**
     * Extract overload string.
     *
     * @param key the key
     * @return the string
     */
    public String extractOverload(T key) {
        return resolve(key).getOverload();
    }

    /**
     * Extract parameters list.
     *
     * @param key the key
     * @return the list
     */
    public List<MethodParameter> extractParameters(T key) {
        return resolve(key).getParameters();
    }

    /**
     * Extract exceptions list.
     *
     * @param key the key
     * @return the list
     */
    public List<ExceptionItem> extractExceptions(T key) {
        return resolve(key).getExceptions();
    }

    /**
     * Extract return return.
     *
     * @param key the key
     * @return the return
     */
    public Return extractReturn(T key) {
        return resolve(key).getReturn();
    }

    /**
     * Extract summary string.
     *
     * @param key the key
     * @return the string
     */
    public String extractSummary(T key) {
        return resolve(key).getSummary();
    }

    /**
     * Extract type string.
     *
     * @param key the key
     * @return the string
     */
    public String extractType(T key) {
        return resolve(key).getType();
    }

    /**
     * Extract java type string.
     *
     * @param element the element
     * @return the string
     */
    public String extractJavaType(T element) {
        return null;
    }

    /**
     * Extract content string.
     *
     * @param key the key
     * @return the string
     */
    public String extractContent(T key) {
        return resolve(key).getContent();
    }

    /**
     * Extract type parameters list.
     *
     * @param key the key
     * @return the list
     */
    public List<TypeParameter> extractTypeParameters(T key) {
        return resolve(key).getTypeParameters();
    }

    /**
     * Extract superclass list.
     *
     * @param key the key
     * @return the list
     */
    public List<String> extractSuperclass(T key) {
        List<String> reversed = resolve(key).getSuperclass();
        Collections.reverse(reversed);
        return reversed;
    }

    /**
     * Extract inherited methods list.
     *
     * @param key the key
     * @return the list
     */
    public List<String> extractInheritedMethods(T key) {
        List<String> sorted = resolve(key).getInheritedMethods();
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * Extract interfaces list.
     *
     * @param key the key
     * @return the list
     */
    public List<String> extractInterfaces(T key) {
        return resolve(key).getInterfaces();
    }

    /**
     * Extract toc name string.
     *
     * @param key the key
     * @return the string
     */
    public String extractTocName(T key) {
        return resolve(key).getTocName();
    }

    /**
     * Extract references set.
     *
     * @param key the key
     * @return the set
     */
    public Set<MetadataFileItem> extractReferences(T key) {
        return resolve(key).getReferences();
    }

    /**
     * Extract overridden string.
     *
     * @param key the key
     * @return the string
     */
    public String extractOverridden(T key) {
        return resolve(key).getOverridden();
    }

    /**
     * Gets doc comment tree.
     *
     * @param element the element
     * @return the doc comment tree
     */
    protected Optional<DocCommentTree> getDocCommentTree(T element) {
        return Optional.ofNullable(environment.getDocTrees().getDocCommentTree(element));
    }

    private boolean hasDeprecatedJavadocTag(T element) {
        List<? extends DocTree> javadocTags =
                getDocCommentTree(element)
                        .map(DocCommentTree::getBlockTags)
                        .orElse(Collections.emptyList());

        return javadocTags.stream().map(DocTree::getKind).anyMatch(DocTree.Kind.DEPRECATED::equals);
    }

    /**
     * Determine type string.
     *
     * @param element the element
     * @return the string
     */
    protected String determineType(T element) {
        return elementKindLookup.get(element.getKind());
    }

    /**
     * Determine package name string.
     *
     * @param element the element
     * @return the string
     */
    protected String determinePackageName(T element) {
        return String.valueOf(environment.getElementUtils().getPackageOf(element));
    }

    /**
     * Determine comment string.
     *
     * @param element the element
     * @return the string
     */
    protected String determineComment(T element) {
        String statusComment  = getStatusComment(element);
        String javadocComment = getJavadocComment(element);
        return joinNullable(statusComment, javadocComment);
    }

    private String getJavadocComment(T element) {
        return getDocCommentTree(element)
                .map(
                        tree -> {
                            String commentWithBlockTags = replaceLinksAndCodes(tree.getFullBody());
                            return replaceBlockTags(tree, commentWithBlockTags);
                        })
                .orElse(null);
    }

    /**
     * Safely combine two nullable strings with a newline delimiter
     *
     * @param top    the top
     * @param bottom the bottom
     * @return the string
     */
    String joinNullable(@Nullable String top, @Nullable String bottom) {
        String a = top == null || top.isEmpty() ? null : top;
        String b = bottom == null || bottom.isEmpty() ? null : bottom;

        if (a != null && b != null) {
            return a + "\n\n" + b;
        } else if (a != null) {
            return a;
        } else {
            return b;
        }
    }

    /**
     * Provides support for deprecated and see tags
     *
     * @param docCommentTree the doc comment tree
     * @param comment        the comment
     * @return the string
     */
    String replaceBlockTags(DocCommentTree docCommentTree, String comment) {
        Set<String> seeItems             = new HashSet<>();
        String      commentWithBlockTags = comment;
        for (DocTree blockTag : docCommentTree.getBlockTags()) {
            switch (blockTag.getKind()) {
                case DEPRECATED:
                    commentWithBlockTags = getDeprecatedSummary((DeprecatedTree) blockTag).concat(comment);
                    break;
                case SEE:
                    seeItems.add(getSeeTagRef((SeeTree) blockTag));
                    break;
                default:
            }
        }
        if (!seeItems.isEmpty()) {
            commentWithBlockTags = commentWithBlockTags.concat(getSeeAlsoSummary(seeItems));
        }
        return commentWithBlockTags;
    }

    /**
     * <ul>
     * <li>Replace @link and @linkplain with <xref> tags</li>
     * <li>Replace @code with <code> tags</li>
     * </ul>
     *
     * @param items the items
     * @return the string
     */
    String replaceLinksAndCodes(List<? extends DocTree> items) {
        return YamlUtil.cleanupHtml(
                items.stream()
                        .map(
                                bodyItem -> {
                                    switch (bodyItem.getKind()) {
                                        case LINK:
                                        case LINK_PLAIN:
                                            return buildXrefTag((LinkTree) bodyItem);
                                        case CODE:
                                            return buildCodeTag((LiteralTree) bodyItem);
                                        case LITERAL:
                                            return expandLiteralBody((LiteralTree) bodyItem);
                                        default:
                                            return String.valueOf(StringEscapeUtils.unescapeJava(bodyItem.toString()));
                                    }
                                })
                        .collect(Collectors.joining()));
    }

    /**
     * By using this way of processing links we provide support of @links with label, like this:
     * {@link List someLabel}
     *
     * @param linkTree the link tree
     * @return the string
     */
    String buildXrefTag(LinkTree linkTree) {
        String signature = linkTree.getReference().getSignature();
        String label =
                linkTree.getLabel().stream().map(String::valueOf).collect(Collectors.joining(" "));
        if (StringUtils.isEmpty(label)) {
            label = signature;
        }
        return String.format(
                "<xref uid=\"%s\" data-throw-if-not-resolved=\"false\">%s</xref>", signature, label);
    }

    /**
     * Build code tag string.
     *
     * @param literalTree the literal tree
     * @return the string
     */
    String buildCodeTag(LiteralTree literalTree) {
        return String.format(
                "<code>%s</code>", StringEscapeUtils.unescapeJava(literalTree.getBody().toString()));
    }

    /**
     * Expand literal body string.
     *
     * @param bodyItem the body item
     * @return the string
     */
    String expandLiteralBody(LiteralTree bodyItem) {
        return String.valueOf(StringEscapeUtils.unescapeJava(bodyItem.getBody().toString()));
    }

    /**
     * We make type shortening in assumption that package name doesn't contain uppercase characters
     *
     * @param value the value
     * @return the string
     */
    public String makeTypeShort(String value) {
        if (!value.contains(".")) {
            return value;
        }
        return Stream.of(StringUtils.split(value, "<"))
                .map(s -> RegExUtils.removeAll(s, "\\b[a-z0-9_.]+\\."))
                .collect(Collectors.joining("<"));
    }

    private String getSeeAlsoSummary(Set<String> seeItems) {
        return String.format("\nSee Also: %s\n", String.join(", ", seeItems));
    }

    private String getDeprecatedSummary(DeprecatedTree deprecatedTree) {
        return String.format(
                "\n<strong>Deprecated.</strong> <em>%s</em>\n\n",
                replaceLinksAndCodes(deprecatedTree.getBody()));
    }

    private String getSeeTagRef(SeeTree seeTree) {
        String ref =
                seeTree.getReference().stream().map(r -> String.valueOf(r)).collect(Collectors.joining(""));
        // if it's already a tag, use that otherwise build xref tag
        if (ref.matches("^<.+>(.|\n)*")) {
            return ref.replaceAll("\n", "").replaceAll("(  )+", " ");
        }
        return String.format(
                "<xref uid=\"%1$s\" data-throw-if-not-resolved=\"false\">%1$s</xref>", ref);
    }

    /**
     * Extract status string.
     *
     * @param element the element
     * @return the string
     */
    public String extractStatus(T element) {
        List<String> annotationNames =
                element.getAnnotationMirrors().stream()
                        .map(mirror -> mirror.getAnnotationType().asElement().getSimpleName().toString())
                        .collect(Collectors.toList());

        if (annotationNames.stream().anyMatch("Deprecated"::equals)
            || hasDeprecatedJavadocTag(element)) {
            return "deprecated";
        }
        if (annotationNames.stream().anyMatch("BetaApi"::equals)) {
            return "beta";
        }
        return null;
    }

    /**
     * Gets status comment.
     *
     * @param element the element
     * @return the status comment
     */
    public String getStatusComment(T element) {
        Map<String, Optional<String>> annotationComments = getAnnotationComments(element);

        // Deprecated comments are determined by the Javadoc @deprecated block tag.
        // See this#replaceBlockTags

        List<String> comments = new ArrayList<>();
        if (annotationComments.containsKey("InternalApi")) {
            comments.add(createInternalOnlyNotice(annotationComments.get("InternalApi")));
        }
        if (annotationComments.containsKey("InternalExtensionOnly")) {
            comments.add(
                    createInternalExtensionOnlyNotice(annotationComments.get("InternalExtensionOnly")));
        }
        if (annotationComments.containsKey("ObsoleteApi")) {
            comments.add(createObsoleteNotice(annotationComments.get("ObsoleteApi")));
        }
        if (annotationComments.containsKey("BetaApi")) {
            comments.add(createBetaNotice(annotationComments.get("BetaApi")));
        }

        if (comments.isEmpty()) {
            return null;
        }
        return String.join("\n\n", comments);
    }

    private String createBetaNotice(Optional<String> customComment) {
        return "<aside class=\"beta\">\n"
               + "<p><strong>Beta</strong></p>\n"
               + customComment.map(comment -> "<p><em>" + comment + "</em></p>\n").orElse("")
               + "<p>This feature is covered by the <a href=\"/terms/service-terms#1\">Pre-GA Offerings "
               + "Terms</a> of the Terms of Service. Pre-GA libraries might have limited support, and "
               + "changes to pre-GA libraries might not be compatible with other pre-GA versions. For "
               + "more information, see the launch stage descriptions.</p>\n"
               + "</aside>\n";
    }

    private String createObsoleteNotice(Optional<String> customComment) {
        return "<aside class=\"deprecated\">\n"
               + "<p><strong>Obsolete</strong></p>\n"
               + customComment.map(comment -> "<p><em>" + comment + "</em></p>\n").orElse("")
               + "<p>This feature is stable for usage in this major version, but may be deprecated in a "
               + "future release.</p>\n"
               + "</aside>\n";
    }

    private String createInternalExtensionOnlyNotice(Optional<String> customComment) {
        return "<aside class=\"special\">\n"
               + "<p><strong>Internal Extension Only</strong>: This feature is stable for usage, but is "
               + "not intended for extension or implementation.</p>\n"
               + customComment.map(comment -> "<p><em>" + comment + "</em></p>\n").orElse("")
               + "</aside>\n";
    }

    private String createInternalOnlyNotice(Optional<String> customComment) {
        return "<aside class=\"warning\">\n"
               + "<p><strong>Internal Only</strong>: This feature is not stable for application use.</p>\n"
               + customComment.map(comment -> "<p><em>" + comment + "</em></p>\n").orElse("")
               + "</aside>\n";
    }

    /**
     * Gets annotation comments.
     *
     * @param element the element
     * @return all annotations on the element and their associated comment, if it exists
     */
    public Map<String, Optional<String>> getAnnotationComments(T element) {
        Map<String, Optional<String>> annotationComments = new HashMap<>();

        for (AnnotationMirror annotation : element.getAnnotationMirrors()) {
            String name = annotation.getAnnotationType().asElement().getSimpleName().toString();
            Optional<String> value =
                    annotation.getElementValues().entrySet().stream()
                            .filter(entry -> entry.getKey().getSimpleName().toString().equals("value"))
                            .map(Map.Entry::getValue)
                            .map(annotationValue -> annotationValue.getValue().toString())
                            .findFirst();

            annotationComments.put(name, value);
        }

        return annotationComments;
    }
}
