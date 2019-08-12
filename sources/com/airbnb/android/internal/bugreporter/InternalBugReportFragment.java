package com.airbnb.android.internal.bugreporter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.erf.ExperimentsProvider;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.AirPhotoPicker;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.internal.C6574R;
import com.airbnb.android.internal.InternalGraph;
import com.airbnb.android.photopicker.PhotoPicker;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class InternalBugReportFragment extends AirFragment {
    private static final int MAX_PHOTO_SIZE_BYTES = 2048;
    private static final int REQUEST_CODE_SELECT_PICTURE = 5;
    private InternalBugReportAdapter adapter;
    ExperimentsProvider experimentsProvider;
    @State
    ArrayList<String> logFiles;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton sendButton;
    @BindView
    AirToolbar toolbar;

    static boolean enabled() {
        return BuildHelper.isDevelopmentBuild() || BuildHelper.isQa();
    }

    public static Intent createIntent(Context context) {
        assertEnabled();
        return AutoFragmentActivity.create(context, InternalBugReportFragment.class).allowAccessWithoutSession().build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assertEnabled();
        ((InternalGraph) CoreApplication.instance(getContext()).component()).inject(this);
        if (this.logFiles == null) {
            this.logFiles = DebugDumps.createFiles(getContext(), this.experimentsProvider);
        }
        this.adapter = new InternalBugReportAdapter(InternalBugReportFragment$$Lambda$1.lambdaFactory$(this), this.logFiles, this.mPrefsHelper.getLastDebugEmailUsed(), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6574R.layout.fragment_debug_bug_report, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 5 && resultCode == -1) {
            this.adapter.addPhoto(data.getStringExtra(PhotoPicker.EXTRA_PHOTO_PATH));
        }
    }

    /* access modifiers changed from: private */
    public void pickPhoto() {
        startActivityForResult(AirPhotoPicker.builder().targetOutputDimensions(2048, 2048).setSource(2).create(getContext()), 5);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onSend() {
        this.sendButton.setState(AirButton.State.Loading);
        String recipient = this.adapter.getRecipient();
        String subject = this.adapter.getSubject();
        String details = this.adapter.getDetails();
        boolean isGuestMode = this.mPrefsHelper.isGuestMode();
        List<String> files = Lists.newArrayList((Iterable<? extends E>) this.logFiles);
        files.addAll(this.adapter.getPhotos());
        startActivity(DebugEmailUtil.createEmailIntent(getContext(), recipient, subject, subject, details, isGuestMode, files));
        this.mPrefsHelper.setLastDebugEmailUsed(recipient);
        getAirActivity().finish();
    }

    private static void assertEnabled() {
        Check.state(enabled(), "Invalid state to trigger, internal builds only");
    }
}
