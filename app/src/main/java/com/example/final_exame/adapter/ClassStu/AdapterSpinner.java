package com.example.final_exame.adapter.ClassStu;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.final_exame.R;
import com.example.final_exame.database.DatabaseClassroom;
import com.example.final_exame.database.DatabaseStudent;
import com.example.final_exame.model.Classroom;
import com.example.final_exame.model.Student;
import com.example.final_exame.model.StudentClass;

import java.util.List;

public class AdapterSpinner <T> extends BaseAdapter {
    private List<T> list;
    private Context context;
    private T data;

    public AdapterSpinner(List<T>list,Context context){
        this.list = list;
        this.context = context;

    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_spinner,null);
        TextView tvId,tvName;
        tvId = view.findViewById(R.id.tvID);
        tvName = view.findViewById(R.id.tvName);
        T data = list.get(i);
        tvId.setText(data.toString());
        return view;
    }
}
