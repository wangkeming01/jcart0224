package io.wkm.jcartadministrationback.mapper;

import com.github.pagehelper.Page;
import io.wkm.jcartadministrationback.dto.out.AdministratorListOutDTO;
import io.wkm.jcartadministrationback.dto.out.AdministratorShowOutDTO;
import io.wkm.jcartadministrationback.pojo.Administrator;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministratorMapper {
    int deleteByPrimaryKey(Integer administratorId);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByPrimaryKey(Integer administratorId);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);

    Administrator getByUserName(@Param("username") String username);


    Page<AdministratorListOutDTO> getList();

    AdministratorShowOutDTO getShowById(@Param("administratorId") Integer administratorId);

    void batchDelete(@Param("administratorIds") List<Integer> administratorIds);
}