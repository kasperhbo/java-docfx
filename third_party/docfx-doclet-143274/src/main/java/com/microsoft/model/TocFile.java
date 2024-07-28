package com.microsoft.model;

import com.microsoft.util.YamlUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TocFile extends ArrayList<TocItem> implements YmlFile {

    private static final String  TOC_FILE_HEADER = "### YamlMime:TableOfContent\n";
    private static final String  TOC_FILE_NAME   = "toc.yml";
    private final        String  outputPath;
    private final        String  projectName;
    private final        boolean disableChangelog;

    private final boolean disableLibraryOverview;

    public TocFile(
            String outputPath,
            String projectName,
            boolean disableChangelog,
            boolean disableLibraryOverview
    ) {
        this.outputPath             = outputPath;
        this.projectName            = projectName;
        this.disableChangelog       = disableChangelog;
        this.disableLibraryOverview = disableLibraryOverview;
    }

    public void addTocItem(TocItem packageTocItem) {
        add(packageTocItem);
    }

    protected void sortByUid() {
        this.sort((a, b) -> a.getUid().compareToIgnoreCase(b.getUid()));
    }

    @Override
    public String getFileContent() {
        sortByUid();
        List<Object> tocContents =
                new TocContents(
                        projectName,
                        disableChangelog,
                        disableLibraryOverview,
                        this).getContents();
        return TOC_FILE_HEADER + YamlUtil.objectToYamlString(tocContents);
    }

    @Override
    public String getFileNameWithPath() {
        return outputPath + File.separator + TOC_FILE_NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TocFile tocFile = (TocFile) o;

        return
                disableChangelog == tocFile.disableChangelog &&
                disableLibraryOverview == tocFile.disableLibraryOverview &&
                Objects.equals(outputPath, tocFile.outputPath) &&
                Objects.equals(projectName, tocFile.projectName);
    }

    @Override
    public int hashCode() {
        int result = outputPath != null ? outputPath.hashCode() : 0;
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (disableChangelog ? 1 : 0);
        result = 31 * result + (disableLibraryOverview ? 1 : 0);
        return result;
    }
}
