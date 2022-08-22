package com.yang.cae.modules.mapper.jpa;

import com.yang.cae.modules.entity.Advertise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvertiseJpa extends JpaRepository<Advertise, String> {
}
