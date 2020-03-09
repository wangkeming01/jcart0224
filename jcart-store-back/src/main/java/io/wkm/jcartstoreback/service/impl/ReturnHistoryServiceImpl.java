package io.wkm.jcartstoreback.service.impl;

import io.wkm.jcartstoreback.mapper.ReturnHistoryMapper;
import io.wkm.jcartstoreback.pojo.ReturnHistory;
import io.wkm.jcartstoreback.service.ReturnHistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReturnHistoryServiceImpl implements ReturnHistoryService {
    @Resource
    private ReturnHistoryMapper returnHistoryMapper;
    @Override
    public List<ReturnHistory> getByReturnId(Integer returnId) {
        return returnHistoryMapper.getByReturnId(returnId);
    }
}
