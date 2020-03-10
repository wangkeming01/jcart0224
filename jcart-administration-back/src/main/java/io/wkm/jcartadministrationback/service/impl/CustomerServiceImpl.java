package io.wkm.jcartadministrationback.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.wkm.jcartadministrationback.dto.in.CustomerListInDTO;
import io.wkm.jcartadministrationback.dto.in.CustomerSetStatusInDTO;
import io.wkm.jcartadministrationback.mapper.CustomerMapper;
import io.wkm.jcartadministrationback.pojo.Customer;
import io.wkm.jcartadministrationback.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerMapper customerMapper;
    @Override
    public Page<Customer> search(Integer pageNum, CustomerListInDTO customerListInDTO) {
        PageHelper.startPage(pageNum, 10);
        Page<Customer> page = customerMapper
                .search(customerListInDTO.getUsername(),
                        customerListInDTO.getRealName(),
                        customerListInDTO.getMobile(),
                        customerListInDTO.getEmail(),
                        customerListInDTO.getStatus());
        return page;
    }

    @Override
    public Customer getById(Integer customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }

    @Override
    public void setStatus(CustomerSetStatusInDTO customerSetStatusInDTO) {
        Customer customer = new Customer();
        customer.setCustomerId(customerSetStatusInDTO.getCustomerId());
        customer.setStatus(customerSetStatusInDTO.getStatus());
        customerMapper.updateByPrimaryKeySelective(customer);
    }


}
