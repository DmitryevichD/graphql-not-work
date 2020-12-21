import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.expediagroup.graphql") version "3.7.0"
    kotlin("jvm") version "1.4.10"
    kotlin("plugin.spring") version "1.4.10"

}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.expediagroup:graphql-kotlin-spring-client:3.7.0")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

graphql {
    client {
        packageName = "com.expediagroup.graphql.generated"
        // you can als2.2.6.RELEASEo use direct sdlEndpoint instead
        endpoint = "http://localhost:8080/graphql"

        // optional
        allowDeprecatedFields = true
        headers["X-Custom-Header"] = "My-Custom-Header"
        converters["UUID"] = com.expediagroup.graphql.plugin.generator.ScalarConverterMapping(
            "java.util.UUID",
            "com.expediagroup.graphql.examples.client.UUIDScalarConverter"
        )
    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
