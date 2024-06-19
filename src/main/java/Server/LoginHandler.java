package Server;

import mainClasses.Teacher;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;

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
            JAXBContext context = JAXBContext.newInstance(Teacher.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Teacher obj = (Teacher) unmarshaller.unmarshal(new File("ali.xml"));
            
        }
        catch(Exception e){

        }

    }
}
