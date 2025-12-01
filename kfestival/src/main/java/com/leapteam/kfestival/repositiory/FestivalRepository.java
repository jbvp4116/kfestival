package com.leapteam.kfestival.repositiory;

import com.leapteam.kfestival.entity.FestivalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalRepository extends JpaRepository<FestivalEntity, Long> {
}
