package com.StudyingPlatform.controllers;

import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.ScheduleEntry;
import com.StudyingPlatform.model.ScheduleTime;
import com.StudyingPlatform.model.SubjectProfessor;
import com.StudyingPlatform.service.DataBaseService;
import com.StudyingPlatform.service.Exceptions.ScheduleException;
import com.StudyingPlatform.service.ProfessorService;
import com.StudyingPlatform.service.SubjectProfessorService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AddActivityController implements Initializable {

    @FXML
    private ChoiceBox<String> activityDayDropDown;
    @FXML
    private Spinner<Integer> activityStartSpinner;
    @FXML
    private Spinner<Integer> activityEndSpinner;
    @FXML
    private Spinner<Integer> capacitySpinner;

    SubjectProfessor subject;
    Stage myStage;
    ChatController parentController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] weekDays = { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        activityDayDropDown.getItems().addAll(weekDays);
        SpinnerValueFactory<Integer> activityStartSpinnerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(6,22);
        activityStartSpinnerFactory.setValue(8);
        activityStartSpinner.setValueFactory(activityStartSpinnerFactory);

        SpinnerValueFactory<Integer> activityEndSpinnerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(6,23);
        activityEndSpinnerFactory.setValue(9);
        activityEndSpinner.setValueFactory(activityEndSpinnerFactory);

        SpinnerValueFactory<Integer> capacitySpinnerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(10,200);
        capacitySpinnerFactory.setValue(60);
        capacitySpinner.setValueFactory(capacitySpinnerFactory);
    }

    @FXML
    public void onCancelButtonClick(){
        myStage.close();
    }

    @FXML
    public void onScheduleButtonClick(){}/*
        List<ScheduleEntry> schedule;
        try {
            schedule = ProfessorService.professorGetSchedule((Professor)SuperController.activeUser);
        }catch(ScheduleException e){
            SuperController.popError("Something went wrong.");
            return;
        }
        if(subject.getHasLecture()){
            if(lectureDayDropDown.getValue() == null){
                SuperController.popError("Lecture requires a day.");
                return;
            }
            if(!isTimeValid(lectureStartSpinner,lectureEndSpinner)){
                SuperController.popError("Invalid hour for lecture");
                return;
            }
            ScheduleTime lectureTime = new ScheduleTime(
                    lectureDayDropDown.getValue(),
                    lectureStartSpinner.getValue(),
                    lectureEndSpinner.getValue() - lectureStartSpinner.getValue()
            );
            boolean doesOverlap = false;
            for(ScheduleEntry e:schedule){
                if(e.getTime().overlaps(lectureTime)){
                    doesOverlap = true;
                    break;
                }
            }
            if(doesOverlap){
                SuperController.popError("The lecture overlaps another activity");
                return;
            }
            subject.setScheduleLecture(lectureTime);
            schedule.add(new ScheduleEntry(lectureTime,"",""));
        }
        if(subject.getHasSeminar()){
            if(seminarDayDropDown.getValue() == null){
                SuperController.popError("Seminar requires a day.");
                return;
            }
            if(!isTimeValid(lectureStartSpinner,seminarEndSpinner)){
                SuperController.popError("Invalid hour for seminar.");
                return;
            }
            ScheduleTime seminarTime = new ScheduleTime(
                    seminarDayDropDown.getValue(),
                    seminarStartSpinner.getValue(),
                    seminarEndSpinner.getValue() - seminarStartSpinner.getValue()
            );
            boolean doesOverlap = false;
            for(ScheduleEntry e:schedule){
                if(e.getTime().overlaps(seminarTime)){
                    doesOverlap = true;
                    break;
                }
            }
            if(doesOverlap){
                SuperController.popError("The seminar overlaps another activity");
                return;
            }
            subject.setScheduleSeminar(seminarTime);
            schedule.add(new ScheduleEntry(seminarTime,"",""));
        }
        if(subject.getHasLab()){
            if(labDayDropDown.getValue() == null){
                SuperController.popError("Lab requires a day.");
                return;
            }
            if(!isTimeValid(labStartSpinner,labEndSpinner)){
                SuperController.popError("Invalid hour for lab.");
                return;
            }
            ScheduleTime labTime = new ScheduleTime(
                    labDayDropDown.getValue(),
                    labStartSpinner.getValue(),
                    labEndSpinner.getValue() - labStartSpinner.getValue()
            );
            boolean doesOverlap = false;
            for(ScheduleEntry e:schedule){
                if(e.getTime().overlaps(labTime)){
                    doesOverlap = true;
                    break;
                }
            }
            if(doesOverlap){
                SuperController.popError("The lab overlaps another activity");
                return;
            }
            subject.setScheduleLab(labTime);
            schedule.add(new ScheduleEntry(labTime,"",""));
        }
        if(capacitySpinner.getValue()!=null){
            subject.setStudentsCapacity(capacitySpinner.getValue());
        }
        try {
            SubjectProfessorService.schedule_activities(subject);
        } catch (SQLException e) {
            SuperController.popError("Something went wrong");
        }
        SuperController.popMessage("Successfully scheduled the activities");
        parentController.disableScheduleButton();
        subject.setFinishedSchedule(true);
        myStage.close();
    }
*/
    public void create( Stage stage,ChatController parentController){
        this.subject = subject;
        this.myStage = stage;
        this.parentController = parentController;

    }


    private boolean isTimeValid(Spinner<Integer> start, Spinner<Integer> end){
        if(start.getValue() == null || end.getValue() == null)
            return false;
        if(start.getValue() >= end.getValue())
            return false;
        return true;
    }
}
