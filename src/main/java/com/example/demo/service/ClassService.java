package com.example.demo.service;

import com.example.demo.model.Class;
import com.example.demo.model.Student;
import com.example.demo.repository.IClassRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClassService {
    private final IClassRepository classRepository;


    public ClassService(IClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public List<Class> getAllClass(){
        return classRepository.findAll();
    }

    public List<Student> getStudentByClass(Long id){
        Class aClass = classRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Not found!!!"));
        return aClass.getStudents();
    }

    public List<Student> getStudentInClass(Long classId, Long studentId){
        Class aClass = classRepository.findById(classId)
                .orElseThrow(()-> new IllegalArgumentException("Not found!!!"));
        return aClass.getStudents().stream().filter(id -> id.getId().equals(studentId)).collect(Collectors.toList());
    }

    public ResponseEntity<Class> getClassById(Long id){
        Class aClass = classRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found!!!"));

        return ResponseEntity.ok(aClass);
    }

    public Class createClass(Class aClass){
        return classRepository.save(aClass);
    }

    public ResponseEntity<Map< String, Boolean >> deleteClass(Long id){
        Class aClass = classRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Not found!!!"));
        classRepository.delete(aClass);
        Map < String, Boolean > response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @Transactional
    public ResponseEntity<Class> updateClass(Long id, Class classDetail){
        Class aClass = classRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("Not found!!!"));

        aClass.setName(classDetail.getName());
        aClass.setStudents(classDetail.getStudents());

        Class updatedClass = classRepository.save(aClass);
        return ResponseEntity.ok(updatedClass);
    }
}
