# GitHub Actions - Auto-Build APK Setup Guide

## What's Created

A **fully automated CI/CD pipeline** that builds your Android app every time you push code. No local Android SDK needed!

### Workflow File
- **Location:** `.github/workflows/build-apk.yml`
- **Builds:** Debug APK + Release APK (unsigned)
- **Artifacts:** Available for download after each build
- **Release Tags:** Auto-creates GitHub releases with APKs when you tag a version

---

## 🚀 Quick Start

### Step 1: Initialize Git (if not already done)
```bash
cd android-delivery-app
git init
git add .
git commit -m "Initial commit: Android delivery app scaffolding"
```

### Step 2: Push to GitHub
```bash
git remote add origin https://github.com/YOUR_USERNAME/YOUR_REPO.git
git branch -M main
git push -u origin main
```

### Step 3: Verify Workflow
- Go to your GitHub repo
- Click **"Actions"** tab
- You should see **"Build Android APK"** workflow
- It will run automatically on push!

---

## 📊 What Happens on Each Push

| Step | Action |
|------|--------|
| 1️⃣ Checkout | Pull your latest code |
| 2️⃣ Java 17 | Install JDK 17 (required for latest Android) |
| 3️⃣ Android SDK | Download & install Android SDK 36 + build tools |
| 4️⃣ Build | Run `./gradlew assembleDebug assembleRelease` |
| 5️⃣ Artifacts | Upload APKs for download |
| 6️⃣ Report | Display build summary in GitHub Actions |

---

## 📥 Download Built APKs

### From Build Artifacts (Every Push)
1. Go to **Actions** → **Latest Build**
2. Scroll to **Artifacts** section
3. Download:
   - **app-debug** - Ready to install on test devices
   - **app-release-unsigned** - For testing (unsigned)

### From Release (Tagged Versions)
1. Create a version tag:
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```
2. Workflow auto-creates a **GitHub Release** with both APKs attached
3. Download from **Releases** page

---

## 🔧 Customization Options

### Build on Specific Branches
Edit `.github/workflows/build-apk.yml`:
```yaml
on:
  push:
    branches: [ main, develop, staging ]  # Add branches here
```

### Build on Schedule (Nightly)
```yaml
on:
  schedule:
    - cron: '0 2 * * *'  # 2 AM UTC daily
```

### Build on Pull Requests
Already enabled! Builds run before merging.

### Run Build Manually (Workflow Dispatch)
- Go to **Actions** → **Build Android APK**
- Click **"Run workflow"** → **main** → **Run**

---

## 🔐 Security & Signing (Optional)

### For Release Builds with Google Play Signing
1. **Create Keystore:**
   ```bash
   keytool -genkey -v -keystore my-release-key.keystore -keyalg RSA -keysize 2048 -validity 10000 -alias my-key-alias
   ```

2. **Store in GitHub Secrets:**
   - Go to **Settings** → **Secrets and variables** → **Actions**
   - Add `RELEASE_KEYSTORE` (base64 encoded keystore file)
   - Add `RELEASE_KEY_PASSWORD`, `RELEASE_ALIAS`, `RELEASE_ALIAS_PASSWORD`

3. **Update `build.gradle`:**
   ```gradle
   signingConfigs {
       release {
           storeFile file("keystore/my-release-key.keystore")
           storePassword System.getenv("RELEASE_KEYSTORE_PASSWORD")
           keyAlias System.getenv("RELEASE_KEY_ALIAS")
           keyPassword System.getenv("RELEASE_KEY_PASSWORD")
       }
   }
   ```

4. **Update workflow** to download and use keystore during build

---

## 🐛 Troubleshooting

### Build Fails - "Gradle wrapper not found"
**Solution:** Add `gradle-wrapper.jar` to repo or regenerate:
```bash
gradle wrapper --gradle-version 8.0
git add gradle/
git commit -m "Add gradle wrapper"
git push
```

### Build Fails - "Credentials Required"
If using Firebase or Supabase in `build.gradle`:
1. Add credentials to GitHub Secrets
2. Reference in workflow: `run: ./gradlew build -PFIREBASE_KEY=${{ secrets.FIREBASE_KEY }}`

### Build Fails - "API Key Missing"
Google Maps, Firebase, etc. require keys in `build.gradle`:
```bash
# Set as GitHub Secrets
echo "${{ secrets.GOOGLE_MAPS_API_KEY }}" > local.properties
```

### APK Size is Large (>100MB)
- Enable ProGuard minification in `build.gradle`
- Set `minifyEnabled true` for release builds
- Remove unused dependencies

---

## 📱 Testing Downloaded APK

### On Android Device
```bash
adb install app-debug.apk
adb uninstall com.samadisolutions.deliveryapp
```

### On Android Emulator
```bash
emulator -avd Pixel_6_API_36 &
adb install app-debug.apk
```

---

## 🎯 Next Steps

1. **Initialize Git & Push**
   ```bash
   git init && git add . && git commit -m "Initial commit"
   git remote add origin https://github.com/YOUR/REPO.git
   git push -u origin main
   ```

2. **Configure Secrets** (if needed)
   - GitHub repo → Settings → Secrets
   - Add `GOOGLE_MAPS_API_KEY`, `SUPABASE_URL`, etc.

3. **Configure App Signing** (for Play Store)
   - Follow security section above
   - Test release build: `./gradlew assembleRelease`

4. **Monitor Builds**
   - Watch **Actions** tab
   - Download APK artifacts
   - Test on devices

5. **Create Release Versions**
   - Tag commits: `git tag v1.0.0 && git push origin v1.0.0`
   - APKs auto-attach to GitHub Release

---

## 📚 Additional Resources

- [GitHub Actions Android Docs](https://github.com/actions/setup-android)
- [Android Gradle Plugin](https://developer.android.com/studio/build)
- [GitHub Secrets Management](https://docs.github.com/en/actions/security-guides/using-secrets-in-github-actions)

---

**Your app is now ready for cloud-based automated builds! 🎉**
