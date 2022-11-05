package ru.hogwarts.hogwartsmystery.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsmystery.model.Faculty;
import ru.hogwarts.hogwartsmystery.model.Student;
import ru.hogwarts.hogwartsmystery.repository.FacultyRepository;
import ru.hogwarts.hogwartsmystery.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository){
        this.facultyRepository=facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(int id) {
        return facultyRepository.findById(id).get();
    }

    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(int id) {
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAll() {
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getByColor(String color) {
        ArrayList<Faculty> result = new ArrayList<>();
        for (Faculty faculty : facultyRepository.findAll()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }
}
