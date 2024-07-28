package com.microsoft.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The type File util test.
 */
public class FileUtilTest {

  private final String ROOT_DIR = "target/dir1";
  private final String FILE_NAME = ROOT_DIR + "/dir2/out.txt";

    /**
     * Sets .
     *
     * @throws IOException the io exception
     */
    @Before
  public void setup() throws IOException {
    deleteDirectory(ROOT_DIR);
  }

    /**
     * Tear down.
     *
     * @throws IOException the io exception
     */
    @After
  public void tearDown() throws IOException {
    deleteDirectory(ROOT_DIR);
  }

    /**
     * Dump to file with directory creation.
     *
     * @throws IOException the io exception
     */
    @Test
  public void dumpToFileWithDirectoryCreation() throws IOException {
    String content = "Bla-bla content";

    FileUtil.dumpToFile(content, FILE_NAME);

    assertTrue("New file should appear", Files.exists(Paths.get(FILE_NAME)));
    assertEquals("Invalid file content", Files.readString(Paths.get(FILE_NAME)), content);
  }

    /**
     * Dump to file for existing non empty directory.
     *
     * @throws IOException the io exception
     */
    @Test
  public void dumpToFileForExistingNonEmptyDirectory() throws IOException {
    createDirectoryWithFile(ROOT_DIR + "/dir2/tmp1.txt");
    String content = "Bla-bla content";

    FileUtil.dumpToFile(content, FILE_NAME);

    assertTrue(
        "Existing file should not be deleted", Files.exists(Path.of(ROOT_DIR + "/dir2/tmp1.txt")));
    assertTrue("New file should appear", Files.exists(Paths.get(FILE_NAME)));
    assertEquals("Invalid file content", Files.readString(Paths.get(FILE_NAME)), content);
  }

    /**
     * Delete directory.
     *
     * @param pathString the path string
     * @throws IOException the io exception
     */
    public static void deleteDirectory(String pathString) throws IOException {
    Path path = Paths.get(pathString);
    if (Files.exists(path)) {
      Files.walk(path).sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
    }
  }

    /**
     * Create directory with file.
     *
     * @param pathString the path string
     * @throws IOException the io exception
     */
    public static void createDirectoryWithFile(String pathString) throws IOException {
    Path path = Paths.get(pathString);
    Files.createDirectories(path.getParent());
    Files.createFile(path);
  }
}
