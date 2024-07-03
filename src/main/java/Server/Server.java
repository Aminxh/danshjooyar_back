package Server;

import mainClasses.Course;
import mainClasses.Student;
import mainClasses.Teacher;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
        String[] split = command.split("~");
        switch (split[0]){
            case "LOGIN":
                try {
                    Loginpage(split[1],split[2]);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
                break;
            case "SIGNUP":
                signuppage();
                break;
            case "EDITACCOUNT":
                //TODO
                break;
            case "CHANGEPASSWORD":
                //TODO
                break;
            case "ADDCLASS":
                //TODO
                break;
            case "GETOBJECT":
                //TODO
                break;
            case "ISLOGIN":
                //TODO
                break;
        }
    }
    void Loginpage(String username,String password) throws JAXBException {
        Boolean studentExist = false ;
        File xmls = new File("src/main/resources/Teachers");
        File[] list_of_xmls = xmls.listFiles();

        for(int i=0 ; i < list_of_xmls.length ; i++)
        {
            JAXBContext context = JAXBContext.newInstance(Teacher.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Teacher checker = (Teacher) unmarshaller.unmarshal(list_of_xmls[i]);
            ArrayList<Course> coursesOfTeacher = checker.getCourses();
            for (int j=0 ; j < coursesOfTeacher.size() ; j++)
            {
                ArrayList<Student> studentsList = coursesOfTeacher.get(j).getStudents();
                for (int k=0 ; k < studentsList.size() ; k++)
                {
                    System.out.println("xxxxx");
                    System.out.println(studentsList.get(k).getName()+" "+studentsList.get(k).getPASSWORD());
                    if (studentsList.get(k).getName().equals(username) && studentsList.get(k).getPASSWORD().equals(password))
                        studentExist = true ;
                }
            }
            writer(studentExist.toString());
        }
    }
    void signuppage(){

    }
}
