package p315io.branch.indexing;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.google.firebase.appindexing.Action.Builder;
import com.google.firebase.appindexing.Action.Metadata;
import com.google.firebase.appindexing.FirebaseAppIndex;
import com.google.firebase.appindexing.FirebaseUserActions;
import com.google.firebase.appindexing.Indexable;
import com.google.firebase.appindexing.builders.Indexables;
import java.lang.reflect.Method;
import p315io.branch.referral.PrefHelper;
import p315io.branch.referral.util.LinkProperties;

/* renamed from: io.branch.indexing.AppIndexingHelper */
class AppIndexingHelper {
    /* access modifiers changed from: private */
    public static final LinkProperties DEF_LINK_PROPERTIES = new LinkProperties().setChannel("google_search");
    /* access modifiers changed from: private */
    public static FirebaseUserActions firebaseUserActionsInstance = null;

    static void addToAppIndex(final Context context, final BranchUniversalObject buo, final LinkProperties linkProperties) {
        new Thread(new Runnable() {
            public void run() {
                String contentUrl;
                try {
                    AppIndexingHelper.firebaseUserActionsInstance = FirebaseUserActions.getInstance();
                } catch (NoClassDefFoundError e) {
                    PrefHelper.Debug("BranchSDK", "Firebase app indexing is not available. Please consider enabling Firebase app indexing for your app for better indexing experience with Google.");
                } catch (Throwable th) {
                    PrefHelper.Debug("BranchSDK", "Failed to index your contents using Firebase. Please make sure Firebase  is enabled and initialised in your app");
                }
                if (linkProperties == null) {
                    contentUrl = buo.getShortUrl(context, AppIndexingHelper.DEF_LINK_PROPERTIES);
                } else {
                    contentUrl = buo.getShortUrl(context, linkProperties);
                }
                PrefHelper.Debug("BranchSDK", "Indexing BranchUniversalObject with Google using URL " + contentUrl);
                if (!TextUtils.isEmpty(contentUrl)) {
                    try {
                        if (AppIndexingHelper.firebaseUserActionsInstance != null) {
                            AppIndexingHelper.addToAppIndexUsingFirebase(contentUrl, buo);
                        } else {
                            AppIndexingHelper.listOnGoogleSearch(contentUrl, context, buo);
                        }
                    } catch (Throwable th2) {
                        PrefHelper.Debug("BranchSDK", "Branch Warning: Unable to list your content in Google search. Please make sure you have added latest Firebase app indexing SDK to your project dependencies.");
                    }
                }
            }
        }).run();
    }

    static void removeFromFirebaseLocalIndex(final Context context, final BranchUniversalObject buo, final LinkProperties linkProperties) {
        new Thread(new Runnable() {
            public void run() {
                String contentUrl;
                try {
                    if (linkProperties == null) {
                        contentUrl = buo.getShortUrl(context, AppIndexingHelper.DEF_LINK_PROPERTIES);
                    } else {
                        contentUrl = buo.getShortUrl(context, linkProperties);
                    }
                    PrefHelper.Debug("BranchSDK", "Removing indexed BranchUniversalObject with link " + contentUrl);
                    FirebaseAppIndex.getInstance().remove(contentUrl);
                } catch (NoClassDefFoundError e) {
                    PrefHelper.Debug("BranchSDK", "Failed to remove the BranchUniversalObject from Firebase local indexing. Please make sure Firebase is enabled and initialised in your app");
                } catch (Throwable th) {
                    PrefHelper.Debug("BranchSDK", "Failed to index your contents using Firebase. Please make sure Firebase is enabled and initialised in your app");
                }
            }
        }).run();
    }

    /* access modifiers changed from: private */
    public static void addToAppIndexUsingFirebase(String contentUrl, BranchUniversalObject buo) {
        String contentText = buo.getTitle() + "\n" + buo.getDescription();
        if (buo.isLocallyIndexable()) {
            Indexable buoToIndex = Indexables.newSimple(contentText, contentUrl);
            FirebaseAppIndex.getInstance().update(buoToIndex);
        }
        firebaseUserActionsInstance.end(new Builder("ViewAction").setObject(contentText, contentUrl).setMetadata(new Metadata.Builder().setUpload(buo.isPublicallyIndexable())).build());
    }

