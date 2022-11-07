package ru.hogwarts.hogwartsmystery.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsmystery.model.Student;
import ru.hogwarts.hogwartsmystery.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;

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
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student){
        if(!studentRepository.existsById(student.getId())) {return null;}
        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        return studentRepository.findAll();
    }

    public Collection<Student> getByAge(int age) {
        return new ArrayList<>(studentRepository.findAllByAge(age));
    }

    public Collection<Student> getByAgeBetween(int minAge, int maxAge) {
        return new ArrayList<>(studentRepository.findAllByAgeBetween(minAge, maxAge));
    }
}
