package com.github.yumengliu.crypto.repository;

import com.github.yumengliu.crypto.model.BtcOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<BtcOrder, UUID> {

    @Query("SELECT o FROM BtcOrder o WHERE o.executed=false AND o.priceLimit >= :price")
    List<BtcOrder> findAllByNotExecutedAndLowerPriceLimit(@Param("price") double price);
}
