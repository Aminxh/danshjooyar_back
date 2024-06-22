package CLI;


import mainClasses.Teacher;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Scanner;

public class AdminMenu {
    public static void adminmenu() throws InterruptedException, IOException {
        boolean finish = false;
        while (!finish) {
            //Add teacher
            //Remove teacher
            //Teacher method
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
                        Files.deleteIfExists(Path.of("resources\\Teachers\\" + name + ".xml"));
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
                        FileInputStream fis = new FileInputStream("resources\\Teachers\\" + name + ".xml");
                        XMLDecoder Reader = new XMLDecoder(fis);
                        Teacher teacher = (Teacher) Reader.readObject();
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
