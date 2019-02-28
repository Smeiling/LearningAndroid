package com.smeiling.learning.proxy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.Logg;
import com.smeiling.learning.R;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = "/app/dynamic_proxy")
public class DynamicProxyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_proxy);
        Observable.create(new ObservableOnSubscribe<Object>() {
            @Override
            public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
//                implDynamicProxy();
                simplyImpl();
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void implDynamicProxy() {
        try {
            Student stu = new Student("SML");
            // 创建一个InvocationHandler对象
            InvocationHandler stuHandler = new StuInvocationHandler<>(stu);

            // 使用Proxy类的getProxyClass静态方法生成一个动态代理类 stuProxyClass
            Class<?> stuProxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class<?>[]{Person.class});

            // 获得stuProxyClass中一个带InvocationHandler参数的构造器constructor
            Constructor<?> constructor = null;

            constructor = stuProxyClass.getConstructor(InvocationHandler.class);

            // 通过构造器constructor来创建一个动态实例stuProxy
            Person stuProxy = (Person) constructor.newInstance(stuHandler);

            stuProxy.giveMoney();

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    private void simplyImpl() {
        Student stu = new Student("SML");
//        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Proxy.class},
//                new StuInvocationHandler<>(stu));
        Person stuProxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class<?>[]{Person.class},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Logg.debug("代理执行" + method.getName() + "方法");
                        // 代理过程中插入监测方法，计算该方法耗时
                        MonitorUtil.start();
                        Object result = method.invoke(stu, args);
                        MonitorUtil.finish(method.getName());
                        return result;
                    }
                });
        stuProxy.giveMoney();
    }
}
