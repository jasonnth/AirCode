package com.airbnb.android.booking.fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PreBookingQuestion;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.TranslationUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.airbnb.p027n2.components.KickerMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import com.airbnb.p027n2.primitives.messaging.MessageItemReceiver;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.jmrtd.cbeff.ISO781611;

public class BookingEditTextFragment extends BookingV2BaseFragment {
    private static final String ARG_MESSAGE = "arg_message";
    private static final String ARG_TYPE = "arg_type";
    private static final String ARG_USER = "arg_user";
    @BindView
    AirEditTextView editTextView;
    private final OnGlobalLayoutListener globalLayoutListener = new OnGlobalLayoutListener() {
        public void onGlobalLayout() {
            boolean isKeyboardUp = KeyboardUtils.isKeyboardUp(BookingEditTextFragment.this.getAirActivity(), BookingEditTextFragment.this.getView());
            if (BookingEditTextFragment.this.isKeyboardUp != isKeyboardUp) {
                BookingEditTextFragment.this.isKeyboardUp = isKeyboardUp;
                if (isKeyboardUp) {
                    BookingEditTextFragment.this.scrollView.post(BookingEditTextFragment$1$$Lambda$1.lambdaFactory$(this));
                    BookingEditTextFragment.this.navView.setVisibility(8);
                    BookingEditTextFragment.this.nextButton.setVisibility(0);
                    return;
                }
                BookingEditTextFragment.this.navView.setVisibility(0);
                BookingEditTextFragment.this.nextButton.setVisibility(8);
            }
        }

        static /* synthetic */ void lambda$onGlobalLayout$0(C55561 r2) {
            VerboseScrollView verboseScrollView = BookingEditTextFragment.this.scrollView;
            VerboseScrollView verboseScrollView2 = BookingEditTextFragment.this.scrollView;
            verboseScrollView.fullScroll(ISO781611.BIOMETRIC_SUBTYPE_TAG);
        }
    };
    @BindView
    HaloImageView hostImage;
    @State
    boolean isKeyboardUp;
    @BindView
    KickerMarquee marquee;
    @State
    String message;
    @BindView
    MessageItemReceiver messageItem;
    @BindView
    BookingNavigationView navView;
    @BindView
    AirButton nextButton;
    @BindView
    FrameLayout quoteContainer;
    @BindView
    VerboseScrollView scrollView;
    private SnackbarWrapper snackbar;
    private final TextWatcher textWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            BookingEditTextFragment.this.setButtonText();
        }
    };
    @BindView
    AirToolbar toolbar;
    @State
    Type type;
    @State
    User user;

    public enum Type {
        ContactHost(C0704R.string.contact_host_say_hello_header, C0704R.string.contact_host_sent_after_booking, C0704R.string.contact_host_hint, false, NavigationTag.BookingFirstMessage, C2467P4FlowPage.BookingFirstMessage),
        TripPurpose(C0704R.string.p4_trip_purpose_title, C0704R.string.p4_trip_purpose_subtitle, C0704R.string.p4_write_a_trip_purpose_hint, true, NavigationTag.BookingTripPurpose, C2467P4FlowPage.BookingTripPurpose),
        ContactHostV2(C0704R.string.contact_host_say_hello_header_v2, C0704R.string.contact_host_say_hello_subtitle, C0704R.string.contact_host_hint_v2, false, NavigationTag.BookingFirstMessage, C2467P4FlowPage.BookingFirstMessage);
        
        final boolean allowEmptyMessage;
        final int captionRes;
        final C2467P4FlowPage flowPage;
        final int hintRes;
        final NavigationTag navigationTag;
        final int titleRes;

        private Type(int titleRes2, int captionRes2, int hintRes2, boolean allowEmptyMessage2, NavigationTag navigationTag2, C2467P4FlowPage flowPage2) {
            this.titleRes = titleRes2;
            this.captionRes = captionRes2;
            this.hintRes = hintRes2;
            this.allowEmptyMessage = allowEmptyMessage2;
            this.navigationTag = navigationTag2;
            this.flowPage = flowPage2;
        }

        public String getTitle(Context context, String hostName) {
            if (this != ContactHost) {
                return context.getString(this.titleRes);
            }
            return context.getString(this.titleRes, new Object[]{hostName});
        }
    }

    public static BookingEditTextFragment newInstance(User user2, String message2, Type type2) {
        return (BookingEditTextFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new BookingEditTextFragment()).putParcelable(ARG_USER, user2)).putString(ARG_MESSAGE, message2)).putSerializable(ARG_TYPE, type2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String firstName;
        String messageText;
        ViewGroup view = (ViewGroup) inflater.inflate(C0704R.layout.fragment_booking_edit_text, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setUpNavButton(this.navView, C0704R.string.next);
        if (savedInstanceState == null) {
            this.user = (User) getArguments().getParcelable(ARG_USER);
            this.message = getArguments().getString(ARG_MESSAGE);
            this.type = (Type) getArguments().getSerializable(ARG_TYPE);
        }
        KickerMarquee kickerMarquee = this.marquee;
        Type type2 = this.type;
        Context context = getContext();
        if (this.user == null) {
            firstName = null;
        } else {
            firstName = this.user.getFirstName();
        }
        kickerMarquee.setTitle(type2.getTitle(context, firstName));
        this.marquee.setSubtitle(this.type.captionRes);
        this.marquee.setKicker((CharSequence) getController().getP4Steps());
        if (this.user == null || !(this.type == Type.ContactHost || this.type == Type.ContactHostV2)) {
            this.quoteContainer.setVisibility(8);
        } else {
            this.hostImage.setImageUrl(this.user.getPictureUrl());
            this.messageItem.setProfileUrl(null);
            String instantBookMessage = this.listing.getInstantBookWelcomeMessage();
            if (this.type != Type.ContactHost) {
                StringBuilder sb = new StringBuilder();
                if (TextUtils.isEmpty(instantBookMessage)) {
                    instantBookMessage = getString(C0704R.string.contact_host_default_quote_v2);
                }
                messageText = sb.append(instantBookMessage).append(getPrebookingQuestions(this.listing)).toString();
                if (!LocaleUtil.getDeviceLanguage(getContext()).equals(this.listing.getDescriptionLocale())) {
                    this.messageItem.setTranslateText(TranslationUtils.getTranslateLink(getContext(), this.listing.getDescriptionLocale(), false, C0704R.color.c_babu, null));
                    this.messageItem.setTranslateClickListener(BookingEditTextFragment$$Lambda$1.lambdaFactory$());
                }
            } else if (TextUtils.isEmpty(instantBookMessage)) {
                messageText = getString(C0704R.string.contact_host_default_quote, this.user.getFirstName());
            } else {
                messageText = instantBookMessage + (TextUtils.isEmpty(this.user.getFirstName()) ? "" : "\n\n" + this.user.getFirstName());
            }
            this.messageItem.setMessageText(messageText);
        }
        this.editTextView.setHint(this.type.hintRes);
        if (!TextUtils.isEmpty(this.message)) {
            this.editTextView.setText(this.message);
        } else if (this.editTextView.requestFocus()) {
            showKeyboard();
        }
        this.navView.setButtonOnClickListener(BookingEditTextFragment$$Lambda$2.lambdaFactory$(this));
        view.getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        this.editTextView.addTextChangedListener(this.textWatcher);
        if (this.type.allowEmptyMessage) {
            setButtonText();
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(View v) {
    }

    private static String getPrebookingQuestions(Listing listing) {
        List<String> formattedLines = new ArrayList<>();
        List<String> customQuestions = listing.getBookingCustomQuestions();
        if (!ListUtils.isEmpty((Collection<?>) customQuestions)) {
            formattedLines.addAll(customQuestions);
        }
        List<PreBookingQuestion> standardQuestions = listing.getBookingStandardQuestions();
        if (!ListUtils.isEmpty((Collection<?>) standardQuestions)) {
            formattedLines.addAll(FluentIterable.from((Iterable<E>) standardQuestions).filter(BookingEditTextFragment$$Lambda$3.lambdaFactory$()).transform(BookingEditTextFragment$$Lambda$4.lambdaFactory$()).toList());
        }
        return TextUtils.join("\n", formattedLines);
    }

    static /* synthetic */ String lambda$getPrebookingQuestions$3(PreBookingQuestion q) {
        return " · " + q.getQuestion();
    }

    /* access modifiers changed from: private */
    public void setButtonText() {
        int buttonTextRes = (!this.type.allowEmptyMessage || !this.editTextView.isEmpty()) ? C0704R.string.next : C0704R.string.action_skip;
        this.nextButton.setText(buttonTextRes);
        this.navView.setButtonText(buttonTextRes);
    }

    public void onDestroyView() {
        getView().getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
        super.onDestroyView();
    }

    public void onUpdateError(NetworkException e) {
    }

    public void onUpdated() {
    }

    @OnClick
    public void onClickNext() {
        if (!this.editTextView.isEmpty() || this.type.allowEmptyMessage) {
            KeyboardUtils.dismissSoftKeyboard((View) this.editTextView);
            updateText();
            logNavigationClick();
            getController().showNextStep(BookingController.getBookingStepLoader(this.navView));
            return;
        }
        this.snackbar = new SnackbarWrapper().view(this.editTextView).action(getResources().getString(C0716R.string.add_message), BookingEditTextFragment$$Lambda$5.lambdaFactory$(this)).body(getResources().getString(C0704R.string.add_a_message_prompt)).duration(0);
        this.snackbar.buildAndShow();
    }

    /* access modifiers changed from: private */
    public void showKeyboard() {
        this.editTextView.postDelayed(BookingEditTextFragment$$Lambda$6.lambdaFactory$(this), 200);
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
    }

    private void updateText() {
        BookingController controller = getController();
        if (this.type == Type.ContactHost) {
            this.reservationDetails = this.reservationDetails.toBuilder().messageToHost(this.editTextView.getText().toString()).build();
        } else {
            this.reservationDetails = this.reservationDetails.toBuilder().businessTripNote(this.editTextView.getText().toString()).build();
        }
        String mobileSearchSessionId = controller.getMobileSearchSessionId();
        controller.setReservationDetails(this.reservationDetails);
        KonaBookingAnalytics.trackUpdate(getNavigationTrackingTag().trackingName, this.reservationDetails, mobileSearchSessionId);
    }

    public boolean onBackPressed() {
        KeyboardUtils.dismissSoftKeyboard((View) this.editTextView);
        return super.onBackPressed();
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(true);
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.type.navigationTag;
    }

    public C2467P4FlowPage getP4FlowPage() {
        return this.type.flowPage;
    }
}
