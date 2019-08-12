package com.airbnb.android.fixit;

import android.text.TextUtils;
import com.airbnb.android.core.models.FixItItem;

public final class FixItItemTextUtils {
    public static int getStatusTextRes(FixItItem item) {
        switch (item.getStatus()) {
            case 1:
                return C6380R.string.report_title_awaiting_review;
            case 2:
                return C6380R.string.report_title_completed;
            default:
                return 0;
        }
    }

    public static int getPhotoProofTitleTextRes(FixItItem item) {
        if (item.getProofs().isEmpty()) {
            return C6380R.string.fixit_report_item_photo_proof_row_title_required;
        }
        return C6380R.string.fixit_report_item_photo_proof_row_title_view;
    }

    public static int getPhotoProofSubtitleTextRes(FixItItem item) {
        if (item.getProofs().isEmpty()) {
            return C6380R.string.fixit_report_item_photo_proof_row_subtitle_required;
        }
        return 0;
    }

    public static int getPhotoProofActionTextRes(FixItItem item) {
        if (item.getProofs().isEmpty()) {
            return C6380R.string.fixit_report_item_photo_proof_row_action_upload;
        }
        return C6380R.string.fixit_report_item_photo_proof_row_action_view;
    }

    public static int getCommentActionTextRes(FixItItem item) {
        return TextUtils.isEmpty(item.getHostComment()) ? C6380R.string.comment_action_add : C6380R.string.comment_action_edit;
    }
}
