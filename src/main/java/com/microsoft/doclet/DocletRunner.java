package com.microsoft.doclet;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.ImmutableList;
import com.microsoft.util.OptionsFileUtil;
import nl.kasper.de.bruin.util.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.tools.ToolProvider;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
    private static final Logger logger = LoggerFactory.getLogger(DocletRunner.class);

    static {
        PropertyUtil.loadPropertyFileIfNotLoaded("etc/doclet-runner.properties", true);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        logger.info("Starting doclet runner");

        final String optionsFilePath  = PropertyUtil.getProperty("optionsFile");
        final String packagesFilePath = PropertyUtil.getProperty("packagesFile");
        final String outputPath       = PropertyUtil.getProperty("outputPath");

        List<String> arguments = List.of(
                optionsFilePath,
                packagesFilePath
        );

        logger.debug("Args: {}", arguments);

        run(optionsFilePath, packagesFilePath, outputPath,
                new EnvironmentToArgumentsBuilder()
                        .addIfExists("artifactVersion")
                        .addIfExists("librariesBomVersion")
                        .addIfExists("repoMetadataFilePath")
                        .build());
    }

    /**
     * Run.
     *
     * @param optionsFilePath the options file path
     * @param packageFilePath the package file path
     * @param outputPath      the output path
     * @param env             the env
     */
    @VisibleForTesting
    static void run(final String optionsFilePath,
                    final String packageFilePath,
                    final String outputPath,
                    List<String> env) {

        if (optionsFilePath == null || packageFilePath == null || outputPath == null) {
            throw new IllegalArgumentException("optionsFilePath, packageFilePath, and outputPath must be provided");
        }

        if (env == null) {
            env = new ArrayList<>();
        }

        if (!Files.exists(Path.of(optionsFilePath))) {
            throw new IllegalArgumentException("options file does not exist: " + optionsFilePath);
        }

        if (!Files.exists(Path.of(packageFilePath))) {
            throw new IllegalArgumentException("package file does not exist: " + packageFilePath);
        }

        List<String> combined = new ArrayList<>(env);

        combined.addAll(OptionsFileUtil.processOptionsFile(optionsFilePath));
        combined.addAll(OptionsFileUtil.processOptionsFile(packageFilePath));
        combined.add("-d");



        combined.add(outputPath);

        ToolProvider.getSystemDocumentationTool()
                .run(null,
                        null,
                        null,
                        combined.toArray(new String[0]));

        logger.info("DocletRunner finished");

    }

    /**
     * The type Environment to arguments builder.
     */
    @VisibleForTesting
    static final class EnvironmentToArgumentsBuilder {
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
