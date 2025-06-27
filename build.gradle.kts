plugins {
    java
    application
}

group = "com.libraryapp"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_21 // or 11, 21, etc.
    targetCompatibility = JavaVersion.VERSION_21
}

application {
    mainClass.set("com.libraryapp.Main")
}

repositories {
    mavenCentral()
}

dependencies {
    // Add any dependencies here
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    implementation("mysql:mysql-connector-java:8.0.33")
}

tasks.test {
    useJUnitPlatform()
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.libraryapp.Main"
    }
}

tasks.withType<JavaExec> {
    jvmArgs = listOf("-Dfile.encoding=UTF-8")
}


