package io.wkm.jcartstoreback.service;

import com.github.pagehelper.Page;
import io.wkm.jcartstoreback.pojo.Return;

public interface ReturnService {
    void create(Return aReturn);

    Page<Return> getPageByCustomerId(Integer customerId, Integer pageNum);

    Return getById(Integer returnId);
}
