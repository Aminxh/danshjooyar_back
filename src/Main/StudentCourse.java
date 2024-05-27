package Main;

/**
 * Represents a student's performance in a specific course.
 * Each student course has a name, credit value, and score.
 * * Associates the student with the course
 */
public class StudentCourse {
    private String name;
    private Double score=0.0;


    private int credit; // The credit value of the course

    /**
     * Constructs a student course with the given name and credit value.
     *
     * @param name   The name of the course.
     * @param credit The credit value of the course.
     */
    public StudentCourse(String name, int credit) {
        this.name = name;
        this.credit = credit;
    }

    public StudentCourse() {
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getScore() {
        return score;
    }


    public void setScore(double score) {
        this.score=score;
    }
}
