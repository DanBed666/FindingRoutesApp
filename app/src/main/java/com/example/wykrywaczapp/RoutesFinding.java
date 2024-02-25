package com.example.wykrywaczapp;

import android.content.Context;
import android.widget.Toast;

import org.osmdroid.bonuspack.kml.KmlDocument;
import org.osmdroid.bonuspack.location.OverpassAPIProvider;
import org.osmdroid.util.BoundingBox;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;

public class RoutesFinding
{
    MapView map;
    GeoPoint location;
    Context context;
    public RoutesFinding(MapView m, GeoPoint loc, Context c)
    {
        map = m;
        location = loc;
        context = c;
    }
    public void findRoutes()
    {
        OverpassAPIProvider overpassAPIProvider = new OverpassAPIProvider();
        BoundingBox range = new BoundingBox(location.getLatitude() + 0.25, location.getLongitude() + 0.25,
                location.getLatitude() - 0.25, location.getLongitude() - 0.25);
        String url = overpassAPIProvider.urlForTagSearchKml("route=hiking", range, 500, 30);
        KmlDocument kmlDocument = new KmlDocument();
        boolean ok = overpassAPIProvider.addInKmlFolder(kmlDocument.mKmlRoot, url);

        if (ok)
        {
            FolderOverlay folderOverlay = (FolderOverlay) kmlDocument.mKmlRoot.buildOverlay(map, null, null, kmlDocument);
            map.getOverlays().add(folderOverlay);
        }
        else
        {
            Toast.makeText(context, "Nie znaleziono szlaków w danym obszarze!", Toast.LENGTH_SHORT).show();
        }
    }
}
