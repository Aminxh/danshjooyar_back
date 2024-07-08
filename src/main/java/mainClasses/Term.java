package mainClasses;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@XmlRootElement
public class Term implements Serializable {

    private int totalThisTermCredit = 0;
    private double avg ;
    private int termNumber;
    private ArrayList<StudentCourse> studentCourses = new ArrayList<>();

    public Term() {
    }

    @XmlElement
    public int getTermNumber() {
        return termNumber;
    }

    /**
     * Constructs a term with the given term number.
     *
     * @param termNumber The term number (e.g., 1, 2, 3).
     */
    public Term(int termNumber) {
        this.termNumber = termNumber;
    }

    /**
     * Calculates the average score for this term.
     *
     * @return The average score.
     */
    public double avgCalculate() {
        double sum = 0;
        totalThisTermCredit = 0;  // Resetting to recalculate
        for (StudentCourse studentCourse : studentCourses) {
            sum += studentCourse.getScore() * studentCourse.getCredit();
        }
        for (StudentCourse studentCourse : studentCourses) {
            totalThisTermCredit += studentCourse.getCredit();
        }

        return totalThisTermCredit == 0 ? 0 : sum / totalThisTermCredit;
    }

    /**
     * Gets the total credits for this term.
     *
     * @return The total credits.
     */
    @XmlElement
    public int getTotalThisTermCredit() {
        totalThisTermCredit = 0;
        for (StudentCourse studentCourse : studentCourses) {
            totalThisTermCredit += studentCourse.getCredit();
        }
        return totalThisTermCredit;
    }

    @XmlElement
    public double getAvg() {
        return avgCalculate();
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    @XmlElement
    public ArrayList<StudentCourse> getStudentCourses() {
        return studentCourses;
    }

    public void setStudentCourses(ArrayList<StudentCourse> studentCourses) {
        this.studentCourses = studentCourses;
    }

    public void setTermNumber(int termNumber) {
        this.termNumber = termNumber;
    }

    public void setTotalThisTermCredit(int totalThisTermCredit) {
        this.totalThisTermCredit = totalThisTermCredit;
    }

    @Override
    public String toString() {
        return "Term{" +
                "totalThisTermCredit=" + totalThisTermCredit +
                ", avg=" + avg +
                ", termNumber=" + termNumber +
                ", studentCourses=" + studentCourses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Term term = (Term) o;
        return termNumber == term.termNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(termNumber);
    }
}
