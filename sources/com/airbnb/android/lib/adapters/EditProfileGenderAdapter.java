package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.interfaces.EditProfileInterface.Gender;
import com.airbnb.android.lib.C0880R;

public class EditProfileGenderAdapter extends ArrayAdapter<Gender> {
    private final int mSelected;

    class ViewHolder {
        @BindView
        TextView name;
        @BindView
        RadioButton radioButton;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target2, View source) {
            this.target = target2;
            target2.name = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_title, "field 'name'", TextView.class);
            target2.radioButton = (RadioButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.radio_button, "field 'radioButton'", RadioButton.class);
        }

        public void unbind() {
            ViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.name = null;
            target2.radioButton = null;
        }
    }

    public EditProfileGenderAdapter(Context context, int resource, int textViewResourceId, Gender gender) {
        super(context, resource, textViewResourceId, Gender.values());
        this.mSelected = gender != null ? gender.ordinal() : -1;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        boolean z = false;
        if (convertView == null) {
            convertView = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C0880R.layout.list_item_gender, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        holder.name.setText(((Gender) getItem(position)).getDisplayId());
        RadioButton radioButton = holder.radioButton;
        if (position == this.mSelected) {
            z = true;
        }
        radioButton.setChecked(z);
        return convertView;
    }
}
