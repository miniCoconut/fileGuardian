package com.miniye.guardian.filepojo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.Properties;

public class PropertyFile extends GenericFile {
    private static Properties props = new Properties();

    public PropertyFile(String filePath) throws Exception {
        super(filePath);
    }

    @Override
    public Properties getContent() {
        return props;
    }

    @Override
    public void loadFile() throws IOException {
        if(!fileIsLegal()) {
            throw new IOException("File Size exceeds the limits");
        }
        FileInputStream inStream = null;
        try {
            inStream = new FileInputStream(file);
            props.load(inStream);
            System.out.println(props);
            lastModif = file.lastModified();
            inStream.close();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }finally {
            if(inStream != null) {
                try {
                    inStream.close();
                } catch (InterruptedIOException e) {
                    Thread.currentThread().interrupt();
                } catch (Throwable var18) {

                }
            }
        }
    }
}
