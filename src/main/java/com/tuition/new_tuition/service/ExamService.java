package com.tuition.new_tuition.service;

import com.tuition.new_tuition.entity.Exam;
import com.tuition.new_tuition.repository.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    public Exam findById(Long id) {
        return examRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));
    }

    public void deleteById(Long id) {
        examRepository.deleteById(id);
    }
}
