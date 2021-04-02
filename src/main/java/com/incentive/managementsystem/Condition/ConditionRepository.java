package com.incentive.managementsystem.Condition;

import com.incentive.managementsystem.Incentive.Incentive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConditionRepository extends JpaRepository<Condition, Integer> {

    @Query(value = "SELECT * FROM CONDITIONS WHERE incentive_id = ?1", nativeQuery = true)
    List<Condition> getConditionsByIncentive(int incentiveID);
}
