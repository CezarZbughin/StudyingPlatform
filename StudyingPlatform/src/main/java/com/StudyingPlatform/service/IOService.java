package com.StudyingPlatform.service;

import com.StudyingPlatform.model.ScheduleEntry;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class IOService {
    public static void writeActivities (List<ScheduleEntry> schedule) throws IOException {
        String now = Long.toString(System.currentTimeMillis());
        String myDocument = "C:\\Users\\Cezar\\Desktop\\Schedule" + now + ".csv";
        FileWriter fileOut = new FileWriter(myDocument);
        fileOut.write("Name:" + "," + "Type:" + "," + "Day:" + ","+ "Hour:" + ","+ "Duration\n");
        for (int i = 0; i < schedule.size(); i++) {
            fileOut.write(schedule.get(i).getName() + ", " + schedule.get(i).getType() + ", "
                    + schedule.get(i).getTime().getDayOfWeek()+ ", " + schedule.get(i).getTime().getHour() + ", " + schedule.get(i).getTime().getDuration() + "\n");
        }
        fileOut.close();
    }
    /*
    public static void writeGrades (List<ScheduleEntry> schedule) throws IOException {
        String now = Long.toString(System.currentTimeMillis());
        String myDocument = "C:\\Users\\Bogdan\\Desktop\\Schedule" + now + ".csv";
        FileWriter fileOut = new FileWriter(myDocument);
        fileOut.write("Name:" + "," + "Type:" + "," + "Day:" + ","+ "Hour:" + ","+ "Duration\n");
        for (int i = 0; i < schedule.size(); i++) {
            fileOut.write(schedule.get(i).getName() + ", " + schedule.get(i).getType() + ", "
                    + schedule.get(i).getTime().getDayOfWeek()+ ", " + schedule.get(i).getTime().getHour() + ", " + schedule.get(i).getTime().getDuration() + "\n");
        }
        fileOut.close();
    }
    */

}

