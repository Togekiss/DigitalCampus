package com.example.sanfe.digitalcampus.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sanfe.digitalcampus.R;
import com.example.sanfe.digitalcampus.windows.AlertDialogWindow;
import com.example.sanfe.digitalcampus.logic.data.Subject;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {

    public String title_elimination;
    public String text_elimination;
    private Context context;
    private ArrayList<Subject> list;

    public ListViewAdapter (Context context, ArrayList<Subject> list, String title_elimination, String text_elimination) {
        this.context = context;
        this.list = list;
        this.title_elimination = title_elimination;
        this.text_elimination = text_elimination;
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
            row = inflater.inflate(R.layout.subjectlist_row, parent, false);
        }

        Subject subject = (Subject) getItem(position);
        row.setTag(subject);

        ImageView image = (ImageView) row.findViewById(R.id.subjectmanager_image);
        TextView title = (TextView) row.findViewById(R.id.subjectmanager_title);
        TextView description = (TextView) row.findViewById(R.id.subjectmanager_text);
        Button button = (Button) row.findViewById(R.id.subjectmanager_button);

        image.setImageResource(subject.getSubjectImage());
        title.setText(subject.getSubjectTitle());
        description.setText(subject.getSubjectDescription());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialogWindow.confirmationMessage(context, title_elimination, text_elimination, position);
            }
        });

        return row;
    }
}
