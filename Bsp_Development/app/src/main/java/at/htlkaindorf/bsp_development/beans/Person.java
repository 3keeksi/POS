package at.htlkaindorf.bsp_development.beans;

public class Person {
    private int id;
    private String name;

    public Person(String line) {
        this.id = Integer.parseInt(line.split(";")[0]);
        this.name = line.split(";")[1];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
