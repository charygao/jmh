
package org.openjdk.jmh.generators;

import org.openjdk.jmh.generators.annotations.APGeneratorDestinaton;
import org.openjdk.jmh.generators.annotations.APGeneratorSource;
import org.openjdk.jmh.generators.core.BenchmarkGenerator;
import org.openjdk.jmh.generators.core.GeneratorDestination;
import org.openjdk.jmh.generators.core.GeneratorSource;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.Set;

@SupportedAnnotationTypes("org.openjdk.jmh.annotations.*")
public class BenchmarkProcessor extends AbstractProcessor {

    private final BenchmarkGenerator generator = new BenchmarkGenerator();

    @Override
    public SourceVersion getSupportedSourceVersion() {
        // We may claim to support the latest version, since we are not using
        // any version-specific extensions.
        return SourceVersion.latest();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        GeneratorSource source = new APGeneratorSource(roundEnv, processingEnv);
        GeneratorDestination destination = new APGeneratorDestinaton(roundEnv, processingEnv);
        if (!roundEnv.processingOver()) {
            generator.generate(source, destination);
        } else {
            generator.complete(source, destination);
        }
        return true;
    }

}
