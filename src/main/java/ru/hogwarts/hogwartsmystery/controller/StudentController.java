package ru.hogwarts.hogwartsmystery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @GetMapping("{age}")
    public ResponseEntity<Collection<Student>> getByAge(@RequestParam int age) {
        if(age>=11) {
            return ResponseEntity.ok(studentService.getByAge(age));
        } else{return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();}
    }
    @GetMapping(path = "all")
    public Map<Integer, Student> getAll(){
        return studentService.getAll();
    }
    ///
    @PutMapping
    public ResponseEntity<Student> put(@RequestBody int id, Student student) {
        Student putStudent = studentService.editStudent(id, student);
        if(putStudent==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(putStudent);
    }
    ///
    @DeleteMapping
    public ResponseEntity<Student> delete(@RequestParam int id) {
        Student deletedStudent = studentService.getAll().get(id);
        if(deletedStudent==null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        studentService.deleteStudent(id);
        return ResponseEntity.ok(deletedStudent);
    }
}
