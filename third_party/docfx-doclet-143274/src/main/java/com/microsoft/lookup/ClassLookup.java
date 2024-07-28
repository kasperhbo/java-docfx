package com.microsoft.lookup;

import com.microsoft.lookup.model.ExtendedMetadataFileItem;
import com.microsoft.model.MetadataFileItem;
import com.microsoft.model.TypeParameter;
import com.microsoft.util.ElementUtil;
import com.microsoft.util.Utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import jdk.javadoc.doclet.DocletEnvironment;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * The type Class lookup.
 */
public class ClassLookup extends BaseLookup<TypeElement> {

  private static final String JAVA_LANG_OBJECT = "java.lang.Object";

  private final ElementUtil elementUtil;

  /**
   * Instantiates a new Class lookup.
   *
   * @param environment the environment
   * @param elementUtil the element util
   */
  public ClassLookup(DocletEnvironment environment, ElementUtil elementUtil) {
    super(environment);
    this.elementUtil = elementUtil;
  }

  @Override
  protected ExtendedMetadataFileItem buildMetadataFileItem(TypeElement classElement) {
    List<ExtendedMetadataFileItem> inheritedMethods = new ArrayList<>();

    String packageName = determinePackageName(classElement);

    String classQName = String.valueOf(classElement.getQualifiedName());
    String classSName = String.valueOf(classElement.getSimpleName());
    String classQNameWithGenericsSupport = String.valueOf(classElement.asType());
    String classSNameWithGenericsSupport =
        classQNameWithGenericsSupport.replace(packageName.concat("."), "");

    ExtendedMetadataFileItem result = new ExtendedMetadataFileItem(classQName);
    result.setId(classSName);
    result.setParent(packageName);
    result.setHref(classQName + ".yml");
    result.setName(classSNameWithGenericsSupport);
    result.setNameWithType(classSNameWithGenericsSupport);
    result.setFullName(classQNameWithGenericsSupport);
    result.setType(determineType(classElement));
    result.setPackageName(packageName);
    result.setNamespace(packageName);
    result.setSummary(determineComment(classElement));
    result.setSuperclass(determineNestedSuperclass(classElement, result, inheritedMethods));
    result.setTypeParameters(determineTypeParameters(classElement));
    result.setInheritedMethods(determineInheritedMembers(inheritedMethods));
    result.setJavaType(extractJavaType(classElement));
    populateContent(classElement, classSNameWithGenericsSupport, result);
    result.setTocName(classQName.replace(packageName.concat("."), ""));
    return result;
  }

  /**
   * Populate content.
   *
   * @param classElement                 the class element
   * @param shortNameWithGenericsSupport the short name with generics support
   * @param container                    the container
   */
  void populateContent(
      TypeElement classElement,
      String shortNameWithGenericsSupport,
      ExtendedMetadataFileItem container) {
    String type = elementKindLookup.get(classElement.getKind());
    String result =
        String.format(
            "%s %s %s",
            classElement.getModifiers().stream()
                .map(String::valueOf)
                .filter(modifier -> !("Interface".equals(type) && "abstract".equals(modifier)))
                .filter(
                    modifier ->
                        !("Enum".equals(type)
                            && ("static".equals(modifier) || "final".equals(modifier))))
                .collect(Collectors.joining(" ")),
            StringUtils.lowerCase(type),
            shortNameWithGenericsSupport);

    String superclass = determineSuperclass(classElement);
    if (superclass != null && !JAVA_LANG_OBJECT.equals(superclass)) {
      result += " extends " + makeTypeShort(superclass);

      addSuperclassToReferencesMap(superclass, container);
    }

    List<? extends TypeMirror> interfaces = classElement.getInterfaces();
    if (CollectionUtils.isNotEmpty(interfaces)) {
      String prefix =
          (classElement.getKind() == ElementKind.INTERFACE) ? " extends " : " implements ";
      result +=
          prefix
              + interfaces.stream()
                  .map(String::valueOf)
                  .map(this::makeTypeShort)
                  .collect(Collectors.joining(", "));

      container.setInterfaces(
          interfaces.stream().map(String::valueOf).collect(Collectors.toList()));

      addInterfacesToReferencesMap(interfaces, container);
    }
    addInheritedMethodsToReferencesMap(container);
    container.setContent(result);
  }

  /**
   * Add superclass to references map.
   *
   * @param superclass the superclass
   * @param container  the container
   */
  void addSuperclassToReferencesMap(String superclass, ExtendedMetadataFileItem container) {
    container.addReferences(
        Set.of(new MetadataFileItem(superclass, makeTypeShort(superclass), false)));
  }

  /**
   * Add inherited methods to references map.
   *
   * @param container the container
   */
  void addInheritedMethodsToReferencesMap(ExtendedMetadataFileItem container) {
    container.addReferences(
        container.getInheritedMethods().stream()
            .map(o -> new MetadataFileItem(o, makeTypeShort(o), false))
            .collect(Collectors.toSet()));
  }

