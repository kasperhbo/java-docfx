package com.microsoft.model;

/**
 * The type Method parameter.
 */
public class MethodParameter {

  private final String id;
  private final String type;
  private String description;

    /**
     * Instantiates a new Method parameter.
     *
     * @param id          the id
     * @param type        the type
     * @param description the description
     */
    public MethodParameter(String id, String type, String description) {
    this.id = id;
    this.type = type;
    this.description = description;
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
     * Gets type.
     *
     * @return the type
     */
    public String getType() {
    return type;
  }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
    return description;
  }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
    this.description = description;
  }
}
