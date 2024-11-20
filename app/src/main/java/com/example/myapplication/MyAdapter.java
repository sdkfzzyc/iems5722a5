package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

public class MyAdapter extends BaseAdapter {

    private Context mContext;
    private LinkedList<chatdata> myData;

    public MyAdapter(LinkedList<chatdata> myData, chatactivity mContext) {
        this.myData = myData;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return myData.size();
    }

    @Override
    public Object getItem(int position) {
        return myData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        if(myData.get(position).getType() == 0){
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.chatleft,parent,false);
            textView = convertView.findViewById(R.id.txt_left);
        }
        else {
            convertView = LayoutInflater.from(mContext).
                    inflate(R.layout.chatright,parent,false);
            textView = convertView.findViewById(R.id.txt_right);
        }
        textView.setText(myData.get(position).getText());

        return convertView;
    }

}
