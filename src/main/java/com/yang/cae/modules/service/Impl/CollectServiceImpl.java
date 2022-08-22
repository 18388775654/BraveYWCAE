package com.yang.cae.modules.service.Impl;

import com.yang.cae.modules.dto.DataDTO;
import com.yang.cae.modules.entity.OrderCollect;
import com.yang.cae.modules.mapper.jpa.OrderCollectJpa;
import com.yang.cae.modules.service.CollectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Resource
    private OrderCollectJpa orderCollectJpa;
    @Override
    public List<DataDTO> getCollect(String userId) {
        List<OrderCollect> collectList = orderCollectJpa.getByUserIdAndIsDeletedIsNull(userId);
        List<DataDTO> dataDTOList = new ArrayList<>();
        if (collectList != null && !collectList.isEmpty()){
            for (OrderCollect collect : collectList) {
                DataDTO dataDTO = new DataDTO();
                dataDTO.setMessageId(collect.getCollectMessageId());
                dataDTO.setFlag(collect.getFlag());
                dataDTO.setMessageName(collect.getCollectMessageName());
                dataDTO.setDate(collect.getCollectTime());
                dataDTOList.add(dataDTO);
            }
        }
        return dataDTOList;
    }
}
