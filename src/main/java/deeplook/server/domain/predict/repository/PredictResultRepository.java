package deeplook.server.domain.predict.repository;

import deeplook.server.domain.predict.entity.PredictResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PredictResultRepository extends JpaRepository<PredictResult,Long> {

    List<PredictResult> findByAuthor_Id(Long userId);
}
