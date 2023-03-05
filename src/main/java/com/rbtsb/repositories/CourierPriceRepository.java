package com.rbtsb.repositories;

import com.rbtsb.entities.CourierPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourierPriceRepository extends JpaRepository<CourierPrice, Long> {
}
