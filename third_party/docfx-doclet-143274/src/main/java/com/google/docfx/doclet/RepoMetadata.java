package com.google.docfx.doclet;

// This parses .repo-metadata.json files to create a new library overview

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import jdk.javadoc.doclet.Reporter;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

/**
 * The type Repo metadata.
 */
public class RepoMetadata {

  @SerializedName("api_shortname")
  private String apiShortName;

  @SerializedName("name_pretty")
  private String namePretty;

  @SerializedName("product_documentation")
  private String productDocumentationUri;

  @SerializedName("api_description")
  private String apiDescription;

  @SerializedName("client_documentation")
  private String clientDocumentationUri;

  @SerializedName("rest_documentation")
  private String restDocumentationUri;

  @SerializedName("rpc_documentation")
  private String rpcDocumentationUri;

  @SerializedName("repo")
  private String repo;

  @SerializedName("repo_short")
  private String repoShort;

  @SerializedName("distribution_name")
  private String distributionName;

  @SerializedName("api_id")
  private String apiId;

  @SerializedName("recommended_package")
  private String recommendedPackage;

  private String artifactId;

    /**
     * Gets name pretty.
     *
     * @return the name pretty
     */
    public String getNamePretty() {
    return this.namePretty;
  }

    /**
     * Sets name pretty.
     *
     * @param namePretty the name pretty
     */
    public void setNamePretty(String namePretty) {
    this.namePretty = namePretty;
  }

    /**
     * Gets api short name.
     *
     * @return the api short name
     */
    public String getApiShortName() {
    return this.apiShortName;
  }

    /**
     * Sets api short name.
     *
     * @param apiShortName the api short name
     */
    public void setApiShortName(String apiShortName) {
    this.apiShortName = apiShortName;
  }

    /**
     * Gets product documentation uri.
     *
     * @return the product documentation uri
     */
    public String getProductDocumentationUri() {
    return this.productDocumentationUri;
  }

    /**
     * Gets rest documentation uri.
     *
     * @return the rest documentation uri
     */
    public Optional<String> getRestDocumentationUri() {
    return Optional.ofNullable(this.restDocumentationUri);
  }

    /**
     * Gets rpc documentation uri.
     *
     * @return the rpc documentation uri
     */
    public Optional<String> getRpcDocumentationUri() {
    return Optional.ofNullable(this.rpcDocumentationUri);
  }

    /**
     * Sets product documentation uri.
     *
     * @param productDocumentationUri the product documentation uri
     */
    public void setProductDocumentationUri(String productDocumentationUri) {
    this.productDocumentationUri = productDocumentationUri;
  }

    /**
     * Gets recommended package.
     *
     * @return the recommended package
     */
    public Optional<String> getRecommendedPackage() {
    return Optional.ofNullable(this.recommendedPackage);
  }

    /**
     * Gets api description.
     *
     * @return the api description
     */
    public String getApiDescription() {
    return this.apiDescription;
  }

    /**
     * Sets api description.
     *
     * @param apiDescription the api description
     */
    public void setApiDescription(String apiDescription) {
    this.apiDescription = apiDescription;
  }

    /**
     * Gets client documentation uri.
     *
     * @return the client documentation uri
     */
    public String getClientDocumentationUri() {
    return this.clientDocumentationUri;
  }

    /**
     * Sets client documentation uri.
     *
     * @param clientDocumentationUri the client documentation uri
     */
    public void setClientDocumentationUri(String clientDocumentationUri) {
    this.clientDocumentationUri = clientDocumentationUri;
  }

    /**
     * Gets repo.
     *
     * @return the repo
     */
    public String getRepo() {
    return this.repo;
  }

    /**
     * Sets repo.
     *
     * @param repo the repo
     */
    public void setRepo(String repo) {
    this.repo = repo;
  }

    /**
     * Gets repo short.
     *
     * @return the repo short
     */
    public String getRepoShort() {
    return this.repoShort;
  }

    /**
     * Sets repo short.
     *
     * @param repoShort the repo short
     */
    public void setRepoShort(String repoShort) {
    this.repoShort = repoShort;
  }

    /**
     * Gets distribution name.
     *
     * @return the distribution name
     */
    public String getDistributionName() {
    return this.distributionName;
  }

    /**
     * Sets distribution name.
     *
     * @param distributionName the distribution name
     */
    public void setDistributionName(String distributionName) {
    this.distributionName = distributionName;
  }

    /**
     * Gets api id.
     *
     * @return the api id
     */
    public String getApiId() {
    return this.apiId;
  }

    /**
     * Sets api id.
     *
     * @param apiId the api id
     */
    public void setApiId(String apiId) {
    this.apiId = apiId;
  }

    /**
     * Gets artifact id.
     *
     * @return the artifact id
     */
// artifactId is parsed from distributionName
  public String getArtifactId() {
    String substrings[] = this.distributionName.split(":");
    return substrings[substrings.length - 1];
  }

    /**
     * Gets github link.
     *
     * @return the github link
     */
// GithubLink is created from repo and repoShort
  public String getGithubLink() {
    String githubRootUri = "https://github.com/";
    String githubLink = githubRootUri + repo;
    if (Objects.equals(this.repo, "googleapis/google-cloud-java")
        || Objects.equals(this.repo, "googleapis/sdk-platform-java")) {
      githubLink = githubLink + "/tree/main/" + this.repoShort;
    }
    // Handwritten libraries have a different base URI
    else {
      githubLink = githubLink + "/tree/main/";
    }
    return githubLink;
  }

    /**
     * Gets maven link.
     *
     * @return the maven link
     */
// MavenLink is created from distributionName
  public String getMavenLink() {
    String mavenRootUri = "https://central.sonatype.com/artifact/";
    String substrings[] = this.distributionName.split(":");
    String groupName = substrings[0];
    String artifactId = substrings[substrings.length - 1];
    String mavenLink = mavenRootUri + groupName + "/" + artifactId;
    return mavenLink;
  }

    /**
     * Parse repo metadata repo metadata.
     *
     * @param fileName the file name
     * @return the repo metadata
     */
    public RepoMetadata parseRepoMetadata(String fileName) {
    Gson gson = new Gson();
    Path path = Paths.get(fileName);
    System.out.println("Parsing " + path.toAbsolutePath().normalize());
    try (FileReader reader = new FileReader(path.toFile())) {
      return gson.fromJson(reader, RepoMetadata.class);
    } catch (IOException e) {
      throw new RuntimeException(
          ".repo-metadata.json is not found @ " + path.toAbsolutePath().normalize(), e);
    }
  }
}
