package com.airbnb.android.core.instant_promo;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.erf.ErfAnalytics;
import com.airbnb.android.core.erf.ErfExperiment;
import com.airbnb.android.core.instant_promo.models.InstantPromotion;
import com.airbnb.android.core.instant_promo.models.InstantPromotion.SurfaceType;
import com.airbnb.android.core.instant_promo.models.InstantPromotion.TemplateType;
import com.airbnb.android.core.instant_promo.models.InstantPromotionContent;
import com.airbnb.android.core.viewcomponents.models.MarketingCardEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.MarketingCard;
import com.airbnb.p027n2.utils.DebouncedOnClickListener;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class InstantPromotionManager {
    /* access modifiers changed from: private */
    public final AirbnbAccountManager accountManager;
    private final ErfAnalytics erfAnalytics;

    private static class PromoConfig {
        final Set<TemplateType> supportedTemplates;
        final SurfaceType surfaceType;

        private PromoConfig(Set<TemplateType> supportedTemplates2, SurfaceType surfaceType2) {
            this.supportedTemplates = supportedTemplates2;
            this.surfaceType = surfaceType2;
        }

        /* access modifiers changed from: 0000 */
        public boolean supportsInstantPromo(InstantPromotion promotion) {
            return this.surfaceType.equals(promotion.getSurface()) && this.supportedTemplates.contains(promotion.getTemplate());
        }
    }

    public InstantPromotionManager(ErfAnalytics erfAnalytics2, AirbnbAccountManager accountManager2) {
        this.erfAnalytics = erfAnalytics2;
        this.accountManager = accountManager2;
    }

    public List<EpoxyModel<?>> getForYouTabEpoxyModelsAndLogErfExposure(List<InstantPromotion> promotions) {
        InstantPromotion promotion = getInstantPromoForConfigAndLogErfExposure(promotions, new PromoConfig(ImmutableSet.m1299of(TemplateType.SimpleMediaTabUnit), SurfaceType.ForYouTab));
        if (promotion != null) {
            if (promotion.getContent() != null) {
                switch (promotion.getTemplate()) {
                    case SimpleMediaTabUnit:
                        InstantPromotionAnalytics.trackPromoImpression(promotion, this.accountManager.getCurrentUserId());
                        return ImmutableList.m1286of(getHeaderModel(promotion), getMarketingCardEpoxyModel(promotion));
                    default:
                        BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Unsupported template for promotion: " + promotion.getId()));
                        break;
                }
            } else {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Promotion does not have content: " + promotion.getId()));
                return new ArrayList();
            }
        }
        return new ArrayList();
    }

    private EpoxyModel<?> getHeaderModel(InstantPromotion promotion) {
        return new MicroSectionHeaderEpoxyModel_().title(promotion.getContent().getSectionTitle()).sectionId(promotion.getContent().getSectionTitle()).showDivider(false).m5172id((CharSequence) promotion.getContent().getSectionTitle());
    }

    private EpoxyModel<MarketingCard> getMarketingCardEpoxyModel(final InstantPromotion promotion) {
        MarketingCardEpoxyModel_ model = new MarketingCardEpoxyModel_();
        final InstantPromotionContent content = promotion.getContent();
        model.imageUrl(content.getImageUrl());
        model.title((CharSequence) content.getTitle());
        model.subtitle((CharSequence) content.getCaption());
        model.action((CharSequence) content.getPrimaryButtonText());
        model.actionClickListener((OnClickListener) new DebouncedOnClickListener() {
            public void onClickDebounced(View v) {
                InstantPromotionAnalytics.trackClickCTA(promotion, InstantPromotionManager.this.accountManager.getCurrentUserId());
                v.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(content.getPrimaryButtonUrl())));
            }
        });
        return model;
    }

    private InstantPromotion getInstantPromoForConfigAndLogErfExposure(List<InstantPromotion> promotions, PromoConfig config) {
        if (ListUtils.isEmpty((Collection<?>) promotions)) {
            return null;
        }
        Collections.sort(promotions, InstantPromotionManager$$Lambda$1.lambdaFactory$());
        for (InstantPromotion promotion : promotions) {
            if (!config.supportsInstantPromo(promotion)) {
                InstantPromotionAnalytics.trackUnknownTemplateType(promotion);
            } else {
                String erfExperimentForLogging = promotion.getErf();
                String erfTreatmentForLogging = promotion.getErfTreatment();
                if (!TextUtils.isEmpty(erfExperimentForLogging) && !TextUtils.isEmpty(erfTreatmentForLogging)) {
                    this.erfAnalytics.logExperiment(new ErfExperiment(erfExperimentForLogging, erfTreatmentForLogging, Collections.emptyList(), "user"), erfTreatmentForLogging);
                }
                if (!promotion.isShowPromo()) {
                    return null;
                }
                return promotion;
            }
        }
        return null;
    }

    static /* synthetic */ int lambda$getInstantPromoForConfigAndLogErfExposure$0(InstantPromotion lhs, InstantPromotion rhs) {
        return lhs.getPriority() - rhs.getPriority();
    }
}
