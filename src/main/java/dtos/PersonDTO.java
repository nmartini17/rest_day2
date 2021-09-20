package dtos;

import entities.Person;

public class PersonDTO {
    private int id;
    private String fname;
    private String lname;
    private String phone;

    public PersonDTO() {
    }

    public PersonDTO(Person p) {
        this.fname = p.getFname();
        this.lname = p.getLname();
        this.phone = p.getPhone();
        this.id = p.getId();
    }

    public PersonDTO(String fname, String lname, String phone){
        this.fname = getFname();
        this.lname = getLname();
        this.phone = getPhone();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
