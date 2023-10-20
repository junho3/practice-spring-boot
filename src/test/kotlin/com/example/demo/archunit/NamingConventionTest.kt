package com.example.demo.archunit

import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.ArchRule
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition
import org.springframework.stereotype.Service

@AnalyzeClasses(packages = ["com.example.demo"])
class NamingConventionTest {
    @ArchTest
    val servicesShouldBeSuffixed: ArchRule =
        ArchRuleDefinition.classes()
            .that().resideInAPackage("..service..")
            .and().areAnnotatedWith(Service::class.java)
            .should().haveSimpleNameEndingWith("Service")
}
