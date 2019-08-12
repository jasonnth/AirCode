package com.airbnb.p027n2.components.bottom_sheet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.view.SupportMenuInflater;
import android.support.p002v7.view.menu.MenuBuilder;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import com.airbnb.n2.R;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.airbnb.n2.components.bottom_sheet.BottomSheetBuilder */
public class BottomSheetBuilder {
    private int backgroundColor;
    private int backgroundDrawable;
    private final Context context;
    private int itemBackground;
    private BottomSheetItemClickListener itemClickListener;
    private int itemTextColor;
    private Menu menu;
    private int menuRes;
    private int titleTextColor;

    public BottomSheetBuilder(Context context2, int menuRes2) {
        this.context = context2;
        this.menuRes = menuRes2;
    }

    public BottomSheetBuilder(Context context2, Menu menu2) {
        this.context = context2;
        this.menu = menu2;
    }

    public BottomSheetBuilder setItemClickListener(BottomSheetItemClickListener listener) {
        this.itemClickListener = listener;
        return this;
    }

    public BottomSheetDialog build() {
        BottomSheetDialog dialog = new BottomSheetDialog(this.context);
        dialog.setContentView(setupView());
        return dialog;
    }

    private View setupView() {
        List<BottomSheetItem> items = createMenuItems();
        View sheet = LayoutInflater.from(this.context).inflate(R.layout.n2_bottomsheetbuilder_sheet_list, null);
        RecyclerView recyclerView = (RecyclerView) ViewLibUtils.findById(sheet, R.id.recycler_view);
        if (this.backgroundDrawable != 0) {
            recyclerView.setBackgroundResource(this.backgroundDrawable);
        } else if (this.backgroundColor != 0) {
            recyclerView.setBackgroundColor(ContextCompat.getColor(this.context, this.backgroundColor));
        } else {
            recyclerView.setBackgroundColor(ContextCompat.getColor(this.context, R.color.n2_foggy_white));
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context));
        recyclerView.setAdapter(new BottomSheetItemAdapter(items, this.itemClickListener));
        return sheet;
    }

    @SuppressLint({"RestrictedApi"})
    private List<BottomSheetItem> createMenuItems() {
        List<BottomSheetItem> items = new ArrayList<>();
        if (this.menu == null) {
            this.menu = new MenuBuilder(this.context);
            new SupportMenuInflater(this.context).inflate(this.menuRes, this.menu);
        }
        for (int i = 0; i < this.menu.size(); i++) {
            MenuItem item = this.menu.getItem(i);
            if (item.hasSubMenu()) {
                CharSequence title = item.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    items.add(new BottomSheetHeader(title.toString(), this.titleTextColor));
                }
                SubMenu subMenu = item.getSubMenu();
                for (int j = 0; j < subMenu.size(); j++) {
                    items.add(new BottomSheetMenuItem(subMenu.getItem(j), this.itemTextColor, this.itemBackground));
                }
            } else {
                items.add(new BottomSheetMenuItem(item, this.itemTextColor, this.itemBackground));
            }
        }
        return items;
    }
}
