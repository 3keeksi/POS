/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import beans.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author crether
 */
public class DBTester {

    private Connection connection;

    public DBTester() throws ClassNotFoundException {
        // load postgres database driver (optional)
        Class.forName("org.postgresql.Driver");
    }

    /**
     * connect to postgres database dbName
     *
     * @param dbName
     */
    public void connect(String dbName) throws SQLException {
        // Default port number is 5432
        String url = "jdbc:postgresql://localhost/" + dbName;
        String username = "postgres";
        String password = "postgres";
        connection = DriverManager.getConnection(url, username, password);
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void createDatabase(String databasename) throws SQLException {
        String sqlString = "CREATE DATABASE " + databasename;

        // create statement
        Statement statement = connection.createStatement();
        statement.execute(sqlString);
        statement.close();
    }

    public void createTable() throws SQLException {
        String sqlString = "CREATE TABLE student (\n"
                + "    student_id serial PRIMARY KEY,\n"
                + "    cat_nr INTEGER NOT NULL,\n"
                + "    firstname VARCHAR(100) NOT NULL,\n"
                + "    lastname VARCHAR(100) NOT NULL,\n"
                + "    birthdate DATE NOT NULL\n"
                + ");";
        Statement statement = connection.createStatement();
        statement.executeUpdate(sqlString);
        statement.close();
    }

    public void insertStudent(Student student) throws SQLException {
        Statement statement = connection.createStatement();
        java.sql.Date date = java.sql.Date.valueOf(student.getBirthdate());
        String sqlString = "INSERT INTO student (cat_nr, firstname, lastname, birthdate)\n"
                + String.format(
                        "VALUES (%d, '%s', '%s', '%s');",
                        student.getCatNr(),
                        student.getFirstname(),
                        student.getLastname(),
                        date.toString());
        statement.executeUpdate(sqlString);
        statement.close();
    }

    public List<Student> showTableContent() throws SQLException {
        List<Student> students = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sqlQuery = "SELECT * FROM student";
        ResultSet rs = statement.executeQuery(sqlQuery);
        while (rs.next()) {
            int id = rs.getInt("student_id");
            int catNR = rs.getInt("cat_nr");
            String firstname = rs.getString("firstname");
            String lastname = rs.getString("lastname");
            LocalDate date = rs.getDate("birthdate").toLocalDate();
            students.add(new Student(id, catNR, firstname, lastname, date));
        }
        statement.close();

        return students;
    }

    public static void main(String[] args) {
        try {
            DBTester dbt = new DBTester();
            dbt.connect("postgres");
            try {
                dbt.createDatabase("studentdb");
            } catch (SQLException ex) {
                System.out.println("Database already exists");
            }
            dbt.disconnect();

            dbt.connect("studentdb");
            try {
                dbt.createTable();
            } catch (SQLException ex) {
                System.out.println("Table already exists");
            }
//            dbt.insertStudent(new Student(0, 1, "Leon", "Anghel", LocalDate.of(2002, Month.OCTOBER, 24)));
//            dbt.insertStudent(new Student(0, 2, "Nico", "Baumann", LocalDate.of(2002, Month.JULY, 31)));
//            dbt.insertStudent(new Student(0, 2, "Adrian", "Berner", LocalDate.of(2003, Month.JUNE, 12)));
              dbt.showTableContent().forEach(System.out::println);

            dbt.disconnect();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBTester.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
