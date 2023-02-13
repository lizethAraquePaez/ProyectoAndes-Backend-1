package com.co.andes.management.adapter.api.controller;
import com.co.andes.management.adapter.api.ApiConst;
import com.co.andes.management.adapter.api.facade.LoginFacade;
import com.co.andes.management.domain.service.model.request.LoginRequestDTO;
import com.co.andes.management.utils.exception.ConstantErrors;
import com.co.andes.management.utils.exception.UMBErrorEnum;
import com.co.andes.management.utils.exception.UMBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.ApiOperation;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import com.google.gson.Gson;


import org.apache.log4j.Logger;

@RestController
@RequestMapping(value = ApiConst.LOGIN_PATH)
@CrossOrigin(origins = "*")
public class LoginController {

    final static Logger logger = Logger.getLogger(LoginController.class);
    
    private LoginFacade loginFacade;
    private Gson gson;

    @Autowired
    public LoginController(LoginFacade loginFacade, Gson gson) {
        this.loginFacade = loginFacade;
        this.gson = gson;
    }
    
	@GetMapping("/message")
	public String getMessage() {
		return "V20200914_01";
	}
	
	@ApiOperation(value = "login")
	@RequestMapping(value = "/login/", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<String> doLogin(@RequestBody LoginRequestDTO loginRequestDTO) {
		try {
			return new ResponseEntity<String>(this.gson.toJson(this.loginFacade.executeLogin(loginRequestDTO)), HttpStatus.OK);
		}
		catch (UMBException e) {
			e.printStackTrace();
			logger.error("Se presentaron problemas en el controller login ", e);
			return new ResponseEntity<String>(new Gson().toJson(ConstantErrors.ERRORS_STATES.get(e.getMessage())),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (Exception e) {
			logger.error("Se presentaron problemas enviar la checkFields en el controller login",e);
			return new ResponseEntity<String> (this.gson.toJson(ConstantErrors.ERRORS_STATES.get(UMBErrorEnum.GENERIC_ERROR.getCode())), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}