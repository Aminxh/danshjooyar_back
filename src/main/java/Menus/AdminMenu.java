package Menus;

import mainClasses.Teacher;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

public class AdminMenu {
    public static void adminmenu() throws InterruptedException, IOException, JAXBException {
        boolean finish = false;
        while (!finish) {
            //Add teacher
            //Remove teacher
            //mainClasses.Teacher method
            System.out.println("1-Add teacher");
            System.out.println("2-Remove teacher");
            System.out.println("3-Teacher method");
            Scanner scanner = new Scanner(System.in);
            boolean ok = false;
            String input = scanner.nextLine();
            boolean valid=false;
            while (!valid)
                switch (input) {
                    case "1": {
                        valid=true;
                        RegisterTeacher.register();
                        Thread.sleep(1000);
                        System.out.println("\033[H\033[2J");
                        System.out.flush();
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
                        break;

                    }
                    case "2": {
                        valid=true;
                        System.out.println("Write teacher name");
                        String name = scanner.nextLine();
                        Files.deleteIfExists(Path.of("src/main/resources/Teachers" + name + ".xml"));
                        System.out.println("done!");
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
                        break;
                    }
                    case "3": {
                        valid=true;
                        System.out.println("Write teacher name");
                        String name = scanner.nextLine();
                        JAXBContext unmarshalContext = JAXBContext.newInstance(Teacher.class);
                        Unmarshaller unmarshaller = unmarshalContext.createUnmarshaller();
                        Teacher teacher = (Teacher) unmarshaller.unmarshal(new File("src/main/resources/Teachers/" + name + ".xml"));
                        TeacherMenu.teachermenu(teacher);
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
                        break;
                    }
                }
        }
    }
}
