package com.packt.coolnewandroid;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

@SuppressWarnings("unchecked")
public class CoolDataSourceFactory extends DataSource.Factory<Integer, Automobile> {

    private MutableLiveData<PageKeyedDataSource<Integer, Automobile>> autosLiveDataSource
            = new MutableLiveData<>();

    private Context context;

    public CoolDataSourceFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public DataSource create() {
        CoolDataSource coolDataSource = new CoolDataSource(context);
        autosLiveDataSource.postValue(coolDataSource);
        return coolDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Automobile>> getAutosLiveDataSource() {
        return autosLiveDataSource;
    }
}
