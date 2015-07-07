package sparql;

import com.hp.hpl.jena.rdf.model.Resource;

public class CityWrapper {
	
	String cityName;
	Resource cityResource;
	
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Resource getCityResource() {
		return cityResource;
	}
	public void setCityResource(Resource cityResource) {
		this.cityResource = cityResource;
	}

	
}
