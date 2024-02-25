package com.example.wykrywaczapp;

import android.content.Context;
import android.util.Log;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class Utilities
{
    MapView map;
    Context context;
    MyLocationNewOverlay mLocationOverlay;
    public Utilities(MapView m, Context c)
    {
        map = m;
        context = c;
    }
    public void setLocation()
    {
        mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(context),map);
        mLocationOverlay.enableFollowLocation();
        map.getOverlays().add(mLocationOverlay);
        map.getController().setZoom(18.0);
    }

    public void setCompass()
    {
        CompassOverlay mCompassOverlay = new CompassOverlay(context, new InternalCompassOrientationProvider(context), map);
        mCompassOverlay.enableCompass();
        map.getOverlays().add(mCompassOverlay);
    }

    public void zoomToLoc()
    {
        GeoPoint location = mLocationOverlay.getMyLocation();
        map.getController().setCenter(location);
        map.getController().setZoom(18.0);
    }

    public GeoPoint getLocation()
    {
        return mLocationOverlay.getMyLocation();
    }
}
