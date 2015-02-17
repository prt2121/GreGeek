package com.prat.gregeek;

import android.app.Application;

/**
 * Created by pt2121 on 2/16/15.
 */
public class GreApp extends Application {

    private static GreApp sInstance;
    private Graph mGraph;

    @Override public void onCreate() {
        super.onCreate();
        sInstance = this;
        mGraph = Graph.Initializer.init(false);
    }

    public static GreApp getInstance() {
        return sInstance;
    }

    public Graph getGraph() {
        return mGraph;
    }
}
