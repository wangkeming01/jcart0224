package io.wkm.jcartstoreback.mapper;

import com.github.pagehelper.Page;
import io.wkm.jcartstoreback.pojo.Return;
import org.apache.ibatis.annotations.Param;

public interface ReturnMapper {
    int deleteByPrimaryKey(Integer returnId);

    int insert(Return record);

    int insertSelective(Return record);

    Return selectByPrimaryKey(Integer returnId);

    int updateByPrimaryKeySelective(Return record);

    int updateByPrimaryKey(Return record);

    Page<Return> getPageByCustomerId(@Param("customerId") Integer customerId);
}