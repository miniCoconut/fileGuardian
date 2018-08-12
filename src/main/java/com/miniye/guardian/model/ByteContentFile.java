package com.miniye.guardian.model;

import java.io.*;

public class ByteContentFile extends GenericFile {
    private static byte[] fileBuffer = new byte[allowedFileSize];

    public ByteContentFile(String filename) throws Exception {
        super(filename);
    }

    @Override
    public Object getContent() {
        return fileBuffer;
    }

    @Override
    public void loadFile() throws IOException {
        if(!fileIsLegal()) {
            throw new IOException("File Size exceeds the limits");
        }
        BufferedInputStream bufferedInputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            bufferedInputStream.read(fileBuffer);
            lastModif = file.lastModified();
            bufferedInputStream.close();
        } finally {
            if(bufferedInputStream != null) {
                try {
                    bufferedInputStream.close();
                } catch (InterruptedIOException e) {
                    Thread.currentThread().interrupt();
                } catch (Throwable var18) {

                }
            }
        }

    }
}
