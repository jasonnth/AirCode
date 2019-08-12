package p003at.grabner.circleprogress;

import android.animation.TimeInterpolator;
import android.os.Handler;
import android.os.Message;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.lang.ref.WeakReference;
import jumio.p317nv.nfc.C4966a;

/* renamed from: at.grabner.circleprogress.AnimationHandler */
public class AnimationHandler extends Handler {
    private long mAnimationStartTime;
    private final WeakReference<CircleProgressView> mCircleViewWeakReference;
    private TimeInterpolator mInterpolator = new AccelerateDecelerateInterpolator();
    private double mLengthChangeAnimationDuration;
    private long mLengthChangeAnimationStartTime;
    private TimeInterpolator mLengthChangeInterpolator = new DecelerateInterpolator();
    private float mSpinningBarLengthStart;

    AnimationHandler(CircleProgressView circleProgressView) {
        super(circleProgressView.getContext().getMainLooper());
        this.mCircleViewWeakReference = new WeakReference<>(circleProgressView);
    }

    public void handleMessage(Message message) {
        CircleProgressView circleProgressView = (CircleProgressView) this.mCircleViewWeakReference.get();
        if (circleProgressView != null) {
            C4966a aVar = C4966a.values()[message.what];
            if (aVar == C4966a.TICK) {
                removeMessages(C4966a.TICK.ordinal());
            }
            switch (circleProgressView.mAnimationState) {
                case IDLE:
                    switch (aVar) {
                        case START_SPINNING:
                            enterSpinning(circleProgressView);
                            return;
                        case SET_VALUE:
                            setValue(message, circleProgressView);
                            return;
                        case SET_VALUE_ANIMATED:
                            enterSetValueAnimated(message, circleProgressView);
                            return;
                        case TICK:
                            removeMessages(C4966a.TICK.ordinal());
                            return;
                        default:
                            return;
                    }
                case SPINNING:
                    switch (aVar) {
                        case STOP_SPINNING:
                            enterEndSpinning(circleProgressView);
                            return;
                        case SET_VALUE:
                            setValue(message, circleProgressView);
                            return;
                        case SET_VALUE_ANIMATED:
                            enterEndSpinningStartAnimating(circleProgressView, message);
                            return;
                        case TICK:
                            float f = circleProgressView.mSpinningBarLengthCurrent - circleProgressView.mSpinningBarLengthOrig;
                            float currentTimeMillis = (float) (((double) (System.currentTimeMillis() - this.mLengthChangeAnimationStartTime)) / this.mLengthChangeAnimationDuration);
                            if (currentTimeMillis > 1.0f) {
                                currentTimeMillis = 1.0f;
                            }
                            float interpolation = this.mLengthChangeInterpolator.getInterpolation(currentTimeMillis);
                            if (Math.abs(f) < 1.0f) {
                                circleProgressView.mSpinningBarLengthCurrent = circleProgressView.mSpinningBarLengthOrig;
                            } else if (circleProgressView.mSpinningBarLengthCurrent < circleProgressView.mSpinningBarLengthOrig) {
                                circleProgressView.mSpinningBarLengthCurrent = (interpolation * (circleProgressView.mSpinningBarLengthOrig - this.mSpinningBarLengthStart)) + this.mSpinningBarLengthStart;
                            } else {
                                circleProgressView.mSpinningBarLengthCurrent = this.mSpinningBarLengthStart - (interpolation * (this.mSpinningBarLengthStart - circleProgressView.mSpinningBarLengthOrig));
                            }
                            circleProgressView.mCurrentSpinnerDegreeValue += circleProgressView.mSpinSpeed;
                            if (circleProgressView.mCurrentSpinnerDegreeValue > 360.0f) {
                                circleProgressView.mCurrentSpinnerDegreeValue = 0.0f;
                            }
                            sendEmptyMessageDelayed(C4966a.TICK.ordinal(), (long) circleProgressView.mDelayMillis);
                            circleProgressView.invalidate();
                            return;
                        default:
                            return;
                    }
                case END_SPINNING:
                    switch (aVar) {
                        case START_SPINNING:
                            circleProgressView.mAnimationState = AnimationState.SPINNING;
                            if (circleProgressView.mAnimationStateChangedListener != null) {
                                circleProgressView.mAnimationStateChangedListener.onAnimationStateChanged(circleProgressView.mAnimationState);
                            }
                            sendEmptyMessageDelayed(C4966a.TICK.ordinal(), (long) circleProgressView.mDelayMillis);
                            return;
                        case SET_VALUE:
                            setValue(message, circleProgressView);
                            return;
                        case SET_VALUE_ANIMATED:
                            enterEndSpinningStartAnimating(circleProgressView, message);
                            return;
                        case TICK:
                            float currentTimeMillis2 = (float) (((double) (System.currentTimeMillis() - this.mLengthChangeAnimationStartTime)) / this.mLengthChangeAnimationDuration);
                            if (currentTimeMillis2 > 1.0f) {
                                currentTimeMillis2 = 1.0f;
                            }
                            circleProgressView.mSpinningBarLengthCurrent = (1.0f - this.mLengthChangeInterpolator.getInterpolation(currentTimeMillis2)) * this.mSpinningBarLengthStart;
                            circleProgressView.mCurrentSpinnerDegreeValue += circleProgressView.mSpinSpeed;
                            if (circleProgressView.mSpinningBarLengthCurrent < 0.01f) {
                                circleProgressView.mAnimationState = AnimationState.IDLE;
                                if (circleProgressView.mAnimationStateChangedListener != null) {
                                    circleProgressView.mAnimationStateChangedListener.onAnimationStateChanged(circleProgressView.mAnimationState);
                                }
                            }
                            sendEmptyMessageDelayed(C4966a.TICK.ordinal(), (long) circleProgressView.mDelayMillis);
                            circleProgressView.invalidate();
                            return;
                        default:
                            return;
                    }
                case END_SPINNING_START_ANIMATING:
                    switch (aVar) {
                        case START_SPINNING:
                            circleProgressView.mDrawBarWhileSpinning = false;
                            enterSpinning(circleProgressView);
                            return;
                        case SET_VALUE:
                            circleProgressView.mDrawBarWhileSpinning = false;
                            setValue(message, circleProgressView);
                            return;
                        case SET_VALUE_ANIMATED:
                            circleProgressView.mValueFrom = 0.0f;
                            circleProgressView.mValueTo = ((float[]) message.obj)[1];
                            sendEmptyMessageDelayed(C4966a.TICK.ordinal(), (long) circleProgressView.mDelayMillis);
                            return;
                        case TICK:
                            if (circleProgressView.mSpinningBarLengthCurrent > circleProgressView.mSpinningBarLengthOrig && !circleProgressView.mDrawBarWhileSpinning) {
                                float currentTimeMillis3 = (float) (((double) (System.currentTimeMillis() - this.mLengthChangeAnimationStartTime)) / this.mLengthChangeAnimationDuration);
                                if (currentTimeMillis3 > 1.0f) {
                                    currentTimeMillis3 = 1.0f;
                                }
                                circleProgressView.mSpinningBarLengthCurrent = (1.0f - this.mLengthChangeInterpolator.getInterpolation(currentTimeMillis3)) * this.mSpinningBarLengthStart;
                            }
                            circleProgressView.mCurrentSpinnerDegreeValue += circleProgressView.mSpinSpeed;
                            if (circleProgressView.mCurrentSpinnerDegreeValue > 360.0f && !circleProgressView.mDrawBarWhileSpinning) {
                                this.mAnimationStartTime = System.currentTimeMillis();
                                circleProgressView.mDrawBarWhileSpinning = true;
                                initReduceAnimation(circleProgressView);
                                if (circleProgressView.mAnimationStateChangedListener != null) {
                                    circleProgressView.mAnimationStateChangedListener.onAnimationStateChanged(AnimationState.START_ANIMATING_AFTER_SPINNING);
                                }
                            }
                            if (circleProgressView.mDrawBarWhileSpinning) {
                                circleProgressView.mCurrentSpinnerDegreeValue = 360.0f;
                                circleProgressView.mSpinningBarLengthCurrent -= circleProgressView.mSpinSpeed;
                                calcNextAnimationValue(circleProgressView);
                                float currentTimeMillis4 = (float) (((double) (System.currentTimeMillis() - this.mLengthChangeAnimationStartTime)) / this.mLengthChangeAnimationDuration);
                                if (currentTimeMillis4 > 1.0f) {
                                    currentTimeMillis4 = 1.0f;
                                }
                                circleProgressView.mSpinningBarLengthCurrent = (1.0f - this.mLengthChangeInterpolator.getInterpolation(currentTimeMillis4)) * this.mSpinningBarLengthStart;
                            }
                            if (((double) circleProgressView.mSpinningBarLengthCurrent) < 0.1d) {
                                circleProgressView.mAnimationState = AnimationState.ANIMATING;
                                if (circleProgressView.mAnimationStateChangedListener != null) {
                                    circleProgressView.mAnimationStateChangedListener.onAnimationStateChanged(circleProgressView.mAnimationState);
                                }
                                circleProgressView.invalidate();
                                circleProgressView.mDrawBarWhileSpinning = false;
                                circleProgressView.mSpinningBarLengthCurrent = circleProgressView.mSpinningBarLengthOrig;
                            } else {
                                circleProgressView.invalidate();
                            }
                            sendEmptyMessageDelayed(C4966a.TICK.ordinal(), (long) circleProgressView.mDelayMillis);
                            return;
                        default:
                            return;
                    }
                case ANIMATING:
                    switch (aVar) {
                        case START_SPINNING:
                            enterSpinning(circleProgressView);
                            return;
                        case SET_VALUE:
                            setValue(message, circleProgressView);
                            return;
                        case SET_VALUE_ANIMATED:
                            this.mAnimationStartTime = System.currentTimeMillis();
                            circleProgressView.mValueFrom = circleProgressView.mCurrentValue;
                            circleProgressView.mValueTo = ((float[]) message.obj)[1];
                            return;
                        case TICK:
                            if (calcNextAnimationValue(circleProgressView)) {
                                circleProgressView.mAnimationState = AnimationState.IDLE;
                                if (circleProgressView.mAnimationStateChangedListener != null) {
                                    circleProgressView.mAnimationStateChangedListener.onAnimationStateChanged(circleProgressView.mAnimationState);
                                }
                                circleProgressView.mCurrentValue = circleProgressView.mValueTo;
                            }
                            sendEmptyMessageDelayed(C4966a.TICK.ordinal(), (long) circleProgressView.mDelayMillis);
                            circleProgressView.invalidate();
                            return;
                        default:
                            return;
                    }
                default:
                    return;
            }
        }
    }

