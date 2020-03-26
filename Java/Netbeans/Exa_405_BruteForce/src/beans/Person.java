/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author crether
 */
public class Person {
	private String firstName;
	private String lastName;
	private String password;
	private String hash;

	public Person(String firstName, String lastName, String password, String hash) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
		this.hash = hash;
	}
	
	public Person(String line) {
		String[] split = line.split(",");
		this.firstName = split[0];
		this.lastName = split[1];
		this.password = split[2];
		this.hash = split[3];
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
	
	
}