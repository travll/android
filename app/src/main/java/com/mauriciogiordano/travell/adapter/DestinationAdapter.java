package com.mauriciogiordano.travell.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mauriciogiordano.travell.R;
import com.mauriciogiordano.travell.model.Destination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mauricio Giordano on 11/7/15.
 * Author: Mauricio Giordano (mauricio.c.giordano@gmail.com)
 * Copyright (c) by Travell, 2015 - All rights reserved.
 */
public class DestinationAdapter extends BaseAdapter {

    private List<Destination> dataList;

    public void setDataList(List<Destination> _dataList) {
        dataList = _dataList;
        notifyDataSetChanged();
    }

    public void remove(int i) {
        dataList.remove(i);
        notifyDataSetChanged();
    }

    public DestinationAdapter() {
        dataList = new ArrayList<>();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.adapter_destination, viewGroup, false);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        ImageView image = (ImageView) view.findViewById(R.id.image);

        Destination city = dataList.get(i);

        name.setText(city.getCity());

        Glide.with(viewGroup.getContext())
                .load(city.getImage())
                .centerCrop()
                .crossFade()
                .into(image);

        return view;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return dataList.get(i).getId().hashCode();
    }
}