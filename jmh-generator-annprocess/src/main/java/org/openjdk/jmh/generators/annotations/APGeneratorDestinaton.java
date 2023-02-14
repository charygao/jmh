
package org.openjdk.jmh.generators.annotations;

import org.openjdk.jmh.generators.core.GeneratorDestination;
import org.openjdk.jmh.generators.core.MetadataInfo;
import org.openjdk.jmh.util.Utils;

import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.StandardLocation;
import java.io.*;

public class APGeneratorDestinaton implements GeneratorDestination {

    private final ProcessingEnvironment processingEnv;

    public APGeneratorDestinaton(RoundEnvironment roundEnv, ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    @Override
    public OutputStream newResource(String resourcePath) throws IOException {
        return processingEnv.getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", resourcePath).openOutputStream();
    }

    @Override
    public InputStream getResource(String resourcePath) throws IOException {
        return processingEnv.getFiler().getResource(StandardLocation.CLASS_OUTPUT, "", resourcePath).openInputStream();
    }

    @Override
    public Writer newClass(String className, String originatingClassName) throws IOException {
        Filer filer = processingEnv.getFiler();
        if (originatingClassName != null) {
            TypeElement originatingType = processingEnv.getElementUtils().getTypeElement(originatingClassName);
            return filer.createSourceFile(className, originatingType).openWriter();
        } else {
            return filer.createSourceFile(className).openWriter();
        }
    }

    @Override
    public void printError(String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message);
    }

    @Override
    public void printError(String message, MetadataInfo element) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, message, ((APMetadataInfo)element).getElement());
    }

    @Override
    public void printError(String message, Throwable throwable) {
        printError(message + " " + Utils.throwableToString(throwable));
    }

    @Override
    public void printWarning(String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, message);
    }

    @Override
    public void printWarning(String message, MetadataInfo element) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, message, ((APMetadataInfo)element).getElement());
    }

    @Override
    public void printWarning(String message, Throwable throwable) {
        printWarning(message + " " + Utils.throwableToString(throwable));
    }

    @Override
    public void printNote(String message) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
    }
}
