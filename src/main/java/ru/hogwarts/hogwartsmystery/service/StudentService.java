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
    private int count = 0;

    public Student addStudent(Student student) {
        student.setId(count++);
        students.put(student.getId(), student);
        return student;
    }

    public Student getStudent(int id) {
        return students.get(id);
    }

    public Student editStudent(int id, Student student){
        if (!students.containsKey(id)) {
            return null;
        }
        students.put(id, student);
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
