package com.smeiling.learning.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.smeiling.learning.Logg;
import com.smeiling.learning.R;

@Route(path = "/app/thread")
public class ThreadDemoActivity extends AppCompatActivity {


    private HandlerThread handlerThread;
    private Handler mOtherHandler;
    private SmlHandler smlHandler;
    private SmlAsyncTask asyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_demo);

        funcHandlerThread();

        findViewById(R.id.btn_send_message).setOnClickListener(v -> {
            mOtherHandler.sendEmptyMessage(0x23);
            funcAsyncTask();
        });

    }

    /**
     * HandlerThread
     */
    private void funcHandlerThread() {
        handlerThread = new HandlerThread("SmlHandlerThread");
        handlerThread.start();

        mOtherHandler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x23) {
                    Logg.debug("other handler current thread = " + Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    smlHandler.sendEmptyMessage(msg.what);
                }
            }
        };

        smlHandler = new SmlHandler();
    }


    /**
     * AsyncTask
     */
    private void funcAsyncTask() {
        asyncTask = new SmlAsyncTask();
//        asyncTask.execute("sml");
        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "sml");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        asyncTask.cancel(true);
    }

    /**
     * Static AsyncTask
     */
    private static class SmlAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... objects) {
            int count = 10;
            while (count >= 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                onProgressUpdate(count);
                count--;
            }
            return "doInBackground " + Thread.currentThread().getName() + " " + System.currentTimeMillis() + " " + objects[0];
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Logg.debug("onProgressUpdate " + Thread.currentThread().getName() + " " + values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Logg.debug("onPostExecute " + Thread.currentThread().getName() + " result = " + s);
        }

    }

    /**
     * Static handler
     */
    private static class SmlHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Logg.debug("other handler current thread = " + Thread.currentThread().getName());
        }
    }
}
