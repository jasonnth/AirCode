package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VerificationsAdapter extends BaseAdapter {
    private static final List<String> DISPLAYED_VERIFICATIONS = Arrays.asList(new String[]{"google", "phone", "facebook", VERIFICATION_REVIEWS, VERIFICATION_LINKEDIN, "email", VERIFICATION_OFFLINE_JUMIO, VERIFICATION_OFFLINE_KBA, VERIFICATION_OFFLINE_SESAME});
    private static final String VERIFICATION_EMAIL = "email";
    public static final String VERIFICATION_FACEBOOK = "facebook";
    public static final String VERIFICATION_GOOGLE = "google";
    public static final String VERIFICATION_LINKEDIN = "linkedin";
    private static final String VERIFICATION_OFFLINE_JUMIO = "jumio";
    private static final String VERIFICATION_OFFLINE_KBA = "kba";
    private static final String VERIFICATION_OFFLINE_SESAME = "sesame";
    private static final String VERIFICATION_PHONE = "phone";
    public static final String VERIFICATION_REVIEWS = "reviews";
    private static String mPassportString;
    private final LayoutInflater mInflater;
    private final List<String> mVerificationLabels = new ArrayList();
    private final List<String> mVerifications = new ArrayList();

    private static class ViewHolder {
        ImageView iconImageView;
        TextView verificationTextView;

        private ViewHolder() {
        }
    }

    public VerificationsAdapter(Context context, User user) {
        this.mInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (user.hasVerifications()) {
            List<String> verifications = user.getVerifications();
            List<String> verificationLabels = user.getVerificationLabels();
            int length = Math.min(verifications.size(), verificationLabels.size());
            for (int i = 0; i < length; i++) {
                if (DISPLAYED_VERIFICATIONS.contains(verifications.get(i))) {
                    this.mVerifications.add(verifications.get(i));
                    this.mVerificationLabels.add(verificationLabels.get(i));
                }
            }
        } else if (BuildHelper.isDebugFeaturesEnabled()) {
            throw new IllegalStateException("user does not have verifications");
        }
    }

    public int getCount() {
        return this.mVerifications.size();
    }

    public String getItem(int position) {
        return (String) this.mVerificationLabels.get(position);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = this.mInflater.inflate(C0880R.layout.list_item_verification, parent, false);
            convertView.setTag(createViewHolder(convertView));
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        String verification = (String) this.mVerifications.get(position);
        String verificationLabel = (String) this.mVerificationLabels.get(position);
        viewHolder.verificationTextView.setText(verificationLabel);
        int iconId = C0880R.C0881drawable.icon_verified_id_email;
        if ("phone".equals(verification)) {
            iconId = C0880R.C0881drawable.icon_verified_id_phone;
        } else if ("facebook".equals(verification)) {
            iconId = C0880R.C0881drawable.icon_verified_id_facebook;
        } else if ("google".equals(verification)) {
            iconId = C0880R.C0881drawable.icon_verified_id_google_plus;
        } else if (VERIFICATION_REVIEWS.equals(verification)) {
            iconId = C0880R.C0881drawable.icon_verified_id_online;
        } else if (VERIFICATION_LINKEDIN.equals(verification)) {
            iconId = C0880R.C0881drawable.icon_verified_id_linked_in;
        } else if ("email".equals(verification)) {
            iconId = C0880R.C0881drawable.icon_verified_id_email;
        } else if (VERIFICATION_OFFLINE_JUMIO.equals(verification)) {
            iconId = isPassportLabel(parent.getContext(), verificationLabel) ? C0880R.C0881drawable.icon_verified_id_offline_passport : C0880R.C0881drawable.icon_verified_id_offline_non_passport;
        } else if (VERIFICATION_OFFLINE_KBA.equals(verification)) {
            iconId = C0880R.C0881drawable.icon_verified_id_offline_non_passport;
        } else if (VERIFICATION_OFFLINE_SESAME.equals(verification)) {
            iconId = C0880R.C0881drawable.icon_verified_id_sesame;
        }
        viewHolder.iconImageView.setImageDrawable(ColorizedDrawable.forIdWithColor(parent.getContext(), iconId, C0880R.color.c_foggy));
        return convertView;
    }

    private boolean isPassportLabel(Context context, String verificationLabel) {
        if (TextUtils.isEmpty(mPassportString)) {
            mPassportString = context.getResources().getString(C0880R.string.verified_id_offline_passport);
        }
        return mPassportString.equals(verificationLabel);
    }

    private ViewHolder createViewHolder(View convertView) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.verificationTextView = (TextView) convertView.findViewById(C0880R.C0882id.verification_title);
        viewHolder.iconImageView = (ImageView) convertView.findViewById(C0880R.C0882id.verification_icon);
        return viewHolder;
    }
}
