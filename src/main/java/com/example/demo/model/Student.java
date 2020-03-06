package com.example.demo.model;

public class Student
{
    private int id;
    private Integer studyGroupId;
    private String surName;
    private String name;
    private String secondName;

    public Student(Integer id, Integer studyGroupId, String surName, String name, String secondName)
    {
        this.id = id;
        this.studyGroupId = studyGroupId;
        this.surName = surName;
        this.name = name;
        this.secondName = secondName;
    }

    public Student()
    {
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Integer getStudyGroupId()
    {
        return studyGroupId;
    }

    public void setStudyGroupId(Integer studyGroupId)
    {
        this.studyGroupId = studyGroupId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurName()
    {
        return surName;
    }

    public void setSurName(String surName)
    {
        this.surName = surName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public void setSecondName(String secondName)
    {
        this.secondName = secondName;
    }

}