    private void enterSetValueAnimated(Message message, CircleProgressView circleProgressView) {
        circleProgressView.mValueFrom = ((float[]) message.obj)[0];
        circleProgressView.mValueTo = ((float[]) message.obj)[1];
        this.mAnimationStartTime = System.currentTimeMillis();
        circleProgressView.mAnimationState = AnimationState.ANIMATING;
        if (circleProgressView.mAnimationStateChangedListener != null) {
            circleProgressView.mAnimationStateChangedListener.onAnimationStateChanged(circleProgressView.mAnimationState);
        }
        sendEmptyMessageDelayed(C4966a.TICK.ordinal(), (long) circleProgressView.mDelayMillis);
    }

    private void enterEndSpinningStartAnimating(CircleProgressView circleProgressView, Message message) {
        circleProgressView.mAnimationState = AnimationState.END_SPINNING_START_ANIMATING;
        if (circleProgressView.mAnimationStateChangedListener != null) {
            circleProgressView.mAnimationStateChangedListener.onAnimationStateChanged(circleProgressView.mAnimationState);
        }
        circleProgressView.mValueFrom = 0.0f;
        circleProgressView.mValueTo = ((float[]) message.obj)[1];
        this.mLengthChangeAnimationStartTime = System.currentTimeMillis();
        this.mSpinningBarLengthStart = circleProgressView.mSpinningBarLengthCurrent;
        sendEmptyMessageDelayed(C4966a.TICK.ordinal(), (long) circleProgressView.mDelayMillis);
    }

