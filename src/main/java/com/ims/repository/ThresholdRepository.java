package com.ims.repository;

import com.ims.beans.Threshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ThresholdRepository extends JpaRepository<Threshold, Integer> {

    @Query(value = "SELECT * FROM incentive.thresholds WHERE condition_id = ?1", nativeQuery = true)
    List<Threshold> getThresholdsByCondition(int condition_id);

    @Query(value = "SELECT t.parameter_name \n" +
            "FROM incentive.incentives i join incentive.conditions c\n" +
            "on i.id = c.incentive_id\n" +
            "join incentive.thresholds t on t.condition_id = c.id\n" +
            "where i.id = ?1", nativeQuery = true)
    List<String> getThresholdsByIncentive(int incentive_id);
}
