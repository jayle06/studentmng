package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.IStudentRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class StudentService {
    private final IStudentRepository studentRepository;

    public StudentService(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public ResponseEntity<Student> getStudentById(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found!!!"));

        return ResponseEntity.ok(student);
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public ResponseEntity<Map< String, Boolean >> deleteStudent(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Not found!!!"));
        studentRepository.delete(student);
        Map < String, Boolean > response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<Student> updateStudent(Long id, Student studentDetail){
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Not found!!!"));
        student.setName(studentDetail.getName());
        student.setAddress(studentDetail.getAddress());
        student.setBirthday(studentDetail.getBirthday());
        student.setPhoneNumber(studentDetail.getPhoneNumber());

        Student updatedStudent = studentRepository.save(student);
        return ResponseEntity.ok(updatedStudent);
    }


//    public List<Object> test(String name) {
//        return studentRepository.findAll(
//                Specification.where((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name)))
//                .stream().collect(Collectors.toList());
//    }

}
