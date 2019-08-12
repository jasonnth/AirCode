package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.models.SpokenLanguage;
import com.airbnb.android.lib.C0880R;
import java.util.List;

public class SpokenLanguagesAdapter extends ArrayAdapter<SpokenLanguage> {

    static class ViewHolder {
        @BindView
        CheckBox checkBox;
        @BindView
        TextView text;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public class ViewHolder_ViewBinding implements Unbinder {
        private ViewHolder target;

        public ViewHolder_ViewBinding(ViewHolder target2, View source) {
            this.target = target2;
            target2.text = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.title, "field 'text'", TextView.class);
            target2.checkBox = (CheckBox) Utils.findRequiredViewAsType(source, C0880R.C0882id.check_box, "field 'checkBox'", CheckBox.class);
        }

        public void unbind() {
            ViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.text = null;
            target2.checkBox = null;
        }
    }

    public SpokenLanguagesAdapter(Context context, List<SpokenLanguage> languages) {
        super(context, 0, 0, languages);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        SpokenLanguage item = (SpokenLanguage) getItem(position);
        if (convertView == null) {
            convertView = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(C0880R.layout.list_item_text_with_checkbox, parent, false);
            convertView.setTag(new ViewHolder(convertView));
        }
        ViewHolder holder = (ViewHolder) convertView.getTag();
        convertView.setOnClickListener(SpokenLanguagesAdapter$$Lambda$1.lambdaFactory$(item, holder));
        holder.text.setText(item.getLanguage());
        holder.checkBox.setChecked(item.isSpoken());
        return convertView;
    }

    static /* synthetic */ void lambda$getView$0(SpokenLanguage item, ViewHolder holder, View v) {
        item.toggleSpoken();
        holder.checkBox.setChecked(item.isSpoken());
    }
}
