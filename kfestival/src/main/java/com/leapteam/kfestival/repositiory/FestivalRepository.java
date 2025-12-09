package com.leapteam.kfestival.repositiory;

import com.leapteam.kfestival.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FestivalRepository extends JpaRepository<FestivalEntity, Long> {
    List<FestivalEntity> findByAddr1(String keyword);

    @Query("""
SELECT f
FROM FestivalEntity f
WHERE PARSEDATETIME(f.eventStartDate, 'yyyyMMdd') <= :date
  AND PARSEDATETIME(f.eventEndDate, 'yyyyMMdd') >= :date
""")
    List<FestivalEntity> findBySingleDate(@Param("date") LocalDate date);

    @Query("""
SELECT f
FROM FestivalEntity f
WHERE (:location IS NULL OR f.addr1 LIKE %:location%)
  AND PARSEDATETIME(f.eventStartDate, 'yyyyMMdd') <= :date
  AND PARSEDATETIME(f.eventEndDate, 'yyyyMMdd') >= :date
""")
    List<FestivalEntity> findByTwoAtt(@Param("location") String location, @Param("date") LocalDate date);
}
