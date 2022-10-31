package ru.hogwarts.hogwartsmystery.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsmystery.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Map<Integer, Student> students = new HashMap<>();

    public Student addStudent(Student student) {
        Student student1 = new Student(student.getName(), student.getAge());
        students.put(student1.getId(), student1);
        return student1;
    }

    public Student getStudent(int id) {
        if(id<=0){return null;}
        return students.get(id);
    }

    public Student editStudent(int id, Student student){
        Student student1 = students.get(id);
        student1 = student;
        return student;
    }

    public Student deleteStudent(int id) {
        Student deletedStudent = students.get(id);
        if(deletedStudent==null) {
            return null;
        } else {
            students.remove(id);
            return deletedStudent;
        }
    }

    public Map<Integer, Student> getAll() {
        return students;
    }

    public Collection<Student> getByAge(int age) {
        return students.values().stream()
                .filter(e -> e.getAge()==age)
                .collect(Collectors.toList());
    }
}
