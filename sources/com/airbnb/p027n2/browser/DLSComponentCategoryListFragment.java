package com.airbnb.p027n2.browser;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.DLSComponentType;
import com.airbnb.p027n2.TeamOwner;
import com.airbnb.p027n2.components.DLSComponents;
import com.airbnb.p027n2.components.StandardRow;
import com.google.common.base.CaseFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* renamed from: com.airbnb.n2.browser.DLSComponentCategoryListFragment */
public class DLSComponentCategoryListFragment extends Fragment {
    /* access modifiers changed from: private */
    public static final List<Item> items = new ArrayList();
    private final Adapter<ViewHolder> adapter = new Adapter<ViewHolder>() {
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(parent);
        }

        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.bind((Item) DLSComponentCategoryListFragment.items.get(position));
        }

        public int getItemCount() {
            return DLSComponentCategoryListFragment.items.size();
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    Toolbar toolbar;

    /* renamed from: com.airbnb.n2.browser.DLSComponentCategoryListFragment$Item */
    private class Item {
        private final TeamOwner team;
        private final String title;
        private final DLSComponentType type;

        private Item(String title2, DLSComponentType type2) {
            this.title = title2;
            this.type = type2;
            this.team = null;
        }

        private Item(String title2, TeamOwner team2) {
            this.title = title2;
            this.team = team2;
            this.type = null;
        }

        /* access modifiers changed from: private */
        public String getTitle() {
            return String.format(Locale.US, "%s (%d)", new Object[]{this.title, Integer.valueOf(getComponentCount())});
        }

        private int getComponentCount() {
            return (this.type != null ? DLSComponents.byType(this.type) : DLSComponents.byTeam(this.team)).length;
        }

        /* access modifiers changed from: private */
        public void onClick() {
            DLSComponentListFragment newInstance;
            DLSComponentCategoryListFragment dLSComponentCategoryListFragment = DLSComponentCategoryListFragment.this;
            if (this.type != null) {
                newInstance = DLSComponentListFragment.newInstance(this.type);
            } else {
                newInstance = DLSComponentListFragment.newInstance(this.team);
            }
            dLSComponentCategoryListFragment.showFragment(newInstance);
        }
    }

    /* renamed from: com.airbnb.n2.browser.DLSComponentCategoryListFragment$ViewHolder */
    final class ViewHolder extends android.support.p002v7.widget.RecyclerView.ViewHolder {
        @BindView
        StandardRow standardRow;

        ViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_list_item_dls_component_type, parent, false));
            ButterKnife.bind(this, this.itemView);
        }

        public void bind(Item item) {
            this.itemView.setOnClickListener(DLSComponentCategoryListFragment$ViewHolder$$Lambda$1.lambdaFactory$(item));
            this.standardRow.setTitle((CharSequence) item.getTitle());
        }
    }

    /* renamed from: com.airbnb.n2.browser.DLSComponentCategoryListFragment$ViewHolder_ViewBinding */
    public final class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target2, View source) {
            this.target = target2;
            target2.standardRow = (StandardRow) Utils.findRequiredViewAsType(source, R.id.standard_row, "field 'standardRow'", StandardRow.class);
        }

        public void unbind() {
            ViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.standardRow = null;
        }
    }

    public static DLSComponentCategoryListFragment newInstance() {
        return new DLSComponentCategoryListFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        TeamOwner[] values;
        super.onCreate(savedInstanceState);
        items.add(new Item("Core", DLSComponentType.Core));
        items.add(new Item("Team - All", DLSComponentType.Team));
        for (TeamOwner team : TeamOwner.values()) {
            if (team != TeamOwner.DLS) {
                items.add(new Item("Team - " + CaseFormat.UPPER_UNDERSCORE.mo41065to(CaseFormat.UPPER_CAMEL, team.name()), team));
            }
        }
        items.add(new Item("Deprecated", DLSComponentType.Deprecated));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.n2_fragment_dls_component_category_list, container, false);
        ButterKnife.bind(this, view);
        this.toolbar.setTitle((CharSequence) "Component Categories");
        ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    /* access modifiers changed from: private */
    public void showFragment(Fragment fragment) {
        ((DLSComponentBrowserActivity) getActivity()).showFragment(fragment);
    }
}
