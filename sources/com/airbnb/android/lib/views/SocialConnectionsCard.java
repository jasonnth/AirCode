package com.airbnb.android.lib.views;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.airbnb.android.core.models.SocialConnection;
import com.airbnb.android.core.responses.SocialConnectionsResponse;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;
import java.util.List;

public class SocialConnectionsCard extends LinearLayout {
    private static final int TYPE_FRIENDS = 2;
    private static final int TYPE_INVALID = -1;
    private static final int TYPE_MIXED = 1000;
    private static final int TYPE_REVIEW = 1;
    private SocialConnectionsResponse mConnectionsRequest;
    private boolean mShowBottomDivider;
    private boolean mShowTopDivider;

    public SocialConnectionsCard(Context context, SocialConnectionsResponse connectionRequest, boolean showTopDivider, boolean showBottomDivider) {
        super(context);
        if (connectionRequest == null || connectionRequest.connections == null) {
            throw new IllegalArgumentException("Connections cannot be null");
        }
        this.mConnectionsRequest = connectionRequest;
        this.mShowTopDivider = showTopDivider;
        this.mShowBottomDivider = showBottomDivider;
        setupViews();
    }

    public SocialConnectionsCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupViews();
    }

    private void setupViews() {
        int numConnections = this.mConnectionsRequest.getNumConnections();
        LayoutInflater.from(getContext()).inflate(C0880R.layout.social_connections_card_one, this);
        SocialConnection firstConnection = (SocialConnection) this.mConnectionsRequest.connections.get(0);
        ((TextView) ButterKnife.findById((View) this, C0880R.C0882id.txt_connections_header)).setText(getResources().getQuantityString(C0880R.plurals.x_connections, numConnections, new Object[]{Integer.valueOf(numConnections)}));
        ((HaloImageView) ButterKnife.findById((View) this, C0880R.C0882id.img_connection)).setImageUrl(firstConnection.getPicUrlLarge());
        ((TextView) ButterKnife.findById((View) this, C0880R.C0882id.txt_connections_caption)).setText(this.mConnectionsRequest.getCaption() != null ? this.mConnectionsRequest.getCaption() : generateTopLevelCaption());
        ViewUtils.setVisibleIf((FloatingActionButton) ButterKnife.findById((View) this, C0880R.C0882id.top_divider), this.mShowTopDivider);
        ViewUtils.setVisibleIf((FloatingActionButton) ButterKnife.findById((View) this, C0880R.C0882id.bottom_divider), this.mShowBottomDivider);
    }

    private String generateTopLevelCaption() {
        int resId;
        int resId2;
        String targetUserName = this.mConnectionsRequest.targetUserName;
        List<SocialConnection> connections = this.mConnectionsRequest.connections;
        int connectionType = -1;
        for (SocialConnection connection : connections) {
            int type = connection.getConnectionType();
            if (connectionType == -1) {
                connectionType = type;
            } else if (connectionType != type) {
                connectionType = 1000;
            }
        }
        int connectionsCount = connections.size();
        SocialConnection firstConnection = (SocialConnection) connections.get(0);
        String firstConnectionName = firstConnection.getConnectionName();
        if (connectionsCount == 1) {
            return firstConnection.getCaption();
        }
        if (connectionsCount == 2) {
            if (connectionType == 1) {
                resId2 = C0880R.string.connections_caption_friends_two;
            } else if (connectionType == 1) {
                resId2 = C0880R.string.connections_caption_reviewed_two;
            } else {
                resId2 = C0880R.string.connections_caption_connected_two;
            }
            String secondConnectionName = ((SocialConnection) connections.get(1)).getConnectionName();
            return getResources().getString(resId2, new Object[]{firstConnectionName, secondConnectionName, targetUserName});
        }
        if (connectionType == 2) {
            resId = C0880R.plurals.connections_caption_friends;
        } else if (connectionType == 1) {
            resId = C0880R.plurals.connections_caption_reviewed;
        } else {
            resId = C0880R.plurals.connections_caption_connected;
        }
        int displayCount = connectionsCount - 1;
        return getResources().getQuantityString(resId, displayCount, new Object[]{firstConnectionName, Integer.valueOf(displayCount), targetUserName});
    }
}
