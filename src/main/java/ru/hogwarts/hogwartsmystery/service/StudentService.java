package ru.hogwarts.hogwartsmystery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsmystery.model.Student;
import ru.hogwarts.hogwartsmystery.repository.StudentRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(int id) {
        return studentRepository.findById(id).get();
    }

    public Student editStudent(Student student){
        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> getByAge(int age) {
        return studentRepository.findAll().stream()
                .filter(e -> e.getAge()==age)
                .collect(Collectors.toList());
    }
}
