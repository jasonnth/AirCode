package com.airbnb.android.fixit;

import android.content.Context;
import com.airbnb.android.core.models.FixItItem;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.viewcomponents.models.BasicRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ImpactDisplayCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.components.InfoActionRowModel_;
import com.airbnb.p027n2.components.LabelDocumentMarqueeModel_;
import com.airbnb.p027n2.epoxy.TypedAirEpoxyController;
import com.google.common.collect.FluentIterable;

public class FixItItemController extends TypedAirEpoxyController<FixItItem> {
    InfoActionRowModel_ commentActionRow;
    BasicRowEpoxyModel_ commentBasicRow;
    private final Context context;
    SimpleTextRowEpoxyModel_ descriptionRow;
    LabelDocumentMarqueeModel_ itemDetail;
    private final Listener listener;
    InfoActionRowModel_ photoProofActionRow;
    BasicRowEpoxyModel_ photoProofBasicRow;
    ImpactDisplayCardEpoxyModel_ photosRow;

    public interface Listener {
        void onCommentItemSelected(FixItItem fixItItem);

        void onPhotoProofsItemSelected();
    }

    public FixItItemController(Context context2, Listener listener2) {
        this.context = context2;
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public void buildModels(FixItItem item) {
        buildItemDetailRow(item);
        buildItemDescriptionRow(item);
        buildIssuePicturesRow(item);
        buildPhotoProofRow(item);
        buildCommentRow(item);
    }

    private void buildItemDetailRow(FixItItem item) {
        this.itemDetail.titleText(item.getCategory()).captionText(item.getName()).labelText(FixItItemTextUtils.getStatusTextRes(item));
    }

    private void buildItemDescriptionRow(FixItItem item) {
        this.descriptionRow.text(item.getDescription()).smallPadding().showDivider(!shouldShowIssuePicturesRow(item));
    }

    private void buildIssuePicturesRow(FixItItem item) {
        Photo coverPhoto = (Photo) FluentIterable.from((Iterable<E>) item.getIssuePictures()).first().orNull();
        this.photosRow.imageUrl(coverPhoto != null ? coverPhoto.getXLargeUrl() : null).title(this.context.getResources().getQuantityString(C6380R.plurals.photos, item.getIssuePictures().size(), new Object[]{Integer.valueOf(item.getIssuePictures().size())})).addIf(shouldShowIssuePicturesRow(item), (EpoxyController) this);
    }

    private void buildPhotoProofRow(FixItItem item) {
        boolean z = true;
        if (item.isReadOnly()) {
            BasicRowEpoxyModel_ subtitleRes = this.photoProofBasicRow.titleRes(FixItItemTextUtils.getPhotoProofTitleTextRes(item)).subtitleRes(FixItItemTextUtils.getPhotoProofSubtitleTextRes(item));
            if (item.getProofRequired() != 1) {
                z = false;
            }
            subtitleRes.addIf(z, (EpoxyController) this);
            return;
        }
        InfoActionRowModel_ onClickListener = this.photoProofActionRow.title(FixItItemTextUtils.getPhotoProofTitleTextRes(item)).subtitleText(FixItItemTextUtils.getPhotoProofSubtitleTextRes(item)).info(FixItItemTextUtils.getPhotoProofActionTextRes(item)).onClickListener(FixItItemController$$Lambda$1.lambdaFactory$(this));
        if (item.getProofRequired() != 1) {
            z = false;
        }
        onClickListener.addIf(z, (EpoxyController) this);
    }

    private void buildCommentRow(FixItItem item) {
        if (item.isReadOnly()) {
            this.commentBasicRow.titleRes(C6380R.string.comment_title).subtitleText(item.getHostComment());
        } else {
            this.commentActionRow.title(C6380R.string.comment_title).subtitleText((CharSequence) item.getHostComment()).info(FixItItemTextUtils.getCommentActionTextRes(item)).onClickListener(FixItItemController$$Lambda$2.lambdaFactory$(this, item));
        }
    }

    private boolean shouldShowIssuePicturesRow(FixItItem item) {
        return item.getIssuePictures().size() > 0;
    }
}
