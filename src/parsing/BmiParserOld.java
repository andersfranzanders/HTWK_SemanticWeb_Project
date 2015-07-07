package parsing;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import classModel.ResourceClass;
import classModel.State;

public class BmiParserOld implements Parser {

	String sURL = "https://api.import.io/store/data/6f11104c-5f02-46e1-adaa-730e018e5dac/_query?input/webpage/url=http%3A%2F%2Fwww.focus.de%2Fgesundheit%2Fernaehrung%2Fnews%2Ftid-8485%2Fuebergewicht_aid_232449.html&_user=74fe7076-4cf9-4ac6-a39e-137feaec6912&_apikey=74fe7076-4cf9-4ac6-a39e-137feaec6912%3A65%2F7D%2BQ1VINrFD%2Fn0utVvr52NOfG8kmACmnbW9tg5%2FMHQ7cI%2BFaLfdQT4HoqtH5cBPxhkLzOzh%2BV%2BOPGZe8THw%3D%3D";

	private JsonObject returnJsonRootObject(HttpURLConnection request) throws IOException {
		// Convert to a JSON object to print data
		JsonParser jp = new JsonParser(); // from gson
		JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));

		JsonObject rootobj = root.getAsJsonObject();
		return rootobj;
	}

	private HttpURLConnection connectToUrl() throws MalformedURLException, IOException {
		// Connect to the URL using java's native library
		URL url = new URL(sURL);
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();
		return request;
	}

	@Override
	public void parse(List<ResourceClass> resourceList) {

		HttpURLConnection request;
		try {
			request = connectToUrl();

			JsonObject rootobj = returnJsonRootObject(request);

			JsonArray results = rootobj.getAsJsonArray("results");
			for (JsonElement object : results) {
				String bundesland = object.getAsJsonObject().get("my_column").getAsString();
				// System.out.println(bundesland);
				String bmi = object.getAsJsonObject().get("bmi").getAsString();
				// System.out.println(bmi);

				for (ResourceClass resource : resourceList) {
					if (resource instanceof State) {
						State state = (State) resource;

						if (state.getName().equals(bundesland)) {
							state.setDurchschnittsBmi(bmi);
						}
					}
				}
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
