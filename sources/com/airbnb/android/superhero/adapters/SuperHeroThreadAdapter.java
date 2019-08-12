package com.airbnb.android.superhero.adapters;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.InlineContextEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.InlineContextEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MessageItemEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.MessageItemEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SuperHeroActionEpoxyModel_;
import com.airbnb.android.superhero.C1713R;
import com.airbnb.android.superhero.SuperHeroAction;
import com.airbnb.android.superhero.SuperHeroDataController;
import com.airbnb.android.superhero.SuperHeroMessage;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.epoxy.EpoxyModel;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SuperHeroThreadAdapter extends AirEpoxyAdapter {
    private final Context context;
    private SuperHeroDataController dataController;

    public SuperHeroThreadAdapter(Context context2, SuperHeroDataController dataController2) {
        enableDiffing();
        setFilterDuplicates(true);
        this.context = context2;
        this.dataController = dataController2;
    }

    public int getModelPosition(EpoxyModel<?> model) {
        return super.getModelPosition(model);
    }

    public void setSuperHeroMessages(Collection<SuperHeroMessage> superHeroMessages) {
        this.models.clear();
        AirDateTime now = new AirDateTime(System.currentTimeMillis());
        for (SuperHeroMessage superHeroMessage : superHeroMessages) {
            List<String> messageList = superHeroMessage.messages();
            boolean expired = superHeroMessage.hasExpired(now);
            int size = messageList.size();
            int i = 0;
            while (i < size) {
                if (i == 0) {
                    this.models.add(createSuperHeroMessageStatus(superHeroMessage, expired));
                }
                this.models.add(createSuperHeroSingleMessage((String) messageList.get(i), i == size + -1, superHeroMessage, i));
                i++;
            }
            if (!expired) {
                List<SuperHeroAction> superHeroActions = superHeroMessage.hero_actions();
                if (!ListUtils.isEmpty((Collection<?>) superHeroActions)) {
                    for (SuperHeroAction action : superHeroActions) {
                        this.models.add(createSuperHeroSingleAction(action, superHeroMessage));
                    }
                }
            }
        }
        notifyModelsChanged();
    }

    private InlineContextEpoxyModel createSuperHeroMessageStatus(SuperHeroMessage superHeroMessage, boolean expired) {
        String statusString;
        String dateString = superHeroMessage.starts_at().getElapsedTime(this.context);
        if (expired) {
            statusString = String.format(this.context.getString(C1713R.string.superhero_expired_message_status), new Object[]{dateString});
        } else {
            statusString = dateString;
        }
        return new InlineContextEpoxyModel_().m4859id(superHeroMessage.mo11531id(), superHeroMessage.starts_at().getMillis()).status(statusString);
    }

    private MessageItemEpoxyModel createSuperHeroSingleMessage(String message, boolean showProfileImage, SuperHeroMessage superHeroMessage, int index) {
        MessageItemEpoxyModel_ messageModel = new MessageItemEpoxyModel_().m5149id((CharSequence) superHeroMessage.mo11531id() + "_" + superHeroMessage.starts_at().getMillis(), (long) index).messageText(message);
        if (showProfileImage) {
            messageModel.profileImageRes(C1713R.C1714drawable.super_hero_profile).receiverWithTail();
        } else {
            messageModel.receiverNoTail();
        }
        return messageModel;
    }

    private SuperHeroActionEpoxyModel_ createSuperHeroSingleAction(SuperHeroAction superHeroAction, SuperHeroMessage superHeroMessage) {
        return new SuperHeroActionEpoxyModel_().text(superHeroAction.text()).m5664id((CharSequence) getUniqueSuperHeroActionId(superHeroAction, superHeroMessage)).actionOnClickListener(SuperHeroThreadAdapter$$Lambda$1.lambdaFactory$(this, superHeroAction));
    }

    private String getUniqueSuperHeroActionId(SuperHeroAction action, SuperHeroMessage message) {
        return TextUtils.join("_", Arrays.asList(new Serializable[]{Long.valueOf(message.mo11531id()), Long.valueOf(message.starts_at().getMillis()), Long.valueOf(action.mo11513id()), action.text()}));
    }
}
