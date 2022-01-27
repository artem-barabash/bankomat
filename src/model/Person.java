package model;

import javafx.collections.ObservableList;

import java.time.LocalDate;

public class Person {
    public String numberCode;
    public String phone;
    public String password;
    public String surname;
    public String name;
    public String fathername;
    public LocalDate birthday;
    public float balance;
    public ObservableList<OperationItem> incomeList;
    public ObservableList<OperationItem> costsList;

    public Person(String numberCode, String phone, String password, String surname, String name, String fathername, LocalDate birthday, float balance, ObservableList<OperationItem> incomeList, ObservableList<OperationItem> costsList) {
        this.numberCode = numberCode;
        this.phone = phone;
        this.password = password;
        this.surname = surname;
        this.name = name;
        this.fathername = fathername;
        this.birthday = birthday;
        this.balance = balance;
        this.incomeList = incomeList;
        this.costsList = costsList;
    }

    public String getNumberCode() {
        return numberCode;
    }

    public void setNumberCode(String numberCode) {
        this.numberCode = numberCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public ObservableList<OperationItem> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(ObservableList<OperationItem> incomeList) {
        this.incomeList = incomeList;
    }

    public ObservableList<OperationItem> getCostsList() {
        return costsList;
    }

    public void setCostsList(ObservableList<OperationItem> costsList) {
        this.costsList = costsList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "numberCode='" + numberCode + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", fathername='" + fathername + '\'' +
                ", birthday=" + birthday +
                ", balance=" + balance +
                ", incomeList=" + incomeList +
                ", costsList=" + costsList +
                '}';
    }
}
