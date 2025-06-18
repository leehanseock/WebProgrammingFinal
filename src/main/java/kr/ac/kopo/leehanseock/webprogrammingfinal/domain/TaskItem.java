package kr.ac.kopo.leehanseock.webprogrammingfinal.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class TaskItem {
    private String content;
    private boolean checked;

    // 기본 생성자 필수!
    public TaskItem() {}
    public TaskItem(String content, boolean checked) {
        this.content = content;
        this.checked = checked;
    }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public boolean isChecked() { return checked; }
    public void setChecked(boolean checked) { this.checked = checked; }
}