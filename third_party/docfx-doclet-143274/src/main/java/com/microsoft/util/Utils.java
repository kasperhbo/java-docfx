package com.microsoft.util;

import static com.sun.source.doctree.DocTree.Kind.ERRONEOUS;
import static javax.lang.model.type.TypeKind.DECLARED;

import com.sun.source.doctree.DocCommentTree;
import com.sun.source.doctree.DocTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import jdk.javadoc.doclet.DocletEnvironment;

/**
 * The type Utils.
 */
public class Utils {

    /**
     * The Doclet environment.
     */
    public final  DocletEnvironment docletEnvironment;
    /**
     * The Elements.
     */
    public final  Elements          elements;
    /**
     * The Type utils.
     */
    public final  Types             typeUtils;
    private final ElementUtil       elementUtil;

    /**
     * Instantiates a new Utils.
     *
     * @param docEnv      the doc env
     * @param elementUtil the element util
     */
    public Utils(DocletEnvironment docEnv, ElementUtil elementUtil) {
        this.docletEnvironment = docEnv;
        this.elements          = docEnv.getElementUtils();
        this.typeUtils         = docEnv.getTypeUtils();
        this.elementUtil       = elementUtil;
    }

    /**
     * Is package private boolean.
     *
     * @param e the e
     * @return the boolean
     */
    public static boolean isPackagePrivate(Element e) {
        return !(isPublic(e) || isPrivate(e) || isProtected(e));
    }

    /**
     * Is private boolean.
     *
     * @param e the e
     * @return the boolean
     */
    public static boolean isPrivate(Element e) {
        return e.getModifiers().contains(Modifier.PRIVATE);
    }

    /**
     * Is protected boolean.
     *
     * @param e the e
     * @return the boolean
     */
    public static boolean isProtected(Element e) {
        return e.getModifiers().contains(Modifier.PROTECTED);
    }

    /**
     * Is public boolean.
     *
     * @param e the e
     * @return the boolean
     */
    public static boolean isPublic(Element e) {
        return e.getModifiers().contains(Modifier.PUBLIC);
    }

    /**
     * Is private or package private boolean.
     *
     * @param e the e
     * @return the boolean
     */
    public static boolean isPrivateOrPackagePrivate(Element e) {
        return isPrivate(e) || isPackagePrivate(e);
    }

    /**
     * Is no type boolean.
     *
     * @param t the t
     * @return the boolean
     */
    public static boolean isNoType(TypeMirror t) {
        return t.getKind() == TypeKind.NONE;
    }

    /**
     * Filtered list list.
     *
     * @param dlist  the dlist
     * @param select the select
     * @return the list
     */
    public static List<? extends DocTree> filteredList(List<? extends DocTree> dlist, DocTree.Kind... select) {
        List<DocTree> list = new ArrayList<>();
        if (select == null) return dlist;
        for (DocTree dt : dlist) {
            if (dt.getKind() != ERRONEOUS) {
                for (DocTree.Kind kind : select) {
                    if (dt.getKind() == kind) {
                        list.add(dt);
                    }
                }
            }
        }
        return list;
    }

    /**
     * Is static boolean.
     *
     * @param e the e
     * @return the boolean
     */
    public boolean isStatic(Element e) {
        return e.getModifiers().contains(Modifier.STATIC);
    }

    /**
     * Returns true if the method has no comments, or a lone &commat;inheritDoc.
     *
     * @param m a method
     * @return true if there are no comments, false otherwise
     */
    public boolean isSimpleOverride(ExecutableElement m) {

        if (!getBlockTags(m).isEmpty()) return false;

        List<? extends DocTree> fullBody = getFullBody(m);
        return fullBody.isEmpty() || (fullBody.size() == 1 && fullBody.get(0).getKind().equals(DocTree.Kind.INHERIT_DOC));
    }

