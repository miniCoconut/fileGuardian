package com.miniye.guardian.filepojo;

import com.miniye.guardian.Constants;

import java.io.File;

public abstract class GenericFile {

    protected File file;
    protected long lastModif = 0L;
    protected static int allowedFileSize = Constants.ALLOWED_FILE_SIZE;

    public GenericFile(String filePath) throws Exception {
        this.file = new File(filePath);
        this.lastModif = file.lastModified();
        this.loadFile();
    }

    public String getFilename() {
        return file.getName();
    }

    public long getLastModif() {
        return lastModif;
    }

    public void setAllowedFileSize(int allowedFileSize) {
        this.allowedFileSize = allowedFileSize;
    }

    public boolean fileIsLegal() {
        return file.length() <= allowedFileSize;
    }

    public abstract Object getContent();

    public boolean fileExists() {
        return file.exists();
    }

    public boolean fileChanged() {
        return this.lastModif != file.lastModified();
    }
    
    public abstract void loadFile() throws Exception;
    
    
}
