package com.airbnb.android.core.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.p002v7.view.menu.MenuBuilder;
import android.view.Menu;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetBuilder;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetItemClickListener;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetMenuItem;
import com.google.common.collect.FluentIterable;
import java.util.Arrays;
import java.util.List;
import p032rx.functions.Func1;

public class OptionsMenuFactory<T> {
    private final Context context;
    private final List<T> items;
    private Listener<T> listener;
    private Func1<T, String> titleTransformer;

    private static class Item<T> {
        public final CharSequence text;
        public final T value;

        public Item(CharSequence text2, T value2) {
            this.text = text2;
            this.value = value2;
        }
    }

    public interface Listener<T> {
        void itemSelected(T t);
    }

    private static class MenuClosingBottomSheetItemListener implements BottomSheetItemClickListener {
        private BottomSheetDialog dialog;
        private final BottomSheetItemClickListener wrappedListener;

        MenuClosingBottomSheetItemListener(BottomSheetItemClickListener wrappedListener2) {
            this.wrappedListener = wrappedListener2;
        }

        public void setDialog(BottomSheetDialog dialog2) {
            this.dialog = dialog2;
        }

        public void onBottomSheetItemClick(BottomSheetMenuItem item) {
            Check.notNull(this.dialog);
            this.wrappedListener.onBottomSheetItemClick(item);
            this.dialog.dismiss();
        }
    }

    private OptionsMenuFactory(Context context2, List<T> items2) {
        this.context = context2;
        this.items = items2;
    }

    public static <T> OptionsMenuFactory<T> forItems(Context context2, List<T> items2) {
        return new OptionsMenuFactory<>(context2, items2);
    }

    public static <T> OptionsMenuFactory<T> forItems(Context context2, T[] items2) {
        return new OptionsMenuFactory<>(context2, Arrays.asList(items2));
    }

    public static OptionsMenuFactory<Integer> forIntRange(Context context2, int start, int end) {
        return new OptionsMenuFactory(context2, Arrays.asList(ListUtils.range(start, end))).setTitleTransformer(OptionsMenuFactory$$Lambda$1.lambdaFactory$());
    }

    public OptionsMenuFactory<T> setTitleTransformer(Func1<T, String> titleTransformer2) {
        this.titleTransformer = titleTransformer2;
        return this;
    }

    public OptionsMenuFactory<T> setTitleResTransformer(Func1<T, Integer> titleResTransformer) {
        return setTitleTransformer(OptionsMenuFactory$$Lambda$2.lambdaFactory$(this, titleResTransformer));
    }

    public OptionsMenuFactory<T> setListener(Listener<T> listener2) {
        this.listener = listener2;
        return this;
    }

    public void buildAndShow() {
        Check.notNull(this.items);
        Check.notNull(this.listener);
        Check.notNull(this.titleTransformer);
        showMenu(this.context, FluentIterable.from((Iterable<E>) this.items).transform(OptionsMenuFactory$$Lambda$3.lambdaFactory$(this)).toList(), this.listener);
    }

    static /* synthetic */ Item lambda$buildAndShow$2(OptionsMenuFactory optionsMenuFactory, Object item) {
        return new Item((CharSequence) optionsMenuFactory.titleTransformer.call(item), item);
    }

    @SuppressLint({"RestrictedApi"})
    public static <T> void showMenu(Context context2, List<Item<T>> options, Listener<T> listener2) {
        MenuBuilder menu = new MenuBuilder(context2);
        for (int i = 0; i < options.size(); i++) {
            menu.add(0, i, i, ((Item) options.get(i)).text);
        }
        MenuClosingBottomSheetItemListener wrappedListener = new MenuClosingBottomSheetItemListener(OptionsMenuFactory$$Lambda$4.lambdaFactory$(options, listener2));
        BottomSheetDialog dialog = new BottomSheetBuilder(context2, (Menu) menu).setItemClickListener(wrappedListener).build();
        wrappedListener.setDialog(dialog);
        dialog.show();
    }
}
