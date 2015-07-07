package rdfStuff;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.ontology.OntModel;

import ontology.OntologyBuilder;
import ontology.StatesOntologyBuilder;
import parsing.BmiParserOld;
import parsing.BmiParser;
import parsing.IncomeParser;
import parsing.Parser;
import parsing.StatesParser;
import classModel.ResourceClass;
import classModel.State;

public class StatesRDF {

	public static void main(String[] args) {

		List<ResourceClass> statesList = new ArrayList<ResourceClass>();

		Parser statesParser = new StatesParser();
		Parser incomeParser = new IncomeParser();
		Parser bmiParser = new BmiParser();
		statesParser.parse(statesList);
		incomeParser.parse(statesList);
		bmiParser.parse(statesList);
		
		OntologyBuilder o = new StatesOntologyBuilder();
		o.initializeModel();
		OntModel ontModel = o.convertParsedDataToTriples(statesList);
		o.writeOutModel(ontModel, "states.rdf");

		// for (ResourceClass resource : statesList) {
		//
		// if (resource instanceof State) {
		//
		// State state = (State) resource;
		//
		// System.out.println(state.getName());
		// System.out.println(state.getResourceUrl());
		// System.out.println(state.getEinwohnerZahl());
		// System.out.println(state.getIncome());
		// System.out.println(state.getDurchschnittsBmi());
		// }
		//
		// }

	}

}
