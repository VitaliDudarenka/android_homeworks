package com.gmail.vitaliklancer.mytestapp.Homework7;

import java.util.ArrayList;
import java.util.List;

public class StudentGroup {
    private List<Student> students = new ArrayList<>();

    public List<Student> getStudentSet() {
        return students;
    }

    public void add(Student student) {
        this.students.add(student);
    }

    public void addAll(List<Student> students) {
        this.students.addAll(students);
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "students=" + students +
                '}';
    }
}
