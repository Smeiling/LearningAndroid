package com.smeiling.learning.ipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Smeiling
 * @Date: 2019-03-20 16-41
 * @Description:
 */
public class BookManagerService extends Service {

    public static final String TAG = BookManagerService.class.getSimpleName();

    private List<Book> mBooks = new ArrayList<>();

    private final IBookManager.Stub mBookManager = new IBookManager.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            if (mBooks != null) {
                return mBooks;
            } else {
                return null;
            }
        }

        @Override
        public void addBook(Book book) throws RemoteException {
            if (mBooks == null) {
                mBooks = new ArrayList<>();
            }
            mBooks.add(book);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        mBooks = new ArrayList<>();
        Book book = new Book(1, "SMLSMLSML");
        mBooks.add(book);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBookManager;
    }
}
