package ru.hogwarts.hogwartsmystery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.hogwartsmystery.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    Collection<Faculty> findAllByColor(String color);

    Faculty findFacultyByNameOrColorIgnoreCase(String name, String color);
}