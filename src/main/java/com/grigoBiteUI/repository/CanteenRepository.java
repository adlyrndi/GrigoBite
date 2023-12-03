package com.grigoBiteUI.repository;

import com.grigoBiteUI.model.CanteenList.Canteen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

@Repository
public interface CanteenRepository extends JpaRepository<Canteen, Long> {

    @NonNull
    List<Canteen> findAll();

    @NonNull
    Optional<Canteen> findById(Long id);

    @NonNull
    Canteen save(Canteen canteen);

    void deleteById(@NonNull Long id);
}
