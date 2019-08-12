package com.airbnb.android.lib.fragments.unlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.enums.LegacyUnlistReason;

public class UnlistLawQuestionsFragment extends BaseSnoozeListingFragment {
    public static UnlistLawQuestionsFragment newInstance() {
        return new UnlistLawQuestionsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_unlist_law_questions, container, false);
        bindViews(view);
        return view;
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void keepListingListed() {
        getActivity().finish();
    }

    /* access modifiers changed from: protected */
    public LegacyUnlistReason getUnlistReason() {
        return LegacyUnlistReason.LawQuestions;
    }
}
