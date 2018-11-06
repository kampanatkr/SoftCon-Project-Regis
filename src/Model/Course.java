package Model;

public class Course {
    private String subjectName;
    private String subjectId;
    private String difficultyLevel;

    public Course(String subjectName, String subjectId, String level) {
        this.subjectName = subjectName;
        this.subjectId = subjectId;
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

    public boolean isPassed(){
        return true;
    }

}
