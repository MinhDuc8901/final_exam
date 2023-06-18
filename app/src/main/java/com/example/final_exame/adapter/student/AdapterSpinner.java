package com.example.final_exame.adapter.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.final_exame.R;
import com.example.final_exame.model.Student;

import java.util.List;

public class AdapterSpinner extends BaseAdapter {
    private List<String> list;
    private Context context;

    public AdapterSpinner(List<String> list,Context context){
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
        view = LayoutInflater.from(context).inflate(R.layout.item_spiner_stu,null);
        TextView tv = view.findViewById(R.id.sptvstu);
        tv.setText(list.get(i)+"");
        return view;
    }
}
