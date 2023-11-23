package com.redfox.redfox.beans;

import java.io.Serializable;

public class UserBean implements Serializable  {
    private int ID;
    private String email;
    private String password;
    private String Fname;
    private String Lname;
    private String Contact;
    private String gender;
    private int usertype = 2;
    private String prefferedGenre;

    

	public UserBean() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
    public String getPrefferedGenre() {
		return prefferedGenre;
	}

	public void setPrefferedGenre(String prefferedGenre) {
		this.prefferedGenre = prefferedGenre;
	}
}
