package com.airbnb.android.core.models;

import com.google.common.collect.FluentIterable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ReferralContact implements Serializable {

    /* renamed from: id */
    private final int f8475id;
    private boolean isRecommended;
    private final ArrayList<ReferralItem> items = new ArrayList<>();
    private final String name;
    private final String pictureUri;

    public static final class Email extends ReferralItem {
        public Email(String email, ReferralContact contact, boolean selected) {
            super(email, contact, selected);
        }

        /* access modifiers changed from: 0000 */
        public int getSortOrder() {
            return 0;
        }
    }

    public static final class Phone extends ReferralItem {
        public Phone(String phone, ReferralContact contact, boolean selected) {
            super(phone, contact, selected);
        }

        /* access modifiers changed from: 0000 */
        public int getSortOrder() {
            return 1;
        }
    }

    public static abstract class ReferralItem implements Serializable {
        private final ReferralContact contact;
        private boolean selected;
        private final String value;

        /* access modifiers changed from: 0000 */
        public abstract int getSortOrder();

        public ReferralItem(String value2, ReferralContact contact2, boolean selected2) {
            this.value = value2;
            this.contact = contact2;
            this.selected = selected2;
        }

        public String getValue() {
            return this.value;
        }

        public ReferralContact getContact() {
            return this.contact;
        }

        public boolean isSelected() {
            return this.selected;
        }

        public void setSelected(boolean selected2) {
            this.selected = selected2;
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return this.value.equals(((ReferralItem) o).value);
        }

        public int hashCode() {
            return this.value.hashCode();
        }
    }

    public ReferralContact(int id, String name2, String pictureUri2) {
        this.f8475id = id;
        this.name = name2;
        this.pictureUri = pictureUri2;
    }

    public void setIsRecommended() {
        this.isRecommended = true;
    }

    public boolean isRecommended() {
        return this.isRecommended;
    }

    public void addEmail(String email, boolean selected) {
        this.items.add(new Email(email, this, selected));
    }

    public void addPhoneNum(String phoneNum, boolean selected) {
        this.items.add(new Phone(phoneNum, this, selected));
    }

    static /* synthetic */ int lambda$getItems$0(ReferralItem lhs, ReferralItem rhs) {
        return lhs.getSortOrder() - rhs.getSortOrder();
    }

    public List<ReferralItem> getItems() {
        return FluentIterable.from((Iterable<E>) this.items).toSortedList(ReferralContact$$Lambda$1.lambdaFactory$());
    }

    public List<String> getPhonesAsStrings() {
        return FluentIterable.from((Iterable<E>) this.items).filter(Phone.class).transform(ReferralContact$$Lambda$2.lambdaFactory$()).toList();
    }

    public List<String> getEmailsAsStrings() {
        return FluentIterable.from((Iterable<E>) this.items).filter(Email.class).transform(ReferralContact$$Lambda$3.lambdaFactory$()).toList();
    }

    public int getId() {
        return this.f8475id;
    }

    public String getPictureUri() {
        return this.pictureUri;
    }

    public String getName() {
        return this.name;
    }
}
