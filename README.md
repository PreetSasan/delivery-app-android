# Android Delivery App - Native Kotlin Implementation

A production-ready Android delivery management app built with **Kotlin, Jetpack libraries, and Supabase**.

## 📱 Project Overview

This is a native Android app (no cross-platform framework) for delivery logistics with:
- Real-time delivery tracking
- GPS location services
- Photo proof uploads
- User authentication
- Statistics dashboard
- Push notifications
- **No payment processing**

## 🛠 Tech Stack

### Architecture
- **MVVM** (Model-View-ViewModel) pattern
- **Hilt** for dependency injection
- **Coroutines** for async operations
- **Flow** for reactive data streams

### UI Framework
- **Material Components 3** for Material Design
- **ConstraintLayout** for responsive layouts
- **Navigation Component** for screen navigation
- **Bottom Navigation** for tab navigation

### Data Layer
- **Retrofit** for REST API calls
- **Gson** for JSON serialization
- **Room** for local database
- **DataStore** for preferences
- **Supabase** for backend

### Services
- **Google Play Services** for location
- **Google Maps** for map visualization
- **Firebase** for push notifications
- **CameraX** for camera access

### Build Config
- **Kotlin 1.9.23**
- **Android 24+** (API level 24+)
- **Target Android 36** (API level 36)
- **Gradle 8.3.0**

## 📁 Project Structure

```
android-delivery-app/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/samadisolutions/deliveryapp/
│   │   │   │   ├── MainActivity.kt                    ← Entry point
│   │   │   │   ├── DeliveryApp.kt                     ← App class
│   │   │   │   ├── ui/
│   │   │   │   │   ├── screens/                       ← UI fragments
│   │   │   │   │   │   ├── HomeFragment.kt
│   │   │   │   │   │   ├── DeliveriesFragment.kt
│   │   │   │   │   │   ├── MapFragment.kt
│   │   │   │   │   │   └── ProfileFragment.kt
│   │   │   │   │   ├── components/                    ← Reusable views
│   │   │   │   │   ├── theme/                         ← Theme & styles
│   │   │   │   │   └── viewmodels/                    ← View models
│   │   │   │   ├── data/
│   │   │   │   │   ├── api/
│   │   │   │   │   │   └── DeliveryApi.kt             ← Retrofit service
│   │   │   │   │   ├── models/
│   │   │   │   │   │   └── Models.kt                  ← Data classes
│   │   │   │   │   └── repositories/
│   │   │   │   │       └── DeliveryRepository.kt      ← Data repository
│   │   │   │   ├── domain/
│   │   │   │   │   ├── entities/                      ← Domain objects
│   │   │   │   │   └── usecases/                      ← Business logic
│   │   │   │   ├── services/
│   │   │   │   │   ├── LocationService.kt
│   │   │   │   │   └── NotificationService.kt
│   │   │   │   ├── utils/
│   │   │   │   │   ├── Constants.kt
│   │   │   │   │   ├── Extensions.kt
│   │   │   │   │   └── Permissions.kt
│   │   │   │   └── di/
│   │   │   │       └── AppModule.kt                   ← Hilt modules
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   │   ├── activity_main.xml
│   │   │   │   │   ├── fragment_home.xml
│   │   │   │   │   ├── fragment_deliveries.xml
│   │   │   │   │   ├── fragment_map.xml
│   │   │   │   │   ├── fragment_profile.xml
│   │   │   │   │   └── item_delivery.xml
│   │   │   │   ├── drawable/                          ← Icons & drawables
│   │   │   │   ├── menu/
│   │   │   │   │   └── menu_bottom_navigation.xml
│   │   │   │   └── values/
│   │   │   │       ├── colors.xml
│   │   │   │       ├── strings.xml
│   │   │   │       └── styles.xml
│   │   │   └── AndroidManifest.xml
│   │   └── test/                                      ← Unit tests
│   ├── build.gradle                                   ← App build config
│   ├── proguard-rules.pro
│   └── .gitignore
├── build.gradle                                       ← Project build config
├── settings.gradle
├── gradle.properties
├── local.properties
└── README.md
```

## 🚀 Getting Started

### Prerequisites
- Android Studio 2024.1 or later
- Java 17 or later (via Android Studio)
- Android SDK 36 (API level 36)
- Google Maps API key
- Supabase project

### Setup Steps

#### 1. Clone the Project
```bash
git clone <your-repo-url>
cd android-delivery-app
```

#### 2. Configure Supabase
Edit `app/build.gradle`:
```gradle
buildConfigField "String", "SUPABASE_URL", '"https://your-project.supabase.co"'
buildConfigField "String", "SUPABASE_ANON_KEY", '"your-supabase-key"'
```

#### 3. Add Google Maps API Key
In `app/src/main/AndroidManifest.xml`:
```xml
<meta-data
    android:name="com.google.android.geo.API_KEY"
    android:value="YOUR_GOOGLE_MAPS_API_KEY" />
```

#### 4. Open in Android Studio
1. Open Android Studio
2. Click "Open" → Select `android-delivery-app` folder
3. Wait for Gradle sync to complete
4. Build the project: `Build → Make Project`

#### 5. Run the App
- Select emulator or physical device
- Click "Run" → Choose `app` configuration
- App will launch on device

### Build Variants
```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Build App Bundle (for Play Store)
./gradlew bundleRelease

# Run tests
./gradlew test

# Check for lint issues
./gradlew lint
```

## 🏗 Architecture Details

### MVVM Pattern
```
UI (Fragment/Activity)
    ↓
ViewModel (State & Logic)
    ↓
Repository (Data Access)
    ↓
API / LocalDB / Cache
```

