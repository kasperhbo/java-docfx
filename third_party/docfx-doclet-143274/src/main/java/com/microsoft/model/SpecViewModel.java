package com.microsoft.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.*;
import org.apache.commons.lang3.StringUtils;

/**
 * The type Spec view model.
 */
@JsonPropertyOrder({"uid", "name", "fullName", "isExternal", "href"})
public class SpecViewModel {

  private String uid;
  private String name;
  private String fullName;
  private boolean isExternal;
  private String href;

    /**
     * Instantiates a new Spec view model.
     *
     * @param uid      the uid
     * @param fullName the full name
     */
    public SpecViewModel(String uid, String fullName) {
    this.uid = uid;
    this.name = getShortName(fullName);
    this.fullName = fullName;
  }

    /**
     * Gets uid.
     *
     * @return the uid
     */
    public String getUid() {
    return uid;
  }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
    return name;
  }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
    return fullName;
  }

    /**
     * Gets short name.
     *
     * @param fullName the full name
     * @return the short name
     */
    String getShortName(String fullName) {

    StringBuilder singleValue = new StringBuilder();
    Optional.ofNullable(fullName)
        .ifPresent(
            Param -> {
              List<String> strList = new ArrayList<>();
              strList = Arrays.asList(StringUtils.split(Param, "."));
              Collections.reverse(strList);
              singleValue.append(strList.get(0));
            });
    return singleValue.toString();
  }

    /**
     * Sets is external.
     *
     * @param isExternal the is external
     */
    public void setIsExternal(boolean isExternal) {
    this.isExternal = isExternal;
  }

    /**
     * Gets is external.
     *
     * @return the is external
     */
    public boolean getIsExternal() {
    return isExternal;
  }

    /**
     * Sets href.
     *
     * @param href the href
     */
    public void setHref(String href) {
    this.href = href;
  }

    /**
     * Gets href.
     *
     * @return the href
     */
    public String getHref() {
    return href;
  }
}
