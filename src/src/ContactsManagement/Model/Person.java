package ContactsManagement.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {
    private String name, gender, phoneNumber, groupNumber, address,  email;
LocalDate birthDate;
    public Person(String name, String gender, String phoneNumber, String groupNumber, String address,
                  LocalDate birthDate, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.groupNumber = groupNumber;
        this.address = address;
        this.birthDate = birthDate;
        this.email = email;
        this.gender = gender;
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(String groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", groupNumber='" + groupNumber + '\'' +
                ", address='" + address + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String convertToString() {
        return name + "," + gender + "," + phoneNumber + "," + groupNumber + "," + address + "," + birthDate + "," + email;
    }
}


