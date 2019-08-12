package com.airbnb.android.core.calendar;

import android.support.p000v4.util.LongSparseArray;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.ListingCalendar;
import com.airbnb.android.core.models.NightCount;
import java.util.ArrayList;
import java.util.List;

public class CalendarStoreCache {
    private static final int CALENDAR_DAYS_INITIAL_CAPACITY = 180;
    private AirDateTime cacheResetTime;
    private final LongSparseArray<ListingCalendar> listingCalendarCache = new LongSparseArray<>();
    private final LongSparseArray<CalendarDays> listingCalendarDaysCache = new LongSparseArray<>();
    private final LongSparseArray<NightCount> nightsCountCache = new LongSparseArray<>();

    public static class CacheResponseWrapper {
        private final LongSparseArray<CalendarDays> calendarDaysByListingIds;
        private final boolean missingOrExpiredDays;
        private final LongSparseArray<NightCount> nightCountsByListingIds;

        CacheResponseWrapper(LongSparseArray<CalendarDays> calendarDaysByListingIds2, LongSparseArray<NightCount> nightCountsByListingIds2, boolean missingOrExpiredDays2) {
            this.calendarDaysByListingIds = calendarDaysByListingIds2;
            this.missingOrExpiredDays = missingOrExpiredDays2;
            this.nightCountsByListingIds = nightCountsByListingIds2;
        }

        public LongSparseArray<CalendarDays> getCalendarDaysByListingIds() {
            return this.calendarDaysByListingIds;
        }

        public LongSparseArray<NightCount> getNightCountsByListingIds() {
            return this.nightCountsByListingIds;
        }

        public boolean hasMissingOrExpiredDays() {
            return this.missingOrExpiredDays;
        }
    }

    public void clear() {
        this.listingCalendarDaysCache.clear();
        this.listingCalendarCache.clear();
        this.nightsCountCache.clear();
    }

    public void setCacheResetTime(AirDateTime cacheResetTime2) {
        this.cacheResetTime = cacheResetTime2;
    }

    public boolean updateCalendars(List<ListingCalendar> calendars, boolean responseForAllListings) {
        if (responseForAllListings) {
            this.listingCalendarCache.clear();
        }
        boolean anyChanges = false;
        for (ListingCalendar calendar : calendars) {
            if (updateDays(calendar.getListingId(), calendar.getCalendarDays())) {
                anyChanges = true;
            }
            calendar.clearCalendarDays();
            this.listingCalendarCache.put(calendar.getListingId(), calendar);
        }
        return anyChanges;
    }

    public void updateNightsCount(List<NightCount> nightCounts, boolean responseForAllListings) {
        if (responseForAllListings) {
            this.nightsCountCache.clear();
        }
        for (NightCount count : nightCounts) {
            this.nightsCountCache.put(count.getListingId(), count);
        }
    }

    public boolean updateDays(long listingId, List<CalendarDay> updatedDays) {
        CalendarDays listingCalendarDays = (CalendarDays) this.listingCalendarDaysCache.get(listingId);
        if (listingCalendarDays == null) {
            listingCalendarDays = new CalendarDays(listingId, 180);
            this.listingCalendarDaysCache.put(listingId, listingCalendarDays);
        }
        return listingCalendarDays.add(updatedDays);
    }

    public ListingCalendar getListingCalendar(long listingId) {
        return (ListingCalendar) this.listingCalendarCache.get(listingId);
    }

    public List<ListingCalendar> getListingCalendars() {
        List<ListingCalendar> listingCalendars = new ArrayList<>();
        for (int i = 0; i < this.listingCalendarCache.size(); i++) {
            listingCalendars.add(this.listingCalendarCache.valueAt(i));
        }
        return listingCalendars;
    }

    /* JADX WARNING: type inference failed for: r17v0, types: [java.util.Set, java.util.Set<java.lang.Long>] */
    /* JADX WARNING: type inference failed for: r17v1, types: [java.util.Set] */
    /* JADX WARNING: type inference failed for: r0v5, types: [java.util.HashSet] */
    /* JADX WARNING: type inference failed for: r0v8, types: [java.util.Set] */
    /* JADX WARNING: type inference failed for: r0v9, types: [java.util.HashSet] */
    /*  JADX ERROR: UnsupportedOperationException in pass: LoopRegionVisitor
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:438)
        	at jadx.core.dex.visitors.regions.LoopRegionVisitor.fixIterableType(LoopRegionVisitor.java:334)
        	at jadx.core.dex.visitors.regions.LoopRegionVisitor.checkIterableForEach(LoopRegionVisitor.java:270)
        	at jadx.core.dex.visitors.regions.LoopRegionVisitor.processLoopRegion(LoopRegionVisitor.java:75)
        	at jadx.core.dex.visitors.regions.LoopRegionVisitor.enterRegion(LoopRegionVisitor.java:59)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:56)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:57)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:57)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:18)
        	at jadx.core.dex.visitors.regions.LoopRegionVisitor.visit(LoopRegionVisitor.java:53)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
        	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
        	at jadx.core.ProcessClass.process(ProcessClass.java:30)
        	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
        	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
        	at jadx.core.ProcessClass.process(ProcessClass.java:35)
        	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
        	at jadx.api.JavaClass.decompile(JavaClass.java:62)
        	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
        */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v9, types: [java.util.HashSet]
      assigns: [java.util.HashSet, java.util.Set<java.lang.Long>]
      uses: [java.util.HashSet, java.util.Set]
      mth insns count: 59
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    public com.airbnb.android.core.calendar.CalendarStoreCache.CacheResponseWrapper retrieveFromCache(
/*
Method generation error in method: com.airbnb.android.core.calendar.CalendarStoreCache.retrieveFromCache(java.util.Set, com.airbnb.android.airdate.AirDate, com.airbnb.android.airdate.AirDate, int):com.airbnb.android.core.calendar.CalendarStoreCache$CacheResponseWrapper, dex: classes20.dex
    java.lang.NullPointerException
    	at jadx.core.codegen.ClassGen.useType(ClassGen.java:442)
    	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:185)
    	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:129)
    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:311)
    	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:262)
    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:225)
    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:110)
    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:76)
    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:32)
    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:20)
    	at jadx.core.ProcessClass.process(ProcessClass.java:36)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
    
*/

    private AirDateTime getCacheExpirationTime(int cacheTTLMinutes) {
        AirDateTime expirationTime = AirDateTime.now().plusMinutes(-cacheTTLMinutes);
        if (this.cacheResetTime == null || !this.cacheResetTime.isAfter(expirationTime)) {
            return expirationTime;
        }
        return this.cacheResetTime;
    }
}
