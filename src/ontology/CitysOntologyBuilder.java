package ontology;

import java.util.List;

import sparql.SPARQLer;
import classModel.City;
import classModel.ResourceClass;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class CitysOntologyBuilder extends OntologyBuilder {

	String uriCityClass = dbPediaOnt + "city";
	String prefixForCitysUrl = "http://de.wikipedia.org";

	OntModel cityModel = ModelFactory.createOntologyModel(modelSpec);
	OntClass cityClass = cityModel.createClass(uriCityClass);

	DatatypeProperty nameProperty = cityModel.createDatatypeProperty(geoNames + "name");
	DatatypeProperty inhabitantsProperty = cityModel.createDatatypeProperty(geoNames + "population");
	ObjectProperty stateProperty = cityModel.createObjectProperty(dbPediaOnt + "federalState");

	@Override
	public void initializeModel() {

		nameProperty.addDomain(cityClass);
		inhabitantsProperty.addDomain(cityClass);
		stateProperty.addDomain(cityClass);
		
		cityClass.addLabel("A german city", "eng");
		nameProperty.addLabel("The Name of the City in German", "eng");
		inhabitantsProperty.addLabel("Total population of the german city", "eng");
		stateProperty.addLabel("The german State this city belongs to", "eng");

		cityModel.setNsPrefix("geo", geoNames);
		cityModel.setNsPrefix("dbo", dbPediaOnt);

	}

	@Override
	public OntModel convertParsedDataToTriples(List<ResourceClass> rList) {
		
		SPARQLer sparqler = new SPARQLer();

		for (ResourceClass resource : rList) {

			if (resource instanceof City) {

				City city = (City) resource;

				String cityState = city.getState();

				//Resource stateR = cityModel.createResource(nameSpace_STATE + cityState);
				
				Resource stateR = sparqler.retriveStateResource(cityState);
				//System.out.println(stateR);
				
				Individual i = cityModel.createIndividual(prefixForCitysUrl + city.getResourceUrl(), cityClass);
				i.addProperty(nameProperty, city.getName());
				i.addProperty(inhabitantsProperty, city.getEinwohnerZahl());
				i.addProperty(stateProperty, stateR);
			}
		}
		return cityModel;
	}

}
