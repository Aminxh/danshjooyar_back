package menu;

import Main.BeheshtiUniversityField;
import Main.Gender;
import Main.Teacher;

import java.beans.XMLEncoder;
import java.io.*;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class RegisterTeacher {
    public static void register() throws IOException, InterruptedException {
//         String name;
//         String PASSWORD;
//         Date BirthDate;
//         Gender gender;
//         String FatherName;
//         String NationalId;
//         String phoneNumber;
//         BeheshtiUniversityField field;

        Scanner scanner =new Scanner(System.in);
        Teacher teacher =new Teacher();
        System.out.println("Enter your name");
        teacher.setName(scanner.nextLine());
        System.out.println("Enter your password");
        teacher.setPASSWORD(scanner.nextLine());
        System.out.println("Enter your BirthDate (yyyy/MM/dd) ");
        String date=scanner.nextLine();
        teacher.setBirthDate(new Date(date));
        System.out.println("""
                Enter your gender
                1-Male
                2-Female""");
        String c=scanner.nextLine();
        if(Objects.equals(c, "1")) {
            teacher.setGender(Gender.Male);
        } else {
            teacher.setGender(Gender.Female);
        }
        System.out.println("Enter your father name");
        teacher.setFatherName(scanner.nextLine());
        System.out.println("Enter your national ID");
        teacher.setNationalId(scanner.nextLine());
        System.out.println("Enter your Field" +
                "   1-COMPUTER_SCIENCE,\n" +
                "   2-LIFE_SCIENCE,\n" +
                "   3- SPORT_SCIENCE,\n" +
                "    4-AGRICULTURE_FORESTRY,\n" +
                "    5-BIOLOGICAL_SCIENCES,\n" +
                "    6-SOCIAL_SCIENCES,\n" +
                "    7-ENVIRONMENTAL_STUDIES,\n" +
                "    8-ENGINEERING_TECHNOLOGY,\n" +
                "    9-ARTS_HUMANITIES,\n" +
                "    10-ECONOMICS_BUSINESS,\n" +
                "    11-NATURAL_SCIENCES_MATH,\n" +
                "    12-PSYCHOLOGY,\n" +
                "    13-LAW,\n" +
                "    14-MEDICINE_HEALTHCARE;");
        String choose=scanner.nextLine();
        switch (choose){
            case "1": teacher.setField(BeheshtiUniversityField.COMPUTER_SCIENCE);
            case "2": teacher.setField(BeheshtiUniversityField.LIFE_SCIENCE);
            case "3":teacher.setField(BeheshtiUniversityField.SPORT_SCIENCE);
            case "4":teacher.setField(BeheshtiUniversityField.AGRICULTURE_FORESTRY);
            case "5":teacher.setField(BeheshtiUniversityField.BIOLOGICAL_SCIENCES);
            case "6":teacher.setField(BeheshtiUniversityField.SOCIAL_SCIENCES);
            case "7":teacher.setField(BeheshtiUniversityField.ENVIRONMENTAL_STUDIES);
            case "8":teacher.setField(BeheshtiUniversityField.ARTS_HUMANITIES);
            case "9":teacher.setField(BeheshtiUniversityField.ECONOMICS_BUSINESS);
            case "10":teacher.setField(BeheshtiUniversityField.NATURAL_SCIENCES_MATH);
            case "11":teacher.setField(BeheshtiUniversityField.PSYCHOLOGY);
            case "12":teacher.setField(BeheshtiUniversityField.LAW);
            case "13":teacher.setField(BeheshtiUniversityField.MEDICINE_HEALTHCARE);
        }
        String name=teacher.getName();
        File file =new File("resources\\Teachers"+"\\"+name+".xml");
        FileOutputStream fos=new FileOutputStream(file);
        XMLEncoder writer=new XMLEncoder(fos);
        writer.writeObject(teacher);
        writer.close();
        fos.close();
        Thread.sleep(1000);
        System.out.println("\033[H\033[2J");
        System.out.flush();

    }
}