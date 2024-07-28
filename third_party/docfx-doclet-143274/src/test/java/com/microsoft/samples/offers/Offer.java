package com.microsoft.samples.offers;

/**
 * Represents a form of product availability to customer
 */
public class Offer {
    /**
     * Initializes a new instance of the Offer class.
     */
    public Offer() {}

  /**
   * Gets or sets qualifications required by the Partner in order to purchase the offer for a
   * customer.
   */
  private String[] __ResellerQualifications;

    /**
     * Get reseller qualifications string [ ].
     *
     * @return the string [ ]
     */
    public String[] getResellerQualifications() {
    return __ResellerQualifications;
  }

    /**
     * Sets reseller qualifications.
     *
     * @param value the value
     */
    public void setResellerQualifications(String[] value) {
    __ResellerQualifications = value;
  }

  /**
   * Gets or sets qualifications required by the customer for the partner to purchase it for the
   * customer.
   */
  private String[] __ReselleeQualifications;

    /**
     * Get resellee qualifications string [ ].
     *
     * @return the string [ ]
     */
    public String[] getReselleeQualifications() {
    return __ReselleeQualifications;
  }

    /**
     * Sets resellee qualifications.
     *
     * @param value the value
     */
    public void setReselleeQualifications(String[] value) {
    __ReselleeQualifications = value;
  }
}
