package com.miniye.guardian.utils;

import com.miniye.guardian.operator.ClassOperator;

import java.io.File;

import java.net.URL;
import java.net.URLClassLoader;

import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class LoadClassUitils {
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    public static URLClassLoader getUrlClassLoader(URL url) {
        return new URLClassLoader(new URL[] {url} ,Thread.currentThread().getContextClassLoader());
    }

    public static Class<?> loadClass(URL url, String className, boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className, isInitialized, getUrlClassLoader(url));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return cls;
    }

    public static Map<String, ClassOperator> getClassMap(String jarPackagePath) {
        Map<String, ClassOperator> classMap = new HashMap<String, ClassOperator>();
        try {
            File jarPackage = new File(jarPackagePath);
            if (jarPackage.isFile() && jarPackage.getName().endsWith(".jar")) {
                URL jarUrl = new URL("file:" + jarPackage.getAbsolutePath());
                URLClassLoader urlClassLoader = getUrlClassLoader(jarUrl);
                JarFile jarFile = new JarFile(jarPackage);
                if (jarFile != null) {
                    Enumeration<JarEntry> jarEntries = jarFile.entries();
                    while (jarEntries.hasMoreElements()) {
                        JarEntry jarEntry = jarEntries.nextElement();
                        String jarEntryName = jarEntry.getName();
                        if (jarEntryName.endsWith(".class")) {
                            String className = jarEntryName.substring(0, jarEntryName.lastIndexOf("."))
                                    .replaceAll("/", ".");
                            Class cls = urlClassLoader.loadClass(className);
                            ClassOperator classOperator = new ClassOperator();
                            classOperator.setCls(cls);
                            classMap.put(className, classOperator);
                        }
                    }

                }

            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return classMap;
    }


}
