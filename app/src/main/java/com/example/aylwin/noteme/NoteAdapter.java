package com.example.aylwin.noteme;

import android.content.Context;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aylwin.noteme.Model.Catatan;

import java.util.ArrayList;

/**
 * Created by Aylwin on 8/28/2017.
 */

public class NoteAdapter extends ArrayAdapter<Catatan> {

    private static class ViewHolder {
        //ImageView imgIcon;
        TextView description;
    }

    public NoteAdapter(@NonNull Context context,ArrayList Cat) {
        super(context,0, Cat);
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder;
//        if (convertView == null) {
//            viewHolder = new ViewHolder();
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
//            viewHolder.description = (TextView) convertView.findViewById(R.id.item_img_infor);
//            //viewHolder.imgIcon = (ImageView) convertView.findViewById(R.id.item_img_icon);
//            convertView.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        Catatan cat = getItem(position);
//        viewHolder.description.setText(cat.toString());
//
//        //viewHolder.imgIcon.setImageBitmap(ThumbnailUtils.createVideoThumbnail(video.getPath(), MediaStore.Images.Thumbnails.MINI_KIND));
//
//        return convertView;
//    }



}
