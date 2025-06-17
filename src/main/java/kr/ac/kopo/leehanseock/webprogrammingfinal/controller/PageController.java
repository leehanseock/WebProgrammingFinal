package kr.ac.kopo.leehanseock.webprogrammingfinal.controller;

import kr.ac.kopo.leehanseock.webprogrammingfinal.domain.StudyLog;
import kr.ac.kopo.leehanseock.webprogrammingfinal.repository.StudyLogRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {
    private final StudyLogRepository repo;

    public PageController(StudyLogRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/edit_mode")
    public String showEditForm(Model model) {
        model.addAttribute("studyLog", new StudyLog());
        return "edit_mode";
    }

    @PostMapping("/edit_mode")
    public String saveLog(@ModelAttribute StudyLog studyLog) {
        repo.save(studyLog);
        return "redirect:/study_log";
    }

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



}