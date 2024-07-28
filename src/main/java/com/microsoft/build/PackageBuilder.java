/*
 * Copyright 2021 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.microsoft.build;

import com.google.docfx.doclet.RepoMetadata;
import com.microsoft.lookup.PackageLookup;
import javax.lang.model.element.PackageElement;

/**
 * The type Package builder.
 */
class PackageBuilder {
  private final PackageLookup packageLookup;
  private final String outputPath;
  private final ReferenceBuilder referenceBuilder;

    /**
     * Instantiates a new Package builder.
     *
     * @param packageLookup    the package lookup
     * @param outputPath       the output path
     * @param referenceBuilder the reference builder
     */
    PackageBuilder(
      PackageLookup packageLookup, String outputPath, ReferenceBuilder referenceBuilder) {
    this.packageLookup = packageLookup;
    this.outputPath = outputPath;
    this.referenceBuilder = referenceBuilder;
  }

    /**
     * Build package overview file package overview file.
     *
     * @param packageElement     the package element
     * @param repoMetadata       the repo metadata
     * @param artifactVersion    the artifact version
     * @param recommendedPackage the recommended package
     * @return the package overview file
     */
    PackageOverviewFile buildPackageOverviewFile(
      PackageElement packageElement,
      RepoMetadata repoMetadata,
      String artifactVersion,
      String recommendedPackage) {
    String status = packageLookup.extractStatus(packageElement);
    String fileName = packageElement.getQualifiedName() + ".md";
    PackageOverviewFile packageOverviewFile =
        new PackageOverviewFile(
            outputPath,
            fileName,
            repoMetadata,
            packageElement,
            status,
            packageLookup,
            referenceBuilder,
            artifactVersion,
            recommendedPackage);
    return packageOverviewFile;
  }
}