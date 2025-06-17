package kr.ac.kopo.leehanseock.webprogrammingfinal.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class StudyLog {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @Column(columnDefinition = "TEXT")
    private String comment;

    // JPA용 기본 생성자
    public StudyLog() {}

    public StudyLog(LocalDate date, String comment) {
        this.date = date;
        this.comment = comment;
    }

    // getter / setter
    public Long getId() { return id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}