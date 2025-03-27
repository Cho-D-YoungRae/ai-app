dependencies {
    implementation(project(":core:core-enum"))

    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("io.github.openfeign:feign-hc5")
    implementation("io.github.openfeign:feign-micrometer")
    implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j")

    implementation(platform("org.springframework.ai:spring-ai-bom:1.0.0-M6"))
    implementation("org.springframework.ai:spring-ai-openai-spring-boot-starter")
    implementation("jakarta.websocket:jakarta.websocket-api:2.0.0")

    repositories {
        mavenCentral()
        maven {
            url = uri("https://repo.spring.io/milestone")
        }
    }
}