package io.wkm.jcartadministrationback.service.impl;

import io.wkm.jcartadministrationback.mapper.AdministratorMapper;
import io.wkm.jcartadministrationback.pojo.Administrator;
import io.wkm.jcartadministrationback.service.AdministratorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    @Resource
    private AdministratorMapper administratorMapper;
    @Override
    public Administrator getByUserName(String username) {
        return administratorMapper.getByUserName(username);
    }
}
