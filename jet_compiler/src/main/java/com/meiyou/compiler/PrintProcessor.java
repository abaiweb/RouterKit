package com.jet.compiler;

import com.google.auto.service.AutoService;
import com.jet.annotation.Print;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
@Deprecated
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@AutoService(Processor.class)
@SupportedAnnotationTypes("com.jet.annotation.Print")
public class PrintProcessor extends AbstractProcessor {

    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mMessager = processingEnvironment.getMessager();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement te : annotations) {
            for (Element e : roundEnv.getElementsAnnotatedWith(te)) {//find special annotationed element
                print(e.toString());//print element
            }
        }
        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        LinkedHashSet<String> annotations = new LinkedHashSet<>();
        annotations.add(Print.class.getCanonicalName());
        return super.getSupportedAnnotationTypes();
    }

    private void print(String msg) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, msg);
    }
}