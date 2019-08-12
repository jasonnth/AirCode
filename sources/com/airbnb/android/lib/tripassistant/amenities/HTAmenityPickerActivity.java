package com.airbnb.android.lib.tripassistant.amenities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.models.HelpThreadAmenity;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import java.util.ArrayList;
import java.util.Collection;

public class HTAmenityPickerActivity extends AirActivity implements SelectionSheetOnItemClickedListener<HelpThreadAmenitySelectionViewItem> {
    private static final String EXTRA_AMENITIES = "extra_amenities";
    public static final String EXTRA_SELECTED_AMENITIES = "extra_selected_amenities";
    @BindView
    AirButton saveButton;
    @BindView
    HTAmenitiesSelectionView selectionView;
    @BindView
    AirToolbar toolbar;

    public static Intent newInstance(Context context, Collection<HelpThreadAmenity> amenities) {
        return new Intent(context, HTAmenityPickerActivity.class).putExtra(EXTRA_AMENITIES, new ArrayList(amenities));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.fragment_help_thread_amenity_picker);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        this.selectionView.setAmenities(getIntent().getParcelableArrayListExtra(EXTRA_AMENITIES));
        this.selectionView.setSelectionSheetOnItemClickedListener(this);
        overridePendingTransition(C0880R.anim.enter_bottom, C0880R.anim.stay);
    }

    public void finish() {
        super.finish();
        overridePendingTransition(C0880R.anim.stay, C0880R.anim.exit_bottom);
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }

    public void onResume() {
        super.onResume();
        updatePrimaryButtonState();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onPrimaryButtonClicked() {
        if (this.selectionView.hasSelectedItems()) {
            setResult(-1, new Intent().putParcelableArrayListExtra(EXTRA_SELECTED_AMENITIES, new ArrayList<>(this.selectionView.getSelectedAmenities())));
        } else {
            setResult(0);
        }
        finish();
    }

    public void onItemClicked(HelpThreadAmenitySelectionViewItem item) {
        updatePrimaryButtonState();
    }

    private void updatePrimaryButtonState() {
        this.saveButton.setText(this.selectionView.hasSelectedItems() ? C0880R.string.continue_text : C0880R.string.cancel);
    }
}
