package mainClasses;

import exceptions.StudentNotFoundException;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@XmlRootElement
public class Course implements Serializable {
    private BeheshtiUniversityField beheshtiUniversityField;
    private String name;
    private int credit;
    private String teacherName;

    public Course(String name, int credit) {
        this.name = name;
        this.credit = credit;
    }

    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Assignment> AllAssignments = new ArrayList<>();
    private ArrayList<Assignment> ActiveAssignments = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public Course() {
    }

    public void AddStudent(Student student) {
        students.add(student);
        student.getTerms().get(student.getTerms().size() - 1).getStudentCourses().add(new StudentCourse(name, credit));
    }

    public void RemoveStudent(Student student) {
        if (student == null) {
            throw new StudentNotFoundException();
        }
        try {
            student.getTerms().get(student.getTerms().size() - 1).getStudentCourses().remove(new StudentCourse(name, credit));
            students.remove(student);
        } catch (Exception exception) {
            throw new StudentNotFoundException();
        }
    }

    @XmlElement
    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElementWrapper(name = "activeAssignments")
    @XmlElement(name = "assignment")
    public ArrayList<Assignment> getActiveAssignments() {
        return ActiveAssignments;
    }

    public void setActiveAssignments(ArrayList<Assignment> activeAssignments) {
        ActiveAssignments = activeAssignments;
    }

    @XmlElementWrapper(name = "allAssignments")
    @XmlElement(name = "assignment")
    public ArrayList<Assignment> getAllAssignments() {
        return AllAssignments;
    }

    public void setAllAssignments(ArrayList<Assignment> allAssignments) {
        AllAssignments = allAssignments;
    }

    @XmlElement
    public BeheshtiUniversityField getBeheshtiUniversityField() {
        return beheshtiUniversityField;
    }

    public void setBeheshtiUniversityField(BeheshtiUniversityField beheshtiUniversityField) {
        this.beheshtiUniversityField = beheshtiUniversityField;
    }

    @XmlElementWrapper(name = "students")
    @XmlElement(name = "student")
    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    @XmlElement
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(name, course.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", credit=" + credit +
                ", teacherName='" + teacherName + '\'' +
                ", students=" + students +
                '}';
    }
}
