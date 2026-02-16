pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

rootProject.name = "Seoudi TMDB"
include(":app")
include(":core:ui")
include(":core:network")
include(":core:navigation")
include(":core:database")
include(":core:common")
include(":feature:moviesList:ui")
include(":feature:moviesList:data")
include(":feature:moviesList:domain")
include(":feature:movieDetails:data")
include(":feature:movieDetails:ui")
include(":feature:movieDetails:domain")
