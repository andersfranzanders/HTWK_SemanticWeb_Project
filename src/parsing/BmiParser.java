package parsing;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import classModel.ResourceClass;
import classModel.State;

public class BmiParser implements Parser {

	static String sourceUrlM = "http://www.focus.de/gesundheit/ernaehrung/news/tid-8485/uebergewicht_aid_232451.html";
	static String sourceUrlW = "http://www.focus.de/gesundheit/ernaehrung/news/tid-8485/uebergewicht_aid_232449.html";

	public void parse(List<ResourceClass> resourceList) {

		parseBmisAndAddToList(resourceList, "m", sourceUrlM);
		parseBmisAndAddToList(resourceList, "w", sourceUrlW);

	}

	private void parseBmisAndAddToList(List<ResourceClass> resourceList, String gender, String uri) {
		try {
			Document doc = Jsoup.connect(uri).get();

			Element table = doc.select("table").get(0);
			Elements rows = table.select("tr");

			for (int i = 1; i < rows.size() - 1; i++) {

				Element row = rows.get(i);
				Elements cols = row.select("td");

				String stateNameBmi = cols.get(2).text();
				String bmi = cols.get(3).text();
				bmi = bmi.replace(",", ".");

				for (ResourceClass resource : resourceList) {
					if (resource.getName().equals(stateNameBmi)) {
						State state = (State) resource;
						if (gender.equals("m")) {
							state.setDurchschnittsBmi(bmi);
						} else {
							state.setDurchschnittsBmiW(bmi);
						}
					}
				}

			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
