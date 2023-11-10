package com.hackathon.decoder;

import com.hackathon.dto.route.response.LatLng;

import java.util.ArrayList;
import java.util.List;

public class PolyLineDecoder {
    public static List<LatLng> decodePolyline(String encodedPolyline) {
        List<LatLng> polylinePoints = new ArrayList<>();
        int index = 0;
        int len = encodedPolyline.length();
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encodedPolyline.charAt(index++) - 63;
                result |= (b & 0x1F) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encodedPolyline.charAt(index++) - 63;
                result |= (b & 0x1F) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng point = new LatLng(lat / 1e5, lng / 1e5);
            polylinePoints.add(point);
        }

        return polylinePoints;
    }
}