package ru.hogwarts.hogwartsmystery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsmystery.repository.AvatarRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class AvatarService {
    @Value ("${avatars.dir.path}")
    private String avatarsDir;
    @Autowired
    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }
    //public void uploadAvatar(int studentId)
}
