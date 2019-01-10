package com.example.studentlistv2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentlistv2.R;
import com.example.studentlistv2.model.Student;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private ArrayList<Student> list;
    Context context;

    ItemClicked activity;

    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public StudentAdapter(Context context, ArrayList<Student> list) {
        this.list = list;
        activity = (ItemClicked) context;
//        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_view_student, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tvName.setText(list.get(i).getName());
        viewHolder.tvSurname.setText(list.get(i).getSurname());
        viewHolder.tvGroup.setText(list.get(i).getGroup());
        viewHolder.tvUniversity.setText(list.get(i).getUniversity());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvSurname, tvGroup, tvUniversity;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvSurname = itemView.findViewById(R.id.tvSurname);
            tvGroup = itemView.findViewById(R.id.tvGroup);
            tvUniversity = itemView.findViewById(R.id.tvUniversity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(list.indexOf(view.getTag()));
                }
            });
        }
    }
}
