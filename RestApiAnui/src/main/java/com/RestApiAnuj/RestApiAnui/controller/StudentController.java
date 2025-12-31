package com.RestApiAnuj.RestApiAnui.controller;

import com.RestApiAnuj.RestApiAnui.Dto.AddStudentRequestDto;
import com.RestApiAnuj.RestApiAnui.Dto.StudentDto;
import com.RestApiAnuj.RestApiAnui.entity.Student;
import com.RestApiAnuj.RestApiAnui.repository.StudentRepository;
import com.RestApiAnuj.RestApiAnui.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stu")
public class StudentController {

    private final StudentService studentService;


    @GetMapping()
    public ResponseEntity<List<StudentDto>> getAllStudents(){
//        return ResponseEntity.status(HttpStatus.OK).body(studentService.getAllStudents());
        return ResponseEntity.ok(studentService.getAllStudents());
    }
    @GetMapping("/students")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable int id){
//        return new StudentDto(43,"krishn","krishm454@gmail.com");
        return ResponseEntity.ok(studentService.getStudentById(id));

    }
//    @PostMapping("/student")
//    public ResponseEntity<StudentDto> createNewStudent(@RequestBody AddStudentRequestDto addStudentRequestDto){
//        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createNewStudent(addStudentRequestDto));
//    }
    @PostMapping("/student")
    public ResponseEntity<StudentDto> createNewStudent(@RequestBody @Valid AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(studentService.createNewStudent(addStudentRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDto> deleteStudent(@PathVariable Integer id){
        studentService.deleteStudentById(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Integer id,@RequestBody AddStudentRequestDto addStudentRequestDto){
        return ResponseEntity.ok(studentService.updateStudent(id ,addStudentRequestDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<StudentDto> updatePartialStudent(@PathVariable Integer id, @RequestBody Map<String , Object> update){
        return ResponseEntity.ok(studentService.updatePartailStudent(id ,update));
    }



}
