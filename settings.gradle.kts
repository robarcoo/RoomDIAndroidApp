pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "My Application"
include(":app")
include(":core")
include(":core:network")
include(":core:network:model")
include(":core:network:di")
include(":core:network:retrofit")
include(":feature")
include(":feature:vacancies")
include(":feature:companies")
include(":core:data")
include(":core:data:repository")
include(":core:common")
include(":feature:candidate")
include(":core:database")
