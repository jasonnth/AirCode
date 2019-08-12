package com.airbnb.android.lib.activities;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class DebugMenuActivity_ViewBinding implements Unbinder {
    private DebugMenuActivity target;
    private View view2131755749;
    private TextWatcher view2131755749TextWatcher;
    private View view2131755752;
    private View view2131755753;
    private View view2131755754;
    private View view2131755755;
    private View view2131755756;
    private View view2131755757;
    private View view2131755758;
    private View view2131755759;
    private View view2131755760;
    private View view2131755761;
    private View view2131755762;
    private View view2131755763;
    private View view2131755764;
    private View view2131755765;
    private View view2131755766;
    private View view2131755767;
    private View view2131755768;
    private View view2131755769;
    private View view2131755770;
    private View view2131755771;
    private View view2131755772;
    private View view2131755773;
    private View view2131755774;
    private View view2131755775;
    private View view2131755776;
    private View view2131755777;
    private View view2131755778;
    private View view2131755779;
    private View view2131755780;
    private View view2131755781;
    private View view2131755782;
    private View view2131755783;
    private View view2131755784;
    private View view2131755785;
    private View view2131755786;
    private View view2131755787;
    private View view2131755788;
    private View view2131755789;
    private View view2131755790;
    private View view2131755791;
    private View view2131755792;
    private View view2131755793;
    private View view2131755794;

    public DebugMenuActivity_ViewBinding(DebugMenuActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public DebugMenuActivity_ViewBinding(final DebugMenuActivity target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.switch_server_layout, "field 'switchServerLayout' and method 'onClickSwitchServer'");
        target2.switchServerLayout = view;
        this.view2131755752 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSwitchServer();
            }
        });
        target2.scrollView = (ScrollView) Utils.findRequiredViewAsType(source, C0880R.C0882id.debug_menu_item_scroll_view, "field 'scrollView'", ScrollView.class);
        target2.debugMenuItemContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.debug_menu_item_container, "field 'debugMenuItemContainer'", LinearLayout.class);
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.debug_menu_filter, "field 'debugMenuFilter' and method 'filterDebugMenuItems'");
        target2.debugMenuFilter = (EditText) Utils.castView(view2, C0880R.C0882id.debug_menu_filter, "field 'debugMenuFilter'", EditText.class);
        this.view2131755749 = view2;
        this.view2131755749TextWatcher = new TextWatcher() {
            public void onTextChanged(CharSequence p0, int p1, int p2, int p3) {
                target2.filterDebugMenuItems(p0);
            }

            public void beforeTextChanged(CharSequence p0, int p1, int p2, int p3) {
            }

            public void afterTextChanged(Editable p0) {
            }
        };
        ((TextView) view2).addTextChangedListener(this.view2131755749TextWatcher);
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.override_erf_layout, "method 'onClickOverrideErfLayout'");
        this.view2131755754 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickOverrideErfLayout();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.dls_component_browser, "method 'onClickDLSComponentBrowser'");
        this.view2131755755 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickDLSComponentBrowser();
            }
        });
        View view5 = Utils.findRequiredView(source, C0880R.C0882id.go_to_listing_details, "method 'onClickGoToListing'");
        this.view2131755756 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickGoToListing();
            }
        });
        View view6 = Utils.findRequiredView(source, C0880R.C0882id.go_to_experience, "method 'onClickGoToExperience'");
        this.view2131755757 = view6;
        view6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickGoToExperience();
            }
        });
        View view7 = Utils.findRequiredView(source, C0880R.C0882id.go_to_article, "method 'onClickGoToArticle'");
        this.view2131755758 = view7;
        view7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickGoToArticle();
            }
        });
        View view8 = Utils.findRequiredView(source, C0880R.C0882id.go_to_trip_assistant, "method 'onClickGoToTripAssistant'");
        this.view2131755759 = view8;
        view8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickGoToTripAssistant();
            }
        });
        View view9 = Utils.findRequiredView(source, C0880R.C0882id.view_checkin, "method 'onClickViewCheckin'");
        this.view2131755792 = view9;
        view9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickViewCheckin();
            }
        });
        View view10 = Utils.findRequiredView(source, C0880R.C0882id.view_phb_page, "method 'onClickViewPHB'");
        this.view2131755793 = view10;
        view10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickViewPHB();
            }
        });
        View view11 = Utils.findRequiredView(source, C0880R.C0882id.build_config, "method 'showBuildConfig'");
        this.view2131755788 = view11;
        view11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showBuildConfig();
            }
        });
        View view12 = Utils.findRequiredView(source, C0880R.C0882id.override_trebuchet_layout, "method 'onClickOverrideTrebuchetLayout'");
        this.view2131755753 = view12;
        view12.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickOverrideTrebuchetLayout();
            }
        });
        View view13 = Utils.findRequiredView(source, C0880R.C0882id.airrequest_debug, "method 'onClickRequestDebugging'");
        this.view2131755786 = view13;
        view13.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickRequestDebugging();
            }
        });
        View view14 = Utils.findRequiredView(source, C0880R.C0882id.set_user_verifications_layout, "method 'onClickSetUserVerifications'");
        this.view2131755768 = view14;
        view14.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSetUserVerifications();
            }
        });
        View view15 = Utils.findRequiredView(source, C0880R.C0882id.launch_verifications_flow, "method 'onClickLaunchVerificationsFlow'");
        this.view2131755769 = view15;
        view15.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchVerificationsFlow();
            }
        });
        View view16 = Utils.findRequiredView(source, C0880R.C0882id.launch_new_p5, "method 'onClickLaunchNewP5'");
        this.view2131755773 = view16;
        view16.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchNewP5();
            }
        });
        View view17 = Utils.findRequiredView(source, C0880R.C0882id.clear_message_storage, "method 'onClickClearMessageStorage'");
        this.view2131755774 = view17;
        view17.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickClearMessageStorage();
            }
        });
        View view18 = Utils.findRequiredView(source, C0880R.C0882id.test_superhero, "method 'onTestSuperhero'");
        this.view2131755760 = view18;
        view18.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onTestSuperhero();
            }
        });
        View view19 = Utils.findRequiredView(source, C0880R.C0882id.clear_itinerary_cache, "method 'onClearItineraryCache'");
        this.view2131755761 = view19;
        view19.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClearItineraryCache();
            }
        });
        View view20 = Utils.findRequiredView(source, C0880R.C0882id.clear_super_hero_cache, "method 'onClearSuperHeroCache'");
        this.view2131755762 = view20;
        view20.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClearSuperHeroCache();
            }
        });
        View view21 = Utils.findRequiredView(source, C0880R.C0882id.force_profile, "method 'onClickForceProfile'");
        this.view2131755763 = view21;
        view21.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickForceProfile();
            }
        });
        View view22 = Utils.findRequiredView(source, C0880R.C0882id.launch_react_native_guidebooks, "method 'onClickLaunchRNGuidebooks'");
        this.view2131755780 = view22;
        view22.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchRNGuidebooks();
            }
        });
        View view23 = Utils.findRequiredView(source, C0880R.C0882id.launch_react_native_giftcards, "method 'onClickLaunchRNGiftCards'");
        this.view2131755765 = view23;
        view23.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchRNGiftCards();
            }
        });
        View view24 = Utils.findRequiredView(source, C0880R.C0882id.launch_add_payment_method, "method 'onClickLaunchAddPaymentMethod'");
        this.view2131755766 = view24;
        view24.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchAddPaymentMethod();
            }
        });
        View view25 = Utils.findRequiredView(source, C0880R.C0882id.launch_react_native_guidebooks_subcategory, "method 'onClickLaunchRNGuidebooksSubcategory'");
        this.view2131755781 = view25;
        view25.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchRNGuidebooksSubcategory();
            }
        });
        View view26 = Utils.findRequiredView(source, C0880R.C0882id.launch_react_native_guidebook_insider, "method 'onClickLaunchRNGuidebookInsider'");
        this.view2131755782 = view26;
        view26.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchRNGuidebookInsider();
            }
        });
        View view27 = Utils.findRequiredView(source, C0880R.C0882id.launch_react_native_guidebook_detour, "method 'onClickLaunchRNGuidebookDetour'");
        this.view2131755783 = view27;
        view27.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchRNGuidebookDetour();
            }
        });
        View view28 = Utils.findRequiredView(source, C0880R.C0882id.launch_react_native_guidebook_meetupcollection, "method 'onClickLaunchRNGuidebookMeetupCollection'");
        this.view2131755784 = view28;
        view28.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchRNGuidebookMeetupCollection();
            }
        });
        View view29 = Utils.findRequiredView(source, C0880R.C0882id.launch_react_native_explorer, "method 'onClickLaunchRNExplorer'");
        this.view2131755778 = view29;
        view29.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchRNExplorer();
            }
        });
        View view30 = Utils.findRequiredView(source, C0880R.C0882id.launch_react_native_apps_menu, "method 'onClickLaunchRNAppsMenu'");
        this.view2131755779 = view30;
        view30.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchRNAppsMenu();
            }
        });
        View view31 = Utils.findRequiredView(source, C0880R.C0882id.launch_react_native_support_chat, "method 'onClickLaunchSupportChat'");
        this.view2131755785 = view31;
        view31.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchSupportChat();
            }
        });
        View view32 = Utils.findRequiredView(source, C0880R.C0882id.launch_new_verification_flow, "method 'onClickLaunchNewVerificationFlow'");
        this.view2131755767 = view32;
        view32.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchNewVerificationFlow();
            }
        });
        View view33 = Utils.findRequiredView(source, C0880R.C0882id.launch_redesigned_referrals, "method 'onClickLaunchRedesignedReferrals'");
        this.view2131755772 = view33;
        view33.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchRedesignedReferrals();
            }
        });
        View view34 = Utils.findRequiredView(source, C0880R.C0882id.launch_react_verifications_flow, "method 'onClickLaunchReactVerificationFlow'");
        this.view2131755770 = view34;
        view34.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchReactVerificationFlow();
            }
        });
        View view35 = Utils.findRequiredView(source, C0880R.C0882id.launch_react_verifications_info, "method 'onClickLaunchReactVerificationInfo'");
        this.view2131755771 = view35;
        view35.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickLaunchReactVerificationInfo();
            }
        });
        View view36 = Utils.findRequiredView(source, C0880R.C0882id.show_git_sha, "method 'onClickGitShaCell'");
        this.view2131755787 = view36;
        view36.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickGitShaCell();
            }
        });
        View view37 = Utils.findRequiredView(source, C0880R.C0882id.gcm_token, "method 'onClickGcmTokenCell'");
        this.view2131755789 = view37;
        view37.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickGcmTokenCell();
            }
        });
        View view38 = Utils.findRequiredView(source, C0880R.C0882id.show_experience_host_inbox, "method 'onShowExperienceHostInbox'");
        this.view2131755775 = view38;
        view38.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onShowExperienceHostInbox();
            }
        });
        View view39 = Utils.findRequiredView(source, C0880R.C0882id.write_reviews_layout, "method 'onClickWriteReview'");
        this.view2131755776 = view39;
        view39.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickWriteReview();
            }
        });
        View view40 = Utils.findRequiredView(source, C0880R.C0882id.adaptive_reviews_layout, "method 'onClickAdaptiveReview'");
        this.view2131755777 = view40;
        view40.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickAdaptiveReview();
            }
        });
        View view41 = Utils.findRequiredView(source, C0880R.C0882id.launch_mt_host_app, "method 'launchMTHostReactNativeActivity'");
        this.view2131755790 = view41;
        view41.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.launchMTHostReactNativeActivity();
            }
        });
        View view42 = Utils.findRequiredView(source, C0880R.C0882id.launch_resy_quick_pay, "method 'launchResyQuickPay'");
        this.view2131755764 = view42;
        view42.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.launchResyQuickPay();
            }
        });
        View view43 = Utils.findRequiredView(source, C0880R.C0882id.go_to_activity_pdp, "method 'launchActivityPDP'");
        this.view2131755791 = view43;
        view43.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.launchActivityPDP();
            }
        });
        View view44 = Utils.findRequiredView(source, C0880R.C0882id.go_to_mythbusters_quiz, "method 'goToMythbustersQuiz'");
        this.view2131755794 = view44;
        view44.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.goToMythbustersQuiz();
            }
        });
    }

    public void unbind() {
        DebugMenuActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.switchServerLayout = null;
        target2.scrollView = null;
        target2.debugMenuItemContainer = null;
        target2.debugMenuFilter = null;
        this.view2131755752.setOnClickListener(null);
        this.view2131755752 = null;
        ((TextView) this.view2131755749).removeTextChangedListener(this.view2131755749TextWatcher);
        this.view2131755749TextWatcher = null;
        this.view2131755749 = null;
        this.view2131755754.setOnClickListener(null);
        this.view2131755754 = null;
        this.view2131755755.setOnClickListener(null);
        this.view2131755755 = null;
        this.view2131755756.setOnClickListener(null);
        this.view2131755756 = null;
        this.view2131755757.setOnClickListener(null);
        this.view2131755757 = null;
        this.view2131755758.setOnClickListener(null);
        this.view2131755758 = null;
        this.view2131755759.setOnClickListener(null);
        this.view2131755759 = null;
        this.view2131755792.setOnClickListener(null);
        this.view2131755792 = null;
        this.view2131755793.setOnClickListener(null);
        this.view2131755793 = null;
        this.view2131755788.setOnClickListener(null);
        this.view2131755788 = null;
        this.view2131755753.setOnClickListener(null);
        this.view2131755753 = null;
        this.view2131755786.setOnClickListener(null);
        this.view2131755786 = null;
        this.view2131755768.setOnClickListener(null);
        this.view2131755768 = null;
        this.view2131755769.setOnClickListener(null);
        this.view2131755769 = null;
        this.view2131755773.setOnClickListener(null);
        this.view2131755773 = null;
        this.view2131755774.setOnClickListener(null);
        this.view2131755774 = null;
        this.view2131755760.setOnClickListener(null);
        this.view2131755760 = null;
        this.view2131755761.setOnClickListener(null);
        this.view2131755761 = null;
        this.view2131755762.setOnClickListener(null);
        this.view2131755762 = null;
        this.view2131755763.setOnClickListener(null);
        this.view2131755763 = null;
        this.view2131755780.setOnClickListener(null);
        this.view2131755780 = null;
        this.view2131755765.setOnClickListener(null);
        this.view2131755765 = null;
        this.view2131755766.setOnClickListener(null);
        this.view2131755766 = null;
        this.view2131755781.setOnClickListener(null);
        this.view2131755781 = null;
        this.view2131755782.setOnClickListener(null);
        this.view2131755782 = null;
        this.view2131755783.setOnClickListener(null);
        this.view2131755783 = null;
        this.view2131755784.setOnClickListener(null);
        this.view2131755784 = null;
        this.view2131755778.setOnClickListener(null);
        this.view2131755778 = null;
        this.view2131755779.setOnClickListener(null);
        this.view2131755779 = null;
        this.view2131755785.setOnClickListener(null);
        this.view2131755785 = null;
        this.view2131755767.setOnClickListener(null);
        this.view2131755767 = null;
        this.view2131755772.setOnClickListener(null);
        this.view2131755772 = null;
        this.view2131755770.setOnClickListener(null);
        this.view2131755770 = null;
        this.view2131755771.setOnClickListener(null);
        this.view2131755771 = null;
        this.view2131755787.setOnClickListener(null);
        this.view2131755787 = null;
        this.view2131755789.setOnClickListener(null);
        this.view2131755789 = null;
        this.view2131755775.setOnClickListener(null);
        this.view2131755775 = null;
        this.view2131755776.setOnClickListener(null);
        this.view2131755776 = null;
        this.view2131755777.setOnClickListener(null);
        this.view2131755777 = null;
        this.view2131755790.setOnClickListener(null);
        this.view2131755790 = null;
        this.view2131755764.setOnClickListener(null);
        this.view2131755764 = null;
        this.view2131755791.setOnClickListener(null);
        this.view2131755791 = null;
        this.view2131755794.setOnClickListener(null);
        this.view2131755794 = null;
    }
}
