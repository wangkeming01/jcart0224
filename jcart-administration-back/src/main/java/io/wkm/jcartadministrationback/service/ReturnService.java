package io.wkm.jcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wkm.jcartadministrationback.dto.in.ReturnSearchInDTO;
import io.wkm.jcartadministrationback.pojo.Return;

public interface ReturnService {
    Page<Return> search(ReturnSearchInDTO returnSearchInDTO, Integer pageNum);

    Return getById(Integer returnId);

    void update(Return aReturn);
}
