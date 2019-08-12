package com.airbnb.android.fixit.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.models.FixItItem;
import com.airbnb.android.core.requests.UpdateFixItItemRequest;
import com.airbnb.android.core.responses.FixItItemResponse;
import com.airbnb.android.fixit.C6380R;
import com.airbnb.android.fixit.FixItItemController;
import com.airbnb.android.fixit.FixItItemController.Listener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import icepick.State;
import p032rx.Observer;

public class FixItItemFragment extends FixItBaseFragment {
    private static final String ARG_FIX_IT_ITEM = "fix_it_item";
    private FixItItemController epoxyController;
    @BindView
    FixedDualActionFooter footer;
    @State
    FixItItem item;
    final RequestListener<FixItItemResponse> itemUpdateReponseListener = new C0699RL().onResponse(FixItItemFragment$$Lambda$1.lambdaFactory$(this)).onError(FixItItemFragment$$Lambda$2.lambdaFactory$(this)).onComplete(FixItItemFragment$$Lambda$3.lambdaFactory$(this)).build();
    private final Listener listener = new Listener() {
        public void onPhotoProofsItemSelected() {
        }

        public void onCommentItemSelected(FixItItem item) {
            FixItItemFragment.this.activity.showItemComment(item);
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static FixItItemFragment create(FixItItem item2) {
        return (FixItItemFragment) ((FragmentBundleBuilder) FragmentBundler.make(new FixItItemFragment()).putParcelable(ARG_FIX_IT_ITEM, item2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.epoxyController = new FixItItemController(getContext(), this.listener);
        if (savedInstanceState == null) {
            this.item = (FixItItem) getArguments().getParcelable(ARG_FIX_IT_ITEM);
            this.epoxyController.setData(this.item);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6380R.layout.fragment_fix_it_item, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        this.footer.setButtonOnClickListener(FixItItemFragment$$Lambda$4.lambdaFactory$(this));
        return view;
    }

    public void onDestroyView() {
        this.recyclerView.setAdapter(null);
        super.onDestroyView();
    }

    public void dataUpdated() {
        super.dataUpdated();
        this.item = this.dataController.getItem(this.item.getId());
        this.epoxyController.setData(this.item);
        updateSaveButtonStatus();
    }

    private void updateSaveButtonStatus() {
        this.footer.setButtonEnabled(!this.item.isReadOnly() && !this.item.isMissingRequiredProof());
    }

    /* access modifiers changed from: private */
    public void onDonePressed() {
        this.footer.setButtonLoading(true);
        UpdateFixItItemRequest.forStatus(this.item.getId(), 1).withListener((Observer) this.itemUpdateReponseListener).execute(this.requestManager);
    }
}
