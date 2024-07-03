package Server;

import mainClasses.Student;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class test {
    public static void main(String[] args) throws JAXBException
    {
        Student newStudent = new Student();
        newStudent.setName("AliVahdati");
        newStudent.setStudentId("402243080");
        newStudent.setPASSWORD("12345678");
        File newStudentFile = new File("src/main/resources/Students" + "/" + "402243080" + ".xml");
        JAXBContext context2 = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context2.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(newStudent, newStudentFile);
    }
}
