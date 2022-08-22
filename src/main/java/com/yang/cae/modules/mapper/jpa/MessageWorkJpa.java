package com.yang.cae.modules.mapper.jpa;

import com.yang.cae.modules.entity.MessageWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageWorkJpa  extends JpaRepository<MessageWork, String> {
}
