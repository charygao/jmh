
package org.openjdk.jmh.generators.core;

import org.openjdk.jmh.annotations.CompilerControl;
import org.openjdk.jmh.runner.CompilerHints;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

class CompilerControlPlugin {

    private final SortedSet<String> lines = new TreeSet<>();

    private final Set<MethodInfo> defaultForceInlineMethods = new TreeSet<>(Comparator.comparing(MethodInfo::getQualifiedName));

    private final Set<String> alwaysDontInlineMethods = new TreeSet<>();

    public void defaultForceInline(MethodInfo methodInfo) {
        defaultForceInlineMethods.add(methodInfo);
    }

    public void alwaysDontInline(String className, String methodName) {
        alwaysDontInlineMethods.add(getName(className, methodName));
    }

    public void process(GeneratorSource source, GeneratorDestination destination) {
        try {
            for (MethodInfo element : BenchmarkGeneratorUtils.getMethodsAnnotatedWith(source, CompilerControl.class)) {
                CompilerControl ann = element.getAnnotation(CompilerControl.class);
                if (ann == null) {
                    throw new IllegalStateException("No annotation");
                }

                CompilerControl.Mode command = ann.value();
                lines.add(command.command() + "," + getName(element));
            }

            for (MethodInfo element : defaultForceInlineMethods) {
                // Skip methods annotated explicitly
                if (element.getAnnotation(CompilerControl.class) != null) continue;

                // Skip methods in classes that are annotated explicitly
                if (element.getDeclaringClass().getAnnotation(CompilerControl.class) != null) continue;

                lines.add(CompilerControl.Mode.INLINE.command() + "," + getName(element));
            }

            for (String element : alwaysDontInlineMethods) {
                lines.add(CompilerControl.Mode.DONT_INLINE.command() + "," + element);
            }

            for (ClassInfo element : BenchmarkGeneratorUtils.getClassesAnnotatedWith(source, CompilerControl.class)) {
                CompilerControl ann = element.getAnnotation(CompilerControl.class);
                if (ann == null) {
                    throw new IllegalStateException("No annotation");
                }

                CompilerControl.Mode command = ann.value();
                lines.add(command.command() + "," + getName(element));
            }

        } catch (Throwable t) {
            destination.printError("Compiler control generators had thrown the unexpected exception", t);
        }
    }

    public void finish(GeneratorSource source, GeneratorDestination destination) {
        try (Writer w = new OutputStreamWriter(destination.newResource(CompilerHints.LIST.substring(1)), StandardCharsets.UTF_8)){
            PrintWriter writer = new PrintWriter(w);
            for (String line : lines) {
                writer.println(line);
            }
            writer.close();
        } catch (IOException ex) {
            destination.printError("Error writing compiler hint list ", ex);
        } catch (Throwable t) {
            destination.printError("Compiler control generators had thrown the unexpected exception", t);
        }
    }

    private static String getName(String className, String methodName) {
        return className.replaceAll("\\.", "/") + "." + methodName;
    }

    private static String getName(MethodInfo mi) {
       return getName(getClassName(mi.getDeclaringClass()), mi.getName());
    }

    private static String getName(ClassInfo ci) {
        return getName(getClassName(ci), "*");
    }

    private static String getClassName(ClassInfo ci) {
        return ci.getPackageName() + "." + BenchmarkGeneratorUtils.getNestedNames(ci);
    }

}
