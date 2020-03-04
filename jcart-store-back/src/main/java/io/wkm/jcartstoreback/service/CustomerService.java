package io.wkm.jcartstoreback.service;

import io.wkm.jcartstoreback.dto.in.CustomerRegisterInDTO;
import io.wkm.jcartstoreback.pojo.Customer;

public interface CustomerService {
    Customer getByUserName(String username);

    Customer getById(Integer customerId);

    void update(Customer customer);

    Customer getByEmail(String email);

    Integer register(CustomerRegisterInDTO customerRegisterInDTO);

}
