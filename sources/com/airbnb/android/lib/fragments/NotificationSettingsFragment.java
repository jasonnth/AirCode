package com.airbnb.android.lib.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.NotificationPreference;
import com.airbnb.android.core.notifications.NotificationPreferencesGroups;
import com.airbnb.android.core.requests.UpdateNotificationPreferencesRequest;
import com.airbnb.android.core.requests.notifications.NotificationPreferencesRequest;
import com.airbnb.android.core.responses.NotificationPreferencesResponse;
import com.airbnb.android.core.superhero.SuperHeroUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.interfaces.SwitchRowInterface.OnCheckedChangeListener;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;
import p032rx.Observer;

public class NotificationSettingsFragment extends AirFragment {
    @State
    ArrayList<NotificationPreference> notificationPreferences;
    final RequestListener<NotificationPreferencesResponse> preferencesRequestListener = new C0699RL().onResponse(NotificationSettingsFragment$$Lambda$1.lambdaFactory$(this)).onError(NotificationSettingsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    LinearLayout pushSettingsLayout;
    @BindView
    LinearLayout smsSettingsLayout;
    @BindView
    AirToolbar toolbar;
    final RequestListener<NotificationPreferencesResponse> updatePreferenceListener = new C0699RL().onResponse(NotificationSettingsFragment$$Lambda$3.lambdaFactory$(this)).onError(NotificationSettingsFragment$$Lambda$4.lambdaFactory$(this)).build();

    public static NotificationSettingsFragment newInstance() {
        return new NotificationSettingsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_notification_settings, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null || this.notificationPreferences == null) {
            setupPushNotifications();
        } else {
            displayPreferencesAndSetCheckListeners();
        }
        return view;
    }

    public void onDestroyView() {
        showLoader(false);
        super.onDestroyView();
    }

