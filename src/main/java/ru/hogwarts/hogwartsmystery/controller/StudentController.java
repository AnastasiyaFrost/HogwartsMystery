package ru.hogwarts.hogwartsmystery.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.hogwartsmystery.model.Avatar;
import ru.hogwarts.hogwartsmystery.model.Faculty;
import ru.hogwarts.hogwartsmystery.model.Student;
import ru.hogwarts.hogwartsmystery.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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
    public ResponseEntity<Student> add(@RequestBody(required = false) Student student) {
        Student addedStudent = studentService.addStudent(student);
        if (addedStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(addedStudent);
    }

    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable int id, @RequestParam MultipartFile avatar)
            throws IOException {
        if (avatar.getSize() >= 1024 * 300) {return ResponseEntity.badRequest().body("Файл слишком большой.");}
        studentService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable int id) {
        Avatar avatar = studentService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar(@PathVariable int id, HttpServletResponse response) throws IOException {
        Avatar avatar = studentService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }

    ////
    @GetMapping("/total")
    public int countTotalAmountStudents() {return studentService.countTotalAmountStudents();}
    @GetMapping
    public ResponseEntity<Student> get(@RequestParam int id) {
        Student gettingStudent = studentService.getStudent(id);
        if (gettingStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(gettingStudent);
    }

    @GetMapping("/faculty")
    public Faculty getFaculty(int studentId) {
        return studentService.getStudent(studentId).getFaculty();
    }

    @GetMapping("{age}")
    public ResponseEntity<Collection<Student>> getByAge(@RequestParam int age) {
        if (age >= 11) {
            return ResponseEntity.ok(studentService.getByAge(age));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    @GetMapping("/avg")
    public int findAVGAgeFromAllStudents(){
        return studentService.findAVGAgeFromAllStudents();
    }

    @GetMapping(path = "byage")
    public Collection<Student> getBetwAges(@RequestParam int minAge, @RequestParam int maxAge) {
        return studentService.getByAgeBetween(minAge, maxAge);
    }

    @GetMapping(path = "all")
    public Collection<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping(path = "/last5")
    public Collection<Student> getLast5Students() {
        return studentService.getLast5Students();
    }

    ///
    @PutMapping
    public ResponseEntity<Student> put(@RequestBody Student student) {
        Student putStudent = studentService.editStudent(student);
        if (putStudent == null) {
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
