package com.yang.cae.modules.mapper.jpa;

import com.yang.cae.modules.entity.MessageCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageCertificateJpa extends JpaRepository<MessageCertificate, String> {
}
