/*
 * Copyright (c) 2015 Prat Tanapaisankit
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
