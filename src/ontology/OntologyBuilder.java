package ontology;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import se.walkercrou.places.Place;
import classModel.City;
import classModel.ResourceClass;
import classModel.State;

import com.hp.hpl.jena.ontology.DatatypeProperty;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.ObjectProperty;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.ResourceFactory;

public abstract class OntologyBuilder {

	static OntModelSpec modelSpec = OntModelSpec.OWL_DL_MEM_RULE_INF;
	static String rdfStile = "RDF/XML-ABBREV";

	public static String getRdfStile() {
		return rdfStile;
	}

	public static OntModelSpec getModelSpec() {
		return modelSpec;
	}

	String nameSpace_STATE = "http://de.wikipedia.org/wiki/state";
	String nameSpace_CITY = "http://de.wikipedia.org/wiki/city";
	String nameSpace_PROPERTYS = "http://www.imn.htwk-leipzig.de/~fanders#";
	String dbPedia = "http://de.dbpedia.org/page/";
	String dublinCoreOnt = "http://dublincore.org/documents/usageguide/glossary.shtml#";
	String dbPediaOnt = "http://dbpedia.org/ontology/";
	String geoNames = "http://www.geonames.org/ontology#";

	public abstract void initializeModel();

	public abstract OntModel convertParsedDataToTriples(List<ResourceClass> rList);

	public void writeOutModel(OntModel model, String dataName) {

		model.write(System.out, rdfStile);

		try {
			model.write(new FileOutputStream("resources/" + dataName), rdfStile);
			//model.write(new FileOutputStream("/Users/franzanders/Desktop/" + dataName), rdfStile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}
}
