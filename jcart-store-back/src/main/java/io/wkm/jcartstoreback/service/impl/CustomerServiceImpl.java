package io.wkm.jcartstoreback.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.wkm.jcartstoreback.dto.in.CustomerRegisterInDTO;
import io.wkm.jcartstoreback.enumeration.CustomerStatus;
import io.wkm.jcartstoreback.mapper.CustomerMapper;
import io.wkm.jcartstoreback.pojo.Customer;
import io.wkm.jcartstoreback.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;
    @Override
    public Customer getByUserName(String username) {
        return customerMapper.getByUserName(username);
    }

    @Override
    public Customer getById(Integer customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public void update(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public Customer getByEmail(String email) {
        return customerMapper.getByEmail(email);
    }

    @Override
    public Integer register(CustomerRegisterInDTO customerRegisterInDTO) {
        Customer customer = new Customer();
        customer.setUsername(customerRegisterInDTO.getUsername());
        customer.setRealName(customerRegisterInDTO.getRealName());
        customer.setEmail(customerRegisterInDTO.getEmail());
        customer.setEmailVerified(false);
        customer.setMobile(customerRegisterInDTO.getMobile());
        customer.setMobileVerified(false);
        customer.setNewsSubscribed(customerRegisterInDTO.getNewsSubscribed());
        customer.setCreateTime(new Date());
        customer.setStatus((byte) CustomerStatus.Enable.ordinal());
        customer.setRewordPoints(0);

        String s = BCrypt.withDefaults().hashToString(12, customerRegisterInDTO.getPassword().toCharArray());
        customer.setEncryptedPassword(s);
        customerMapper.insertSelective(customer);
        return customer.getCustomerId();
    }
}
