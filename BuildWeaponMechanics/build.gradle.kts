import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

description = "A New Age of Weapons in Minecraft"
version = "1.6.3-BETA"

plugins {
    id("me.deecaad.java-conventions")
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
}

configurations {
    compileClasspath.get().extendsFrom(create("shadeOnly"))
}

dependencies {
    implementation(project(":WeaponMechanics"))
    implementation(project(":WeaponCompatibility"))

    implementation(project(":Weapon_1_9_R2" ))
    implementation(project(":Weapon_1_10_R1"))
    implementation(project(":Weapon_1_11_R1"))
    implementation(project(":Weapon_1_12_R1"))
    implementation(project(":Weapon_1_13_R2"))
    implementation(project(":Weapon_1_14_R1"))
    implementation(project(":Weapon_1_15_R1"))
    implementation(project(":Weapon_1_16_R3"))
    implementation(project(":Weapon_1_17_R1", "reobf"))
    implementation(project(":Weapon_1_18_R1", "reobf"))
    implementation(project(":Weapon_1_18_R2", "reobf"))
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name() // We want UTF-8 for everything
        options.release.set(17) // We need to set release compatibility to java 17 since MC 18+ uses it
    }
}

// See https://github.com/Minecrell/plugin-yml
bukkit {
    main = "me.deecaad.weaponmechanics.WeaponMechanicsLoader"
    name = "WeaponMechanics" // Since we don't want to use "BuildWeaponMechanics"
    apiVersion = "1.13"

    authors = listOf("DeeCaaD", "CJCrafter")
    softDepend = listOf("MechanicsCore", "MythicMobs", "CrackShot", "CrackShotPlus")

    permissions {
        register("weaponmechanics.use.*") {
            description = "Permission to use all weapons"
            default = BukkitPluginDescription.Permission.Default.TRUE
        }
    }
}

tasks.named<ShadowJar>("shadowJar") {
    dependsOn("versionFile")

    destinationDirectory.set(file("../build"))
    archiveFileName.set("WeaponMechanics-${version}.jar")
    configurations = listOf(project.configurations["shadeOnly"], project.configurations["runtimeClasspath"])

    dependencies {
        include(project(":WeaponMechanics"))
        include(project(":WeaponCompatibility"))

        include(project(":Weapon_1_9_R2" ))
        include(project(":Weapon_1_10_R1"))
        include(project(":Weapon_1_11_R1"))
        include(project(":Weapon_1_12_R1"))
        include(project(":Weapon_1_13_R2"))
        include(project(":Weapon_1_14_R1"))
        include(project(":Weapon_1_15_R1"))
        include(project(":Weapon_1_16_R3"))
        include(project(":Weapon_1_17_R1"))
        include(project(":Weapon_1_18_R1"))
        include(project(":Weapon_1_18_R2"))

        relocate ("co.aikar.timings.lib", "me.deecaad.weaponmechanics.lib.timings") {
            include(dependency("co.aikar:minecraft-timings"))
        }

        relocate ("org.bstats", "me.deecaad.weaponmechanics.lib.bstats") {
            include(dependency("org.bstats:"))
        }

        relocate ("net.kyori.adventure", "me.deecaad.core.lib.adventure")
    }
}

tasks.register("versionFile").configure {
    val file = file("../WeaponMechanics/src/main/resources/version.txt")
    file.writeText(project(":BuildMechanicsCore").version.toString());
}

tasks.named("assemble").configure {
    dependsOn("shadowJar")
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/WeaponMechanics/MechanicsMain")
            credentials {
                username = findProperty("user").toString()
                password = findProperty("pass").toString()
            }
        }
    }
    publications {
        create<MavenPublication>("weaponPublication") {
            artifact(tasks.named("shadowJar"))

            pom {
                groupId = "me.deecaad"
                artifactId = "weaponmechanics" // MUST be lowercase
                packaging = "jar"
            }
        }
    }
}