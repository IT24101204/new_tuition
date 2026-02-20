package com.tuition.new_tuition.controller;

import com.tuition.new_tuition.entity.Exam;
import com.tuition.new_tuition.service.ExamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/exams")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    // LIST
    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("exams", examService.findAll());
        return "exam-list";
    }

    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("exam", new Exam());
        return "exam-add";
    }

    // SAVE NEW
    @PostMapping("/save")
    public String saveExam(@ModelAttribute("exam") Exam exam) {
        examService.save(exam);
        return "redirect:/exams/list";
    }

    // SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("exam", examService.findById(id));
        return "exam-edit";
    }


    // UPDATE
    @PostMapping("/update")
    public String update(@ModelAttribute("exam") Exam exam) {
        examService.save(exam);
        return "redirect:/exams/list";
    }


    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        examService.deleteById(id);
        return "redirect:/exams/list";
    }
    @GetMapping
    public String home() {
        return "redirect:/exams/list";
    }
}
