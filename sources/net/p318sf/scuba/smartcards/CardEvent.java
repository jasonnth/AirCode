package net.p318sf.scuba.smartcards;

import java.util.EventObject;

/* renamed from: net.sf.scuba.smartcards.CardEvent */
public class CardEvent extends EventObject {
    public static final int INSERTED = 1;
    public static final int REMOVED = 0;
    private static final long serialVersionUID = -5645277246646615351L;
    private CardService service;
    private int type;

    public CardEvent(int i, CardService cardService) {
        super(cardService);
        this.type = i;
        this.service = cardService;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        try {
            if (!obj.getClass().equals(getClass())) {
                return false;
            }
            CardEvent cardEvent = (CardEvent) obj;
            if (this.type != cardEvent.type || !this.service.equals(cardEvent.service)) {
                z = false;
            }
            return z;
        } catch (ClassCastException e) {
            return false;
        }
    }

    public CardService getService() {
        return this.service;
    }

    public int getType() {
        return this.type;
    }

    public int hashCode() {
        return (this.service.hashCode() * 5) + (this.type * 7);
    }

    public String toString() {
        switch (this.type) {
            case 0:
                return "Card removed from " + this.service;
            case 1:
                return "Card inserted in " + this.service;
            default:
                return "CardEvent " + this.service;
        }
    }
}
