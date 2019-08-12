package com.airbnb.android.superhero;

import android.os.Bundle;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.android.superhero.SuperHeroMessage.TriggerType;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.jitney.event.logging.Hero.p110v1.HeroShowHeroThreadEvent;
import com.airbnb.jitney.event.logging.Hero.p111v2.HeroDismissHeroEvent;
import com.airbnb.jitney.event.logging.Hero.p112v3.HeroCacheHeroEvent.Builder;
import com.airbnb.jitney.event.logging.Hero.p112v3.HeroClickHeroEvent;
import com.airbnb.jitney.event.logging.Hero.p112v3.HeroScheduleHeroEvent;
import com.airbnb.jitney.event.logging.Hero.p112v3.HeroShowHeroEvent;
import com.airbnb.jitney.event.logging.HeroContext.p113v1.C2205HeroContext;
import com.google.common.collect.FluentIterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class SuperHeroJitneyLogger extends BaseLogger {
    public SuperHeroJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    /* access modifiers changed from: 0000 */
    public void cacheHero(SuperHeroMessage message) {
        publish(new Builder(context(), "", "", "", getHeroContext(message)));
    }

    /* access modifiers changed from: 0000 */
    public void scheduleHero(SuperHeroMessage message) {
        publish(new HeroScheduleHeroEvent.Builder(context(), "", "", "", getHeroContext(message)));
    }

    /* access modifiers changed from: 0000 */
    public void showHero(Bundle superHeroBundle) {
        publish(new HeroShowHeroEvent.Builder(context(), "", "", "", getHeroContext(superHeroBundle)));
    }

    /* access modifiers changed from: 0000 */
    public void dismissHero(Bundle superHeroBundle) {
        publish(new HeroDismissHeroEvent.Builder(context(), "", "", getHeroContext(superHeroBundle)));
    }

    /* access modifiers changed from: 0000 */
    public void showHeroThread(Collection<SuperHeroMessage> messages) {
        publish(new HeroShowHeroThreadEvent.Builder(context(), FluentIterable.from((Iterable<E>) messages).transform(SuperHeroJitneyLogger$$Lambda$1.lambdaFactory$(this)).toList()));
    }

    /* access modifiers changed from: 0000 */
    public void clickHero(Bundle superHeroBundle) {
        publish(new HeroClickHeroEvent.Builder(context(), "", "", "", getHeroContext(superHeroBundle)));
    }

    /* access modifiers changed from: private */
    public C2205HeroContext getHeroContext(SuperHeroMessage message) {
        List<String> buttonTexts;
        boolean z = false;
        ArrayList<SuperHeroAction> superHeroActions = message.hero_actions();
        if (ListUtils.isEmpty((Collection<?>) superHeroActions)) {
            buttonTexts = new ArrayList<>(0);
        } else {
            buttonTexts = FluentIterable.from((Iterable<E>) superHeroActions).transform(SuperHeroJitneyLogger$$Lambda$2.lambdaFactory$()).toList();
        }
        AirDateTime startsAtAirDateTime = message.starts_at();
        long startsAtLong = startsAtAirDateTime != null ? startsAtAirDateTime.getMillis() : 0;
        C2205HeroContext.Builder button_texts = new C2205HeroContext.Builder(Long.valueOf(message.mo11531id())).hero_type(message.hero_type_string()).text(TextUtils.join("\n", message.messages())).alert_text(message.firstMessage()).button_texts(buttonTexts);
        if (message.triggerTypeEnum() == TriggerType.LOCAL) {
            z = true;
        }
        return button_texts.is_timed(Boolean.valueOf(z)).time_scheduled(Long.valueOf(startsAtLong)).build();
    }

    private C2205HeroContext getHeroContext(Bundle superHeroBundle) {
        return new C2205HeroContext.Builder(Long.valueOf(SuperHeroBundleUtil.m1449id(superHeroBundle))).build();
    }
}
