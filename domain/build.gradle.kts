plugins {
    kotlin("plugin.jpa") version "1.6.0"
}

dependencies {
    implementation(kotlin("stdlib"))
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}