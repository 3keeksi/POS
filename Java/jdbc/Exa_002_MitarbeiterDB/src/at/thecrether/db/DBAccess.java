/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.thecrether.db;

import at.thecrether.beans.Employee;
import at.thecrether.sql.FileLoader;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.postgresql.util.PSQLException;

/**
 * DBConnector is the connector class to the PostgreSQL database
 *
 * @author crether
 */
public class DBAccess {

    private final String baseUrl = "jdbc:postgresql://localhost/";
    private final String username = "postgres";
    private final String password = "postgres";

    private Connection con;

    private final String dataSql;
    private final String createSql;
    public List<Employee> employees;

    // these are the statements where i programmatically insert the values
    private PreparedStatement empFromDep;
    private PreparedStatement avgSal;
    private PreparedStatement insEmp;
    private PreparedStatement remEmp;

    public int highestPersNr = 0;

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern(
            "HH:mm:ss");

    public DBAccess() throws ClassNotFoundException {
        // load postgres database driver (optional)
        Class.forName("org.postgresql.Driver");

        // load the normal sql statements
        dataSql = FileLoader.loadSql("data.sql");
        createSql = FileLoader.loadSql("createTable.sql");
    }

    /**
     * connects to the database and creates the database/table also inserts the
     * data
     *
     * @throws SQLException
     */
    public void setup() throws SQLException {
        log("starting the setup!");
        log("creating the database if it isnt there");
        try {
            createDB();
        } catch (SQLException e) {
            log("could not connect to server!");
            throw e;
        }
        log("finished creating the database");

        log("starting to connect to the database!");
        connect();
        log("finished connecting to the database!");

        log("creating the table if it isnt there");
        createTable();
        log("finished creating the table");

        log("getting current employees");
        employees = getEmployees();
        log("got the current employees");
    }

    public List<Employee> getEmployees() throws SQLException {
        List<Employee> list = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet set = st.executeQuery(
                "SELECT * FROM mitarbeiter ORDER BY name, vorname;");

        while (set.next()) {
            Employee emp = new Employee(set);
            list.add(emp);
            if (highestPersNr < emp.getPers_nr())
                highestPersNr = emp.getPers_nr();
        }

        return list;
    }

    public void connect() throws SQLException {
        // Default port number is 5432
        String url = baseUrl + "mitarbeiterdb";
        con = DriverManager.getConnection(url, username, password);
        log("connected to mitarbeiterdb");

        // load all the prepared statements
        String empFromDepSql = FileLoader.loadSql("empFromDep.sql");
        String avgSalSql = FileLoader.loadSql("avgSal.sql");
        String insEmpSql = FileLoader.loadSql("insEmp.sql");
        String remEmpSql = FileLoader.loadSql("remEmp.sql");

        empFromDep = con.prepareStatement(empFromDepSql);
        avgSal = con.prepareStatement(avgSalSql);
        insEmp = con.prepareStatement(insEmpSql);
        remEmp = con.prepareStatement(remEmpSql);

        log("sucessfully loaded all prepared statements");
    }

    public void disconnect() throws SQLException {
        if (con != null && !con.isClosed())
            con.close();
    }

    public boolean createDB() throws SQLException {
        // Default port number is 5432
        String url = baseUrl + "postgres";
        Connection connection = DriverManager.getConnection(url, username,
                password);

        String sql = "CREATE DATABASE mitarbeiterdb;";
        // create statement with a "try-resources" block
        try (Statement st = connection.createStatement()) {
            boolean val = st.execute(sql);
            connection.close();
            return val;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean createTable() {
        // create the mitarbeiter table with a "try-resources" block
        try (Statement st = con.createStatement()) {
            return st.execute(createSql);
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * this one is for inserting the testdata script
     * @throws SQLException 
     */
    public void insertEmployees() throws SQLException {
        // check if the table mitarbeiter exists and then delete all rows
        // to always have the newest data, if it doesn't exist, create the table
        log("inserting test data");
        try (Statement st = con.createStatement()) {
            try {
                log("trying to delete all rows from mitarbeiter");
                st.execute("DELETE FROM mitarbeiter;");
                log("deleted all rows from mitarbeiter");
            } catch (SQLException e) {
                log("there is no mitarbeiter table, creating it");
                createTable();
            }

            // insert the data into the mitarbeiter table
            st.execute(dataSql);
            log("(re-)inserted all the test data");
        }

        employees = getEmployees();
    }

    public List<Employee> getEmployeesFromDepartment(int department) throws SQLException {
        List<Employee> list = new ArrayList<>();

        empFromDep.setInt(1, department);
        ResultSet set = empFromDep.executeQuery();
        while (set.next()) {
            list.add(new Employee(set));
        }

        log("getting employees for a department");

        return list;
    }

    public double getAverageSalary(char gender) throws SQLException {
        avgSal.setString(1, "" + gender);
        ResultSet set = avgSal.executeQuery();
        log("getting the average salary for " + gender);

        if (set.next())
            return set.getBigDecimal(1).doubleValue();
        return 0;
    }

    /**
     * this is for inserting a single employee
     * @param employee the employee you want to insert
     * @return if it added the employee or not
     * @throws SQLException 
     */
    public boolean insertEmployee(Employee employee) throws SQLException {
        // replace all the values
        insEmp.setInt(1, employee.getPers_nr());
        insEmp.setString(2, employee.getName());
        insEmp.setString(3, employee.getVorname());
        insEmp.setDate(4, java.sql.Date.valueOf(employee.getGeb_datum()));
        insEmp.setBigDecimal(5, employee.getGehalt());
        insEmp.setInt(6, employee.getAbt_nr());
        insEmp.setString(7, employee.getGeschlecht());
        highestPersNr = employee.getPers_nr();

        log("inserting an employee");

        return insEmp.execute();
    }

    public boolean removeEmployee(Employee employee) throws SQLException {
        remEmp.setInt(1, employee.getPers_nr());
        log("removing employee with pers_nr: " + employee.getPers_nr());
        return remEmp.execute();
    }

    public static void main(String[] args) {
        try {
            DBAccess db = new DBAccess();
            db.setup();
            Employee emp = new Employee(0, "Imeri", "Denis", LocalDate.now(),
                    BigDecimal.TEN, 2, "M");
//            db.insertEmployee(emp);
            db.removeEmployee(emp);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null,
                    ex);
        }
    }

    private static void log(String msg) {
        LocalTime now = LocalTime.now();
        System.out.format("[%s] %s\n", DTF.format(now), msg);
    }
}
