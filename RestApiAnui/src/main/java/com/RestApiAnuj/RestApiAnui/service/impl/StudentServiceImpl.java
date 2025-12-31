package com.RestApiAnuj.RestApiAnui.service.impl;

import com.RestApiAnuj.RestApiAnui.Dto.AddStudentRequestDto;
import com.RestApiAnuj.RestApiAnui.Dto.StudentDto;
import com.RestApiAnuj.RestApiAnui.entity.Student;
import com.RestApiAnuj.RestApiAnui.repository.StudentRepository;
import com.RestApiAnuj.RestApiAnui.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream().map(student -> modelMapper.map(student,StudentDto.class))
//        List<StudentDto> studentDtoList = students.stream().map(student -> new StudentDto(student.getId(),student.getName(),student.getEmail()))
        .toList();
//        return studentDtoList;
    }

    @Override
    public StudentDto getStudentById(int id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("student not fount with id"));
        return modelMapper.map(student,StudentDto.class);
    }

    @Override
    public StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto) {
        Student newStudent = modelMapper.map(addStudentRequestDto ,Student.class);
        Student student = studentRepository.save(newStudent);
        return modelMapper.map(student,StudentDto.class);
    }



    @Override
    public void deleteStudentById(Integer id) {
        if(!studentRepository.existsById(id)){
            throw new RuntimeException("student does not exist by id:"+id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    public StudentDto updateStudent(Integer id, AddStudentRequestDto addStudentRequestDto) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("student not fount with id"));
        modelMapper.map(addStudentRequestDto , student);
        student = studentRepository.save(student);
        return modelMapper.map(student ,StudentDto.class);
    }

    @Override
    public StudentDto updatePartailStudent(Integer id, Map<String, Object> update) {
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("student not fount with id"));
        update.forEach((field, value)->{
            switch (field){
                case "name":
                    student.setName((String)value);
                    break;
                case "email":
                    student.setEmail((String)value);
                    break;
                default:
                    throw new IllegalArgumentException("field is not ");
            }
        });
        Student SaveStudent = studentRepository.save(student);
        return modelMapper.map(SaveStudent ,StudentDto.class);
    }

}
