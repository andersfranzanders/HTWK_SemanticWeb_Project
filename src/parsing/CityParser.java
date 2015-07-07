package parsing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ontology.OntologyBuilder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import classModel.City;
import classModel.McDonaldsRestaurant;
import classModel.ResourceClass;

public class CityParser implements Parser {

	static String sourceUrl = "http://de.wikipedia.org/wiki/Liste_der_Gro%C3%9F-_und_Mittelst%C3%A4dte_in_Deutschland";

	@Override
	public void parse(List<ResourceClass> resourceList) {

		try {
			Document doc = Jsoup.connect(sourceUrl).get();

			Element table = doc.select("table").get(1);
			Elements rows = table.select("tr");

			for (int i = 1; i < rows.size(); i++) {
				Element row = rows.get(i);
				Elements cols = row.select("td");

				City city = new City();
				city.setName(cols.get(1).text()
			             .replaceAll("[(*)]", "")
			             .replaceAll("[\\d]", ""));
				if (cols.get(1).text().contains("*")) {
					city.setResourceUrl(cols.get(1).select("a").first().attr("href"));
				} else {
					city.setResourceUrl(cols.get(1).select("a").last().attr("href"));
				}
				city.setEinwohnerZahl(cols.get(7).text()
			             .replaceAll("\\[\\d*\\]", "")
			    	     .replace(".", ""));
				city.setBundesland(cols.get(8).text()
			             .replaceAll("[\\d]", ""));

				resourceList.add(city);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<City> onlyParseCityNames() {

		List<City> cityList = new ArrayList<City>();

		try {
			Document doc = Jsoup.connect(sourceUrl).get();

			Element table = doc.select("table").get(1);
			Elements rows = table.select("tr");

			for (int i = 1; i < rows.size(); i++) {
				Element row = rows.get(i);
				Elements cols = row.select("td");

				City city = new City();
				city.setName(cols.get(1).text());
				if (cols.get(1).text().contains("*")) {
					city.setResourceUrl(cols.get(1).select("a").first().attr("href"));
				} else {
					city.setResourceUrl(cols.get(1).select("a").last().attr("href"));
				}
				cityList.add(city);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return cityList;

	}
}
