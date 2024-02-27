package com.example.wykrywaczapp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import org.osmdroid.bonuspack.kml.KmlFeature;
import org.osmdroid.bonuspack.kml.KmlLineString;
import org.osmdroid.bonuspack.kml.KmlPlacemark;
import org.osmdroid.bonuspack.kml.KmlPoint;
import org.osmdroid.bonuspack.kml.KmlPolygon;
import org.osmdroid.bonuspack.kml.KmlTrack;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.Overlay;
import org.osmdroid.views.overlay.Polygon;
import org.osmdroid.views.overlay.Polyline;

public class RouteStyler implements KmlFeature.Styler
{
    FragmentInterface listener;
    FragmentManager fragmentManager;
    public RouteStyler(FragmentInterface lis)
    {
        listener = lis;
    }

    @Override
    public void onFeature(Overlay overlay, KmlFeature kmlFeature)
    {

    }

    @Override
    public void onPoint(Marker marker, KmlPlacemark kmlPlacemark, KmlPoint kmlPoint)
    {

    }

    @Override
    public void onLineString(Polyline polyline, KmlPlacemark kmlPlacemark, KmlLineString kmlLineString)
    {
        polyline.setColor(Color.RED);

        Bundle bundle = new Bundle();
        bundle.putString("ROUTE_NAME", kmlPlacemark.getExtendedData("name"));
        polyline.setOnClickListener(new Polyline.OnClickListener()
        {
            @Override
            public boolean onClick(Polyline polyline, MapView mapView, GeoPoint eventPos)
            {
                listener.getSupportFM().beginTransaction()
                        .setReorderingAllowed(true)
                        .add(R.id.fragment_container_view, InfoFragment.class, bundle)
                        .commit();

                return true;
            }
        });
    }

    @Override
    public void onPolygon(Polygon polygon, KmlPlacemark kmlPlacemark, KmlPolygon kmlPolygon)
    {

    }

    @Override
    public void onTrack(Polyline polyline, KmlPlacemark kmlPlacemark, KmlTrack kmlTrack)
    {

    }
}
