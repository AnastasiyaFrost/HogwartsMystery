package ru.hogwarts.hogwartsmystery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsmystery.FacultyNotFoundException;
import ru.hogwarts.hogwartsmystery.model.Faculty;
import ru.hogwarts.hogwartsmystery.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {
    Logger logger = LoggerFactory.getLogger(FacultyService.class);
    private FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository){
        this.facultyRepository=facultyRepository;
    }

    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for add faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        logger.info("Was invoked method for get faculty by id");
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for update faculty");
        if(!facultyRepository.existsById(faculty.getId())) {
            logger.error("There is not faculty with id = " + faculty.getId());
            return null;
        } else return facultyRepository.save(faculty);

    }

    public void deleteFaculty(long id) {
        logger.info("Was invoked method for delete faculty by id");
        facultyRepository.deleteById(id);
    }

    public Collection<Faculty> getAll() {
        logger.info("Was invoked method for get all faculties");
        return facultyRepository.findAll();
    }

    public Collection<Faculty> getByColor(String color) {
        logger.info("Was invoked method for get faculties by color");
        return new ArrayList<>(facultyRepository.findAllByColor(color));
    }

    public Faculty getByNameOrColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method for get faculty by name or color ignore case");
        return facultyRepository.findFacultyByNameOrColorIgnoreCase(name, color);
    }

    public String getLongestName() {
        logger.info("Was invoked method for get longest faculty`s name");
        Faculty findFaculty = facultyRepository.findAll().stream()
                .max(Comparator.comparingInt(e -> e.getName().length()))
                .orElseThrow(FacultyNotFoundException::new);
        return findFaculty.getName();
    }
}
