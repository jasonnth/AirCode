package com.airbnb.android.contentframework.controller;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.android.contentframework.C5709R;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics.PublishErrorType;
import com.airbnb.android.contentframework.ContentFrameworkUtil;
import com.airbnb.android.contentframework.data.StoryCreationImage;
import com.airbnb.android.contentframework.data.StoryPublishArguments;
import com.airbnb.android.contentframework.fragments.StoryCreationComposerFragment;
import com.airbnb.android.contentframework.fragments.StoryDetailViewFragment;
import com.airbnb.android.contentframework.requests.StoryCreationEditRequest;
import com.airbnb.android.contentframework.requests.StoryCreationPublishRequest;
import com.airbnb.android.contentframework.responses.StoryCreationPublishResponse;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.requests.ObservableRequestManager;
import com.airbnb.rxgroups.Preconditions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import p032rx.Observable;
import p032rx.android.schedulers.AndroidSchedulers;
import p032rx.schedulers.Schedulers;

public class StoryPublishController {
    private final Map<Long, List<String>> articleToImageNames = new HashMap();
    private final Context context;
    private final ObservableRequestManager requestManager;

    public StoryPublishController(Context context2, AirRequestInitializer airRequestInitializer) {
        this.context = context2;
        this.requestManager = ObservableRequestManager.onCreate(airRequestInitializer, null, null);
    }

    public void publish(StoryPublishArguments arguments) throws IllegalArgumentException {
        validateArguments(arguments);
        this.requestManager.adapt(new StoryCreationPublishRequest()).observeOn(Schedulers.m4048io()).subscribe(StoryPublishController$$Lambda$1.lambdaFactory$(this, arguments), StoryPublishController$$Lambda$2.lambdaFactory$(this, arguments));
    }

    /* access modifiers changed from: private */
    public void processAndUploadPhotos(long articleId, StoryPublishArguments arguments) {
        this.articleToImageNames.put(Long.valueOf(articleId), new ArrayList());
        List<String> processedPhotos = new ArrayList<>();
        Observable.from((Iterable<? extends T>) arguments.imageList()).concatMap(StoryPublishController$$Lambda$3.lambdaFactory$()).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(StoryPublishController$$Lambda$4.lambdaFactory$(processedPhotos), StoryPublishController$$Lambda$5.lambdaFactory$(this, articleId, arguments), StoryPublishController$$Lambda$6.lambdaFactory$(this, articleId, arguments, processedPhotos));
    }

    static /* synthetic */ Observable lambda$processAndUploadPhotos$2(StoryCreationImage image) {
        try {
            return Observable.just(ImageUploadProcessor.processPhotoForUpload(image));
        } catch (IOException e) {
            BugsnagWrapper.notify((Throwable) e);
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: private */
    public void uploadPhotos(long articleId, StoryPublishArguments arguments, List<String> photosForUpload) {
        Observable.from((Iterable<? extends T>) photosForUpload).concatMap(StoryPublishController$$Lambda$7.lambdaFactory$(this, articleId)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(StoryPublishController$$Lambda$8.lambdaFactory$(this, articleId), StoryPublishController$$Lambda$9.lambdaFactory$(this, articleId, arguments), StoryPublishController$$Lambda$10.lambdaFactory$(this, articleId, arguments));
    }

    /* access modifiers changed from: private */
    public void editStory(long articleId, StoryPublishArguments arguments) {
        String str;
        ObservableRequestManager observableRequestManager = this.requestManager;
        long listingId = arguments.appendix().listingId();
        String title = arguments.title();
        String body = arguments.body();
        List list = (List) this.articleToImageNames.get(Long.valueOf(articleId));
        if (arguments.placeTag() != null) {
            str = arguments.placeTag().getGooglePlaceId();
        } else {
            str = null;
        }
        observableRequestManager.adapt(new StoryCreationEditRequest(articleId, listingId, title, body, list, str)).subscribeOn(Schedulers.m4048io()).observeOn(AndroidSchedulers.mainThread()).subscribe(StoryPublishController$$Lambda$11.lambdaFactory$(this, arguments), StoryPublishController$$Lambda$12.lambdaFactory$(this, articleId, arguments));
    }

    static /* synthetic */ void lambda$editStory$10(StoryPublishController storyPublishController, StoryPublishArguments arguments, AirResponse response) {
        ContentFrameworkAnalytics.trackPublishSuccess(arguments);
        storyPublishController.launchIntent(StoryDetailViewFragment.forPartialArticle(storyPublishController.context, ((StoryCreationPublishResponse) response.body()).article, ContentFrameworkUtil.STORY_BACKGROUND_PUBLISHER, storyPublishController.context.getString(C5709R.string.story_creation_composer_publish_finished)));
    }

    private void validateArguments(StoryPublishArguments arguments) throws IllegalArgumentException {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4 = true;
        Preconditions.checkArgument(arguments != null, "Empty publish argument");
        if (arguments.imageList() == null || arguments.imageList().isEmpty()) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "Empty image list");
        if (!TextUtils.isEmpty(arguments.title())) {
            z2 = true;
        } else {
            z2 = false;
        }
        Preconditions.checkArgument(z2, "Empty title");
        if (!TextUtils.isEmpty(arguments.body())) {
            z3 = true;
        } else {
            z3 = false;
        }
        Preconditions.checkArgument(z3, "Missing body");
        if (arguments.appendix().listingId() <= 0) {
            z4 = false;
        }
        Preconditions.checkArgument(z4, "Missing listing id");
    }

    /* access modifiers changed from: private */
    public void recoverStoryInComposer(long articleId, StoryPublishArguments arguments, String getErrorMessage, PublishErrorType errorType) {
        this.articleToImageNames.remove(Long.valueOf(articleId));
        recoverStoryInComposer(arguments, getErrorMessage, errorType);
    }

    /* access modifiers changed from: private */
    public void recoverStoryInComposer(StoryPublishArguments arguments, String getErrorMessage, PublishErrorType errorType) {
        ContentFrameworkAnalytics.trackPublishError(arguments, errorType);
        launchIntent(StoryCreationComposerFragment.newIntentToContinueEdit(this.context, arguments, getErrorMessage));
    }

    private void launchIntent(Intent intent) {
        intent.addFlags(268435456);
        this.context.startActivity(intent);
    }
}
