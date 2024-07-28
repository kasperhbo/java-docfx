package com.microsoft.doclet;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.microsoft.util.OptionsFileUtil;
import java.util.ArrayList;
import java.util.List;
import javax.tools.ToolProvider;

/**
 * To use runner just pass as commandline param to main method: - name of file with doclet name,
 * parameter file, and argument file.
 *
 * <p>For example: <code>
 * java DocletRunner /Users/brk/_hellebrekers/CUSTOMER/ALCON/overtheweeken/alconbus/target/site/apidocs/options
 * repoMetadataFilePath=/Users/brk/_hellebrekers/CUSTOMER/ALCON/overtheweeken/alconbus/repo-metadata.yaml
 * java DocletRunner $HOME/java-aiplatform/target/site/apidocs/options
 * $HOME/java-aiplatform/target/site/apidocs/argfile</code>
 */
public class DocletRunner {

  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
   /* if (args.length < 1) {
      System.err.println("Usage: java DocletRunner <options file> <argfile>");
      return;
    }*/

    String firstArg = "/Users/brk/_hellebrekers/0.VISSUALLINK/repos/Product/core/isacore/target/site/apidocs/options";
    String secondArg = "/Users/brk/_hellebrekers/0.VISSUALLINK/repos/Product/core/isacore/target/site/apidocs/packages";

    args = new String[] {firstArg, secondArg};

    run(
        args,
        new EnvironmentToArgumentsBuilder()
            .addIfExists("artifactVersion")
            .addIfExists("librariesBomVersion")
            .addIfExists("repoMetadataFilePath")
            .build());
  }

  /**
   * Run.
   *
   * @param args the args
   * @param env  the env
   */
  @VisibleForTesting
  static void run(final String[] args, List<String> env) {
    List<String> combined = new ArrayList<>(env);
    for (String arg : args) {
      if (!(new java.io.File(arg)).isFile()) {
        System.err.println(String.format("File '%s' does not exist", args[0]));
      }
      combined.addAll(OptionsFileUtil.processOptionsFile(arg));
    }
    ToolProvider.getSystemDocumentationTool()
        .run(null, null, null, combined.toArray(new String[0]));
  }

  /**
   * The type Environment to arguments builder.
   */
  @VisibleForTesting
  static class EnvironmentToArgumentsBuilder {
    private final ImmutableList.Builder<String> env = new ImmutableList.Builder<>();

    /**
     * Add if exists environment to arguments builder.
     *
     * @param name the name
     * @return the environment to arguments builder
     */
    public EnvironmentToArgumentsBuilder addIfExists(String name) {
      String value = System.getenv(name);
      if (value != null) {
        return add(name, value);
      }
      return this;
    }

    /**
     * Add environment to arguments builder.
     *
     * @param name  the name
     * @param value the value
     * @return the environment to arguments builder
     */
    @VisibleForTesting
    EnvironmentToArgumentsBuilder add(String name, String value) {
      env.add("-" + name, value);
      return this;
    }

    /**
     * Build immutable list.
     *
     * @return the immutable list
     */
    public ImmutableList<String> build() {
      return env.build();
    }
  }
}
