package com.prat.gregeek.data;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by pt2121 on 2/16/15.
 */
@Module
public class DebugDicDataModule {
    private final boolean mockMode;

    public DebugDicDataModule(boolean provideMocks) {
        mockMode = provideMocks;
    }

    @Provides
    @Singleton
    DicData provideDicDataModule() {
        if (mockMode) {
            return Mockito.mock(DicData.class);
        } else {
            return new DicData();
        }
    }
}
