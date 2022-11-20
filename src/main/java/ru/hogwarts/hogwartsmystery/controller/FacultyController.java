package ru.hogwarts.hogwartsmystery.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.hogwartsmystery.model.Faculty;
import ru.hogwarts.hogwartsmystery.model.Student;
import ru.hogwarts.hogwartsmystery.service.FacultyService;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }
    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }
    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFaculty(@RequestParam long id) {
        Faculty faculty = facultyService.getFaculty(id);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping ("/students")
    public Set<Student> getStudents(@RequestParam long facultyId) {return facultyService
            .getFaculty(facultyId).getStudents();}
    @GetMapping("/color")
    public ResponseEntity<Collection<Faculty>> getByColor(@RequestParam(required = false) String color) {
        if(!(color ==null)&&!(color.isBlank())) {
            return ResponseEntity.ok(facultyService.getByColor(color));
        }else {
            return ResponseEntity.ok(Collections.emptyList());
        }
    }
    @GetMapping("/longest_name")
    public ResponseEntity<String> getLongestName() {
        return ResponseEntity.ok(facultyService.getLongestName());
    }
    @GetMapping
    public ResponseEntity<Faculty> getByNameOrColorIgnoreCase (String name, String color){
        if(name==null&&color==null){return ResponseEntity.badRequest().build();}
        Faculty findFaculty = facultyService.getByNameOrColorIgnoreCase(name, color);
        if(findFaculty==null) {return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(findFaculty);
    }
    @GetMapping (path = "/all")
    public Collection <Faculty> getAll() {
        return facultyService.getAll();
    }
    @PutMapping
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
