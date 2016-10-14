package com.apress.springrecipes.replicator;

import org.springframework.scheduling.annotation.Scheduled;

import java.io.File;
import java.io.IOException;


public class FileReplicatorImpl implements FileReplicator {
    private String srcDir;
    private String destDir;
    private FileCopier fileCopier;

    public String getSrcDir() {
        return srcDir;
    }

    public void setSrcDir(String srcDir) {
        this.srcDir = srcDir;
        revaluateDirectories();
    }

    public String getDestDir() {
        return destDir;
    }

    public void setDestDir(String destDir) {
        this.destDir = destDir;
        revaluateDirectories();
    }

    public void setFileCopier(FileCopier fileCopier) {
        this.fileCopier = fileCopier;
    }

    @Scheduled(fixedDelay = 60 * 1000)
    public synchronized void replicate() throws IOException {
        File[] files = new File(srcDir).listFiles();

        for (File file : files) {
            if (file.isFile()) {
                fileCopier.copyFile(srcDir, destDir, file.getName());
            }
        }
    }

    private void revaluateDirectories() {
        File src = new File(srcDir);
        File dest = new File(destDir);

        if (!src.exists()) {
            src.mkdirs();
        }

        if (!dest.exists()) {
            dest.mkdirs();
        }
    }
}
