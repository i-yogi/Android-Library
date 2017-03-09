package com.androiddeveloper.yogi.hackernewskiiplib;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by yogi on 3/8/17.
 */

public class TopHackerNews extends RelativeLayout{

    private Context context;

    private final String TOP_HK_NEWS = "https://hacker-news.firebaseio.com/v0/topstories.json";
    private final String STORY_URL = "https://hacker-news.firebaseio.com/v0/item/";

    private ArrayList<MyJsonObj> finalFeed = null;
    private ArrayList<String> testFeed = null;
    private ArrayAdapter<String> adapter;

    private ListView listView = null;
//    private WebView webView = null;

    public TopHackerNews(Context context) {
        super(context);
        this.context = context;
    }

    public TopHackerNews(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public TopHackerNews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void initSDK(){

        Activity a = (Activity) context;
        View v = inflate(context, R.layout.list_view, this);
        a.setContentView(v);

        listView = (ListView) findViewById(R.id.listView);
//        webView = (WebView) findViewById(R.id.webView);

        finalFeed = new ArrayList<>();
        testFeed = new ArrayList<>();
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, testFeed);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                listView.setVisibility(View.GONE);
//                webView.setVisibility(View.VISIBLE);

                MyJsonObj ob = finalFeed.get(position);
                String searchUrl = ob.getOrgUrl();
//                webView.loadUrl(searchUrl);

                Intent browseUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(searchUrl));
                context.startActivity(browseUrl);
            }
        });


    }

    public void showHackerNews(){

        new DownloadHackerNews().execute();
    }



    protected class DownloadHackerNews extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader breader = null;
            InputStream inputStream = null;

            if (params == null)
                return null;

            try {

                URL url = new URL(TOP_HK_NEWS);
                connection = (HttpURLConnection) url.openConnection();
                connection.getResponseCode();

                inputStream = connection.getInputStream();
                if (inputStream == null)
                    return null;

                breader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuilder result = new StringBuilder();
                String line;

                while ((line = breader.readLine()) != null)
                    result.append(line);

//               return result.toString();

                //Downloading stories
                JSONArray top20Ids = new JSONArray(result.toString());

                ArrayList<String> topStories = new ArrayList<>();
                for (int i = 0; i < 20; i++) {

                    String storyId = top20Ids.getString(i);

                    URL urlS = new URL(STORY_URL + storyId + ".json");
                    connection = (HttpURLConnection) urlS.openConnection();
                    connection.getResponseCode();

                    inputStream = connection.getInputStream();
                    if (inputStream == null)
                        return null;

                    breader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder story = new StringBuilder();

                    while ((line = breader.readLine()) != null)
                        story.append(line);

//                    Log.i("Story "+storyId, story.toString());

                    topStories.add(story.toString());
                }

                return topStories;

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(ArrayList<String> strings) {
            super.onPostExecute(strings);

            for(String story : strings ){

                try {

                    JSONObject jobj  = new JSONObject(story);

                    int id = 0;
                    String title = null;
                    String author = null;
                    int score = 0;
                    Date time = null;
                    int noComments = 0;
                    String orgUrl = null;

                    if( jobj.has("id"))
                        id = jobj.getInt("id");
                    if( jobj.has("title"))
                        title = jobj.getString("title");
                    if( jobj.has("by"))
                        author = jobj.getString("by");
                    if( jobj.has("score"))
                        score= jobj.getInt("score");
                    if( jobj.has("time"))
                        time = new Date(jobj.getLong("time"));
                    if( jobj.has("descendants"))
                        noComments = jobj.getInt("descendants");
                    if( jobj.has("url"))
                        orgUrl = jobj.getString("url");

                    MyJsonObj storyItem = new MyJsonObj(id, title, author, score, time, noComments, orgUrl);
                    finalFeed.add(storyItem);

                    String stringEntry = "Title: " + title + "\n" + "Author: " + author + " Score: " + score + "\n" + "Time: " + time + " #Comments: " + noComments;
                    testFeed.add(stringEntry);

                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
