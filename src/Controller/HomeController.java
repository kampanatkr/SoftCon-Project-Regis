package Controller;

import DB.DBConnecter;
import Model.Course;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class HomeController {
    private Connection connection;
    private DBConnecter dbConnecter = new DBConnecter();
    private List<Course> courses = new ArrayList<>();

    @FXML protected TextArea showCourseTextArea;
    @FXML protected Label titleHomeLabel;
    @FXML protected Label commandLabel;
    @FXML protected TextField courseTextField;
    @FXML protected TextField semesterTextField;
    @FXML protected TextField yearTextField;
    @FXML protected Button searchCourseButton;

    private String year;
    private String semester;
    private String courseId;

    private Collection<Course> courseList = new ArrayList<>();;

    public void initialize(){
        DBConnecter dbConnecter = new DBConnecter();
        connection = dbConnecter.connect();
        courseList =  dbConnecter.getCourse();
    }

    @FXML
    public void showCourses(String point){
        year = yearTextField.getText();
        semester = semesterTextField.getText();
        courseId = courseTextField.getText();
        if (point.equals("year")){
            for (Course c:
                 courseList) {
                if (c.getYear() == Integer.parseInt(year)){
                    showCourseTextArea.setText(c.toString());
                }
            }
        }else if (point.equals("semester")){
            for (Course c:
                    courseList) {
                if (c.getSemester() == Integer.parseInt(semester)){
                    showCourseTextArea.setText(c.toString());
                }
            }
        }else if (point.equals("yearandsemester")){
            for (Course c:
                    courseList) {
                if (c.getYear() == Integer.parseInt(year) && c.getSemester() == Integer.parseInt(semester)){
                    showCourseTextArea.setText(c.toString());
                }
            }
        }else if (point.equals("id")){
            for (Course c:
                    courseList) {
                if (c.getSubjectId().equals(courseId)){
                    showCourseTextArea.setText(c.toString());
                }
            }
        }

    }

    @FXML
    public void pressSearchButton(){
        year = yearTextField.getText();
        semester = semesterTextField.getText();
        courseId = courseTextField.getText();
        if (semester.isEmpty() && courseId.isEmpty()){
            System.out.println("Show courses by year.");
            showCourses("year");
        }else if (year.isEmpty() && courseId.isEmpty()){
            System.out.println("Show courses by semester.");
            showCourses("semester");
        }else if (year.isEmpty() && semester.isEmpty()){
            System.out.println("Show courses by courseId.");
            showCourses("id");
        }else if (courseId.isEmpty()){
            System.out.println("Show courses by year & semester.");
            showCourses("yearandsemester");
        }
    }
}
