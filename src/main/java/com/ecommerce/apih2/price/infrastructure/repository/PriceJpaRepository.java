package com.ecommerce.apih2.price.infrastructure.repository;

import com.ecommerce.apih2.price.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PriceJpaRepository extends JpaRepository<PriceEntity, Long> {

    @Query("SELECT p " +
            "FROM PriceEntity p " +
            "WHERE p.startDate <= :priceApplicationDate " +
            "AND p.endDate >= :priceApplicationDate " +
            "AND p.productId = :productId " +
            "AND p.brandId = :brandId " +
            "ORDER BY p.priority DESC LIMIT 1" )
    Optional<PriceEntity> findPrice(@Param("priceApplicationDate") LocalDateTime priceApplicationDate,
                                        @Param("productId") Long productId,
                                        @Param("brandId") Integer brandId);

}
