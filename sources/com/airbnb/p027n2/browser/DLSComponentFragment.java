package com.airbnb.p027n2.browser;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.app.AppCompatActivity;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.support.p002v7.widget.Toolbar;
import android.text.Html;
import android.text.Spannable;
import android.text.Spannable.Factory;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Space;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.DLSMockAdapter;
import com.airbnb.p027n2.components.DLSComponent;
import com.airbnb.p027n2.components.DLSComponents;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.ExpandableTextView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.airbnb.n2.browser.DLSComponentFragment */
public class DLSComponentFragment extends Fragment {
    private final Adapter<BaseViewHolder> adapter = new Adapter<BaseViewHolder>() {
        public int getItemViewType(int position) {
            return (position != 1 || !DLSComponentFragment.this.hasDocumentation) ? 1 : 0;
        }

        public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case 0:
                    return new DocumentationViewHolder(parent);
                case 1:
                    return new ComponentViewHolder(parent);
                default:
                    return null;
            }
        }

        public void onBindViewHolder(BaseViewHolder holder, int position) {
            if (getItemViewType(position) == 1) {
                if (DLSComponentFragment.this.hasDocumentation && position != 0) {
                    position--;
                }
                holder.bind(position);
                return;
            }
            holder.bind(position);
        }

        public int getItemCount() {
            return (DLSComponentFragment.this.hasDocumentation ? 1 : 0) + DLSComponentFragment.this.mockAdapter.getItemCount();
        }
    };
    /* access modifiers changed from: private */
    public DLSComponent<?> component;
    /* access modifiers changed from: private */
    public Spanned documentationSpanned;
    /* access modifiers changed from: private */
    public boolean hasDocumentation;
    /* access modifiers changed from: private */
    public DLSMockAdapter mockAdapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    Toolbar toolbar;

    /* renamed from: com.airbnb.n2.browser.DLSComponentFragment$BaseViewHolder */
    private abstract class BaseViewHolder extends ViewHolder {
        /* access modifiers changed from: 0000 */
        public abstract void bind(int i);

        public BaseViewHolder(View itemView) {
            super(itemView);
        }
    }

    /* renamed from: com.airbnb.n2.browser.DLSComponentFragment$ComponentViewHolder */
    final class ComponentViewHolder extends BaseViewHolder {
        @BindView
        FrameLayout frameLayout;
        @BindView
        AirTextView name;
        @BindView
        Space topSpace;

        private ComponentViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_list_item_dls_component_mock, parent, false));
            ButterKnife.bind(this, this.itemView);
        }

        /* access modifiers changed from: 0000 */
        public void bind(int index) {
            ViewLibUtils.setVisibleIf(this.topSpace, index > 0);
            ViewLibUtils.bindOptionalTextView((TextView) this.name, (CharSequence) DLSComponentFragment.this.mockAdapter.getName(index));
            this.frameLayout.setBackgroundResource(DLSComponentFragment.this.mockAdapter.getStyle(index).backgroundRes());
            View mockView = DLSComponentFragment.this.component.createViewWithDefaultStyle(DLSComponentFragment.this.getContext(), null);
            DLSComponentFragment.this.mockAdapter.bindView(mockView, index);
            this.frameLayout.removeAllViews();
            this.frameLayout.addView(mockView);
        }
    }

    /* renamed from: com.airbnb.n2.browser.DLSComponentFragment$ComponentViewHolder_ViewBinding */
    public final class ComponentViewHolder_ViewBinding implements Unbinder {
        private ComponentViewHolder target;

        public ComponentViewHolder_ViewBinding(ComponentViewHolder target2, View source) {
            this.target = target2;
            target2.topSpace = (Space) Utils.findRequiredViewAsType(source, R.id.component_space_top, "field 'topSpace'", Space.class);
            target2.name = (AirTextView) Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", AirTextView.class);
            target2.frameLayout = (FrameLayout) Utils.findRequiredViewAsType(source, R.id.frame_layout, "field 'frameLayout'", FrameLayout.class);
        }

        public void unbind() {
            ComponentViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.topSpace = null;
            target2.name = null;
            target2.frameLayout = null;
        }
    }

    /* renamed from: com.airbnb.n2.browser.DLSComponentFragment$DocumentationViewHolder */
    final class DocumentationViewHolder extends BaseViewHolder {
        @BindView
        ExpandableTextView expandableTextView;

        private DocumentationViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.n2_list_item_dls_component_documentation, parent, false));
            ButterKnife.bind(this, this.itemView);
        }

        /* access modifiers changed from: 0000 */
        public void bind(int position) {
            this.expandableTextView.setContentText((CharSequence) DLSComponentFragment.this.documentationSpanned);
            this.expandableTextView.setReadMoreText("read more");
        }
    }

    /* renamed from: com.airbnb.n2.browser.DLSComponentFragment$DocumentationViewHolder_ViewBinding */
    public final class DocumentationViewHolder_ViewBinding implements Unbinder {
        private DocumentationViewHolder target;

        public DocumentationViewHolder_ViewBinding(DocumentationViewHolder target2, View source) {
            this.target = target2;
            target2.expandableTextView = (ExpandableTextView) Utils.findRequiredViewAsType(source, R.id.documentation_text, "field 'expandableTextView'", ExpandableTextView.class);
        }

        public void unbind() {
            DocumentationViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.expandableTextView = null;
        }
    }

    public static DLSComponentFragment newInstance(DLSComponent<?> component2) {
        Bundle args = new Bundle();
        args.putString("component_name", component2.name());
        DLSComponentFragment fragment = new DLSComponentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.n2_fragment_dls_component, container, false);
        ButterKnife.bind(this, view);
        String componentName = getArguments().getString("component_name");
        this.toolbar.setTitle((CharSequence) componentName);
        ((AppCompatActivity) getActivity()).setSupportActionBar(this.toolbar);
        this.component = DLSComponents.fromName(componentName);
        this.documentationSpanned = formatDocumentation(this.component.documentation());
        this.hasDocumentation = this.documentationSpanned != null;
        this.mockAdapter = this.component.mockAdapter();
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    private Spanned formatDocumentation(String documentationText) {
        if (TextUtils.isEmpty(documentationText)) {
            return null;
        }
        Spannable documentationSpannable = Factory.getInstance().newSpannable(fromHtml(documentationText.replaceAll("<li>([^<]+)", "<li>• $1<br>").replaceAll("\\{@\\S+\\s+(([^}]+\\.)|#)?([^}]+)\\}", "$3").replaceAll("@see", "<p>@see")));
        Matcher seeMatcher = Pattern.compile("@see (\\S+)").matcher(documentationSpannable);
        while (seeMatcher.find()) {
            String className = seeMatcher.group(1);
            DLSComponent<?>[] all = DLSComponents.all();
            int length = all.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                final DLSComponent<?> component2 = all[i];
                if (component2.name().equals(className)) {
                    documentationSpannable.setSpan(new ClickableSpan() {
                        public void onClick(View view) {
                            DLSComponentFragment.this.showFragment(DLSComponentFragment.newInstance(component2));
                        }
                    }, seeMatcher.start(1), seeMatcher.end(1), 33);
                    break;
                }
                i++;
            }
        }
        return documentationSpannable;
    }

    private static Spanned fromHtml(String text) {
        if (VERSION.SDK_INT >= 24) {
            return Html.fromHtml(text, 0);
        }
        return Html.fromHtml(text);
    }

    /* access modifiers changed from: private */
    public void showFragment(Fragment fragment) {
        ((DLSComponentBrowserActivity) getActivity()).showFragment(fragment);
    }
}
