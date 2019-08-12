package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.AutoAirModalLargeActivity;
import com.airbnb.android.lib.views.LinearListView;
import java.util.List;

public class AirbnbCreditBreakdownFragment extends AirFragment {
    private static final String ARG_PRICE_ITEM = "price_item";
    @BindView
    LinearListView mCreditsListView;
    private Price priceItem;

    private static class AirbnbCreditDescriptionAdapter extends ArrayAdapter<Price> {

        private static class ViewHolder {
            final TextView amount;
            final TextView description;

            public ViewHolder(View view) {
                this.description = (TextView) ButterKnife.findById(view, C0880R.C0882id.txt_description);
                this.amount = (TextView) ButterKnife.findById(view, C0880R.C0882id.txt_amount);
            }
        }

        public AirbnbCreditDescriptionAdapter(Context context, List<Price> airbnbCredits) {
            super(context, 0, airbnbCredits);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.list_item_airbnb_credit_description, parent, false);
                convertView.setTag(new ViewHolder(convertView));
            }
            ViewHolder holder = (ViewHolder) convertView.getTag();
            Price giftCredit = (Price) getItem(position);
            holder.description.setText(giftCredit.getLocalizedTitle());
            holder.amount.setText(giftCredit.getTotal().formattedForDisplay());
            return convertView;
        }
    }

    public static Intent intentForPriceItem(Context context, Price priceItem2) {
        Bundle args = new Bundle();
        args.putParcelable(ARG_PRICE_ITEM, priceItem2);
        return AutoAirModalLargeActivity.intentForFragment(context, AirbnbCreditBreakdownFragment.class, args, C0880R.string.airbnb_credit);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.priceItem = (Price) getArguments().getParcelable(ARG_PRICE_ITEM);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_gift_credit, container, false);
        bindViews(view);
        this.mCreditsListView.setAdapter(new AirbnbCreditDescriptionAdapter(getActivity(), this.priceItem.getPriceItems()));
        return view;
    }
}
