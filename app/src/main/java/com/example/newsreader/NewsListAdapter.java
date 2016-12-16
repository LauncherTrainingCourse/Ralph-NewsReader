package com.example.newsreader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ralph_Chao on 2016/12/15.
 */

public class NewsListAdapter extends BaseAdapter {

    private ArrayList<NewsData> newsData;
    private LayoutInflater inflater;

    public NewsListAdapter(Context context, ArrayList<NewsData> input) {
        this.inflater = LayoutInflater.from(context);
        this.newsData = input;
    }

    private class ViewHolder {
        TextView textTitle;
        TextView textStars;
        ImageView starView;
        public ViewHolder(TextView title, TextView stars, ImageView starImage) {
            this.textTitle = title;
            this.textStars = stars;
            this.starView = starImage;
        }
    }

    @Override
    public int getCount() {
        return this.newsData.size();
    }

    @Override
    public NewsData getItem(int arg) {
        return this.newsData.get(arg);
    }

    @Override
    public long getItemId(int position) {
        return this.newsData.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = inflater.inflate(R.layout.news_list_item, null);
            holder = new ViewHolder ((TextView)convertView.findViewById(R.id.news_title), (TextView)convertView.findViewById(R.id.news_stars), (ImageView)convertView.findViewById(R.id.star_image));
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }
        NewsData news = getItem(position);
        holder.textTitle.setText(news.getTitle());
        //holder.textStars.setText(Float.toString(news.getStars()));
        switch((int)news.getStars()) {
            case 1:
                holder.starView.setImageResource(R.drawable.star_1_1);
                break;
            case 2:
                holder.starView.setImageResource(R.drawable.star_2);
                break;
            case 3:
                holder.starView.setImageResource(R.drawable.star_3);
                break;
            case 4:
                holder.starView.setImageResource(R.drawable.star_4);
                break;
            case 5:
                holder.starView.setImageResource(R.drawable.star_5);
                break;
            default:
                holder.starView.setImageResource(R.drawable.star_0);
        }
        return convertView;
    }

}
