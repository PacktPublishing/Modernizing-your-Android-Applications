package com.packt.coolnewandroid;

import android.content.Context;
import android.util.Log;

import com.packt.coolnewandroid.room.CoolNewDatabase;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

public class CoolDataSource extends PageKeyedDataSource<Integer, Automobile> {

    public static final int PAGE_SIZE = 10;
    public static final int FIRST_PAGE = 1;

    private Context context;

    public CoolDataSource(Context context) {
        this.context = context;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Automobile> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Automobile> firstAutos = Objects.requireNonNull(CoolNewDatabase.Companion.getAppDataBase(context.getApplicationContext())).dao().getFirstTenAutos();
                callback.onResult(firstAutos, null, FIRST_PAGE + 1);
            }
        }).start();
    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Automobile> callback) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<Automobile> firstAutos = Objects.requireNonNull(CoolNewDatabase.Companion.getAppDataBase(context.getApplicationContext())).dao().getTenAutos(params.key - PAGE_SIZE, params.key);
//                Log.d("TEST", "Loaded item set " + params.key);
//                callback.onResult(firstAutos, params.key + 1);
//            }
//        }).start();
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Automobile> callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Automobile> firstAutos = Objects.requireNonNull(CoolNewDatabase.Companion.getAppDataBase(context.getApplicationContext())).dao().getTenAutos(params.key * PAGE_SIZE, PAGE_SIZE);
                Log.d("TEST", "Loaded item set " + params.key);
                for (Automobile firstAuto : firstAutos) {
                    Log.d("TEST", "Loaded item: " + firstAuto.getId() + ") " + firstAuto.getName());
                }
                callback.onResult(firstAutos, (params.key == 3 ? null : params.key + 1));
            }
        }).start();
    }
}
