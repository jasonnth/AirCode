package com.airbnb.android.lib.responses;

import android.content.Context;
import android.content.Intent;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.superhero.DestinationType;
import com.airbnb.android.superhero.SuperHeroAction;
import com.airbnb.android.superhero.SuperHeroBundleUtil;
import com.airbnb.android.superhero.SuperHeroMessage;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import java.util.ArrayList;

public class ChinaCampaignCouponClaimResponse extends BaseResponse {
    @JsonProperty("china_campaign_coupon_claim_operation")
    public Result result;

    private static class Result {
        @JsonProperty("messages")
        ArrayList<String> messages;
        @JsonProperty("success")
        boolean success;

        private Result() {
        }
    }

    public Intent getCouponClaimConfirmationIntent(Context context) {
        if (!this.result.success) {
            return null;
        }
        SuperHeroAction action = SuperHeroAction.builder().mo11521id(-110).destination_type(DestinationType.DEEPLINK.value).destination("airbnb://discover").text(context.getString(C0880R.string.enter_airbnb)).build();
        return HomeActivityIntents.intentForSuperHero(context, SuperHeroBundleUtil.from(SuperHeroMessage.builder().mo11547id(-110).status(0).hero_actions(Lists.newArrayList((E[]) new SuperHeroAction[]{action})).messages(this.result.messages).should_takeover(true).build()));
    }
}
