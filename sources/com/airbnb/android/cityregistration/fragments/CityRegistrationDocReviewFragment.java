package com.airbnb.android.cityregistration.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.cityregistration.adapters.CityRegistrationDocReviewAdapter;
import com.airbnb.android.cityregistration.adapters.CityRegistrationDocReviewAdapter.Listener;
import com.airbnb.android.cityregistration.controller.CityRegistrationController;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationAnswer;
import com.airbnb.android.core.models.ListingRegistrationQuestion;
import com.airbnb.android.listing.views.TipView;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.io.File;

public class CityRegistrationDocReviewFragment extends CityRegistrationBaseFragment {
    private static final String ARG_FILE_PATH = "filePath";
    private static final String ARG_QUESTION = "currentQuestionBeingUpdated";
    private static final String ARG_URL = "url";
    private CityRegistrationDocReviewAdapter adapter;
    @State
    String filePath;
    private Listener listener = new Listener() {
        public void showDocumentTypeSelection(ListingRegistrationQuestion question) {
            CityRegistrationDocReviewFragment.this.controller.showDocTypeSelection(question);
        }

        public void getDocPhoto(ListingRegistrationQuestion question) {
            CityRegistrationDocReviewFragment.this.controller.getDocPhoto(question.getInputAnswer());
        }
    };
    @State
    ListingRegistrationQuestion question;
    @BindView
    RecyclerView recyclerView;
    @BindView
    TipView tipView;
    @BindView
    AirToolbar toolbar;
    @BindView
    TextView toolbarTitle;
    @State
    String url;

    public static CityRegistrationDocReviewFragment createWithFilePath(ListingRegistrationQuestion question2, String filePath2) {
        return (CityRegistrationDocReviewFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CityRegistrationDocReviewFragment()).putParcelable(ARG_QUESTION, question2)).putString(ARG_FILE_PATH, filePath2)).build();
    }

    public static CityRegistrationDocReviewFragment createWithUrl(ListingRegistrationQuestion question2, String url2) {
        return (CityRegistrationDocReviewFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CityRegistrationDocReviewFragment()).putParcelable(ARG_QUESTION, question2)).putString("url", url2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (savedInstanceState == null) {
            this.question = (ListingRegistrationQuestion) getArguments().getParcelable(ARG_QUESTION);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5630R.layout.fragment_city_registration_doc_review, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.filePath = getArguments().getString(ARG_FILE_PATH);
            this.url = getArguments().getString("url");
        }
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.toolbarTitle.setText(this.question.getQuestionText());
        ListingRegistrationAnswer answer = this.question.getAnswer(this.question.getInputAnswer());
        if (answer.getExplanationText() == null || answer.getExplanationText().size() <= 0) {
            this.tipView.setTipTextRes(C5630R.string.cityregistration_doc_review_tip);
        } else {
            this.tipView.setTipText((String) answer.getExplanationText().get(0));
        }
        this.adapter = new CityRegistrationDocReviewAdapter(getContext(), this.listener, this.question, this.filePath, this.url, savedInstanceState);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(C5630R.C5633menu.manage_photo, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C5630R.C5632id.delete_photo) {
            return super.onOptionsItemSelected(item);
        }
        this.controller.deleteDoc(this.question);
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            switch (requestCode) {
                case CityRegistrationController.RC_CHOOSE_PHOTO /*202*/:
                    this.controller.handleImageCapture(Uri.fromFile(new File(data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH))), this.question, this.question.getInputAnswer());
                    return;
                default:
                    return;
            }
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (this.adapter != null) {
            this.adapter.onSaveInstanceState(outState);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onSaveActionPressed() {
        return true;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationInputGroupDocUploadReview;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11639kv("input_key", this.question.getInputKey()).mo11639kv("input_answer", this.question.getInputAnswer());
    }

    @OnClick
    public void onSave() {
        this.controller.saveDoc(this.question, this.filePath);
    }
}
