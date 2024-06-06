package Menus;

import mainClasses.Teacher;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class SigningTeacher {
    public static void signing() throws IOException, InterruptedException, JAXBException {

        Scanner scanner = new Scanner(System.in);
        boolean ok = false;
        String name = null;
        Teacher teacher;
        try {
            System.out.println("Enter your name");
            name = scanner.nextLine();;
            JAXBContext unmarshalContext = JAXBContext.newInstance(Teacher.class);
            Unmarshaller unmarshaller = unmarshalContext.createUnmarshaller();
             teacher = (Teacher) unmarshaller.unmarshal(new File("src/main/resources/Teachers"+"\\"+name+".xml"));
        }
        catch (Exception ex){
            System.out.println("username not found");
            return;
        }
        while (!ok) {
            System.out.println("Enter your Password");
            String password = scanner.nextLine();
            if (Objects.equals(teacher.getPASSWORD(), password)) {
                System.out.println("welcome");
               TeacherMenu.teachermenu(teacher);
                ok = true;
            }
        }
    }
}

