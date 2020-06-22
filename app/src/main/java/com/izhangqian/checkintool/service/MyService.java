package com.izhangqian.checkintool.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.graphics.Path;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityWindowInfo;

import com.izhangqian.checkintool.listener.ServiceListener;
import com.izhangqian.checkintool.utils.Logit;

import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * Created by xtayf on 2020/5/30.
 */

public class MyService extends AccessibilityService implements ServiceListener {

    private static final String TAG = "MyService";
    private static MyService instance;
    private static ServiceListener listener;

    public static MyService getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        listener = this;
    }

    public static ServiceListener getListener() {
        return listener;
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        Logit.Companion.i(TAG, "service connect");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        Logit.Companion.i(TAG, "on event");
    }

    @Override
    public void onInterrupt() {
        Logit.Companion.i(TAG, "on interrupt");
    }

    @Override
    public AccessibilityNodeInfo findNodeByText(String text, String viewType) {
        AccessibilityNodeInfo root = getRootInActiveWindow();
        AccessibilityNodeInfo nodeInfo = null;
        if (root != null) {
            List<AccessibilityNodeInfo> nodeInfos = root.findAccessibilityNodeInfosByText(text);
            if (nodeInfos != null && nodeInfos.size() > 0) {
                if (TextUtils.isEmpty(viewType)) {
                    nodeInfo = nodeInfos.get(0);
                } else {
                    for (AccessibilityNodeInfo nodeInfo1 : nodeInfos) {
                        String className = (String) nodeInfo1.getClassName();
                        if (TextUtils.equals(className, viewType)) {
                            nodeInfo = nodeInfo1;
                            break;
                        }
                    }
                }
            }
        }

        return nodeInfo;
    }

    // 需要重新写
    @Override
    public AccessibilityNodeInfo findNodeByDesc(String text, String viewType) {
        AccessibilityNodeInfo root = getRootInActiveWindow();
        AccessibilityNodeInfo nodeInfo = null;
        if (root != null) {
            List<AccessibilityNodeInfo> nodeInfos = root.findAccessibilityNodeInfosByText(text);
            if (nodeInfos != null && nodeInfos.size() > 0) {
                nodeInfo = nodeInfos.get(0);
            }
        }

        return nodeInfo;
    }

    @Nullable
    @Override
    public AccessibilityNodeInfo findNodeById(@Nullable String id, @Nullable String viewType) {
        AccessibilityNodeInfo root = getRootInActiveWindow();
        AccessibilityNodeInfo nodeInfo = null;
        if (root != null) {
            List<AccessibilityNodeInfo> nodeInfos = root.findAccessibilityNodeInfosByViewId(id);
            if (nodeInfos != null && nodeInfos.size() > 0) {
                if (TextUtils.isEmpty(viewType)) {
                    nodeInfo = nodeInfos.get(0);
                } else {
                    for (AccessibilityNodeInfo nodeInfo1 : nodeInfos) {
                        String className = (String) nodeInfo1.getClassName();
                        if (TextUtils.equals(className, viewType)) {
                            nodeInfo = nodeInfo1;
                            break;
                        }
                    }
                }
            }
        }
        return nodeInfo;
    }

    @Override
    public boolean performClick(@Nullable AccessibilityNodeInfo nodeInfo) {
        if (nodeInfo == null) {
            return false;
        }
        if (nodeInfo.isClickable()) {
            return nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        } else {
            return performClick(nodeInfo.getParent());
        }
    }

    @Override
    public boolean performTouch(float x, float y, long duration) {
        GestureDescription.Builder gestureBuilder = new GestureDescription.Builder();
        Path path = new Path();
        path.moveTo(x, y);
        gestureBuilder.addStroke(new GestureDescription.StrokeDescription(path, 100, duration));
        boolean res = dispatchGesture(gestureBuilder.build(), new GestureResultCallback() {
            @Override
            public void onCompleted(GestureDescription gestureDescription) {
                super.onCompleted(gestureDescription);
            }
        }, null);
        return res;
    }

//    public static void sendClickEvent(final float x, final float y, final long duration) {
//        Log.i(TAG, "send click event : " + x + " , " + y + " ; duration : "+duration);
//        try {
//            InstrumentUtils.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                    SystemClock.uptimeMillis(), MotionEvent.ACTION_DOWN, x, y, 0));
//            Thread.sleep(duration);
//            InstrumentUtils.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),
//                    SystemClock.uptimeMillis(), MotionEvent.ACTION_UP, x, y, 0));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
