package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PreBookingQuestion;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ManageListingPreBookingPreviewFragment extends ManageListingBaseFragment {
    @BindView
    FixedDualActionFooter footer;
    private ManageListingPreBookingPreviewAdapter preBookingPreviewAdapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.preBookingPreviewAdapter = new ManageListingPreBookingPreviewAdapter(this.controller);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_footer_bar, container, false);
        bindViews(view);
        if (savedInstanceState == null) {
            this.preBookingPreviewAdapter.updateUserAndMessage(this.controller.getListing().getPrimaryHost(), formatQuestions(this.controller.getListing()));
        }
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.preBookingPreviewAdapter);
        setupFooter();
        return view;
    }

    private static String formatQuestions(Listing listing) {
        List<String> formattedLines = new ArrayList<>();
        List<String> customQuestions = listing.getBookingCustomQuestions();
        if (!ListUtils.isEmpty((Collection<?>) customQuestions)) {
            formattedLines.addAll(FluentIterable.from((Iterable<E>) customQuestions).transform(ManageListingPreBookingPreviewFragment$$Lambda$1.lambdaFactory$()).toList());
        }
        List<PreBookingQuestion> standardQuestions = listing.getBookingStandardQuestions();
        if (!ListUtils.isEmpty((Collection<?>) standardQuestions)) {
            formattedLines.addAll(FluentIterable.from((Iterable<E>) standardQuestions).filter(ManageListingPreBookingPreviewFragment$$Lambda$2.lambdaFactory$()).transform(ManageListingPreBookingPreviewFragment$$Lambda$3.lambdaFactory$()).toList());
        }
        return TextUtils.join("\n", formattedLines);
    }

    static /* synthetic */ String lambda$formatQuestions$0(String q) {
        return " · " + q;
    }

    static /* synthetic */ String lambda$formatQuestions$2(PreBookingQuestion q) {
        return " · " + q.getQuestion();
    }

    private void setupFooter() {
        this.footer.setVisibility(0);
        this.footer.setButtonText(C7368R.string.done_caps);
        this.footer.setSecondaryButtonText(C7368R.string.manage_listing_prebooking_preview_button);
        this.footer.setButtonOnClickListener(ManageListingPreBookingPreviewFragment$$Lambda$4.lambdaFactory$(this));
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
