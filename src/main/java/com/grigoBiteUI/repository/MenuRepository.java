package com.grigoBiteUI.repository;

import com.grigoBiteUI.model.CanteenList.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu findById(long id);
    List<Menu> findAllByTenantId(Long tenantId);

    Optional<Menu> findByIdAndTenantId(Long id, Long tenantId);

    List<Menu> findByTenantIdAndNamaContaining(Long tenantId, String keyword);

    List<Menu> findAllByTenantIdOrderByHargaDesc(Long tenantId);
}