    /**
     * Has inline tag boolean.
     *
     * @param inlineTags the inline tags
     * @param kind       the kind
     * @return the boolean
     */
    public boolean hasInlineTag(List<? extends DocTree> inlineTags, DocTree.Kind kind) {
        for (DocTree dt : inlineTags) {
            if (dt.getKind() == kind) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets member by signature.
     *
     * @param te        the te
     * @param kind      the kind
     * @param signature the signature
     * @return the member by signature
     */
    public Element getMemberBySignature(TypeElement te, ElementKind kind, String signature) {
        return getMembers(te, kind).stream().filter(e -> e.toString().equals(signature)).findFirst().orElse(null);
    }

    /**
     * Gets object type.
     *
     * @return the object type
     */
    public TypeElement getObjectType() {
        return elements.getTypeElement("java.lang.Object");
    }

    /**
     * Return the element of given type.
     *
     * @param t the t
     * @return the type element
     */
    public TypeElement asTypeElement(TypeMirror t) {
        if (t.getKind() != TypeKind.NONE) {
            TypeElement element = (TypeElement) docletEnvironment.getTypeUtils().asElement(t);
            return element;
        } else return null;
    }

    /**
     * Return the lexically enclosing element for a nested type.
     * The inherited items will not be listed.
     *
     * @param e the e
     * @return the enclosing type element
     */
    public TypeElement getEnclosingTypeElement(Element e) {
        if (e.getKind() == ElementKind.PACKAGE) return null;
        Element     encl = e.getEnclosingElement();
        ElementKind kind = encl.getKind();
        if (kind == ElementKind.PACKAGE) return null;
        while (!(kind.isClass() || kind.isInterface())) {
            encl = encl.getEnclosingElement();
            kind = encl.getKind();
        }
        return (TypeElement) encl;
    }

    /**
     * Follow the same behavior with Standard doclet.
     * Return the ExecutableElement being overridden by given method,
     * when a method in a class overrides a method in a superclass.
     * For following cases, they will be marked as "Specified by":
     * <ul>
     *   <li>a method in an interface overrides a method in a superinterface
     *   <li>a method in a class implements a method in an interface
     * </ul>
     * <p>
     * todo add "Specified by" to yaml.
     *
     * @param method the method
     * @return the executable element
     */
    public ExecutableElement overriddenMethod(ExecutableElement method) {
        if (isStatic(method)) {
            return null;
        }
        final TypeElement origin = getEnclosingTypeElement(method);
        for (TypeMirror t = getSuperType(origin); t.getKind() == DECLARED; t = getSuperType(asTypeElement(t))) {
            TypeElement te = asTypeElement(t);
            if (te == null) {
                return null;
            }

            for (Element e : getMembers(te, ElementKind.METHOD)) {
                ExecutableElement ee = (ExecutableElement) e;
                if (elements.overrides(method, ee, origin)) {
                    return ee;
                }
            }
            if (t.equals(getObjectType().asType())) return null;
        }
        return null;
    }

    /**
     * Gets super type.
     *
     * @param te the te
     * @return the super type
     */
    public TypeMirror getSuperType(TypeElement te) {
        TypeMirror t = te.getSuperclass();
        return getType(t);
    }

    private TypeMirror getType(TypeMirror t) {
        return (isNoType(t)) ? getObjectType().asType() : t;
    }

    /**
     * Gets doc comment tree.
     *
     * @param element the element
     * @return the doc comment tree
     */
    public Optional<DocCommentTree> getDocCommentTree(Element element) {
        return Optional.ofNullable(docletEnvironment.getDocTrees().getDocCommentTree(element));
    }

    /**
     * Gets full body.
     *
     * @param element the element
     * @return the full body
     */
    protected List<? extends DocTree> getFullBody(Element element) {
        return getDocCommentTree(element).map(DocCommentTree::getFullBody).orElse(Collections.emptyList());
    }

    private List<? extends DocTree> getBlockTags0(Element element, DocTree.Kind... kinds) {
        Optional<DocCommentTree> dcTree = getDocCommentTree(element);
        if (dcTree.isEmpty()) return Collections.emptyList();

        return filteredList(dcTree.get().getBlockTags(), kinds);
    }

    /**
     * Gets block tags.
     *
     * @param element the element
     * @return the block tags
     */
    public List<? extends DocTree> getBlockTags(Element element) {
        return getBlockTags0(element, (DocTree.Kind[]) null);
    }

    /**
     * Remove block tag list.
     *
     * @param dctree the dctree
     * @param kind   the kind
     * @return the list
     */
    public List<? extends DocTree> removeBlockTag(List<? extends DocTree> dctree, DocTree.Kind kind) {
        return dctree.stream().filter(dc -> !dc.getKind().equals(kind)).collect(Collectors.toList());
    }

    /**
     * Returns a list of visible enclosed members of given kind,
     * declared in this type element, and does not include
     * any inherited members or extra members.
     *
     * @param te   the te
     * @param kind the kind
     * @return a list of visible enclosed members in this type
     */
    public List<? extends Element> getMembers(TypeElement te, ElementKind kind) {
        return elementUtil.getEnclosedElements(te).stream().filter(e -> e.getKind() == kind
                                                                        && !isPrivateOrPackagePrivate(e)).
                collect(Collectors.toList());
    }

    /**
     * Returns a list of methods being implemented by given method.
     * When a method in an interface overrides a method its superinterface,
     * it will be considered as "implemented", instead of "overridden".
     *
     * @param signature          the signature
     * @param encl               the encl
     * @param implementedMethods the implemented methods
     * @return a list of implemented methods
     */
    public List<Element> getImplementedMethods(String signature, TypeElement encl, List<Element> implementedMethods) {
        if (encl == null) {
            return implementedMethods;
        }

        for (TypeElement interfaceType : getImplementedInterfaces(encl)) {
            Element implementedMethod = getMemberBySignature(interfaceType, ElementKind.METHOD, signature);
            if (implementedMethod != null) {
                implementedMethods.add(implementedMethod);
            }
            // We need to search every implemented interface of the Inheritance chain.
            getImplementedMethods(signature, interfaceType, implementedMethods);
        }
        return implementedMethods;
    }

    /**
     * Returns a list of implemented interface type elements of given type element.
     * Follow Standard doclet, search in the order of appearance following the word implements in declaration.
     *
     * @param element the element
     * @return a list of implemented interfaces
     */
    public List<TypeElement> getImplementedInterfaces(TypeElement element) {
        return element.getInterfaces().stream()
                .map(e -> asTypeElement(e)).collect(Collectors.toList());
    }
}
