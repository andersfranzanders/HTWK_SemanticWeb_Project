package rdfStuff;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModel;

import ontology.CitysOntologyBuilder;
import ontology.OntologyBuilder;
import parsing.CityParser;
import parsing.Parser;
import classModel.City;
import classModel.ResourceClass;

public class CitysRDF {
	public static void main(String[] args) {

		List<ResourceClass> citysList = new ArrayList<ResourceClass>();

		Parser cityParser = new CityParser();
		cityParser.parse(citysList);

		CitysOntologyBuilder oBuilder = new CitysOntologyBuilder();
		
		oBuilder.initializeModel();
		OntModel oModel = oBuilder.convertParsedDataToTriples(citysList);
		oBuilder.writeOutModel(oModel, "citys.rdf");

//		for (ResourceClass resource : citysList) {
//
//			if (resource instanceof City) {
//				City city = (City) resource;
//				System.out.println(city.getName());
//				System.out.println(city.getResourceUrl());
//				System.out.println(city.getState());
//			}
//
//		}
	}
}
