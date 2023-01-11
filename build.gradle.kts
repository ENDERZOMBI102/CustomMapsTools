@file:Suppress("UnstableApiUsage")
plugins {
    kotlin("jvm") version "1.7.20"
    id("org.quiltmc.loom") version "1.+"
}

repositories {
	mavenCentral()
	maven( "https://maven.gegy.dev" )
	maven( "https://maven.wispforest.io" )
	maven( "https://maven.architectury.dev" )
	maven( "https://maven.saps.dev/minecraft" )
	maven( "https://aperlambda.github.io/maven" )
	maven( "https://repsy.io/mvn/enderzombi102/mc" )
	maven( "https://maven.terraformersmc.com/releases" )
	maven( "https://ladysnake.jfrog.io/artifactory/mods" )
	maven( "https://maven.quiltmc.org/repository/release" )
	maven( "https://maven.quiltmc.org/repository/snapshot" )
}

loom {
	runtimeOnlyLog4j.set( true )
	runConfigs["client"].isIdeConfigGenerated = true
	runConfigs["server"].isIdeConfigGenerated = true
	accessWidenerPath.set( file( "src/main/resources/cmt.accesswidener" ) )
}

dependencies {
    minecraft( "com.mojang:minecraft:1.19.3" )
    mappings( "org.quiltmc:quilt-mappings:1.19.3+build.16:intermediary-v2" )

	include( libs.bundles.implementation )
    implementation( libs.bundles.implementation )
    modImplementation( libs.bundles.modImplementation )
    annotationProcessor( libs.bundles.annotationProc )
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
}

tasks.withType<ProcessResources> {
    inputs.property( "version"          , version )
	inputs.property( "group"            , project.group )
	inputs.property( "description"      , project.description )
    inputs.property( "loader_version"   , libs.versions.loader )
    inputs.property( "minecraft_version", libs.versions.minecraft )
    inputs.property( "repo_url"         , project.ext["repo_url"] )
    filteringCharset = "UTF-8"

    filesMatching( "quilt.mod.json" ) {
        expand(
            "version"      to version,
            "group"        to project.group,
            "description"  to project.description,
            "repo_url"     to project.ext["repo_url"],
            "dependencies" to """
				{ "id": "quilt_loader", "versions": "${libs.versions.loader.get()}" },
				{ "id": "quilt_base", "versions": "*" },
				{ "id": "minecraft", "versions": ">=${libs.versions.minecraft.get()}" },
				{ "id": "java", "versions": ">=17" }"""
        )
        filter { it.substringBefore("///") }
    }
}

tasks.withType<JavaCompile> {
	options.encoding = "UTF-8"
	options.release.set( 17 )
}

java.toolchain.languageVersion.set( JavaLanguageVersion.of( 17 ) )

tasks.withType<Jar> {
    from( "LICENSE" ) {
        rename { "${it}_$archiveBaseName}" }
    }
}
