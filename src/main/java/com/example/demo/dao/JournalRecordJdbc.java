package com.example.demo.dao;

import com.example.demo.model.JournalRecord;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class JournalRecordJdbc
{
    private final JdbcTemplate jdbcTemplate;

    public JournalRecordJdbc(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JournalRecord get(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM \"journal\" WHERE \"id\" = ?", this::mapJournalRecord, id);
    }

    public List<JournalRecord> getAll()
    {
        return jdbcTemplate.queryForObject("SELECT * FROM \"journal\"", this::mapAllJournalRecords);
    }

    public List<JournalRecord> getAllByStudent(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM \"journal\" WHERE \"student_id\" = ?", this::mapAllJournalRecords, id);
    }

    public List<JournalRecord> getAllByStudyPlan(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM \"journal\" WHERE \"study_plan_id\" = ?", this::mapAllJournalRecords, id);
    }

    public int addJournalRecord(@NotNull JournalRecord jR)
    {
        return jdbcTemplate.update("INSERT INTO \"journal\" (\"student_id\", \"study_plan_id\", \"in_time\", \"count\", \"mark_id\") VALUES (?, ?, ?, ?, ?)", jR.getStudentId(),
                jR.getStudyPlanId(), jR.isInTime(), jR.getCount(), jR.getMarkId());
    }

    public int updateJournalRecord(@NotNull JournalRecord jR)
    {
        StringBuilder sqlQuery = new StringBuilder("UPDATE \"journal\" SET ");

        if (jR.getStudentId() != null) sqlQuery.append("student_id = '").append(jR.getStudentId()).append("', ");
        if (jR.getStudyPlanId() != null) sqlQuery.append("study_plan_id = '").append(jR.getStudyPlanId()).append("', ");
        if (jR.isInTime() != null) sqlQuery.append("in_time = '").append(jR.isInTime()).append("', ");
        if (jR.getCount() != null) sqlQuery.append("count = '").append(jR.getCount()).append("', ");
        if (jR.getMarkId() != null) sqlQuery.append("mark_id = ").append(jR.getMarkId()).append("' ");
        else if (sqlQuery.charAt(sqlQuery.length() - 2) == ',') sqlQuery.deleteCharAt(sqlQuery.length() - 2);
        sqlQuery.append("WHERE id = ?");

        return jdbcTemplate.update(sqlQuery.toString(), jR.getId());
    }

    public int deleteJournalRecord(int id)
    {
        return jdbcTemplate.update("DELETE FROM \"journal\" WHERE \"id\" = ?", id);
    }

    private JournalRecord mapJournalRecord(@NotNull ResultSet rs, int i) throws SQLException
    {
        return new JournalRecord(
                rs.getInt("id"),
                rs.getInt("student_id"),
                rs.getInt("study_plan_id"),
                rs.getBoolean("in_time"),
                rs.getInt("count"),
                rs.getInt("mark_id")
        );
    }

    private List<JournalRecord> mapAllJournalRecords(@NotNull ResultSet rs, int i) throws SQLException
    {
        List<JournalRecord> journalRecordList = new ArrayList<>();

        do
        {
            journalRecordList.add(new JournalRecord(
                    rs.getInt("id"),
                    rs.getInt("student_id"),
                    rs.getInt("study_plan_id"),
                    rs.getBoolean("in_time"),
                    rs.getInt("count"),
                    rs.getInt("mark_id")));
        }
        while (rs.next());

        return journalRecordList;
    }
}
