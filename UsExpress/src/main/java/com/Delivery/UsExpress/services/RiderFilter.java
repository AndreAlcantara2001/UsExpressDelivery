/**
 * 
 */
package com.Delivery.UsExpress.services;

import java.util.ArrayList;
import java.util.List;

import com.Delivery.UsExpress.entites.PickUpAddress;
import com.Delivery.UsExpress.entites.Rider;



/**
 * @author Acer
 *
 */


public class RiderFilter {
	public List<Rider> findNearRiders(List<Rider> riders, PickUpAddress pickupAddress) {
        List<Rider> filteredRiders = new ArrayList<>();
        
        for (Rider rider : riders) {
            double distance = findDistance(pickupAddress.getLatitude(),pickupAddress.getLongitude(),rider.getLatitude(),rider.getLongitude());
            if (distance <= 3) {
                filteredRiders.add(rider);
            }
        }
		return filteredRiders;
		
		

	}
	

	public static double findDistance(double pickupLat, double pickupLng, double riderLat, double riderLng) {
        double earthRadius = 6378;//km

        double userLatRad = Math.toRadians(pickupLat);
        double userLngRad = Math.toRadians(pickupLng);
        double riderLatRad = Math.toRadians(riderLat);
        double riderLngRad = Math.toRadians(riderLng);

        double lngDiff = riderLngRad - userLngRad;
        double latDiff = riderLatRad - userLatRad;

        double a = Math.pow(Math.sin(latDiff / 2), 2)
                + Math.cos(userLatRad) * Math.cos(riderLatRad) * Math.pow(Math.sin(lngDiff / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return earthRadius * c;//km
    }


}
