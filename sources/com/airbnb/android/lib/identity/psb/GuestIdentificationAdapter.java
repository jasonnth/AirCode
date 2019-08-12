package com.airbnb.android.lib.identity.psb;

import android.content.Context;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.KickerMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.StandardRow;
import com.google.common.collect.FluentIterable;
import java.util.Collection;
import java.util.List;

public class GuestIdentificationAdapter extends AirEpoxyAdapter {
    AirbnbAccountManager accountManager;
    private final Context context;
    private final KickerMarqueeEpoxyModel_ headerModel;
    private final Callbacks identityClickListener;
    private final int totalGuestCount;

    interface Callbacks {
        void onAddBookerIdentificationClick();

        void onAddIdentityClick();

        void onIdentityClick(GuestIdentity guestIdentity, boolean z);
    }

    GuestIdentificationAdapter(List<GuestIdentity> guestIdentifications, int totalGuestCount2, Callbacks identityClickListener2, Context context2, String p4Steps) {
        super(true);
        ((AirbnbGraph) AirbnbApplication.instance(context2).component()).inject(this);
        this.context = context2;
        this.totalGuestCount = totalGuestCount2;
        this.identityClickListener = identityClickListener2;
        this.headerModel = new KickerMarqueeEpoxyModel_().titleRes(C0880R.string.p4_china_guest_identification_title).subTitleRes(ChinaUtils.isUserCountrySetToChina(this.accountManager.getCurrentUser()) ? C0880R.string.guest_profiles_caption_cn : C0880R.string.guest_profiles_caption_non_cn).kickerText(p4Steps).isSubtitleBold(false);
        setIdentifications(guestIdentifications);
    }

    /* access modifiers changed from: 0000 */
    public void setIdentifications(List<GuestIdentity> guestIdentifications) {
        this.models.clear();
        this.models.add(this.headerModel);
        GuestIdentity bookerIdentification = (GuestIdentity) FluentIterable.from((Iterable<E>) guestIdentifications).filter(GuestIdentificationAdapter$$Lambda$1.lambdaFactory$()).first().orNull();
        if (bookerIdentification == null) {
            this.models.add(new StandardRowEpoxyModel_().actionText(C0880R.string.p4_add).title((CharSequence) this.context.getString(C0880R.string.p4_china_guest_identification_subtitle)).clickListener(GuestIdentificationAdapter$$Lambda$2.lambdaFactory$(this)));
            notifyDataSetChanged();
            if (ListUtils.isEmpty((Collection<?>) guestIdentifications)) {
                return;
            }
        } else {
            this.models.add(buildIdentificationEpoxyModel(bookerIdentification, this.context.getString(C0880R.string.your_information), getIdentificationInfoString(bookerIdentification, this.context), true));
        }
        int i = 2;
        for (GuestIdentity identification : guestIdentifications) {
            if (!identification.isBooker()) {
                int i2 = i + 1;
                this.models.add(buildIdentificationEpoxyModel(identification, this.context.getString(C0880R.string.guest_x, new Object[]{Integer.valueOf(i)}), getIdentificationInfoString(identification, this.context), false));
                i = i2;
            }
        }
        if (guestIdentifications.size() < this.totalGuestCount) {
            this.models.add(new StandardRowEpoxyModel_().actionText(C0880R.string.p4_add).title((CharSequence) this.context.getString(C0880R.string.guest_x, new Object[]{Integer.valueOf(i)})).clickListener(GuestIdentificationAdapter$$Lambda$3.lambdaFactory$(this)));
        }
        notifyDataSetChanged();
    }

    static /* synthetic */ boolean lambda$setIdentifications$0(GuestIdentity input) {
        return input != null && input.isBooker();
    }

    private String getIdentificationInfoString(GuestIdentity identification, Context context2) {
        int identificationInfoResId;
        String fullName = context2.getString(C0880R.string.guest_identity_full_name, new Object[]{identification.getGivenNames(), identification.getSurname()});
        switch (identification.getType()) {
            case Passport:
                identificationInfoResId = C0880R.string.identification_info_passport;
                break;
            case ChineseNationalID:
                identificationInfoResId = C0880R.string.identification_info_national_id;
                break;
            default:
                throw new IllegalArgumentException("unknown ID type: " + identification.getType());
        }
        String idNumberString = context2.getString(identificationInfoResId, new Object[]{identification.getIdentityString()});
        return context2.getString(C0880R.string.bullet_with_space_parameterized, new Object[]{fullName, idNumberString});
    }

    private EpoxyModel<StandardRow> buildIdentificationEpoxyModel(GuestIdentity identification, String title, String subtitle, boolean isBooker) {
        return new StandardRowEpoxyModel_().title((CharSequence) title).subtitle((CharSequence) subtitle).actionText(C0880R.string.remove_guest_identification).clickListener(GuestIdentificationAdapter$$Lambda$4.lambdaFactory$(this, identification, isBooker)).m5602id((long) identification.getId());
    }
}
