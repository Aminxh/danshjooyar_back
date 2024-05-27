package Main;

import exceptions.StudentNotFoundException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

/**
 * Represents a course in an academic program.
 * Each course has a name, credit value, associated students, and assignments.
 */
public class Course {
    private BeheshtiUniversityField beheshtiUniversityField;
    private String name;
    private int credit;
    private String teacherName;


    public Course(String name, int credit) {
        this.name = name;
        this.credit = credit;
    }


    private ArrayList<StudentCourse> studentCourseVersion = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<Assignment> AllAssignments = new ArrayList<>();
    private ArrayList<Assignment> ActiveAssignments = new ArrayList<>();

    public Course(String name) {
        this.name = name;
    }

    public Course() {
    }

    /**
     * Adds a student to this course.
     * Associates the student with the course and updates the student's term information.
     *
     * @param student The student to be added.
     */
    public void AddStudent(Student student) {
        students.add(student);
        studentCourseVersion.add(new StudentCourse(name, credit));
        student.getTerms().getLast().getStudentCourses().add(studentCourseVersion.getLast());
    }

    /**
     * Removes a student from this course.
     * Disassociates the student from the course and updates the student's term information.
     *
     * @param student The student to be removed.
     */
    public void RemoveStudent(Student student) {
        if (student == null) {
            throw new StudentNotFoundException();
        }
        try {


            student.getTerms().getLast().getStudentCourses().remove(studentCourseVersion.get(students.indexOf(student)));
            students.remove(student);
        }
        catch (Exception exception){
            throw new StudentNotFoundException();
        }
    }

    /**
     * Retrieves the top-performing student in this course.
     * The top student is determined based on their scores in the course.
     *
     * @return The student with the highest score.
     */
    public Student GetTopStudent() {
        Student[] s = new Student[students.size()];
        if(students.isEmpty()){
            throw new StudentNotFoundException();
        }
        for (int i = 0; i < students.size(); i++) {
            s[i] = students.get(i);
        }
        sort(s);

        return s[students.size() - 1];
    }

    private void sort(Student[] students) {
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - i - 1; j++) {
                if (students[j].getTerms().getLast().getStudentCourses().get(students[j].getTerms().getLast().getStudentCourses().indexOf(studentCourseVersion.get(this.students.indexOf(students[j])))).getScore() > students[j + 1].getTerms().getLast().getStudentCourses().get(students[j + 1].getTerms().getLast().getStudentCourses().indexOf(studentCourseVersion.get(this.students.indexOf(students[j + 1])))).getScore()) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }

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

    public ArrayList<Assignment> getActiveAssignments() {
        return ActiveAssignments;
    }

    public void setActiveAssignments(ArrayList<Assignment> activeAssignments) {
        ActiveAssignments = activeAssignments;
    }

    public ArrayList<Assignment> getAllAssignments() {
        return AllAssignments;
    }

    public void setAllAssignments(ArrayList<Assignment> allAssignments) {
        AllAssignments = allAssignments;
    }

    public BeheshtiUniversityField getBeheshtiUniversityField() {
        return beheshtiUniversityField;
    }

    public void setBeheshtiUniversityField(BeheshtiUniversityField beheshtiUniversityField) {
        this.beheshtiUniversityField = beheshtiUniversityField;
    }

    public ArrayList<StudentCourse> getStudentCourseVersion() {
        return studentCourseVersion;
    }

    public void setStudentCourseVersion(ArrayList<StudentCourse> studentCourseVersion) {
        this.studentCourseVersion = studentCourseVersion;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

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
}
