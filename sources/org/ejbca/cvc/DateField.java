package org.ejbca.cvc;

import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoSelectionFragment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateField extends AbstractDataField {
    private static final int DATE_ARRAY_SIZE = 6;
    private static final DateFormat FORMAT_PRINTABLE = new SimpleDateFormat("yyyy-MM-dd");
    private static final TimeZone GMTTIMEZONE = TimeZone.getTimeZone("GMT");
    private Date date;

    static {
        FORMAT_PRINTABLE.setTimeZone(GMTTIMEZONE);
    }

    DateField(CVCTagEnum cVCTagEnum) {
        super(cVCTagEnum);
    }

    DateField(CVCTagEnum cVCTagEnum, Date date2) {
        this(cVCTagEnum);
        Calendar instance = Calendar.getInstance(GMTTIMEZONE);
        instance.setTimeInMillis(date2.getTime());
        int i = instance.get(1);
        int i2 = instance.get(2);
        int i3 = instance.get(5);
        instance.clear();
        instance.set(i, i2, i3);
        this.date = instance.getTime();
    }

    DateField(CVCTagEnum cVCTagEnum, byte[] bArr) {
        int i = 0;
        this(cVCTagEnum);
        if (bArr == null || bArr.length != 6) {
            StringBuilder append = new StringBuilder().append("data argument must have length 6, was ");
            if (bArr != null) {
                i = bArr.length;
            }
            throw new IllegalArgumentException(append.append(i).toString());
        }
        int i2 = bArr[1] + (bArr[0] * 10) + OfficialIdPhotoSelectionFragment.MAX_IMAGE_SIZE;
        int i3 = ((bArr[2] * 10) + bArr[3]) - 1;
        int i4 = bArr[5] + (bArr[4] * 10);
        Calendar instance = Calendar.getInstance(GMTTIMEZONE);
        instance.clear();
        if (cVCTagEnum == CVCTagEnum.EFFECTIVE_DATE) {
            instance.set(i2, i3, i4, 0, 0, 0);
        } else {
            instance.set(i2, i3, i4, 23, 59, 59);
        }
        this.date = instance.getTime();
    }

    public Date getDate() {
        return this.date;
    }

    /* access modifiers changed from: protected */
    public byte[] getEncoded() {
        Calendar instance = Calendar.getInstance(GMTTIMEZONE);
        instance.setTimeInMillis(this.date.getTime());
        int i = instance.get(1) - 2000;
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        return new byte[]{(byte) (i / 10), (byte) (i % 10), (byte) (i2 / 10), (byte) (i2 % 10), (byte) (i3 / 10), (byte) (i3 % 10)};
    }

    /* access modifiers changed from: protected */
    public String valueAsText() {
        return FORMAT_PRINTABLE.format(this.date);
    }
}
