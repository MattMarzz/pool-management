package main.java.it.uniroma2.dicii.bd.model;

public class Member {
    private String cf;
    private String name;
    private String surname;
    private String cap;
    private String comune;
    private String province;
    private String add;
    private int n;
    private String cell;
    private String tel;
    private String email;

    //needed "link"
    private String course;

    public Member() {
    }

    public Member(String cf, String name, String surname, String cap, String comune, String province, String add, int n, String cell, String tel, String email, String course) {
        this.cf = cf;
        this.name = name;
        this.surname = surname;
        this.cap = cap;
        this.comune = comune;
        this.province = province;
        this.add = add;
        this.n = n;
        this.cell = cell;
        this.tel = tel;
        this.email = email;
        this.course = course;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAdd() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        if (cell == null || cell.isBlank()) {
            this.cell = null;
        } else {
            this.cell = cell;
        }
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        if (tel == null || tel.isBlank()) {
            this.tel = null;
        } else {
            this.tel = tel;
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            this.email = null;
        } else {
            this.email = email;
        }
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
