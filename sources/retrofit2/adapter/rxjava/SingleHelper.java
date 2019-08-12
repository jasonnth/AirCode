package retrofit2.adapter.rxjava;

import java.lang.reflect.Type;
import p032rx.Observable;
import p032rx.Single;
import retrofit2.Call;
import retrofit2.CallAdapter;

final class SingleHelper {
    SingleHelper() {
    }

    static CallAdapter<Single<?>> makeSingle(final CallAdapter<Observable<?>> callAdapter) {
        return new CallAdapter<Single<?>>() {
            public Type responseType() {
                return callAdapter.responseType();
            }

            public <R> Single<?> adapt(Call<R> call) {
                return ((Observable) callAdapter.adapt(call)).toSingle();
            }
        };
    }
}
