package com.prat.gregeek.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pt2121 on 2/16/15.
 */
@Module
public final class DicDataModule {

    @Provides
    @Singleton
    public DicData provideDicDataModule() {
        return new DicData();
    }

}
