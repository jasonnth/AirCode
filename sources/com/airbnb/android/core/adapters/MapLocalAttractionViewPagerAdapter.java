package com.airbnb.android.core.adapters;

import android.support.p002v7.content.res.AppCompatResources;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.beta.models.guidebook.LocalAttraction;
import com.airbnb.android.core.beta.models.guidebook.LocalAttraction.GuidebookPin;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import java.util.List;

public class MapLocalAttractionViewPagerAdapter extends LoopingPagerAdapter<LocalAttraction> {
    @BindView
    AirTextView mLocalAttractionDescription;
    @BindView
    AirTextView mLocalAttractionName;
    @BindView
    AirImageView mLocalAttractionThumbnail;
    private final List<LocalAttraction> mLocalAttractions;

    public MapLocalAttractionViewPagerAdapter(List<LocalAttraction> localAttractions) {
        super(localAttractions);
        this.mLocalAttractions = localAttractions;
    }

    /* access modifiers changed from: protected */
    public View createView(ViewGroup container, int position) {
        String descriptionCaption;
        View view = LayoutInflater.from(container.getContext()).inflate(C0716R.layout.list_item_local_attraction, container, false);
        ButterKnife.bind(this, view);
        LocalAttraction localAttraction = (LocalAttraction) this.mLocalAttractions.get(getAdjustedPosition(position));
        this.mLocalAttractionThumbnail.setImageDrawable(AppCompatResources.getDrawable(container.getContext(), GuidebookPin.getGuidebookPin(localAttraction.getPin()).getResourceThumbnail()));
        if (!TextUtils.isEmpty(localAttraction.getPhoto())) {
            this.mLocalAttractionThumbnail.setImageUrl(localAttraction.getPhoto());
        }
        this.mLocalAttractionName.setText(localAttraction.getName());
        int numberDollarSigns = localAttraction.getPrice();
        if (numberDollarSigns > 0) {
            String symbolicPrice = CoreApplication.instance(view.getContext()).component().currencyHelper().getSymbolicPrice(numberDollarSigns);
            descriptionCaption = view.getResources().getString(C0716R.string.guidebook_pager_caption, new Object[]{localAttraction.getCategory(), symbolicPrice});
        } else {
            descriptionCaption = localAttraction.getCategory();
        }
        if (!TextUtils.isEmpty(descriptionCaption)) {
            this.mLocalAttractionDescription.setText(descriptionCaption);
            this.mLocalAttractionDescription.setVisibility(0);
        } else {
            this.mLocalAttractionDescription.setVisibility(8);
        }
        return view;
    }

    public int getLocalAttractionPosition(long attractionResourceId) {
        if (this.mLocalAttractions != null) {
            for (int i = 0; i < this.mLocalAttractions.size(); i++) {
                if (attractionResourceId == ((LocalAttraction) this.mLocalAttractions.get(i)).getResourceId()) {
                    return i;
                }
            }
        }
        return -1;
    }
}
