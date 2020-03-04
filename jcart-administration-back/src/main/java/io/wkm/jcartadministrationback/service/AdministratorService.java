package io.wkm.jcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wkm.jcartadministrationback.dto.out.AdministratorListOutDTO;
import io.wkm.jcartadministrationback.dto.out.AdministratorShowOutDTO;
import io.wkm.jcartadministrationback.pojo.Administrator;

import java.util.List;

public interface AdministratorService {
    Administrator getByUserName(String username);

    Administrator getById(Integer administratorId);

    void changePwd(Administrator administrator);

    void create(Administrator administrator);

    void update(Administrator administrator);

    Page<Administrator> getList(Integer pageNum);

    AdministratorShowOutDTO getShowById(Integer administratorId);

    void delete(Integer administratorId);

    void batchDelete(List<Integer> administratorIds);
}
