package auca.AiGrading.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import auca.AiGrading.enums.SubmissionStatus;
import auca.AiGrading.model.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
    List<Submission> findByStatusOrderBySubmittedDateAsc(SubmissionStatus status);
    List<Submission> findBySubmittedDateBetweenOrderBySubmittedDateAsc(LocalDate start, LocalDate end);

    boolean existsByExaminerIdAndSubmittedDateAndStatusNot(UUID examinerId, LocalDate submittedDate, SubmissionStatus status);

    @Query("SELECT s.status, COUNT(s) FROM Submission s GROUP BY s.status")
    List<Object[]> countByStatus();
    
    @Modifying
    @Query ("UPDATE Submission s SET s.status = :newStatus " +
        "WHERE s.examiner.id = :examinerId " +
        "AND s.submittedDate = :date " +
        "AND s.status <> :excluded"
    )
    int cancelDay(@Param ("examinerId") UUID examinerId,
                    @Param ("date") LocalDate date,
                    @Param ("newStatus") SubmissionStatus status,
                    @Param ("excluded") SubmissionStatus excluded);

        long countBySubmittedDate(LocalDate submittedDate);
        long countByStatusEquals(SubmissionStatus status);
    }
