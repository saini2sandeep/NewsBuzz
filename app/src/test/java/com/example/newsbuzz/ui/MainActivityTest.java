package com.example.newsbuzz.ui;

import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.example.newsbuzz.R;
import com.example.newsbuzz.database.entity.NewsEntity;
import com.example.newsbuzz.ui.newsdetail.NewsDetailActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by sandeepsaini on 30,June,2019
 */

@RunWith(MockitoJUnitRunner.class)
public class MainActivityTest {


    @Mock
    MainActivity mainActivity;

    @Mock
    NewsDetailActivity newsDetailActivity;

    @Mock
    MainActivityViewModel mainActivityViewModel;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getData() {

        mainActivity.setUp();

        mainActivity.initObservers();

        mainActivity.showLoadingBar(true);

    }

    @After
    public void tearDown() throws Exception {

        mainActivity = null;
    }
}