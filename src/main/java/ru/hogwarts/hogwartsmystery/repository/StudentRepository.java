package ru.hogwarts.hogwartsmystery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.hogwartsmystery.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Collection<Student> findAllByAge(int age);

    Collection<Student> findAllByAgeBetween(int minAge, int maxAge);
}
