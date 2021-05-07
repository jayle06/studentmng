package com.example.demo.controller;

import com.example.demo.model.Class;
import com.example.demo.model.Student;
import com.example.demo.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService) {
        this.classService = classService;
    }

    @GetMapping("/classes")
    public List<Class> getAllClass(){
        return classService.getAllClass();
    }

    @GetMapping("classes/{id}")
    public ResponseEntity<Class> getClassById(@PathVariable Long id){
        return classService.getClassById(id);
    }

    @GetMapping("classes/{classId}/students/{studentId}")
    public List<Student> getStudentInClass(@PathVariable Long classId, @PathVariable Long studentId){
        return classService.getStudentInClass(classId, studentId);
    }

    @GetMapping("classes/class-students/{id}")
    public List<Student> getStudentByClass(@PathVariable Long id){
        return classService.getStudentByClass(id);
    }

    @PostMapping("/classes")
    public Class createClass(@RequestBody Class aClass){
        return classService.createClass(aClass);
    }

    @PutMapping("/classes/{id}")
    public ResponseEntity<Class> updateClass(@PathVariable Long id, @RequestBody Class classDetail){
        return classService.updateClass(id, classDetail);
    }

    @DeleteMapping("/classes/{id}")
    public ResponseEntity<Map< String, Boolean >> deleteClass(@PathVariable Long id){
        return classService.deleteClass(id);
    }
}
