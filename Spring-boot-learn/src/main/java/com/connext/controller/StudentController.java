package com.connext.controller;

import com.connext.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/list")
    public String findAll(Model model){
        model.addAttribute("studentList",studentService.findAll());
        return "student";
    }
}
