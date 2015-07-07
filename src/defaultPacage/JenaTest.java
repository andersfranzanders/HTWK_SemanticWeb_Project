package defaultPacage;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.VCARD;

public class JenaTest {
	static String personURI = "http://somewhere/JohnSmith";
	static String personURI2 = "http://somewhere/PhilippAnders";
	static String fullName = "John Smith";

	public static void main(String args[]) {

		org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.OFF);
		// create an empty model
		Model model = ModelFactory.createDefaultModel();
		//
		// // create the resource
		// Resource johnSmith = model.createResource(personURI);
		//
		// // add the property
		// johnSmith.addProperty(VCARD.FN, fullName);

		Resource johnSmith = model.createResource(personURI).addProperty(VCARD.FN, fullName);
		Resource philAnders = model.createResource(personURI2).addProperty(VCARD.EMAIL, "phil-a@gmx.de");
	}
}
