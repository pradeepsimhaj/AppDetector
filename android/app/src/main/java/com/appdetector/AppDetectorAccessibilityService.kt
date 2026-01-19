package com.appdetector

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class AppDetectorAccessibilityService : AccessibilityService() {

    // Packages we want to ignore (launcher + our own app)
    private val ignoredPackages = listOf(
        "com.android.launcher",
        "com.google.android.launcher",
        "com.miui.home",
        "com.samsung.android.launcher",
        "com.appdetector" // <-- your app package
    )

    private var lastEmittedPackage: String? = null

    override fun onServiceConnected() {
        super.onServiceConnected()
        FloatingOverlayManager.showOverlay(this)
        FloatingOverlayManager.updateText("Service Active")
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event == null) return
        if (event.eventType != AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) return

        val packageName = event.packageName?.toString() ?: return

        // ❌ Ignore launcher and our own app
        if (ignoredPackages.any { packageName.startsWith(it) }) return

        // ❌ Prevent duplicate emissions
        if (packageName == lastEmittedPackage) return
        lastEmittedPackage = packageName

        // ✅ Update floating overlay
        FloatingOverlayManager.updateText(packageName)

        // ✅ Send to React Native
        AppDetectorModule.sendAppChangedEvent(packageName)
    }

    override fun onInterrupt() {
        // Required override
    }

    override fun onDestroy() {
        super.onDestroy()
        FloatingOverlayManager.hideOverlay()
    }
}
