package io.wkm.jcartstoreback.service;

import io.wkm.jcartstoreback.pojo.ReturnHistory;

import java.util.List;

public interface ReturnHistoryService {
    List<ReturnHistory> getByReturnId(Integer returnId);
}
