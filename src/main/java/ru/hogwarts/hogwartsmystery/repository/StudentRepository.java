package ru.hogwarts.hogwartsmystery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.hogwarts.hogwartsmystery.model.Student;

import java.util.Collection;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findAllByAge(int age);

    Collection<Student> findStudentsByName(String name);

    Collection<Student> findAllByAgeBetween(int minAge, int maxAge);
    @Query(value = "SELECT COUNT(id) FROM student", nativeQuery = true)
    int countAllFromSchool();

    @Query(value = "SELECT AVG(age) FROM student", nativeQuery = true)
    double findAVGAge();

    @Query(value = "SELECT * FROM student ORDER BY id DESC LIMIT 5", nativeQuery = true)
    Collection<Student> findLast5Students();
}
