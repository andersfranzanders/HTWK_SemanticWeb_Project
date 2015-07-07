package sparql;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ontology.OntologyBuilder;
import ontology.StatesOntologyBuilder;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class SPARQLer {

	String statesFile = "resources/states.rdf";
	String citysFile = "resources/citys.rdf";

	Model stateModel;
	Model cityModel;

	public SPARQLer() {

		try {
			stateModel = ModelFactory.createOntologyModel(OntologyBuilder.getModelSpec());
			InputStream rdfStateFile = new FileInputStream(new File(statesFile));
			stateModel.read(rdfStateFile, OntologyBuilder.getRdfStile());

			cityModel = ModelFactory.createOntologyModel(OntologyBuilder.getModelSpec());
			InputStream rdfCityFile = new FileInputStream(new File(citysFile));
			cityModel.read(rdfCityFile, OntologyBuilder.getRdfStile());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public List<CityWrapper> retriveCityResources() {
		
		List<CityWrapper> cityWrapperList = new ArrayList<CityWrapper>();

		String queryString = " PREFIX dbo:<http://dbpedia.org/ontology/> "
				+ "PREFIX geo: <http://www.geonames.org/ontology#> SELECT ?x ?y WHERE { ?x a dbo:city. ?x geo:name ?y }";
		
		Query query = QueryFactory.create(queryString);
		
		try (QueryExecution qexec = QueryExecutionFactory.create(query, cityModel)) {
			ResultSet results = qexec.execSelect();
			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				Resource r = soln.getResource("?x");
				Literal l = soln.getLiteral("?y");
				
				CityWrapper cityWrapper = new CityWrapper();
				cityWrapper.setCityName(l.toString());
				cityWrapper.setCityResource(r);
				cityWrapperList.add(cityWrapper);
			}
		}

		return cityWrapperList;
	}

	public Resource retriveStateResource(String stateName) {

		String queryString = " PREFIX wiki:<http://de.dbpedia.org/page/Kategorie:> "
				+ "PREFIX geo: <http://www.geonames.org/ontology#> SELECT ?x WHERE { ?x a wiki:Bundesland_%28Deutschland%29. ?x geo:name \"" + stateName
				+ "\"  } ";
		Query query = QueryFactory.create(queryString);
		try (QueryExecution qexec = QueryExecutionFactory.create(query, stateModel)) {
			ResultSet results = qexec.execSelect();
			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				Resource r = soln.getResource("?x");
				//System.out.println(r);

				return r;

			}
		}
		return null;

	}

}
