plugins {
	java
	id("org.springframework.boot") version "4.0.2"
	id("io.spring.dependency-management") version "1.1.7"
	id("org.flywaydb.flyway") version "12.0.0"
}

extra["flyway.version"] = "12.0.0"

group = "com.zuehlke.academy"
version = "0.0.1-SNAPSHOT"
description = "Clean Architecture Demo Project"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(25)
	}
}

flyway {
	url = "jdbc:postgresql://localhost:5432/academy"
	user = "academy"
	password = "academy"
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-flyway")
	implementation("org.springframework.boot:spring-boot-starter-webmvc")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.1")
	implementation("org.postgresql:postgresql")
	runtimeOnly("org.flywaydb:flyway-database-postgresql")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.h2database:h2")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
