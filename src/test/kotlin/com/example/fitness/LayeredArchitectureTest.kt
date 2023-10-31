package com.example.fitness

import com.tngtech.archunit.core.importer.ImportOption
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.library.Architectures

@AnalyzeClasses(packages = ["com.example.demo"], importOptions = [ImportOption.DoNotIncludeTests::class])
class LayeredArchitectureTest {
    @ArchTest
    val layerDependenciesAreRespected: ArchRule =
        Architectures.layeredArchitecture().consideringAllDependencies()
            .layer("Controllers").definedBy("com.example.demo.web.v1..")
            .layer("Services").definedBy("com.example.demo.core..service..")
            .layer("Persistence").definedBy("com.example.demo.infrastructure.persistence..")
            .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
            .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Services")
}
