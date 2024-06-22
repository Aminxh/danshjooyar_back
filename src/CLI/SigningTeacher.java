
package CLI;

import Main.Teacher;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class SigningTeacher {
    public static void signing() throws IOException, InterruptedException {

        Scanner scanner = new Scanner(System.in);
        boolean ok = false;
        String name = null;
        Teacher teacher;
        try {
            System.out.println("Enter your name");
            name = scanner.nextLine();;
            FileInputStream fis = new FileInputStream("resources\\Teachers"+"\\"+name+".xml");
            XMLDecoder Reader = new XMLDecoder(fis);
            teacher = (Teacher) Reader.readObject();
            Reader.close();
            fis.close();
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

