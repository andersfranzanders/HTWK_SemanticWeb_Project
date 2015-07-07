package tutorials;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

public class SparqlTest {

	public static void main(String[] args) throws FileNotFoundException {

		OntModel base = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);

		InputStream rdfFile = new FileInputStream(new File("/Users/franzanders/Desktop/states.rdf"));
		base.read(rdfFile, "RDF/XML-ABBREV");

		OntClass stateClass = base.getOntClass("http://de.wikipedia.org/wiki/Bundesland");

		Model model = base;
		String queryString = " PREFIX wiki:<http://de.wikipedia.org/wiki/> "
				+ "PREFIX ver: <http://properties#> SELECT ?x WHERE { ?x a wiki:Bundesland. ?x ver:name \"Hamburg\"  } ";
		Query query = QueryFactory.create(queryString);
		try (QueryExecution qexec = QueryExecutionFactory.create(query, model)) {
			ResultSet results = qexec.execSelect();
			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				
				System.out.println(soln.toString());
				Resource r = soln.getResource("?x"); // Get a result variable
														// - must be a resource
				System.out.println(r);

			}
		}

		//base.write(System.out, "RDF/XML-ABBREV");
	}

}
