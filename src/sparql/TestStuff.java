package sparql;

import java.util.ArrayList;
import java.util.List;

import parsing.BmiParser;
import parsing.Parser;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import classModel.ResourceClass;
import classModel.State;

public class TestStuff {

	static String APIkey = "AIzaSyA6WUB-kXVzckzRi0XCeJsGaxBcFAl3nZo";

	public static void main(String[] args) {

		GooglePlaces client = new GooglePlaces(APIkey);

		List<Place> places = client.getPlacesByQuery("McDonalds Leipzig", 1);
		
		for(Place place: places){
			System.out.println(place.getAddress());
		}
	}

}
