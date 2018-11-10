package Model;

public class Course {
    private String subjectName;
    private String subjectId;
    private int year;
    private int semester;
    private String difficultyLevel;

    public Course(String subjectName, String subjectId, int year, int semester, String level) {
        this.subjectName = subjectName;
        this.subjectId = subjectId;
        this.year = year;
        this.semester = semester;
        this.difficultyLevel = level;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public boolean isPassed(){
        return true;
    }

    @Override
    public String toString() {
        return  "Subject Id='" + subjectId + '\'' +
                " Subject Name='" + subjectName + '\'' +
                " year=" + year +
                " semester=" + semester +
                " difficultyLevel='" + difficultyLevel + '\'' +
                "\n";
    }
}
