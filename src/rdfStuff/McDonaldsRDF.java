package rdfStuff;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModel;

import ontology.McDonaldsOntologyBuilder;
import ontology.OntologyBuilder;
import parsing.CityParser;
import parsing.McDonaldsParser;
import parsing.Parser;
import classModel.McDonaldsRestaurant;
import classModel.ResourceClass;

public class McDonaldsRDF {

	public static void main(String[] args) {

		List<ResourceClass> McDList = new ArrayList<ResourceClass>();

		Parser mcdParser = new McDonaldsParser();
		mcdParser.parse(McDList);

		OntologyBuilder oBuilder = new McDonaldsOntologyBuilder();
		oBuilder.initializeModel();
		OntModel oModel = oBuilder.convertParsedDataToTriples(McDList);
		oBuilder.writeOutModel(oModel, "McDonalds.rdf");

		// for (ResourceClass resource : McDList) {
		//
		// if (resource instanceof McDonaldsRestaurant) {
		//
		// McDonaldsRestaurant rest = (McDonaldsRestaurant) resource;
		//
		// System.out.println(rest.getCity());
		//
		// }
		// }

	}

}
