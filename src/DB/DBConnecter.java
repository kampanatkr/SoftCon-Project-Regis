package DB;

import Model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnecter {
    private String url = "jdbc:sqlite:course.db";
    private Connection connection;

    public Connection connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                System.out.println("Connect !");
            } else {
                System.out.println("Null URL !");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public List<Course> getCourse() {
        System.out.println("req course");
        List<Course> courses = new ArrayList<>();
        try {
            if (connection != null) {
                String sql = "select * from courseTable";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String courseID = "0"+resultSet.getString("courseID");
                    String courseName = resultSet.getString("courseName");
                    int year = resultSet.getInt("year");
                    int semester = resultSet.getInt("semester");
                    int lv = resultSet.getInt("level");
                    String level;
                    if (lv == 1) {
                        level = "Green";
                    } else if (lv == 2) {
                        level = "Blue";
                    } else {
                        level = "Red";
                    }

                    Course course = new Course(courseName, courseID, year, semester, level);
                    courses.add(course);
                    System.out.println("add course " + courseID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return courses;
    }
}
