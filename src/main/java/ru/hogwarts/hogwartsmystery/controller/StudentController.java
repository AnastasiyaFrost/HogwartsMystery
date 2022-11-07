package ru.hogwarts.hogwartsmystery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.hogwartsmystery.model.Faculty;
import ru.hogwarts.hogwartsmystery.model.Student;
import ru.hogwarts.hogwartsmystery.service.StudentService;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    /////
    @PostMapping
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student addedStudent = studentService.addStudent(student);
        if(addedStudent==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(addedStudent);
    }
    ////
    @GetMapping
    public ResponseEntity<Student> get(@RequestParam int id) {
        Student gettingStudent = studentService.getStudent(id);
        if(gettingStudent==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(gettingStudent);
    }
    @GetMapping("/faculty")
    public Faculty getFaculty(int studentId) {return studentService.getStudent(studentId).getFaculty();}
    @GetMapping("{age}")
    public ResponseEntity<Collection<Student>> getByAge(@RequestParam int age) {
        if(age>=11) {
            return ResponseEntity.ok(studentService.getByAge(age));
        } else{return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
    }
    @GetMapping(path = "byage")
    public Collection<Student> getBetwAges(@RequestParam int minAge, @RequestParam int maxAge){
        return studentService.getByAgeBetween(minAge, maxAge);
    }
    @GetMapping(path = "all")
    public Collection <Student> getAll(){
        return studentService.getAll();
    }
    ///
    @PutMapping
    public ResponseEntity<Student> put(@RequestBody Student student) {
        Student putStudent = studentService.editStudent(student);
        if(putStudent==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(putStudent);
    }
    ///
    @DeleteMapping
    public ResponseEntity<Student> delete(@RequestParam int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
