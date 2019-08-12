package com.appboy.p028ui.inappmessage.listeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* renamed from: com.appboy.ui.inappmessage.listeners.SimpleSwipeDismissTouchListener */
public class SimpleSwipeDismissTouchListener implements OnTouchListener {
    private final GestureDetector mSwipeGestureListener;

    /* renamed from: com.appboy.ui.inappmessage.listeners.SimpleSwipeDismissTouchListener$SwipeGestureListener */
    private final class SwipeGestureListener extends SimpleOnGestureListener {
        private SwipeGestureListener() {
        }

        public boolean onFling(MotionEvent downEvent, MotionEvent upEvent, float velocityX, float velocityY) {
            float distanceX = upEvent.getX() - downEvent.getX();
            if (Math.abs(distanceX) <= Math.abs(upEvent.getY() - downEvent.getY()) || Math.abs(distanceX) <= 120.0f || Math.abs(velocityX) <= 90.0f) {
                return false;
            }
            if (distanceX > 0.0f) {
                SimpleSwipeDismissTouchListener.this.onSwipeRight();
            } else {
                SimpleSwipeDismissTouchListener.this.onSwipeLeft();
            }
            return true;
        }
    }

    public SimpleSwipeDismissTouchListener(Context context) {
        this.mSwipeGestureListener = new GestureDetector(context, new SwipeGestureListener());
    }

    public void onSwipeLeft() {
    }

    public void onSwipeRight() {
    }

    public boolean onTouch(View view, MotionEvent event) {
        return this.mSwipeGestureListener.onTouchEvent(event);
    }
}
