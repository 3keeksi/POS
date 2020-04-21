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

/**
 * DBConnector is the connector class to the PostgreSQL database
 *
 * @author crether
 */
public class DBAccess {

    private Connection con;

    private final String dataSql;
    private final String createSql;
    public List<Employee> employees;

    // these are the statements where i programmatically insert the values
    private PreparedStatement empFromDep;
    private PreparedStatement avgSal;
    private PreparedStatement insEmp;
    private PreparedStatement remEmp;
    
    private static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("HH:mm:ss");

    public DBAccess() throws ClassNotFoundException {
        // load postgres database driver (optional)
        Class.forName("org.postgresql.Driver");

        // load all the sql statements
        dataSql = FileLoader.loadSql("data.sql");
        createSql = FileLoader.loadSql("createTable.sql");
    }

    /**
     * connects to the database and creates the database/table
     * also inserts the data
     * @throws SQLException 
     */
    public void setup() throws SQLException {
        log("starting the setup!");
        log("starting to connect to the database!");
        connect();
        log("finished connecting to the database!");
        
        log("creating the database if it isnt there");
        createDB();
        log("finished creating the database");
        
        log("creating the table if it isnt there");
        createTable();
        log("finished creating the table");
        
        log("inserting all the test data");
        insertEmployees();
        employees = getEmployees();
        log("inserted the test data");
    }

    public List<Employee> getEmployees() throws SQLException {
        List<Employee> list = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet set = st.executeQuery("SELECT * FROM mitarbeiter ORDER BY name, vorname;");

        while (set.next()) {
            list.add(new Employee(set));
        }

        return list;
    }

    public void connect() throws SQLException {
        // Default port number is 5432
        String url = "jdbc:postgresql://localhost/mitarbeiterdb";
        String username = "postgres";
        String password = "postgres";
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

    public boolean createDB() {
        String sql = "CREATE DATABASE mitarbeiterdb;";
        // create statement with a "try-resources" block
        try (Statement st = con.createStatement()) {
            return st.execute(sql);
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

    public void insertEmployees() throws SQLException {
        // check if the table mitarbeiter exists and then delete all rows
        // to always have the newest data, if it doesn't exist, create the table
        try (Statement st = con.createStatement()) {
            try {
                st.execute("DELETE FROM mitarbeiter;");
            } catch (SQLException e) {
                createTable();
            }

            // insert the data into the mitarbeiter table
            st.execute(dataSql);
        }
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
        
        if(set.next()) return set.getBigDecimal(1).doubleValue();
        return 0;
    }

    public boolean insertEmployee(Employee employee) throws SQLException {
        // replace all the values
        insEmp.setInt(1, employee.getPers_nr());
        insEmp.setString(2, employee.getName());
        insEmp.setString(3, employee.getVorname());
        insEmp.setDate(4, java.sql.Date.valueOf(employee.getGeb_datum()));
        insEmp.setBigDecimal(5, employee.getGehalt());
        insEmp.setInt(6, employee.getAbt_nr());
        insEmp.setString(7, employee.getGeschlecht());
        
        log("inserting an employee");

        return insEmp.execute();
    }

    public boolean removeEmployee(Employee employee) throws SQLException {
        remEmp.setInt(1, employee.getPers_nr());
        log("removing an employee");
        return remEmp.execute();
    }

    public static void main(String[] args) {
        try {
            DBAccess db = new DBAccess();
            db.setup();
            Employee emp = new Employee(0, "Imeri", "Denis", LocalDate.now(), BigDecimal.TEN, 2, "M");
//            db.insertEmployee(emp);
            db.removeEmployee(emp);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBAccess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void log(String msg) {
        LocalTime now = LocalTime.now();
        System.out.format("[%s] %s\n", DTF.format(now), msg);
    }
}