### Dependency Injection (Hilt)
```kotlin
// In Fragment/Activity
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()
}

// Automatic injection
@Inject
lateinit var repository: DeliveryRepository
```

### Reactive Data Flow
```kotlin
viewModel.deliveries.collectLatest { deliveries ->
    adapter.submitList(deliveries)
}
```

## 📱 Key Screens

### Home Screen
- Dashboard with delivery statistics
- Quick action buttons
- Recent deliveries preview
- Greeting with user name

### Deliveries Screen
- Filterable delivery list (by status)
- Pull-to-refresh
- Delivery cards with details
- Click to view full details

### Map Screen
- Real-time GPS tracking
- Delivery markers with status colors
- Current location marker
- Map legend

### Profile Screen
- User profile information
- Delivery statistics
- Account settings
- Sign out button

## 🔑 Key Services

### DeliveryApi (Retrofit)
```kotlin
interface DeliveryApi {
    @GET("deliveries")
    suspend fun getDeliveries(@Query("status") status: String?): List<Delivery>
    
    @POST("deliveries/{id}/accept")
    suspend fun acceptDelivery(@Path("id") id: String): Delivery
    
    // ... more endpoints
}
```

### DeliveryRepository
```kotlin
class DeliveryRepository @Inject constructor(
    private val api: DeliveryApi
) {
    fun getDeliveries(status: String?): Flow<Result<List<Delivery>>>
}
```

### Result<T> Sealed Class
```kotlin
sealed class Result<out T> {
    object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
```

## 🎨 Theming

### Colors
- **Primary:** `#1f41bb` (Blue)
- **Primary Dark:** `#1a3494`
- **Accent:** `#ff6b6b` (Red)
- **Background:** `#f5f5f5` (Light Gray)

Edit in `app/src/main/res/values/colors.xml`

### Status Colors
- **Pending:** Yellow (`#ffc107`)
- **Assigned:** Cyan (`#17a2b8`)
- **In Transit:** Blue (`#007bff`)
- **Completed:** Green (`#28a745`)
- **Failed:** Red (`#dc3545`)

## 🔐 Permissions

### Android Manifest Permissions
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
```

### Runtime Permissions (API 23+)
- Location (Fine/Coarse)
- Camera
- Storage (Read/Write)
- Microphone

## 🌐 API Integration

### Base URL
```
https://your-project.supabase.co/functions/v1/
```

### Request Flow
1. Fragment calls ViewModel method
2. ViewModel calls Repository
3. Repository calls API via Retrofit
4. API returns response wrapped in Result<T>
5. Flow emits value to ViewModel
6. ViewModel updates State
7. Fragment observes and updates UI

## 📊 Database Schema

### Supabase Tables (Ready to use)
- `users` - User profiles
- `deliveries` - Delivery records
- `delivery_blocks` - Geographic zones
- `ratings` - User ratings

## 🧪 Testing

### Unit Tests Location
```
app/src/test/java/com/samadisolutions/deliveryapp/
```

### Instrumented Tests Location
```
app/src/androidTest/java/com/samadisolutions/deliveryapp/
```

### Run Tests
```bash
./gradlew test              # Unit tests
./gradlew connectedTest     # Instrumented tests
```

## 📦 Dependencies

### Core
- androidx-appcompat: 1.7.0
- androidx-core-ktx: 1.13.0
- androidx-lifecycle: 2.8.0

### UI
- material: 1.12.0
- constraint-layout: 2.1.4
- recyclerview: 1.3.2

### Navigation
- navigation-fragment: 2.7.7
- navigation-ui: 2.7.7

### Data
- retrofit: 2.11.0
- okhttp: 4.12.0
- room: 2.6.1
- datastore: 1.1.1

### Backend
- supabase-kt: 0.26.0
- firebase-bom: 33.0.0

### DI & Async
- hilt: 2.50
- coroutines: 1.8.0

## 🚀 Deployment

### Build for Play Store
1. Create signing key:
   ```bash
   keytool -genkey -v -keystore release.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias deliveryapp
   ```

2. Add to `local.properties`:
   ```
   RELEASE_STORE_FILE=release.keystore
   RELEASE_STORE_PASSWORD=your-password
   RELEASE_KEY_ALIAS=deliveryapp
   RELEASE_KEY_PASSWORD=your-password
   ```

3. Build release bundle:
   ```bash
   ./gradlew bundleRelease
   ```

4. Upload to Play Console

## 📚 Resources

- [Android Docs](https://developer.android.com)
- [Jetpack Libraries](https://developer.android.com/jetpack)
- [Hilt DI](https://dagger.dev/hilt)
- [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html)
- [Retrofit](https://square.github.io/retrofit/)
- [Supabase Docs](https://supabase.com/docs)
- [Material Design](https://m3.material.io/)

## 🐛 Troubleshooting

### Gradle Sync Failed
```bash
./gradlew clean
./gradlew sync
```

### Build Errors
- Check Java version (need Java 17+)
- Invalidate caches: File → Invalidate Caches
- Update Android SDK via SDK Manager

### Runtime Issues
- Grant permissions when prompted
- Check network connectivity
- Verify Supabase credentials
- Check logcat for errors

## 📝 Next Steps

1. **Set up Supabase** - Create project and database
2. **Add Google Maps API Key** - Enable Maps in Cloud Console
3. **Customize UI** - Modify layouts and colors
4. **Add Screens** - Create additional fragments
5. **Implement Features** - Add business logic
6. **Test** - Write unit and instrumented tests
7. **Deploy** - Build and submit to Play Store

## 📄 License

MIT License - Free to use and modify

---

**Built with ❤️ using Kotlin and Jetpack** 🚀
