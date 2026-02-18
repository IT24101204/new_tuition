package com.tuition.new_tuition.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "submission_answers")
public class SubmissionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "submission_id", nullable = false)
    private ExamSubmission submission;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    // store either selected option (A/B/C/D) OR essay text
    @Column(name="answer_text", columnDefinition = "nvarchar(max)")
    private String answerText;

    public Long getId() { return id; }

    public ExamSubmission getSubmission() { return submission; }
    public void setSubmission(ExamSubmission submission) { this.submission = submission; }

    public Question getQuestion() { return question; }
    public void setQuestion(Question question) { this.question = question; }

    public String getAnswerText() { return answerText; }
    public void setAnswerText(String answerText) { this.answerText = answerText; }
}
