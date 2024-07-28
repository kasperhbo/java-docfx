package com.microsoft.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Return.
 */
public class Return {

  @JsonProperty("type")
  private final String returnType;

  @JsonProperty("description")
  private String returnDescription;

    /**
     * Instantiates a new Return.
     *
     * @param returnType        the return type
     * @param returnDescription the return description
     */
    public Return(String returnType, String returnDescription) {
    this.returnType = returnType;
    this.returnDescription = returnDescription;
  }

    /**
     * Instantiates a new Return.
     *
     * @param returnType the return type
     */
    public Return(String returnType) {
    this.returnType = returnType;
  }

    /**
     * Gets return type.
     *
     * @return the return type
     */
    public String getReturnType() {
    return returnType;
  }

    /**
     * Gets return description.
     *
     * @return the return description
     */
    public String getReturnDescription() {
    return returnDescription;
  }

    /**
     * Sets return description.
     *
     * @param returnDescription the return description
     */
    public void setReturnDescription(String returnDescription) {
    this.returnDescription = returnDescription;
  }
}
