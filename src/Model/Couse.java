package Model;

public class Couse {
    private String subjectName;
    private String subjectId;
    private String difficultyLevel;

    public Couse(String subjectName, String subjectId, String level) {
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
