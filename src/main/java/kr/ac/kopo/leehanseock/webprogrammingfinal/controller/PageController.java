package kr.ac.kopo.leehanseock.webprogrammingfinal.controller;

import kr.ac.kopo.leehanseock.webprogrammingfinal.domain.StudyLog;
import kr.ac.kopo.leehanseock.webprogrammingfinal.domain.TaskItem;
import kr.ac.kopo.leehanseock.webprogrammingfinal.repository.StudyLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private StudyLogRepository repo;

    public PageController(StudyLogRepository repo) {
        this.repo = repo;
    }

    // ✅ [2번] 수정폼 띄우기 (id 파라미터로 기존데이터 조회)
    @GetMapping("/edit_mode")
    public String showEditForm(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            StudyLog log = repo.findById(id).orElse(new StudyLog());
            model.addAttribute("studyLog", log);
        } else {
            model.addAttribute("studyLog", new StudyLog()); // 무조건 studyLog를 넘김
        }
        return "edit_mode";
    }

    // ✅ [4번] 저장 (id 있으면 수정, 없으면 새로 생성)
    @PostMapping("/edit_mode")
    public String saveLog(
            @RequestParam(required = false) Long id,
            @RequestParam String date,
            @RequestParam List<String> todayContent,
            @RequestParam(required = false) List<String> todayChecked,
            @RequestParam List<String> weekContent,
            @RequestParam(required = false) List<String> weekChecked
    ) {
        StudyLog log;
        if (id != null) {
            log = repo.findById(id).orElse(new StudyLog());
        } else {
            log = new StudyLog();
        }
        log.setDate(LocalDate.parse(date));

        List<TaskItem> todayTasks = new ArrayList<>();
        for (int i = 0; i < todayContent.size(); i++) {
            boolean checked = todayChecked != null && todayChecked.contains(String.valueOf(i));
            todayTasks.add(new TaskItem(todayContent.get(i), checked));
        }
        List<TaskItem> weekTasks = new ArrayList<>();
        for (int i = 0; i < weekContent.size(); i++) {
            boolean checked = weekChecked != null && weekChecked.contains(String.valueOf(i));
            weekTasks.add(new TaskItem(weekContent.get(i), checked));
        }
        log.setTodayTasks(todayTasks);
        log.setWeeklyTasks(weekTasks);

        repo.save(log);
        return "redirect:/study_log";
    }

    // 기존 study_log, 메인, 로그인 등 기타 기능은 동일
    @GetMapping("/study_log")
    public String viewLogs(Model model) {
        model.addAttribute("logs", repo.findAll());
        return "study_log";
    }
    @GetMapping("/")
    public String main() {
        return "main";
    }
    @GetMapping("/sign_in")
    public String loginPage() {
        return "sign_in";
    }
}
