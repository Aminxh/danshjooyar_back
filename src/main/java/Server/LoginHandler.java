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
        String userData = inputUserInfo.readUTF(); //USERNAME-|-PASSWORDS
        String[] userDataSplitted = userData.split("-|-");
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
                    if (studentsList.get(i).getName().equals(userDataSplitted[0]) && studentsList.get(i).getPASSWORD().equals(userDataSplitted[1]))
                        studentExist = true ;
                }
            }
        }

        userVerfier.writeUTF(studentExist.toString());
        userVerfier.flush();
        }
        catch(Exception e){

        }

    }
}
