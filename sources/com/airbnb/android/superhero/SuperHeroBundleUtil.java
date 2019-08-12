package com.airbnb.android.superhero;

import android.os.Bundle;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;

public class SuperHeroBundleUtil {
    private static final String DISMISS_TEXT_ARG = "dismissText";
    private static final String FULL_SIZE_ARG = "fullSize";
    private static final String HERO_ACTIONS_ARG = "heroActions";
    public static final String HERO_MESSAGE_ARG = "heroMessage";
    public static final String MESSAGES_ARG = "messages";
    private static final String MESSAGE_ID_ARG = "messageID";
    private static final String SHOULD_TAKEOVER_ARG = "shouldTakeover";

    private SuperHeroBundleUtil() {
    }

    public static Bundle from(SuperHeroMessage message) {
        return ((BundleBuilder) new BundleBuilder().putBundle(HERO_MESSAGE_ARG, ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putLong(MESSAGE_ID_ARG, message.mo11531id())).putStringArrayList("messages", Lists.newArrayList((Iterable<? extends E>) message.messages()))).putParcelableArrayList(HERO_ACTIONS_ARG, Lists.newArrayList((Iterable<? extends E>) !ListUtils.isEmpty((Collection<?>) message.hero_actions()) ? FluentIterable.from((Iterable<E>) message.hero_actions()).transform(SuperHeroBundleUtil$$Lambda$1.lambdaFactory$()).toList() : new ArrayList<>()))).putString(DISMISS_TEXT_ARG, message.dismiss_text())).putBoolean(SHOULD_TAKEOVER_ARG, message.should_takeover())).putBoolean(FULL_SIZE_ARG, true)).toBundle())).toBundle();
    }

    static Bundle test() {
        Check.state(BuildHelper.isInternalSettingsEnabled());
        SuperHeroAction action = SuperHeroAction.builder().mo11521id(-111).destination("888 Brannan St").destination_type(Long.valueOf(0)).text("Get directions").build();
        return from(SuperHeroMessage.builder().mo11547id(-111).status(0).hero_actions(Lists.newArrayList((E[]) new SuperHeroAction[]{action})).messages(Lists.newArrayList((E[]) new String[]{"Hey Emily", "How is debugging going?"})).dismiss_text("Not right now").should_takeover(true).build());
    }

    /* renamed from: id */
    static long m1449id(Bundle bundle) {
        return heroMessage(bundle).getLong(MESSAGE_ID_ARG);
    }

    static String firstMessage(Bundle bundle) {
        return (String) messages(bundle).get(0);
    }

    private static ArrayList<String> messages(Bundle bundle) {
        return heroMessage(bundle).getStringArrayList("messages");
    }

    static boolean shouldTakeover(Bundle bundle) {
        return heroMessage(bundle).getBoolean(SHOULD_TAKEOVER_ARG);
    }

    private static Bundle heroMessage(Bundle bundle) {
        return bundle.getBundle(HERO_MESSAGE_ARG);
    }
}
