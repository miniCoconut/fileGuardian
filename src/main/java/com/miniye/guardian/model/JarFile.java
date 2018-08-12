package com.miniye.guardian.model;

import com.miniye.guardian.utils.LoadClassUitils;

import java.util.HashMap;
import java.util.Map;

public class JarFile extends GenericFile {

    private static Map<String, ClassBean> beanMap = new HashMap<String, ClassBean>();

    public JarFile(String jarFilePath) throws Exception{
        super(jarFilePath);
    }

    @Override
    public Object getContent() {
        return beanMap;
    }

    @Override
    public void loadFile() throws Exception {
        try {
            beanMap = LoadClassUitils.getClassMap(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
