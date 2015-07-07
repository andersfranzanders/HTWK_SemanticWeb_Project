package classModel;

import com.hp.hpl.jena.rdf.model.Resource;

public class McDonaldsRestaurant extends ResourceClass {

	String adress;
	Resource cityResource;

	public Resource getCityResource() {
		return cityResource;
	}

	public void setCityResource(Resource cityResource) {
		this.cityResource = cityResource;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

}
