package com.gmail.vitaliklancer.mytestapp.Homework7;

import android.support.v7.util.SortedList;

import com.github.wrdlbrnft.sortedlistadapter.SortedListAdapter;

public class Student {
    private String name;
    private String surname;
    private String imgURL;
    private Long id;

    public Student(String name, String surname, String imgURL, Long id) {
        this.name = name;
        this.surname = surname;
        this.imgURL = imgURL;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getImgURL() {
        return imgURL;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (id != student.id) return false;
        return surname != null ? surname.equals(student.surname) : student.surname == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
