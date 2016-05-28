package com.example.sanfe.digitalcampus.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.sanfe.digitalcampus.R;
import java.util.ArrayList;

public class ThemesListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> list;

    public ThemesListAdapter (Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.themeslist_row, parent, false);
            row.setClickable(true);
        }

        String theme = (String) getItem(position);
        row.setTag(theme);

        TextView row_theme = (TextView) row.findViewById(R.id.theme);
        row_theme.setText((position+1) + ". " + theme);

        ImageView up = (ImageView) row.findViewById(R.id.up);
        ImageView down = (ImageView) row.findViewById(R.id.down);
        ImageView delete = (ImageView) row.findViewById(R.id.delete);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position != 0) {
                    String aux = list.get(position);
                    list.set(position, list.get(position-1));
                    list.set(position-1, aux);
                }
                notifyDataSetChanged();
            }
        });

        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < list.size()-1) {
                    String aux = list.get(position);
                    list.set(position, list.get(position+1));
                    list.set(position+1, aux);
                }
                notifyDataSetChanged();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                notifyDataSetChanged();
            }
        });
        return row;
    }
}
