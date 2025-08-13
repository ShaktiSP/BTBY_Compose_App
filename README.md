Jetpack Compose MVVM Starter
A modern Android starter showcasing Jetpack Compose UI, MVVM architecture, Kotlin Coroutines & Flow for async/reactive data, and Dagger Hilt for dependency injection.

Clean, testable, modular, and production-ready patterns.

📦 Tech Stack
UI: Jetpack Compose, Material 3, Navigation-Compose

DI: Dagger Hilt

Concurrency: Kotlin Coroutines, Flow, StateFlow

Networking: Retrofit, OkHttp, Moshi (or Kotlinx Serialization)

Persistence (optional): Room

Lifecycle: ViewModel (lifecycle-viewmodel-ktx)

Testing: JUnit, Turbine, MockK/Mockito, Compose UI Test

Build: Gradle (Kotlin DSL)

🧱 Architecture
pgsql
Copy
Edit
presentation/   <-- Compose screens, UI state, ViewModels (MVVM)
domain/         <-- UseCases, domain models (pure Kotlin)
data/           <-- Repositories, data sources (network/db), DTOs, mappers
di/             <-- Hilt modules

🗂️ Project Structure
swift
Copy
Edit
app/
 ├─ src/main/java/com/example/app/
 │   ├─ di/
 │   │   ├─ NetworkModule.kt
 │   │   └─ RepositoryModule.kt
 │   ├─ data/
 │   │   ├─ remote/ (Retrofit services, DTOs)
 │   │   ├─ local/ (Room DAOs, entities)   # optional
 │   │   ├─ repository/ (Repository impls)
 │   │   └─ mapper/ (DTO↔Domain)
 │   ├─ domain/
 │   │   ├─ model/
 │   │   └─ usecase/
 │   ├─ presentation/
 │   │   ├─ navigation/
 │   │   ├─ screen/
 │   │   │   └─ home/
 │   │   │      ├─ HomeScreen.kt
 │   │   │      └─ HomeViewModel.kt
 │   │   └─ ui/ (theme, components)
 │   └─ App.kt
 └─ build.gradle.kts
