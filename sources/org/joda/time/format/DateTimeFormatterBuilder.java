package org.joda.time.format;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.MutableDateTime;
import org.joda.time.MutableDateTime.Property;
import org.joda.time.ReadablePartial;
import org.joda.time.field.MillisDurationField;
import org.joda.time.field.PreciseDateTimeField;
import p005cn.jpush.android.JPushConstants;

public class DateTimeFormatterBuilder {
    private ArrayList<Object> iElementPairs = new ArrayList<>();
    private Object iFormatter;

    static class CharacterLiteral implements InternalParser, InternalPrinter {
        private final char iValue;

        CharacterLiteral(char c) {
            this.iValue = c;
        }

        public int estimatePrintedLength() {
            return 1;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        public int estimateParsedLength() {
            return 1;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            if (i >= charSequence.length()) {
                return i ^ -1;
            }
            char charAt = charSequence.charAt(i);
            char c = this.iValue;
            if (charAt != c) {
                char upperCase = Character.toUpperCase(charAt);
                char upperCase2 = Character.toUpperCase(c);
                if (!(upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2))) {
                    return i ^ -1;
                }
            }
            return i + 1;
        }
    }

    static class Composite implements InternalParser, InternalPrinter {
        private final int iParsedLengthEstimate;
        private final InternalParser[] iParsers;
        private final int iPrintedLengthEstimate;
        private final InternalPrinter[] iPrinters;

        Composite(List<Object> list) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            decompose(list, arrayList, arrayList2);
            if (arrayList.contains(null) || arrayList.isEmpty()) {
                this.iPrinters = null;
                this.iPrintedLengthEstimate = 0;
            } else {
                int size = arrayList.size();
                this.iPrinters = new InternalPrinter[size];
                int i = 0;
                for (int i2 = 0; i2 < size; i2++) {
                    InternalPrinter internalPrinter = (InternalPrinter) arrayList.get(i2);
                    i += internalPrinter.estimatePrintedLength();
                    this.iPrinters[i2] = internalPrinter;
                }
                this.iPrintedLengthEstimate = i;
            }
            if (arrayList2.contains(null) || arrayList2.isEmpty()) {
                this.iParsers = null;
                this.iParsedLengthEstimate = 0;
                return;
            }
            int size2 = arrayList2.size();
            this.iParsers = new InternalParser[size2];
            int i3 = 0;
            for (int i4 = 0; i4 < size2; i4++) {
                InternalParser internalParser = (InternalParser) arrayList2.get(i4);
                i3 += internalParser.estimateParsedLength();
                this.iParsers[i4] = internalParser;
            }
            this.iParsedLengthEstimate = i3;
        }

        public int estimatePrintedLength() {
            return this.iPrintedLengthEstimate;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            Locale locale2;
            InternalPrinter[] internalPrinterArr = this.iPrinters;
            if (internalPrinterArr == null) {
                throw new UnsupportedOperationException();
            }
            if (locale == null) {
                locale2 = Locale.getDefault();
            } else {
                locale2 = locale;
            }
            for (InternalPrinter printTo : internalPrinterArr) {
                printTo.printTo(appendable, j, chronology, i, dateTimeZone, locale2);
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            InternalPrinter[] internalPrinterArr = this.iPrinters;
            if (internalPrinterArr == null) {
                throw new UnsupportedOperationException();
            }
            if (locale == null) {
                locale = Locale.getDefault();
            }
            for (InternalPrinter printTo : internalPrinterArr) {
                printTo.printTo(appendable, readablePartial, locale);
            }
        }

        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            InternalParser[] internalParserArr = this.iParsers;
            if (internalParserArr == null) {
                throw new UnsupportedOperationException();
            }
            int length = internalParserArr.length;
            for (int i2 = 0; i2 < length && i >= 0; i2++) {
                i = internalParserArr[i2].parseInto(dateTimeParserBucket, charSequence, i);
            }
            return i;
        }

        /* access modifiers changed from: 0000 */
        public boolean isPrinter() {
            return this.iPrinters != null;
        }

        /* access modifiers changed from: 0000 */
        public boolean isParser() {
            return this.iParsers != null;
        }

        private void decompose(List<Object> list, List<Object> list2, List<Object> list3) {
            int size = list.size();
            for (int i = 0; i < size; i += 2) {
                Object obj = list.get(i);
                if (obj instanceof Composite) {
                    addArrayToList(list2, ((Composite) obj).iPrinters);
                } else {
                    list2.add(obj);
                }
                Object obj2 = list.get(i + 1);
                if (obj2 instanceof Composite) {
                    addArrayToList(list3, ((Composite) obj2).iParsers);
                } else {
                    list3.add(obj2);
                }
            }
        }

