package ru.hogwarts.hogwartsmystery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.hogwarts.hogwartsmystery.model.Avatar;
import ru.hogwarts.hogwartsmystery.service.AvatarService;

import java.util.Collection;

@RestController
public class AvatarController {
    private AvatarService avatarService;
    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }
    @GetMapping("/avatars")
    public ResponseEntity<Collection<Avatar>> getAllAvatars(@RequestParam("page") Integer pageNumber,
                                                            @RequestParam("size") Integer pageSize){
        Collection<Avatar> avatars = avatarService.getAllAvatars(pageNumber, pageSize);
        return ResponseEntity.ok(avatars);
    }
}
