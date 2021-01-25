package ednote.com.ednote.GlobalExceptionHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	
	
	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<ExceptionModel> catchException(Exception e){
		logger.error("chcked exception",e);
		ExceptionModel exceptionModel = new ExceptionModel();
		
		exceptionModel.setE(e);
		
		exceptionModel.setMessage("chcked exception");
		
		return new ResponseEntity<ExceptionModel>(exceptionModel,HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	@ExceptionHandler(value=RuntimeException.class)
	public ResponseEntity<ExceptionModel> catchRuntimeException(Exception e){
		logger.error("runtime exception",e);
		ExceptionModel exceptionModel = new ExceptionModel();
		
		exceptionModel.setE(e);
		
		exceptionModel.setMessage("runtime exception");
		
		return new ResponseEntity<ExceptionModel>(exceptionModel,HttpStatus.INTERNAL_SERVER_ERROR);
		
		
	}
	@ExceptionHandler(value=EmailNotFound.class)
	public ResponseEntity<ExceptionModel> CatchEmailNotFound(EmailNotFound emailNotFound){
		logger.error("Email is not found",emailNotFound);
		
		ExceptionModel exceptionModel = new ExceptionModel();
		exceptionModel.setE(emailNotFound);
		exceptionModel.setMessage("This email address does't exists");
		
		
		return new ResponseEntity<ExceptionModel>(exceptionModel,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value=TokenNotFound.class)
	public ResponseEntity<ExceptionModel> CatchTokenNotFound(TokenNotFound tokenNotFound){
		
		logger.error("token not found");
		ExceptionModel exceptionModel = new ExceptionModel();
		exceptionModel.setE(tokenNotFound);
		exceptionModel.setMessage("Token not Found");
		return new ResponseEntity<ExceptionModel>(exceptionModel,HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(value=PasswordNotMatch.class)
	public ResponseEntity<ExceptionModel> catchPasswordNotMatch(PasswordNotMatch passwordNotMatch){
		
		logger.error("password not Match");
		ExceptionModel exceptionModel = new ExceptionModel();
		exceptionModel.setE(passwordNotMatch);
		exceptionModel.setMessage("password not Match");
		
		
		return new ResponseEntity<ExceptionModel>(exceptionModel,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value=PasswordMissmatch.class)
	public ResponseEntity<ExceptionModel> catchPasswordMissmatch(PasswordMissmatch passwordMissmatch){
		
		logger.error("password missMatch");
		ExceptionModel exceptionModel = new ExceptionModel();
		exceptionModel.setE(passwordMissmatch);
		exceptionModel.setMessage("password missMatch");
		
		
		return new ResponseEntity<ExceptionModel>(exceptionModel,HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(value=DuplicatetagName.class)
	public ResponseEntity<ExceptionModel> catchDulicateTagName(PasswordMissmatch passwordMissmatch){
		
		logger.error("duplicate tagname");
		ExceptionModel exceptionModel = new ExceptionModel();
		exceptionModel.setE(passwordMissmatch);
		exceptionModel.setMessage("duplicate tagname");
		
		
		return new ResponseEntity<ExceptionModel>(exceptionModel,HttpStatus.BAD_REQUEST);
		
	}
}
