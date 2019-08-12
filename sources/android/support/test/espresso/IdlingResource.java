package android.support.test.espresso;

public interface IdlingResource {

    public interface ResourceCallback {
        void onTransitionToIdle();
    }
}
