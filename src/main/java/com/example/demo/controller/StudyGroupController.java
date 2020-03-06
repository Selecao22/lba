package com.example.demo.controller;

import com.example.demo.dao.StudyGroupJdbc;
import com.example.demo.model.StudyGroup;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudyGroupController
{
    private final StudyGroupJdbc studyGroupJdbc;

    public StudyGroupController(StudyGroupJdbc studyGroupJdbc)
    {
        this.studyGroupJdbc = studyGroupJdbc;
    }

    @GetMapping("/group/{id}")
    public StudyGroup getStudyGroup(@PathVariable int id)
    {
        return studyGroupJdbc.get(id);
    }

    @GetMapping("/group/all")
    public List<StudyGroup> getAllStudents()
    {
        return studyGroupJdbc.getAll();
    }

    @PostMapping("/group/new")
    public int addStudyGroup(@RequestBody StudyGroup sG)
    {
        return studyGroupJdbc.add(sG);
    }

    @PostMapping("/group/update")
    public int updateStudyGroup(@RequestBody StudyGroup sG)
    {
        return studyGroupJdbc.update(sG);
    }

    @DeleteMapping("/group/delete/{id}")
    public int deleteStudyGroup(@PathVariable int id)
    {
        return studyGroupJdbc.delete(id);
    }

}
