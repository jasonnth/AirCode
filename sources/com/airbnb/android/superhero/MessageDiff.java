package com.airbnb.android.superhero;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

class MessageDiff {
    private final SuperHeroTableOpenHelper tableOpenHelper;

    MessageDiff(SuperHeroTableOpenHelper tableOpenHelper2) {
        this.tableOpenHelper = tableOpenHelper2;
    }

    /* access modifiers changed from: 0000 */
    public List<MessageDiffResult> diffResults(List<? extends SuperHeroMessage> _serverMessages) {
        List<? extends SuperHeroMessage> localMessages = this.tableOpenHelper.allMessages();
        List<? extends SuperHeroMessage> serverMessages = Lists.newArrayList((Iterable<? extends E>) _serverMessages);
        List<MessageDiffResult> diffResult = new ArrayList<>(localMessages.size());
        for (SuperHeroMessage localMessage : localMessages) {
            int index = findMessageById(serverMessages, localMessage.mo11531id());
            if (index == -1) {
                diffResult.add(new MessageDiffResult(localMessage, null, Status.REMOVE));
            } else {
                diffResult.add(new MessageDiffResult(localMessage, (SuperHeroMessage) serverMessages.get(index), Status.CONFLICT));
                serverMessages.remove(index);
            }
        }
        for (SuperHeroMessage serverMessage : serverMessages) {
            diffResult.add(new MessageDiffResult(null, serverMessage, Status.ADD));
        }
        return diffResult;
    }

    private static int findMessageById(List<? extends SuperHeroMessage> messages, long id) {
        int size = messages.size();
        for (int i = 0; i < size; i++) {
            if (((SuperHeroMessage) messages.get(i)).mo11531id() == id) {
                return i;
            }
        }
        return -1;
    }
}
