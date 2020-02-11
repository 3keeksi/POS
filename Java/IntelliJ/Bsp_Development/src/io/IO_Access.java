package io;

import beans.Gender;
import beans.Student;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class IO_Access {
    private List<Student> students = new ArrayList<>();
    private Path path = Paths.get(System.getProperty("user.dir"), "src", "res", "students.ser");

    public IO_Access() {
        students.add(new Student("Leon", "Anghel", LocalDate.now().minusYears(16), Gender.MALE));
        students.add(new Student("Nico", "Baumann", LocalDate.now().minusYears(17), Gender.MALE));
    }

    public void writeToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(path.toFile());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(students);
//        for (Student st : students) {
//            oos.writeObject(st);
//        }

        oos.close();
    }

    public void readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(path.toFile());
        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Student> studentsFromFile = (List<Student>) ois.readObject();

//        List<Student> studentsFromFile = new LinkedList<>();
//        try {
//            while (true) {
//                Student st = (Student) ois.readObject();
//                studentsFromFile.add(st);
//            }
//        } catch (EOFException ex) {
//            System.out.println(ex.toString());
//        }

        for (Student st : studentsFromFile) {
            System.out.println(st);
        }
    }
}
