package com.devbrackets.android.exomedia.core.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.SurfaceTexture;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.devbrackets.android.exomedia.core.video.scale.MatrixManager;
import com.devbrackets.android.exomedia.core.video.scale.ScaleType;
import com.miteksystems.facialcapture.science.api.params.FacialCaptureApiConstants;
import java.util.concurrent.locks.ReentrantLock;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

public class ResizingTextureView extends TextureView {
    private static final int[] GL_CLEAR_CONFIG_ATTRIBUTES = {12324, 8, 12323, 8, 12322, 8, 12321, 8, 12352, 4, 12344, 0, 12344};
    private static final int[] GL_CLEAR_CONTEXT_ATTRIBUTES = {12440, 2, 12344};
    protected AttachedListener attachedListener = new AttachedListener();
    protected GlobalLayoutMatrixListener globalLayoutMatrixListener = new GlobalLayoutMatrixListener();
    protected final ReentrantLock globalLayoutMatrixListenerLock = new ReentrantLock(true);
    protected Point lastNotifiedSize = new Point(0, 0);
    protected MatrixManager matrixManager = new MatrixManager();
    protected boolean measureBasedOnAspectRatio;
    protected OnSizeChangeListener onSizeChangeListener;
    protected int requestedConfigurationRotation = 0;
    protected int requestedUserRotation = 0;
    protected Point videoSize = new Point(0, 0);

    private class AttachedListener implements OnAttachStateChangeListener {
        private AttachedListener() {
        }

        public void onViewAttachedToWindow(View view) {
            ResizingTextureView.this.globalLayoutMatrixListenerLock.lock();
            ResizingTextureView.this.getViewTreeObserver().addOnGlobalLayoutListener(ResizingTextureView.this.globalLayoutMatrixListener);
            ResizingTextureView.this.removeOnAttachStateChangeListener(this);
            ResizingTextureView.this.globalLayoutMatrixListenerLock.unlock();
        }

        public void onViewDetachedFromWindow(View view) {
        }
    }

    private class GlobalLayoutMatrixListener implements OnGlobalLayoutListener {
        private GlobalLayoutMatrixListener() {
        }

        public void onGlobalLayout() {
            ResizingTextureView.this.setScaleType(ResizingTextureView.this.matrixManager.getCurrentScaleType());
            if (VERSION.SDK_INT >= 16) {
                ResizingTextureView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            } else {
                ResizingTextureView.this.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        }
    }

    public interface OnSizeChangeListener {
        void onVideoSurfaceSizeChange(int i, int i2);
    }

    public ResizingTextureView(Context context) {
        super(context);
    }

