package com.airbnb.android.lib.adapters;

import android.support.p002v7.widget.RecyclerView.Adapter;
import android.support.p002v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.models.ReservationUser;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.lib.C0880R;
import java.util.ArrayList;
import java.util.List;

public class ReservationUserAdapter extends Adapter<ReservationUserViewHolder> {
    /* access modifiers changed from: private */
    public final OnClickListener onClickListener;
    private final ArrayList<ReservationUser> reservationUsers = new ArrayList<>();

    class ReservationUserViewHolder extends ViewHolder {
        @BindView
        ImageView closeIcon;
        @BindView
        TextView emailText;

        public ReservationUserViewHolder(ViewGroup parent) {
            super(LayoutInflater.from(parent.getContext()).inflate(C0880R.layout.list_item_reservation_user, parent, false));
            ButterKnife.bind(this, this.itemView);
            this.closeIcon.setOnClickListener(ReservationUserAdapter.this.onClickListener);
        }

        public void bind(ReservationUser user) {
            MiscUtils.showTextViewIfNeeded(this.emailText, user.getEmail());
            this.closeIcon.setTag(user);
        }
    }

    public class ReservationUserViewHolder_ViewBinding implements Unbinder {
        private ReservationUserViewHolder target;

        public ReservationUserViewHolder_ViewBinding(ReservationUserViewHolder target2, View source) {
            this.target = target2;
            target2.emailText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_email, "field 'emailText'", TextView.class);
            target2.closeIcon = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.share_itin_close_icon, "field 'closeIcon'", ImageView.class);
        }

        public void unbind() {
            ReservationUserViewHolder target2 = this.target;
            if (target2 == null) {
                throw new IllegalStateException("Bindings already cleared.");
            }
            this.target = null;
            target2.emailText = null;
            target2.closeIcon = null;
        }
    }

    public ReservationUserAdapter(OnClickListener onClickListener2) {
        this.onClickListener = onClickListener2;
    }

    public void setReservationUsers(List<ReservationUser> users) {
        this.reservationUsers.clear();
        this.reservationUsers.addAll(users);
        notifyDataSetChanged();
    }

    public ReservationUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ReservationUserViewHolder(parent);
    }

    public void onBindViewHolder(ReservationUserViewHolder holder, int position) {
        holder.bind((ReservationUser) this.reservationUsers.get(position));
    }

    public int getItemCount() {
        return this.reservationUsers.size();
    }

    public ArrayList<ReservationUser> getReservationUsers() {
        return new ArrayList<>(this.reservationUsers);
    }
}
