package com.airbnb.epoxy;

public interface GeneratedModel<T> {
    void handlePostBind(T t, int i);

    void handlePreBind(EpoxyViewHolder epoxyViewHolder, T t, int i);
}
