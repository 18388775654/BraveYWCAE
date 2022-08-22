package com.yang.cae.modules.mapper.jpa;

import com.yang.cae.modules.entity.MessageExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageExamJpa extends JpaRepository<MessageExam, String>{
}
