package com.miniye.guardian.operator;

import com.miniye.guardian.filepojo.GenericFile;
import com.miniye.guardian.utils.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileOperator {
    private Logger log = LoggerFactory.getLogger(FileOperator.class);

    //    file Class to guard
    protected Class clazz;
    //    file instance
    public GenericFile gFile;
    //    set guard = false to disable hot reload
    private boolean guard = true;

    protected String absoluteFilePath;

    public FileOperator(Class clazz, String absoluteFilePath) {
        this.clazz = clazz;
        this.absoluteFilePath = absoluteFilePath;

        //        Init and load gFile
        try {
            Class[] parameterTypes = {String.class};
            Object[] parameters = {absoluteFilePath};
            gFile = (GenericFile) ReflectionUtils.newInstance4ConstructorWithArgs(clazz, parameterTypes, parameters);
        } catch (Exception e) {
            log.error("fail to new GenericFile instance:" + e);
        }
    }


    public boolean isGuard() {
        return guard;
    }

    public void setGuard(boolean guard) {
        this.guard = guard;
    }

    public Object getFileContent() {
        return gFile.getContent();
    }

    public String getFilename() {
        return gFile.getFilename();
    }

    public long getFileLastModified(String filename) {
        return gFile.getLastModif();
    }

    public String getAbsoluteFilePath() {
        return absoluteFilePath;
    }
}
