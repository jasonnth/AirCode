package com.airbnb.p027n2.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.n2.components.AutoResizableButtonBar */
public class AutoResizableButtonBar extends RelativeLayout implements OnPreDrawListener {
    @BindView
    LinearLayout container;
    private State currentState;
    @BindView
    AirButton leftButton;
    private OnClickListener leftButtonAction;
    @BindView
    LoadingView loader;
    @BindView
    AirButton rightButton;
    private OnClickListener rightButtonAction;

    /* renamed from: com.airbnb.n2.components.AutoResizableButtonBar$State */
    public enum State {
        Normal,
        Loading
    }

    public AutoResizableButtonBar(Context context) {
        super(context);
        init();
    }

    public AutoResizableButtonBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoResizableButtonBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_auto_resizable_button_bar, this);
        ButterKnife.bind((View) this);
        this.rightButton.getViewTreeObserver().addOnPreDrawListener(this);
        this.leftButton.getViewTreeObserver().addOnPreDrawListener(this);
    }

    public void setRightButtonText(int stringRes) {
        setRightButtonText((CharSequence) getResources().getString(stringRes));
    }

    public void setRightButtonText(CharSequence text) {
        this.rightButton.setText(text);
    }

    public void setRightButtonAction(OnClickListener listener) {
        this.rightButtonAction = listener;
    }

    public void setLeftButtonText(int stringRes) {
        setLeftButtonText((CharSequence) getResources().getString(stringRes));
    }

    public void setLeftButtonText(CharSequence text) {
        this.leftButton.setText(text);
    }

    public void setLeftButtonAction(OnClickListener listener) {
        this.leftButtonAction = listener;
    }

    @OnClick
    public void rightButtonClicked(View v) {
        if (this.rightButtonAction != null) {
            this.rightButtonAction.onClick(v);
        }
    }

    @OnClick
    public void leftButtonClicked(View v) {
        if (this.leftButtonAction != null) {
            this.leftButtonAction.onClick(v);
        }
    }

    public void setState(State state) {
        switch (state) {
            case Loading:
                this.currentState = State.Loading;
                this.container.setVisibility(4);
                this.loader.setVisibility(0);
                return;
            default:
                this.currentState = State.Normal;
                this.loader.setVisibility(8);
                this.container.setVisibility(0);
                return;
        }
    }

    public State getState() {
        return this.currentState;
    }

    public View getView() {
        return this;
    }

    public boolean onPreDraw() {
        if (this.rightButton.getLineCount() > 1 || this.leftButton.getLineCount() > 1) {
            this.container.setOrientation(1);
            getViewTreeObserver().removeOnPreDrawListener(this);
            this.container.removeAllViews();
            this.container.addView(this.rightButton);
            this.container.addView(this.leftButton);
            ((LayoutParams) this.leftButton.getLayoutParams()).setMargins(0, getResources().getDimensionPixelSize(R.dimen.n2_resizable_buttons_space), 0, 0);
            ((LayoutParams) this.rightButton.getLayoutParams()).setMargins(0, 0, 0, getResources().getDimensionPixelSize(R.dimen.n2_resizable_buttons_space));
        }
        return true;
    }

    public static void mock(AutoResizableButtonBar view) {
        view.setRightButtonText((CharSequence) "Send message");
        view.setLeftButtonText((CharSequence) "Book");
    }

    public static void mockLoadingView(AutoResizableButtonBar view) {
        view.setRightButtonText((CharSequence) "Send message");
        view.setLeftButtonText((CharSequence) "Book");
        view.setState(State.Loading);
    }

    public static void mockLongText(AutoResizableButtonBar view) {
        view.setRightButtonText((CharSequence) "This is a really long text (Click me)");
        view.setLeftButtonText((CharSequence) "Book (Click me)");
        view.setRightButtonAction(new OnClickListener(view) {
            final /* synthetic */ AutoResizableButtonBar val$view;

            {
                this.val$view = r1;
            }

            public void onClick(View v) {
                Toast.makeText(this.val$view.getContext(), "Right Button Clicked", 0).show();
            }
        });
        view.setLeftButtonAction(new OnClickListener(view) {
            final /* synthetic */ AutoResizableButtonBar val$view;

            {
                this.val$view = r1;
            }

            public void onClick(View v) {
                Toast.makeText(this.val$view.getContext(), "Left Button Clicked", 0).show();
            }
        });
    }

    public static void mockLoadingViewLong(AutoResizableButtonBar view) {
        view.setRightButtonText((CharSequence) "Send message");
        view.setLeftButtonText((CharSequence) "This is a really really long text");
        view.setState(State.Loading);
    }
}
