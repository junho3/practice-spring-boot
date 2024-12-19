import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.4.0"
    id("io.spring.dependency-management") version "1.1.6"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    id("org.asciidoctor.jvm.convert") version "3.3.2"
    id("java-test-fixtures")

    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    kotlin("plugin.jpa") version "1.9.25"
    kotlin("plugin.allopen") version "1.9.25"
    kotlin("plugin.noarg") version "1.9.25"
    kotlin("kapt") version "1.9.25"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

val asciidoctorExtensions: Configuration by configurations.creating

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.5")

    /** JPA */
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    /** Exposed */
    implementation("org.jetbrains.exposed:exposed-core:0.50.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.50.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.50.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.50.1")

    /** Querydsl */
    implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
    kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
    kapt("jakarta.annotation:jakarta.annotation-api")
    kapt("jakarta.persistence:jakarta.persistence-api")

    runtimeOnly("com.h2database:h2")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest:kotest-runner-junit5:5.7.2")
    testImplementation("io.kotest:kotest-assertions-core:5.7.2")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")
    testImplementation("com.h2database:h2")
    testImplementation("io.mockk:mockk:1.13.11")
    testImplementation("com.tngtech.archunit:archunit-junit5:1.1.0")

    /** asciidoc */
    asciidoctorExtensions("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")

    testFixturesImplementation("com.navercorp.fixturemonkey:fixture-monkey-starter-kotlin:1.0.16")

    allOpen {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("javax.persistence.Embeddable")
    }

    noArg {
        annotation("javax.persistence.Entity")
        annotation("javax.persistence.MappedSuperclass")
        annotation("javax.persistence.Embeddable")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val snippetsDir by extra { file("$buildDir/generated-snippets") }
val restdocsDir by extra { file("$buildDir/resources/main/static/docs") }

tasks {

    clean {
        delete("src/main/resources/static/docs")
    }

    test {
        // 위에서 작성한 snippetsDir 디렉토리를 test의 output으로 구성하는 설정 -> 스니펫 조각들이 build/generated-snippets로 출력
        outputs.dir(snippetsDir)
    }

    build {
        dependsOn("copyDocument")
    }

    // asciidoctor 작업 구성
    asciidoctor {
        dependsOn(test) // test 작업 이후에 작동하도록 하는 설정
        configurations(asciidoctorExtensions.name) // 위에서 작성한 configuration 적용
        inputs.dir(snippetsDir) // snippetsDir 를 입력으로 구성
        attributes["project-version"] = project.version
        attributes["source-highlighter"] = "prettify"

        // source가 없으면 .adoc파일을 전부 html로 만들어버림
        // source 지정시 특정 adoc만 HTML로 만든다.
        sources {
            include("**/index.adoc", "**/common/*.adoc")
        }

        // 특정 .adoc에 다른 adoc 파일을 가져와서(include) 사용하고 싶을 경우 경로를 baseDir로 맞춰주는 설정입니다.
        // 개별 adoc 으로 운영한다면 필요 없는 옵션입니다.
        baseDirFollowsSourceFile()

        // static/docs 폴더 비우기
        doFirst {
            delete("src/main/resources/static/docs")
        }
    }

    // asccidoctor 작업 이후 생성된 HTML 파일을 static/docs 로 copy
    register<Copy>("copyDocument") {
        dependsOn("asciidoctor")
        from("build/docs/asciidoc")
        into("src/main/resources/static/docs")
    }

    bootJar {
        dependsOn(asciidoctor)
        from("${asciidoctor.get().outputDir}") {
            into("static/docs")
        }
    }
}
