package com.example.sanfe.digitalcampus.logic;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sanfe.digitalcampus.R;

public class GridViewImageAdapter extends BaseAdapter{

    private Context context;
    private Integer[] images;
    private String[] descriptions;

    public GridViewImageAdapter(Context context, Integer [] images, String[] descriptions){
        this.context = context;
        this.images = images;
        this.descriptions = descriptions;
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        public ImageView imageView;
        public TextView textView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = new ViewHolder ();
            convertView = inflater.inflate(R.layout.gridview_row, null);

            view.imageView = (ImageView) convertView.findViewById(R.id.gridview_image);
            view.textView = (TextView) convertView.findViewById(R.id.gridview_text);

            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        view.textView.setText(descriptions[position]);
        view.imageView.setImageResource(images[position]);

        return convertView;
    }


}
