package Menus;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class AdminLogin {
    static public void   AdminLoginMenu() throws InterruptedException, IOException, JAXBException {
        boolean isLoggedIn=false;
        while(!isLoggedIn){
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter admin password: ");
        String passwordAttempt = scanner.nextLine();
        if (Objects.equals(passwordAttempt, "admin123")) {
            System.out.println("Sign in successful! Welcome, admin.");
             isLoggedIn = true;
            Thread.sleep(1000);
            System.out.println("\033[H\033[2J");
            System.out.flush();
             AdminMenu.adminmenu();
        } else {
            System.out.println("Incorrect password. Please try again.");
            Thread.sleep(1000);
            System.out.println("\033[H\033[2J");
            System.out.flush();

        }
    }
}
}
