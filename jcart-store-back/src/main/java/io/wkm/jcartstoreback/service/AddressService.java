package io.wkm.jcartstoreback.service;

import io.wkm.jcartstoreback.pojo.Address;

import java.util.List;

public interface AddressService {
    List<Address>  getListByCustomerId(Integer customerId);

    Integer create(Address address);

    Address getById(Integer addressId);

    void update(Address address);

    void delete(Integer addressId);
}
