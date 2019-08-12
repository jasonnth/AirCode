package retrofit2.adapter.rxjava;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;
import p032rx.Observable;
import p032rx.Observable.OnSubscribe;
import p032rx.Producer;
import p032rx.Scheduler;
import p032rx.Subscriber;
import p032rx.Subscription;
import p032rx.exceptions.Exceptions;
import p032rx.functions.Func1;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.CallAdapter.Factory;
import retrofit2.Response;
import retrofit2.Retrofit;

public final class RxJavaCallAdapterFactory extends Factory {
    private final Scheduler scheduler;

    static final class CallOnSubscribe<T> implements OnSubscribe<Response<T>> {
        private final Call<T> originalCall;

        CallOnSubscribe(Call<T> originalCall2) {
            this.originalCall = originalCall2;
        }

        public void call(Subscriber<? super Response<T>> subscriber) {
            RequestArbiter<T> requestArbiter = new RequestArbiter<>(this.originalCall.clone(), subscriber);
            subscriber.add(requestArbiter);
            subscriber.setProducer(requestArbiter);
        }
    }

    static final class RequestArbiter<T> extends AtomicBoolean implements Producer, Subscription {
        private final Call<T> call;
        private final Subscriber<? super Response<T>> subscriber;

        RequestArbiter(Call<T> call2, Subscriber<? super Response<T>> subscriber2) {
            this.call = call2;
            this.subscriber = subscriber2;
        }

        public void request(long n) {
            if (n < 0) {
                throw new IllegalArgumentException("n < 0: " + n);
            } else if (n != 0 && compareAndSet(false, true)) {
                try {
                    Response<T> response = this.call.execute();
                    if (!this.subscriber.isUnsubscribed()) {
                        this.subscriber.onNext(response);
                    }
                    if (!this.subscriber.isUnsubscribed()) {
                        this.subscriber.onCompleted();
                    }
                } catch (Throwable t) {
                    Exceptions.throwIfFatal(t);
                    if (!this.subscriber.isUnsubscribed()) {
                        this.subscriber.onError(t);
                    }
                }
            }
        }

        public void unsubscribe() {
            this.call.cancel();
        }

        public boolean isUnsubscribed() {
            return this.call.isCanceled();
        }
    }

    static final class ResponseCallAdapter implements CallAdapter<Observable<?>> {
        private final Type responseType;
        private final Scheduler scheduler;

        ResponseCallAdapter(Type responseType2, Scheduler scheduler2) {
            this.responseType = responseType2;
            this.scheduler = scheduler2;
        }

        public Type responseType() {
            return this.responseType;
        }

        public <R> Observable<Response<R>> adapt(Call<R> call) {
            Observable<Response<R>> observable = Observable.create(new CallOnSubscribe(call));
            if (this.scheduler != null) {
                return observable.subscribeOn(this.scheduler);
            }
            return observable;
        }
    }

    static final class ResultCallAdapter implements CallAdapter<Observable<?>> {
        private final Type responseType;
        private final Scheduler scheduler;

        ResultCallAdapter(Type responseType2, Scheduler scheduler2) {
            this.responseType = responseType2;
            this.scheduler = scheduler2;
        }

        public Type responseType() {
            return this.responseType;
        }

        public <R> Observable<Result<R>> adapt(Call<R> call) {
            Observable<Result<R>> observable = Observable.create(new CallOnSubscribe(call)).map(new Func1<Response<R>, Result<R>>() {
                public Result<R> call(Response<R> response) {
                    return Result.response(response);
                }
            }).onErrorReturn(new Func1<Throwable, Result<R>>() {
                public Result<R> call(Throwable throwable) {
                    return Result.error(throwable);
                }
            });
            if (this.scheduler != null) {
                return observable.subscribeOn(this.scheduler);
            }
            return observable;
        }
    }

    static final class SimpleCallAdapter implements CallAdapter<Observable<?>> {
        private final Type responseType;
        private final Scheduler scheduler;

        SimpleCallAdapter(Type responseType2, Scheduler scheduler2) {
            this.responseType = responseType2;
            this.scheduler = scheduler2;
        }

        public Type responseType() {
            return this.responseType;
        }

        public <R> Observable<R> adapt(Call<R> call) {
            Observable<R> observable = Observable.create(new CallOnSubscribe(call)).lift(OperatorMapResponseToBodyOrError.instance());
            if (this.scheduler != null) {
                return observable.subscribeOn(this.scheduler);
            }
            return observable;
        }
    }

    public static RxJavaCallAdapterFactory create() {
        return new RxJavaCallAdapterFactory(null);
    }

    public static RxJavaCallAdapterFactory createWithScheduler(Scheduler scheduler2) {
        if (scheduler2 != null) {
            return new RxJavaCallAdapterFactory(scheduler2);
        }
        throw new NullPointerException("scheduler == null");
    }

    private RxJavaCallAdapterFactory(Scheduler scheduler2) {
        this.scheduler = scheduler2;
    }

    public CallAdapter<?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        String canonicalName = rawType.getCanonicalName();
        boolean isSingle = "rx.Single".equals(canonicalName);
        boolean isCompletable = "rx.Completable".equals(canonicalName);
        if (rawType != Observable.class && !isSingle && !isCompletable) {
            return null;
        }
        if (!isCompletable && !(returnType instanceof ParameterizedType)) {
            String name = isSingle ? "Single" : "Observable";
            throw new IllegalStateException(name + " return type must be parameterized" + " as " + name + "<Foo> or " + name + "<? extends Foo>");
        } else if (isCompletable) {
            return CompletableHelper.createCallAdapter(this.scheduler);
        } else {
            CallAdapter<Observable<?>> callAdapter = getCallAdapter(returnType, this.scheduler);
            if (isSingle) {
                return SingleHelper.makeSingle(callAdapter);
            }
            return callAdapter;
        }
    }

    private CallAdapter<Observable<?>> getCallAdapter(Type returnType, Scheduler scheduler2) {
        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
        Class<?> rawObservableType = getRawType(observableType);
        if (rawObservableType == Response.class) {
            if (observableType instanceof ParameterizedType) {
                return new ResponseCallAdapter(getParameterUpperBound(0, (ParameterizedType) observableType), scheduler2);
            }
            throw new IllegalStateException("Response must be parameterized as Response<Foo> or Response<? extends Foo>");
        } else if (rawObservableType != Result.class) {
            return new SimpleCallAdapter(observableType, scheduler2);
        } else {
            if (observableType instanceof ParameterizedType) {
                return new ResultCallAdapter(getParameterUpperBound(0, (ParameterizedType) observableType), scheduler2);
            }
            throw new IllegalStateException("Result must be parameterized as Result<Foo> or Result<? extends Foo>");
        }
    }
}
