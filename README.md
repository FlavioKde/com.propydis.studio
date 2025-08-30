# com.propydis
# Propydis

## 📦 dependencies(build.gradle.kts)

```groovy
plugins {
    id 'java'
    id 'org.springframework.boot' version '3.5.4'
    id 'io.spring.dependency-management' version '1.1.0'
}
dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web:3.5.4'
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb:3.5.4'
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa:3.5.4'
    implementation 'org.springframework.boot:spring-boot-starter-validation:3.5.4'
    implementation 'org.springframework.security:spring-security-core:6.5.4'
    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9'

    // JWT
    implementation 'io.jsonwebtoken:jjwt-api:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-impl:0.11.5'
    runtimeOnly 'io.jsonwebtoken:jjwt-jackson:0.11.5'

    // Lombok (opcional)
    compileOnly 'org.projectlombok:lombok:1.18.34'
    annotationProcessor 'org.projectlombok:lombok:1.18.34'
    testCompileOnly 'org.projectlombok:lombok:1.18.34'
    testAnnotationProcessor 'org.projectlombok:lombok:1.18.34'

    developmentOnly 'org.springframework.boot:spring-boot-devtools:3.5.4'

    runtimeOnly 'com.mysql:mysql-connector-j:8.1.0'

    // Testing
    testImplementation 'org.springframework.boot:spring-boot-starter-test:3.5.4'
    testImplementation 'org.mockito:mockito-core:5.11.0'
    testImplementation 'org.mockito:mockito-junit-jupiter:5.11.0'
    testImplementation 'io.projectreactor:reactor-test:1.5.14'
    testRuntimeOnly 'org.junit.platform:junit-platform-launcher:1.10.0'
}
```
