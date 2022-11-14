package ru.hogwarts.hogwartsmystery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwarts.hogwartsmystery.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(long studentId);

}
