package com.example.demo.controller;

import com.example.demo.dao.JournalRecordJdbc;
import com.example.demo.model.JournalRecord;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JournalRecordController {
    private final JournalRecordJdbc journalRecordJdbc;

    public JournalRecordController(JournalRecordJdbc journalRecordJdbc)
    {
        this.journalRecordJdbc = journalRecordJdbc;
    }

    @GetMapping("/journal/{id}")
    public JournalRecord getJournalRecord(@PathVariable int id)
    {
        return journalRecordJdbc.get(id);
    }

    @GetMapping("/journal/all")
    public List<JournalRecord> getAllJournalRecords()
    {
        return journalRecordJdbc.getAll();
    }

    @GetMapping("/journal/student/{id}")
    public List<JournalRecord> getJournalRecordsByStudent(@PathVariable int id)
    {
        return journalRecordJdbc.getAllByStudent(id);
    }

    @GetMapping("/journal/study_plan/{id}")
    public List<JournalRecord> getJournalRecordsByStudyPlan(@PathVariable int id)
    {
        return journalRecordJdbc.getAllByStudyPlan(id);
    }

    @PostMapping("/journal/add")
    public int addNewRecord(@RequestBody JournalRecord jR)
    {
        return journalRecordJdbc.addJournalRecord(jR);
    }

    @PostMapping("/journal/update")
    public int updateRecord(@RequestBody JournalRecord jR)
    {
        return journalRecordJdbc.updateJournalRecord(jR);
    }

    @DeleteMapping("/journal/delete/{id}")
    public int deleteRecordById(@PathVariable int id)
    {
        return journalRecordJdbc.deleteJournalRecord(id);
    }
}
