package com.microsoft.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microsoft.util.YamlUtil;
import java.io.File;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * The type Metadata file.
 */
public class MetadataFile implements YmlFile {

  private static final String METADATA_FILE_HEADER = "### YamlMime:ManagedReference\n";
  private final String outputPath;
  private String fileName;
  private Set<MetadataFileItem> items = new LinkedHashSet<>();
  private Set<MetadataFileItem> references = new LinkedHashSet<>();

    /**
     * Instantiates a new Metadata file.
     *
     * @param outputPath the output path
     * @param fileName   the file name
     */
    public MetadataFile(String outputPath, String fileName) {
    this.outputPath = outputPath;
    this.fileName = fileName;
  }

    /**
     * Gets items.
     *
     * @return the items
     */
    public Set<MetadataFileItem> getItems() {
    return items;
  }

    /**
     * Gets references.
     *
     * @return the references
     */
    public Set<MetadataFileItem> getReferences() {
    return references;
  }

  @JsonIgnore
  @Override
  public String getFileContent() {
    Set<MetadataFileItem> sortedSet = new TreeSet<>(this.items);
    this.items = sortedSet;
    return METADATA_FILE_HEADER + YamlUtil.objectToYamlString(this);
  }

  @JsonIgnore
  @Override
  public String getFileNameWithPath() {
    return outputPath + File.separator + fileName;
  }

    /**
     * Gets file name.
     *
     * @return the file name
     */
    @JsonIgnore
  public String getFileName() {
    return fileName;
  }

    /**
     * Sets file name.
     *
     * @param fileName the file name
     */
    public void setFileName(String fileName) {
    this.fileName = fileName;
  }
}