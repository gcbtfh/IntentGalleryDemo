package com.example.tonghung.intentgallerydemo.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tonghung.intentgallerydemo.R;
import com.example.tonghung.intentgallerydemo.object.Picture;

import java.util.List;

/**
 * Created by tonghung on 21/12/2016.
 */

public class PictureAdapter extends ArrayAdapter {
    Activity context;
    List<Picture> listPic;


    public PictureAdapter(Activity context, List<Picture> listPic) {
        super(context, R.layout.layout_lv_picture, listPic);
        this.context = context;
        this.listPic = listPic;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = context.getLayoutInflater().inflate(R.layout.layout_lv_picture, parent,
                false);
        TextView name = (TextView) convertView.findViewById(R.id.textView_name_custom);
        ImageView iv = (ImageView) convertView.findViewById(R.id.imageView_pic_custom);

        name.setText(listPic.get(position).getName());
        iv.setImageBitmap(listPic.get(position).getBitmap());

        return convertView;
    }
}
