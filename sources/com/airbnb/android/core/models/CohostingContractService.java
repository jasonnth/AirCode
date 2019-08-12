package com.airbnb.android.core.models;

import com.airbnb.android.core.C0716R;
import com.google.common.collect.FluentIterable;

public enum CohostingContractService {
    CREATE_LISTING(1, C0716R.string.cohosting_contract_service_item_create_a_listing),
    UPDATE_CALENDAR(2, C0716R.string.cohosting_contract_service_item_update_calendar),
    MESSAGE_WITH_GUESTS(3, C0716R.string.cohosting_contract_service_item_message_with_guests),
    HANDLE_RESERVATION(4, C0716R.string.cohosting_contract_service_item_handle_reservation),
    GET_HOME_READY(5, C0716R.string.cohosting_contract_service_item_get_home_ready),
    WELCOME_GUEST(6, C0716R.string.cohosting_contract_service_item_welcome_guests),
    RESPOND_TO_ISSUES(7, C0716R.string.cohosting_contract_service_item_respond_to_issues),
    REVIEW_GUESTS(8, C0716R.string.cohosting_contract_service_item_review_guests),
    RESTOCK(9, C0716R.string.cohosting_contract_service_item_restock),
    COORDINATE_CLEANING(10, C0716R.string.cohosting_contract_service_item_coordinate_cleaning),
    COORDINATION_MAINTAINANCE(11, C0716R.string.cohosting_contract_service_item_coordinate_maintenance);
    
    public int index;
    public final int titleRes;

    private CohostingContractService(int index2, int titleRes2) {
        this.index = index2;
        this.titleRes = titleRes2;
    }

    public static int getTitleFromIndex(int index2) {
        CohostingContractService service = (CohostingContractService) FluentIterable.from((E[]) values()).filter(CohostingContractService$$Lambda$1.lambdaFactory$(index2)).first().orNull();
        if (service != null) {
            return service.titleRes;
        }
        return 0;
    }

    public static int size() {
        return values().length;
    }
}
