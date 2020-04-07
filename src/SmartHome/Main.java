package SmartHome;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import org.apache.jena.query.Query;
import org.topbraid.spin.arq.ARQFactory;
/**
 *
 * @author Antonis Pliatsios
 */
public class Main {

    /**
     * Main class of example project Smart Home Semantics
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        //String filename=args[0];
        String fileName = "C:/SmartHome3.owl";
        //run constructor for JenaClass J
        JenaClass J = new JenaClass();
        // call function makeSmartHomeModel to create my model
        J.setMyModel(J.makeSmartHomeModel(fileName));
        //create new Activity class for Jena Object J 
        J.setMyOnt(J.createActivityClass());
        // Array List with names of all Activities
        ArrayList<String> results = J.findAllInstances();
        //Array List for all activities of model 
        ArrayList<Activity> activities = new ArrayList<>();
        //Array List for all Observations of one Activity
        ArrayList<Observation> myObservations = new ArrayList<>();
        //Looping Array List
        for (int i = 0; i < results.size(); i++) {
            //Populate Objects by calling the fuction populateClassObjects 
            J.populateClassObjects(results.get(i), activities, myObservations);//first parameter is the name of current Activity
        }
        //Print all Activities        
        for (int i = 0; i < activities.size(); i++) {
            System.out.println(" #Activity:" + (i + 1)); //here it display my Activity individuals
            activities.get(i).printInfo();
        }
        //call function with query for question 3.1       
        J.printQueryData(J.queryForAllObservations());
        //call function with query for question 3.2
        J.printQueryData(J.queryForActivities());
        //call function with query for question 3.3
        J.printQueryData(J.queryWithFilter());
    }
}
