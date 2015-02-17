package com.prat.gregeek.data;

import com.prat.gregeek.db.DbAdapter;
import com.prat.gregeek.model.Word;

import java.util.Random;

import rx.Observable;

/**
 * Created by pt2121 on 2/16/15.
 */
public class DicData {

    public Observable<Word> getRandomWord(DbAdapter dbAdapter) {
        Random random = new Random();
        int i = random.nextInt(dbAdapter.count()) + 1;
        return dbAdapter.getWords()
                .take(i)
                .last();
    }
}
