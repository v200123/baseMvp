package com.lc.annotaions

import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.element.TypeElement

class BindViewProcessor : AbstractProcessor(){
    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment?
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun getSupportedAnnotationTypes(): Set<String> {
        return setOf(BindViewProcessor::class.java.canonicalName)
    }
}
