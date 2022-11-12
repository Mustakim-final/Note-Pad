package com.example.note;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyHolder>{

    Context context;

    List<Note> notes;

    public Adapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.simple_layout,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String title=notes.get(position).getTitle();
        String date=notes.get(position).getDate();
        String time=notes.get(position).getTime();

        holder.nTitle.setText(title);
        holder.nDate.setText(date);
        holder.nTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView nTitle,nDate,nTime;
        public MyHolder(@NonNull View itemView) {
            super(itemView);

            nTitle=itemView.findViewById(R.id.title_ID);
            nDate=itemView.findViewById(R.id.date_ID);
            nTime=itemView.findViewById(R.id.time_ID);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(context,Details.class);
                    intent.putExtra("ID",notes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);

                }
            });
        }
    }
}
