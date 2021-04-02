package com.incentive.managementsystem.Threshold;

import com.incentive.managementsystem.Incentive.Incentive;
import com.incentive.managementsystem.Threshold.Threshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThresholdRepository extends JpaRepository<Threshold, Integer> {

    @Query(value = "SELECT * FROM THRESHOLDS WHERE condition_id = ?1", nativeQuery = true)
    List<Threshold> getThresholdsByCondition(int condition_id);
}
