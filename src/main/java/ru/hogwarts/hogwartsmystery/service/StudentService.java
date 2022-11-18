package ru.hogwarts.hogwartsmystery.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.hogwartsmystery.model.Avatar;
import ru.hogwarts.hogwartsmystery.model.Student;
import ru.hogwarts.hogwartsmystery.repository.AvatarRepository;
import ru.hogwarts.hogwartsmystery.repository.StudentRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE_NEW;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Value("${avatars.dir.path}")
    private String avatarsDir;
    private final StudentRepository studentRepository;
    private final AvatarRepository avatarRepository;

    public StudentService(StudentRepository studentRepository, AvatarRepository avatarRepository) {
        this.studentRepository = studentRepository;
        this.avatarRepository = avatarRepository;
    }

    public Student addStudent(Student student) {
        logger.info("Was invoked method for create student");

        Student newStudent = new Student();
        newStudent.setName(student.getName());
        newStudent.setAge(student.getAge());
        return studentRepository.save(newStudent);
    }

    public Student getStudent(long id) {
        logger.info("Was invoked method for get student by id");
        return studentRepository.findById(id).orElse(null);
    }

    public Student editStudent(Student student) {
        logger.info("Was invoked method for update student");
        if (!studentRepository.existsById(student.getId())) {
            logger.error("There is not student with id = " + student.getId());
            return null;
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student by id");
        studentRepository.deleteById(id);
    }

    public Collection<Student> getAll() {
        logger.info("Was invoked method for get all students");
        return studentRepository.findAll();
    }
    public Collection<Student> getLast5Students() {
        logger.info("Was invoked method for get last 5 students");
        return studentRepository.findLast5Students();
    }
    public int countTotalAmountStudents() {
        logger.info("Was invoked method for count total amount of students");
        return studentRepository.countAllFromSchool();
    }

    public Collection<Student> getByAge(int age) {
        logger.info("Was invoked method for get students by age");
        return new ArrayList<>(studentRepository.findAllByAge(age));
    }
    public Collection<Student> getByName(String name) {
        logger.info("Was invoked method for get students by name");
        return new ArrayList<>(studentRepository.findStudentsByName(name));
    }
    public List<String> getAllNamesStartsWithAInOrderInUpperCase() {
        logger.info("Was invoked method for get all names starts with A");
         return studentRepository.findAll().stream()
                .filter(e -> e.getName().startsWith("A"))
                .map(e -> e.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public double findAVGAgeFromAllStudents(){
        logger.info("Was invoked method for find average age from all students");
        //return studentRepository.findAVGAge();
        return studentRepository.findAll().stream()
                .mapToDouble(Student::getAge)
                .average()
                .getAsDouble();
    }

    public Collection<Student> getByAgeBetween(int minAge, int maxAge) {
        logger.info("Was invoked method for get students by age between min and max ages");
        return new ArrayList<>(studentRepository.findAllByAgeBetween(minAge, maxAge));
    }

    public Avatar findAvatar(long studentId) {
        logger.info("Was invoked method for find avatar by student`s id");
        return avatarRepository.findByStudentId(studentId).orElseThrow();
    }

    public void uploadAvatar(long studentId, MultipartFile file) throws IOException {
        logger.info("Was invoked method for upload avatar");
        Student student = getStudent(studentId);

        Path filePath = Path.of(avatarsDir, studentId + "." + getExtension(file.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);

        try (InputStream is = file.getInputStream();
             OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
             BufferedInputStream bis = new BufferedInputStream(is, 1024);
             BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }

        Avatar avatar = avatarRepository.findByStudentId(studentId).orElseGet(Avatar::new);
        avatar.setStudent(student);
        avatar.setFilePath(filePath.toString());
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());

        avatarRepository.save(avatar);
    }

    private String getExtension(String fileName) {
        logger.info("Was invoked method for get extension");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}
