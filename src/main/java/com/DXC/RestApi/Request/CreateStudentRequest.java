package com.DXC.RestApi.Request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;


public class CreateStudentRequest {
    @JsonProperty("first_name")
    @NotBlank(message = "FirstName is required")
    private String firstName;

    private String lastName;

    private String email;
    //ADDED LATER FOR ENTITY RELATIONSHIP one to one
    private String street;

    private String city;

    //added many to one
    private List<CreateSubjectRequest> subjectsLearning;

    public List<CreateSubjectRequest> getSubjectsLearning() {
        return subjectsLearning;
    }

    public void setSubjectLearning(List<CreateSubjectRequest> subjectLearning) {
        this.subjectsLearning = subjectLearning;
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
/*@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

}
