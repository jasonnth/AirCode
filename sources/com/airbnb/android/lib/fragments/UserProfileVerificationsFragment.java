package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.VerifiedIdActivityIntents;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class UserProfileVerificationsFragment extends AirFragment {
    private static final String ARG_USER = "user";
    @BindView
    RecyclerView recyclerView;
    @BindView
    PrimaryButton startVerificationsButton;
    @BindView
    AirToolbar toolbar;

    private static final class IdentificationsAdapter extends AirEpoxyAdapter {
        IdentificationsAdapter() {
            this.models.add(new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.section_header_identifications).linkRes(C0880R.string.learn_more).linkClickListener(C6912x235b5078.lambdaFactory$()));
            int rowDrawable = C0880R.C0881drawable.n2_ic_check_babu;
            this.models.add(new StandardRowEpoxyModel_().title(C0880R.string.ro_verification_government_id).rowDrawableRes(rowDrawable));
            this.models.add(new StandardRowEpoxyModel_().title(C0880R.string.ro_verification_selfie).rowDrawableRes(rowDrawable));
            this.models.add(new StandardRowEpoxyModel_().title(C0880R.string.ro_verification_birthdate).rowDrawableRes(rowDrawable));
            this.models.add(new StandardRowEpoxyModel_().title(C0880R.string.ro_verification_email).rowDrawableRes(rowDrawable));
            this.models.add(new StandardRowEpoxyModel_().title(C0880R.string.ro_verification_phone_number).rowDrawableRes(rowDrawable));
        }
    }

    private static final class VerificationsAdapter extends AirEpoxyAdapter {
        private static final String VERIFICATION_EMAIL = "email";
        private static final String VERIFICATION_FACEBOOK = "facebook";
        private static final String VERIFICATION_GOOGLE = "google";
        private static final String VERIFICATION_LINKEDIN = "linkedin";
        private static final String VERIFICATION_OFFLINE_JUMIO = "jumio";
        private static final String VERIFICATION_OFFLINE_KBA = "kba";
        private static final String VERIFICATION_OFFLINE_SESAME = "sesame";
        private static final String VERIFICATION_PHONE = "phone";
        private static final String VERIFICATION_REVIEWS = "reviews";

        VerificationsAdapter(List<String> verifications, List<String> verificationLabels) {
            this.models.add(new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.section_header_verified_id));
            if (ListUtils.isEmpty(verifications, verificationLabels)) {
                this.models.add(new StandardRowEpoxyModel_().title(C0880R.string.none));
                return;
            }
            List<String> verifications2 = FluentIterable.from((Iterable<E>) verifications).filter(UserProfileVerificationsFragment$VerificationsAdapter$$Lambda$1.lambdaFactory$()).toList();
            List<String> verificationLabels2 = FluentIterable.from((Iterable<E>) verificationLabels).filter(UserProfileVerificationsFragment$VerificationsAdapter$$Lambda$2.lambdaFactory$()).toList();
            if (verifications2.size() != verificationLabels2.size()) {
                BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Verifications and Verification Labels are not the same size. " + verifications2.size() + " vs " + verificationLabels2.size()));
                return;
            }
            for (int i = 0; i < verifications2.size(); i++) {
                String label = (String) verificationLabels2.get(i);
                this.models.add(new StandardRowEpoxyModel_().title((CharSequence) label).rowDrawableRes(getNonManuallyVerifiedDrawable((String) verifications2.get(i))));
            }
        }

        static /* synthetic */ boolean lambda$new$0(String input) {
            return input != null;
        }

        static /* synthetic */ boolean lambda$new$1(String input) {
            return input != null;
        }

        private int getNonManuallyVerifiedDrawable(String verification) {
            char c = 65535;
            switch (verification.hashCode()) {
                case 96619420:
                    if (verification.equals("email")) {
                        c = 0;
                        break;
                    }
                    break;
                case 101486888:
                    if (verification.equals(VERIFICATION_OFFLINE_JUMIO)) {
                        c = 2;
                        break;
                    }
                    break;
                case 106642798:
                    if (verification.equals("phone")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1099953179:
                    if (verification.equals("reviews")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    return C0880R.C0881drawable.ic_verifications_email;
                case 1:
                    return C0880R.C0881drawable.ic_verifications_reviewed;
                case 2:
                    return C0880R.C0881drawable.ic_verifications_offline_id;
                case 3:
                    return C0880R.C0881drawable.ic_verifications_phone;
                default:
                    return C0880R.C0881drawable.n2_ic_check_hof;
            }
        }
    }

    public static UserProfileVerificationsFragment newInstance(User user) {
        return (UserProfileVerificationsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new UserProfileVerificationsFragment()).putParcelable("user", user)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Adapter<?> adapter;
        boolean showVerifications = false;
        View view = inflater.inflate(C0880R.layout.fragment_profile_verifications_fragment, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        User user = (User) getArguments().getParcelable("user");
        Check.notNull(user);
        if (user.isManuallyVerified()) {
            adapter = new IdentificationsAdapter<>();
        } else {
            adapter = new VerificationsAdapter<>(user.getVerifications(), user.getVerificationLabels());
        }
        this.recyclerView.setAdapter(adapter);
        if (user.equals(this.mAccountManager.getCurrentUser()) && user.isManuallyVerified()) {
            showVerifications = true;
        }
        ViewLibUtils.setVisibleIf(this.startVerificationsButton, showVerifications);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onStartVerificationClick() {
        startActivity(VerifiedIdActivityIntents.intentForVerifiedId(getContext()));
    }
}
