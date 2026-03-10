package com.pooja.order_service.repository;

import com.pooja.order_service.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
   /* @Transactional
    @Modifying
 @Query(value="update orders set order_status=:status where id=:id",nativeQuery=true)
    public int CancelOrder(@Param("status") String status,@Param("id") Long id);*/
}

