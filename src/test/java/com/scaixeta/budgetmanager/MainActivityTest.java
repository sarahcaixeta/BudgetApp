package com.scaixeta.budgetmanager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void shouldAssertSarahIsLame(){
        Robolectric.buildActivity(MainActivity.class).get().getResources();
        assertFalse(false);
    }


}