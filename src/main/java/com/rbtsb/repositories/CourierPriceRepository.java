package com.rbtsb.repositories;

import com.rbtsb.entities.CourierPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourierPriceRepository extends JpaRepository<CourierPrice, Long> {

    @Query(value = """
            SELECT cp.* FROM COURIER_PRICE AS cp WHERE
            (:originPostcode IS NULL OR :originPostcode = cp.ORIGIN_POSTCODE) AND
            (:destinationCountry IS NULL OR LOWER(:destinationCountry) = LOWER(cp.DESTINATION_COUNTRY)) AND
            (:destinationPostcode IS NULL OR :destinationPostcode = cp.DESTINATION_POSTCODE) AND
            (:width IS NULL OR :width = cp.WIDTH) AND
            (:height IS NULL OR :height = cp.HEIGHT) AND
            (:parcelWeight IS NULL OR :parcelWeight = cp.PARCEL_WEIGHT)
            """,
            nativeQuery = true)
    List<CourierPrice> search(Integer originPostcode,
                              String destinationCountry,
                              Integer destinationPostcode,
                              Double width,
                              Double height,
                              Double parcelWeight);
}
