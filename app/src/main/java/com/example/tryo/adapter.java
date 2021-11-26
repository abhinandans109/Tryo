package com.example.tryo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.viewholder> {
    Context context;
    ArrayList<items> list;

    public adapter(Context context, ArrayList<items> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        items i=list.get(position);
        holder.activity.setText(i.getActtivity());
        holder.trainername.setText(i.getTrainer_name());
        holder.type.setText(i.getType());
        holder.time.setText(i.getTime());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView activity,trainername,type,time;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            activity=itemView.findViewById(R.id.activity);
            trainername=itemView.findViewById(R.id.trainer);
            type=itemView.findViewById(R.id.type);
            time=itemView.findViewById(R.id.time);
        }
    }
}
