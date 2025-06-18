package kr.ac.kopo.leehanseock.webprogrammingfinal.controller;

import kr.ac.kopo.leehanseock.webprogrammingfinal.domain.StudyLog;
import kr.ac.kopo.leehanseock.webprogrammingfinal.domain.TaskItem;
import kr.ac.kopo.leehanseock.webprogrammingfinal.repository.StudyLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/edit_mode")
    public String showEditForm(Model model) {
        model.addAttribute("studyLog", new StudyLog());
        return "edit_mode";
    }

//    @PostMapping("/edit_mode")
//    public String saveLog(@ModelAttribute StudyLog studyLog) {
//        repo.save(studyLog);
//        return "redirect:/study_log";
//    }

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
        return "sign_in"; // sign_in.html 템플릿 반환
    }

    @PostMapping("/edit_mode")
    public String saveLog(
            @RequestParam String date,
            @RequestParam List<String> todayContent,
            @RequestParam(required=false) List<String> todayChecked,
            @RequestParam List<String> weekContent,
            @RequestParam(required=false) List<String> weekChecked
    ) {
        List<TaskItem> todayTasks = new ArrayList<>();
        for (int i = 0; i < todayContent.size(); i++) {
            boolean checked = todayChecked != null && todayChecked.size() > i && "true".equals(todayChecked.get(i));
            todayTasks.add(new TaskItem(todayContent.get(i), checked));
        }
        // weekContent도 동일하게
        List<TaskItem> weekTasks = new ArrayList<>();
        for (int i = 0; i < weekContent.size(); i++) {
            boolean checked = weekChecked != null && weekChecked.size() > i && "true".equals(weekChecked.get(i));
            weekTasks.add(new TaskItem(weekContent.get(i), checked));
        }

        StudyLog log = new StudyLog();
        log.setDate(LocalDate.parse(date));
        log.setTodayTasks(todayTasks);
        log.setWeeklyTasks(weekTasks);
        repo.save(log);
        return "redirect:/study_log";
    }


}