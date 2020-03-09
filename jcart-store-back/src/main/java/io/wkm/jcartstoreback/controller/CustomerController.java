package io.wkm.jcartstoreback.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.sun.xml.internal.ws.client.ClientTransportException;
import io.wkm.jcartstoreback.constant.ClientExceptionConstant;
import io.wkm.jcartstoreback.dto.in.*;
import io.wkm.jcartstoreback.dto.out.CustomerLoginOutDTO;
import io.wkm.jcartstoreback.dto.out.CustomerProfileOutDTO;
import io.wkm.jcartstoreback.exception.ClientException;
import io.wkm.jcartstoreback.pojo.Customer;
import io.wkm.jcartstoreback.service.CustomerService;
import io.wkm.jcartstoreback.util.JWTUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/customer")
@CrossOrigin
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @Resource
    private JWTUtil jwtUtil;

    @PostMapping("/register")
    public Integer register(@RequestBody CustomerRegisterInDTO customerRegisterInDTO){

        return  customerService.register(customerRegisterInDTO);
    }

    @GetMapping("/login")
    public CustomerLoginOutDTO login(CustomerLoginInDTO customerLoginInDTO) throws ClientException {
        Customer customer = customerService.getByUserName(customerLoginInDTO.getUsername());
        if (customer == null){
            throw new ClientException(ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRCODE,ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRMSG);
        }
        String encryptedPassword = customer.getEncryptedPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(customerLoginInDTO.getPassword().toCharArray(), encryptedPassword);
        if (result.verified){
            return jwtUtil.issueToken(customer);
        }else {
            throw new ClientException(ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRCODE,ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRMSG);
        }
    }

    @GetMapping("/getProfile")
    public CustomerProfileOutDTO getProfile(@RequestAttribute Integer customerId){
        Customer customer = customerService.getById(customerId);
        CustomerProfileOutDTO customerGetProfileOutDTO = new CustomerProfileOutDTO();
        customerGetProfileOutDTO.setUsername(customer.getUsername());
        customerGetProfileOutDTO.setRealName(customer.getRealName());
        customerGetProfileOutDTO.setMobile(customer.getMobile());
        customerGetProfileOutDTO.setMobileVerified(customer.getMobileVerified());
        customerGetProfileOutDTO.setEmail(customer.getEmail());
        customerGetProfileOutDTO.setEmailVerified(customer.getEmailVerified());
        return customerGetProfileOutDTO;
    }

    @PostMapping("/updateProfile")
    public void updateProfile(@RequestBody CustomerUpdateProfileInDTO customerUpdateProfileInDTO,@RequestAttribute Integer customerId){
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setRealName(customerUpdateProfileInDTO.getRealName());
        customer.setMobile(customerUpdateProfileInDTO.getMobile());
        customer.setEmail(customerUpdateProfileInDTO.getEmail());
        customerService.update(customer);
    }

    @PostMapping("/changePassword")
    public void changePassword(@RequestBody CustomerChangePasswordInDTO customerChangePasswordInDTO,@RequestAttribute Integer customerId) throws ClientException {
        Customer customer = customerService.getById(customerId);
        String encryptedPassword = customer.getEncryptedPassword();
        BCrypt.Result result = BCrypt.verifyer().verify(customerChangePasswordInDTO.getOriginPwd().toCharArray(), encryptedPassword);
        if (result.verified){
            String s = BCrypt.withDefaults().hashToString(12, customerChangePasswordInDTO.getNewPwd().toCharArray());
            customer.setEncryptedPassword(s);
            customerService.update(customer);
        }else {
            throw new ClientException(ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRCODE
                    , ClientExceptionConstant.CUSTOMER_PASSWORD_INVALID_ERRMSG);
        }
    }

    @GetMapping("/getPwdResetCode")
    public String getPwdResetCode(@RequestParam String email) throws ClientException {
        Customer customer = customerService.getByEmail(email);
        if (customer == null){
            throw new ClientException(ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRCODE, ClientExceptionConstant.CUSTOMER_USERNAME_NOT_EXIST_ERRMSG);
        }

        return null;
    }

    @PostMapping("/resetPwd")
    public void resetPwd(@RequestBody CustomerResetPwdInDTO customerResetPwdInDTO){

    }
}
