package com.microsoft.samples;

import java.util.Collection;

/**
 * The type Link.
 */
public class Link {

    /**
     * Instantiates a new Link.
     */
    public Link() {}

  /**
   * Initializes a new instance of the Link class.
   *
   * @param uri The URI.
   */

  /**
   * Gets the URI.
   *
   * <p>/** Gets the method.
   */
  private String method;

    /**
     * Gets method.
     *
     * @return the method
     */
    public String getMethod() {
    return method;
  }

    /**
     * Sets method.
     *
     * @param value the value
     */
    public void setMethod(String value) {
    method = value;
  }

  /** Gets the link headers. */
  private Collection<KeyValuePair<String, String>> headers;

    /**
     * Gets headers.
     *
     * @return the headers
     */
    public Collection<KeyValuePair<String, String>> getHeaders() {
    return headers;
  }

    /**
     * Sets headers.
     *
     * @param value the value
     */
    public void setHeaders(Collection<KeyValuePair<String, String>> value) {
    headers = value;
  }
}