    private void setupPushNotifications() {
        showLoader(true);
        new NotificationPreferencesRequest(this.preferencesRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$1(NotificationSettingsFragment notificationSettingsFragment, NotificationPreferencesResponse response) {
        notificationSettingsFragment.showLoader(false);
        notificationSettingsFragment.notificationPreferences = new ArrayList<>(FluentIterable.from((Iterable<E>) response.notificationPreferences).filter(NotificationSettingsFragment$$Lambda$7.lambdaFactory$()).toList());
        notificationSettingsFragment.displayPreferencesAndSetCheckListeners();
    }

    static /* synthetic */ boolean lambda$null$0(NotificationPreference input) {
        return !NotificationPreferencesGroups.ACCOUNTS.equals(input.getGroup());
    }

    static /* synthetic */ void lambda$new$2(NotificationSettingsFragment notificationSettingsFragment, AirRequestNetworkException e) {
        notificationSettingsFragment.showLoader(false);
        NetworkUtil.toastGenericNetworkError(notificationSettingsFragment.getActivity());
    }

    private void setStrings(String group, SwitchRow groupedCheck) {
        char c = 65535;
        switch (group.hashCode()) {
            case -2137146394:
                if (group.equals(NotificationPreferencesGroups.ACCOUNTS)) {
                    c = 2;
                    break;
                }
                break;
            case -1210894809:
                if (group.equals(NotificationPreferencesGroups.RESERVATIONS)) {
                    c = 1;
                    break;
                }
                break;
            case -933770714:
                if (group.equals(NotificationPreferencesGroups.MARKETING)) {
                    c = 3;
                    break;
                }
                break;
            case -462094004:
                if (group.equals("messages")) {
                    c = 0;
                    break;
                }
                break;
            case -332264971:
                if (group.equals(NotificationPreferencesGroups.SUPERHERO)) {
                    c = 5;
                    break;
                }
                break;
            case 106069776:
                if (group.equals("other")) {
                    c = 4;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                groupedCheck.setTitle(C0880R.string.notifications_messages);
                groupedCheck.setDescription(C0880R.string.notifications_messages_subtitle);
                return;
            case 1:
                groupedCheck.setTitle(C0880R.string.notifications_reservation_updates);
                groupedCheck.setDescription(C0880R.string.notifications_reservation_updates_subtitle);
                return;
            case 2:
                groupedCheck.setTitle(C0880R.string.notifications_account_activity);
                groupedCheck.setDescription(C0880R.string.notifications_account_activity_subtitle);
                return;
            case 3:
                groupedCheck.setTitle(C0880R.string.notifications_travel_recommendations);
                groupedCheck.setDescription(C0880R.string.notifications_travel_recommendations_subtitle);
                return;
            case 4:
                groupedCheck.setTitle(C0880R.string.notifications_other);
                groupedCheck.setDescription(C0880R.string.notifications_other_subtitle);
                return;
            case 5:
                groupedCheck.setTitle(C0880R.string.notifications_superhero);
                groupedCheck.setDescription(C0880R.string.notifications_superhero_subtitle);
                return;
            default:
                AirbnbEventLogger.track("notification_settings", Strap.make().mo11639kv("unknown_group", group));
                return;
        }
    }

    private void displayPreferencesAndSetCheckListeners() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        int layout = C0880R.layout.n2_view_holder_switch_row;
        Iterator it = this.notificationPreferences.iterator();
        while (it.hasNext()) {
            NotificationPreference preference = (NotificationPreference) it.next();
            if (preference.isAppliesToPush()) {
                SwitchRow groupedCheck = (SwitchRow) inflater.inflate(layout, this.pushSettingsLayout, false);
                groupedCheck.setChecked(preference.isPushEnabled());
                groupedCheck.setOnCheckedChangeListener(pushChanged(preference));
                setStrings(preference.getGroup(), groupedCheck);
                this.pushSettingsLayout.addView(groupedCheck);
            }
            if (preference.isAppliesToSms()) {
                SwitchRow groupedCheck2 = (SwitchRow) inflater.inflate(layout, this.smsSettingsLayout, false);
                groupedCheck2.setChecked(preference.isSmsEnabled());
                groupedCheck2.setOnCheckedChangeListener(smsChanged(preference));
                setStrings(preference.getGroup(), groupedCheck2);
                this.smsSettingsLayout.addView(groupedCheck2);
            }
            if (NotificationPreferencesGroups.SUPERHERO.equals(preference.getGroup())) {
                SuperHeroUtils.saveSuperHeroSetting(this.mPreferences, preference.isPushEnabled());
            }
        }
    }

    private OnCheckedChangeListener pushChanged(NotificationPreference preference) {
        return NotificationSettingsFragment$$Lambda$5.lambdaFactory$(this, preference);
    }

    static /* synthetic */ void lambda$pushChanged$3(NotificationSettingsFragment notificationSettingsFragment, NotificationPreference preference, SwitchRowInterface compoundButton, boolean checked) {
        preference.setPushEnabled(checked);
        notificationSettingsFragment.executeNotificationPreferenceRequest(preference);
        if (NotificationPreferencesGroups.SUPERHERO.equals(preference.getGroup())) {
            SuperHeroUtils.saveSuperHeroSetting(notificationSettingsFragment.mPreferences, checked);
        }
    }

    private OnCheckedChangeListener smsChanged(NotificationPreference preference) {
        return NotificationSettingsFragment$$Lambda$6.lambdaFactory$(this, preference);
    }

    static /* synthetic */ void lambda$smsChanged$4(NotificationSettingsFragment notificationSettingsFragment, NotificationPreference preference, SwitchRowInterface compoundButton, boolean checked) {
        preference.setSmsEnabled(checked);
        notificationSettingsFragment.executeNotificationPreferenceRequest(preference);
    }

    private void executeNotificationPreferenceRequest(NotificationPreference preference) {
        showLoader(true);
        new UpdateNotificationPreferencesRequest(preference).withListener((Observer) this.updatePreferenceListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$5(NotificationSettingsFragment notificationSettingsFragment, NotificationPreferencesResponse data) {
        notificationSettingsFragment.showLoader(false);
        Toast.makeText(notificationSettingsFragment.getActivity(), C0880R.string.notifications_preferences_updated, 0).show();
    }

    static /* synthetic */ void lambda$new$6(NotificationSettingsFragment notificationSettingsFragment, AirRequestNetworkException e) {
        notificationSettingsFragment.showLoader(false);
        NetworkUtil.toastGenericNetworkError(notificationSettingsFragment.getActivity());
    }
}
