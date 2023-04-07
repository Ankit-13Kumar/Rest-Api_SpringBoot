package com.DXC.RestApi.Service;

import com.DXC.RestApi.Entity.Address;
import com.DXC.RestApi.Entity.Student;
import com.DXC.RestApi.Entity.Subject;
import com.DXC.RestApi.Repository.AddressRepository;
import com.DXC.RestApi.Repository.StudentRepository;
import com.DXC.RestApi.Repository.SubjectRepository;
import com.DXC.RestApi.Request.CreateStudentRequest;
import com.DXC.RestApi.Request.CreateSubjectRequest;
import com.DXC.RestApi.Request.InQueryRequest;
import com.DXC.RestApi.Request.UpdateStudentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;


import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    //Business Logic
    @Autowired
    StudentRepository studentRepository;

    //added later for entity relationship
    @Autowired
    AddressRepository addressRepository;

    //added of many to one
    @Autowired
    private SubjectRepository subjectRepository;




    //Native Query
   /* public void getAllStudents(){
        studentRepository.findAll();
    }

    */
    //For List of users
    //get users
    public List<Student> getAllStudents(){
       return studentRepository.findAll();
    }
    //add users
    public Student createStudent(CreateStudentRequest createStudentRequest){
   Student student=new Student(createStudentRequest);
   //added later for entity relation ship one to one mapping
        Address address=new Address();
        address.setStreet(createStudentRequest.getStreet());
        address.setCity(createStudentRequest.getCity());
        addressRepository.save(address);
        student.setAddress(address);

   student =studentRepository.save(student);

        //added later many to one
        List<Subject> subjectsList = new ArrayList<Subject>();

        if(createStudentRequest.getSubjectsLearning() != null) {
            for (CreateSubjectRequest createSubjectRequest :
                    createStudentRequest.getSubjectsLearning()) {
                Subject subject = new Subject();
                subject.setSubjectName(createSubjectRequest.getSubjectName());
                subject.setMarksObtained(createSubjectRequest.getMarksObtained());
                subject.setStudent(student);
                subjectsList.add(subject);
            }
            subjectRepository.saveAll(subjectsList);
        }
        student.setLearningSubjects(subjectsList);



   return student;
    }
    //Update Users
//coming data from controller, I have passed here in bracket.
    public Student updateStudent(UpdateStudentRequest updateStudentRequest){
        //return optional of entity class last get()
       Student student=studentRepository.findById(updateStudentRequest.getId()).get();
       //check
        if (updateStudentRequest.getFirstName()!=null && !updateStudentRequest.getFirstName()
                .isEmpty()){
            student.setFirstName(updateStudentRequest.getFirstName());
        }
        student=studentRepository.save(student);
        return student;
    }

    //Delete API
public String deleteStudent(long id){
        studentRepository.deleteById(id);
        return "Student Deleted Successfully";
}
//Query
    //after go to controller
    public List<Student>getByFirstName(String firstName){
        return studentRepository.findByFirstName(firstName);
    }
    //And proxy order noted
    public Student getByFirstNameAndLastName(String firstName,String lastName){
     return studentRepository.findByLastNameAndFirstName(lastName,firstName);
    }


    //Or proxy order noted after go to repository-2
    public List<Student> getByFirstNameOrLastName(String firstName,String lastName){
//Step-4
        //After go to controller
        return studentRepository.findByFirstNameOrLastName(firstName,lastName);
    }

    //InQuery
    public List<Student> getByFirstNameIn (InQueryRequest inQueryRequest) {
        return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
    }
    //Pagination
    //showing error resolve when we added import org.springframework.data.domain.Pageable;
    public List<Student> getAllStudentsWithPagination (int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return studentRepository.findAll(pageable).getContent();
    }
    //Sorting
    // Step-2
    public List<Student>getAllStudentsWithSorting(){
//        Sort sort= Sort.by(Sort.Direction.ASC,"firstName");
        //we can pass multiple field here in properties
        Sort sort= Sort.by(Sort.Direction.ASC,"firstName","lastName","email");

        return studentRepository.findAll(sort);
    }

    //like query step-2
    public List<Student>like(String firstName){
//step-4
        return studentRepository.findByFirstNameContains(firstName);
    }

    //start with query
    public List<Student> startsWith(String firstName) {
        return studentRepository.findByFirstNameStartsWith(firstName);
    }

      //JPQL select
      public Student gettingByFirstNameAndLastName (String firstName, String lastName) {
          return (Student) studentRepository.gettingByLastNameAndFirstName(lastName, firstName);
      }
      //JPQL Update
      public Integer updateStudentWithJpql (Long id, String firstName) {
        return studentRepository.updateFirstName(id, firstName);
    }
    //jpql delete
    public Integer deleteStudent(String firstName){
        //after go to repository step-2
        //step-4 came from repository
        return studentRepository.deleteByFirstName(firstName);
    }

    //step-2 join relationship
    //step-3 go to student repository
    public List<Student> getByCity (String city) {
        //step-5
        //return studentRepository.findByAddressCity(city);

        //added later for jpql json
        return studentRepository.getByAddressCity(city);
    }
}


