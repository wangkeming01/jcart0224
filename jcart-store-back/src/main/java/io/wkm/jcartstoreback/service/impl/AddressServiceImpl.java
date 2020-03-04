package io.wkm.jcartstoreback.service.impl;

import io.wkm.jcartstoreback.mapper.AddressMapper;
import io.wkm.jcartstoreback.pojo.Address;
import io.wkm.jcartstoreback.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    private AddressMapper addressMapper;
    @Override
    public List<Address> getListByCustomerId(Integer customerId) {
        return addressMapper.getListByCustomerId(customerId);
    }

    @Override
    public Integer create(Address address) {
        return addressMapper.insertSelective(address);
    }

    @Override
    public Address getById(Integer addressId) {
        return addressMapper.selectByPrimaryKey(addressId);
    }

    @Override
    public void update(Address address) {
        addressMapper.updateByPrimaryKeySelective(address);
    }

    @Override
    public void delete(Integer addressId) {
        addressMapper.deleteByPrimaryKey(addressId);
    }
}
