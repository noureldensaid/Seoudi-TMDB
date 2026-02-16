# 🎬 TMDB Movies App — Seoudi Android Assessment

![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=for-the-badge&logo=android&logoColor=white)
![Material 3](https://img.shields.io/badge/Material%203-6750A4?style=for-the-badge&logo=materialdesign&logoColor=white)
![Ktor](https://img.shields.io/badge/Ktor-0095D5?style=for-the-badge&logo=ktor&logoColor=white)
![Koin](https://img.shields.io/badge/Koin-F78C40?style=for-the-badge&logo=koin&logoColor=white)
![Room](https://img.shields.io/badge/Room-2C7DCE?style=for-the-badge&logo=android&logoColor=white)

A modern native Android application built as part of the **Seoudi Android technical assessment**.  
The app shows a **paginated list of popular movies** and a **movie details screen**, with the ability to **save favorites locally** using Room (DB as source of truth).

---

## ✅ Task Requirements Implemented

### Step 1 — Movies List Screen
- Displays **popular movies** in a **single-column list** (one item per row).
- Each movie card shows: **Poster, Name, Release Date, Rating**
- Pagination implemented using the TMDB popular endpoint.

API: GET https://api.themoviedb.org/3/movie/popular?api_key={key}&language=en&page=1

### Step 2 — Movie Details Screen
- Opens movie details on card click.
- Displays movie information (runtime, budget, revenue, genres, production companies, etc.).
- Star icon toggles favorite.
- Favorites stored locally using Room.

API: GET https://api.themoviedb.org/3/movie/{id}?api_key={key}

---

## 🏗️ Architecture

This project follows a **Multi-Module Clean Architecture** using the **MVI (Model–View–Intent)** pattern to ensure scalability, separation of concerns, and testability.

### Module Structure
- **:app** — Entry point, navigation setup, DI bootstrap.
- **:core** — Shared utilities, common UI components, base classes, Result wrappers.
- **:network** — Ktor HTTP client configuration and API layer.
- **:database** — Room database + DAOs + entities.
- **:moviesList** — Movies list feature (UI, domain, data).
- **:movieDetails** — Movie details feature (UI, domain, data).

### Layered Design (Per Feature)
- **Presentation:** Compose UI + ViewModels (MVI state/events)
- **Domain:** UseCases + Repository interfaces + models
- **Data:** Repository implementations + Remote (Ktor) + Local (Room) + Mappers

---

## 🛠️ Tech Stack
- **Language:** Kotlin (Latest Stable)
- **UI:** Jetpack Compose + Material 3
- **Networking:** Ktor
- **Dependency Injection:** Koin
- **Local Storage:** Room
- **Async:** Coroutines + Flow
- **Image Loading:** Coil
- **Architecture:** Multi-Module + MVI

---

## ✨ Features
- **Paginated Popular Movies List**
- **Movie Details Screen**
- **Favorite Movies Persistence (Room)**
- **DB as Source of Truth** (favorite state preserved locally)
- **Process-Death Safe ViewModels** (SavedStateHandle for route params)
- **Unit Tests for Data & Domain Layers**

---

## 🧪 Testing
Unit tests implemented for:
- Repositories
- UseCases
- Mappers

Tools used:
- **JUnit**
- **Truth** (assertions)
- **MockK** (mocking)
- **Turbine** (Flow testing)
- **kotlinx-coroutines-test** (coroutines control)

---

## ScreenShots

<img width="300"    alt="image" src="https://github.com/user-attachments/assets/a552a3c3-ce41-4a33-bc70-9a231baa6617" />
<img width="300"   alt="image" src="https://github.com/user-attachments/assets/e5df339c-da17-434d-9b44-22265b1c1e85" />
<img width="300"    alt="image" src="https://github.com/user-attachments/assets/02cd96ce-94c6-42a2-947c-128efb2c5331" />
<img width="300"   alt="image" src="https://github.com/user-attachments/assets/cbc4fc95-4976-4ec6-b1c6-56f8658bb1a9" />
<img width="300"   alt="image" src="https://github.com/user-attachments/assets/a78faea4-4131-482a-805f-47a44f5a0bd6" />
<img width="300"   alt="image" src="https://github.com/user-attachments/assets/180b9458-aee6-4fdf-9e5c-da7db44c7200" />



