package com.example.latepractice.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('student_read')")
    public List<Student> getAllStudents(){

        return  studentService.getAllStudentsService();
    }

    @GetMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student_read')")
    public Student getStudent(@PathVariable ("studentId") long studentId){
        return studentService.getStudentService(studentId);
    }
    @PostMapping
    @PreAuthorize("hasAuthority('student_write')")
    public void registerStudent( @Valid @RequestBody  Student student) throws IllegalStateException{

        studentService.registerStudentService(student);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student_write')")
    public void updateStudent(
            @PathVariable("studentId") long studentId,
            @RequestParam(required = false) String studentName,
            @RequestParam(required = false) Integer studentAge,
            @RequestParam(required = false) String studentAddress,
            @RequestParam(required = false) String studentEmail
    ){
        studentService.updateStudentService(studentId,studentAddress,studentAge,studentEmail,studentName);
    }

   @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") long studentId){

        studentService.deleteStudentService(studentId);
   }


}
