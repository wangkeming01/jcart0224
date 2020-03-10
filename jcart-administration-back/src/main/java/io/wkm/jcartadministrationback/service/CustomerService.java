package io.wkm.jcartadministrationback.service;

import com.github.pagehelper.Page;
import io.wkm.jcartadministrationback.dto.in.CustomerListInDTO;
import io.wkm.jcartadministrationback.dto.in.CustomerSetStatusInDTO;
import io.wkm.jcartadministrationback.pojo.Customer;

public interface CustomerService {
    Page<Customer> search(Integer pageNum, CustomerListInDTO customerListInDTO);

    Customer getById(Integer customerId);

    void setStatus(CustomerSetStatusInDTO customerSetStatusInDTO);
}
