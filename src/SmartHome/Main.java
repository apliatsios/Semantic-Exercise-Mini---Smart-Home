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
     * Main class of project SmartHomeSemantics
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        //String filename=args[0];
        String fileName = "C:/SmartHome3.owl";
        JenaClass J = new JenaClass();
     
        J.setMyModel(J.createModel(fileName));
     
        J.setMyOnt(J.createActivityClass());
        ArrayList<String> namesOfActivities = J.findAllInstances();
        ArrayList<Activity> activities = new ArrayList<>();
        ArrayList<Observation> myObservations = new ArrayList<>();
        for (int i = 0; i < namesOfActivities.size(); i++) {
            J.populateObject(namesOfActivities.get(i), activities, myObservations);//first parameter is the name of current Activity
        }
        //here it display Activity individuals      
        for (int i = 0; i < activities.size(); i++) {
            System.out.println(" #Activity:" + (i + 1)); 
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
