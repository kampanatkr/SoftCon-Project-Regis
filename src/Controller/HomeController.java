package Controller;

import DBConnecter.DBConnecter;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class HomeController {

    @FXML protected TextArea showCourseTextArea;
    @FXML protected Label titleHomeLabel;
    @FXML protected Label commandLabel;
    @FXML protected TextField courseTextField;
    @FXML protected TextField semesterTextField;
    @FXML protected TextField yearTextField;
    @FXML protected Button searchCourseButton;

    public void initialize(){
        DBConnecter dbConnecter = new DBConnecter();
        dbConnecter.connect();
        dbConnecter.getCourse();
        showCourses();
    }

    @FXML
    public void showCourses(){
        showCourseTextArea.setText("Hello Regis");
    }

    @FXML
    public void pressSearchButton(){
        showCourseTextArea.setText("Searching ...");
    }
}
