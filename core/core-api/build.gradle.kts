tasks.getByName("bootJar") {
    enabled = true
}

dependencies {
    implementation(project(":core:core-enum"))
    implementation(project(":storage:db-core"))
    implementation(project(":client:example-client"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
}
