package kr.ac.kopo.leehanseock.webprogrammingfinal.repository;

import kr.ac.kopo.leehanseock.webprogrammingfinal.domain.StudyLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyLogRepository extends JpaRepository<StudyLog, Long> {
}