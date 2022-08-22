package com.yang.cae.modules.service;

import com.yang.cae.modules.dto.DataDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollectService {
    public List<DataDTO> getCollect(String userId);
}
