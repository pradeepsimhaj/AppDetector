# App Detector – React Native + Android Accessibility Service

## Overview

**App Detector** is a React Native Android application that detects the **currently foreground app** on the device using Android’s **Accessibility Service** and displays the detected package name in real time.

This project was built as part of a technical assignment for **Gig Assistant** to demonstrate:

* Native Android (Kotlin) development
* Accessibility Service configuration
* React Native ↔ Android native bridging
* Real-time event communication
* Stability while switching between third-party apps

---

## Key Features

### ✅ Foreground App Detection

* Detects which app is currently active (e.g. `com.android.settings`, `com.android.chrome`, `com.whatsapp`)
* Uses `TYPE_WINDOW_STATE_CHANGED` accessibility events

### ✅ React Native ↔ Native Bridge

* Android Accessibility Service emits app change events
* Data is sent to React Native using a **custom native module** and `DeviceEventEmitter`

### ✅ Live UI Updates

* React Native UI updates instantly when the user switches apps
* Displays:

  ```
  Last detected app: com.android.chrome
  ```

### ⭐ Bonus: Floating Overlay (Optional Feature)

* Displays a small floating overlay (chat-head style) on top of other apps
* Shows the current foreground app in real time
* Implemented using Android `WindowManager`

---

## Tech Stack

| Layer           | Technology                    |
| --------------- | ----------------------------- |
| UI              | React Native                  |
| Native Android  | Kotlin                        |
| System Access   | Android Accessibility Service |
| Native Bridge   | React Native Native Module    |
| Overlay (Bonus) | WindowManager                 |

---

## Project Structure (Important Files)

```
android/app/src/main/java/com/appdetector/
├── AppDetectorAccessibilityService.kt   # Accessibility Service
├── AppDetectorModule.kt                 # Native bridge to React Native
├── AppDetectorPackage.kt                # ReactPackage registration
├── FloatingOverlayManager.kt             # Floating overlay manager
├── MainApplication.kt
└── MainActivity.kt

android/app/src/main/res/
├── xml/accessibility_service_config.xml
└── layout/floating_overlay.xml

App.tsx                                   # React Native UI
```

---

## How the App Works (High-Level Flow)

```
User switches app
      ↓
AccessibilityService receives event
      ↓
Foreground app package detected
      ↓
Native Module emits event
      ↓
React Native UI updates in real time
      ↓
(Optional) Floating overlay updates text
```

---

## Setup & Installation

### Prerequisites

* Node.js (≥ 16)
* Android Studio
* Android SDK
* Java JDK 17
* Android device or emulator (Android 8+ recommended)

---

### Install Dependencies

```bash
npm install
```

---

### Run the App on Android

```bash
npx react-native run-android
```

---

## Required Permissions (Manual)

### 1. Enable Accessibility Service

1. Open **Settings**
2. Go to **Accessibility**
3. Find **App Detector**
4. Enable the service

### 2. Enable Overlay Permission (Bonus Feature)

1. Open **Settings**
2. Go to **Apps → App Detector**
3. Enable **Display over other apps**

---

## How to Test the App (Demo Flow)

1. Open **App Detector**
2. Tap **Enable Accessibility Service**
3. Enable the service in system settings
4. Press Home
5. Open **Settings** or **Chrome**
6. Return to App Detector

### Expected Output

```
Last detected app: com.android.settings
```

If overlay permission is enabled, a floating overlay will also show the detected app.

---

## Handling Real-World Edge Cases

* Launcher apps (e.g. `com.android.launcher`) are filtered out
* The app ignores its own package name
* Duplicate accessibility events are deduplicated for stability

This ensures accurate and meaningful detection in real usage scenarios.

---

## Why Accessibility Service?

Android does not allow regular applications to detect foreground apps for privacy reasons.
Accessibility Services are the **approved system-level mechanism** for observing window and UI state changes, making them suitable for assistive and productivity tools like **Gig Assistant**.

---

## Build APK (Optional)

To generate a debug APK:

```bash
cd android
./gradlew assembleDebug
```

APK location:

```
android/app/build/outputs/apk/debug/
```

---

## Demo Video

The demo video demonstrates:

* Enabling Accessibility Service
* Switching between apps
* Live detection in React Native UI
* Floating overlay updating in real time

---

## Summary

This project demonstrates:

* Correct use of Android Accessibility APIs
* Clean React Native ↔ Kotlin bridging
* Real-time system event handling
* Production-style filtering and stability
* Optional advanced Android overlay integration

---

## Author

**Pradeep Simha**
React Native & Full Stack Developer
