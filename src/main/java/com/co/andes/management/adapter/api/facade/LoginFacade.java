package com.co.andes.management.adapter.api.facade;
import com.co.andes.management.domain.service.model.request.LoginRequestDTO;
import com.co.andes.management.utils.exception.UMBException;
import com.co.andes.management.domain.service.LoginService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
public class LoginFacade {


    final static Logger logger = Logger.getLogger(LoginFacade.class);
    
    private LoginService loginService;
    
    @Autowired
    public LoginFacade(LoginService generalService) {
    	this.loginService = generalService;
    }
    
    public Object executeLogin(LoginRequestDTO loginRequestDTO) throws UMBException , ExecutionException, InterruptedException{
        return  this.loginService.executeValidation(loginRequestDTO);
    }
}