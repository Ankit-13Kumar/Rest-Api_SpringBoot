package com.DXC.RestApi.Controller;

import com.DXC.RestApi.Entity.Student;
import com.DXC.RestApi.Repository.StudentRepository;
import com.DXC.RestApi.Request.CreateStudentRequest;
import com.DXC.RestApi.Request.InQueryRequest;
import com.DXC.RestApi.Request.UpdateStudentRequest;
import com.DXC.RestApi.Response.StudentResponse;
import com.DXC.RestApi.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    //value annotation get value from application properties
    // and put it to appName and render it to return.
  /*  @Value("${app.name}")
    private String appName;*/


    // Testing
   @GetMapping("/get")
//@RequestMapping(value = "/get",method = RequestMethod.GET)
    //* public String getStudents(){*//*
    public StudentResponse getStudent() {
        StudentResponse studentResponse = new StudentResponse(1, "Ankit@gmail.com", "Ankit", "Kumar");
        //StudentResponse.set or get to check lambook working or not
//return "Hello Student";
        return studentResponse;
    }
        @Autowired
    StudentService studentService;
    @Autowired
    private StudentRepository studentRepository;

    //taking data from entity
    /*@GetMapping("/getAll")
        public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }*/


    //D/f ways
    //after make changes in controller
    //data came from in getAll in student response not from student entity.
    //in this way we are not exposing data, this is right way
    @GetMapping("/getAll")
    public List<StudentResponse> getAllStudents() {
        List<Student> studentList =studentService.getAllStudents();
        List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
         return studentResponseList;
    }
    //validation
    //json to object - request body annotation
    //getting message in terminal don't forget to add validation
    @PostMapping("/create")
    public StudentResponse createStudent (@Valid @RequestBody CreateStudentRequest createStudentRequest) {
        Student student = studentService.createStudent(createStudentRequest);

        return new StudentResponse(student);
    }

    //put mapping
    @PutMapping("/update")
    public StudentResponse updateStudent(@Valid @RequestBody UpdateStudentRequest updateStudentRequest){
     Student student=studentService.updateStudent(updateStudentRequest);
        return new StudentResponse(student);
    }

    //Delete Maping
    //requestparam is important here Param Meyhod
   /* @DeleteMapping("/delete")
    public String deleteStudent(@RequestParam long id){
     return studentService.deleteStudent(id);
    }*/

    //Path Variable Method
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable long id){
        return studentService.deleteStudent(id);
    }

    //get
    @GetMapping("/getFirstName/{firstName}")
    public List<StudentResponse> getByFirstName(@PathVariable String firstName){
        List<Student> studentList=studentService.getByFirstName(firstName);
//copy & paste list of student to list of response
        List<StudentResponse> studentResponseList=new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }
    // method proxy And
    @GetMapping("/getByFirstNameAndLastName/{firstName}/{lastName}")
    public StudentResponse getByFirstNameAndLastName(@PathVariable String firstName
            ,@PathVariable String lastName){
    return new StudentResponse(studentService.getByFirstNameAndLastName(firstName,lastName));
    }


    //Mthod Proxy OR
    //After go To service-1
@GetMapping("/getByFirstNameOrLastName/{firstName}/{lastName}")
public List<StudentResponse> getByFirstNameOrLastName(@PathVariable String firstName
        ,@PathVariable String lastName){
    //we have to convert this list into response
    List<Student> studentList = studentService.getByFirstNameOrLastName(firstName, lastName);
//copy and paster this
    List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
    studentList.stream().forEach(student -> {
        studentResponseList.add(new StudentResponse(student));
    });
    return studentResponseList;
}

//InQuery

    @GetMapping("/getByFirstNameIn")
    public List<StudentResponse> getByFirstNameIn (
            @RequestBody InQueryRequest inQueryRequest) {
        List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }
    //Pagination
    @GetMapping("/getAllWithPagination")
    public List<StudentResponse> getAllStudentsWithPagination (@RequestParam int pageNo,
                                                               @RequestParam int pageSize) {
        List<Student> studentList = studentService.
                getAllStudentsWithPagination(pageNo, pageSize);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }

//Sorting
    //there is no input data so we are not passing
    //step-1 After go to service layer
    @GetMapping("/getAllWithSorting")
    public List<StudentResponse> getAllStudentsWithSorting(){
        //copy and paste
        List<Student> studentList=studentService.getAllStudentsWithSorting();

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;

    }

    //Like Query step-1
    @GetMapping("like/{firstName}")
    public List<StudentResponse> like(@PathVariable String firstName) {
        List<Student> studentList = studentService.like(firstName);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }

    //start with query
    @GetMapping("/startsWith/{firstName}")
    public List<StudentResponse> startsWith(@PathVariable String firstName) {

        List<Student> studentList = studentService.startsWith(firstName);

        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();

        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });

        return studentResponseList;
    }

    //JPQL
    //jpql query into native query
    @GetMapping("/gettingByFirstNameOrLastName/{firstName}/{lastName}")
    public List<StudentResponse> gettingByFirstNameOrLastName(@PathVariable String firstName
            ,@PathVariable String lastName){
        //we have to convert this list into response
        List<Student> studentList = studentService.getByFirstNameOrLastName(firstName, lastName);
         //copy and paster this
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }

    //Update JPQL QUERY
    //message students updated
    @PutMapping("/updateFirstName/{id}/{firstName}")
    public String updateStudentWithJpql(@PathVariable Long id,@PathVariable String firstName){
        return studentService.updateStudentWithJpql(id,firstName)+" student(s) updated";
    }

    //Delete JPQL Query
    //step-1 after go to service layer
    @DeleteMapping("/deleteFirstName/{firstName}")
    public  String deleteStudent(@PathVariable String firstName){
        return studentService.deleteStudent(firstName) +" Student(s) Deleted Successfully";
    }

    //JOIN relationship
    //step-1 go to service
    @GetMapping("/getByCity/{city}")
    public List<StudentResponse> getByCity(@PathVariable String city) {
        List<Student> studentList = studentService.getByCity(city);
        List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
        studentList.stream().forEach(student -> {
            studentResponseList.add(new StudentResponse(student));
        });
        return studentResponseList;
    }

}