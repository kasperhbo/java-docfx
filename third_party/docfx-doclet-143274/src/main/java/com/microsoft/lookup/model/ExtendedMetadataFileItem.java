package com.microsoft.lookup.model;

import com.microsoft.model.MetadataFileItem;
import com.microsoft.model.MethodParameter;
import com.microsoft.model.Return;
import com.microsoft.model.TypeParameter;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Just container to keep cached precalculated values for lookup
 */
public class ExtendedMetadataFileItem extends MetadataFileItem {

  private String methodContent;
  private String fieldContent;
  private String constructorContent;
  private List<MethodParameter> parameters;
  private Return returnValue;
  private String content;
  private List<TypeParameter> typeParameters;
  private List<String> superclass;
  private String tocName;
  private Set<MetadataFileItem> references = new LinkedHashSet<>();
  private Integer nestedLevel;

    /**
     * Instantiates a new Extended metadata file item.
     *
     * @param uid the uid
     */
    public ExtendedMetadataFileItem(String uid) {
    super(uid);
  }

    /**
     * Gets method content.
     *
     * @return the method content
     */
    public String getMethodContent() {
    return methodContent;
  }

    /**
     * Sets method content.
     *
     * @param methodContent the method content
     */
    public void setMethodContent(String methodContent) {
    this.methodContent = methodContent;
  }

    /**
     * Gets field content.
     *
     * @return the field content
     */
    public String getFieldContent() {
    return fieldContent;
  }

    /**
     * Sets field content.
     *
     * @param fieldContent the field content
     */
    public void setFieldContent(String fieldContent) {
    this.fieldContent = fieldContent;
  }

    /**
     * Gets constructor content.
     *
     * @return the constructor content
     */
    public String getConstructorContent() {
    return constructorContent;
  }

    /**
     * Sets constructor content.
     *
     * @param constructorContent the constructor content
     */
    public void setConstructorContent(String constructorContent) {
    this.constructorContent = constructorContent;
  }

    /**
     * Gets parameters.
     *
     * @return the parameters
     */
    public List<MethodParameter> getParameters() {
    return parameters;
  }

    /**
     * Gets nested level.
     *
     * @return the nested level
     */
    public Integer getNestedLevel() {
    return nestedLevel;
  }

    /**
     * Sets nested level.
     *
     * @param level the level
     */
    public void setNestedLevel(Integer level) {
    this.nestedLevel = level;
  }

  @Override
  public void setParameters(List<MethodParameter> parameters) {
    this.parameters = parameters;
  }

    /**
     * Gets return.
     *
     * @return the return
     */
    public Return getReturn() {
    return returnValue;
  }

  @Override
  public void setReturn(Return returnValue) {
    this.returnValue = returnValue;
  }

    /**
     * Gets content.
     *
     * @return the content
     */
    public String getContent() {
    return content;
  }

  @Override
  public void setContent(String content) {
    this.content = content;
  }

    /**
     * Gets type parameters.
     *
     * @return the type parameters
     */
    public List<TypeParameter> getTypeParameters() {
    return typeParameters;
  }

  @Override
  public void setTypeParameters(List<TypeParameter> typeParameters) {
    this.typeParameters = typeParameters;
  }

    /**
     * Gets superclass.
     *
     * @return the superclass
     */
    public List<String> getSuperclass() {
    return superclass;
  }

    /**
     * Sets superclass.
     *
     * @param superclass the superclass
     */
    public void setSuperclass(List<String> superclass) {
    this.superclass = superclass;
  }

    /**
     * Sets toc name.
     *
     * @param tocName the toc name
     */
    public void setTocName(String tocName) {
    this.tocName = tocName;
  }

    /**
     * Gets toc name.
     *
     * @return the toc name
     */
    public String getTocName() {
    return tocName;
  }

    /**
     * Add references.
     *
     * @param references the references
     */
    public void addReferences(Set<MetadataFileItem> references) {
    this.references.addAll(references);
  }

    /**
     * Gets references.
     *
     * @return the references
     */
    public Set<MetadataFileItem> getReferences() {
    return references;
  }
}
