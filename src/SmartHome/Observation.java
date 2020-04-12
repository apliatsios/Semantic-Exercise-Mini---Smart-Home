package SmartHome;

public class Observation {

    private String content;
    private String startTime;
    private String endTime;

  
    public Observation(String content, String startTime, String endTime) {
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Observation() {
    }

    public void setAll(String content, String startTime, String endTime) {
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;

    }

    @Override
    public String toString() {
        return "Observation{" + "content=" + content + ", startTime=" + startTime + ", endTime=" + endTime + '}';
    }

    //print data of current Observation 
    public void printInfo() {
        System.out.println("<content> :" + getContent());
        System.out.println("<startTime> " + getStartTime());
        System.out.println("<endTime> " + getEndTime());
    }
}
