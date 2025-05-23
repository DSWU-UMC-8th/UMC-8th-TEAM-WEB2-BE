package umc.reviewinclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.reviewinclass.domain.lecture.Lecture;

import java.util.List;

public interface LectureRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findByNameContaining(String name);

}

