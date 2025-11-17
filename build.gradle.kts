plugins {
	java
	id("org.springframework.boot") version "3.5.7"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "io.github.henriquemcc"
version = "0.0.1-SNAPSHOT"
description = "Teste prático dti digital"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
    implementation("com.github.testcontainers.testcontainers-java:testcontainers-junit-jupiter:2.0.2")
    implementation("com.github.testcontainers.testcontainers-java:testcontainers-mysql:2.0.2")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
