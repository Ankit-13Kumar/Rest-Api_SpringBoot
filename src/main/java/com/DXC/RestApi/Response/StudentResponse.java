package com.DXC.RestApi.Response;



import com.DXC.RestApi.Entity.Student;
import com.DXC.RestApi.Entity.Subject;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
/*@Getter
@Setter*/
@Entity
public class StudentResponse {

    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    //if u don't want to populate id filed use jsonignore annotation
    //@JsonIgnore
    private long id;
    //if u want to chnage name in populate window use jsonproperty
    @JsonProperty("First_Name")
    private String firstName;
    private String lastName;
    //added for @transiant annotation step-2
    private String fullName;

    //added for entity relationship later one to one
    //getter setter imp
    private String street;
    private String city;

    //added many to one
    //need to check error now usd this annotation
  @ElementCollection(targetClass=Integer.class)
    private List<SubjectResponse>learningSubjects;

    public List<SubjectResponse> getLearningSubjects() {
        return learningSubjects;
    }

    public void setLearningSubjects(List<SubjectResponse> learningSubjects) {
        this.learningSubjects = learningSubjects;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    public StudentResponse() {
    }

    //all field constructor
    public StudentResponse(long id, String email, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    //getter setter
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    /*@Override
    public String toString() {
        return "StudentResponse{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }*/


    //after make changes in controller
    //data came from in getAll in student response not from student entity.
    public StudentResponse(Student student) {
        this.id = student.getId();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.email = student.getEmail();
        //added later 2 @Transiant annotation
        this.fullName = student.getFirstName() + " " + student.getLastName();
        //added later for entity relationship one to one mapping
        this.street = student.getAddress().getStreet();
        this.city = student.getAddress().getCity();


           //added many to one
        if (student.getLearningSubjects() != null) {
            learningSubjects = new ArrayList<SubjectResponse>();
            for (Subject subject: student.getLearningSubjects()) {
                learningSubjects.add(new SubjectResponse(subject));
            }
        }

    }
}
