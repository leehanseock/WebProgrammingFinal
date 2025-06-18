package kr.ac.kopo.leehanseock.webprogrammingfinal.domain; // ← 본인 프로젝트 패키지에 맞게!

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class StudyLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    @ElementCollection
    private List<TaskItem> todayTasks;

    @ElementCollection
    private List<TaskItem> weeklyTasks;

    public StudyLog() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<TaskItem> getTodayTasks() {
        return todayTasks;
    }
    public void setTodayTasks(List<TaskItem> todayTasks) {
        this.todayTasks = todayTasks;
    }

    public List<TaskItem> getWeeklyTasks() {
        return weeklyTasks;
    }
    public void setWeeklyTasks(List<TaskItem> weeklyTasks) {
        this.weeklyTasks = weeklyTasks;
    }
}
