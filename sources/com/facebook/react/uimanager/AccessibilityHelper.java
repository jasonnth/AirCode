package com.facebook.react.uimanager;

import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.RadioButton;

class AccessibilityHelper {
    private static final String BUTTON = "button";
    private static final AccessibilityDelegate BUTTON_DELEGATE = new AccessibilityDelegate() {
        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(Button.class.getName());
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setClassName(Button.class.getName());
        }
    };
    private static final String RADIOBUTTON_CHECKED = "radiobutton_checked";
    private static final AccessibilityDelegate RADIOBUTTON_CHECKED_DELEGATE = new AccessibilityDelegate() {
        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(RadioButton.class.getName());
            event.setChecked(true);
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setClassName(RadioButton.class.getName());
            info.setCheckable(true);
            info.setChecked(true);
        }
    };
    private static final String RADIOBUTTON_UNCHECKED = "radiobutton_unchecked";
    private static final AccessibilityDelegate RADIOBUTTON_UNCHECKED_DELEGATE = new AccessibilityDelegate() {
        public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
            super.onInitializeAccessibilityEvent(host, event);
            event.setClassName(RadioButton.class.getName());
            event.setChecked(false);
        }

        public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
            super.onInitializeAccessibilityNodeInfo(host, info);
            info.setClassName(RadioButton.class.getName());
            info.setCheckable(true);
            info.setChecked(false);
        }
    };

    AccessibilityHelper() {
    }

    public static void updateAccessibilityComponentType(View view, String componentType) {
        if (componentType == null) {
            view.setAccessibilityDelegate(null);
            return;
        }
        char c = 65535;
        switch (componentType.hashCode()) {
            case -1377687758:
                if (componentType.equals(BUTTON)) {
                    c = 0;
                    break;
                }
                break;
            case -1320494052:
                if (componentType.equals(RADIOBUTTON_UNCHECKED)) {
                    c = 2;
                    break;
                }
                break;
            case -714126251:
                if (componentType.equals(RADIOBUTTON_CHECKED)) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                view.setAccessibilityDelegate(BUTTON_DELEGATE);
                return;
            case 1:
                view.setAccessibilityDelegate(RADIOBUTTON_CHECKED_DELEGATE);
                return;
            case 2:
                view.setAccessibilityDelegate(RADIOBUTTON_UNCHECKED_DELEGATE);
                return;
            default:
                view.setAccessibilityDelegate(null);
                return;
        }
    }

    public static void sendAccessibilityEvent(View view, int eventType) {
        view.sendAccessibilityEvent(eventType);
    }
}
