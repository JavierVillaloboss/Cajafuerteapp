package com.example.cajafuerte;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.registrosVH>{

    Context context;
    ArrayList<AC> acList;


    public Adaptador(Context context, ArrayList<AC> list){
        this.context = context;
        this.acList = list;
    }


    @NonNull
    @Override
    public registrosVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.recycler,parent,false);
        return new registrosVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull registrosVH holder, int position) {
        AC ac = acList.get(position);
        holder.textViewAccion.setText(ac.getAccion());
        holder.textViewFecha.setText(ac.getFecha());
    }

    @Override
    public int getItemCount() {
        return acList.size();
    }

    public static class registrosVH extends RecyclerView.ViewHolder{

        TextView textViewAccion, textViewFecha;


        public registrosVH(@NonNull View itemView) {
            super(itemView);
            textViewAccion = itemView.findViewById(R.id.accion);
            textViewFecha =  itemView.findViewById(R.id.fecha);
        }
    }
}