    private void enterEndSpinning(CircleProgressView circleProgressView) {
        circleProgressView.mAnimationState = AnimationState.END_SPINNING;
        initReduceAnimation(circleProgressView);
        if (circleProgressView.mAnimationStateChangedListener != null) {
            circleProgressView.mAnimationStateChangedListener.onAnimationStateChanged(circleProgressView.mAnimationState);
        }
        sendEmptyMessageDelayed(C4966a.TICK.ordinal(), (long) circleProgressView.mDelayMillis);
    }

    private void initReduceAnimation(CircleProgressView circleProgressView) {
        this.mLengthChangeAnimationDuration = (double) ((circleProgressView.mSpinningBarLengthCurrent / circleProgressView.mSpinSpeed) * ((float) circleProgressView.mDelayMillis) * 2.0f);
        this.mLengthChangeAnimationStartTime = System.currentTimeMillis();
        this.mSpinningBarLengthStart = circleProgressView.mSpinningBarLengthCurrent;
    }

    private void enterSpinning(CircleProgressView circleProgressView) {
        circleProgressView.mAnimationState = AnimationState.SPINNING;
        if (circleProgressView.mAnimationStateChangedListener != null) {
            circleProgressView.mAnimationStateChangedListener.onAnimationStateChanged(circleProgressView.mAnimationState);
        }
        circleProgressView.mSpinningBarLengthCurrent = (360.0f / circleProgressView.mMaxValue) * circleProgressView.mCurrentValue;
        circleProgressView.mCurrentSpinnerDegreeValue = (360.0f / circleProgressView.mMaxValue) * circleProgressView.mCurrentValue;
        this.mLengthChangeAnimationStartTime = System.currentTimeMillis();
        this.mSpinningBarLengthStart = circleProgressView.mSpinningBarLengthCurrent;
        this.mLengthChangeAnimationDuration = (double) ((circleProgressView.mSpinningBarLengthOrig / circleProgressView.mSpinSpeed) * ((float) circleProgressView.mDelayMillis) * 2.0f);
        sendEmptyMessageDelayed(C4966a.TICK.ordinal(), (long) circleProgressView.mDelayMillis);
    }

    private boolean calcNextAnimationValue(CircleProgressView circleProgressView) {
        float currentTimeMillis = (float) (((double) (System.currentTimeMillis() - this.mAnimationStartTime)) / circleProgressView.mAnimationDuration);
        if (currentTimeMillis > 1.0f) {
            currentTimeMillis = 1.0f;
        }
        circleProgressView.mCurrentValue = (this.mInterpolator.getInterpolation(currentTimeMillis) * (circleProgressView.mValueTo - circleProgressView.mValueFrom)) + circleProgressView.mValueFrom;
        return currentTimeMillis >= 1.0f;
    }

    private void setValue(Message message, CircleProgressView circleProgressView) {
        circleProgressView.mValueFrom = circleProgressView.mValueTo;
        float f = ((float[]) message.obj)[0];
        circleProgressView.mValueTo = f;
        circleProgressView.mCurrentValue = f;
        circleProgressView.mAnimationState = AnimationState.IDLE;
        if (circleProgressView.mAnimationStateChangedListener != null) {
            circleProgressView.mAnimationStateChangedListener.onAnimationStateChanged(circleProgressView.mAnimationState);
        }
        circleProgressView.invalidate();
    }
}
