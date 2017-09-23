package com.example.kapusta.elements2;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kapusta.elements2.ElementInterface.ElementListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kapusta on 10.09.2017.
 */

public class ResViewAdapter extends  RecyclerView.Adapter<ResViewAdapter.ElementViewHolder> {

    public static class ElementViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView tv_name;
        TextView tv_number;
        ImageView iv_symbol;

        ElementViewHolder(View itemView) {
            super(itemView);
            cv =  itemView.findViewById(R.id.cv);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_number = itemView.findViewById(R.id.tv_number);
            iv_symbol = itemView.findViewById(R.id.iv_symbol);
        }
    }

    List<Element> elements;
    ElementListener listener;

    ResViewAdapter(List<Element> elements, ElementListener listener){
        this.elements = elements;
        this.listener = listener;
    }

    public List<Element> getElements() {
        return elements;
    }

    @Override
    public ResViewAdapter.ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        ElementViewHolder pvh = new ElementViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ElementViewHolder holder, final int position) {
        holder.tv_name.setText(elements.get(position).getName());
        holder.tv_number.setText("" +elements.get(position).getInfo());
        holder.iv_symbol.setImageResource(R.drawable.atom);
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getElement(elements.get(position));
            }
        });
    }

    public void addElement(Element element){
        getElements().add(element);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return elements.size();
    }
}