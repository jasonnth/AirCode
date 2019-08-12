package com.airbnb.android.lib.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.models.AirViewType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.ListingUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SpannableUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.ListingSelectDialogFragment.ListingsLoadedCallback;
import com.airbnb.rxgroups.RequestSubscription;
import java.util.ArrayList;
import java.util.List;

public class ListingsRowAdapter extends BaseListingAdapter {
    /* access modifiers changed from: private */
    public final ListingsLoadedCallback callback;
    private final User currentUser;
    private final DescriptionType descriptionType;
    /* access modifiers changed from: private */
    public RequestSubscription mySpacesCall;
    private final boolean noPressedState;

    public enum DescriptionType {
        WITH_ROOMTYPE,
        WITH_ROOMTYPE_AND_LIST_STATUS,
        WITH_ROOMTYPE_AND_LIST_STATUS_MINI,
        WITH_REVIEW_COUNT_AND_PRICE,
        USER_PROFILE
    }

    public static ListingsRowAdapter forCurrentUser(User currentUser2, List<Listing> listings, ListingsLoadedCallback callback2) {
        boolean shouldPaginate;
        if (listings.size() < currentUser2.getTotalListingsCount()) {
            shouldPaginate = true;
        } else {
            shouldPaginate = false;
        }
        return new ListingsRowAdapter(listings, DescriptionType.WITH_ROOMTYPE_AND_LIST_STATUS_MINI, false, shouldPaginate, currentUser2, callback2);
    }

    public ListingsRowAdapter(List<Listing> listings, DescriptionType type) {
        this(listings, type, false, false, null, null);
    }

