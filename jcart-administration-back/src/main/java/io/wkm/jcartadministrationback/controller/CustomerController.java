package io.wkm.jcartadministrationback.controller;

import com.github.pagehelper.Page;
import io.wkm.jcartadministrationback.dto.in.CustomerListInDTO;
import io.wkm.jcartadministrationback.dto.in.CustomerSetStatusInDTO;
import io.wkm.jcartadministrationback.dto.out.CustomerListOutDTO;
import io.wkm.jcartadministrationback.dto.out.CustomerShowOutDTO;
import io.wkm.jcartadministrationback.dto.out.PageOutDTO;
import io.wkm.jcartadministrationback.pojo.Address;
import io.wkm.jcartadministrationback.pojo.Customer;
import io.wkm.jcartadministrationback.service.AddressService;
import io.wkm.jcartadministrationback.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {
    @Resource
    private CustomerService customerService;
    @Resource
    private AddressService addressService;
    @GetMapping("/search")
    public PageOutDTO<CustomerListOutDTO> search(CustomerListInDTO customerListInDTO, @RequestParam(required = false, defaultValue = "1") Integer pageNum){
        Page<Customer> page = customerService.search(pageNum,customerListInDTO);
        List<CustomerListOutDTO> customerListOutDTOS = page.stream().map(customer -> {
            CustomerListOutDTO customerListOutDTO = new CustomerListOutDTO();
            customerListOutDTO.setCustomerId(customer.getCustomerId());
            customerListOutDTO.setUsername(customer.getUsername());
            customerListOutDTO.setRealName(customer.getRealName());
            customerListOutDTO.setMobile(customer.getMobile());
            customerListOutDTO.setEmail(customer.getEmail());
            customerListOutDTO.setStatus(customer.getStatus());
            customerListOutDTO.setCreateTimestamp(customer.getCreateTime().getTime());
            return customerListOutDTO;
        }).collect(Collectors.toList());

        PageOutDTO<CustomerListOutDTO> pageOutDTO = new PageOutDTO<>();
        pageOutDTO.setTotal(page.getTotal());
        pageOutDTO.setPageSize(page.getPageSize());
        pageOutDTO.setPageNum(page.getPageNum());
        pageOutDTO.setList(customerListOutDTOS);

        return pageOutDTO;
    }
    @GetMapping("/getById")
    public CustomerShowOutDTO getById(@RequestParam Integer customerId){
        Customer customer = customerService.getById(customerId);

        CustomerShowOutDTO customerShowOutDTO = new CustomerShowOutDTO();
        customerShowOutDTO.setCustomerId(customerId);
        customerShowOutDTO.setUsername(customer.getUsername());
        customerShowOutDTO.setRealName(customer.getRealName());
        customerShowOutDTO.setMobile(customer.getMobile());
        customerShowOutDTO.setEmail(customer.getEmail());
        customerShowOutDTO.setAvatarUrl(customer.getAvatarUrl());
        customerShowOutDTO.setStatus(customer.getStatus());
        customerShowOutDTO.setRewordPoints(customer.getRewordPoints());
        customerShowOutDTO.setNewsSubscribed(customer.getNewsSubscribed());
        customerShowOutDTO.setCreateTimestamp(customer.getCreateTime().getTime());
        customerShowOutDTO.setDefaultAddressId(customer.getDefaultAddressId());

        Address address = addressService.getById(customer.getDefaultAddressId());
        if (address != null){
            customerShowOutDTO.setDefaultAddress(address.getContent());
        }
        return customerShowOutDTO;
    }

    @PostMapping("/setStatus")
    public void disable(@RequestBody CustomerSetStatusInDTO customerSetStatusInDTO){
        customerService.setStatus(customerSetStatusInDTO);
    }

}
