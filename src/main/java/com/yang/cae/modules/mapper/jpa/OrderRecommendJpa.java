package com.yang.cae.modules.mapper.jpa;

import com.yang.cae.modules.entity.OrderRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRecommendJpa  extends JpaRepository<OrderRecommend, String> {


}
