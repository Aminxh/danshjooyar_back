package menu;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class firstMenu {
    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Please select your role:");
            System.out.println("1. Admin");
            System.out.println("2. Teacher");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            boolean normalSelect=false;
            while (!normalSelect){
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    normalSelect=true;
                    AdminLogin.AdminLoginMenu();
                    break;
                case 2:

                    System.out.println("\033[H\033[2J");
                    System.out.flush();
                    normalSelect=true;
                    SigningTeacher.signing();
                    break;
                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    normalSelect=true;
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    System.out.println("\033[H\033[2J");
                    System.out.flush();
            }
        }
    }

}}
