package Server;

import mainClasses.Assignment;
import mainClasses.Course;
import mainClasses.Student;
import mainClasses.Teacher;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    public static void main(String[] args) {

        try {
            ServerSocket inputDataUserInfo = new ServerSocket(7777);
            while (true){
            Socket accepter = inputDataUserInfo.accept();
            System.out.println("connected");

                RequestHandler requestHandler =new RequestHandler(accepter);
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
                index=dis.read();
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
        switch (splited[0]){
            case "LOGIN":
                try {
                    Loginpage(splited[1],splited[2]);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "SIGNUP":
                try {
                signuppage(splited[1],splited[2],splited[3]);
                } catch (JAXBException e)
                {
                    throw new RuntimeException(e) ;
                }
                break;
            case "EDITACCOUNT":
                //TODO
                break;
            case "CHANGEPASSWORD":
                //TODO
                break;
            case "CLASSA":
                //TODO
                break;
            case "ASSIGNMENTS":
                try {
                    assignmentPage(splited[1],splited[2]);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "SARA":
                //TODO
                break;
            case "DELETEACCOUNT":
                //TODO
                break;
        }
    }


    void Loginpage(String username,String password) throws JAXBException {
        Boolean studentExist = false ;
        File xmls = new File("src/main/resources/Students");
        File[] list_of_xmls = xmls.listFiles();

        for(int i=0 ; i < list_of_xmls.length ; i++)
        {
            JAXBContext context = JAXBContext.newInstance(Student.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Student checker = (Student) unmarshaller.unmarshal(list_of_xmls[i]);
            if (checker.getName().equals(username) && checker.getPASSWORD().equals(password))
            {
            studentExist = true ;
            }
        }
            writer(studentExist.toString());
    }
    void signuppage(String username ,String studentID ,String password) throws JAXBException {
        boolean studentIsRepetitive = false ;
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File xmls = new File("src/main/resources/Students");
        File[] list_of_xmls = xmls.listFiles();
        for (int i = 0 ; i < list_of_xmls.length ; i++)
        {
            Student checker = (Student) unmarshaller.unmarshal(list_of_xmls[i]);
            if (checker.getStudentId().equals(studentID) || checker.getName().equals(username))
                studentIsRepetitive = true ;
        }

        if (studentIsRepetitive)
        {
            writer("repetitive");
        }

        else if (!studentIsRepetitive)
        {
            Student newStudent = new Student();
            newStudent.setName(username);
            newStudent.setStudentId(studentID);
            newStudent.setPASSWORD(password);
            File newStudentFile = new File("src/main/resources/Students" + "/" + studentID + ".xml");
            JAXBContext context2 = JAXBContext.newInstance(Student.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(newStudent, newStudentFile);
            writer("not repetitive");
        }

    }
    private void assignmentPage(String username,String studentid) throws JAXBException {
        ArrayList<Assignment> result=new ArrayList<>();

        JAXBContext context = JAXBContext.newInstance(Teacher.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File xmls = new File("src/main/resources/Teachers");
        File[] list_of_xmls = xmls.listFiles();
        for (int i = 0 ; i < list_of_xmls.length ; i++)
        {
            Teacher checker = (Teacher) unmarshaller.unmarshal(list_of_xmls[i]);
            for (int j = 0; j < checker.getCourses().size(); j++) {
                if (checker.getCourses().get(j).getStudents().contains(new Student(username,studentid))){
                    result.addAll(checker.getCourses().get(j).getActiveAssignments());
                }
            }

        }
        StringBuilder stringBuilder=new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            if (i== result.size()-1){
                stringBuilder.append(result.get(i).getSubject());
                stringBuilder.append("~");
                stringBuilder.append(result.get(i).getDeadLine());
                stringBuilder.append("~");
                stringBuilder.append(result.get(i).getDescription()
                );
            }
            else {
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
}
