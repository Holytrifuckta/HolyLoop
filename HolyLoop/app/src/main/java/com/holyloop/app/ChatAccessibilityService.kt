package com.holyloop.app

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent

class ChatAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) { /* v0.1: deliberately read-only and inactive until verified */ }
    override fun onInterrupt() {}
}
