package com.example.newsreader;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Ralph_Chao on 2016/12/15.
 */

public class NewsDB extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NewsDB.db";
    private String newsTable = "newsTable";
    ArrayList<NewsData> newsData = new ArrayList<>();

    public NewsDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        Log.d("DataBase", "Create");
        String SQL = "CREATE TABLE IF NOT EXISTS "+ newsTable +" (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "title VARCHAR(50), " +
                "content TEXT, " +
                "url VARCHAR(60), " +
                "stars float, " +
                "commentNum int)";
        db.execSQL(SQL);
        initialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String SQL = "DROP TABLE IF EXISTS " + newsTable;
        db.execSQL(SQL);
    }

    public void initialData(SQLiteDatabase db) {
        insertNewsData();
        ContentValues values = new ContentValues();
        for(int i = 0; i < newsData.size(); i++) {
            values.put("title", newsData.get(i).getTitle());
            values.put("content", newsData.get(i).getContent());
            values.put("url", newsData.get(i).getUrl());
            values.put("stars", newsData.get(i).getStars());
            values.put("commentNum", newsData.get(i).getCommentNum());
            db.insert(newsTable, null, values);
        }
    }

    public void insertNewsData() {
        newsData.add(new NewsData("明年七天假 代工雙雄不同調",
                "工商時報【鄭淑芳、吳筱雯╱台北報導】\n" +
                "7天假放不放？在網路PTT上引起熱議，市場看法兩極，就連組裝代工雙雄也不同調。鴻海昨（14）日一早遭員工在PTT上爆料，確定7天假沒了；倒是宣稱從不看公司行事曆的和碩董事長童子賢，昨日大方認了7天假，還說「就當作是明年送給員工一份大禮」...",
                "https://tw.news.yahoo.com/%E6%98%8E%E5%B9%B4%E4%B8%83%E5%A4%A9%E5%81%87-%E4%BB%A3%E5%B7%A5%E9%9B%99%E9%9B%84%E4%B8%8D%E5%90%8C%E8%AA%BF-215007524--finance.html",
                0,
                0));
        newsData.add(new NewsData("日圓貶至近1年低點 0.26字頭非夢事",
                "美國確定升息，日圓兌美元貶破117元，再創逾10個月新低，而根據台銀牌告價格，日圓現金賣出價來到0.2739元，續創今年開紅盤以來近一年低點，「0.26」字頭或許非夢事...",
                "https://tw.news.yahoo.com/%E6%97%A5%E5%9C%93%E8%B2%B6%E8%87%B3%E8%BF%911%E5%B9%B4%E4%BD%8E%E9%BB%9E-0-26%E5%AD%97%E9%A0%AD%E9%9D%9E%E5%A4%A2%E4%BA%8B-011441679.html",
                0,
                0));
        newsData.add(new NewsData("高薪搶岱桑 助吸台灣財",
                "日職讀賣巨人隊聘用來自台灣或擁有中華民國護照的球員不多，真正能在巨人隊揚名立萬都是野手，從吳征昌、王貞治、呂明賜、再到陽岱鋼，不同世代都能在巨人隊掀起驚天波濤，陽岱鋼深受巨人軍與球迷的期待...",
                "https://tw.news.yahoo.com/%E9%AB%98%E8%96%AA%E6%90%B6%E5%B2%B1%E6%A1%91-%E5%8A%A9%E5%90%B8%E5%8F%B0%E7%81%A3%E8%B2%A1-215007626--mlb.html",
                0,
                0));
        newsData.add(new NewsData("傅達仁為安樂死請命 府函請政院處理",
                "資深媒體人傅達仁日前上書總統蔡英文請命，建議訂立久病或高齡得不治之症者安樂死法案，也願當台灣「安樂死」合法首例，引發社會討論。總統府昨回函給傅達仁，信中強調：「總統非常重視，已函請行政院處理。」...",
                "https://tw.news.yahoo.com/%E5%82%85%E9%81%94%E4%BB%81%E7%82%BA%E5%AE%89%E6%A8%82%E6%AD%BB%E8%AB%8B%E5%91%BD-%E5%BA%9C%E5%87%BD%E8%AB%8B%E6%94%BF%E9%99%A2%E8%99%95%E7%90%86-215007004.html",
                0,
                0));
        newsData.add(new NewsData("移民路不好聽 居民要求改「古亭路」",
                "桃園大園區的古亭，市政府進行道路整合，為了紀念當時興建石門水庫，遷徙的「移民新村」村民，所以把沒有路名的道路取名為移民路，沒想到這一改，讓居民覺的怪怪的，好像住在這裡的人都是移民來的，多數居民反應後，市政府也把移民路改成了古亭路...",
                "https://tw.news.yahoo.com/%E7%A7%BB%E6%B0%91%E8%B7%AF%E4%B8%8D%E5%A5%BD%E8%81%BD-%E5%B1%85%E6%B0%91%E8%A6%81%E6%B1%82%E6%94%B9-%E5%8F%A4%E4%BA%AD%E8%B7%AF-035524918.html",
                0,
                0));
    }

}
