/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;
/**
 *
 * @author Antonis Pliatsios
 */
public class Observation {
    //properties
    private String content;
    private String startTime;
    private String endTime;
    //constructor
    public Observation(String content, String startTime, String endTime) {
        this.content = content;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    //getters and setters
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

    public void setObservation(String content, String startTime, String endTime) {
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
