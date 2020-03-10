package io.wkm.jcartadministrationback.service;

import io.wkm.jcartadministrationback.pojo.Address;

import java.util.List;

public interface AddressService {
    List<Address> getListByCustomer(Integer customerId);

    Address getById(Integer addressId);
}
