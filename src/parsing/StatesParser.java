package parsing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import classModel.City;
import classModel.ResourceClass;
import classModel.State;

public class StatesParser implements Parser {

	String sourceUrl = "http://de.wikipedia.org/wiki/Land_%28Deutschland%29";

	@Override
	public void parse(List<ResourceClass> resourceList) {

		try {
			Document doc = Jsoup.connect(sourceUrl).get();

			Element table = doc.select("table").get(0);
			Elements rows = table.select("tr");

			for (int i = 1; i < rows.size() - 1; i++) {

				Element row = rows.get(i);
				Elements cols = row.select("td");

				State state = new State();
				state.setName(cols.get(1).text());
				state.setResourceUrl(cols.get(1).select("a").attr("href"));
				String inhabitants = (cols.get(9).text() + "000").replace(",", "");
				state.setEinwohnerZahl(inhabitants);

				resourceList.add(state);

			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}
