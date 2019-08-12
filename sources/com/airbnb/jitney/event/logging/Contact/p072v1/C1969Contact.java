package com.airbnb.jitney.event.logging.Contact.p072v1;

import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.jitney.event.logging.Address.p037v1.C1797Address;
import com.microsoft.thrifty.Adapter;
import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.StructBuilder;
import com.microsoft.thrifty.protocol.Protocol;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.jmrtd.PassportService;

/* renamed from: com.airbnb.jitney.event.logging.Contact.v1.Contact */
public final class C1969Contact implements Struct {
    public static final Adapter<C1969Contact, Builder> ADAPTER = new ContactAdapter();
    public final List<C1797Address> addresses;
    public final List<String> emails;
    public final Boolean favorite;
    public final String first_name;
    public final Boolean has_photo;
    public final String job_title;
    public final Long last_contacted;
    public final String last_name;
    public final String organization_name;
    public final List<String> phone_numbers;
    public final Long times_contacted;

    /* renamed from: com.airbnb.jitney.event.logging.Contact.v1.Contact$Builder */
    public static final class Builder implements StructBuilder<C1969Contact> {
        /* access modifiers changed from: private */
        public List<C1797Address> addresses;
        /* access modifiers changed from: private */
        public List<String> emails;
        /* access modifiers changed from: private */
        public Boolean favorite;
        /* access modifiers changed from: private */
        public String first_name;
        /* access modifiers changed from: private */
        public Boolean has_photo;
        /* access modifiers changed from: private */
        public String job_title;
        /* access modifiers changed from: private */
        public Long last_contacted;
        /* access modifiers changed from: private */
        public String last_name;
        /* access modifiers changed from: private */
        public String organization_name;
        /* access modifiers changed from: private */
        public List<String> phone_numbers;
        /* access modifiers changed from: private */
        public Long times_contacted;

        public Builder first_name(String first_name2) {
            this.first_name = first_name2;
            return this;
        }

        public Builder phone_numbers(List<String> phone_numbers2) {
            this.phone_numbers = phone_numbers2;
            return this;
        }

        public Builder emails(List<String> emails2) {
            this.emails = emails2;
            return this;
        }

        public Builder has_photo(Boolean has_photo2) {
            this.has_photo = has_photo2;
            return this;
        }

        public Builder favorite(Boolean favorite2) {
            this.favorite = favorite2;
            return this;
        }

        public C1969Contact build() {
            return new C1969Contact(this);
        }
    }

    /* renamed from: com.airbnb.jitney.event.logging.Contact.v1.Contact$ContactAdapter */
    private static final class ContactAdapter implements Adapter<C1969Contact, Builder> {
        private ContactAdapter() {
        }

