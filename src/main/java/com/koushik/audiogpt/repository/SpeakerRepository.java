package com.koushik.audiogpt.repository;

import com.koushik.audiogpt.entity.Speaker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    List<Speaker> findByPriceInrLessThanEqual(Integer budget);

    List<Speaker> findByBrand(String brand);

    List<Speaker> findByBluetooth(Boolean bluetooth);

    List<Speaker> findByActive(Boolean active);
}
