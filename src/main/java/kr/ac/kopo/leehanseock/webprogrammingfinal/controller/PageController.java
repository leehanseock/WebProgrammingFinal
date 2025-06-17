package kr.ac.kopo.leehanseock.webprogrammingfinal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @GetMapping("/study_log")
    public String studyLog() {
        return "study_log";
    }

    @GetMapping("/edit_mode")
    public String editMode() {
        return "edit_mode";
    }

    @GetMapping("/sign_in")
    public String loginPage() {
        return "sign_in"; // sign_in.html 템플릿 반환
    }
}