    /* access modifiers changed from: private */
    public static void listOnGoogleSearch(String shortLink, Context context, BranchUniversalObject branchUniversalObject) throws Exception {
        Class<?> ThingClass = Class.forName("com.google.android.gms.appindexing.Thing");
        Class<?> ThingBuilderClass = Class.forName("com.google.android.gms.appindexing.Thing$Builder");
        Object thingBuilder = ThingBuilderClass.getConstructor(new Class[0]).newInstance(new Object[0]);
        Method setNameMethod = ThingBuilderClass.getMethod("setName", new Class[]{String.class});
        Method setDescMethod = ThingBuilderClass.getMethod("setDescription", new Class[]{String.class});
        Method setUrlMethod = ThingBuilderClass.getMethod("setUrl", new Class[]{Uri.class});
        Method thingBuildMethod = ThingBuilderClass.getMethod("build", new Class[0]);
        setNameMethod.invoke(thingBuilder, new Object[]{branchUniversalObject.getTitle()});
        setDescMethod.invoke(thingBuilder, new Object[]{branchUniversalObject.getDescription()});
        setUrlMethod.invoke(thingBuilder, new Object[]{Uri.parse(shortLink)});
        Object thingObj = thingBuildMethod.invoke(thingBuilder, new Object[0]);
        Class<?> ThingActionClass = Class.forName("com.google.android.gms.appindexing.Action");
        Class<?> ThingActionBuilderClass = Class.forName("com.google.android.gms.appindexing.Action$Builder");
        Object actionBuilder = ThingActionBuilderClass.getConstructor(new Class[]{String.class}).newInstance(new Object[]{(String) ThingActionClass.getDeclaredField("TYPE_VIEW").get(null)});
        Method setObjectMethod = ThingActionBuilderClass.getMethod("setObject", new Class[]{ThingClass});
        Method setActionStatusMethod = ThingActionBuilderClass.getMethod("setActionStatus", new Class[]{String.class});
        Method actionBuildMethod = ThingActionBuilderClass.getMethod("build", new Class[0]);
        setObjectMethod.invoke(actionBuilder, new Object[]{thingObj});
        setActionStatusMethod.invoke(actionBuilder, new Object[]{(String) ThingActionClass.getDeclaredField("STATUS_TYPE_COMPLETED").get(null)});
        Object actionObj = actionBuildMethod.invoke(actionBuilder, new Object[0]);
        Class<?> AppIndexClass = Class.forName("com.google.android.gms.appindexing.AppIndex");
        Class<?> ApiClass = Class.forName("com.google.android.gms.common.api.Api");
        Class<?> GoogleApiClientClass = Class.forName("com.google.android.gms.common.api.GoogleApiClient");
        Class<?> GoogleApiClientBuilderClass = Class.forName("com.google.android.gms.common.api.GoogleApiClient$Builder");
        Object apiClientBuilder = GoogleApiClientBuilderClass.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{context});
        Method addApiMethod = GoogleApiClientBuilderClass.getMethod("addApi", new Class[]{ApiClass});
        Method apiClientBuildMethod = GoogleApiClientBuilderClass.getMethod("build", new Class[0]);
        Method apiClientConnectMethod = GoogleApiClientClass.getMethod("connect", new Class[0]);
        Method apiClientDisConnectMethod = GoogleApiClientClass.getMethod("disconnect", new Class[0]);
        addApiMethod.invoke(apiClientBuilder, new Object[]{ApiClass.cast(AppIndexClass.getDeclaredField("API").get(null))});
        Object googleApiClientApiClientObj = apiClientBuildMethod.invoke(apiClientBuilder, new Object[0]);
        apiClientConnectMethod.invoke(googleApiClientApiClientObj, new Object[0]);
        Class<?> AppIndexApiClass = Class.forName("com.google.android.gms.appindexing.AppIndexApi");
        Object appIndexApiObj = AppIndexClass.getDeclaredField("AppIndexApi").get(null);
        AppIndexApiClass.getMethod("start", new Class[]{GoogleApiClientClass, ThingActionClass}).invoke(appIndexApiObj, new Object[]{googleApiClientApiClientObj, actionObj});
        apiClientDisConnectMethod.invoke(googleApiClientApiClientObj, new Object[0]);
    }
}
