package com.airbnb.p027n2.browser;

import android.content.Context;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.widget.C0289SearchView;
import android.support.p002v7.widget.C0289SearchView.OnQueryTextListener;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.DLSComponentType;
import com.airbnb.p027n2.TeamOwner;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DLSComponent;
import com.airbnb.p027n2.components.DLSComponents;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* renamed from: com.airbnb.n2.browser.DLSComponentListFragment */
public class DLSComponentListFragment extends Fragment {
    private final Adapter<ViewHolder> adapter = new Adapter<ViewHolder>() {
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind(holder.itemView.getContext(), (DLSComponent) DLSComponentListFragment.this.filteredComponents.get(position));
        }

        public int getItemCount() {
            return DLSComponentListFragment.this.filteredComponents.size();
        }
    };
    private DLSComponent<?>[] components;
    /* access modifiers changed from: private */
    public List<DLSComponent<?>> filteredComponents;
    @BindDimen
    int itemMaxHeight;
    private final OnQueryTextListener queryTextListener = new OnQueryTextListener() {
        public boolean onQueryTextSubmit(String searchString) {
            DLSComponentListFragment.this.filterComponents(searchString);
            return true;
        }

        public boolean onQueryTextChange(String searchString) {
            DLSComponentListFragment.this.filterComponents(searchString);
            return true;
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    /* renamed from: com.airbnb.n2.browser.DLSComponentListFragment$ViewHolder */
    final class ViewHolder extends android.support.p002v7.widget.RecyclerView.ViewHolder {
        @BindView
        View clickOverlay;
        @BindView
        FrameLayout frame;
        @BindView
        TextView name;

        ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_list_item_dls_component, parent, false));
            ButterKnife.bind(this, this.itemView);
        }

        public void bind(Context context, DLSComponent<?> component) {
            this.clickOverlay.setOnClickListener(DLSComponentListFragment$ViewHolder$$Lambda$1.lambdaFactory$(this, component));
            this.name.setText(component.name());
            View view = component.createDefaultMockView(context, this.frame);
            this.frame.removeAllViews();
            this.frame.setBackgroundResource(component.getDefaultStyle().backgroundRes());
            this.frame.addView(view);
            view.addOnLayoutChangeListener(DLSComponentListFragment$ViewHolder$$Lambda$2.lambdaFactory$(this));
        }

        static /* synthetic */ void lambda$bind$2(ViewHolder viewHolder, View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            int height = bottom - top;
            if (height > DLSComponentListFragment.this.itemMaxHeight) {
                v.setPivotX(0.0f);
                v.setPivotY(0.0f);
                float scale = (((float) DLSComponentListFragment.this.itemMaxHeight) * 1.0f) / ((float) height);
                v.setScaleX(scale);
                v.setScaleY(scale);
                v.post(DLSComponentListFragment$ViewHolder$$Lambda$3.lambdaFactory$(viewHolder));
            }
        }
    }

    /* renamed from: com.airbnb.n2.browser.DLSComponentListFragment$ViewHolder_ViewBinding */
    public final class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target2, View source) {
            this.target = target2;
            target2.clickOverlay = Utils.findRequiredView(source, R.id.component_click_overlay, "field 'clickOverlay'");
            target2.name = (TextView) Utils.findRequiredViewAsType(source, R.id.component_name, "field 'name'", TextView.class);
            target2.frame = (FrameLayout) Utils.findRequiredViewAsType(source, R.id.component_frame, "field 'frame'", FrameLayout.class);
        }

        public void unbind() {
            ViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.clickOverlay = null;
            target2.name = null;
            target2.frame = null;
        }
    }

    public static DLSComponentListFragment newInstance(DLSComponentType type) {
        Bundle bundle = new Bundle();
        bundle.putString("type_name", type.name());
        DLSComponentListFragment fragment = new DLSComponentListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public static DLSComponentListFragment newInstance(TeamOwner team) {
        Bundle bundle = new Bundle();
        bundle.putString("team_name", team.name());
        DLSComponentListFragment fragment = new DLSComponentListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String title;
        View view = inflater.inflate(R.layout.n2_fragment_dls_component_list, container, false);
        ButterKnife.bind(this, view);
        Bundle arguments = getArguments();
        if (arguments.containsKey("type_name")) {
            DLSComponentType type = DLSComponentType.valueOf(arguments.getString("type_name"));
            this.components = DLSComponents.byType(type);
            title = type.name() + " Components";
        } else if (arguments.containsKey("team_name")) {
            TeamOwner team = TeamOwner.valueOf(arguments.getString("team_name"));
            this.components = DLSComponents.byTeam(team);
            title = team.name() + " Components";
        } else {
            throw new IllegalStateException("Either a type of component or a team must be specified");
        }
        filterComponents("");
        this.toolbar.setTitle((CharSequence) title);
        ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (this.toolbar != null) {
            this.toolbar.onCreateOptionsMenu(menu, inflater);
            C0289SearchView searchView = (C0289SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setOnQueryTextListener(this.queryTextListener);
            searchView.setSubmitButtonEnabled(false);
        }
    }

    /* access modifiers changed from: private */
    public void filterComponents(String searchString) {
        DLSComponent<?>[] dLSComponentArr;
        if (TextUtils.isEmpty(searchString)) {
            this.filteredComponents = Arrays.asList(this.components);
        } else {
            String searchString2 = searchString.toLowerCase(Locale.US);
            this.filteredComponents = new ArrayList();
            for (DLSComponent<?> component : this.components) {
                if (component.name().toLowerCase(Locale.US).contains(searchString2)) {
                    this.filteredComponents.add(component);
                }
            }
        }
        this.adapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void onComponentClick(DLSComponent<?> component) {
        hideSoftKeyboard();
        showFragment(DLSComponentFragment.newInstance(component));
    }

    public void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService("input_method");
        if (imm != null && getView() != null) {
            imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
        }
    }

    private void showFragment(Fragment fragment) {
        ((DLSComponentBrowserActivity) getActivity()).showFragment(fragment);
    }
}
