package com.airbnb.android.core.viewcomponents.models;

import android.content.Context;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import com.airbnb.p027n2.components.InlineFormattedIntegerInputRow;
import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.utils.IntegerNumberFormatHelper;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;

public abstract class InlineFormattedIntegerInputRowEpoxyModel extends AirEpoxyModel<InlineFormattedIntegerInputRow> {
    Listener amountChangedListener;
    boolean enabled = true;
    OnFocusChangeListener focusChangeListener;
    CharSequence hint;
    int hintRes;
    Integer inputAmount;
    private final Listener listenerWrapper = new Listener() {
        public void amountChanged(Integer inputAmountvalue) {
            InlineFormattedIntegerInputRowEpoxyModel.this.inputAmount = inputAmountvalue;
            InlineFormattedIntegerInputRowEpoxyModel.this.showError = false;
            if (InlineFormattedIntegerInputRowEpoxyModel.this.amountChangedListener != null) {
                InlineFormattedIntegerInputRowEpoxyModel.this.amountChangedListener.amountChanged(InlineFormattedIntegerInputRowEpoxyModel.this.inputAmount);
            }
        }
    };
    NumberFormat numberFormat;
    boolean removeHintOnFocusMode;
    boolean showError;
    CharSequence subTitle;
    int subTitleRes;
    CharSequence tip;
    Integer tipAmount;
    OnClickListener tipClickListener;
    int tipRes;
    CharSequence title;
    int titleRes;
    boolean updateModelData = true;

    public static InlineFormattedIntegerInputRowEpoxyModel_ forCurrency(Currency currency) {
        return new InlineFormattedIntegerInputRowEpoxyModel_().numberFormat(IntegerNumberFormatHelper.forCurrency(currency)).hint(currency.getSymbol());
    }

    public static InlineFormattedIntegerInputRowEpoxyModel_ forIntegerPercentage() {
        return new InlineFormattedIntegerInputRowEpoxyModel_().numberFormat(IntegerNumberFormatHelper.forIntegerPercentage()).hint(Character.toString(DecimalFormatSymbols.getInstance().getPercent()));
    }

    public void bind(InlineFormattedIntegerInputRow view) {
        super.bind(view);
        Context context = view.getContext();
        CharSequence actualTitle = this.titleRes != 0 ? context.getString(this.titleRes) : this.title;
        CharSequence actualSubTitle = this.subTitleRes != 0 ? context.getString(this.subTitleRes) : this.subTitle;
        CharSequence actualTip = this.tipRes != 0 ? context.getString(this.tipRes) : this.tip;
        CharSequence actualHint = this.hintRes != 0 ? context.getString(this.hintRes) : this.hint;
        view.setTitle(actualTitle);
        view.setSubTitleText(actualSubTitle);
        view.setHint(actualHint);
        view.setNumberFormat(this.numberFormat);
        view.setEnabled(this.enabled);
        view.setRemoveHintOnFocus(this.removeHintOnFocusMode);
        view.setTip(actualTip);
        view.setTipAmount(this.tipAmount);
        view.setOnTipClickListener(this.tipClickListener);
        view.showError(this.showError);
        view.setOnFocusChangeListener(this.focusChangeListener);
        view.setInputListener(null);
        view.setAmount(this.inputAmount);
        if (this.updateModelData) {
            view.setInputListener(this.listenerWrapper);
        } else {
            view.setInputListener(this.amountChangedListener);
        }
    }

    public void unbind(InlineFormattedIntegerInputRow view) {
        super.unbind(view);
        view.setInputListener(null);
        view.setOnFocusChangeListener(null);
    }

    public AirEpoxyModel<InlineFormattedIntegerInputRow> reset() {
        this.enabled = true;
        this.focusChangeListener = null;
        this.amountChangedListener = null;
        this.tipClickListener = null;
        return super.reset();
    }
}
