plugins {
    java
    `maven-publish`
}

group = "de.goldmensch"
version = "1.0"

java.sourceCompatibility = JavaVersion.VERSION_16
java.targetCompatibility = JavaVersion.VERSION_16

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    compileOnly("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("com.destroystokyo.paper:paper-api:1.16.5-R0.1-SNAPSHOT")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

publishing {
    val publishData = PublishData(project)
    publications.create<MavenPublication>("maven") {
        from(components["java"])
        groupId = project.group as String?
        artifactId = project.name
        version = publishData.getVersion()
    }

    repositories {
        maven {
            authentication {
                credentials(PasswordCredentials::class) {
                    username = System.getenv("NEXUS_USERNAME")
                    password = System.getenv("NEXUS_PASSWORD")
                }
            }

            name = "EldoNexus"
            url = uri(publishData.getRepository())
        }
    }
}

class PublishData(private val project: Project) {
    var type: Type = getReleaseType()
    var hashLength: Int = 7

    private fun getReleaseType(): Type {
        val branch = getCheckedOutBranch()
        return when {
            branch.contentEquals("master") -> Type.RELEASE
            branch.startsWith("dev") -> Type.DEV
            else -> Type.SNAPSHOT
        }
    }

    private fun getCheckedOutGitCommitHash(): String = System.getenv("GITHUB_SHA")?.substring(0, hashLength) ?: "local"

    private fun getCheckedOutBranch(): String = System.getenv("GITHUB_REF")?.replace("refs/heads/", "") ?: "local"

    fun getVersion(): String = getVersion(false)

    fun getVersion(appendCommit: Boolean): String =
        type.append(getVersionString(), appendCommit, getCheckedOutGitCommitHash())

    private fun getVersionString(): String = (project.version as String).replace("-SNAPSHOT", "").replace("-DEV", "")

    fun getRepository(): String = type.repo

    enum class Type(private val append: String, val repo: String, private val addCommit: Boolean) {
        RELEASE("", "https://eldonexus.de/repository/maven-releases/", false),
        DEV("-DEV", "https://eldonexus.de/repository/maven-dev/", true),
        SNAPSHOT("-SNAPSHOT", "https://eldonexus.de/repository/maven-snapshots/", true);

        fun append(name: String, appendCommit: Boolean, commitHash: String): String =
            name.plus(append).plus(if (appendCommit && addCommit) "-".plus(commitHash) else "")
    }
}