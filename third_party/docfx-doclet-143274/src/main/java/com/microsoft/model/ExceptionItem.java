package com.microsoft.model;

/**
 * The type Exception item.
 */
public class ExceptionItem {

  private final String type;
  private final String description;

    /**
     * Instantiates a new Exception item.
     *
     * @param type        the type
     * @param description the description
     */
    public ExceptionItem(String type, String description) {
    this.type = type;
    this.description = description;
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
}
