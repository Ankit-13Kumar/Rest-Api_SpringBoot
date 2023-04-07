package com.DXC.RestApi.Entity;

import com.DXC.RestApi.Request.CreateStudentRequest;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
   /* @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)*/
    //Auto increment id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_Name")

    private String firstName;
    @Column(name = "last_Name")

    private String lastName;
    @Column(name = "email")
    private String email;

    //@Transiant annotation if not using this annotation giving error bcoz we added letter
    //it means they are not any column in database
    @Transient
    private String fullName;

    //Added for entity relationship
    //foreignkey
    //they fire only one query at a time
   //
    // @OneToOne(fetch=FetchType.LAZY)
   @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;
    //getter setter important

    //one TO many
    @OneToMany(mappedBy = "student")
    private List<Subject> learningSubjects;

    public List<Subject> getLearningSubjects() {
        return learningSubjects;
    }

    public void setLearningSubjects(List<Subject> learningSubjects) {
        this.learningSubjects = learningSubjects;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



   /* @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }*/





    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //default constructor
    public Student() {
    }

    //all agr constructor , default arg constructor java provide automatically
   /* public Student(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }*/


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //one constructor , parameterized Constructor
    public Student (CreateStudentRequest createStudentRequest){
     this.firstName=createStudentRequest.getFirstName();
     this.lastName=createStudentRequest.getLastName();
     this.email=createStudentRequest.getEmail();
     //added later for @transiant annotation
        //after go to response
        this.fullName=createStudentRequest.getFirstName()+" "+ createStudentRequest.getLastName();
    }

}