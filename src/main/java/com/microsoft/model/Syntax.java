package com.microsoft.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * The type Syntax.
 */
public class Syntax {

  private String content;
  private List<MethodParameter> parameters;

  @JsonProperty("return")
  private Return returnValue;

  private List<TypeParameter> typeParameters;

  /**
   * Gets content.
   *
   * @return the content
   */
  public String getContent() {
    return content;
  }

  /**
   * Sets content.
   *
   * @param content the content
   */
  public void setContent(String content) {
    this.content = content;
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
   * Sets parameters.
   *
   * @param parameters the parameters
   */
  public void setParameters(List<MethodParameter> parameters) {
    this.parameters = parameters;
  }

  /**
   * Gets return value.
   *
   * @return the return value
   */
  public Return getReturnValue() {
    return returnValue;
  }

  /**
   * Sets return value.
   *
   * @param returnValue the return value
   */
  public void setReturnValue(Return returnValue) {
    this.returnValue = returnValue;
  }

  /**
   * Gets type parameters.
   *
   * @return the type parameters
   */
  public List<TypeParameter> getTypeParameters() {
    return typeParameters;
  }

  /**
   * Sets type parameters.
   *
   * @param typeParameters the type parameters
   */
  public void setTypeParameters(List<TypeParameter> typeParameters) {
    this.typeParameters = typeParameters;
  }
}
