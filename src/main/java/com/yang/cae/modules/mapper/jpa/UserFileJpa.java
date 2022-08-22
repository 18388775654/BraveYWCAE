package com.yang.cae.modules.mapper.jpa;


import com.yang.cae.modules.entity.UserFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFileJpa  extends JpaRepository<UserFile, String> {
}
