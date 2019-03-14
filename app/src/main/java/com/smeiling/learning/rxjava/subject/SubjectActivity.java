package com.smeiling.learning.rxjava.subject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.Logg;
import com.smeiling.learning.R;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

@Route(path = "/app/subject")
public class SubjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);


        PublishSubject<Integer> subject = PublishSubject.create();
        subject.subscribe(integer -> Logg.debug("Thread = " + Thread.currentThread() + " value = " + integer));

//        Disposable disposable = Observable.range(1, 5)
//                .subscribeOn(Schedulers.single())
//                .subscribe(new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Thread.sleep(1000);
//                        subject.onNext(integer);
//                    }
//                });

        subject.onNext(2333);

    }


}
