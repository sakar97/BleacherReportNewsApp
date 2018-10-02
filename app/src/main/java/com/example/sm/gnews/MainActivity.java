package com.example.sm.gnews;

import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.sm.gnews.Helper.TouchListener;
import com.example.sm.gnews.Helper.TouchListenerListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TouchListenerListener {
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    Toolbar toolbar;
    private List<Items> mitems;
    CoordinatorLayout coordinatorLayout;
    backgroundtask background = new backgroundtask();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mitems = new ArrayList<>();
        coordinatorLayout=findViewById(R.id.coordinator);
        recyclerView = findViewById(R.id.listsource);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, mitems);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setAdapter(recyclerViewAdapter);
        ItemTouchHelper.SimpleCallback item = new TouchListener(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(item).attachToRecyclerView(recyclerView);
        background.execute();
    }

    public class backgroundtask extends AsyncTask<Void, Void, String> {
        private String json_url;
        private String JSON_STRING;
        private String json_result;

        @Override
        protected void onPreExecute() {
            json_url = "https://newsapi.org/v2/everything?sources=bleacher-report&apiKey=03a84ce64ef242418d262c11a57248de";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append(JSON_STRING + "/n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            json_result = result;
            try {
                JSONObject jsonObject = new JSONObject(json_result);
                JSONArray jsonArray = jsonObject.getJSONArray("articles");
                int count = 0;
                String author, title, description, urltoImage, PublishAt, content, url;
                while (count < jsonArray.length()) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(count);
                    author = jsonObject1.getString("author");
                    title = jsonObject1.getString("title");
                    description = jsonObject1.getString("description");
                    urltoImage = jsonObject1.getString("urlToImage");
                    PublishAt = jsonObject1.getString("publishedAt");
                    content = jsonObject1.getString("content");
                    url = jsonObject1.getString("url");
                    Items items = new Items(author, title, description, urltoImage, PublishAt, content, url);
                    count++;
                    mitems.add(items);
                }
                recyclerViewAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();
        if (res_id == R.id.share) {
            Intent a = new Intent(Intent.ACTION_SEND);
            final String appPackageName = getApplicationContext().getPackageName();
            String strAppLink = "";
            try {
                strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
            } catch (android.content.ActivityNotFoundException anfe) {
                strAppLink = "https://play.google.com/store/apps/details?id=" + appPackageName;
            }
            // this is the sharing part
            a.setType("text/link");
            String shareBody = "Hey! Download my app for free and win amazing watch awesome news everyday." +
                    "\n" + "" + strAppLink;
            String shareSub = "APP NAME/TITLE";
            a.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            a.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(a, "Share Using"));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof RecyclerViewAdapter.MyViewHolder) {
            final Items Deletednews=mitems.get(viewHolder.getAdapterPosition());
            final int deletedIndex=viewHolder.getAdapterPosition();
            recyclerViewAdapter.removeItem(deletedIndex);
            Snackbar snackbar=Snackbar.make(coordinatorLayout,"Removed from List",Snackbar.LENGTH_SHORT);
            snackbar.setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerViewAdapter.addItem(Deletednews,deletedIndex);
                }
            });
            snackbar.show();
        }
    }
}

