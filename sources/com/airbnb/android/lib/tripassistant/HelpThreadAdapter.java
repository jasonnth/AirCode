package com.airbnb.android.lib.tripassistant;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.Attachment;
import com.airbnb.android.core.models.HelpThread;
import com.airbnb.android.core.models.HelpThreadDisplayElement;
import com.airbnb.android.core.models.HelpThreadDisplayElement.Type;
import com.airbnb.android.core.models.HelpThreadIssue;
import com.airbnb.android.core.models.HelpThreadNode;
import com.airbnb.android.core.models.HelpThreadOption;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.webintent.WebIntentMatcherResult;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.utils.webintent.WebIntentMatcherUtil;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.primitives.messaging.MessageItem.MessageItemURLClickHandler;
import com.facebook.common.internal.ImmutableList;
import java.util.ArrayList;
import java.util.List;
import p030in.uncod.android.bypass.Bypass;

public class HelpThreadAdapter extends AirEpoxyAdapter {
    private final AttachmentsProvider attachmentsProvider;
    private final Bypass bypass;
    /* access modifiers changed from: private */
    public final User currentUser;
    private final HelpThreadAdapterListener helpThreadAdapterListener;
    private final HelpThreadLoadingEpoxyModel loadingModel = new HelpThreadLoadingEpoxyModel_();
    private final MessageItemURLClickHandler messageItemURLClickHandler = new MessageItemURLClickHandler() {
        public void onURLClick(Context context, String url) {
            Intent intent;
            if (url.startsWith("mailto:") || url.startsWith("tel:")) {
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(url));
                if (!context.getPackageManager().queryIntentActivities(intent2, 0).isEmpty()) {
                    context.startActivity(intent2);
                }
            } else if (DeepLinkUtils.isDeepLink(url)) {
                DeepLinkUtils.startActivityForDeepLink(context, url);
            } else {
                WebIntentMatcherResult result = WebIntentMatcherUtil.getMatch(context, Uri.parse(url), HelpThreadAdapter.this.currentUser);
                if (result.hasIntentMatch()) {
                    intent = result.getIntent();
                } else {
                    intent = WebViewIntentBuilder.newBuilder(context, url).title(C0880R.string.airbnb_help).authenticate().toIntent();
                }
                context.startActivity(intent);
            }
        }
    };

    public interface HelpThreadAdapterListener {
        void onDialogLinkElementClicked(HelpThreadDisplayElement helpThreadDisplayElement);

        void onOptionSelected(NodeSelection nodeSelection);
    }

    public HelpThreadAdapter(Context context, Bypass bypass2, HelpThreadAdapterListener helpThreadAdapterListener2, AttachmentsProvider attachmentsProvider2, User currentUser2) {
        this.bypass = bypass2;
        this.helpThreadAdapterListener = helpThreadAdapterListener2;
        this.attachmentsProvider = attachmentsProvider2;
        this.currentUser = currentUser2;
        enableDiffing();
    }

    public void updateThread(HelpThread helpThread, boolean isLoadingThread) {
        this.models.clear();
        if (helpThread != null) {
            addModelsForThread(helpThread);
        }
        if (isLoadingThread) {
            this.models.add(this.loadingModel);
        }
        notifyModelsChanged();
    }

    public void updateAttachments() {
        for (EpoxyModel<?> model : this.models) {
            if (model instanceof HelpOptionEpoxyModel) {
                HelpOptionEpoxyModel optionModel = (HelpOptionEpoxyModel) model;
                if (optionModel.shouldHaveAttachments()) {
                    List<Attachment> attachments = this.attachmentsProvider.getAttachmentsForIssue(optionModel.getIssue());
                    if (!optionModel.hasSameAttachments(attachments)) {
                        optionModel.attachments(attachments);
                        notifyModelChanged(optionModel);
                    }
                }
            }
        }
    }

    private void addModelsForThread(HelpThread helpThread) {
        for (HelpThreadIssue issue : helpThread.getIssues()) {
            for (HelpThreadNode node : issue.getNodes()) {
                addModelsForNode(helpThread, issue, node);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addModelsForNode(HelpThread helpThread, HelpThreadIssue issue, HelpThreadNode node) {
        if (!node.isInvisibleNode()) {
            addDisplayElements(issue, node);
            addHelpOptions(helpThread, issue, node);
        }
    }

    private void addDisplayElements(HelpThreadIssue issue, HelpThreadNode node) {
        boolean z;
        boolean z2;
        if (!node.getThingsToDisplay().isEmpty()) {
            z = true;
        } else {
            z = false;
        }
        Check.state(z, "Display elements should not be empty");
        List<HelpThreadDisplayElement> group = new ArrayList<>();
        boolean hasLink = false;
        for (HelpThreadDisplayElement displayElement : node.getThingsToDisplay()) {
            if (!displayElement.isMerge().booleanValue()) {
                addDisplayElementGroup(issue, node, group, hasLink);
                group.clear();
                hasLink = false;
            }
            group.add(displayElement);
            if (displayElement.getType() == Type.LinkAndDialog) {
                z2 = true;
            } else {
                z2 = false;
            }
            hasLink |= z2;
        }
        addDisplayElementGroup(issue, node, group, hasLink);
        ((DisplayElementGroupEpoxyModel) this.models.get(this.models.size() - 1)).withTail(true).time(node.getCreatedAt());
    }

    private void addDisplayElementGroup(HelpThreadIssue issue, HelpThreadNode node, List<HelpThreadDisplayElement> group, boolean hasLink) {
        if (!group.isEmpty()) {
            DisplayElementGroupEpoxyModel model = new DisplayElementGroupEpoxyModel(issue, node, ImmutableList.copyOf(group), this.bypass).messageItemURLClickHandler(this.messageItemURLClickHandler);
            if (hasLink) {
                model.linkClickListener(this.helpThreadAdapterListener);
            }
            this.models.add(model);
        }
    }

    private void addHelpOptions(HelpThread helpThread, HelpThreadIssue issue, HelpThreadNode node) {
        if (!node.getHelpOptions().isEmpty()) {
            if (node.hasSelectedOption()) {
                HelpOptionEpoxyModel model = new HelpOptionEpoxyModel(this.bypass, issue, node, node.getSelection()).time(node.getUpdatedAt());
                if (model.shouldHaveAttachments()) {
                    model.attachments(this.attachmentsProvider.getAttachmentsForIssue(issue));
                }
                this.models.add(model);
            } else {
                for (HelpThreadOption option : node.getHelpOptions()) {
                    HelpOptionEpoxyModel model2 = new HelpOptionEpoxyModel(this.bypass, issue, node, option);
                    if (node.isCurrent() && !helpThread.isLocked()) {
                        model2.optionClickListener(this.helpThreadAdapterListener);
                    }
                    this.models.add(model2);
                }
            }
            EpoxyModel<?> lastModel = (EpoxyModel) this.models.get(this.models.size() - 1);
            if (lastModel instanceof HelpOptionEpoxyModel) {
                ((HelpOptionEpoxyModel) lastModel).withTail(true).profileImageUrl(helpThread.getSpeaker().getPictureUrl());
            }
        }
    }
}
