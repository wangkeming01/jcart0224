package io.wkm.jcartadministrationback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.wkm.jcartadministrationback.dto.out.AdministratorListOutDTO;
import io.wkm.jcartadministrationback.dto.out.AdministratorShowOutDTO;
import io.wkm.jcartadministrationback.mapper.AdministratorMapper;
import io.wkm.jcartadministrationback.pojo.Administrator;
import io.wkm.jcartadministrationback.service.AdministratorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Resource
    private AdministratorMapper administratorMapper;
    @Override
    public Administrator getByUserName(String username) {
        return administratorMapper.getByUserName(username);
    }

    @Override
    public Administrator getById(Integer administratorId) {
        return administratorMapper.selectByPrimaryKey(administratorId);
    }

    @Override
    public void changePwd(Administrator administrator) {
        administratorMapper.updateByPrimaryKeySelective(administrator);
    }

    @Override
    public void create(Administrator administrator) {
        administratorMapper.insertSelective(administrator);
    }

    @Override
    public void update(Administrator administrator) {
        administratorMapper.updateByPrimaryKeySelective(administrator);
    }

    @Override
    public Page<Administrator> getList(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        return administratorMapper.getList();
    }

    @Override
    public AdministratorShowOutDTO getShowById(Integer administratorId) {
        return administratorMapper.getShowById(administratorId);
    }

    @Override
    public void delete(Integer administratorId) {
        administratorMapper.deleteByPrimaryKey(administratorId);
    }

    @Override
    public void batchDelete(List<Integer> administratorIds) {
        administratorMapper.batchDelete(administratorIds);
    }
}
