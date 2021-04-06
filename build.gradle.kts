import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.31"
	kotlin("plugin.spring") version "1.4.31"
}

buildscript {
	dependencies {
		classpath("io.qameta.allure:allure-gradle:2.6.0")
	}
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot", "spring-boot-starter-webflux")
	implementation("org.springframework.boot", "spring-boot-starter-actuator")
	implementation("io.micrometer", "micrometer-registry-prometheus")
	implementation("mysql", "mysql-connector-java", "8.0.22")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("io.projectreactor", "reactor-core")
	implementation("org.springframework.boot", "spring-boot-starter-data-r2dbc")
	implementation("org.springframework.boot", "spring-boot-starter-data-jdbc")
	implementation("dev.miku", "r2dbc-mysql")
	implementation("org.liquibase", "liquibase-core")
	implementation("io.github.serpro69", "kotlin-faker", "1.6.0")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter", "junit-jupiter-engine")
	testImplementation("org.junit.jupiter", "junit-jupiter-params")
	testImplementation("io.projectreactor", "reactor-test")
	testImplementation("io.projectreactor.kotlin", "reactor-kotlin-extensions")
	testImplementation("org.jetbrains.kotlin", "kotlin-test-junit")
	testImplementation("io.qameta.allure:allure-java-commons:2.12.1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

apply(plugin = "io.qameta.allure")

configure<io.qameta.allure.gradle.AllureExtension> {
	autoconfigure = true
	aspectjweaver = true
	version = "2.12.1"

	useJUnit5 {
		version = "2.12.1"
	}
}
