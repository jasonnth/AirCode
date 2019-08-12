package com.airbnb.android.contentframework.imagepicker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.content.ContextCompat;
import android.support.p002v7.view.menu.ActionMenuItemView;
import android.support.p002v7.widget.GridLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.contentframework.imagepicker.MediaGridItemView.OnMediaItemClickListener;
import com.airbnb.android.contentframework.imagepicker.MediaLoader.MediaItemLoaderCallbacks;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import java.util.ArrayList;

public class StoryCreationImagePickerFragment extends AirFragment implements OnMediaItemClickListener, MediaItemLoaderCallbacks {
    private static final String ARG_SELECTED_PHOTO_URIS = "ARG_SELECTED_PHOTO_URIS";
    public static final int COL_COUNT = 4;
    public static final String EXTRA_RESULT_PHOTO_URIS = "EXTRA_RESULT_PHOTO_URIS";
    public static final int MAX_SELECT_COUNT = 4;
    private MediaGridAdapter mediaGridAdapter;
    private MediaLoader mediaLoader = new MediaLoader();
    private ActionMenuItemView menuItemView;
    @BindView
    RecyclerView recyclerView;
    @State
    ArrayList<Uri> selectedItems = new ArrayList<>();
    @BindView
    AirToolbar toolbar;

    public static Intent intent(Context context, ArrayList<Uri> selectedItems2) {
        return ((Builder) AutoFragmentActivity.create(context, StoryCreationImagePickerFragment.class).putParcelableArrayList(ARG_SELECTED_PHOTO_URIS, selectedItems2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.selectedItems.addAll(getArguments().getParcelableArrayList(ARG_SELECTED_PHOTO_URIS));
        }
        this.mediaGridAdapter = new MediaGridAdapter(this, this.selectedItems);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5709R.layout.fragment_story_creation_image_picker, container, false);
        bindViews(view);
        this.recyclerView.setAdapter(this.mediaGridAdapter);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        this.recyclerView.addItemDecoration(new MediaGridItemDecoration(4, getResources().getDimensionPixelSize(C5709R.dimen.story_creation_media_grid_inner_padding)));
        setToolbar(this.toolbar);
        updateToolbar();
        setHasOptionsMenu(true);
        this.mediaLoader.onCreate(getActivity(), this);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C5709R.C5712menu.image_picker_menu, menu);
        this.toolbar.post(StoryCreationImagePickerFragment$$Lambda$1.lambdaFactory$(this));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C5709R.C5711id.done || this.selectedItems.size() <= 0) {
            return super.onOptionsItemSelected(item);
        }
        ContentFrameworkAnalytics.trackImagePickerDone(this.selectedItems.size());
        Intent data = new Intent();
        data.putParcelableArrayListExtra(EXTRA_RESULT_PHOTO_URIS, this.selectedItems);
        getActivity().setResult(-1, data);
        getActivity().finish();
        return true;
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mediaLoader.onDestroy();
    }

    public void onMediaLoad(Cursor cursor) {
        this.mediaGridAdapter.setData(cursor);
    }

    public void onMediaReset() {
        this.mediaGridAdapter.setData(null);
    }

    public void onThumbnailClicked(Uri item) {
        if (this.selectedItems.contains(item)) {
            this.selectedItems.remove(item);
        } else if (this.selectedItems.size() < 4) {
            this.selectedItems.add(item);
        } else {
            new SnackbarWrapper().view(getView()).body(C5709R.string.story_creation_photo_picker_max_reached).duration(-1).buildAndShow();
        }
        onSelectionUpdated();
    }

    public void onThumbnailLongPressed(AirImageView imageview, Uri item) {
        startActivity(StoryCreationImagePreviewActivity.newInstance(getContext(), item), ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(), imageview, "preview_image").toBundle());
    }

    private void onSelectionUpdated() {
        updateToolbar();
        this.mediaGridAdapter.notifyDataSetChanged();
    }

    private void updateToolbar() {
        this.toolbar.setTitle((CharSequence) String.format(getString(C5709R.string.story_creation_photo_picker_title), new Object[]{Integer.valueOf(this.selectedItems.size()), Integer.valueOf(4)}));
        updateActionMenuState();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.StoryImagePicker;
    }

    private boolean readyToFinish() {
        return !this.selectedItems.isEmpty();
    }

    /* access modifiers changed from: private */
    public void updateActionMenuState() {
        boolean enableMenuItem = readyToFinish();
        if (this.menuItemView == null) {
            this.menuItemView = MiscUtils.getActionMenuItemView(this.toolbar);
        }
        if (this.menuItemView != null) {
            this.menuItemView.setTextColor(ContextCompat.getColor(getContext(), enableMenuItem ? C5709R.color.n2_babu : C5709R.color.n2_babu_30));
            this.menuItemView.setEnabled(enableMenuItem);
        }
    }
}
