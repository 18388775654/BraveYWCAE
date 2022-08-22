package com.yang.cae.modules.mapper.jpa;

import com.yang.cae.modules.entity.NameProfession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameProfessionJpa  extends JpaRepository<NameProfession, String> {
}
