package Server;

import mainClasses.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {

        try {
            ServerSocket inputDataUserInfo = new ServerSocket(7777);
            while (true) {
                Socket accepter = inputDataUserInfo.accept();
                System.out.println("connected");

                RequestHandler requestHandler = new RequestHandler(accepter);
                requestHandler.start();
            }

        } catch (IOException e) {
        }
    }
}

class RequestHandler extends Thread {
    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;

    public RequestHandler(Socket socket) throws IOException {
        this.socket = socket;
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
    }

    String listener() {
        StringBuilder num = new StringBuilder();
        StringBuilder listen = new StringBuilder();
        char i;
        try {
            int index = dis.read();
            while (index != 0) {
                listen.append((char) index);
                index = dis.read();
            }
        } catch (IOException e) {
            try {
                dis.close();
                dos.close();
                socket.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            e.printStackTrace();
        }
        return listen.toString();
    }

    void writer(String write) {
        if (write != null && !write.isEmpty()) {
            try {
                dos.writeBytes(write);
                System.out.println("write: " + write);
            } catch (IOException e) {
                try {
                    dis.close();
                    dos.close();
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                e.printStackTrace();
            }
            return;
        }
        System.out.println("invalid write");
    }

    @Override
    public void run() {
        System.out.println("ready");
        String command = listener();
        System.out.println("command is: " + command);
        String[] splited = command.split("~");

        switch (splited[0]) {

            case "LOGIN": //username~password
                try {
                    Loginpage(splited[1], splited[2]);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
                break;
//-------------------------------------------------------------------------------
            case "SIGNUP": //username~studentID~password
                try {
                    signuppage(splited[1], splited[2], splited[3]);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
                break;
//-------------------------------------------------------------------------------
            case "EDITACCOUNT": //username~birthday~fatherName~nationalID~phone~fieldOfStudy
                try {
                    editAccount(splited[1],splited[2],splited[3],splited[4],splited[5],splited[6]);
                } catch (Exception e)
                {
                    throw new RuntimeException(e) ;
                }
                break;
//-------------------------------------------------------------------------------
            case "ASSIGNMENTS":
                try {
                    assignmentPage(splited[1]);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
                break;
//-------------------------------------------------------------------------------
            case "SHOWDETAIL":
                try {
                    showdetail(splited[1]);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
                break;
//-------------------------------------------------------------------------------
            case "CHANGEPASSWORD":
                try {
                    changePassword(splited[1], splited[2]);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
                break;
//-------------------------------------------------------------------------------
            case "CURRENTPASSWORD": //username~password
                try {
                    currentPasswordChecker(splited[1], splited[2]);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
                break;
//-------------------------------------------------------------------------------
            case "ADDCLASS":
                try {
                    addclass(splited[1], splited[2], splited[3]);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
                break;
//-------------------------------------------------------------------------------
            case "SHOWCLASS":
                try {
                    showClass(splited[1]);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
                break;
//-------------------------------------------------------------------------------
            case "SHOWTASKS":
                showtasks(splited[1]);
                break;

            case "ADDTASK":
                try {
                    addtask(splited[1],splited[2],splited[3],splited[4] );
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

            case "DELETETASK":
                try {
                    deletetask(splited[1],splited[2]);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
//-------------------------------------------------------------------------------
            case "DELETEACCOUNT":
                try {
                    deleteAccount(splited[1]);
                } catch (JAXBException e)
                {
                    throw new RuntimeException(e) ;
                }
                break;
//-------------------------------------------------------------------------------
            case "PROFILE":
                try {
                    initProfileData(splited[1]);
                } catch (JAXBException e)
                {
                    throw new RuntimeException(e) ;
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
    }

    private void showtasks(String username) {
        File file = new File("src/main/resources/Tasks/" + username + ".txt");
        StringBuilder tasksOutput = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String taskLine = scanner.nextLine();
                tasksOutput.append(taskLine).append("\n");
            }
        } catch (FileNotFoundException e) {
        }
        writer(tasksOutput.toString());
    }
    //****************************************************************
    private void deletetask(String username,String name) throws IOException {
        File file = new File("src/main/resources/Tasks/" + username + ".txt");
        Scanner scanner =new Scanner(file);
        StringBuilder stringBuilder=new StringBuilder();
        while (scanner.hasNextLine()){
            String task=scanner.nextLine();
            if(!task.split("~")[0].equals(name)){
                stringBuilder.append(task+"\n");
            }
        }

        PrintWriter out = new PrintWriter(new FileWriter(file,false));
        out.print(stringBuilder);
        out.close();
        scanner.close();
        showtasks(username);
    }
    //****************************************************************
    private void addtask(String username, String name, String dateTime, String description) throws IOException {
        File file = new File("src/main/resources/Tasks/" + username + ".txt");
        PrintWriter out = new PrintWriter(new FileWriter(file, true));
        out.println(name+"~"+dateTime+"~"+description);
        out.close();
        showtasks(username);
    }
    //****************************************************************

    private void showdetail(String username) throws JAXBException {
        List<Double> result = new ArrayList<>();

        File studentsFolder = new File("src/main/resources/Students");
        File[] studentFiles = studentsFolder.listFiles();
        if (studentFiles != null) {
            JAXBContext studentContext = JAXBContext.newInstance(Student.class);
            Unmarshaller studentUnmarshaller = studentContext.createUnmarshaller();

            for (File studentFile : studentFiles) {
                Student student = (Student) studentUnmarshaller.unmarshal(studentFile);
                if (student.getName().equals(username)) {
                    long courseCount = student.getTerms().stream()
                            .flatMap(term -> term.getStudentCourses().stream())
                            .count();
                    result.add((double) courseCount);

                    if (courseCount > 0) {
                        OptionalDouble maxScore = student.getTerms().stream()
                                .flatMap(term -> term.getStudentCourses().stream())
                                .mapToDouble(StudentCourse::getScore)
                                .max();

                        OptionalDouble minScore = student.getTerms().stream()
                                .flatMap(term -> term.getStudentCourses().stream())
                                .mapToDouble(StudentCourse::getScore)
                                .min();

                        result.add(maxScore.orElse(0.0));
                        result.add(minScore.orElse(0.0));
                    } else {
                        result.add(0.0);
                        result.add(0.0);
                    }
                    break;
                }
            }
        }

        JAXBContext teacherContext = JAXBContext.newInstance(Teacher.class);
        Unmarshaller teacherUnmarshaller = teacherContext.createUnmarshaller();
        File teacherFolder = new File("src/main/resources/Teachers");
        File[] teacherFiles = teacherFolder.listFiles();
        if (teacherFiles != null) {
            int totalAssignments = 0;
            int totalActiveAssignments = 0;

            for (File teacherFile : teacherFiles) {
                Teacher teacher = (Teacher) teacherUnmarshaller.unmarshal(teacherFile);
                for (Course course : teacher.getCourses()) {
                    boolean studentFound = course.getStudents().stream()
                            .anyMatch(student -> student.getName().equals(username));
                    if (studentFound) {
                        totalAssignments += course.getAllAssignments().size();
                        totalActiveAssignments += course.getActiveAssignments().size();
                    }
                }
            }
            result.add((double) totalAssignments);
            result.add((double) totalActiveAssignments);
        } else {
            result.add(0.0);
            result.add(0.0);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            stringBuilder.append(result.get(i));
            if (i < result.size() - 1) {
                stringBuilder.append("~");
            }
        }

        System.out.println(stringBuilder.toString());
        writer(stringBuilder.toString());
    }


    //****************************************************************
    private void addclass(String username, String teacher, String course) throws JAXBException {
        File studentsFolder = new File("src/main/resources/Students");
        File[] studentFiles = studentsFolder.listFiles();

        for (File studentFile : studentFiles) {
            JAXBContext studentContext = JAXBContext.newInstance(Student.class);
            Unmarshaller studentUnmarshaller = studentContext.createUnmarshaller();
            Student student = (Student) studentUnmarshaller.unmarshal(studentFile);

            if (student.getName().equals(username)) {
                JAXBContext teacherContext = JAXBContext.newInstance(Teacher.class);
                Unmarshaller teacherUnmarshaller = teacherContext.createUnmarshaller();
                File teacherFile = new File("src/main/resources/Teachers/" + teacher + ".xml");
                Teacher teacherObj = (Teacher) teacherUnmarshaller.unmarshal(teacherFile);

                boolean courseExistsInStudent = student.getTerms().stream()
                        .flatMap(term -> term.getStudentCourses().stream())
                        .anyMatch(studentCourse -> studentCourse.getName().equals(course));

                if (!courseExistsInStudent) {
                    Optional<Course> existingCourse = teacherObj.getCourses().stream()
                            .filter(c -> c.getName().equals(course))
                            .findFirst();

                    if (existingCourse.isPresent()) {
                        existingCourse.get().AddStudent(student);

                        student.getTerms().get(student.getTerms().size() - 1)
                                .getStudentCourses().add(new StudentCourse(course, existingCourse.get().getCredit()));

                        JAXBContext studentMarshallerContext = JAXBContext.newInstance(Student.class);
                        Marshaller studentMarshaller = studentMarshallerContext.createMarshaller();
                        studentMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                        studentMarshaller.marshal(student, studentFile);

                        JAXBContext teacherMarshallerContext = JAXBContext.newInstance(Teacher.class);
                        Marshaller teacherMarshaller = teacherMarshallerContext.createMarshaller();
                        teacherMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
                        teacherMarshaller.marshal(teacherObj, teacherFile);
                    } else {
                    }
                } else {
                }
            }
        }

        writer("");
    }
    //****************************************************************
    private void showClass(String username) throws JAXBException {
        Set<Course> result = new HashSet<>();

        JAXBContext context = JAXBContext.newInstance(Teacher.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File teacherFolder = new File("src/main/resources/Teachers");
        File[] list_of_teacher = teacherFolder.listFiles();
        for (int i = 0; i < list_of_teacher.length; i++) {
            Teacher checker3 = (Teacher) unmarshaller.unmarshal(list_of_teacher[i]);
            for (int j = 0; j < checker3.getCourses().size(); j++) {
                for (int k = 0; k < checker3.getCourses().get(j).getStudents().size(); k++) {
                    if (checker3.getCourses().get(j).getStudents().get(k).getName().equals(username)) {
                        result.add(checker3.getCourses().get(j));
                    }
                }
            }

        }
        StringBuilder stringBuilder = new StringBuilder();
        int counter = 0;
        int size = result.size();

        for (Course course : result) {
            stringBuilder.append(course.getName());
            stringBuilder.append("~");
            stringBuilder.append(course.getCredit());
            stringBuilder.append("~");
            stringBuilder.append(course.getAllAssignments().size());
            stringBuilder.append("~");
            stringBuilder.append(course.getStudents().size());

            if (counter < size - 1) {
                stringBuilder.append("~");
            }
            counter++;
        }

        System.out.println(stringBuilder.toString());
        writer(stringBuilder.toString());
    }
    //****************************************************************
    void Loginpage(String username, String password) throws JAXBException {
        Boolean studentExist = false;
        File xmls = new File("src/main/resources/Students");
        File[] list_of_xmls = xmls.listFiles();

        for (int i = 0; i < list_of_xmls.length; i++) {
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Student checker = (Student) unmarshaller.unmarshal(list_of_xmls[i]);
            if (checker.getName().equals(username) && checker.getPASSWORD().equals(password))
                studentExist = true;
        }
        writer(studentExist.toString());
    }
    //****************************************************************
    void signuppage(String username, String studentID, String password) throws JAXBException {
        boolean studentIsRepetitive = false;
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File xmls = new File("src/main/resources/Students");
        File[] list_of_xmls = xmls.listFiles();
        for (int i = 0; i < list_of_xmls.length; i++) {
            Student checker = (Student) unmarshaller.unmarshal(list_of_xmls[i]);
            if (checker.getStudentId().equals(studentID) || checker.getName().equals(username))
                studentIsRepetitive = true;
        }
        File file = new File("src/main/resources/Tasks/" + username + ".txt");
        if (studentIsRepetitive)
            writer("repetitive");

        else if (!studentIsRepetitive) {
            Student newStudent = new Student();
            newStudent.setName(username);
            newStudent.setStudentId(studentID);
            newStudent.setPASSWORD(password);
            File newStudentFile = new File("src/main/resources/Students" + "/" + studentID + ".xml");
            JAXBContext context2 = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = context2.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(newStudent, newStudentFile);
            writer("not repetitive");
        }
    }
    //****************************************************************
    void currentPasswordChecker(String username, String currentPassword) throws JAXBException {
        Boolean currentPasswordIsCorrect = false;
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File xmls = new File("src/main/resources/Students");
        File[] list_of_xmls = xmls.listFiles();

        for (int i = 0; i < list_of_xmls.length; i++) {
            Student checker = (Student) unmarshaller.unmarshal(list_of_xmls[i]);
            if (checker.getName().equals(username)) {
                if (checker.getPASSWORD().equals(currentPassword))
                    currentPasswordIsCorrect = true;
            }
        }

        writer(currentPasswordIsCorrect.toString());
    }
    //****************************************************************
    void changePassword(String username, String password) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File xmls = new File("src/main/resources/Students");
        File[] list_of_xmls = xmls.listFiles();

        for (int i = 0; i < list_of_xmls.length; i++) {
            Student checker = (Student) unmarshaller.unmarshal(list_of_xmls[i]);
            if (checker.getName().equals(username)) {
                checker.setPASSWORD(password);
                File studentFile = new File("src/main/resources/Students" + "/" + checker.getStudentId() + ".xml");
                JAXBContext editedVersion = JAXBContext.newInstance(Student.class);
                Marshaller marshaller2 = editedVersion.createMarshaller();
                marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
                marshaller2.marshal(checker,studentFile);
            }
        }

        writer(""); //just for closing dis & dos
    }
    //****************************************************************
    private void assignmentPage(String username) throws JAXBException {
        ArrayList<Assignment> result = new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(Teacher.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File xmls = new File("src/main/resources/Teachers");
        File[] list_of_xmls = xmls.listFiles();
        for (int i = 0; i < list_of_xmls.length; i++) {
            Teacher checker = (Teacher) unmarshaller.unmarshal(list_of_xmls[i]);
            for (int j = 0; j < checker.getCourses().size(); j++) {
                for (int k = 0; k < checker.getCourses().get(j).getStudents().size(); k++) {
                    if (checker.getCourses().get(j).getStudents().get(k).getName().equals(username)) {
                        result.addAll(checker.getCourses().get(j).getActiveAssignments());

                    }
                }
            }

        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            if (i == result.size() - 1) {
                stringBuilder.append(result.get(i).getSubject());
                stringBuilder.append("~");
                stringBuilder.append(result.get(i).getDeadLine());
                stringBuilder.append("~");
                stringBuilder.append(result.get(i).getDescription()
                );
            } else {
                stringBuilder.append(result.get(i).getSubject());
                stringBuilder.append("~");
                stringBuilder.append(result.get(i).getDeadLine());
                stringBuilder.append("~");
                stringBuilder.append(result.get(i).getDescription());
                stringBuilder.append("~");

            }
        }
        System.out.println(stringBuilder.toString());
        writer(stringBuilder.toString());
    }
    //****************************************************************
    void initProfileData(String username) throws JAXBException, InterruptedException {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File xmls = new File("src/main/resources/Students");
        File[] list_of_xmls = xmls.listFiles();

        for (int i = 0; i < list_of_xmls.length ; i++) {
            Student checker = (Student) unmarshaller.unmarshal(list_of_xmls[i]);
            if (checker.getName().equals(username)) {
                int currentTermCredit = 0 ;

                for (int k = 0; k < checker.getTerms().get(checker.getTerms().size() - 1).getStudentCourses().size() ; k++)
                    currentTermCredit += checker.getTerms().get(checker.getTerms().size() - 1).getStudentCourses().get(k).getCredit();

                writer(checker.getStudentId() + "-" + checker.getTotalAverage() + "-" + checker.getCurrentTerm() + "-" + currentTermCredit + "-" + checker.getTotalPassedCredit());
            }
        }
    }
    //****************************************************************
    void deleteAccount(String username) throws JAXBException {
        String un = username ;
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File xmls = new File("src/main/resources/Students");
        File[] list_of_xmls = xmls.listFiles();

        Student willBeDeleted = null;
        for (int i = 0; i < list_of_xmls.length ; i++) {
            Student checker = (Student) unmarshaller.unmarshal(list_of_xmls[i]);
            if (checker.getName().equals(username))
                willBeDeleted = checker ;
        }
        String studentID = willBeDeleted.getStudentId();

        JAXBContext context_t = JAXBContext.newInstance(Teacher.class);
        Unmarshaller unmarshaller_t = context_t.createUnmarshaller();
        File xmls_teacher = new File("src/main/resources/Teachers");
        File[] list_of_xmls_teacher = xmls_teacher.listFiles();
        Teacher temp ;

        for (int i = 0; i < list_of_xmls_teacher.length ; i++) {
            temp = (Teacher) unmarshaller_t.unmarshal(list_of_xmls_teacher[i]);
            ArrayList<Course> coursesOfTeacher = temp.getCourses();

            for (int j = 0; j < coursesOfTeacher.size() ; j++) {
                temp.RemoveStudent(coursesOfTeacher.get(j),new Student(willBeDeleted.getName(),willBeDeleted.getStudentId()));
                File teacherFile = new File("src/main/resources/Teachers" + "/" + temp.getName() + ".xml");
                JAXBContext editedVersion = JAXBContext.newInstance(Teacher.class);
                Marshaller marsh = editedVersion.createMarshaller();
                marsh.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marsh.marshal(temp, teacherFile);
            }
        }

        File finialDelete = new File("src/main/resources/Students" + "/" + studentID + ".xml");
        finialDelete.delete();
        File file =new File("src/main/resources/Tasks"+username+".txt");
        file.delete();
        writer("");
    }
    //****************************************************************
    void editAccount(String username ,String birthday ,String fatherName ,String nationalID ,String phone ,String fieldOfStudy) throws JAXBException, ParseException {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File xmls = new File("src/main/resources/Students");
        File[] list_of_xmls = xmls.listFiles();

        for (int i = 0; i < list_of_xmls.length ; i++) {
            Student checker = (Student) unmarshaller.unmarshal(list_of_xmls[i]);
            if (checker.getName().equals(username))
            {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                checker.setBirthDate(formatter.parse(birthday));
                checker.setFatherName(fatherName);
                checker.setNationalId(nationalID);
                checker.setPhoneNumber(phone);
                checker.setField(BeheshtiUniversityField.valueOf(fieldOfStudy.split("\\.")[1]));
                File StudentFile = new File("src/main/resources/Students" + "/" + checker.getStudentId() + ".xml");
                JAXBContext editedVersion = JAXBContext.newInstance(Student.class);
                Marshaller marshaller2 = editedVersion.createMarshaller();
                marshaller2.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                marshaller2.marshal(checker, StudentFile);
            }
        }
        writer("success");
    }

}