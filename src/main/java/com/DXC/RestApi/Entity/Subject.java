package com.DXC.RestApi.Entity;


import lombok.NoArgsConstructor;

import javax.persistence.*;

//one to many
//showing error bcoz not added no argument constructor now resolved

@Entity
@Table(name = "subject")
public class Subject {
     @Id
     @GeneratedValue(strategy=GenerationType.IDENTITY)
     @Column(name = "id")
    private Long id;
    @Column(name = "subject_name")
    private String subjectName;
    @Column(name = "marks_obtained")
    private Double marksObtained;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    //No Arg Constructor
    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public Double getMarksObtained() {
        return marksObtained;
    }

    public void setMarksObtained(Double marksObtained) {
        this.marksObtained = marksObtained;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject(Long id, String subjectName, Double marksObtained, Student student) {
        this.id = id;
        this.subjectName = subjectName;
        this.marksObtained = marksObtained;
        this.student = student;
    }
}
