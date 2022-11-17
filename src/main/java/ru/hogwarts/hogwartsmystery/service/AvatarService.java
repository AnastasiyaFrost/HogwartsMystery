package ru.hogwarts.hogwartsmystery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.hogwartsmystery.model.Avatar;
import ru.hogwarts.hogwartsmystery.repository.AvatarRepository;

import javax.transaction.Transactional;
import java.util.Collection;

@Service
@Transactional
public class AvatarService {
    Logger logger = LoggerFactory.getLogger(AvatarService.class);
    @Value ("${avatars.dir.path}")
    private String avatarsDir;
    @Autowired
    private final StudentService studentService;
    private final AvatarRepository avatarRepository;

    public AvatarService(StudentService studentService, AvatarRepository avatarRepository) {
        this.studentService = studentService;
        this.avatarRepository = avatarRepository;
    }
    public Collection<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize) {
        logger.info("Was invoked method for get all avatars");
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        return avatarRepository.findAll(pageRequest).getContent();
    }
}
