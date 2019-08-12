package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.ZenDialog.ZenBuilder;
import com.airbnb.android.core.models.ActionItem;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.utils.ColorizedDrawable;
import java.util.ArrayList;
import java.util.List;

public class ActionItemZenDialogFragment extends ZenDialog {
    private static final String ARG_ACTION_ITEMS = "action_items";
    public static final String ARG_EXTRAS_BUNDLE = "extras_bundle";
    public static final String EXTRA_ACTION_ITEM_ID = "action_item_id";

    private static class ActionItemAdapter extends ArrayAdapter<ActionItem> {
        private final int mResource;

        public ActionItemAdapter(Context context, int resource, List<ActionItem> objects) {
            super(context, resource, objects);
            this.mResource = resource;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            Context context = parent.getContext();
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(this.mResource, parent, false);
                convertView.setTag(new ViewHolder(convertView));
            }
            ViewHolder viewHolder = (ViewHolder) convertView.getTag();
            int colorRes = ((ActionItem) getItem(position)).getIconColorRes();
            int drawableId = ((ActionItem) getItem(position)).getIconRes();
            if (colorRes > 0) {
                viewHolder.mImageView.setImageDrawable(ColorizedDrawable.forIdWithColor(context, drawableId, colorRes));
            } else {
                viewHolder.mImageView.setImageResource(drawableId);
            }
            viewHolder.mTextView.setText(((ActionItem) getItem(position)).getTextString());
            return convertView;
        }
    }

    public static class ViewHolder {
        @BindView
        ImageView mImageView;
        @BindView
        TextView mTextView;

        public ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target2, View source) {
            this.target = target2;
            target2.mImageView = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.action_item_icon, "field 'mImageView'", ImageView.class);
            target2.mTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.action_item_text, "field 'mTextView'", TextView.class);
        }

        public void unbind() {
            ViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.mImageView = null;
            target2.mTextView = null;
        }
    }

    public static ActionItemZenDialogFragment newInstance(int title, ArrayList<ActionItem> actionItems, Bundle extras) {
        return newInstance(title, -1, actionItems, extras);
    }

    public static ActionItemZenDialogFragment newInstance(int title, int subtitle, ArrayList<ActionItem> actionItems, Bundle extras) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_ACTION_ITEMS, actionItems);
        args.putBundle(ARG_EXTRAS_BUNDLE, extras);
        ZenBuilder<ActionItemZenDialogFragment> builder = new ZenBuilder(new ActionItemZenDialogFragment()).withTitle(title).withCancelButton().withListView(args).withoutDividers();
        if (subtitle != -1) {
            builder.withBodyText(subtitle);
        }
        return (ActionItemZenDialogFragment) builder.create();
    }

    /* access modifiers changed from: protected */
    public ListAdapter getListAdapter() {
        return new ActionItemAdapter(getActivity(), C0880R.layout.list_item_action_item_material, getArguments().getParcelableArrayList(ARG_ACTION_ITEMS));
    }

    /* access modifiers changed from: protected */
    public OnItemClickListener getItemClickListener() {
        return ActionItemZenDialogFragment$$Lambda$1.lambdaFactory$(this);
    }

    static /* synthetic */ void lambda$getItemClickListener$0(ActionItemZenDialogFragment actionItemZenDialogFragment, AdapterView parent, View view, int position, long id) {
        actionItemZenDialogFragment.dismiss();
        if (actionItemZenDialogFragment.getTargetFragment() != null) {
            Object item = parent.getAdapter().getItem(position);
            if (item instanceof ActionItem) {
                Intent intent = new Intent();
                intent.putExtra(EXTRA_ACTION_ITEM_ID, ((ActionItem) item).getActionItemId());
                intent.putExtra(ARG_EXTRAS_BUNDLE, actionItemZenDialogFragment.getArguments().getBundle(ARG_EXTRAS_BUNDLE));
                actionItemZenDialogFragment.getTargetFragment().onActivityResult(actionItemZenDialogFragment.getTargetRequestCode(), -1, intent);
            }
        }
    }
}
