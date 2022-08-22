package com.yang.cae.modules.service;

import com.yang.cae.modules.dto.DataDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {
    public List<DataDTO> getData(String queryData);

    public void saveSearchRecord(String id,String queryData);
    public List<DataDTO> getSearchRecord(String userId);
}
