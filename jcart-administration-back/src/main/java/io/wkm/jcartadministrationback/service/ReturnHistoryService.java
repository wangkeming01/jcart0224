package io.wkm.jcartadministrationback.service;

import io.wkm.jcartadministrationback.pojo.ReturnHistory;

import java.util.List;

public interface ReturnHistoryService {
    List<ReturnHistory> getListByReturnId(Integer returnId);

    Long create(ReturnHistory returnHistory);

}
