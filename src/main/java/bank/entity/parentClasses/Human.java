package bank.entity.parentClasses;

import java.time.LocalDate;

public class Human {
    private Integer id;
    private String name;
    private String surname;
    private String middleName = null;
    private LocalDate birthDay;

    public Human(Integer id, String name, String surname, LocalDate birthDay) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
    }

    public Human(Integer id, String name, String surname, String middleName, LocalDate birthDay) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.birthDay = birthDay;
    }

    public String getFullName() {
        if (middleName != null)
            return name + " " + surname + " " + middleName;
        else
            return name + " " + surname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }
}
