package io.wkm.jcartstoreback.mapper;

import io.wkm.jcartstoreback.pojo.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {
    int deleteByPrimaryKey(Integer customerId);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer customerId);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    Customer getByUserName(@Param("username") String username);

    Customer getByEmail(@Param("email") String email);
}