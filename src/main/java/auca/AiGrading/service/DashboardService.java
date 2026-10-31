package auca.AiGrading.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.AiGrading.enums.SubmissionStatus;
import auca.AiGrading.repository.CourseRepository;
import auca.AiGrading.repository.ExaminerRepository;
import auca.AiGrading.repository.RubricScoreRepository;
import auca.AiGrading.repository.StudentRepository;
import auca.AiGrading.repository.SubmissionRepository;

@Service 
public class DashboardService {
    @Autowired private StudentRepository studentRepo;
    @Autowired private ExaminerRepository examinerRepo;
    @Autowired private CourseRepository courseRepo;
    @Autowired private SubmissionRepository submissionRepo;
    @Autowired private RubricScoreRepository rubricScoreRepo;

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("students", studentRepo.count());
        summary.put("examiners", examinerRepo.count());
        summary.put("freeCourses", (long) courseRepo.findFreeCourses().size());
        summary.put("SubmissionsToday", submissionRepo.countBySubmittedDate(LocalDate.now()));
        summary.put("uploaded", submissionRepo.countByStatusEquals(SubmissionStatus.UPLOADED));
        summary.put("rubricScores", rubricScoreRepo.count());

        return summary;
    }
}
