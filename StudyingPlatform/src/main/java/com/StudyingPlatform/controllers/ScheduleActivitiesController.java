package com.StudyingPlatform.controllers;

import com.StudyingPlatform.model.Professor;
import com.StudyingPlatform.model.SubjectProfessor;
import com.StudyingPlatform.service.DataBaseService;
import com.StudyingPlatform.service.ProfessorService;
import com.StudyingPlatform.service.SubjectProfessorService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Stage;

import javax.security.auth.Subject;
import java.net.URL;
import java.util.ResourceBundle;

public class ScheduleActivitiesController implements Initializable {
    @FXML
    private ChoiceBox<String> lectureDayDropDown;
    @FXML
    private ChoiceBox<String> seminarDayDropDown;
    @FXML
    private ChoiceBox<String> labDayDropDown;
    @FXML
    private Spinner<Integer> lectureStartSpinner;
    @FXML
    private Spinner<Integer> seminarStartSpinner;
    @FXML
    private Spinner<Integer> labStartSpinner;
    @FXML
    private Spinner<Integer> lectureEndSpinner;
    @FXML
    private Spinner<Integer> seminarEndSpinner;
    @FXML
    private Spinner<Integer> labEndSpinner;
    @FXML
    private Spinner<Integer> capacitySpinner;

    SubjectProfessor subject;
    Stage myStage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] weekDays = { "MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        lectureDayDropDown.getItems().addAll(weekDays);
        seminarDayDropDown.getItems().addAll(weekDays);
        labDayDropDown.getItems().addAll(weekDays);
        SpinnerValueFactory<Integer> lectureStartSpinnerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(6,22);
        lectureStartSpinnerFactory.setValue(8);
        lectureStartSpinner.setValueFactory(lectureStartSpinnerFactory);

        SpinnerValueFactory<Integer> seminarStartSpinnerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(6,22);
        seminarStartSpinnerFactory.setValue(8);
        seminarStartSpinner.setValueFactory(seminarStartSpinnerFactory);

        SpinnerValueFactory<Integer> labStartSpinnerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(6,22);
        labStartSpinnerFactory.setValue(8);
        labStartSpinner.setValueFactory(labStartSpinnerFactory);

        SpinnerValueFactory<Integer> lectureEndSpinnerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(6,23);
        lectureEndSpinnerFactory.setValue(9);
        lectureEndSpinner.setValueFactory(lectureEndSpinnerFactory);

        SpinnerValueFactory<Integer> seminarEndSpinnerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(6,23);
        seminarEndSpinnerFactory.setValue(9);
        seminarEndSpinner.setValueFactory(seminarEndSpinnerFactory);

        SpinnerValueFactory<Integer> labEndSpinnerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(6,23);
        labEndSpinnerFactory.setValue(9);
        labEndSpinner.setValueFactory(labEndSpinnerFactory);

        SpinnerValueFactory<Integer> capacitySpinnerFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(10,200);
        capacitySpinnerFactory.setValue(60);
        capacitySpinner.setValueFactory(capacitySpinnerFactory);
        //DEL LATER
        try {
            Professor professor = (Professor) DataBaseService.getUserById(2);
            subject = ProfessorService.professorGetSubjects(professor).get(0);
            subject.setProfessor(professor);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    public void onCancelButtonClick(){
        myStage.close();
    }

    @FXML
    public void onScheduleButtonClick(){
        if(subject.getHasLecture()){
            if(lectureDayDropDown.getValue() == null){
                SuperController.popError("Lecture requires a day.");
                return;
            }
            if(!isTimeValid(lectureStartSpinner,lectureEndSpinner)){
                SuperController.popError("Invalid hour for lecture");
                return;
            }
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
        }
    }

    public void create(SubjectProfessor subject, Stage stage){
        this.subject = subject;
        this.myStage = stage;
        disableAll();
        if(subject.getHasLecture()){
            lectureDayDropDown.setDisable(false);
            lectureStartSpinner.setDisable(false);
            lectureEndSpinner.setDisable(false);
        }
        if(subject.getHasSeminar()){
            seminarDayDropDown.setDisable(false);
            seminarStartSpinner.setDisable(false);
            seminarEndSpinner.setDisable(false);
        }
        if(subject.getHasLab()){
            labDayDropDown.setDisable(false);
            labStartSpinner.setDisable(false);
            labEndSpinner.setDisable(false);
        }
    }
    public void disableAll(){
        lectureDayDropDown.setDisable(true);
        lectureStartSpinner.setDisable(true);
        lectureEndSpinner.setDisable(true);
        seminarDayDropDown.setDisable(true);
        seminarStartSpinner.setDisable(true);
        seminarEndSpinner.setDisable(true);
        labDayDropDown.setDisable(true);
        labStartSpinner.setDisable(true);
        labEndSpinner.setDisable(true);
    }

    private boolean isTimeValid(Spinner<Integer> start, Spinner<Integer> end){
        if(start.getValue() == null || end.getValue() == null)
            return false;
        if(start.getValue() >= end.getValue())
            return false;
        return true;
    }
}
