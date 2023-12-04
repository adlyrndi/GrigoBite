package com.grigoBiteUI.repository;

import com.grigoBiteUI.model.CanteenList.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    
    List<Tenant> findByCanteenId(Long canteenId);

    Optional<Tenant> findByIdAndCanteenId(Long id, Long canteenId);
}
