package com.airbnb.p027n2.components;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.n2.R;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.primitives.TagWithImageAndText;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.android.flexbox.FlexboxLayout;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.airbnb.n2.components.TagsCollectionRow */
public class TagsCollectionRow extends LinearLayout implements DividerView {
    @BindView
    FlexboxLayout container;
    @BindView
    View divider;
    @BindView
    SectionHeader sectionHeader;

    /* renamed from: com.airbnb.n2.components.TagsCollectionRow$TagRowItem */
    public static class TagRowItem {
        public final int drawableRes;
        public final String label;

        public TagRowItem(String label2, int drawableRes2) {
            this.label = label2;
            this.drawableRes = drawableRes2;
        }

        public TagRowItem(String label2) {
            this.label = label2;
            this.drawableRes = 0;
        }

        public int hashCode() {
            return this.label.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            return TextUtils.equals(((TagRowItem) obj).label, this.label);
        }
    }

    public TagsCollectionRow(Context context) {
        super(context);
        init();
    }

    public TagsCollectionRow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TagsCollectionRow(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.n2_tags_collection_row, this);
        setOrientation(1);
        ButterKnife.bind((View) this);
    }

    public void setTags(ArrayList<TagRowItem> tags) {
        this.container.removeAllViews();
        Iterator it = tags.iterator();
        while (it.hasNext()) {
            TagRowItem tag = (TagRowItem) it.next();
            addNewTag(tag.label, tag.drawableRes);
        }
    }

    public void setTitle(CharSequence title) {
        ViewLibUtils.setVisibleIf(this.sectionHeader, !TextUtils.isEmpty(title));
        this.sectionHeader.setTitle(title);
    }

    private void addNewTag(String label, int drawableRes) {
        TagWithImageAndText tag = new TagWithImageAndText(getContext());
        tag.setLabel((CharSequence) label);
        if (drawableRes != 0) {
            tag.setImage(drawableRes);
        }
        this.container.addView(tag);
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }

    public static void mockOneTag(TagsCollectionRow view) {
        ArrayList<TagRowItem> tagRow = new ArrayList<>();
        tagRow.add(new TagRowItem("Cat", R.drawable.n2_ic_am_cat));
        view.setTags(tagRow);
        view.setTitle("A list of amazing tags");
        view.showDivider(true);
    }

    public static void mockTwoTags(TagsCollectionRow view) {
        ArrayList<TagRowItem> tagRow = new ArrayList<>();
        tagRow.add(new TagRowItem("Cat", R.drawable.n2_ic_am_cat));
        tagRow.add(new TagRowItem("Elevator", R.drawable.n2_ic_am_elevator));
        view.setTags(tagRow);
    }

    public static void mockListOfTags(TagsCollectionRow view) {
        ArrayList<TagRowItem> tagRow = new ArrayList<>();
        tagRow.add(new TagRowItem("Entire Home", R.drawable.n2_ic_entire_home));
        tagRow.add(new TagRowItem("Elevator", R.drawable.n2_ic_am_elevator));
        tagRow.add(new TagRowItem("Shared Room", R.drawable.n2_ic_shared_room));
        tagRow.add(new TagRowItem("Private", R.drawable.n2_ic_private_room));
        tagRow.add(new TagRowItem("Location", R.drawable.n2_ic_dates));
        tagRow.add(new TagRowItem("Dates", R.drawable.n2_ic_location));
        view.setTags(tagRow);
        view.setTitle("A list of amazing tags");
    }

    public static void mockListOfTagsWithoutImage(TagsCollectionRow view) {
        ArrayList<TagRowItem> tagRow = new ArrayList<>();
        tagRow.add(new TagRowItem("Cat"));
        tagRow.add(new TagRowItem("Elevator"));
        tagRow.add(new TagRowItem("Bathtub"));
        tagRow.add(new TagRowItem("Cable TV"));
        tagRow.add(new TagRowItem("Breakfast"));
        tagRow.add(new TagRowItem("Books & Toys"));
        view.setTags(tagRow);
        view.setTitle("A list of amazing tags");
    }

    public static void mockListOfTagsWithLongText(TagsCollectionRow view) {
        ArrayList<TagRowItem> tagRow = new ArrayList<>();
        tagRow.add(new TagRowItem("This is the best home in the history of best homes", R.drawable.n2_ic_entire_home));
        tagRow.add(new TagRowItem("This Elevator Is the best elevator out there. It has to be longer than this", R.drawable.n2_ic_am_elevator));
        tagRow.add(new TagRowItem("Shared Room", R.drawable.n2_ic_shared_room));
        tagRow.add(new TagRowItem("Private Room", R.drawable.n2_ic_private_room));
        view.setTags(tagRow);
        view.showDivider(true);
    }
}
