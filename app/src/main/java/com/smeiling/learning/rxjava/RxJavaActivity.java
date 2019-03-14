package com.smeiling.learning.rxjava;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.Logg;
import com.smeiling.learning.R;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/app/rxjava")
public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

//        funcAssemblyHook();
        funcCreate();
//        funcGroupJoin();

//        funcCompose();
    }

    /**
     * 基本操作符
     * 装饰者模式
     */
    private void funcCreate() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                Logg.error("CurrentThread = " + Thread.currentThread().getName());
                emitter.onNext("Observable Create");
                emitter.onNext(123);
            }
        }).map(new Function<Object, Object>() {
            @Override
            public Object apply(Object o) throws Exception {
                return o + "sml";
            }
        }).filter(new Predicate<Object>() {
            @Override
            public boolean test(Object o) throws Exception {
                Logg.error("test = " + o);
                if (((String) o).contains("123")) {
                    return false;
                } else {
                    return true;
                }
            }
        }).subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        Logg.error(o.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * RxJavaPlugin的Hook功能
     */
    private void funcAssemblyHook() {
        RxJavaPlugins.setOnObservableAssembly(new Function<Observable, Observable>() {
            @Override
            public Observable apply(Observable observable) throws Exception {
                Logg.error("Assembly hook test");
                return observable;
            }
        });
    }

    /**
     * 操作符
     */
    private void funcGroupJoin() {

        //产生字母的序列,周期为1000ms
        String[] words = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        Observable<String> observableA = Observable.interval(1000, TimeUnit.MILLISECONDS)
                .map(new Function<Long, String>() {
                    @Override
                    public String apply(Long aLong) throws Exception {
                        return words[aLong.intValue()];
                    }

                }).take(8);

        //产0,1,2,3,4,5,6,7的序列,延时500ms发射,周期为1000ms
        Observable<Long> observableB = Observable.interval(500, 1000, TimeUnit.MILLISECONDS)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) {
                        return aLong;
                    }
                }).take(words.length);

        observableA.groupJoin(observableB,
                new Function<String, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(String s) throws Exception {
                        return Observable.timer(600, TimeUnit.MILLISECONDS);
                    }

                },
                new Function<Long, ObservableSource<Long>>() {
                    @Override
                    public ObservableSource<Long> apply(Long aLong) {
                        return Observable.timer(600, TimeUnit.MILLISECONDS);
                    }
                },
                new BiFunction<String, Observable<Long>, Observable<String>>() {
                    @Override
                    public Observable<String> apply(String s, Observable<Long> longObservable) throws Exception {
                        return longObservable.map(new Function<Long, String>() {
                            @Override
                            public String apply(Long aLong) {
                                return s + aLong;
                            }
                        });
                    }
                })
                .subscribe(new Observer<Observable<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Observable<String> stringObservable) {
                        stringObservable.subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String s) {
                                Log.e("SMLSMLSML", "groupJoin：" + s);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }

                        });
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    /**
     * Compose操作符
     */
    private void funcCompose() {
        Observable.create((ObservableOnSubscribe<String>) emitter -> {
            emitter.onNext("subscribe thread = " + Thread.currentThread().getName());
            emitter.onComplete();
        }).compose(new SchedulerTransformer<>())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Logg.debug("onNext thread = " + Thread.currentThread().getName() + ", s = " + s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Observable.create((ObservableOnSubscribe<Long>) emitter -> {
            emitter.onNext(System.currentTimeMillis());
            emitter.onComplete();
        }).compose(new SchedulerTransformer<>())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        Logg.debug("onNext thread = " + Thread.currentThread().getName() + ", value = " + aLong);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        transform(Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(23);
                emitter.onComplete();
            }
        })).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Logg.debug("onNext thread = " + Thread.currentThread().getName() + ", value = " + integer);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    public <T> Observable<T> transform(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public class SchedulerTransformer<T> implements ObservableTransformer<T, T> {

        @Override
        public ObservableSource<T> apply(Observable<T> upstream) {
            return upstream.subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }
}
