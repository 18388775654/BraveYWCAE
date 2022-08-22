package com.yang.cae.modules.mapper.jpa;

import com.yang.cae.modules.entity.NameMajor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameMajorJpa  extends JpaRepository<NameMajor, String> {
}
