package com.airbnb.p027n2.components;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.p000v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageListener;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.messaging.MessageInputListener;

/* renamed from: com.airbnb.n2.components.MessageInputRow */
public abstract class MessageInputRow extends LinearLayout {
    private String imagePath;
    @BindView
    AirImageView imagePreview;
    protected AirEditTextView input;
    protected MessageInputListener messageInputListener;
    @BindView
    AirTextView sendButton;

    /* access modifiers changed from: protected */
    public abstract void bindViews();

    /* access modifiers changed from: protected */
    public abstract int getLayoutId();

    public abstract ImageView getSavedMessageIcon();

    public MessageInputRow(Context context) {
        super(context);
        init();
    }

    public MessageInputRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MessageInputRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public MessageInputRow(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void setMessageInputListener(MessageInputListener messageInputListener2) {
        this.messageInputListener = messageInputListener2;
    }

    public void setMessageHint(String hint) {
        this.input.setHint(hint);
    }

    public void setButtonClickListener(OnClickListener listener) {
        this.sendButton.setOnClickListener(listener);
    }

    public String getInputText() {
        return this.input.getText().toString();
    }

    public void clearInputText() {
        this.input.setText("");
    }

    public void setInputText(String text) {
        this.input.setText(text);
        this.input.setSelection(text.length());
        this.input.setOnFocusChangeListener(MessageInputRow$$Lambda$1.lambdaFactory$(this));
        this.input.requestFocus();
    }

    static /* synthetic */ void lambda$null$0(MessageInputRow messageInputRow, View v) {
        InputMethodManager imm = (InputMethodManager) messageInputRow.getContext().getSystemService("input_method");
        if (imm != null) {
            imm.showSoftInput(v, 0);
        }
    }

    public void setButtonText(String text) {
        this.sendButton.setText(text);
    }

    public void setImagePreview(String imagePath2) {
        this.imagePath = imagePath2;
        fetchImage();
        this.imagePreview.setAdjustViewBounds(true);
        this.imagePreview.setVisibility(0);
        this.input.setVisibility(8);
        updateButtonState();
    }

    public void clearImagePreview() {
        this.imagePath = null;
        this.imagePreview.clear();
        this.imagePreview.setVisibility(8);
        this.input.setVisibility(0);
        updateButtonState();
    }

    public String getImagePath() {
        return this.imagePath;
    }

    /* access modifiers changed from: protected */
    public void init() {
        inflate(getContext(), getLayoutId(), this);
        ButterKnife.bind((View) this);
        setOrientation(1);
        bindViews();
        setupButton();
    }

    /* access modifiers changed from: protected */
    public void setupButton() {
        updateButtonState();
        this.input.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                MessageInputRow.this.updateButtonState();
            }
        });
    }

    /* access modifiers changed from: private */
    public void updateButtonState() {
        boolean hasInput = !TextUtils.isEmpty(this.input.getText().toString().trim()) || this.imagePreview.getVisibility() == 0;
        this.sendButton.setTextColor(ContextCompat.getColor(getContext(), hasInput ? R.color.n2_babu : R.color.n2_text_color_main));
        this.sendButton.setEnabled(hasInput);
    }

    private void fetchImage() {
        AirImageView.getImage(getContext(), this.imagePath, new AirImageListener() {
            public void onResponse(Bitmap response, boolean isImmediate) {
                MessageInputRow.this.imagePreview.setImageBitmap(response);
            }

            public void onErrorResponse(Exception exception) {
            }
        });
    }
}
