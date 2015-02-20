package com.prat.gregeek.activity;

import com.prat.gregeek.GreApp;
import com.prat.gregeek.data.DicData;

import android.app.Activity;
import android.os.Bundle;

import javax.inject.Inject;

/**
 * Created by pt2121 on 2/19/15.
 */
public abstract class BaseActivity extends Activity {

    @Inject
    DicData mApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        GreApp.getInstance().getGraph().inject(this);
    }

    protected DicData getApi() {
        return mApi;
    }
}
