/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.thecrether.beans;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author crether
 */
public class Employee {

    private int pers_nr;
    private String name;
    private String vorname;
    private LocalDate geb_datum;
    private BigDecimal gehalt;
    private int abt_nr;
    private String geschlecht;
    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern(
            "dd.MM.yyyy");

    public Employee() {
    }

    public Employee(int pers_nr, String name, String vorname,
            LocalDate geb_datum, BigDecimal gehalt, int abt_nr,
            String geschlecht) {
        this.pers_nr = pers_nr;
        this.name = name;
        this.vorname = vorname;
        this.geb_datum = geb_datum;
        this.gehalt = gehalt;
        this.abt_nr = abt_nr;
        this.geschlecht = geschlecht;
    }

    /**
     * constructor when retrieving from the database
     *
     * @param set the set to read from
     * @throws SQLException
     */
    public Employee(ResultSet set) throws SQLException {
        this.pers_nr = set.getInt("pers_nr");
        this.name = set.getString("name");
        this.vorname = set.getString("vorname");
        this.geb_datum = set.getDate("geb_datum").toLocalDate();
        this.gehalt = set.getBigDecimal("gehalt");
        this.abt_nr = set.getInt("abt_nr");
        this.geschlecht = set.getString("geschlecht");
    }

    /**
     * create a Employee from an table data row
     *
     * @param arr
     */
    public Employee(Object[] arr) {
        pers_nr = (int) arr[0];
        name = (String) arr[1];
        vorname = (String) arr[2];
        geb_datum = LocalDate.parse((String) arr[3], DTF);
        gehalt = (BigDecimal) arr[4];
        abt_nr = (int) arr[5];
        geschlecht = (String) arr[6];
    }
    
    public Employee(String line) {
        String[] split = line.split(";");
        pers_nr = 0;
        name = split[0];
        vorname = split[1];
        geb_datum = LocalDate.parse((String) split[2], DTF);
        gehalt = BigDecimal.valueOf(Double.parseDouble(split[3]));
        abt_nr = Integer.parseInt(split[4]);
        geschlecht = split[5].charAt(0)+"";
    }

    public int getPers_nr() {
        return pers_nr;
    }

    public void setPers_nr(int pers_nr) {
        this.pers_nr = pers_nr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public LocalDate getGeb_datum() {
        return geb_datum;
    }

    public void setGeb_datum(LocalDate geb_datum) {
        this.geb_datum = geb_datum;
    }

    public BigDecimal getGehalt() {
        return gehalt;
    }

    public void setGehalt(BigDecimal gehalt) {
        this.gehalt = gehalt;
    }

    public int getAbt_nr() {
        return abt_nr;
    }

    public void setAbt_nr(int abt_nr) {
        this.abt_nr = abt_nr;
    }

    public String getGeschlecht() {
        return geschlecht;
    }

    public void setGeschlecht(String geschlecht) {
        this.geschlecht = geschlecht;
    }

    /**
     * convert to a table data row
     *
     * @return an table data row containing the attributes
     */
    public Object[] convertToTableData() {
        Object[] arr = new Object[7];
        arr[0] = pers_nr;
        arr[1] = name;
        arr[2] = vorname;
        arr[3] = DTF.format(geb_datum);
        arr[4] = gehalt;
        arr[5] = abt_nr;
        arr[6] = geschlecht;
        return arr;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.name.toLowerCase(), other.name.toLowerCase()))
            return false;
        if (!Objects.equals(this.vorname.toLowerCase(), other.vorname.
                toLowerCase()))
            return false;
        if (!Objects.equals(this.geschlecht, other.geschlecht))
            return false;
        if (!Objects.equals(this.geb_datum, other.geb_datum))
            return false;
        return true;
    }
}
