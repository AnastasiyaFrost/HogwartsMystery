package ru.hogwarts.hogwartsmystery.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Faculty {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String color;
    @JsonIgnore
    @OneToMany(mappedBy = "faculty")
  private Set<Student> students;

    public Faculty(long id, String name, String color, Set<Student> students) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.students = students;
    }

    public Faculty() {

    }
 public Set<Student> getStudents() {return students;}
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

}
