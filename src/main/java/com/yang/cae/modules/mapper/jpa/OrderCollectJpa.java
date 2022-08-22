package com.yang.cae.modules.mapper.jpa;

import com.yang.cae.modules.entity.OrderCollect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderCollectJpa  extends JpaRepository<OrderCollect, String> {

    List<OrderCollect> getByUserIdAndIsDeletedIsNull(String id);

    boolean existsByCollectMessageIdAndUserId(String message, String userId);
    OrderCollect getByCollectMessageIdAndUserId(String message, String userId);
}
