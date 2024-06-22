package CLI;

import Main.Assignment;
import Main.Course;
import Main.Student;
import Main.Teacher;
import java.beans.XMLEncoder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class TeacherMenu {
    static void teachermenu(Teacher teacher) throws IOException, InterruptedException {

        boolean finish = false;
        while (!finish) {

            System.out.println("1-Add course");
            System.out.println("2-Remove course");
            System.out.println("3-Add student");
            System.out.println("4-Remove student");
            System.out.println("5-Add Assignment");
            System.out.println("6-Remove Assignment");
            System.out.println("7-Grading");
            System.out.println("8-DeadLine Edit");
            System.out.println("9-Active Assignment");
            Scanner scanner = new Scanner(System.in);

            boolean validInput = false;
            while (!validInput) {
                String input = scanner.nextLine();
                switch (input) {
                    case "1": {
                        FileOutputStream fos = new FileOutputStream("resources\\Teachers" + "\\" + teacher.getName() + ".xml");
                        XMLEncoder writer = new XMLEncoder(fos);
                        validInput = true;
                        System.out.println("Enter Course Name");
                        String name = scanner.nextLine();
                        System.out.println("Enter Course Credit");
                        String credit = scanner.nextLine();
                        Course course = new Course(name, Integer.parseInt(credit));
                        teacher.AddCourse(course);
                        writer.writeObject(teacher);
                        System.out.println("done!");
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        boolean V = false;
                        System.out.println("Finish? Y/N");
                        String finished = scanner.nextLine();
writer.close();
fos.close();
                        while (!V) {
                            if (Objects.equals(finished, "Y")) {
                                finish = true;
                                V = true;
                        System.out.println("done!");
                            } else if (Objects.equals(finished, "N")) {
                                finish = false;
                                V = true;
                            } else {
                                System.out.println("choose Y or N");
                            }
                        }
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        break;
                    }
                    case "2": {
                        FileOutputStream fos = new FileOutputStream("resources\\Teachers" + "\\" + teacher.getName() + ".xml");
                        XMLEncoder writer = new XMLEncoder(fos);
                        try {
                            validInput = true;
                            System.out.println("Enter Course Name");
                            String name2 = scanner.nextLine();
                            System.out.println("Enter Course Credit");
                            String credit2 = scanner.nextLine();
                            teacher.RemoveCourse(new Course(name2, Integer.parseInt(credit2)));
                            System.out.println("done!");
                            Thread.sleep(1000);
                            System.out.println("\033[H\033[2J");
                            System.out.flush();

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Course not Found");
                        } finally {
                            writer.writeObject(teacher);
                            writer.close();
                            fos.close();

                        }
                        boolean V = false;
                        System.out.println("Finish? Y/N");
                        String finished = scanner.nextLine();

                        while (!V) {
                            if (Objects.equals(finished, "Y")) {
                                finish = true;
                                V = true;
                                System.out.println("done!");
                            } else if (Objects.equals(finished, "N")) {
                                finish = false;
                                V = true;
                            } else {
                                System.out.println("choose Y or N");
                            }
                        }
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        break;
                    }
                    case "3": {
                        FileOutputStream fos = new FileOutputStream("resources\\Teachers" + "\\" + teacher.getName() + ".xml");
                        XMLEncoder writer = new XMLEncoder(fos);
                        try {
                            validInput = true;
                            System.out.println("First type Course name Then press Enter\n" +
                                    "Then type number of new students you wanna to add and press Enter\n" +
                                    "At the end Enter students info in this format : name-StudentID\n");
                            String courseName = scanner.nextLine();
                            String number = scanner.nextLine();
                            int number2 = Integer.parseInt(number);
                            for (int i = 0; i < number2; i++) {
                                String[] detail = scanner.nextLine().split("-");
                                Course course=new Course(courseName);
                                teacher.AddStudent(course, new Student(detail[0], detail[1]));
                            }
                            System.out.println("done!");
                            Thread.sleep(1000);
                            System.out.println("\033[H\033[2J");
                            System.out.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Course not found");
                        }
                        finally {
                            writer.writeObject(teacher);
                            writer.close();
                            fos.close();

                        }
                    boolean V = false;
                    System.out.println("Finish? Y/N");
                    String finished = scanner.nextLine();

                    while (!V) {
                        if (Objects.equals(finished, "Y")) {
                            finish = true;
                            V = true;
                    System.out.println("done!");
                        } else if (Objects.equals(finished, "N")) {
                            finish = false;
                            V = true;
                        } else {
                            System.out.println("choose Y or N");
                        }
                    }
                    Thread.sleep(1000);
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    break;
                    }


                    case "4":
                    {
                        FileOutputStream fos = new FileOutputStream("resources\\Teachers" + "\\" + teacher.getName() + ".xml");
                        XMLEncoder writer = new XMLEncoder(fos);
                        try {


                            validInput = true;
                            System.out.println("Enter Course Name");
                            String courseName2 = scanner.nextLine();
                            System.out.println("Enter Student name");
                            String name3 = scanner.nextLine();
                            System.out.println("Enter Student ID");
                            String ID = scanner.nextLine();
                            teacher.RemoveStudent(new Course(courseName2), new Student(name3, ID));
                            System.out.println("done!");
                            Thread.sleep(1000);
                            System.out.println("\033[H\033[2J");
                            System.out.flush();
                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.println("Student or course not found");
                        }
                        finally {
                            writer.writeObject(teacher);
                            writer.close();
                            fos.close();

                        }
                        boolean V = false;
                        System.out.println("Finish? Y/N");
                        String finished =scanner.nextLine();

                        while (!V) {
                            if (Objects.equals(finished, "Y")) {
                                finish=true;
                                V=true;
                        System.out.println("done!");
                            } else if (Objects.equals(finished, "N")) {
                                finish =false;
                                V=true;
                            } else {
                                System.out.println("choose Y or N");
                            }
                        }
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        break;
                    }
                    case "5":

                    {
                        FileOutputStream fos = new FileOutputStream("resources\\Teachers" + "\\" + teacher.getName() + ".xml");
                        XMLEncoder writer = new XMLEncoder(fos);
                        try {
                            validInput = true;
                            System.out.println("Enter Course Name");
                            String courseName = scanner.nextLine();
                            System.out.println("Enter Assignment Subject");
                            String subject = scanner.nextLine();
                            System.out.println("Enter Deadline (YYYY/MM/DD)");
                            String stringdate = scanner.nextLine();
                            System.out.println("Do you want to Active it ? Y/N");
                            String YN = scanner.nextLine();
                            boolean valid = false;
                            while (!valid) {
                                if (Objects.equals(YN, "Y")) {
                                    teacher.AddAssignment(new Course(courseName), new Assignment(subject, new Date(stringdate), true));
                                    valid = true;
                                } else if (Objects.equals(YN, "N")) {
                                    teacher.AddAssignment(new Course(courseName), new Assignment(subject, new Date(stringdate), false));
                                    valid = true;
                                } else {
                                    System.out.println("choose Y or N");
                                }
                            }
                            System.out.println("done!");
                            Thread.sleep(1000);
                            System.out.println("\033[H\033[2J");
                            System.out.flush();
                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.println("course not found");
                        }
                        finally {
                            writer.writeObject(teacher);
                            writer.close();
                            fos.close();

                        }
                        boolean V = false;
                        System.out.println("Finish? Y/N");
                        String finished =scanner.nextLine();

                        while (!V) {
                            if (Objects.equals(finished, "Y")) {
                                finish=true;
                                V=true;
                        System.out.println("done!");
                            } else if (Objects.equals(finished, "N")) {
                                finish =false;
                                V=true;
                            } else {
                                System.out.println("choose Y or N");
                            }
                        }
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        break;
                    }
                    case "6": {
                        FileOutputStream fos = new FileOutputStream("resources\\Teachers" + "\\" + teacher.getName() + ".xml");
                        XMLEncoder writer = new XMLEncoder(fos);
                        try {


                        validInput = true;
                        System.out.println("Enter your Course name");
                        String name5 = scanner.nextLine();
                        System.out.println("Enter your Assignment subject");
                        String name6 = scanner.nextLine();
                        teacher.RemoveAssignment(new Course(name5), new Assignment(name6));
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            System.out.println("Assignment or course not found");
                        }
                        finally {
                        writer.writeObject(teacher);
                            writer.close();
                            fos.close();

                        }
                        System.out.println("done!");
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        boolean V = false;
                        System.out.println("Finish? Y/N");
                        String finished =scanner.nextLine();

                        while (!V) {
                            if (Objects.equals(finished, "Y")) {
                                finish=true;
                                V=true;
                        System.out.println("done!");
                            } else if (Objects.equals(finished, "N")) {
                                finish =false;
                                V=true;
                            } else {
                                System.out.println("choose Y or N");
                            }
                        }
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        break;
                    }
                    case "7": {
                        FileOutputStream fos = new FileOutputStream("resources\\Teachers" + "\\" + teacher.getName() + ".xml");
                        XMLEncoder writer = new XMLEncoder(fos);
                        try {


                        validInput = true;
                        System.out.println("Enter course name");
                        String name=scanner.nextLine();
                        System.out.println("Enter Number of student you want to grading (should be less than "+teacher.getCourses().get(teacher.getCourses().indexOf(new Course(name))).getStudents().size());
                        System.out.println("First Enter Name and ID then Score(name-ID-Score) ");
                        for (int i = 0; i < teacher.getCourses().get(teacher.getCourses().indexOf(new Course(name))).getStudents().size(); i++) {
                            String[] detail=scanner.nextLine().split("-");
                            teacher.Score(new Course(name),new Student(detail[0],detail[1]),Double.parseDouble(detail[2]));
                        }
                            System.out.println("done!");
                            Thread.sleep(1000);
                            System.out.println("\033[H\033[2J");
                            System.out.flush();
                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.println("Course not found or Student or you grading more than students size");
                        }
                        finally {
                        writer.writeObject(teacher);
                            writer.close();
                            fos.close();

                        }

                        boolean V = false;
                        System.out.println("Finish? Y/N");
                        String finished =scanner.nextLine();

                        while (!V) {
                            if (Objects.equals(finished, "Y")) {
                                finish = true;
                                V = true;
                        System.out.println("done!");
                            } else if (Objects.equals(finished, "N")) {
                                finish = false;
                                V = true;
                            } else {
                                System.out.println("choose Y or N");
                            }
                        }
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        break;
                    }
                    case "8": {
                        FileOutputStream fos = new FileOutputStream("resources\\Teachers" + "\\" + teacher.getName() + ".xml");
                        XMLEncoder writer = new XMLEncoder(fos);
                        try {
                            validInput = true;
                            System.out.println("Enter your Course name");
                            String name5 = scanner.nextLine();
                            System.out.println("Enter your Assignment subject");
                            String name6 = scanner.nextLine();
                            System.out.println("Enter NewDeadline (YYYY/MM/DD)");
                            String stringdate = scanner.nextLine();
                            teacher.DeadLineTimeEdit(new Course(name5), new Assignment(name6), new Date(stringdate));
                            System.out.println("done!");
                            Thread.sleep(1000);
                            System.out.println("\033[H\033[2J");
                            System.out.flush();
                        }catch (Exception e){
                            e.printStackTrace();
                            System.out.println("Course or Assigment not found");
                        }
                        finally {
                            writer.writeObject(teacher);
                            writer.close();
                            fos.close();

                        }
                        boolean V = false;
                        System.out.println("Finish? Y/N");
                        String finished =scanner.nextLine();

                        while (!V) {
                            if (Objects.equals(finished, "Y")) {
                                finish = true;
                                V = true;
                        System.out.println("done!");
                            } else if (Objects.equals(finished, "N")) {
                                finish = false;
                                V = true;
                            } else {
                                System.out.println("choose Y or N");
                            }
                        }
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        break;
                    }
                    case "9": {
                        FileOutputStream fos = new FileOutputStream("resources\\Teachers" + "\\" + teacher.getName() + ".xml");
                        XMLEncoder writer = new XMLEncoder(fos);
                        try {
                        validInput = true;
                        System.out.println("Enter your Course name");
                        String name5 = scanner.nextLine();
                        System.out.println("Enter your Assignment subject");
                        String name6 = scanner.nextLine();
                        teacher.AssignmentActivator(new Course(name5), new Assignment(name6));
                            System.out.println("done!");
                            Thread.sleep(1000);
                            System.out.println("\033[H\033[2J");
                            System.out.flush();

                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Course or Assigment not found");
                    }finally {
                        writer.writeObject(teacher);
                            writer.close();
                            fos.close();

                        }

                        boolean V = false;
                        System.out.println("Finish? Y/N");
                        String finished =scanner.nextLine();

                        while (!V) {
                            if (Objects.equals(finished, "Y")) {
                                finish=true;
                                V=true;
                        System.out.println("done!");
                            } else if (Objects.equals(finished, "N")) {
                                V=true;
                            } else {
                                System.out.println("choose Y or N");
                            }
                        }
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                        break;
                    }
                    default:
                        System.out.println("Choose valid Number");
                        System.out.println("done!");
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
                }
            }
        }
    }
}