        public void write(Protocol protocol, C1969Contact struct) throws IOException {
            protocol.writeStructBegin("Contact");
            if (struct.first_name != null) {
                protocol.writeFieldBegin(CohostingConstants.FIRST_NAME_FIELD, 1, PassportService.SF_DG11);
                protocol.writeString(struct.first_name);
                protocol.writeFieldEnd();
            }
            if (struct.last_name != null) {
                protocol.writeFieldBegin("last_name", 2, PassportService.SF_DG11);
                protocol.writeString(struct.last_name);
                protocol.writeFieldEnd();
            }
            if (struct.phone_numbers != null) {
                protocol.writeFieldBegin("phone_numbers", 3, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.phone_numbers.size());
                for (String item0 : struct.phone_numbers) {
                    protocol.writeString(item0);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.emails != null) {
                protocol.writeFieldBegin("emails", 4, 15);
                protocol.writeListBegin(PassportService.SF_DG11, struct.emails.size());
                for (String item02 : struct.emails) {
                    protocol.writeString(item02);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.addresses != null) {
                protocol.writeFieldBegin("addresses", 5, 15);
                protocol.writeListBegin(PassportService.SF_DG12, struct.addresses.size());
                for (C1797Address item03 : struct.addresses) {
                    C1797Address.ADAPTER.write(protocol, item03);
                }
                protocol.writeListEnd();
                protocol.writeFieldEnd();
            }
            if (struct.has_photo != null) {
                protocol.writeFieldBegin("has_photo", 6, 2);
                protocol.writeBool(struct.has_photo.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.job_title != null) {
                protocol.writeFieldBegin("job_title", 7, PassportService.SF_DG11);
                protocol.writeString(struct.job_title);
                protocol.writeFieldEnd();
            }
            if (struct.organization_name != null) {
                protocol.writeFieldBegin("organization_name", 8, PassportService.SF_DG11);
                protocol.writeString(struct.organization_name);
                protocol.writeFieldEnd();
            }
            if (struct.favorite != null) {
                protocol.writeFieldBegin("favorite", 9, 2);
                protocol.writeBool(struct.favorite.booleanValue());
                protocol.writeFieldEnd();
            }
            if (struct.last_contacted != null) {
                protocol.writeFieldBegin("last_contacted", 10, 10);
                protocol.writeI64(struct.last_contacted.longValue());
                protocol.writeFieldEnd();
            }
            if (struct.times_contacted != null) {
                protocol.writeFieldBegin("times_contacted", 11, 10);
                protocol.writeI64(struct.times_contacted.longValue());
                protocol.writeFieldEnd();
            }
            protocol.writeFieldStop();
            protocol.writeStructEnd();
        }
    }

    private C1969Contact(Builder builder) {
        List<C1797Address> list = null;
        this.first_name = builder.first_name;
        this.last_name = builder.last_name;
        this.phone_numbers = builder.phone_numbers == null ? null : Collections.unmodifiableList(builder.phone_numbers);
        this.emails = builder.emails == null ? null : Collections.unmodifiableList(builder.emails);
        if (builder.addresses != null) {
            list = Collections.unmodifiableList(builder.addresses);
        }
        this.addresses = list;
        this.has_photo = builder.has_photo;
        this.job_title = builder.job_title;
        this.organization_name = builder.organization_name;
        this.favorite = builder.favorite;
        this.last_contacted = builder.last_contacted;
        this.times_contacted = builder.times_contacted;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof C1969Contact)) {
            return false;
        }
        C1969Contact that = (C1969Contact) other;
        if ((this.first_name == that.first_name || (this.first_name != null && this.first_name.equals(that.first_name))) && ((this.last_name == that.last_name || (this.last_name != null && this.last_name.equals(that.last_name))) && ((this.phone_numbers == that.phone_numbers || (this.phone_numbers != null && this.phone_numbers.equals(that.phone_numbers))) && ((this.emails == that.emails || (this.emails != null && this.emails.equals(that.emails))) && ((this.addresses == that.addresses || (this.addresses != null && this.addresses.equals(that.addresses))) && ((this.has_photo == that.has_photo || (this.has_photo != null && this.has_photo.equals(that.has_photo))) && ((this.job_title == that.job_title || (this.job_title != null && this.job_title.equals(that.job_title))) && ((this.organization_name == that.organization_name || (this.organization_name != null && this.organization_name.equals(that.organization_name))) && ((this.favorite == that.favorite || (this.favorite != null && this.favorite.equals(that.favorite))) && (this.last_contacted == that.last_contacted || (this.last_contacted != null && this.last_contacted.equals(that.last_contacted)))))))))))) {
            if (this.times_contacted == that.times_contacted) {
                return true;
            }
            if (this.times_contacted != null && this.times_contacted.equals(that.times_contacted)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int code = (((((((((((((((((((16777619 ^ (this.first_name == null ? 0 : this.first_name.hashCode())) * -2128831035) ^ (this.last_name == null ? 0 : this.last_name.hashCode())) * -2128831035) ^ (this.phone_numbers == null ? 0 : this.phone_numbers.hashCode())) * -2128831035) ^ (this.emails == null ? 0 : this.emails.hashCode())) * -2128831035) ^ (this.addresses == null ? 0 : this.addresses.hashCode())) * -2128831035) ^ (this.has_photo == null ? 0 : this.has_photo.hashCode())) * -2128831035) ^ (this.job_title == null ? 0 : this.job_title.hashCode())) * -2128831035) ^ (this.organization_name == null ? 0 : this.organization_name.hashCode())) * -2128831035) ^ (this.favorite == null ? 0 : this.favorite.hashCode())) * -2128831035) ^ (this.last_contacted == null ? 0 : this.last_contacted.hashCode())) * -2128831035;
        if (this.times_contacted != null) {
            i = this.times_contacted.hashCode();
        }
        return (code ^ i) * -2128831035;
    }

    public String toString() {
        return "Contact{first_name=" + this.first_name + ", last_name=" + this.last_name + ", phone_numbers=" + this.phone_numbers + ", emails=" + this.emails + ", addresses=" + this.addresses + ", has_photo=" + this.has_photo + ", job_title=" + this.job_title + ", organization_name=" + this.organization_name + ", favorite=" + this.favorite + ", last_contacted=" + this.last_contacted + ", times_contacted=" + this.times_contacted + "}";
    }

    public void write(Protocol protocol) throws IOException {
        ADAPTER.write(protocol, this);
    }
}
