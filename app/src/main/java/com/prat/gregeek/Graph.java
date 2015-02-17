package com.prat.gregeek;

import com.prat.gregeek.data.DicDataModule;
import com.prat.gregeek.fragment.DailyWordFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by pt2121 on 2/16/15.
 */
@Singleton
@Component(modules = {DicDataModule.class})
public interface Graph {

    void inject(DailyWordFragment fragment);

    public final static class Initializer {
        public static Graph init(boolean mockMode) {
            return Dagger_Graph.builder()
                    .build();
        }
    }

}
