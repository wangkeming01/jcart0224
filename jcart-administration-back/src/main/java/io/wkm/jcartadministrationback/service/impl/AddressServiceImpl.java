package io.wkm.jcartadministrationback.service.impl;

import io.wkm.jcartadministrationback.mapper.AddressMapper;
import io.wkm.jcartadministrationback.pojo.Address;
import io.wkm.jcartadministrationback.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    @Override
    public List<Address> getListByCustomer(Integer customerId) {
        return addressMapper.getListByCustomer(customerId);
    }

    @Override
    public Address getById(Integer addressId) {
        return addressMapper.selectByPrimaryKey(addressId);
    }
}
