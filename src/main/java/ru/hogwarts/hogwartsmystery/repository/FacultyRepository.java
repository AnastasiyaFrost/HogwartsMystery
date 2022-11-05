package ru.hogwarts.hogwartsmystery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.hogwartsmystery.model.Faculty;

public interface FacultyRepository extends JpaRepository<Faculty, Integer> {
}