    public ListingsRowAdapter(List<Listing> listings, DescriptionType type, boolean noPressedState2, boolean paginate, User currentUser2, ListingsLoadedCallback callback2) {
        super(paginate);
        this.descriptionType = type;
        this.noPressedState = noPressedState2;
        this.currentUser = currentUser2;
        this.callback = callback2;
        setData(listings);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (getItemViewType(position) == AirViewType.LOADING.ordinal()) {
            loadMoreListings();
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.list_item_loading, parent, false);
            }
            return convertView;
        }
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.list_item_listing_short, parent, false);
        }
        if (this.noPressedState) {
            ButterKnife.findById(convertView, C0880R.C0882id.list_item_root).setBackgroundResource(0);
        }
        return setupListingRow(convertView, (Listing) getItem(position), this.descriptionType);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0066, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0079, code lost:
        r0 = getTextWithRoomTypeStatus(r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x008b, code lost:
        r0 = r8.getNumReviewsText(r1);
        r4 = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0035, code lost:
        ((android.widget.TextView) butterknife.ButterKnife.findById(r7, com.airbnb.android.lib.C0880R.C0882id.txt_listing_info)).setText(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0040, code lost:
        if (r4 == false) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0042, code lost:
        r3 = (android.widget.TextView) butterknife.ButterKnife.findById(r7, com.airbnb.android.lib.C0880R.C0882id.txt_listing_price);
        r3.setText(com.airbnb.android.core.utils.SearchUtil.getPriceTagText(r8));
        r3.setVisibility(0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0057, code lost:
        if (r9 != com.airbnb.android.lib.adapters.ListingsRowAdapter.DescriptionType.USER_PROFILE) goto L_0x0066;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0059, code lost:
        r3.setTextColor(r1.getResources().getColor(com.airbnb.android.lib.C0880R.color.c_babu));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.view.View setupListingRow(android.view.View r7, com.airbnb.android.core.models.Listing r8, com.airbnb.android.lib.adapters.ListingsRowAdapter.DescriptionType r9) {
        /*
            int r5 = com.airbnb.android.lib.C0880R.C0882id.img_list_item
            android.view.View r2 = butterknife.ButterKnife.findById(r7, r5)
            com.airbnb.n2.primitives.imaging.AirImageView r2 = (com.airbnb.p027n2.primitives.imaging.AirImageView) r2
            int r5 = r8.getPictureCount()
            if (r5 <= 0) goto L_0x0067
            java.lang.String r5 = r8.getPictureUrl()
            r2.setImageUrl(r5)
        L_0x0015:
            android.content.Context r1 = r7.getContext()
            int r5 = com.airbnb.android.lib.C0880R.C0882id.txt_listing_name
            android.view.View r5 = butterknife.ButterKnife.findById(r7, r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            java.lang.String r6 = com.airbnb.android.core.utils.listing.ListingDisplayUtils.getNameOrPlaceholder(r1, r8)
            r5.setText(r6)
            r0 = 0
            r4 = 0
            int[] r5 = com.airbnb.android.lib.adapters.ListingsRowAdapter.C66962.f9490xf6248c2d
            int r6 = r9.ordinal()
            r5 = r5[r6]
            switch(r5) {
                case 1: goto L_0x0071;
                case 2: goto L_0x0076;
                case 3: goto L_0x0079;
                case 4: goto L_0x007e;
                case 5: goto L_0x008b;
                default: goto L_0x0035;
            }
        L_0x0035:
            int r5 = com.airbnb.android.lib.C0880R.C0882id.txt_listing_info
            android.view.View r5 = butterknife.ButterKnife.findById(r7, r5)
            android.widget.TextView r5 = (android.widget.TextView) r5
            r5.setText(r0)
            if (r4 == 0) goto L_0x0066
            int r5 = com.airbnb.android.lib.C0880R.C0882id.txt_listing_price
            android.view.View r3 = butterknife.ButterKnife.findById(r7, r5)
            android.widget.TextView r3 = (android.widget.TextView) r3
            java.lang.String r5 = com.airbnb.android.core.utils.SearchUtil.getPriceTagText(r8)
            r3.setText(r5)
            r5 = 0
            r3.setVisibility(r5)
            com.airbnb.android.lib.adapters.ListingsRowAdapter$DescriptionType r5 = com.airbnb.android.lib.adapters.ListingsRowAdapter.DescriptionType.USER_PROFILE
            if (r9 != r5) goto L_0x0066
            android.content.res.Resources r5 = r1.getResources()
            int r6 = com.airbnb.android.lib.C0880R.color.c_babu
            int r5 = r5.getColor(r6)
            r3.setTextColor(r5)
        L_0x0066:
            return r7
        L_0x0067:
            r5 = 0
            r2.setImageUrl(r5)
            int r5 = com.airbnb.android.lib.C0880R.C0881drawable.default_photo_icon
            r2.setImageResource(r5)
            goto L_0x0015
        L_0x0071:
            java.lang.CharSequence r0 = getTextWithRoomType(r1, r8)
            goto L_0x0035
        L_0x0076:
            makeMiniMe(r7)
        L_0x0079:
            java.lang.CharSequence r0 = getTextWithRoomTypeStatus(r8)
            goto L_0x0035
        L_0x007e:
            android.content.res.Resources r5 = r1.getResources()
            int r6 = com.airbnb.android.lib.C0880R.color.c_foggy_white
            int r5 = r5.getColor(r6)
            r7.setBackgroundColor(r5)
        L_0x008b:
            java.lang.CharSequence r0 = r8.getNumReviewsText(r1)
            r4 = 1
            goto L_0x0035
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.lib.adapters.ListingsRowAdapter.setupListingRow(android.view.View, com.airbnb.android.core.models.Listing, com.airbnb.android.lib.adapters.ListingsRowAdapter$DescriptionType):android.view.View");
    }

    private static CharSequence getTextWithRoomType(Context context, Listing listing) {
        return listing.getRoomType(context);
    }

    private static CharSequence getTextWithRoomTypeStatus(Listing listing) {
        Context context = AirbnbApplication.appContext();
        List<CharSequence> description = new ArrayList<>();
        Pair<String, Integer> statusAndColor = ListingUtils.getListingStatusAndColor(context, listing);
        String listStatus = (String) statusAndColor.first;
        int listStatusColor = ((Integer) statusAndColor.second).intValue();
        description.add(SpannableUtils.COLORED_SUBSTRING_TOKEN);
        description.add(listing.getRoomType());
        return SpannableUtils.makeColoredSubstring(TextUtils.join(context.getString(C0880R.string.bullet_with_space), description), listStatus, listStatusColor);
    }

    private static void makeMiniMe(View inflatedView) {
        Resources r = inflatedView.getContext().getResources();
        View listingPic = inflatedView.findViewById(C0880R.C0882id.img_list_item);
        int height = r.getDimensionPixelSize(C0880R.dimen.small_listing_thumb);
        LayoutParams params = listingPic.getLayoutParams();
        params.height = height;
        params.width = (int) (((float) height) / 0.6666667f);
        listingPic.setLayoutParams(params);
        ((TextView) ButterKnife.findById(inflatedView, C0880R.C0882id.txt_listing_name)).setSingleLine(true);
    }

    public int getItemViewType(int position) {
        if (!hasRemainingData() || position != getCount() - 1) {
            return AirViewType.LISTING.ordinal();
        }
        return AirViewType.LOADING.ordinal();
    }

    public void cancelAdditionalLoading() {
        if (this.mySpacesCall != null) {
            this.mySpacesCall.cancel();
        }
    }

    public int getViewTypeCount() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public void loadMoreListings() {
        if (this.currentUser == null) {
            if (BuildHelper.isDevelopmentBuild()) {
                throw new IllegalStateException("currentUser is null when trying to load more listings");
            }
            setRemainingData(false);
        } else if (this.mySpacesCall == null) {
            this.mySpacesCall = ListingRequest.forMySpaces(this.currentUser.getId(), false, 10, this.mListings.size(), new NonResubscribableRequestListener<ListingResponse>() {
                public void onResponse(ListingResponse response) {
                    if (response.getListings().isEmpty()) {
                        ListingsRowAdapter.this.setRemainingData(false);
                    } else {
                        ListingsRowAdapter.this.addListings(response.getListings());
                        if (response.getListings().size() < 10) {
                            ListingsRowAdapter.this.setRemainingData(false);
                        }
                        if (ListingsRowAdapter.this.callback != null) {
                            ListingsRowAdapter.this.callback.onListingsLoaded(response.getListings());
                        }
                    }
                    ListingsRowAdapter.this.mySpacesCall = null;
                }

                public void onErrorResponse(AirRequestNetworkException e) {
                    ListingsRowAdapter.this.setRemainingData(false);
                    ListingsRowAdapter.this.mySpacesCall = null;
                }
            }).singleResponse().execute(NetworkUtil.singleFireExecutor());
        }
    }
}
