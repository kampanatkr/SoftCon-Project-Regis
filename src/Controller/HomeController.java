package Controller;

import DB.DBConnecter;
import Model.Course;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class HomeController {
    @FXML
    protected TableView<Course> showCourseTableView;
    @FXML
    protected TableColumn<Course, String> courseIdColumn;
    @FXML
    protected TableColumn<Course, String> courseNameColumn;
    @FXML
    protected TableColumn<Course, Integer> yearColumn;
    @FXML
    protected TableColumn<Course, Integer> semesterColumn;
    @FXML
    protected TableColumn<Course, String> levelColumn;
    @FXML
    protected Label titleHomeLabel;
    @FXML
    protected Label commandLabel;
    @FXML
    protected Label addOrRemoveLabel;
    @FXML
    protected TextField courseTextField;
    @FXML
    protected TextField semesterTextField;
    @FXML
    protected TextField yearTextField;
    @FXML
    protected TextField addCourseTextField;
    @FXML
    protected Button searchCourseButton;
    @FXML
    protected Button addButton;
    @FXML
    protected Button removeButton;

    private Connection connection;
    private DBConnecter dbConnecter = new DBConnecter();

    private String year;
    private String semester;
    private String courseId;

    private List<Course> courseList = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    public void initialize() {
        System.out.println("Initialize");
        initColumn();
        connection = dbConnecter.connect();
        courseList = dbConnecter.getCourse();
    }

    @FXML
    public void showCourses(String point) {
        year = yearTextField.getText();
        semester = semesterTextField.getText();
        courseId = courseTextField.getText();

        if (point.equals("Year")) {
            int yearInt = Integer.parseInt(yearTextField.getText());
            if (yearInt > 0 && yearInt < 5) {
                for (int i = 0; i < courseList.size(); i++) {
                    int c = courseList.get(i).getYear();
                    if (c == Integer.parseInt(year)) {
                        courses.add(courseList.get(i));
                        System.out.println(courseList.get(i).getSubjectId());
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Input Dialog");
                alert.setHeaderText("Look, Year field");
                alert.setContentText("Enter year 1 to 4 !");

                alert.showAndWait();
            }

        } else if (point.equals("Semester")) {
            int semesterInt = Integer.parseInt(semesterTextField.getText());
            if (semesterInt > 0 && semesterInt < 3) {
                for (int i = 0; i < courseList.size(); i++) {
                    int c = courseList.get(i).getSemester();
                    if (c == Integer.parseInt(semester)) {
                        courses.add(courseList.get(i));
                        System.out.println(courseList.get(i).getSubjectId());
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Input Dialog");
                alert.setHeaderText("Look, Semester field");
                alert.setContentText("Enter semester 1 or 2");

                alert.showAndWait();
            }
        } else if (point.equals("Year&Semester")) {
            int yearInt = Integer.parseInt(yearTextField.getText());
            int semesterInt = Integer.parseInt(semesterTextField.getText());
            if (yearInt > 0 && yearInt < 5 && semesterInt > 0 && semesterInt < 3) {
                for (int i = 0; i < courseList.size(); i++) {
                    int c = courseList.get(i).getYear();
                    int d = courseList.get(i).getSemester();
                    if (c == Integer.parseInt(year) && d == Integer.parseInt(semester)) {
                        courses.add(courseList.get(i));
                        System.out.println(courseList.get(i).getSubjectId());
                    }
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning Input Dialog");
                alert.setHeaderText("Look, Year and Semester field");
                alert.setContentText("Enter year 1 to 4 or semester 1 or 2 !");

                alert.showAndWait();
            }
        } else if (point.equals("Id")) {
            System.out.println(courseId);
            for (int i = 0; i < courseList.size(); i++) {
                String c = courseList.get(i).getSubjectId();
                if (c.equals(courseId)) {
                    courses.add(courseList.get(i));
                    System.out.println(courseList.get(i).getSubjectId());
                } else if (courseId.length() != 8) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Input Dialog");
                    alert.setHeaderText("Look, Course ID field");
                    alert.setContentText("Enter correct course!");

                    alert.showAndWait();
                    break;
                }
            }
        }
        initData();
    }

    @FXML
    public void pressSearchButton() {
        courses.clear();
        System.out.println("Searching...");
        year = yearTextField.getText();
        semester = semesterTextField.getText();
        courseId = courseTextField.getText();
        if (semester.isEmpty() && courseId.isEmpty()) {
            System.out.println("Show courses by year.");
            showCourses("Year");
        } else if (year.isEmpty() && courseId.isEmpty()) {
            System.out.println("Show courses by semester.");
            showCourses("Semester");
        } else if (year.isEmpty() && semester.isEmpty()) {
            System.out.println("Show courses by courseId.");
            showCourses("Id");
        } else if (courseId.isEmpty()) {
            System.out.println("Show courses by year & semester.");
            showCourses("Year&Semester");
        }
        initData();
    }

    @FXML
    public void handlerAddCourseButton() {
        System.out.println("AddCourse");
    }

    @FXML
    public void handlerRemoveCourseButton() {
        System.out.println("RemoveCourse");
    }

    public void initData() {
        ObservableList<Course> data = FXCollections.observableList(courses);
        showCourseTableView.setItems(data);
    }

    public void initColumn() {
        System.out.println("InitColumn");
        courseIdColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("subjectId"));
        courseNameColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("subjectName"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("year"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<Course, Integer>("semester"));
        levelColumn.setCellValueFactory(new PropertyValueFactory<Course, String>("difficultyLevel"));
        levelColumn.setCellFactory(new Callback<TableColumn<Course, String>, TableCell<Course, String>>() {
            @Override
            public TableCell<Course, String> call(TableColumn<Course, String> param) {
                TableCell cell = new TableCell<Course, String>(){
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty ? null : getString());
                        setGraphic(null);
                        if(item != null){
                            setStyle("-fx-background-color: " + item);
                        }
                    }

                    private String getString() {
                        return getItem() == null ? "" : getItem().toString();
                    }
                };
                return cell;
            }
        });
    }
}

