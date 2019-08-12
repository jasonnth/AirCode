package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.ReservationUser;
import com.airbnb.android.core.requests.DeleteReservationUserRequest;
import com.airbnb.android.core.requests.ReservationUsersRequest;
import com.airbnb.android.core.responses.ReservationUserResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.InviteGuestsActivity;
import com.airbnb.android.lib.adapters.InviteGuestsAdapter;
import com.airbnb.android.lib.adapters.InviteGuestsAdapter.InviteGuestsAdapterCallbacks;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;

public class InviteGuestsFragment extends AirFragment implements InviteGuestsAdapterCallbacks {
    private static final String CONFIRMATION_CODE = "conf_code";
    private static final int DIALOG_REQ_DELETE_CONFIRM = 5530;
    private InviteGuestsAdapter adapter;
    private String confirmationCode;
    final RequestListener<Object> deleteUserListener = new C0699RL().onResponse(InviteGuestsFragment$$Lambda$3.lambdaFactory$(this)).onError(InviteGuestsFragment$$Lambda$4.lambdaFactory$(this)).build();
    final RequestListener<ReservationUserResponse> listener = new C0699RL().onResponse(InviteGuestsFragment$$Lambda$1.lambdaFactory$(this)).onError(InviteGuestsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;

    public static InviteGuestsFragment newInstance(String confirmationCode2) {
        return (InviteGuestsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new InviteGuestsFragment()).putString(CONFIRMATION_CODE, confirmationCode2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.confirmationCode = Check.notEmpty(getArguments().getString(CONFIRMATION_CODE), "need reservation confirmation code");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_invite_guests, container, false);
        bindViews(view);
        this.adapter = new InviteGuestsAdapter(this);
        this.recyclerView.setAdapter(this.adapter);
        loadReservationUsers();
        return view;
    }

    private void loadReservationUsers() {
        new ReservationUsersRequest(this.confirmationCode, this.listener).execute(this.requestManager);
    }

    public void goToInvite() {
        ((InviteGuestsActivity) getActivity()).showFragment(InviteGuestSelectFragment.newInstance(this.confirmationCode));
    }

    public void removeGuest(ReservationUser user) {
        DeleteReservationUserDialog.newInstance(user, DIALOG_REQ_DELETE_CONFIRM, this).show(getFragmentManager(), (String) null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DIALOG_REQ_DELETE_CONFIRM) {
            deleteReservationUser(data.getLongExtra(DeleteReservationUserDialog.KEY_RESERVATION_USER_ID, 0));
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void deleteReservationUser(long reservationUserId) {
        if (reservationUserId <= 0) {
            BugsnagWrapper.notify(new Throwable("attempting to delete an invalid reservation user id" + reservationUserId));
        } else {
            new DeleteReservationUserRequest(reservationUserId, this.deleteUserListener).execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$2(InviteGuestsFragment inviteGuestsFragment, Object response) {
        if (inviteGuestsFragment.getActivity() != null) {
            inviteGuestsFragment.loadReservationUsers();
        }
    }

    static /* synthetic */ void lambda$new$3(InviteGuestsFragment inviteGuestsFragment, AirRequestNetworkException e) {
        NetworkUtil.toastGenericNetworkError(inviteGuestsFragment.getActivity());
        if (e.statusCode() == 404 && inviteGuestsFragment.getActivity() != null) {
            inviteGuestsFragment.loadReservationUsers();
        }
    }
}
