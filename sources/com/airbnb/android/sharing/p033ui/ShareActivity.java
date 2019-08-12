package com.airbnb.android.sharing.p033ui;

import android.support.p002v7.widget.RecyclerView;
import butterknife.BindView;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.p027n2.components.AirToolbar;

/* renamed from: com.airbnb.android.sharing.ui.ShareActivity */
public class ShareActivity extends AirActivity {
    @BindView
    AirToolbar airToolbar;
    @BindView
    RecyclerView recyclerView;

    /* access modifiers changed from: protected */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r15) {
        /*
            r14 = this;
            r5 = 1
            r8 = 0
            r4 = -1
            super.onCreate(r15)
            int r1 = com.airbnb.android.sharing.C0921R.layout.activity_share
            r14.setContentView(r1)
            butterknife.ButterKnife.bind(r14)
            com.airbnb.n2.components.AirToolbar r1 = r14.airToolbar
            r14.setToolbar(r1)
            android.content.Intent r12 = r14.getIntent()
            java.lang.String r1 = "KEY_RN_ACTIVITY_ARGUMENT"
            android.os.Parcelable r13 = r12.getParcelableExtra(r1)
            com.airbnb.android.sharing.models.ShareArguments r13 = (com.airbnb.android.sharing.models.ShareArguments) r13
            if (r13 == 0) goto L_0x0035
            java.lang.String r11 = r13.entryPoint()
        L_0x0026:
            int r1 = r11.hashCode()
            switch(r1) {
                case -1843293139: goto L_0x007f;
                case -1656948749: goto L_0x0095;
                case -1089247049: goto L_0x0048;
                case -968641083: goto L_0x005e;
                case -222657721: goto L_0x00a1;
                case -85567126: goto L_0x0074;
                case 109770997: goto L_0x0069;
                case 181975684: goto L_0x003d;
                case 573290908: goto L_0x008a;
                case 1878271489: goto L_0x0053;
                case 1879474642: goto L_0x00ad;
                default: goto L_0x002d;
            }
        L_0x002d:
            r1 = r4
        L_0x002e:
            switch(r1) {
                case 0: goto L_0x00ba;
                case 1: goto L_0x010d;
                case 2: goto L_0x0123;
                case 3: goto L_0x0139;
                case 4: goto L_0x014f;
                case 5: goto L_0x015e;
                case 6: goto L_0x017c;
                case 7: goto L_0x0183;
                case 8: goto L_0x018a;
                case 9: goto L_0x0191;
                case 10: goto L_0x01da;
                default: goto L_0x0031;
            }
        L_0x0031:
            r14.finish()
        L_0x0034:
            return
        L_0x0035:
            java.lang.String r1 = "entry_point"
            java.lang.String r11 = r12.getStringExtra(r1)
            goto L_0x0026
        L_0x003d:
            java.lang.String r1 = "listing"
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L_0x002d
            r1 = r8
            goto L_0x002e
        L_0x0048:
            java.lang.String r1 = "listing_photo"
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L_0x002d
            r1 = r5
            goto L_0x002e
        L_0x0053:
            java.lang.String r1 = "listing_screenshot"
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L_0x002d
            r1 = 2
            goto L_0x002e
        L_0x005e:
            java.lang.String r1 = "wishlist"
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L_0x002d
            r1 = 3
            goto L_0x002e
        L_0x0069:
            java.lang.String r1 = "story"
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L_0x002d
            r1 = 4
            goto L_0x002e
        L_0x0074:
            java.lang.String r1 = "experience"
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L_0x002d
            r1 = 5
            goto L_0x002e
        L_0x007f:
            java.lang.String r1 = "guidebook_place"
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L_0x002d
            r1 = 6
            goto L_0x002e
        L_0x008a:
            java.lang.String r1 = "guidebook_insider"
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L_0x002d
            r1 = 7
            goto L_0x002e
        L_0x0095:
            java.lang.String r1 = "guidebook_detour"
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L_0x002d
            r1 = 8
            goto L_0x002e
        L_0x00a1:
            java.lang.String r1 = "place_activity"
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L_0x002d
            r1 = 9
            goto L_0x002e
        L_0x00ad:
            java.lang.String r1 = "playlist"
            boolean r1 = r11.equals(r1)
            if (r1 == 0) goto L_0x002d
            r1 = 10
            goto L_0x002e
        L_0x00ba:
            com.airbnb.android.sharing.shareables.HomeShareable r0 = new com.airbnb.android.sharing.shareables.HomeShareable
            java.lang.String r1 = "listing"
            android.os.Parcelable r2 = r12.getParcelableExtra(r1)
            com.airbnb.android.core.models.Listing r2 = (com.airbnb.android.core.models.Listing) r2
            java.lang.String r1 = "check_in"
            android.os.Parcelable r3 = r12.getParcelableExtra(r1)
            com.airbnb.android.airdate.AirDate r3 = (com.airbnb.android.airdate.AirDate) r3
            java.lang.String r1 = "check_out"
            android.os.Parcelable r4 = r12.getParcelableExtra(r1)
            com.airbnb.android.airdate.AirDate r4 = (com.airbnb.android.airdate.AirDate) r4
            java.lang.String r1 = "guest_details"
            android.os.Parcelable r5 = r12.getParcelableExtra(r1)
            com.airbnb.android.core.models.GuestDetails r5 = (com.airbnb.android.core.models.GuestDetails) r5
            r1 = r14
            r0.<init>(r1, r2, r3, r4, r5)
        L_0x00e4:
            com.airbnb.android.sharing.adapters.ShareAdapter r10 = new com.airbnb.android.sharing.adapters.ShareAdapter
            com.airbnb.android.core.AirbnbPreferences r1 = r14.preferences
            r10.<init>(r14, r0, r1)
            android.support.v7.widget.RecyclerView r1 = r14.recyclerView
            r1.setAdapter(r10)
            com.airbnb.android.core.analytics.NavigationLogging r1 = r14.navigationAnalytics
            com.airbnb.android.core.fragments.NavigationTag r4 = com.airbnb.android.core.fragments.NavigationTag.ShareSheet
            com.airbnb.android.utils.Strap r5 = com.airbnb.android.utils.Strap.make()
            java.lang.String r6 = "entry_point"
            com.airbnb.android.utils.Strap r5 = r5.mo11639kv(r6, r11)
            r1.trackImpressionExplicitly(r4, r5)
            com.airbnb.android.core.AirbnbPreferences r1 = r14.preferences
            android.content.SharedPreferences r1 = r1.getSharedPreferences()
            com.airbnb.android.apprater.AppRaterController.incrementSignificantEvent(r1)
            goto L_0x0034
        L_0x010d:
            com.airbnb.android.sharing.shareables.HomeShareable r0 = new com.airbnb.android.sharing.shareables.HomeShareable
            java.lang.String r1 = "listing"
            android.os.Parcelable r1 = r12.getParcelableExtra(r1)
            com.airbnb.android.core.models.Listing r1 = (com.airbnb.android.core.models.Listing) r1
            java.lang.String r5 = "picture_id"
            int r4 = r12.getIntExtra(r5, r4)
            r0.<init>(r14, r1, r4)
            goto L_0x00e4
        L_0x0123:
            com.airbnb.android.sharing.shareables.HomeShareable r0 = new com.airbnb.android.sharing.shareables.HomeShareable
            java.lang.String r1 = "listing"
            android.os.Parcelable r1 = r12.getParcelableExtra(r1)
            com.airbnb.android.core.models.Listing r1 = (com.airbnb.android.core.models.Listing) r1
            java.lang.String r4 = "screenshot_path"
            java.lang.String r4 = r12.getStringExtra(r4)
            r0.<init>(r14, r1, r4)
            goto L_0x00e4
        L_0x0139:
            com.airbnb.android.sharing.shareables.WishListShareable r0 = new com.airbnb.android.sharing.shareables.WishListShareable
            java.lang.String r1 = "wishlist"
            android.os.Parcelable r1 = r12.getParcelableExtra(r1)
            com.airbnb.android.core.models.WishList r1 = (com.airbnb.android.core.models.WishList) r1
            java.lang.String r4 = "public_share"
            boolean r4 = r12.getBooleanExtra(r4, r5)
            r0.<init>(r14, r1, r4)
            goto L_0x00e4
        L_0x014f:
            com.airbnb.android.sharing.shareables.StoryShareable r0 = new com.airbnb.android.sharing.shareables.StoryShareable
            java.lang.String r1 = "story"
            android.os.Parcelable r1 = r12.getParcelableExtra(r1)
            com.airbnb.android.core.models.Article r1 = (com.airbnb.android.core.models.Article) r1
            r0.<init>(r14, r1)
            goto L_0x00e4
        L_0x015e:
            java.lang.Integer r1 = r13.productType()
            int r1 = r1.intValue()
            com.airbnb.android.core.mt.models.ProductType r4 = com.airbnb.android.core.p008mt.models.C6213ProductType.EXPERIENCE
            int r4 = r4.ordinal()
            if (r1 != r4) goto L_0x0178
            java.lang.String r11 = "experience"
        L_0x0171:
            com.airbnb.android.sharing.shareables.ExperienceShareable r0 = new com.airbnb.android.sharing.shareables.ExperienceShareable
            r0.<init>(r14, r13)
            goto L_0x00e4
        L_0x0178:
            java.lang.String r11 = "immersion"
            goto L_0x0171
        L_0x017c:
            com.airbnb.android.sharing.shareables.PlaceShareable r0 = new com.airbnb.android.sharing.shareables.PlaceShareable
            r0.<init>(r14, r13)
            goto L_0x00e4
        L_0x0183:
            com.airbnb.android.sharing.shareables.InsiderFavoritesShareable r0 = new com.airbnb.android.sharing.shareables.InsiderFavoritesShareable
            r0.<init>(r14, r13)
            goto L_0x00e4
        L_0x018a:
            com.airbnb.android.sharing.shareables.DetourGuidebookShareable r0 = new com.airbnb.android.sharing.shareables.DetourGuidebookShareable
            r0.<init>(r14, r13)
            goto L_0x00e4
        L_0x0191:
            java.lang.String r1 = "activity_is_meetup"
            boolean r1 = r12.getBooleanExtra(r1, r8)
            if (r1 == 0) goto L_0x01d6
            java.lang.String r11 = "meetup"
        L_0x019d:
            java.lang.String r1 = "activity_id"
            int r9 = r12.getIntExtra(r1, r4)
            long r4 = (long) r9
            com.airbnb.android.core.utils.Check.validId(r4)
            com.airbnb.android.sharing.shareables.ActivityShareable r0 = new com.airbnb.android.sharing.shareables.ActivityShareable
            long r2 = (long) r9
            java.lang.String r1 = "activity_base_url"
            java.lang.String r4 = r12.getStringExtra(r1)
            java.lang.String r1 = "activity_picture"
            android.os.Parcelable r5 = r12.getParcelableExtra(r1)
            com.airbnb.android.core.models.Photo r5 = (com.airbnb.android.core.models.Photo) r5
            java.lang.String r1 = "activity_title"
            java.lang.String r6 = r12.getStringExtra(r1)
            java.lang.String r1 = "activity_location"
            java.lang.String r7 = r12.getStringExtra(r1)
            java.lang.String r1 = "activity_is_meetup"
            boolean r8 = r12.getBooleanExtra(r1, r8)
            r1 = r14
            r0.<init>(r1, r2, r4, r5, r6, r7, r8)
            goto L_0x00e4
        L_0x01d6:
            java.lang.String r11 = "activity"
            goto L_0x019d
        L_0x01da:
            java.lang.String r1 = "playlist_id"
            int r1 = r12.getIntExtra(r1, r4)
            long r2 = (long) r1
            com.airbnb.android.sharing.shareables.PlaylistShareable r0 = new com.airbnb.android.sharing.shareables.PlaylistShareable
            java.lang.String r1 = "playlist_base_url"
            java.lang.String r4 = r12.getStringExtra(r1)
            java.lang.String r1 = "playlist_picture"
            android.os.Parcelable r5 = r12.getParcelableExtra(r1)
            com.airbnb.android.core.models.Photo r5 = (com.airbnb.android.core.models.Photo) r5
            java.lang.String r1 = "playlist_title"
            java.lang.String r6 = r12.getStringExtra(r1)
            java.lang.String r1 = "playlist_location"
            java.lang.String r7 = r12.getStringExtra(r1)
            r1 = r14
            r0.<init>(r1, r2, r4, r5, r6, r7)
            goto L_0x00e4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.airbnb.android.sharing.p033ui.ShareActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: protected */
    public boolean hasCustomEnterTransition() {
        return true;
    }
}
