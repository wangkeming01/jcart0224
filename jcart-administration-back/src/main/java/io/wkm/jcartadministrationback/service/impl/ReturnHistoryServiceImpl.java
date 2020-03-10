package io.wkm.jcartadministrationback.service.impl;

import io.wkm.jcartadministrationback.mapper.ReturnHistoryMapper;
import io.wkm.jcartadministrationback.pojo.ReturnHistory;
import io.wkm.jcartadministrationback.service.ReturnHistoryService;
import io.wkm.jcartadministrationback.service.ReturnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ReturnHistoryServiceImpl implements ReturnHistoryService {
    @Resource
    private ReturnHistoryMapper returnHistoryMapper;
    @Override
    public List<ReturnHistory> getListByReturnId(Integer returnId) {
        return returnHistoryMapper.getListByReturnId(returnId);
    }

    @Override
    public Long create(ReturnHistory returnHistory) {
        returnHistoryMapper.insertSelective(returnHistory);
        return returnHistory.getReturnHistoryId();
    }
}
