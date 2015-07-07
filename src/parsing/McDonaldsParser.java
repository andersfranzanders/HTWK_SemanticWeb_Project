package parsing;

import java.util.List;

import classModel.City;
import classModel.McDonaldsRestaurant;
import classModel.ResourceClass;
import ontology.OntologyBuilder;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import sparql.CityWrapper;
import sparql.SPARQLer;

public class McDonaldsParser implements Parser {

	static String APIkey = "AIzaSyA6WUB-kXVzckzRi0XCeJsGaxBcFAl3nZo";

	@Override
	public void parse(List<ResourceClass> resourceList) {

		SPARQLer sparqler = new SPARQLer();
		List<CityWrapper> cityList = sparqler.retriveCityResources();

		GooglePlaces client = new GooglePlaces(APIkey);
		int requestNr = 0;

		for (CityWrapper city : cityList) {

			requestNr++;
			System.out.println("RequestNr: " + requestNr);

			// if (city.getCityName().equals("Jena")) {

			String queryForGoogle = "McDonalds " + city.getCityName();
			System.out.println(city.getCityName());

			try {

				List<Place> places = client.getPlacesByQuery(queryForGoogle, GooglePlaces.MAXIMUM_RESULTS);

				int id = 1;

				for (Place place : places) {

					// System.out.println(place.getAddress());

					if (place.getAddress().contains(city.getCityName() + ", Germany")) {

						McDonaldsRestaurant restaurant = new McDonaldsRestaurant();

						restaurant.setName(city.getCityName() + "_" + id);
						restaurant.setAdress(place.getAddress());
						restaurant.setCityResource(city.getCityResource());
						resourceList.add(restaurant);

						id++;
						System.out.println(place.getAddress());

					}
				}

				// Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("No Restaurant found");
			}
			// }
		}
	}
}
