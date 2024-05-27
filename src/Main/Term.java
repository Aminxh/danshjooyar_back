package Main;

import java.util.ArrayList;

public class Term {


    private int totalThisTermCredit =0;
    private double avg=0;
    private int termNumber;
     private ArrayList<StudentCourse> studentCourses =new ArrayList<StudentCourse>();
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
    public double avgCalculate(){
        double sum=0;
        for (int i = 0; i <studentCourses.size() ; i++) {
            sum+= studentCourses.get(i).getScore()*studentCourses.get(i).getCredit();
        }
        for (int i = 0; i <studentCourses.size() ; i++) {
            totalThisTermCredit +=studentCourses.get(i).getCredit();
        }

        return sum/ totalThisTermCredit;
    }
    /**
     * Gets the total credits for this term.
     *
     * @return The total credits.
     */
    public int getTotalThisTermCredit() {
        for (int i = 0; i <studentCourses.size() ; i++) {
            totalThisTermCredit +=studentCourses.get(i).getCredit();
        }
        return totalThisTermCredit;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

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
}

