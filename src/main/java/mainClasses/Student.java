package mainClasses;

import exceptions.CourseNotFoundException;
import exceptions.TermNotFoundException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@XmlRootElement
public class Student implements Serializable {

    private String name;
    private String PASSWORD;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd ");
    private String FatherName;
    private Date BirthDate;
    private String NationalId;
    private String StudentId;
    private BeheshtiUniversityField field;
    private String phoneNumber;
    private int currentTerm = 0;
    private ArrayList<Term> terms = new ArrayList<>();
    private double totalAverage = 0;
    private double currentAverage = 0;
    private int totalPassedCredit = 0;


    {
        terms.add(new Term(1));
    }

    public Student(String name, String studentId) {
        this.name = name;
        this.StudentId = studentId;
        this.PASSWORD = studentId;
    }

    public Student() {
    }

    @XmlElement
    public double getCurrentAverage() {
        return currentAverage;
    }

    public void setCurrentAverage(double currentAverage) {
        this.currentAverage = currentAverage;
    }

    @XmlElement
    public int getCurrentTerm() {
        return currentTerm;
    }

    public void setCurrentTerm(int currentTerm) {
        this.currentTerm = currentTerm;
    }

    @XmlTransient
    public DateFormat getDateFormat() {
        return dateFormat;
    }

    @XmlElement()
    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }

    @XmlElement
    public double getTotalAverage() {
        return totalAverage;
    }

    public void setTotalAverage(double totalAverage) {
        this.totalAverage = totalAverage;
    }

    @XmlElement
    public int getTotalPassedCredit() {
        int totalCredits = 0;
        for (Term term : terms) {
            int termCredits = term.getTotalThisTermCredit();
            totalCredits += termCredits;
        }
        return totalCredits;
    }

    public void setTotalPassedCredit(int totalPassedCredit) {
        this.totalPassedCredit = totalPassedCredit;
    }

    public void addCourse(Course course) {
        if (course == null) {
            throw new CourseNotFoundException();
        }
        course.AddStudent(this);
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    @XmlElement
    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    @XmlElement
    public String getNationalId() {
        return NationalId;
    }

    public void setNationalId(String nationalId) {
        NationalId = nationalId;
    }

    @XmlElement
    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    @XmlElement
    public BeheshtiUniversityField getField() {
        return field;
    }

    public void setField(BeheshtiUniversityField field) {
        this.field = field;
    }

    public ArrayList<StudentCourse> termCourseDetail(int term) {
        if (term > currentTerm) {
            throw new TermNotFoundException();
        }
        return terms.get(term - 1).getStudentCourses();
    }

    public ArrayList<StudentCourse> termCourseDetail() {
        return terms.get(currentTerm - 1).getStudentCourses();
    }

    public int termCreditDetail(int term) {
        if (terms.size() < term) {
            throw new TermNotFoundException();
        }
        return terms.get(term - 1).getTotalThisTermCredit();
    }

    public int termCreditDetail() {
        return terms.get(currentTerm - 1).getTotalThisTermCredit();
    }


public double termAvgDetail(int term) {
    if (terms.size() < term) {
        throw new TermNotFoundException();
    }
    Term specifiedTerm = terms.get(term - 1);
    double avg = specifiedTerm.avgCalculate();
    return avg;
}

    public double termAvgDetail() {
        return terms.get(currentTerm - 1).avgCalculate();
    }

    public void newTerm() {
        currentTerm++;
        terms.add(new Term(currentTerm));
    }

public double totalAvg() {
    double sum = 0;
    int totalCredits = getTotalPassedCredit();

    System.out.println("Debug: Calculating total average");
    for (Term term : terms) {
        for (StudentCourse course : term.getStudentCourses()) {
            sum += course.getScore() * course.getCredit();
        }
    }

    if (totalCredits == 0) {
        return 0;
    }

    totalAverage = sum / totalCredits;
    return totalAverage;
}

    public void AllCoursePrinter() {
        for (Term term : terms) {
            System.out.println(term.getStudentCourses());
        }
    }

    public void TotalPassedCreditPrinter() {
        System.out.println(getTotalPassedCredit());
    }

    public void totalAvgPrinter() {
        System.out.println(totalAvg());
    }

    public void CurrentTermAvgPrinter() {
        System.out.println(termAvgDetail());
    }

    @XmlElement
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @XmlElement
    public String getPASSWORD() {
        return PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student student = (Student) o;
        return Objects.equals(StudentId, student.StudentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(StudentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