  /**
   * Add interfaces to references map.
   *
   * @param interfaces the interfaces
   * @param container  the container
   */
  void addInterfacesToReferencesMap(
      List<? extends TypeMirror> interfaces, ExtendedMetadataFileItem container) {
    container.addReferences(
        interfaces.stream()
            .map(String::valueOf)
            .map(o -> new MetadataFileItem(o, makeTypeShort(o), false))
            .collect(Collectors.toSet()));
  }

  /**
   * Determine superclass string.
   *
   * @param classElement the class element
   * @return the string
   */
  String determineSuperclass(TypeElement classElement) {
    TypeMirror superclass = classElement.getSuperclass();
    if (superclass.getKind() == TypeKind.NONE) {
      return null;
    }
    return String.valueOf(superclass);
  }

  /**
   * Determine nested superclass list.
   *
   * @param classElement     the class element
   * @param result           the result
   * @param inheritedMethods the inherited methods
   * @return the list
   */
  List<String> determineNestedSuperclass(
      TypeElement classElement,
      ExtendedMetadataFileItem result,
      List<ExtendedMetadataFileItem> inheritedMethods) {
    List<String> nestedList = new ArrayList<>();

    if (result.getSuperclass() != null) {
      nestedList = result.getSuperclass();
    }

    TypeMirror superclass = classElement.getSuperclass();
    if (superclass.getKind() != TypeKind.NONE) {
      TypeElement superClassElement =
          (TypeElement) environment.getTypeUtils().asElement(superclass);

      nestedList.add(superClassElement.getQualifiedName().toString());
      result.setSuperclass(nestedList);
      appendInheritedMethods(superClassElement, inheritedMethods);

      determineNestedSuperclass(superClassElement, result, inheritedMethods);
    }
    return nestedList;
  }

  /**
   * Determine type parameters list.
   *
   * @param element the element
   * @return the list
   */
  List<TypeParameter> determineTypeParameters(TypeElement element) {
    return element.getTypeParameters().stream()
        .map(typeParameter -> new TypeParameter(String.valueOf(typeParameter)))
        .collect(Collectors.toList());
  }

  /**
   * Append inherited methods.
   *
   * @param element          the element
   * @param inheritedMethods the inherited methods
   */
  void appendInheritedMethods(
      TypeElement element, List<ExtendedMetadataFileItem> inheritedMethods) {
    List<? extends Element> members = elementUtil.getEnclosedElements(element);
    Integer level = Optional.ofNullable(getMaxNestedLevel(inheritedMethods)).orElse(0);

    for (Element m : members) {
      if (m.getKind() == ElementKind.METHOD && !Utils.isPrivateOrPackagePrivate(m)) {
        String uid = element.getQualifiedName().toString().concat(".") + String.valueOf(m);

        ExtendedMetadataFileItem item = new ExtendedMetadataFileItem(uid);
        item.setName(String.valueOf(m));
        item.setNestedLevel(level + 1);

        inheritedMethods.add(item);
      }
    }
  }

  /**
   * Gets max nested level.
   *
   * @param inheritedMethods the inherited methods
   * @return the max nested level
   */
  Integer getMaxNestedLevel(List<ExtendedMetadataFileItem> inheritedMethods) {
    Integer level = 0;

    if (inheritedMethods.size() > 0) {
      level =
          inheritedMethods.stream()
              .mapToInt(v -> v.getNestedLevel())
              .max()
              .orElseThrow(NoSuchElementException::new);
    }
    return level;
  }

  /**
   * Determine inherited members list.
   *
   * @param inheritedMethods the inherited methods
   * @return the list
   */
  List<String> determineInheritedMembers(List<ExtendedMetadataFileItem> inheritedMethods) {

    if (!inheritedMethods.isEmpty()) {
      HashMap<String, ExtendedMetadataFileItem> map = new HashMap<>();
      for (ExtendedMetadataFileItem item : inheritedMethods) {
        String key = item.getName();

        if (map.containsKey(key) && map.get(key).getNestedLevel() > item.getNestedLevel()) {
          // child class will have smaller than superclass, we only need the nearest methods
          // inherited with same signature
          map.put(key, item);
        } else if (!map.containsKey(key)) {
          map.put(key, item);
        }
      }

        return map.values().stream().map(MetadataFileItem::getUid).collect(Collectors.toList());
    }
    return new ArrayList<>();
  }

  @Override
  public String getStatusComment(TypeElement element) {
    // Don't provide status comments for classes.
    return null;
  }

  @Override
  public String extractJavaType(TypeElement element) {
    String superClass = determineSuperclass(element);
    if (superClass != null && superClass.contains("Exception")) {
      return "exception";
    }

    String javatype = element.getKind().name().toLowerCase().replaceAll("_", "");
    if (javatype.equals("annotationtype")) {
      return javatype;
    }
    return null;
  }
}
