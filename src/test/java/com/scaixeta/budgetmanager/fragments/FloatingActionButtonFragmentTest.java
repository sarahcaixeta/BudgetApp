package com.scaixeta.budgetmanager.fragments;

import android.app.Dialog;
import android.widget.FrameLayout;

import com.scaixeta.budgetmanager.MainActivity;
import com.scaixeta.budgetmanager.R;
import com.scaixeta.budgetmanager.testrunner.CustomRobolectricTestRunner;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowDialog;
import org.robolectric.util.FragmentTestUtil;

import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@Config(emulateSdk = 18)
@RunWith(CustomRobolectricTestRunner.class)
public class FloatingActionButtonFragmentTest {

    private FloatingActionButtonBasicFragment fragment;

    @Before
    public void setUp() {
        fragment = new FloatingActionButtonBasicFragment();
        FragmentTestUtil.startFragment(fragment, MainActivity.class);
    }

    @Test
    public void shouldShowAFloatingButton() {
        FrameLayout fab = (FrameLayout) fragment.getView().findViewById(R.id.fab);
        assertThat(fab, notNullValue());
    }

    @Ignore //FIXME when I mock the database
    @Test
    public void shouldOpenADialogWhenTheButtonIsClicked() {
        fragment.getView().findViewById(R.id.fab).callOnClick();

        Dialog dialog = ShadowDialog.getLatestDialog();
        assertThat(dialog, notNullValue());
    }
}
