package SmartHome;

import java.util.ArrayList;

public class Activity {

    //Object properties
    private String content;
    private String startTime;
    private String endTime;
    ArrayList<Observation> observations = new ArrayList<Observation>();//Array List of observations of Java Object Activity

    public Activity(String content, String startTime, String endTime) {
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Activity() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAll(String content, String startTime, String endTime) {
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ArrayList<Observation> getObservations() {
        return observations;
    }

    public void setObservations(ArrayList<Observation> observations) {
        this.observations = observations;
    }

    @Override
    public String toString() {
        return "Activity{" + "content=" + content + ", startTime=" + startTime + ", endTime=" + endTime + ", observations=" + observations + '}';
    }

    public void addObservation(Observation Ob) {
        observations.add(Ob);
    }

    public void printInfo() {
        System.out.println("<content>" + getContent());
        System.out.println("<startTime>" + getStartTime());
        System.out.println("<endTime>" + getEndTime());
        for (int i = 0; i < observations.size(); i++) {
            System.out.println(" #Observation:" + (i + 1));
            observations.get(i).printInfo();
        }

    }
}
