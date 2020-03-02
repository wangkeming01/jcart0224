package io.wkm.jcartadministrationback.service;

import io.wkm.jcartadministrationback.pojo.Administrator;

public interface AdministratorService {
    Administrator getByUserName(String username);
}
