package com.example.newsreader;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Ralph_Chao on 2016/12/15.
 */

public class NewsContentActivity extends AppCompatActivity {

    public NewsData newsData;
    private Intent backIntent;
    private Bundle backData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content_activity);
        final Intent intent = getIntent();
        int newsID ;

        if(intent.getStringExtra("activity_name").equals("NewsReader"))
            newsID = intent.getIntExtra(MainActivity.EXTRA_MESSAGE, 0);
        else
            newsID = intent.getExtras().getInt("newsNo");

        newsData = getData(newsID);

        TextView titleView = (TextView)findViewById(R.id.content_title);
        TextView contentView = (TextView)findViewById(R.id.news_content);
        titleView.setText(newsData.getTitle());
        contentView.setText((newsData.getContent()));

        Button webButton = (Button)findViewById(R.id.web_link);
        webButton.setText("查看詳情");
        webButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri location = Uri.parse(newsData.getUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, location);

                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(webIntent, 0);
                boolean isIntentSave = activities.size() > 0;
                Toast.makeText(NewsContentActivity.this, "There are "+activities.size()+" apps respond to intent", Toast.LENGTH_SHORT).show();
                if(isIntentSave)
                    startActivity(webIntent);
            }
        });

        Button shareButton = (Button)findViewById(R.id.share_button);
        shareButton.setText("認同請分享");
        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                Intent chooser = Intent.createChooser(shareIntent, "請選擇分享的平台");
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, newsData.getUrl());
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(shareIntent, 0);
                boolean isIntentSave = activities.size() > 0;
                Toast.makeText(NewsContentActivity.this, "There are "+activities.size()+" apps respond to intent", Toast.LENGTH_SHORT).show();
                if(isIntentSave)
                    startActivity(chooser);
            }
        });

        RatingBar ratingBar = (RatingBar)findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                System.out.println(rating);
                backIntent = new Intent();
                backData = new Bundle();
                backData.putInt("Target",newsData.getID());
                backData.putFloat("Rating", rating);
                backIntent.putExtras(backData);
                NewsContentActivity.this.setResult(RESULT_OK, backIntent);
            }
        });
        ratingBar.setRating(newsData.getStars());
    }

    public NewsData getData(int newsID) {
        NewsDB dbHelper = new NewsDB(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String SQL = "SELECT * FROM newsTable WHERE _id=" + newsID;
        Cursor cursor = db.rawQuery(SQL, null);
        cursor.moveToFirst();
        db.close();
        return new NewsData(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5));
    }
}
