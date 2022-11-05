package ru.hogwarts.hogwartsmystery.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsmystery.model.Faculty;
import ru.hogwarts.hogwartsmystery.model.Student;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final HashMap<Integer, Faculty> faculties = new HashMap<>();
    private int count = 0;

    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(count++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty getFaculty(int id) {
        return faculties.get(id);
    }

    public Faculty editFaculty(int id, Faculty faculty) {
        if (!faculties.containsKey(id)) {
            return null;
        }
        faculties.put(id, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(int id) {
        return faculties.remove(id);
    }

    public Map<Integer, Faculty> getAll() {
        return faculties;
    }

    public Collection<Faculty> getByColor(String color) {
        ArrayList<Faculty> result = new ArrayList<>();
        for (Faculty faculty : faculties.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }
}
