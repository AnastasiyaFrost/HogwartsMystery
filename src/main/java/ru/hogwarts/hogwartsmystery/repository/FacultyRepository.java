package ru.hogwarts.hogwartsmystery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.hogwartsmystery.model.Faculty;

import java.util.Collection;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
    Collection<Faculty> findAllByColor(String color);
}
