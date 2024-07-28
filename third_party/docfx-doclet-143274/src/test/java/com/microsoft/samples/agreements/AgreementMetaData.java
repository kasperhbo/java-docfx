package com.microsoft.samples.agreements;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The AgreementMetaData provides metadata about the agreement type that partner can provide
 * confirmation of customer acceptance.
 */
public class AgreementMetaData {
  /** Gets or sets the unique identifier of an agreement template. */
  @JsonProperty("templateId")
  private String templateId;

    /**
     * Gets template id.
     *
     * @return the template id
     */
    public String getTemplateId() {
    return templateId;
  }

    /**
     * Sets template id.
     *
     * @param value the value
     */
    public void setTemplateId(String value) {
    templateId = value;
  }

  /** Gets or sets agreement type. */

  /**
   * Gets or sets URL to the agreement template.
   *
   * @deprecated
   */
  @Deprecated
  @JsonProperty("agreementLink")
  private String agreementLink;

    /**
     * Gets agreement link.
     *
     * @return the agreement link
     */
    public String getAgreementLink() {
    return agreementLink;
  }

    /**
     * Sets agreement link.
     *
     * @param value the value
     */
    public void setAgreementLink(String value) {
    agreementLink = value;
  }

  /** Gets or sets the version rank of an agreement template. */
  @JsonProperty("versionRank")
  private int versionRank;

    /**
     * Gets version rank.
     *
     * @return the version rank
     */
    public int getVersionRank() {
    return versionRank;
  }

    /**
     * Sets version rank.
     *
     * @param value the value
     */
    public void setVersionRank(int value) {
    versionRank = value;
  }
}
