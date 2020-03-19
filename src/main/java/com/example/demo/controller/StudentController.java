package com.example.demo.controller;

import com.example.demo.dao.StudentJdbc;
import com.example.demo.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class StudentController {
    private final StudentJdbc studentJdbc;

    public StudentController(StudentJdbc studentJdbc)
    {
        this.studentJdbc = studentJdbc;
    }

    @GetMapping("/student/{id}")
    public Student getStudent(@PathVariable int id)
    {
        return studentJdbc.get(id);
    }

    @GetMapping("/student/all")
    public List<Student> getAllStudents()
    {
        return studentJdbc.getAll();
    }

    @GetMapping("/student/group/{id}")
    public List<Student> getStudentsFromGroup(@PathVariable int id)
    {
        return studentJdbc.getAllByGroup(id);
    }

    @PostMapping("/student/new")
    public int addNewStudent(@RequestBody Student stud)
    {
        return studentJdbc.add(stud);
    }

    @PostMapping("/student/update")
    public int updateStudent(@RequestBody Student stud)
    {
        return studentJdbc.update(stud);
    }

    @DeleteMapping("/student/delete/{id}")
    public int deleteStudent(@PathVariable int id)
    {
        return studentJdbc.delete(id);
    }
}
