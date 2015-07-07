package ontology;

import java.util.List;

import rdfStuff.StatesRDF;
import classModel.ResourceClass;
import classModel.State;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class StatesOntologyBuilder extends OntologyBuilder {

	static String statesClassUri = "http://de.dbpedia.org/page/Kategorie:Bundesland_%28Deutschland%29";
	static String prefixForStatesUrl = "http://de.wikipedia.org";
	static String nameSpaceState = "http://de.wikipedia.org/wiki/";

	OntModel statesModel = ModelFactory.createOntologyModel(modelSpec);
	OntClass stateClass = statesModel.createClass(statesClassUri);

	DatatypeProperty statesNameProperty = statesModel.createDatatypeProperty(geoNames + "name");
	DatatypeProperty statesInhabitantsProperty = statesModel.createDatatypeProperty(geoNames + "population");
	DatatypeProperty incomeProperty = statesModel.createDatatypeProperty(dbPediaOnt + "income");
	DatatypeProperty maleBmiProperty = statesModel.createDatatypeProperty(nameSpace_PROPERTYS + "maleBMI");
	DatatypeProperty femaleBmiProperty = statesModel.createDatatypeProperty(nameSpace_PROPERTYS + "femaleBMI");

	@Override
	public void initializeModel() {

		stateClass.addLabel("A German state", "eng");

		statesModel.setNsPrefix("own", nameSpace_PROPERTYS);
		statesModel.setNsPrefix("wikipedia", nameSpaceState);
		statesModel.setNsPrefix("geo", geoNames);
		statesModel.setNsPrefix("dbo", dbPediaOnt);

		statesNameProperty.addDomain(stateClass);
		statesInhabitantsProperty.addDomain(stateClass);
		incomeProperty.addDomain(stateClass);
		maleBmiProperty.addDomain(stateClass);
		femaleBmiProperty.addDomain(stateClass);

		statesNameProperty.addLabel("Name of the State in German", "eng");
		statesInhabitantsProperty.addLabel("Total population of the german state", "eng");
		incomeProperty.addLabel("Average Income of the state's population", "eng");
		maleBmiProperty.addLabel("Average Bmi of the male population of this state", "eng");
		femaleBmiProperty.addLabel("Average Bmi of the female population of this state", "eng");
	}

	@Override
	public OntModel convertParsedDataToTriples(List<ResourceClass> rList) {

		for (ResourceClass resource : rList) {

			if (resource instanceof State) {

				State state = (State) resource;
				Individual i = statesModel.createIndividual(prefixForStatesUrl + state.getResourceUrl(), stateClass);
				i.addProperty(statesNameProperty, state.getName());
				i.addProperty(statesInhabitantsProperty, state.getEinwohnerZahl());
				i.addProperty(incomeProperty, Double.toString(state.getIncome()));
				i.addProperty(maleBmiProperty, state.getDurchschnittsBmiM());
				i.addProperty(femaleBmiProperty, state.getDurchschnittsBmiW());

			}

		}
		return statesModel;
	}

	public static String getStatesClassUri() {
		return statesClassUri;
	}

}
