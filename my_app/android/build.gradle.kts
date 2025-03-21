// plugins {
//   // ...

//   // Add the dependency for the Google services Gradle plugin
//   id("com.google.gms.google-services") version "4.4.2" apply false

// }
allprojects {
    repositories {
        google()
        mavenCentral()
    }
    configurations.all {
        resolutionStrategy {
            force("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")
            force("org.jetbrains.kotlin:kotlin-stdlib-jdk7:2.0.0")
            force("org.jetbrains.kotlin:kotlin-stdlib-jdk8:2.0.0")
        }
    }
}
// allprojects {
    
// }

val newBuildDir: Directory = rootProject.layout.buildDirectory.dir("../../build").get()
rootProject.layout.buildDirectory.value(newBuildDir)

subprojects {
    val newSubprojectBuildDir: Directory = newBuildDir.dir(project.name)
    project.layout.buildDirectory.value(newSubprojectBuildDir)
}
subprojects {
    project.evaluationDependsOn(":app")
}

tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
