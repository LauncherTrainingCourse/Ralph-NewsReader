package com.example.newsreader;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<NewsData> newsData = new ArrayList<>();
    public final static String EXTRA_MESSAGE = "com.example.newsreader.MESSAGE";
    public final static String ACTIVITY_NAME = "activity_name";
    private ListView listView;
    private NewsListAdapter listAdapter;
    static final int PICK_CONTACT_REQUEST = 1;
    private float newsRating;
    private int updatedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getData();
        //this.deleteDatabase("NewsDB1.db");

        Log.d("Create Activity", "onCreate");

        listView = (ListView)findViewById(R.id.news_list);
        listAdapter = new NewsListAdapter(this, newsData);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int message = newsData.get(position).getID();
                Intent intent = new Intent(view.getContext(), NewsContentActivity.class);
                intent.putExtra(EXTRA_MESSAGE, message);
                intent.putExtra(ACTIVITY_NAME, "NewsReader");
                startActivityForResult(intent, PICK_CONTACT_REQUEST);   //Call the NewsContentActivity to show content of news
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent Data) { //To handle the data which get from NewsContentActivity
        Log.d("Get Result", "Get news Rating");
        if(requestCode == PICK_CONTACT_REQUEST) {
            if(resultCode == RESULT_OK) {
                newsRating = Data.getExtras().getFloat("Rating");
                updatedItem = Data.getExtras().getInt("Target");
            }
        }
        rateUpdate(updatedItem, newsRating);
        newsData.clear();
        getData();
        listAdapter.notifyDataSetChanged();     //Update ListView
    }

    public void getData() {
        NewsDB dbHelper = new NewsDB(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String SQL = "SELECT * FROM newsTable ORDER BY stars DESC";
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            newsData.add(new NewsData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5)));
            cursor.moveToNext();
        }
        db.close();
    }

    public void rateUpdate(int id, float rate) {
        NewsDB dbHelper = new NewsDB(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("stars", rate);
        db.update("newsTable", values, "_id=" + Integer.toString(id), null);
        db.close();
    }

}
