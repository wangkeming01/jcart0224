package io.wkm.jcartstoreback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.wkm.jcartstoreback.mapper.ReturnMapper;
import io.wkm.jcartstoreback.pojo.Return;
import io.wkm.jcartstoreback.service.ReturnService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ReturnServiceImpl implements ReturnService {
    @Resource
    private ReturnMapper returnMapper;
    @Override
    public void create(Return aReturn) {
        returnMapper.insertSelective(aReturn);
    }

    @Override
    public Page<Return> getPageByCustomerId(Integer customerId, Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        return returnMapper.getPageByCustomerId(customerId);
    }

    @Override
    public Return getById(Integer returnId) {
        return returnMapper.selectByPrimaryKey(returnId);
    }
}
