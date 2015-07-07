package tutorials;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Iterator;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public class OntologyTest {

	public static void main(String[] args) {
		
		String NS = "https://something/whatever#";
		
		OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_DL_MEM_RULE_INF);
		
		OntClass paper     = m.createClass( NS + "Paper" );
		OntClass bestPaper = m.createClass( NS + "BestPaper" );
		
		paper.addLabel("this is the label for Paper", "en");
		
		DatatypeProperty hasProgramme = m.createDatatypeProperty( NS + "hasProgramme" );

		hasProgramme.addDomain( paper );
		
		Individual p1 = m.createIndividual( NS + "paper1", paper );
		p1.addProperty(hasProgramme, "1234");
		
			
		m.write(System.out, "RDF/XML-ABBREV");
		
//		try {
//			m.write(new FileOutputStream("/Users/franzanders/Desktop/ontology.rdf"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
	}

}
