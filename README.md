# 🎬 MovieRater (Android)

MovieRater is a simple Android application built with **Jetpack Compose** that allows users to search for movies via the OMDb API and rate them. Rated movies are stored locally using Jetpack DataStore. This project mirrors the iOS version built with SwiftUI.

## 🛠️ Built With

- **Kotlin**
- **Jetpack Compose** – UI Toolkit
- **Jetpack ViewModel & Lifecycle** – MVVM architecture
- **Jetpack Navigation** – Navigation between screens
- **DataStore** – Persistent local storage for ratings
- **Coil** – Image loading
- **OMDb API** – Movie data source

## 📱 Features

- 🔍 **Search** for movies by title
- ⭐ **Rate** movies from 1 to 5 stars
- 📂 **View previously rated** movies
- 💾 Ratings stored **locally** using Jetpack DataStore
- 🎨 Modern UI with **Jetpack Compose**

## 📦 Dependencies

| Library                          | Purpose                    |
|----------------------------------|-----------------------------|
| `androidx.lifecycle`             | MVVM architecture           |
| `androidx.navigation`            | Navigation component        |
| `io.coil-kt:coil-compose`        | Load movie posters          |
| `com.google.code.gson:gson`      | Parse JSON responses        |
| `Jetpack Compose` (Material3, etc.) | Declarative UI             |
| `Jetpack DataStore`              | Local persistent storage    |

## 🚀 Getting Started

1. Clone the repo:
   ```bash
   git clone https://github.com/yourusername/movierater-android.git
   ```

2. Open in **Android Studio Hedgehog** or newer.

3. Run the app on a device or emulator.

## 🔐 OMDb API Key

This app requires an OMDb API key. Create a free account at [http://www.omdbapi.com](http://www.omdbapi.com), then:

- Add your API key in `MovieService.kt`:
  ```kotlin
  private val apiKey = "your_api_key_here"
  ```

## 🧪 Testing

The app uses JUnit for unit testing. You can run tests from Android Studio via:

```
Run > Run 'All Tests'
```

## 📷 Screenshots

| Search & Rate | Rated Movies |
|---------------|--------------|
| ![search](docs/search.png) | ![rated](docs/rated.png) |

## 📂 Folder Structure

```
├── MainActivity.kt
├── navigation/
├── screens/
├── viewmodel/
├── network/
├── storage/
└── model/
```

## 👩‍💻 Author

Sebastian Strus  

## 📄 License

MIT License. See `LICENSE` file for details.
