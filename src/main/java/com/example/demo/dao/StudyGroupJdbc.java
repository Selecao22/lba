package com.example.demo.dao;

import com.example.demo.model.StudyGroup;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudyGroupJdbc
{
    private final JdbcTemplate jdbcTemplate;

    public StudyGroupJdbc(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    public StudyGroup get(int id)
    {
        return jdbcTemplate.queryForObject("SELECT * FROM \"study_group\" WHERE \"id\" = ?", this::mapStudyGroup, id);
    }

    public List<StudyGroup> getAll()
    {
        return jdbcTemplate.queryForObject("SELECT * FROM \"study_group\"", this::mapAllStudyGroups);
    }

    public int add(@NotNull StudyGroup sG)
    {
        return jdbcTemplate.update("INSERT INTO \"study_group\" (\"name\") VALUES (?)", sG.getName());
    }

    public int update(@NotNull StudyGroup sG)
    {
        return jdbcTemplate.update("UPDATE \"study_group\" SET \"name\" = ? WHERE \"id\" = ?", sG.getName(), sG.getId());
    }

    public int delete(int id)
    {
        return jdbcTemplate.update("DELETE FROM \"study_group\" WHERE \"id\" = ?", id);
    }

    private StudyGroup mapStudyGroup(@NotNull ResultSet rs, int i) throws SQLException
    {
        return new StudyGroup(
                rs.getInt("id"),
                rs.getString("name")
        );
    }

    private List<StudyGroup> mapAllStudyGroups(@NotNull ResultSet rs, int i) throws SQLException
    {
        List<StudyGroup> groupList = new ArrayList<>();

        do
        {
            groupList.add(new StudyGroup(rs.getInt("id"),
                    rs.getString("name")));
        }
        while (rs.next());

        return groupList;
    }
}