plugins {
	java
	id("org.springframework.boot") version "3.5.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.propydis"
version = "0.0.1-SNAPSHOT"
description = "Project for architecture studio"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}


repositories {
	mavenCentral()
}

dependencies {
	// Core spring boot
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Logback
	implementation("net.logstash.logback:logstash-logback-encoder:7.4")

	// SpringDoc OpenAPI
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")

	// Security
	implementation("org.springframework.boot:spring-boot-starter-security")

	// JWT
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
	implementation ("org.springframework.boot:spring-boot-starter-oauth2-resource-server")

	// BD
	implementation("mysql:mysql-connector-java:8.0.33")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Memory Cache
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation ("com.github.ben-manes.caffeine:caffeine")

	// Cloudinary
	implementation("com.cloudinary:cloudinary-http44:1.29.0")

	// Environment Variables
	implementation("io.github.cdimascio:dotenv-java:2.2.4")

	// DevTools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Testing
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.springframework.security:spring-security-test")
	testImplementation("org.mockito:mockito-core:5.11.0")
	testImplementation("org.mockito:mockito-junit-jupiter:5.11.0")

	}

tasks.withType<Test> {
	useJUnitPlatform()
}
