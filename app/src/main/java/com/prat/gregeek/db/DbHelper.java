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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pt2121 on 7/16/14.
 */
public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";

    public static final String DB_NAME = "gre_geek.db";

    // word table
    public static final String TABLE_NAME = "word";

    public static final String WORD_ID = "_id";

    public static final String WORD = "word";

    public static final String WORD_DEFINITION = "definition";

    public static final String WORD_EXAMPLE = "example";

    public static final String WORD_SYNONYM = "synonym";

    public static final String WORD_STARRED = "starred";

    public static final String NOTE_ID = "note_id";

    private static final String CREATE_TABLE_SQL = "Create table " + TABLE_NAME
            + " (" + WORD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + WORD
            + " TEXT, " + WORD_DEFINITION + " TEXT, " + WORD_EXAMPLE
            + " TEXT, " + WORD_SYNONYM + " TEXT, " + WORD_STARRED
            + " INTEGER DEFAULT 0, " + NOTE_ID + " INTEGER DEFAULT -1);";

    // note table
    public static final String NOTE_TABLE = "note";

    public static final String NOTE_KEY_ID = "_id";

    public static final String TEXT = "text";

    public static final String NOTE_DATE = "date";

    public static final String NOTE_WORD_ID = "word_id";

    public static final String NOTE_WORD = "word";

    private static final String CREATE_NOTE_TABLE = "Create table " + NOTE_TABLE
            + " (" + NOTE_KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TEXT + " TEXT, " + NOTE_DATE + " TEXT, " + NOTE_WORD + " TEXT, "
            + NOTE_WORD_ID + " INTEGER);";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub
    }
}
