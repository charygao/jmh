
package org.openjdk.jmh.generators.core;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FileSystemDestination implements GeneratorDestination {

    private final File resourceDir;
    private final File sourceDir;
    private final List<SourceError> sourceErrors;
    private final List<SourceWarning> sourceWarnings;

    public FileSystemDestination(File resourceDir, File sourceDir) {
        this.resourceDir = resourceDir;
        this.sourceDir = sourceDir;
        this.sourceErrors = new ArrayList<>();
        this.sourceWarnings = new ArrayList<>();
    }

    @Override
    public OutputStream newResource(String resourcePath) throws IOException {
        String pathName = resourceDir.getAbsolutePath() + "/" + resourcePath;
        File p = new File(pathName.substring(0, pathName.lastIndexOf("/")));
        if (!p.mkdirs() && !p.isDirectory()) {
            throw new IOException("Unable to create " + p.getAbsolutePath());
        }
        return new FileOutputStream(new File(pathName));
    }

    @Override
    public InputStream getResource(String resourcePath) throws IOException {
        String pathName = resourceDir.getAbsolutePath() + "/" + resourcePath;
        File p = new File(pathName.substring(0, pathName.lastIndexOf("/")));
        if (!p.isFile() && !p.exists()) {
            throw new IOException("Unable to open " + pathName);
        }
        return new FileInputStream(new File(pathName));
    }

    @Override
    public Writer newClass(String className, String originatingClassName) throws IOException {
        String pathName = sourceDir.getAbsolutePath() + "/" + className.replaceAll("\\.", "/");
        File p = new File(pathName.substring(0, pathName.lastIndexOf("/")));
        if (!p.mkdirs() && !p.isDirectory()) {
            throw new IOException("Unable to create " + p.getAbsolutePath());
        }
        return new FileWriter(new File(pathName + ".java"));
    }

    @Override
    public void printError(String message) {
        sourceErrors.add(new SourceError(message));
    }

    @Override
    public void printError(String message, MetadataInfo element) {
        sourceErrors.add(new SourceElementError(message, element));
    }

    @Override
    public void printError(String message, Throwable throwable) {
        sourceErrors.add(new SourceThrowableError(message, throwable));
    }

    public boolean hasErrors() {
        return !sourceErrors.isEmpty();
    }

    public Collection<SourceError> getErrors() {
        return sourceErrors;
    }

    @Override
    public void printWarning(String message) {
        sourceWarnings.add(new SourceWarning(message));
    }

    @Override
    public void printWarning(String message, MetadataInfo element) {
        sourceWarnings.add(new SourceElementWarning(message, element));
    }

    @Override
    public void printWarning(String message, Throwable throwable) {
        sourceWarnings.add(new SourceThrowableWarning(message, throwable));
    }

    public boolean hasWarnings() {
        return !sourceWarnings.isEmpty();
    }

    public Collection<SourceWarning> getWarnings() {
        return sourceWarnings;
    }

    @Override
    public void printNote(String message) {
        // do nothing
    }
}
