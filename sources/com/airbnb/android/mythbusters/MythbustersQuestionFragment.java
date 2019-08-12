package com.airbnb.android.mythbusters;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;

public class MythbustersQuestionFragment extends AirFragment {
    private static final String ARG_QUESTION = "question";
    private MythbustersQuestionEpoxyController epoxyController;
    private TrueFalseQuestion question;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static MythbustersQuestionFragment create(TrueFalseQuestion question2) {
        return (MythbustersQuestionFragment) ((FragmentBundleBuilder) FragmentBundler.make(new MythbustersQuestionFragment()).putParcelable(ARG_QUESTION, question2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.question = (TrueFalseQuestion) getArguments().getParcelable(ARG_QUESTION);
        this.epoxyController = new MythbustersQuestionEpoxyController(getContext(), this.question);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7485R.layout.fragment_recycler_view_with_toolbar_dark_foreground, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        return view;
    }
}
