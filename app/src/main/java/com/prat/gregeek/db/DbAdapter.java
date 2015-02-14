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

import com.prat.gregeek.model.Word;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by pt2121 on 7/16/14.
 */
public class DbAdapter {

    private final String TAG = getClass().getSimpleName();

    private Context mContext;

    private SQLiteDatabase mDb;

    //private DbHelper mHelper;
    private GreGeekDatabase mHelper;

    public DbAdapter(Context context) {
        this.mContext = context;
        //mHelper = new DbHelper(context);
        mHelper = new GreGeekDatabase(context);
    }

    public void open() throws SQLException {
        mDb = mHelper.getWritableDatabase();
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDB() {
        return mDb;
    }

    public void flushProgramTable() {
        open();
        mDb.delete(DbHelper.TABLE_NAME, null, null);
        close();
    }

    public int count() {
        Cursor mCount = null;
        if (mDb.isOpen()) {
            mCount = mDb.rawQuery("select count(*) from " + DbHelper.TABLE_NAME, null);
        }
        if (mCount != null) {
            mCount.moveToFirst();
            int count = mCount.getInt(0);
            mCount.close();
            return count;
        } else {
            return -1;
        }
    }

    public Observable<Word> getWords() {
        return Observable.create(subscriber -> {
            Cursor cursor = null;
            final String[] columns = {DbHelper.WORD_ID, DbHelper.WORD,
                    DbHelper.WORD_DEFINITION, DbHelper.WORD_EXAMPLE,
                    DbHelper.WORD_SYNONYM,};
            if (mDb.isOpen()) {
                cursor = mDb.query(DbHelper.TABLE_NAME, columns, null, null, null,
                        null, DbHelper.WORD);
            }
            if (cursor != null && cursor.getCount() > 0) {
                final int count = cursor.getCount();
                cursor.moveToFirst();
                for (int i = 0; i < count; i++) {
                    int id = cursor.getInt(cursor.getColumnIndex(DbHelper.WORD_ID));
                    final String wordStr = cursor.getString(cursor
                            .getColumnIndex(DbHelper.WORD));
                    final String def = cursor.getString(cursor
                            .getColumnIndex(DbHelper.WORD_DEFINITION));
                    final String ex = cursor.getString(cursor
                            .getColumnIndex(DbHelper.WORD_EXAMPLE));
                    final String syn = cursor.getString(cursor
                            .getColumnIndex(DbHelper.WORD_SYNONYM));
                    Word w = new Word();
                    w.setId(id);
                    w.setWord(wordStr);
                    w.setDefinition(def);
                    w.setExample(ex);
                    w.setSynonyms(syn);
                    cursor.moveToNext();
                    if (!subscriber.isUnsubscribed()) {
                        subscriber.onNext(w);
                    }
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            subscriber.onCompleted();
        });
    }

    public List<Word> getAllWords() {
        final ArrayList<Word> words = new ArrayList<>();
        Cursor cursor = null;
        // add more
        final String[] columns = {DbHelper.WORD_ID, DbHelper.WORD,
                DbHelper.WORD_DEFINITION, DbHelper.WORD_EXAMPLE,
                DbHelper.WORD_SYNONYM,};
        if (mDb.isOpen()) {
            cursor = mDb.query(DbHelper.TABLE_NAME, columns, null, null, null,
                    null, DbHelper.WORD);
        }
        if (cursor != null && cursor.getCount() > 0) {
            final int count = cursor.getCount();
            cursor.moveToFirst();
            for (int i = 0; i < count; i++) {
                int id = cursor.getInt(cursor.getColumnIndex(DbHelper.WORD_ID));
                final String wordStr = cursor.getString(cursor
                        .getColumnIndex(DbHelper.WORD));
                final String def = cursor.getString(cursor
                        .getColumnIndex(DbHelper.WORD_DEFINITION));
                final String ex = cursor.getString(cursor
                        .getColumnIndex(DbHelper.WORD_EXAMPLE));
                final String syn = cursor.getString(cursor
                        .getColumnIndex(DbHelper.WORD_SYNONYM));
                Word w = new Word();
                w.setId(id);
                w.setWord(wordStr);
                w.setDefinition(def);
                w.setExample(ex);
                w.setSynonyms(syn);
                words.add(w);
                cursor.moveToNext();
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        return words;
    }

}