        private void addArrayToList(List<Object> list, Object[] objArr) {
            if (objArr != null) {
                for (Object add : objArr) {
                    list.add(add);
                }
            }
        }
    }

    static class FixedNumber extends PaddedNumber {
        protected FixedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z, i);
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int parseInto = super.parseInto(dateTimeParserBucket, charSequence, i);
            if (parseInto < 0) {
                return parseInto;
            }
            int i2 = this.iMaxParsedDigits + i;
            if (parseInto == i2) {
                return parseInto;
            }
            if (this.iSigned) {
                char charAt = charSequence.charAt(i);
                if (charAt == '-' || charAt == '+') {
                    i2++;
                }
            }
            if (parseInto > i2) {
                return (i2 + 1) ^ -1;
            }
            if (parseInto < i2) {
                return parseInto ^ -1;
            }
            return parseInto;
        }
    }

    static class Fraction implements InternalParser, InternalPrinter {
        private final DateTimeFieldType iFieldType;
        protected int iMaxDigits;
        protected int iMinDigits;

        protected Fraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
            this.iFieldType = dateTimeFieldType;
            if (i2 > 18) {
                i2 = 18;
            }
            this.iMinDigits = i;
            this.iMaxDigits = i2;
        }

        public int estimatePrintedLength() {
            return this.iMaxDigits;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            printTo(appendable, j, chronology);
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            printTo(appendable, readablePartial.getChronology().set(readablePartial, 0), readablePartial.getChronology());
        }

        /* access modifiers changed from: protected */
        public void printTo(Appendable appendable, long j, Chronology chronology) throws IOException {
            String l;
            DateTimeField field = this.iFieldType.getField(chronology);
            int i = this.iMinDigits;
            try {
                long remainder = field.remainder(j);
                if (remainder == 0) {
                    while (true) {
                        i--;
                        if (i >= 0) {
                            appendable.append('0');
                        } else {
                            return;
                        }
                    }
                } else {
                    long[] fractionData = getFractionData(remainder, field);
                    long j2 = fractionData[0];
                    int i2 = (int) fractionData[1];
                    if ((2147483647L & j2) == j2) {
                        l = Integer.toString((int) j2);
                    } else {
                        l = Long.toString(j2);
                    }
                    int length = l.length();
                    while (length < i2) {
                        appendable.append('0');
                        i--;
                        i2--;
                    }
                    if (i < i2) {
                        while (i < i2 && length > 1 && l.charAt(length - 1) == '0') {
                            i2--;
                            length--;
                        }
                        if (length < l.length()) {
                            for (int i3 = 0; i3 < length; i3++) {
                                appendable.append(l.charAt(i3));
                            }
                            return;
                        }
                    }
                    appendable.append(l);
                }
            } catch (RuntimeException e) {
                DateTimeFormatterBuilder.appendUnknownString(appendable, i);
            }
        }

        private long[] getFractionData(long j, DateTimeField dateTimeField) {
            int i;
            long j2;
            long unitMillis = dateTimeField.getDurationField().getUnitMillis();
            int i2 = this.iMaxDigits;
            while (true) {
                switch (i) {
                    case 1:
                        j2 = 10;
                        break;
                    case 2:
                        j2 = 100;
                        break;
                    case 3:
                        j2 = 1000;
                        break;
                    case 4:
                        j2 = 10000;
                        break;
                    case 5:
                        j2 = 100000;
                        break;
                    case 6:
                        j2 = 1000000;
                        break;
                    case 7:
                        j2 = 10000000;
                        break;
                    case 8:
                        j2 = 100000000;
                        break;
                    case 9:
                        j2 = 1000000000;
                        break;
                    case 10:
                        j2 = 10000000000L;
                        break;
                    case 11:
                        j2 = 100000000000L;
                        break;
                    case 12:
                        j2 = 1000000000000L;
                        break;
                    case 13:
                        j2 = 10000000000000L;
                        break;
                    case 14:
                        j2 = 100000000000000L;
                        break;
                    case 15:
                        j2 = 1000000000000000L;
                        break;
                    case 16:
                        j2 = 10000000000000000L;
                        break;
                    case 17:
                        j2 = 100000000000000000L;
                        break;
                    case 18:
                        j2 = 1000000000000000000L;
                        break;
                    default:
                        j2 = 1;
                        break;
                }
                if ((unitMillis * j2) / j2 == unitMillis) {
                    return new long[]{(j2 * j) / unitMillis, (long) i};
                }
                i2 = i - 1;
            }
        }

        public int estimateParsedLength() {
            return this.iMaxDigits;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            DateTimeField field = this.iFieldType.getField(dateTimeParserBucket.getChronology());
            int min = Math.min(this.iMaxDigits, charSequence.length() - i);
            long j = 0;
            long unitMillis = field.getDurationField().getUnitMillis() * 10;
            int i2 = 0;
            while (i2 < min) {
                char charAt = charSequence.charAt(i + i2);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i2++;
                unitMillis /= 10;
                j += ((long) (charAt - '0')) * unitMillis;
            }
            long j2 = j / 10;
            if (i2 == 0) {
                return i ^ -1;
            }
            if (j2 > 2147483647L) {
                return i ^ -1;
            }
            dateTimeParserBucket.saveField((DateTimeField) new PreciseDateTimeField(DateTimeFieldType.millisOfSecond(), MillisDurationField.INSTANCE, field.getDurationField()), (int) j2);
            return i2 + i;
        }
    }

    static class MatchingParser implements InternalParser {
        private final int iParsedLengthEstimate;
        private final InternalParser[] iParsers;

        MatchingParser(InternalParser[] internalParserArr) {
            int i;
            this.iParsers = internalParserArr;
            int i2 = 0;
            int length = internalParserArr.length;
            while (true) {
                int i3 = length - 1;
                if (i3 >= 0) {
                    InternalParser internalParser = internalParserArr[i3];
                    if (internalParser != null) {
                        i = internalParser.estimateParsedLength();
                        if (i > i2) {
                            i2 = i;
                            length = i3;
                        }
                    }
                    i = i2;
                    i2 = i;
                    length = i3;
                } else {
                    this.iParsedLengthEstimate = i2;
                    return;
                }
            }
        }

        public int estimateParsedLength() {
            return this.iParsedLengthEstimate;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x001e, code lost:
            r11.restoreState(r2);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:40:?, code lost:
            return r4;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:42:?, code lost:
            return r0 ^ -1;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:0x0016, code lost:
            if (r4 > r13) goto L_0x001c;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
            if (r4 != r13) goto L_0x0055;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:8:0x001a, code lost:
            if (r1 == false) goto L_0x0055;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:9:0x001c, code lost:
            if (r2 == null) goto L_0x0021;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int parseInto(org.joda.time.format.DateTimeParserBucket r11, java.lang.CharSequence r12, int r13) {
            /*
                r10 = this;
                r5 = 0
                org.joda.time.format.InternalParser[] r7 = r10.iParsers
                int r8 = r7.length
                java.lang.Object r9 = r11.saveState()
                r2 = 0
                r6 = r5
                r0 = r13
                r4 = r13
            L_0x000c:
                if (r6 >= r8) goto L_0x005b
                r1 = r7[r6]
                if (r1 != 0) goto L_0x0023
                if (r4 > r13) goto L_0x0015
            L_0x0014:
                return r13
            L_0x0015:
                r1 = 1
            L_0x0016:
                if (r4 > r13) goto L_0x001c
                if (r4 != r13) goto L_0x0055
                if (r1 == 0) goto L_0x0055
            L_0x001c:
                if (r2 == 0) goto L_0x0021
                r11.restoreState(r2)
            L_0x0021:
                r13 = r4
                goto L_0x0014
            L_0x0023:
                int r3 = r1.parseInto(r11, r12, r13)
                if (r3 < r13) goto L_0x004b
                if (r3 <= r4) goto L_0x0058
                int r1 = r12.length()
                if (r3 >= r1) goto L_0x003b
                int r1 = r6 + 1
                if (r1 >= r8) goto L_0x003b
                int r1 = r6 + 1
                r1 = r7[r1]
                if (r1 != 0) goto L_0x003d
            L_0x003b:
                r13 = r3
                goto L_0x0014
            L_0x003d:
                java.lang.Object r1 = r11.saveState()
                r2 = r3
            L_0x0042:
                r11.restoreState(r9)
                int r3 = r6 + 1
                r6 = r3
                r4 = r2
                r2 = r1
                goto L_0x000c
            L_0x004b:
                if (r3 >= 0) goto L_0x0058
                r1 = r3 ^ -1
                if (r1 <= r0) goto L_0x0058
                r0 = r1
                r1 = r2
                r2 = r4
                goto L_0x0042
            L_0x0055:
                r13 = r0 ^ -1
                goto L_0x0014
            L_0x0058:
                r1 = r2
                r2 = r4
                goto L_0x0042
            L_0x005b:
                r1 = r5
                goto L_0x0016
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatterBuilder.MatchingParser.parseInto(org.joda.time.format.DateTimeParserBucket, java.lang.CharSequence, int):int");
        }
    }

    static abstract class NumberFormatter implements InternalParser, InternalPrinter {
        protected final DateTimeFieldType iFieldType;
        protected final int iMaxParsedDigits;
        protected final boolean iSigned;

        NumberFormatter(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iMaxParsedDigits = i;
            this.iSigned = z;
        }

        public int estimateParsedLength() {
            return this.iMaxParsedDigits;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:49:0x00c1, code lost:
            r3 = r1;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int parseInto(org.joda.time.format.DateTimeParserBucket r13, java.lang.CharSequence r14, int r15) {
            /*
                r12 = this;
                r10 = 48
                r9 = 45
                r8 = 43
                r2 = 1
                r3 = 0
                int r0 = r12.iMaxParsedDigits
                int r1 = r14.length()
                int r1 = r1 - r15
                int r0 = java.lang.Math.min(r0, r1)
                r4 = r3
                r1 = r3
                r5 = r0
                r0 = r3
            L_0x0017:
                if (r4 >= r5) goto L_0x00c1
                int r6 = r15 + r4
                char r6 = r14.charAt(r6)
                if (r4 != 0) goto L_0x0059
                if (r6 == r9) goto L_0x0025
                if (r6 != r8) goto L_0x0059
            L_0x0025:
                boolean r7 = r12.iSigned
                if (r7 == 0) goto L_0x0059
                if (r6 != r9) goto L_0x0047
                r1 = r2
            L_0x002c:
                if (r6 != r8) goto L_0x0049
                r0 = r2
            L_0x002f:
                int r6 = r4 + 1
                if (r6 >= r5) goto L_0x00c1
                int r6 = r15 + r4
                int r6 = r6 + 1
                char r6 = r14.charAt(r6)
                if (r6 < r10) goto L_0x00c1
                r7 = 57
                if (r6 <= r7) goto L_0x004b
                r3 = r1
            L_0x0042:
                if (r4 != 0) goto L_0x0064
                r1 = r15 ^ -1
            L_0x0046:
                return r1
            L_0x0047:
                r1 = r3
                goto L_0x002c
            L_0x0049:
                r0 = r3
                goto L_0x002f
            L_0x004b:
                int r4 = r4 + 1
                int r5 = r5 + 1
                int r6 = r14.length()
                int r6 = r6 - r15
                int r5 = java.lang.Math.min(r5, r6)
                goto L_0x0017
            L_0x0059:
                if (r6 < r10) goto L_0x00c1
                r7 = 57
                if (r6 <= r7) goto L_0x0061
                r3 = r1
                goto L_0x0042
            L_0x0061:
                int r4 = r4 + 1
                goto L_0x0017
            L_0x0064:
                r1 = 9
                if (r4 < r1) goto L_0x008f
                if (r0 == 0) goto L_0x0080
                int r0 = r15 + 1
                int r1 = r15 + r4
                java.lang.CharSequence r0 = r14.subSequence(r0, r1)
                java.lang.String r0 = r0.toString()
                int r0 = java.lang.Integer.parseInt(r0)
            L_0x007a:
                org.joda.time.DateTimeFieldType r2 = r12.iFieldType
                r13.saveField(r2, r0)
                goto L_0x0046
            L_0x0080:
                int r1 = r15 + r4
                java.lang.CharSequence r0 = r14.subSequence(r15, r1)
                java.lang.String r0 = r0.toString()
                int r0 = java.lang.Integer.parseInt(r0)
                goto L_0x007a
            L_0x008f:
                if (r3 != 0) goto L_0x0093
                if (r0 == 0) goto L_0x00bf
            L_0x0093:
                int r0 = r15 + 1
                r1 = r0
            L_0x0096:
                int r0 = r1 + 1
                char r1 = r14.charAt(r1)     // Catch:{ StringIndexOutOfBoundsException -> 0x00b7 }
                int r2 = r1 + -48
                int r1 = r15 + r4
                r11 = r0
                r0 = r2
                r2 = r11
            L_0x00a3:
                if (r2 >= r1) goto L_0x00bb
                int r4 = r0 << 3
                int r0 = r0 << 1
                int r4 = r4 + r0
                int r0 = r2 + 1
                char r2 = r14.charAt(r2)
                int r2 = r2 + r4
                int r2 = r2 + -48
                r11 = r0
                r0 = r2
                r2 = r11
                goto L_0x00a3
            L_0x00b7:
                r0 = move-exception
                r1 = r15 ^ -1
                goto L_0x0046
            L_0x00bb:
                if (r3 == 0) goto L_0x007a
                int r0 = -r0
                goto L_0x007a
            L_0x00bf:
                r1 = r15
                goto L_0x0096
            L_0x00c1:
                r3 = r1
                goto L_0x0042
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatterBuilder.NumberFormatter.parseInto(org.joda.time.format.DateTimeParserBucket, java.lang.CharSequence, int):int");
        }
    }

    static class PaddedNumber extends NumberFormatter {
        protected final int iMinPrintedDigits;

        protected PaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z, int i2) {
            super(dateTimeFieldType, i, z);
            this.iMinPrintedDigits = i2;
        }

        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                FormatUtils.appendPaddedInteger(appendable, this.iFieldType.getField(chronology).get(j), this.iMinPrintedDigits);
            } catch (RuntimeException e) {
                DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendPaddedInteger(appendable, readablePartial.get(this.iFieldType), this.iMinPrintedDigits);
                } catch (RuntimeException e) {
                    DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
                }
            } else {
                DateTimeFormatterBuilder.appendUnknownString(appendable, this.iMinPrintedDigits);
            }
        }
    }

    static class StringLiteral implements InternalParser, InternalPrinter {
        private final String iValue;

        StringLiteral(String str) {
            this.iValue = str;
        }

        public int estimatePrintedLength() {
            return this.iValue.length();
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            appendable.append(this.iValue);
        }

        public int estimateParsedLength() {
            return this.iValue.length();
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            if (DateTimeFormatterBuilder.csStartsWithIgnoreCase(charSequence, i, this.iValue)) {
                return this.iValue.length() + i;
            }
            return i ^ -1;
        }
    }

    static class TextField implements InternalParser, InternalPrinter {
        private static Map<Locale, Map<DateTimeFieldType, Object[]>> cParseCache = new ConcurrentHashMap();
        private final DateTimeFieldType iFieldType;
        private final boolean iShort;

        TextField(DateTimeFieldType dateTimeFieldType, boolean z) {
            this.iFieldType = dateTimeFieldType;
            this.iShort = z;
        }

        public int estimatePrintedLength() {
            return this.iShort ? 6 : 20;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                appendable.append(print(j, chronology, locale));
            } catch (RuntimeException e) {
                appendable.append(65533);
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            try {
                appendable.append(print(readablePartial, locale));
            } catch (RuntimeException e) {
                appendable.append(65533);
            }
        }

        private String print(long j, Chronology chronology, Locale locale) {
            DateTimeField field = this.iFieldType.getField(chronology);
            if (this.iShort) {
                return field.getAsShortText(j, locale);
            }
            return field.getAsText(j, locale);
        }

        private String print(ReadablePartial readablePartial, Locale locale) {
            if (!readablePartial.isSupported(this.iFieldType)) {
                return "�";
            }
            DateTimeField field = this.iFieldType.getField(readablePartial.getChronology());
            if (this.iShort) {
                return field.getAsShortText(readablePartial, locale);
            }
            return field.getAsText(readablePartial, locale);
        }

        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            Map map;
            Map map2;
            int intValue;
            Locale locale = dateTimeParserBucket.getLocale();
            Map map3 = (Map) cParseCache.get(locale);
            if (map3 == null) {
                ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
                cParseCache.put(locale, concurrentHashMap);
                map = concurrentHashMap;
            } else {
                map = map3;
            }
            Object[] objArr = (Object[]) map.get(this.iFieldType);
            if (objArr == null) {
                Map concurrentHashMap2 = new ConcurrentHashMap(32);
                Property property = new MutableDateTime(0, DateTimeZone.UTC).property(this.iFieldType);
                int minimumValueOverall = property.getMinimumValueOverall();
                int maximumValueOverall = property.getMaximumValueOverall();
                if (maximumValueOverall - minimumValueOverall > 32) {
                    return i ^ -1;
                }
                intValue = property.getMaximumTextLength(locale);
                while (minimumValueOverall <= maximumValueOverall) {
                    property.set(minimumValueOverall);
                    concurrentHashMap2.put(property.getAsShortText(locale), Boolean.TRUE);
                    concurrentHashMap2.put(property.getAsShortText(locale).toLowerCase(locale), Boolean.TRUE);
                    concurrentHashMap2.put(property.getAsShortText(locale).toUpperCase(locale), Boolean.TRUE);
                    concurrentHashMap2.put(property.getAsText(locale), Boolean.TRUE);
                    concurrentHashMap2.put(property.getAsText(locale).toLowerCase(locale), Boolean.TRUE);
                    concurrentHashMap2.put(property.getAsText(locale).toUpperCase(locale), Boolean.TRUE);
                    minimumValueOverall++;
                }
                if ("en".equals(locale.getLanguage()) && this.iFieldType == DateTimeFieldType.era()) {
                    concurrentHashMap2.put("BCE", Boolean.TRUE);
                    concurrentHashMap2.put("bce", Boolean.TRUE);
                    concurrentHashMap2.put("CE", Boolean.TRUE);
                    concurrentHashMap2.put("ce", Boolean.TRUE);
                    intValue = 3;
                }
                map.put(this.iFieldType, new Object[]{concurrentHashMap2, Integer.valueOf(intValue)});
                map2 = concurrentHashMap2;
            } else {
                map2 = (Map) objArr[0];
                intValue = ((Integer) objArr[1]).intValue();
            }
            for (int min = Math.min(charSequence.length(), intValue + i); min > i; min--) {
                String obj = charSequence.subSequence(i, min).toString();
                if (map2.containsKey(obj)) {
                    dateTimeParserBucket.saveField(this.iFieldType, obj, locale);
                    return min;
                }
            }
            return i ^ -1;
        }
    }

    enum TimeZoneId implements InternalParser, InternalPrinter {
        INSTANCE;
        
        private static final List<String> ALL_IDS = null;
        private static final List<String> BASE_GROUPED_IDS = null;
        private static final Map<String, List<String>> GROUPED_IDS = null;
        static final int MAX_LENGTH = 0;
        static final int MAX_PREFIX_LENGTH = 0;

        static {
            BASE_GROUPED_IDS = new ArrayList();
            ALL_IDS = new ArrayList(DateTimeZone.getAvailableIDs());
            Collections.sort(ALL_IDS);
            GROUPED_IDS = new HashMap();
            int i = 0;
            int i2 = 0;
            for (String str : ALL_IDS) {
                int indexOf = str.indexOf(47);
                if (indexOf >= 0) {
                    if (indexOf < str.length()) {
                        indexOf++;
                    }
                    int max = Math.max(i, indexOf);
                    String substring = str.substring(0, indexOf + 1);
                    String substring2 = str.substring(indexOf);
                    if (!GROUPED_IDS.containsKey(substring)) {
                        GROUPED_IDS.put(substring, new ArrayList());
                    }
                    ((List) GROUPED_IDS.get(substring)).add(substring2);
                    i = max;
                } else {
                    BASE_GROUPED_IDS.add(str);
                }
                i2 = Math.max(i2, str.length());
            }
            MAX_LENGTH = i2;
            MAX_PREFIX_LENGTH = i;
        }

        public int estimatePrintedLength() {
            return MAX_LENGTH;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(dateTimeZone != null ? dateTimeZone.getID() : "");
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        public int estimateParsedLength() {
            return MAX_LENGTH;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            List<String> list;
            String str;
            List<String> list2 = BASE_GROUPED_IDS;
            int length = charSequence.length();
            int min = Math.min(length, MAX_PREFIX_LENGTH + i);
            String str2 = "";
            int i3 = i;
            while (true) {
                if (i3 >= min) {
                    i2 = i;
                    list = list2;
                    break;
                } else if (charSequence.charAt(i3) == '/') {
                    str2 = charSequence.subSequence(i, i3 + 1).toString();
                    i2 = i + str2.length();
                    if (i3 < length) {
                        str = str2 + charSequence.charAt(i3 + 1);
                    } else {
                        str = str2;
                    }
                    List<String> list3 = (List) GROUPED_IDS.get(str);
                    if (list3 == null) {
                        return i ^ -1;
                    }
                    list = list3;
                } else {
                    i3++;
                }
            }
            String str3 = null;
            int i4 = 0;
            while (i4 < list.size()) {
                String str4 = (String) list.get(i4);
                if (!DateTimeFormatterBuilder.csStartsWith(charSequence, i2, str4) || (str3 != null && str4.length() <= str3.length())) {
                    str4 = str3;
                }
                i4++;
                str3 = str4;
            }
            if (str3 == null) {
                return i ^ -1;
            }
            dateTimeParserBucket.setZone(DateTimeZone.forID(str2 + str3));
            return str3.length() + i2;
        }
    }

    static class TimeZoneName implements InternalParser, InternalPrinter {
        private final Map<String, DateTimeZone> iParseLookup;
        private final int iType;

        TimeZoneName(int i, Map<String, DateTimeZone> map) {
            this.iType = i;
            this.iParseLookup = map;
        }

        public int estimatePrintedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            appendable.append(print(j - ((long) i), dateTimeZone, locale));
        }

        private String print(long j, DateTimeZone dateTimeZone, Locale locale) {
            if (dateTimeZone == null) {
                return "";
            }
            switch (this.iType) {
                case 0:
                    return dateTimeZone.getName(j, locale);
                case 1:
                    return dateTimeZone.getShortName(j, locale);
                default:
                    return "";
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        public int estimateParsedLength() {
            return this.iType == 1 ? 4 : 20;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            Map<String, DateTimeZone> map = this.iParseLookup;
            Map<String, DateTimeZone> defaultTimeZoneNames = map != null ? map : DateTimeUtils.getDefaultTimeZoneNames();
            String str = null;
            for (String str2 : defaultTimeZoneNames.keySet()) {
                if (!DateTimeFormatterBuilder.csStartsWith(charSequence, i, str2) || (str != null && str2.length() <= str.length())) {
                    str2 = str;
                }
                str = str2;
            }
            if (str == null) {
                return i ^ -1;
            }
            dateTimeParserBucket.setZone((DateTimeZone) defaultTimeZoneNames.get(str));
            return str.length() + i;
        }
    }

    static class TimeZoneOffset implements InternalParser, InternalPrinter {
        private final int iMaxFields;
        private final int iMinFields;
        private final boolean iShowSeparators;
        private final String iZeroOffsetParseText;
        private final String iZeroOffsetPrintText;

        TimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
            int i3 = 4;
            this.iZeroOffsetPrintText = str;
            this.iZeroOffsetParseText = str2;
            this.iShowSeparators = z;
            if (i <= 0 || i2 < i) {
                throw new IllegalArgumentException();
            }
            if (i > 4) {
                i2 = 4;
            } else {
                i3 = i;
            }
            this.iMinFields = i3;
            this.iMaxFields = i2;
        }

        public int estimatePrintedLength() {
            int i = (this.iMinFields + 1) << 1;
            if (this.iShowSeparators) {
                i += this.iMinFields - 1;
            }
            if (this.iZeroOffsetPrintText == null || this.iZeroOffsetPrintText.length() <= i) {
                return i;
            }
            return this.iZeroOffsetPrintText.length();
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            if (dateTimeZone != null) {
                if (i != 0 || this.iZeroOffsetPrintText == null) {
                    if (i >= 0) {
                        appendable.append('+');
                    } else {
                        appendable.append('-');
                        i = -i;
                    }
                    int i2 = i / JPushConstants.ONE_HOUR;
                    FormatUtils.appendPaddedInteger(appendable, i2, 2);
                    if (this.iMaxFields != 1) {
                        int i3 = i - (i2 * JPushConstants.ONE_HOUR);
                        if (i3 != 0 || this.iMinFields > 1) {
                            int i4 = i3 / JPushConstants.ONE_MINUTE;
                            if (this.iShowSeparators) {
                                appendable.append(':');
                            }
                            FormatUtils.appendPaddedInteger(appendable, i4, 2);
                            if (this.iMaxFields != 2) {
                                int i5 = i3 - (i4 * JPushConstants.ONE_MINUTE);
                                if (i5 != 0 || this.iMinFields > 2) {
                                    int i6 = i5 / 1000;
                                    if (this.iShowSeparators) {
                                        appendable.append(':');
                                    }
                                    FormatUtils.appendPaddedInteger(appendable, i6, 2);
                                    if (this.iMaxFields != 3) {
                                        int i7 = i5 - (i6 * 1000);
                                        if (i7 != 0 || this.iMinFields > 3) {
                                            if (this.iShowSeparators) {
                                                appendable.append('.');
                                            }
                                            FormatUtils.appendPaddedInteger(appendable, i7, 3);
                                            return;
                                        }
                                        return;
                                    }
                                    return;
                                }
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                appendable.append(this.iZeroOffsetPrintText);
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
        }

        public int estimateParsedLength() {
            return estimatePrintedLength();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:7:0x0021, code lost:
            if (r0 != '+') goto L_0x0028;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public int parseInto(org.joda.time.format.DateTimeParserBucket r10, java.lang.CharSequence r11, int r12) {
            /*
                r9 = this;
                r5 = 45
                r4 = 43
                r2 = 0
                r1 = 1
                r8 = 2
                int r0 = r11.length()
                int r3 = r0 - r12
                java.lang.String r0 = r9.iZeroOffsetParseText
                if (r0 == 0) goto L_0x0023
                java.lang.String r0 = r9.iZeroOffsetParseText
                int r0 = r0.length()
                if (r0 != 0) goto L_0x0030
                if (r3 <= 0) goto L_0x0028
                char r0 = r11.charAt(r12)
                if (r0 == r5) goto L_0x0023
                if (r0 != r4) goto L_0x0028
            L_0x0023:
                if (r3 > r1) goto L_0x0047
                r12 = r12 ^ -1
            L_0x0027:
                return r12
            L_0x0028:
                java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
                r10.setOffset(r0)
                goto L_0x0027
            L_0x0030:
                java.lang.String r0 = r9.iZeroOffsetParseText
                boolean r0 = org.joda.time.format.DateTimeFormatterBuilder.csStartsWithIgnoreCase(r11, r12, r0)
                if (r0 == 0) goto L_0x0023
                java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
                r10.setOffset(r0)
                java.lang.String r0 = r9.iZeroOffsetParseText
                int r0 = r0.length()
                int r12 = r12 + r0
                goto L_0x0027
            L_0x0047:
                char r0 = r11.charAt(r12)
                if (r0 != r5) goto L_0x005b
                r0 = r1
            L_0x004e:
                int r3 = r3 + -1
                int r4 = r12 + 1
                int r5 = r9.digitCount(r11, r4, r8)
                if (r5 >= r8) goto L_0x0062
                r12 = r4 ^ -1
                goto L_0x0027
            L_0x005b:
                if (r0 != r4) goto L_0x005f
                r0 = r2
                goto L_0x004e
            L_0x005f:
                r12 = r12 ^ -1
                goto L_0x0027
            L_0x0062:
                int r5 = org.joda.time.format.FormatUtils.parseTwoDigits(r11, r4)
                r6 = 23
                if (r5 <= r6) goto L_0x006d
                r12 = r4 ^ -1
                goto L_0x0027
            L_0x006d:
                r6 = 3600000(0x36ee80, float:5.044674E-39)
                int r5 = r5 * r6
                int r3 = r3 + -2
                int r4 = r4 + 2
                if (r3 > 0) goto L_0x0084
                r1 = r5
                r12 = r4
            L_0x0079:
                if (r0 == 0) goto L_0x0157
                int r0 = -r1
            L_0x007c:
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                r10.setOffset(r0)
                goto L_0x0027
            L_0x0084:
                char r6 = r11.charAt(r4)
                r7 = 58
                if (r6 != r7) goto L_0x009e
                int r2 = r3 + -1
                int r3 = r4 + 1
                r4 = r3
                r3 = r2
                r2 = r1
            L_0x0093:
                int r6 = r9.digitCount(r11, r4, r8)
                if (r6 != 0) goto L_0x00a9
                if (r2 != 0) goto L_0x00a9
                r1 = r5
                r12 = r4
                goto L_0x0079
            L_0x009e:
                r7 = 48
                if (r6 < r7) goto L_0x00a6
                r7 = 57
                if (r6 <= r7) goto L_0x0093
            L_0x00a6:
                r1 = r5
                r12 = r4
                goto L_0x0079
            L_0x00a9:
                if (r6 >= r8) goto L_0x00af
                r12 = r4 ^ -1
                goto L_0x0027
            L_0x00af:
                int r6 = org.joda.time.format.FormatUtils.parseTwoDigits(r11, r4)
                r7 = 59
                if (r6 <= r7) goto L_0x00bb
                r12 = r4 ^ -1
                goto L_0x0027
            L_0x00bb:
                r7 = 60000(0xea60, float:8.4078E-41)
                int r6 = r6 * r7
                int r5 = r5 + r6
                int r3 = r3 + -2
                int r4 = r4 + 2
                if (r3 > 0) goto L_0x00c9
                r1 = r5
                r12 = r4
                goto L_0x0079
            L_0x00c9:
                if (r2 == 0) goto L_0x00da
                char r6 = r11.charAt(r4)
                r7 = 58
                if (r6 == r7) goto L_0x00d6
                r1 = r5
                r12 = r4
                goto L_0x0079
            L_0x00d6:
                int r3 = r3 + -1
                int r4 = r4 + 1
            L_0x00da:
                int r6 = r9.digitCount(r11, r4, r8)
                if (r6 != 0) goto L_0x00e5
                if (r2 != 0) goto L_0x00e5
                r1 = r5
                r12 = r4
                goto L_0x0079
            L_0x00e5:
                if (r6 >= r8) goto L_0x00eb
                r12 = r4 ^ -1
                goto L_0x0027
            L_0x00eb:
                int r6 = org.joda.time.format.FormatUtils.parseTwoDigits(r11, r4)
                r7 = 59
                if (r6 <= r7) goto L_0x00f7
                r12 = r4 ^ -1
                goto L_0x0027
            L_0x00f7:
                int r6 = r6 * 1000
                int r5 = r5 + r6
                int r6 = r3 + -2
                int r3 = r4 + 2
                if (r6 > 0) goto L_0x0104
                r1 = r5
                r12 = r3
                goto L_0x0079
            L_0x0104:
                if (r2 == 0) goto L_0x011e
                char r4 = r11.charAt(r3)
                r7 = 46
                if (r4 == r7) goto L_0x011a
                char r4 = r11.charAt(r3)
                r7 = 44
                if (r4 == r7) goto L_0x011a
                r1 = r5
                r12 = r3
                goto L_0x0079
            L_0x011a:
                int r4 = r6 + -1
                int r3 = r3 + 1
            L_0x011e:
                r4 = 3
                int r6 = r9.digitCount(r11, r3, r4)
                if (r6 != 0) goto L_0x012b
                if (r2 != 0) goto L_0x012b
                r1 = r5
                r12 = r3
                goto L_0x0079
            L_0x012b:
                if (r6 >= r1) goto L_0x0131
                r12 = r3 ^ -1
                goto L_0x0027
            L_0x0131:
                int r4 = r3 + 1
                char r2 = r11.charAt(r3)
                int r2 = r2 + -48
                int r2 = r2 * 100
                int r2 = r2 + r5
                if (r6 <= r1) goto L_0x015d
                int r3 = r4 + 1
                char r1 = r11.charAt(r4)
                int r1 = r1 + -48
                int r1 = r1 * 10
                int r1 = r1 + r2
                if (r6 <= r8) goto L_0x015a
                int r4 = r3 + 1
                char r2 = r11.charAt(r3)
                int r2 = r2 + -48
                int r1 = r1 + r2
                r12 = r4
                goto L_0x0079
            L_0x0157:
                r0 = r1
                goto L_0x007c
            L_0x015a:
                r12 = r3
                goto L_0x0079
            L_0x015d:
                r1 = r2
                r12 = r4
                goto L_0x0079
            */
            throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormatterBuilder.TimeZoneOffset.parseInto(org.joda.time.format.DateTimeParserBucket, java.lang.CharSequence, int):int");
        }

        private int digitCount(CharSequence charSequence, int i, int i2) {
            int i3 = 0;
            for (int min = Math.min(charSequence.length() - i, i2); min > 0; min--) {
                char charAt = charSequence.charAt(i + i3);
                if (charAt < '0' || charAt > '9') {
                    break;
                }
                i3++;
            }
            return i3;
        }
    }

    static class TwoDigitYear implements InternalParser, InternalPrinter {
        private final boolean iLenientParse;
        private final int iPivot;
        private final DateTimeFieldType iType;

        TwoDigitYear(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            this.iType = dateTimeFieldType;
            this.iPivot = i;
            this.iLenientParse = z;
        }

        public int estimateParsedLength() {
            return this.iLenientParse ? 4 : 2;
        }

        public int parseInto(DateTimeParserBucket dateTimeParserBucket, CharSequence charSequence, int i) {
            int i2;
            int i3;
            int i4;
            int i5;
            boolean z;
            int i6 = 0;
            int length = charSequence.length() - i;
            if (this.iLenientParse) {
                int i7 = 0;
                boolean z2 = false;
                boolean z3 = false;
                int i8 = length;
                while (i7 < i8) {
                    char charAt = charSequence.charAt(i + i7);
                    if (i7 != 0 || (charAt != '-' && charAt != '+')) {
                        if (charAt < '0' || charAt > '9') {
                            break;
                        }
                        i7++;
                    } else {
                        if (charAt == '-') {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (z) {
                            i7++;
                            z2 = z;
                            z3 = true;
                        } else {
                            i++;
                            z3 = true;
                            i8--;
                            z2 = z;
                        }
                    }
                }
                if (i7 == 0) {
                    return i ^ -1;
                }
                if (z3 || i7 != 2) {
                    if (i7 >= 9) {
                        i4 = i + i7;
                        i5 = Integer.parseInt(charSequence.subSequence(i, i4).toString());
                    } else {
                        if (z2) {
                            i3 = i + 1;
                        } else {
                            i3 = i;
                        }
                        int i9 = i3 + 1;
                        try {
                            int charAt2 = charSequence.charAt(i3) - '0';
                            i4 = i + i7;
                            int i10 = i9;
                            i5 = charAt2;
                            int i11 = i10;
                            while (i11 < i4) {
                                int i12 = (i5 << 3) + (i5 << 1);
                                int i13 = i11 + 1;
                                i5 = (charSequence.charAt(i11) + i12) - 48;
                                i11 = i13;
                            }
                            if (z2) {
                                i5 = -i5;
                            }
                        } catch (StringIndexOutOfBoundsException e) {
                            return i ^ -1;
                        }
                    }
                    dateTimeParserBucket.saveField(this.iType, i5);
                    return i4;
                }
            } else if (Math.min(2, length) < 2) {
                return i ^ -1;
            }
            char charAt3 = charSequence.charAt(i);
            if (charAt3 < '0' || charAt3 > '9') {
                return i ^ -1;
            }
            int i14 = charAt3 - '0';
            char charAt4 = charSequence.charAt(i + 1);
            if (charAt4 < '0' || charAt4 > '9') {
                return i ^ -1;
            }
            int i15 = (((i14 << 1) + (i14 << 3)) + charAt4) - 48;
            int i16 = this.iPivot;
            if (dateTimeParserBucket.getPivotYear() != null) {
                i16 = dateTimeParserBucket.getPivotYear().intValue();
            }
            int i17 = i16 - 50;
            if (i17 >= 0) {
                i2 = i17 % 100;
            } else {
                i2 = ((i17 + 1) % 100) + 99;
            }
            if (i15 < i2) {
                i6 = 100;
            }
            dateTimeParserBucket.saveField(this.iType, ((i6 + i17) - i2) + i15);
            return i + 2;
        }

        public int estimatePrintedLength() {
            return 2;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            int twoDigitYear = getTwoDigitYear(j, chronology);
            if (twoDigitYear < 0) {
                appendable.append(65533);
                appendable.append(65533);
                return;
            }
            FormatUtils.appendPaddedInteger(appendable, twoDigitYear, 2);
        }

        private int getTwoDigitYear(long j, Chronology chronology) {
            try {
                int i = this.iType.getField(chronology).get(j);
                if (i < 0) {
                    i = -i;
                }
                return i % 100;
            } catch (RuntimeException e) {
                return -1;
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            int twoDigitYear = getTwoDigitYear(readablePartial);
            if (twoDigitYear < 0) {
                appendable.append(65533);
                appendable.append(65533);
                return;
            }
            FormatUtils.appendPaddedInteger(appendable, twoDigitYear, 2);
        }

        private int getTwoDigitYear(ReadablePartial readablePartial) {
            if (readablePartial.isSupported(this.iType)) {
                try {
                    int i = readablePartial.get(this.iType);
                    if (i < 0) {
                        i = -i;
                    }
                    return i % 100;
                } catch (RuntimeException e) {
                }
            }
            return -1;
        }
    }

    static class UnpaddedNumber extends NumberFormatter {
        protected UnpaddedNumber(DateTimeFieldType dateTimeFieldType, int i, boolean z) {
            super(dateTimeFieldType, i, z);
        }

        public int estimatePrintedLength() {
            return this.iMaxParsedDigits;
        }

        public void printTo(Appendable appendable, long j, Chronology chronology, int i, DateTimeZone dateTimeZone, Locale locale) throws IOException {
            try {
                FormatUtils.appendUnpaddedInteger(appendable, this.iFieldType.getField(chronology).get(j));
            } catch (RuntimeException e) {
                appendable.append(65533);
            }
        }

        public void printTo(Appendable appendable, ReadablePartial readablePartial, Locale locale) throws IOException {
            if (readablePartial.isSupported(this.iFieldType)) {
                try {
                    FormatUtils.appendUnpaddedInteger(appendable, readablePartial.get(this.iFieldType));
                } catch (RuntimeException e) {
                    appendable.append(65533);
                }
            } else {
                appendable.append(65533);
            }
        }
    }

    public DateTimeFormatter toFormatter() {
        InternalPrinter internalPrinter;
        InternalParser internalParser;
        Object formatter = getFormatter();
        if (isPrinter(formatter)) {
            internalPrinter = (InternalPrinter) formatter;
        } else {
            internalPrinter = null;
        }
        if (isParser(formatter)) {
            internalParser = (InternalParser) formatter;
        } else {
            internalParser = null;
        }
        if (internalPrinter != null || internalParser != null) {
            return new DateTimeFormatter(internalPrinter, internalParser);
        }
        throw new UnsupportedOperationException("Both printing and parsing not supported");
    }

    public DateTimeParser toParser() {
        Object formatter = getFormatter();
        if (isParser(formatter)) {
            return InternalParserDateTimeParser.m3955of((InternalParser) formatter);
        }
        throw new UnsupportedOperationException("Parsing is not supported");
    }

    public DateTimeFormatterBuilder append(DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter != null) {
            return append0(dateTimeFormatter.getPrinter0(), dateTimeFormatter.getParser0());
        }
        throw new IllegalArgumentException("No formatter supplied");
    }

    public DateTimeFormatterBuilder append(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, DateTimeParserInternalParser.m3953of(dateTimeParser));
    }

    public DateTimeFormatterBuilder append(DateTimePrinter dateTimePrinter, DateTimeParser[] dateTimeParserArr) {
        int i = 0;
        if (dateTimePrinter != null) {
            checkPrinter(dateTimePrinter);
        }
        if (dateTimeParserArr == null) {
            throw new IllegalArgumentException("No parsers supplied");
        }
        int length = dateTimeParserArr.length;
        if (length != 1) {
            InternalParser[] internalParserArr = new InternalParser[length];
            while (i < length - 1) {
                InternalParser of = DateTimeParserInternalParser.m3953of(dateTimeParserArr[i]);
                internalParserArr[i] = of;
                if (of == null) {
                    throw new IllegalArgumentException("Incomplete parser array");
                }
                i++;
            }
            internalParserArr[i] = DateTimeParserInternalParser.m3953of(dateTimeParserArr[i]);
            return append0(DateTimePrinterInternalPrinter.m3954of(dateTimePrinter), new MatchingParser(internalParserArr));
        } else if (dateTimeParserArr[0] != null) {
            return append0(DateTimePrinterInternalPrinter.m3954of(dateTimePrinter), DateTimeParserInternalParser.m3953of(dateTimeParserArr[0]));
        } else {
            throw new IllegalArgumentException("No parser supplied");
        }
    }

    public DateTimeFormatterBuilder appendOptional(DateTimeParser dateTimeParser) {
        checkParser(dateTimeParser);
        return append0(null, new MatchingParser(new InternalParser[]{DateTimeParserInternalParser.m3953of(dateTimeParser), null}));
    }

    private void checkParser(DateTimeParser dateTimeParser) {
        if (dateTimeParser == null) {
            throw new IllegalArgumentException("No parser supplied");
        }
    }

    private void checkPrinter(DateTimePrinter dateTimePrinter) {
        if (dateTimePrinter == null) {
            throw new IllegalArgumentException("No printer supplied");
        }
    }

    private DateTimeFormatterBuilder append0(Object obj) {
        this.iFormatter = null;
        this.iElementPairs.add(obj);
        this.iElementPairs.add(obj);
        return this;
    }

    private DateTimeFormatterBuilder append0(InternalPrinter internalPrinter, InternalParser internalParser) {
        this.iFormatter = null;
        this.iElementPairs.add(internalPrinter);
        this.iElementPairs.add(internalParser);
        return this;
    }

    public DateTimeFormatterBuilder appendLiteral(char c) {
        return append0(new CharacterLiteral(c));
    }

    public DateTimeFormatterBuilder appendLiteral(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Literal must not be null");
        }
        switch (str.length()) {
            case 0:
                return this;
            case 1:
                return append0(new CharacterLiteral(str.charAt(0)));
            default:
                return append0(new StringLiteral(str));
        }
    }

    public DateTimeFormatterBuilder appendDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        } else if (i <= 1) {
            return append0(new UnpaddedNumber(dateTimeFieldType, i2, false));
        } else {
            return append0(new PaddedNumber(dateTimeFieldType, i2, false, i));
        }
    }

    public DateTimeFormatterBuilder appendFixedDecimal(DateTimeFieldType dateTimeFieldType, int i) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        } else if (i > 0) {
            return append0(new FixedNumber(dateTimeFieldType, i, false));
        } else {
            throw new IllegalArgumentException("Illegal number of digits: " + i);
        }
    }

    public DateTimeFormatterBuilder appendSignedDecimal(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i < 0 || i2 <= 0) {
            throw new IllegalArgumentException();
        } else if (i <= 1) {
            return append0(new UnpaddedNumber(dateTimeFieldType, i2, true));
        } else {
            return append0(new PaddedNumber(dateTimeFieldType, i2, true, i));
        }
    }

    public DateTimeFormatterBuilder appendText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType != null) {
            return append0(new TextField(dateTimeFieldType, false));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendShortText(DateTimeFieldType dateTimeFieldType) {
        if (dateTimeFieldType != null) {
            return append0(new TextField(dateTimeFieldType, true));
        }
        throw new IllegalArgumentException("Field type must not be null");
    }

    public DateTimeFormatterBuilder appendFraction(DateTimeFieldType dateTimeFieldType, int i, int i2) {
        if (dateTimeFieldType == null) {
            throw new IllegalArgumentException("Field type must not be null");
        }
        if (i2 < i) {
            i2 = i;
        }
        if (i >= 0 && i2 > 0) {
            return append0(new Fraction(dateTimeFieldType, i, i2));
        }
        throw new IllegalArgumentException();
    }

    public DateTimeFormatterBuilder appendFractionOfSecond(int i, int i2) {
        return appendFraction(DateTimeFieldType.secondOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfMinute(int i, int i2) {
        return appendFraction(DateTimeFieldType.minuteOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendFractionOfHour(int i, int i2) {
        return appendFraction(DateTimeFieldType.hourOfDay(), i, i2);
    }

    public DateTimeFormatterBuilder appendSecondOfMinute(int i) {
        return appendDecimal(DateTimeFieldType.secondOfMinute(), i, 2);
    }

    public DateTimeFormatterBuilder appendMinuteOfHour(int i) {
        return appendDecimal(DateTimeFieldType.minuteOfHour(), i, 2);
    }

    public DateTimeFormatterBuilder appendHourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.hourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfDay(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfDay(), i, 2);
    }

    public DateTimeFormatterBuilder appendHourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.hourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendClockhourOfHalfday(int i) {
        return appendDecimal(DateTimeFieldType.clockhourOfHalfday(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfWeek(int i) {
        return appendDecimal(DateTimeFieldType.dayOfWeek(), i, 1);
    }

    public DateTimeFormatterBuilder appendDayOfMonth(int i) {
        return appendDecimal(DateTimeFieldType.dayOfMonth(), i, 2);
    }

    public DateTimeFormatterBuilder appendDayOfYear(int i) {
        return appendDecimal(DateTimeFieldType.dayOfYear(), i, 3);
    }

    public DateTimeFormatterBuilder appendWeekOfWeekyear(int i) {
        return appendDecimal(DateTimeFieldType.weekOfWeekyear(), i, 2);
    }

    public DateTimeFormatterBuilder appendWeekyear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.weekyear(), i, i2);
    }

    public DateTimeFormatterBuilder appendMonthOfYear(int i) {
        return appendDecimal(DateTimeFieldType.monthOfYear(), i, 2);
    }

    public DateTimeFormatterBuilder appendYear(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.year(), i, i2);
    }

    public DateTimeFormatterBuilder appendTwoDigitYear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.year(), i, z));
    }

    public DateTimeFormatterBuilder appendTwoDigitWeekyear(int i, boolean z) {
        return append0(new TwoDigitYear(DateTimeFieldType.weekyear(), i, z));
    }

    public DateTimeFormatterBuilder appendYearOfEra(int i, int i2) {
        return appendDecimal(DateTimeFieldType.yearOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendCenturyOfEra(int i, int i2) {
        return appendSignedDecimal(DateTimeFieldType.centuryOfEra(), i, i2);
    }

    public DateTimeFormatterBuilder appendHalfdayOfDayText() {
        return appendText(DateTimeFieldType.halfdayOfDay());
    }

    public DateTimeFormatterBuilder appendDayOfWeekText() {
        return appendText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendDayOfWeekShortText() {
        return appendShortText(DateTimeFieldType.dayOfWeek());
    }

    public DateTimeFormatterBuilder appendMonthOfYearText() {
        return appendText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendMonthOfYearShortText() {
        return appendShortText(DateTimeFieldType.monthOfYear());
    }

    public DateTimeFormatterBuilder appendEraText() {
        return appendText(DateTimeFieldType.era());
    }

    public DateTimeFormatterBuilder appendTimeZoneName() {
        return append0(new TimeZoneName(0, null), null);
    }

    public DateTimeFormatterBuilder appendTimeZoneShortName(Map<String, DateTimeZone> map) {
        TimeZoneName timeZoneName = new TimeZoneName(1, map);
        return append0(timeZoneName, timeZoneName);
    }

    public DateTimeFormatterBuilder appendTimeZoneId() {
        return append0(TimeZoneId.INSTANCE, TimeZoneId.INSTANCE);
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str, z, i, i2));
    }

    public DateTimeFormatterBuilder appendTimeZoneOffset(String str, String str2, boolean z, int i, int i2) {
        return append0(new TimeZoneOffset(str, str2, z, i, i2));
    }

    private Object getFormatter() {
        Object obj = this.iFormatter;
        if (obj == null) {
            if (this.iElementPairs.size() == 2) {
                Object obj2 = this.iElementPairs.get(0);
                Object obj3 = this.iElementPairs.get(1);
                if (obj2 == null) {
                    obj = obj3;
                } else if (obj2 == obj3 || obj3 == null) {
                    obj = obj2;
                }
            }
            if (obj == null) {
                obj = new Composite(this.iElementPairs);
            }
            this.iFormatter = obj;
        }
        return obj;
    }

    private boolean isPrinter(Object obj) {
        if (!(obj instanceof InternalPrinter)) {
            return false;
        }
        if (obj instanceof Composite) {
            return ((Composite) obj).isPrinter();
        }
        return true;
    }

    private boolean isParser(Object obj) {
        if (!(obj instanceof InternalParser)) {
            return false;
        }
        if (obj instanceof Composite) {
            return ((Composite) obj).isParser();
        }
        return true;
    }

    static void appendUnknownString(Appendable appendable, int i) throws IOException {
        while (true) {
            i--;
            if (i >= 0) {
                appendable.append(65533);
            } else {
                return;
            }
        }
    }

    static boolean csStartsWith(CharSequence charSequence, int i, String str) {
        int length = str.length();
        if (charSequence.length() - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            if (charSequence.charAt(i + i2) != str.charAt(i2)) {
                return false;
            }
        }
        return true;
    }

    static boolean csStartsWithIgnoreCase(CharSequence charSequence, int i, String str) {
        int length = str.length();
        if (charSequence.length() - i < length) {
            return false;
        }
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = charSequence.charAt(i + i2);
            char charAt2 = str.charAt(i2);
            if (charAt != charAt2) {
                char upperCase = Character.toUpperCase(charAt);
                char upperCase2 = Character.toUpperCase(charAt2);
                if (!(upperCase == upperCase2 || Character.toLowerCase(upperCase) == Character.toLowerCase(upperCase2))) {
                    return false;
                }
            }
        }
        return true;
    }
}
