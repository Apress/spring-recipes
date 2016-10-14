package com.apress.springrecipes.replicator;

import java.io.IOException;


public interface FileReplicator {
    public String getSrcDir();

    public void setSrcDir(String srcDir);

    public String getDestDir();

    public void setDestDir(String destDir);

    public void replicate() throws IOException;
}
