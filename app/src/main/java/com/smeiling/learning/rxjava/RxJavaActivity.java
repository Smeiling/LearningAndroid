package com.smeiling.learning.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.Logg;
import com.smeiling.learning.R;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/app/rxjava")
public class RxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);

        funcAssemblyHook();
        funcCreate();
//        funcGroupJoin();

    }

    private void funcCreate() {
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                emitter.onNext("Observable Create");
            }
        }).subscribe(new Observer<Object>() {
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

    private void funcAssemblyHook() {
        RxJavaPlugins.setOnObservableAssembly(new Function<Observable, Observable>() {
            @Override
            public Observable apply(Observable observable) throws Exception {
                Logg.error("Assembly hook test");
                return observable;
            }
        });
    }

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
}
