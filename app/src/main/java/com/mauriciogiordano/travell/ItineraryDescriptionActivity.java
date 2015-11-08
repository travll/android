package com.mauriciogiordano.travell;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.GridView;

import com.mauriciogiordano.travell.adapter.PlacesAdapter;
import com.mauriciogiordano.travell.model.Destination;
import com.mauriciogiordano.travell.model.Place;

import java.util.List;

/**
 * Created by Mauricio Giordano on 11/8/15.
 * Author: Mauricio Giordano (mauricio.c.giordano@gmail.com)
 * Copyright (c) by Travell, 2015 - All rights reserved.
 */
public class ItineraryDescriptionActivity extends ActionBarActivity {

    public Destination destination;
    public List<Place> placeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_itinerary_description);

        String destinationId = getIntent().getStringExtra("destinationId");

        if (destinationId == null || destinationId.equals("")) {
            finish();
            return;
        }

        destination = Destination.find(destinationId, this);

        if (destination == null) {
            finish();
            return;
        }

        placeList = Place.findForDestination(destination.getId(), this);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        PlacesAdapter adapter = new PlacesAdapter();

        gridView.setAdapter(adapter);
        adapter.setDataList(placeList);

        getSupportActionBar().setTitle("Itinerary");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("destinationId", destination.getId());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}