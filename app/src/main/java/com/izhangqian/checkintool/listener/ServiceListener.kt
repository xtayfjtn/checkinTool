package com.izhangqian.checkintool.listener

import android.view.accessibility.AccessibilityNodeInfo

interface ServiceListener {
    fun findNodeByText(text: String?, viewType: String?): AccessibilityNodeInfo?
    fun findNodeByDesc(text: String?, viewType: String?): AccessibilityNodeInfo?
    fun findNodeById(id: String?, viewType: String?): AccessibilityNodeInfo?
    fun performClick(nodeInfo: AccessibilityNodeInfo?): Boolean
    fun performTouch(x: Float, y: Float, duration: Long): Boolean
}