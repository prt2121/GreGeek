package com.prat.gregeek.db;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.Context;

/**
 * Created by prt2121 on 1/3/15.
 */
public class GreGeekDatabase extends SQLiteAssetHelper {

    public static final String DATABASE_NAME = "gre_geek.db";

    public static final int DATABASE_VERSION = 1;

    public GreGeekDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
