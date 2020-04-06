
package SmartHome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.util.iterator.ExtendedIterator;

/**
 *
 * @author Antonis Pliatsios
 */
public class JenaClass {

    private OntModel myModel;
    private OntClass myOnt;

    public JenaClass() {
    }

    public void setMyModel(OntModel myModel) {
        this.myModel = myModel;
    }

    public void setMyOnt(OntClass myOnt) {
        this.myOnt = myOnt;
    }

    public OntModel getMyModel() {
        return myModel;
    }

    public OntClass getMyOnt() {
        return myOnt;
    }

    public JenaClass(OntModel myModel, OntClass myOnt) {
        this.myModel = myModel;
        this.myOnt = myOnt;
    }

    /**
     * Function makeSmartHomeModel create Model from .owl file
     *
     * @param fileName name
     * @return the model from .owl file
     * @throws java.io.FileNotFoundException
     */
    public OntModel makeSmartHomeModel(String fileName) throws FileNotFoundException {
        OntModel model = ModelFactory.createOntologyModel();
        //parse file 

        try ( InputStream inputStream = new FileInputStream(fileName)) {
            model.read(inputStream, "RDF/XML"); //type of protege file
        } //type of protege file
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return model;
    }
    
    public OntClass createActivityClass() {
        return this.getMyModel().getOntClass(this.getNS() + "Activity");
    }
    
    /**
     * Function printQueryData print the results of a SPARQL query
     *
     * @param queryString a SPARQL query
     * @return the model from .owl file
     */
    public void printQueryData(String queryString) {
        Query query = QueryFactory.create(queryString);
        QueryExecution qe = QueryExecutionFactory.create(query, this.getMyModel());
        ResultSet resultset = qe.execSelect();
        ResultSetFormatter.out(System.out, resultset, query);
    }

    /**
     * Function findAllInstances find the values of all properties of Ontology class
     * @param A Ontology Class
     * @return Array List of all instances of a class
     */
    public ArrayList<String> findAllInstances() {
        ArrayList<String> result = new ArrayList<>();
        ExtendedIterator instances = getMyOnt().listInstances();
        int k = 0; //counter for import values in the correct position
        while (instances.hasNext()) {
            Individual thisInstance = (Individual) instances.next();
            result.add(result.size() - k, thisInstance.getLocalName());
            k++;
        }
        return result;
    }

    /**
     * Function populateClassObjects populate java Objects Activity,Observation
     * with values from .owl file
     * @param instanceName is the name of current instance  
     * @param activities is the Array List for activities
     * @param observations is the Array List for observations for current
     * Activity
     */
    public void populateClassObjects(String instanceName, ArrayList<Activity> activities, ArrayList<Observation> observations) {
        Activity A1 = new Activity();
        Observation Ob1 = new Observation();
        //Array List for all observations for current Activity
        ArrayList<String> myobservations = new ArrayList<>();
        //current Resource
        Resource bfo2 = this.getMyModel().getResource(this.getNS() + instanceName);
        String c = new String(); //property Activity-->content
        String s = new String(); //property Activity-->start
        String e = new String(); //property Activity-->end
        //populate the values of object with properties content,start,end
        for (StmtIterator i = bfo2.listProperties(); i.hasNext();) {
            Statement stmt2 = i.next();
            if (stmt2.getPredicate().getLocalName().equalsIgnoreCase("contentA")) {
                c = stmt2.getObject().toString();//get values for contentA property
            }
            if (stmt2.getPredicate().getLocalName().equalsIgnoreCase("startA")) {
                s = stmt2.getObject().toString();//get values for startA property
            }
            if (stmt2.getPredicate().getLocalName().equalsIgnoreCase("endA")) {
                e = stmt2.getObject().toString(); //get values for startA property
            }
            int l = 0;//counter for import values in the correct position
            if (stmt2.getPredicate().getLocalName().equalsIgnoreCase("contains")) {
                myobservations.add(l, stmt2.getObject().toString());// get values for Object property contains
                l++;
            }
        }
        A1 = new Activity(c, s, e);//set values of Activity
        activities.add(A1); //add Activity to ArrayList activities
        String cOb = new String(); //property Observation-->content for current Activity
        String sOb = new String(); //property Observation-->start for current Activity
        String eOb = new String(); //property Observation-->end for current Activity
        //looping the Array List to find current observations
        for (int j = 0; j < myobservations.size(); j++) {
            Resource bfo3 = this.getMyModel().getResource(myobservations.get(j));
            //populate the values of object with properties content,start,end
            for (StmtIterator i = bfo3.listProperties(); i.hasNext();) {
                Statement stmt3 = i.next();
                if (stmt3.getPredicate().getLocalName().equalsIgnoreCase("content")) {
                    cOb = stmt3.getObject().toString();
                }
                if (stmt3.getPredicate().getLocalName().equalsIgnoreCase("start")) {
                    sOb = stmt3.getObject().toString();
                }
                if (stmt3.getPredicate().getLocalName().equalsIgnoreCase("end")) {
                    eOb = stmt3.getObject().toString();
                }
            }
            Observation Ob2 = new Observation(cOb, sOb, eOb); //set values of observation
            A1.addObservation(Ob2); //add observation to the current Activity
        }
        myobservations.clear();//remove and wait for observations of the next Activity
    }

    public String queryForAllObservations() {
        //query for question 3.1
        String queryString
                = getSPARQLPrefixes()
                + " SELECT ?ind ?content ?start ?end  WHERE { "
                + "    ?ind rdf:type iptil-exercise1:Observation . "
                + "    ?ind  iptil-exercise1:content ?content . "
                + "    ?ind  iptil-exercise1:start ?start . "
                + "    ?ind  iptil-exercise1:end ?end ."
                + "} ";

        return queryString;
    }

    public String queryForActivities() {
        //query for question 3.2
        String queryString
                = getSPARQLPrefixes()
                + " SELECT ?ind ?contentA   WHERE { "
                + "    ?ind rdf:type iptil-exercise1:Activity . "
                + "    ?ind  iptil-exercise1:contentA ?contentA . "
                + "} ";

        return queryString;
    }
    
     public String queryWithFilter() {
        //query for question 3.3
        
        String queryString
                = getSPARQLPrefixes()
                + " SELECT ?ind ?content ?start ?end  WHERE { "
                + "    ?ind rdf:type iptil-exercise1:Observation . "
                + "    ?ind  iptil-exercise1:content ?content . "
                + "    ?ind  iptil-exercise1:start ?start . "
                + "    ?ind  iptil-exercise1:end ?end ."
                + " FILTER (?start >= '2014-05-05T18:24:13.000' && ?start <= '2014-05-05T18:55:40.000')"
                + "} ";

        return queryString;
    }

    public String getNS() {
        return this.getMyModel().getNsPrefixURI("");
    }

    public String getSPARQLPrefixes() {
        String prefixes = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> "
                + "PREFIX owl: <http://www.w3.org/2002/07/owl#> "
                + "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#> "
                + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> "
                + "PREFIX iptil-exercise1:" + "<" + this.getNS() + ">";
        return prefixes;
    }
}
