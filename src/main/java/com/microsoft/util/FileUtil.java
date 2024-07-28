package com.microsoft.util;

import com.microsoft.build.PackageOverviewFile;
import com.microsoft.model.LibraryOverviewFile;
import com.microsoft.model.YmlFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * The type File util.
 */
public class FileUtil {
  private FileUtil() {
    // hide implicit public constructor
  }

  /**
   * Dump string to file. Create required folders when needed  @param content the content
   *
   * @param fileName the file name
   */
  public static void dumpToFile(String content, String fileName) {

    try {
      Path path = Paths.get(fileName);
      Files.createDirectories(path.getParent());
      Files.write(path, content.getBytes());
    } catch (IOException ioe) {
      throw new CreateFileException("Error during dump to file", ioe);
    }
  }

  /**
   * Dump to file.
   *
   * @param ymlFile the yml file
   */
  public static void dumpToFile(YmlFile ymlFile) {
    dumpToFile(ymlFile.getFileContent(), ymlFile.getFileNameWithPath());
  }

  /**
   * Dump to file.
   *
   * @param libraryOverviewFile the library overview file
   */
  public static void dumpToFile(LibraryOverviewFile libraryOverviewFile) {
    dumpToFile(libraryOverviewFile.getFileContent(), libraryOverviewFile.getFileNameWithPath());
  }

  /**
   * Dump to file.
   *
   * @param packageOverviewFile the package overview file
   */
  public static void dumpToFile(PackageOverviewFile packageOverviewFile) {
    dumpToFile(packageOverviewFile.getFileContent(), packageOverviewFile.getFileNameWithPath());
  }

  private static final class CreateFileException extends RuntimeException {
    /**
     * Instantiates a new Create file exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public CreateFileException(String message, Throwable cause) {
      super(message, cause);
    }
  }

}
