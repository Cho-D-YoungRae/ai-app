tasks.getByName("bootJar") {
    enabled = true
}

dependencies {
    implementation(project(":core:core-enum"))
    implementation(project(":storage:db-core"))
    implementation(project(":client:openai-client"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("io.jsonwebtoken:jjwt-api:${property("jwtVersion")}")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:${property("jwtVersion")}")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:${property("jwtVersion")}")
}
