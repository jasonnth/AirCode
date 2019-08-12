package com.airbnb.android.lib.fragments;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import java.util.ArrayList;
import java.util.List;

public class EndpointSelectorDialogFragment extends AirDialogFragment {
    private OnEndpointSelectedListener mListener;

    private static class Endpoint {
        final String title;
        final String url;

        public Endpoint(String title2, String url2) {
            this.title = title2;
            this.url = url2;
        }
    }

    public static class EndpointAdapter extends ArrayAdapter<Endpoint> {
        AirbnbApi mAirbnbApi;
        private final List<Endpoint> mEndpoints;
        private int mSelectedIndex = -1;

        private enum RowType {
            Endpoint,
            Custom
        }

        static class ViewHolder {
            CheckedTextView name;

            ViewHolder() {
            }
        }

        public EndpointAdapter(Context context, int resource, int textViewResourceId, List<Endpoint> endpoints) {
            super(context, resource, textViewResourceId, endpoints);
            ((AirbnbGraph) AirbnbApplication.instance(context).component()).inject(this);
            this.mEndpoints = endpoints;
            String currentEndpoint = AirbnbApi.API_ENDPOINT_URL;
            for (int i = 0; i < this.mEndpoints.size(); i++) {
                if (currentEndpoint.equals(((Endpoint) this.mEndpoints.get(i)).url)) {
                    this.mSelectedIndex = i;
                    return;
                }
            }
        }

        public int getCount() {
            return this.mEndpoints.size() + 1;
        }

        public int getViewTypeCount() {
            return RowType.values().length;
        }

        public int getItemViewType(int position) {
            if (position == this.mEndpoints.size()) {
                return RowType.Custom.ordinal();
            }
            return RowType.Endpoint.ordinal();
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            boolean z;
            if (convertView == null) {
                convertView = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(17367055, parent, false);
                ViewHolder holder = new ViewHolder();
                holder.name = (CheckedTextView) convertView.findViewById(16908308);
                convertView.setTag(holder);
            }
            ViewHolder holder2 = (ViewHolder) convertView.getTag();
            String text = "";
            switch (RowType.values()[getItemViewType(position)]) {
                case Endpoint:
                    Endpoint item = (Endpoint) getItem(position);
                    text = item.title + " (" + item.url + ")";
                    break;
                case Custom:
                    text = getContext().getString(C0880R.string.debug_menu_enter_custom_endpoint);
                    break;
            }
            holder2.name.setText(text);
            CheckedTextView checkedTextView = holder2.name;
            if (position == this.mSelectedIndex) {
                z = true;
            } else {
                z = false;
            }
            checkedTextView.setChecked(z);
            return convertView;
        }
    }

    public interface OnEndpointSelectedListener {
        void onCustomEndpointSelected();

        void onEndpointSelected(String str);
    }

    public static EndpointSelectorDialogFragment newInstance() {
        EndpointSelectorDialogFragment f = new EndpointSelectorDialogFragment();
        f.setArguments(new Bundle());
        return f;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        List<Endpoint> endpoints = new ArrayList<>();
        endpoints.add(new Endpoint("Production", AirbnbApi.PROD_API_URL));
        endpoints.add(new Endpoint("Next", AirbnbApi.NEXT_API_URL));
        endpoints.add(new Endpoint("Saturn5/Vagrant", "genymotion".equalsIgnoreCase(Build.MANUFACTURER) ? AirbnbApi.GENYMOTION_LOCALHOST_API_URL : AirbnbApi.ANDROID_LOCALHOST_AVD_API_URL));
        return new Builder(getActivity()).setTitle(C0880R.string.debug_menu_select_server).setAdapter(new EndpointAdapter(getActivity(), 0, 0, endpoints), EndpointSelectorDialogFragment$$Lambda$1.lambdaFactory$(this, endpoints)).create();
    }

    static /* synthetic */ void lambda$onCreateDialog$0(EndpointSelectorDialogFragment endpointSelectorDialogFragment, List endpoints, DialogInterface dialog, int item) {
        if (endpointSelectorDialogFragment.mListener != null) {
            if (item < endpoints.size()) {
                endpointSelectorDialogFragment.mListener.onEndpointSelected(((Endpoint) endpoints.get(item)).url);
            } else {
                endpointSelectorDialogFragment.mListener.onCustomEndpointSelected();
            }
        }
    }

    public void setListener(OnEndpointSelectedListener listener) {
        this.mListener = listener;
    }
}
