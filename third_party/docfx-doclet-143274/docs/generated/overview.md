---
_layout: landing
---

# This is the **HOMEPAGE**.

# AlconBus overview (1)

## Key Reference Links

**Alcon Order Bus Description:** Keeps the conections between the Alcon Bus and Visuallink

<table>
   <tr>
     <td><a href="https://gitlab.com/overtheweeken/alcon-bus">Alcon Order Bus Product Reference</a></td>
     <td><a href="https://github.com/hellebrekers/alcon/alcon-bus/tree/main/">GitHub Repository (includes samples)</a></td>
     <td><a href="https://central.sonatype.com/artifact/nl.hellebrekers.isa.alcon.bus.order/AlconBus">Maven artifact</a></td>
   </tr>
 </table>

## Getting Started

In order to use this library, you first need to go through the following steps:

- [Install a JDK (Java Development Kit)](https://cloud.google.com/java/docs/setup#install_a_jdk_java_development_kit)
- [Select or create a Cloud Platform project](https://console.cloud.google.com/project)
- [Enable billing for your project](https://cloud.google.com/billing/docs/how-to/modify-project#enable_billing_for_a_project)
- [Enable the API](https://console.cloud.google.com/apis/library/order_bus.googleapis.com)
- [Set up authentication](https://cloud.google.com/docs/authentication/client-libraries)

## Use the Alcon Order Bus for Java

To ensure that your project uses compatible versions of the libraries
and their component artifacts, import `com.google.cloud:libraries-bom` and use
the BOM to specify dependency versions.  Be sure to remove any versions that you
set previously. For more information about
BOMs, see [Google Cloud Platform Libraries BOM](https://cloud.google.com/java/docs/bom).

### Maven

Import the BOM in the <code>dependencyManagement</code> section of your <code>pom.xml</code> file.
Include specific artifacts you depend on in the <code>dependencies</code> section, but don't
specify the artifacts' versions in the <code>dependencies</code> section.

The example below demonstrates how you would import the BOM and include the <code>AlconBus</code> artifact.

<pre class="prettyprint lang-xml devsite-click-to-copy">
<dependencyManagement>
 <dependencies>
   <dependency>
      <groupId>com.google.cloud</groupId>
      <artifactId>libraries-bom</artifactId>
      <version>1.0.0</version>
      <type>pom</type>
      <scope>import</scope>
   </dependency>
 </dependencies>
</dependencyManagement>

<dependencies>
 <dependency>
   <groupId>com.google.cloud</groupId>
   <artifactId>AlconBus</artifactId>
 </dependency>
</dependencies>
</pre>

### Gradle

BOMs are supported by default in Gradle 5.x or later. Add a <code>platform</code>
dependency on <code>com.google.cloud:libraries-bom</code> and remove the version from the
dependency declarations in the artifact's <code>build.gradle</code> file.

The example below demonstrates how you would import the BOM and include the <code>AlconBus</code> artifact.

<pre class="prettyprint lang-Groovy devsite-click-to-copy">
implementation platform('com.google.cloud:libraries-bom:1.0.0')
implementation 'nl.hellebrekers.isa.alcon.bus.order:AlconBus'
</pre>

The <code>platform</code> and <code>enforcedPlatform</code> keywords supply dependency versions
declared in a BOM. The <code>enforcedPlatform</code> keyword enforces the dependency
versions declared in the BOM and thus overrides what you specified.

For more details of the <code>platform</code> and <code>enforcedPlatform</code> keywords Gradle 5.x or higher, see
[Gradle: Importing Maven BOMs](https://docs.gradle.org/current/userguide/platforms.html#sub:bom_import).

If you're using Gradle 4.6 or later, add
<code>enableFeaturePreview('IMPROVED_POM_SUPPORT')</code> to your <code>settings.gradle</code> file. For details, see
[Gradle 4.6 Release Notes: BOM import](https://docs.gradle.org/4.6/release-notes.html#bom-import).
Versions of Gradle earlier than 4.6 don't support BOMs.</p>

### SBT

SBT [doesn't support BOMs](https://github.com/sbt/sbt/issues/4531). You can find
recommended versions of libraries from a particular BOM version on the
[dashboard](https://storage.googleapis.com/cloud-opensource-java-dashboard/com.google.cloud/libraries-bom/index.html)
and set the versions manually.
To use the latest version of this library, add this to your dependencies:

<pre class="prettyprint lang-Scala devsite-click-to-copy">
libraryDependencies += "com.google.cloud" % "AlconBus" % "1"
</pre>
