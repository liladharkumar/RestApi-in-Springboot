package com.RestApiAnuj.RestApiAnui.service;

import com.RestApiAnuj.RestApiAnui.Dto.AddStudentRequestDto;
import com.RestApiAnuj.RestApiAnui.Dto.StudentDto;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentById(int id);

    StudentDto createNewStudent(AddStudentRequestDto addStudentRequestDto);

    void deleteStudentById(Integer id);

    StudentDto updateStudent(Integer id, AddStudentRequestDto addStudentRequestDto);

    StudentDto updatePartailStudent(Integer id, Map<String, Object> update);
}
