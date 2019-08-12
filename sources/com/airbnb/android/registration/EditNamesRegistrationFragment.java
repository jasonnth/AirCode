package com.airbnb.android.registration;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.registration.models.AccountRegistrationStep;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.TextUtil;

public class EditNamesRegistrationFragment extends BaseRegistrationFragment {
    @BindView
    SheetInputText editFirstName;
    @BindView
    SheetInputText editLastName;
    @BindView
    JellyfishView jellyfishView;
    @BindView
    SheetMarquee nameSheetMarquee;
    private final TextWatcher nameTextWathcer = TextWatcherUtils.create(EditNamesRegistrationFragment$$Lambda$1.lambdaFactory$(this));
    @BindView
    AirButton nextButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1562R.layout.fragment_names_registration, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.editFirstName.setText(this.dataPassedIn.firstName());
            this.editLastName.setText(this.dataPassedIn.lastName());
        }
        setupViews();
        return view;
    }

    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(this.editFirstName.getText())) {
            this.editFirstName.requestFocus();
        }
    }

    public void onDestroyView() {
        this.editFirstName.removeTextChangedListener(this.nameTextWathcer);
        this.editLastName.removeTextChangedListener(this.nameTextWathcer);
        super.onDestroyView();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RegistrationName;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void next() {
        KeyboardUtils.dismissSoftKeyboard(getView());
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.NEXT_BUTTON, this.dataPassedIn.getRegistrationServiceForAnalytics(), getNavigationTrackingTag());
        this.registrationController.onStepFinished(AccountRegistrationStep.Names, AccountRegistrationData.builder().firstName(this.editFirstName.getText().toString()).lastName(this.editLastName.getText().toString()).build());
    }

    private void setupViews() {
        this.editFirstName.addTextChangedListener(this.nameTextWathcer);
        this.editLastName.addTextChangedListener(this.nameTextWathcer);
        this.editLastName.setOnEditorActionListener(EditNamesRegistrationFragment$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ boolean lambda$setupViews$1(EditNamesRegistrationFragment editNamesRegistrationFragment, TextView v, int actionId, KeyEvent event) {
        if (editNamesRegistrationFragment.hasEnteredValidNames()) {
            editNamesRegistrationFragment.next();
        }
        return true;
    }

    private boolean hasEnteredValidNames() {
        return TextUtil.fieldNotEmptyWithTrimming(this.editFirstName) && TextUtil.fieldNotEmptyWithTrimming(this.editLastName);
    }
}
