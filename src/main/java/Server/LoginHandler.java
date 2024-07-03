package Server;


import mainClasses.Course;
import mainClasses.Student;
import mainClasses.Teacher;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;
import java.util.ArrayList;

public class LoginHandler implements Runnable{
    Socket socketForDisDos ;
    LoginHandler (Socket sock)
    {
        socketForDisDos = sock;
    }

    @Override
    public void run() {
        try{
        DataInputStream inputUserInfo = new DataInputStream(socketForDisDos.getInputStream());
        DataOutputStream userVerfier = new DataOutputStream(socketForDisDos.getOutputStream());
        System.out.println("in LoginHandeler");
            StringBuilder userData = new StringBuilder();
            int index = inputUserInfo.read();
            while (index != 0) {
                userData.append((char) index);
                index = inputUserInfo.read();
            System.out.println(index);
            }
            //USERNAME-|-PASSWORDS
            System.out.println(userData);
        String[] userDataSplitted = userData.toString().split("~");
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
                    if (studentsList.get(k).getName().equals(userDataSplitted[0]) && studentsList.get(k).getPASSWORD().equals(userDataSplitted[1]))
                        studentExist = true ;
                }
            }
        }

        userVerfier.writeBytes("true");
        userVerfier.flush();
        userVerfier.close();
        inputUserInfo.close();
        socketForDisDos.close();
            System.out.println(studentExist);
        }
        catch(Exception e){

        }

    }
}
