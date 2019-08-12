package net.p318sf.scuba.smartcards;

import java.util.EventListener;

/* renamed from: net.sf.scuba.smartcards.CardTerminalListener */
public interface CardTerminalListener extends EventListener {
    void cardInserted(CardEvent cardEvent);

    void cardRemoved(CardEvent cardEvent);
}
