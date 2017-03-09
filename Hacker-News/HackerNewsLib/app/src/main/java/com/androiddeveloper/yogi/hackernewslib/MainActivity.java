package com.androiddeveloper.yogi.hackernewslib;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.androiddeveloper.yogi.hackernewskiiplib.TopHackerNews;

public class MainActivity extends AppCompatActivity {

    ListView myListView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TopHackerNews ob = new TopHackerNews(this);
        ob.initSDK();
        ob.showHackerNews();
    }
}
