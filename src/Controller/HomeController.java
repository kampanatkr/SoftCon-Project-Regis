package Controller;

import DB.DBConnecter;
import Model.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    @FXML protected TableView<Course> showCourseTableView;
    @FXML protected TableColumn<Course, String> courseIdColumn;
    @FXML protected TableColumn<Course, String> courseNameColumn;
    @FXML protected TableColumn<Course, Integer> yearColumn;
    @FXML protected TableColumn<Course, Integer> semesterColumn;
    @FXML protected TableColumn<Course, String> levelColumn;
    @FXML protected Label titleHomeLabel;
    @FXML protected Label commandLabel;
    @FXML protected Label addOrRemoveLabel;
    @FXML protected TextField courseTextField;
    @FXML protected TextField semesterTextField;
    @FXML protected TextField yearTextField;
    @FXML protected TextField addCourseTextField;
    @FXML protected Button searchCourseButton;
    @FXML protected Button addButton;
    @FXML protected Button removeButton;

    private Connection connection;
    private DBConnecter dbConnecter = new DBConnecter();

    private String year;
    private String semester;
    private String courseId;

    private List<Course> courseList = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public void initialize() {
        initColumn();
        connection = dbConnecter.connect();
        courseList = dbConnecter.getCourse();
    }

    @FXML
    public void showCourses(String point) {
        year = yearTextField.getText();
        semester = semesterTextField.getText();
        courseId = courseTextField.getText();

        if (point.equals("year")) {
            for (int i =0; i < courseList.size(); i++) {
                int c = courseList.get(i).getYear();
                if (c == Integer.parseInt(year)) {
                    courses.add(courseList.get(i));
                    System.out.println(courseList.get(i).getSubjectId());
                }
            }
        } else if (point.equals("semester")) {
            for (int i =0; i < courseList.size(); i++) {
                int c = courseList.get(i).getSemester();
                if (c == Integer.parseInt(semester)) {
                    courses.add(courseList.get(i));
                    System.out.println(courseList.get(i).getSubjectId());
                }
            }
        } else if (point.equals("yearandsemester")) {
            for (int i =0; i < courseList.size(); i++) {
                int c = courseList.get(i).getYear();
                int d = courseList.get(i).getSemester();
                if (c == Integer.parseInt(year) && d == Integer.parseInt(semester)) {
                    courses.add(courseList.get(i));
                    System.out.println(courseList.get(i).getSubjectId());
                }
            }
        } else if (point.equals("id")) {
            for (int i =0; i < courseList.size(); i++) {
                String c = courseList.get(i).getSubjectId();
                if (c.equals(courseId)) {
                    courses.add(courseList.get(i));
                    System.out.println(courseList.get(i).getSubjectId());
                }
            }
        }
        initData();
    }

    @FXML
    public void pressSearchButton() {
        courses.clear();
        year = yearTextField.getText();
        semester = semesterTextField.getText();
        courseId = courseTextField.getText();
        if (semester.isEmpty() && courseId.isEmpty()) {
            System.out.println("Show courses by year.");
            showCourses("year");
        } else if (year.isEmpty() && courseId.isEmpty()) {
            System.out.println("Show courses by semester.");
            showCourses("semester");
        } else if (year.isEmpty() && semester.isEmpty()) {
            System.out.println("Show courses by courseId.");
            showCourses("id");
        } else if (courseId.isEmpty()) {
            System.out.println("Show courses by year & semester.");
            showCourses("yearandsemester");
        }
        initData();
        System.out.println("searching...");
    }

    @FXML
    public void handlerAddCourseButton(){

    }

    @FXML
    public void handlerRemoveCourseButton(){

    }

    public void initData() {
        ObservableList<Course> data = FXCollections.observableList(courses);
        showCourseTableView.setItems(data);
    }
    public void initColumn(){
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("subjectId"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("subjectName"));
        yearColumn.setCellValueFactory(new  PropertyValueFactory<Course, Integer>("year"));
        semesterColumn.setCellValueFactory(new  PropertyValueFactory<Course, Integer>("semester"));
        levelColumn.setCellValueFactory(new  PropertyValueFactory<Course, String>("difficultyLevel"));
    }
}

