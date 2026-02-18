package com.tuition.new_tuition.service;

import com.tuition.new_tuition.entity.Student;
import com.tuition.new_tuition.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student register(Student student) {
        return repo.save(student);
    }

    public Student login(String email, String password) {
        Optional<Student> st = repo.findByEmail(email);

        if (st.isPresent() && st.get().getPassword().equals(password)) {
            return st.get();
        }

        return null;
    }
}
