package com.airbnb.p027n2;

/* renamed from: com.airbnb.n2.DLSMockAdapter */
public interface DLSMockAdapter<T> {
    void bindView(T t, int i);

    int getDefaultPosition();

    int getItemCount();

    String getName(int i);

    DLSStyleWrapper getStyle(int i);
}