    public ResizingTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResizingTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public ResizingTextureView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        if (!this.measureBasedOnAspectRatio) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            notifyOnSizeChangeListener(getMeasuredWidth(), getMeasuredHeight());
            return;
        }
        int width2 = getDefaultSize(this.videoSize.x, widthMeasureSpec);
        int height2 = getDefaultSize(this.videoSize.y, heightMeasureSpec);
        if (this.videoSize.x <= 0 || this.videoSize.y <= 0) {
            setMeasuredDimension(width2, height2);
            notifyOnSizeChangeListener(width2, height2);
            return;
        }
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == 1073741824 && heightSpecMode == 1073741824) {
            width = widthSpecSize;
            height = heightSpecSize;
            if (this.videoSize.x * height < this.videoSize.y * width) {
                width = (this.videoSize.x * height) / this.videoSize.y;
            } else if (this.videoSize.x * height > this.videoSize.y * width) {
                height = (this.videoSize.y * width) / this.videoSize.x;
            }
        } else if (widthSpecMode == 1073741824) {
            width = widthSpecSize;
            height = (this.videoSize.y * width) / this.videoSize.x;
            if (heightSpecMode == Integer.MIN_VALUE && height > heightSpecSize) {
                height = heightSpecSize;
            }
        } else if (heightSpecMode == 1073741824) {
            height = heightSpecSize;
            width = (this.videoSize.x * height) / this.videoSize.y;
            if (widthSpecMode == Integer.MIN_VALUE && width > widthSpecSize) {
                width = widthSpecSize;
            }
        } else {
            width = this.videoSize.x;
            height = this.videoSize.y;
            if (heightSpecMode == Integer.MIN_VALUE && height > heightSpecSize) {
                height = heightSpecSize;
                width = (this.videoSize.x * height) / this.videoSize.y;
            }
            if (widthSpecMode == Integer.MIN_VALUE && width > widthSpecSize) {
                width = widthSpecSize;
                height = (this.videoSize.y * width) / this.videoSize.x;
            }
        }
        setMeasuredDimension(width, height);
        notifyOnSizeChangeListener(width, height);
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration newConfig) {
        updateMatrixOnLayout();
        super.onConfigurationChanged(newConfig);
    }

    public void setOnSizeChangeListener(OnSizeChangeListener listener) {
        this.onSizeChangeListener = listener;
    }

    public void clearSurface() {
        if (getSurfaceTexture() != null) {
            try {
                EGL10 gl10 = (EGL10) EGLContext.getEGL();
                EGLDisplay display = gl10.eglGetDisplay(EGL10.EGL_DEFAULT_DISPLAY);
                gl10.eglInitialize(display, null);
                EGLConfig[] configs = new EGLConfig[1];
                gl10.eglChooseConfig(display, GL_CLEAR_CONFIG_ATTRIBUTES, configs, configs.length, new int[1]);
                EGLContext context = gl10.eglCreateContext(display, configs[0], EGL10.EGL_NO_CONTEXT, GL_CLEAR_CONTEXT_ATTRIBUTES);
                EGLSurface eglSurface = gl10.eglCreateWindowSurface(display, configs[0], getSurfaceTexture(), new int[]{12344});
                gl10.eglMakeCurrent(display, eglSurface, eglSurface, context);
                gl10.eglSwapBuffers(display, eglSurface);
                gl10.eglDestroySurface(display, eglSurface);
                gl10.eglMakeCurrent(display, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT);
                gl10.eglDestroyContext(display, context);
                gl10.eglTerminate(display);
            } catch (Exception e) {
                Log.e("ResizingTextureView", "Error clearing surface", e);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean updateVideoSize(int width, int height) {
        this.matrixManager.setIntrinsicVideoSize(width, height);
        updateMatrixOnLayout();
        this.videoSize.x = width;
        this.videoSize.y = height;
        if (width == 0 || height == 0) {
            return false;
        }
        if (VERSION.SDK_INT >= 15) {
            SurfaceTexture surfaceTexture = getSurfaceTexture();
            if (surfaceTexture == null) {
                return false;
            }
            surfaceTexture.setDefaultBufferSize(width, height);
        }
        return true;
    }

    public void setScaleType(ScaleType scaleType) {
        this.matrixManager.scale(this, scaleType);
    }

    public ScaleType getScaleType() {
        return this.matrixManager.getCurrentScaleType();
    }

    public void setMeasureBasedOnAspectRatioEnabled(boolean enabled) {
        this.measureBasedOnAspectRatio = enabled;
        requestLayout();
    }

    public void setVideoRotation(int rotation, boolean fromUser) {
        int i = fromUser ? rotation : this.requestedUserRotation;
        if (fromUser) {
            rotation = this.requestedConfigurationRotation;
        }
        setVideoRotation(i, rotation);
    }

    public void setVideoRotation(int userRotation, int configurationRotation) {
        this.requestedUserRotation = userRotation;
        this.requestedConfigurationRotation = configurationRotation;
        this.matrixManager.rotate(this, (userRotation + configurationRotation) % FacialCaptureApiConstants.EYE_MAX_DISTANCE_DEFAULT);
    }

    /* access modifiers changed from: protected */
    public void updateMatrixOnLayout() {
        this.globalLayoutMatrixListenerLock.lock();
        if (getWindowToken() == null) {
            addOnAttachStateChangeListener(this.attachedListener);
        } else {
            getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutMatrixListener);
        }
        this.globalLayoutMatrixListenerLock.unlock();
    }

    /* access modifiers changed from: protected */
    public void notifyOnSizeChangeListener(int width, int height) {
        if (this.lastNotifiedSize.x != width || this.lastNotifiedSize.y != height) {
            this.lastNotifiedSize.x = width;
            this.lastNotifiedSize.y = height;
            if (this.onSizeChangeListener != null) {
                this.onSizeChangeListener.onVideoSurfaceSizeChange(width, height);
            }
        }
    }
}
