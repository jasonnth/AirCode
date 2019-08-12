package com.airbnb.android.cityregistration.controller;

import android.net.Uri;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.cityregistration.executor.CityRegistrationActionExecutor;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingRegistrationProcess;
import com.airbnb.android.core.models.ListingRegistrationProcessInputGroup;
import com.airbnb.android.core.models.ListingRegistrationQuestion;

public interface CityRegistrationController {
    public static final int RC_CHOOSE_PHOTO = 202;

    void deleteDoc(ListingRegistrationQuestion listingRegistrationQuestion);

    void finishCancel();

    void finishEdit(boolean z, String str);

    void finishOk();

    void finishSaveAndExit();

    CityRegistrationActionExecutor getActionExecutor();

    AirAddress getAddress(ListingRegistrationProcessInputGroup listingRegistrationProcessInputGroup);

    String getDocFileUrl(String str);

    void getDocPhoto(String str);

    ListingRegistrationProcessInputGroup getInputGroupFromIndex(int i);

    Listing getListing();

    ListingRegistrationProcess getListingRegistrationProcess();

    void handleImageCapture(Uri uri, ListingRegistrationQuestion listingRegistrationQuestion, String str);

    boolean isLYS();

    void saveAddress(ListingRegistrationQuestion listingRegistrationQuestion, AirAddress airAddress);

    void saveDoc(ListingRegistrationQuestion listingRegistrationQuestion, String str);

    void saveTextAnswers(ListingRegistrationProcessInputGroup listingRegistrationProcessInputGroup);

    void setListing(Listing listing);

    void setListingRegistrationProcess(ListingRegistrationProcess listingRegistrationProcess);

    boolean shouldUpdateInputGroup(String str);

    void showDocTypeSelection(ListingRegistrationQuestion listingRegistrationQuestion);

    void showDocUploadReviewWithFilePath(ListingRegistrationQuestion listingRegistrationQuestion, String str);

    void showDocUploadReviewWithUrl(ListingRegistrationQuestion listingRegistrationQuestion, String str);

    void showInputGroupModal(ListingRegistrationProcessInputGroup listingRegistrationProcessInputGroup);

    void showModal(Fragment fragment);
}
