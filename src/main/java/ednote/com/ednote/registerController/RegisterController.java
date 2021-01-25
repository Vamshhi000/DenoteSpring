package ednote.com.ednote.registerController;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import ednote.com.ednote.GlobalExceptionHandler.EmailNotFound;
import ednote.com.ednote.GlobalExceptionHandler.PasswordMissmatch;
import ednote.com.ednote.GlobalExceptionHandler.PasswordNotMatch;
import ednote.com.ednote.GlobalExceptionHandler.TokenNotFound;
import ednote.com.ednote.registerModel.EmailServiceModel;
import ednote.com.ednote.registerModel.ImgModel;

import ednote.com.ednote.registerModel.RegisterModel;
import ednote.com.ednote.registerModel.Responcedto;
import ednote.com.ednote.registerRepository.ImageRepository;

import ednote.com.ednote.registerService.ImgeeService;
import ednote.com.ednote.registerService.RegisterService;

import net.bytebuddy.utility.RandomString;

@RestController
@RequestMapping(value="/register")
@CrossOrigin("http://localhost:4200")
public class RegisterController {
	private String email;
	Logger logger =LoggerFactory.getLogger(RegisterController.class);
	@Autowired
	private RegisterService registerService;

	@Autowired
	private ImageRepository imageRepository;
	@PostMapping(value="/registration")
	public ResponseEntity<Responcedto> save(@RequestBody RegisterModel registerModel) {
		Responcedto responcedto= new Responcedto();
		
		List<ImgModel> imgmodel=imageRepository.findByEmailIsNull();
			try {
				if(registerService.findById(registerModel.getEmail())) {
					throw new EmailNotFound("Already registered");
				}else {
					if(registerModel.getPassword().equals(registerModel.getReenterPassword())) {
						
						for(ImgModel img:imgmodel) {
							ImgModel imgm =new ImgModel();
							
							imgm.setName(img.getName());
							imgm.setPicByte(img.getPicByte());
							imgm.setRegisterModel(registerModel);
							
							imgm.setType(img.getType());
							
							imageRepository.save(imgm);
							
						}
						
						
//						imgeeService.saveimges(registerModel);
						registerService.save(registerModel);
						responcedto.setMsg("You have successfully completed the registration! Please login");
						return new ResponseEntity<Responcedto>(responcedto,HttpStatus.OK);
					}else {
						throw new PasswordNotMatch("Password not Matched");
					}	
				
				}


		
			}catch (EmailNotFound e) {
				responcedto.setMsg("This email is already get registered!");
				
				return new ResponseEntity<Responcedto>(responcedto,HttpStatus.INTERNAL_SERVER_ERROR);
			}catch (PasswordNotMatch e) {
				responcedto.setMsg("password's not matched");
				return new ResponseEntity<Responcedto>(responcedto,HttpStatus.BAD_REQUEST);
		}
			catch (Exception e) {
				responcedto.setMsg("internal server error");
				return new ResponseEntity<Responcedto>(responcedto,HttpStatus.BAD_REQUEST);
			}
		
		
		
	}
	
	
	@GetMapping(value="/{password}")
	public ResponseEntity<Responcedto> loginData(@RequestParam("email") String email,@PathVariable("password") String password ){
		Responcedto responcedto = new Responcedto();
		RegisterModel rregisterModel=null;
		try {
		rregisterModel = registerService.verifyUser(email, password);
		responcedto.setIsAuthenticated(true);
		responcedto.setEmail(rregisterModel.getEmail());
		responcedto.setEmail(rregisterModel.getEmail());
		responcedto.setFullName(rregisterModel.getFullName());
		responcedto.setGender(rregisterModel.getGender());
		responcedto.setPhoneNumber(rregisterModel.getPhoneNumber());
		responcedto.setDob(rregisterModel.getDob());
		responcedto.setMsg("Login successfull");
		return new ResponseEntity<Responcedto>(responcedto,HttpStatus.OK);	
		}catch (EmailNotFound e) {
			responcedto.setMsg("Email Not Registered");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.BAD_REQUEST);
		}
		
		catch (PasswordNotMatch e) {
			responcedto.setMsg("Incorrect Password");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.BAD_REQUEST);
		
	}
	

	
	
	}
	
	@PutMapping(value="/saveProfile")
	
	public ResponseEntity<Responcedto> saveProfile(@RequestParam("email") String email,@RequestBody RegisterModel registerModel) {
		
		Responcedto responcedto= new Responcedto();
		RegisterModel rregisterModel=null;
		try {
			rregisterModel=registerService.updateProfile(email, registerModel);
			responcedto.setEmail(rregisterModel.getEmail());
			responcedto.setFullName(rregisterModel.getFullName());
			responcedto.setGender(rregisterModel.getGender());
			responcedto.setDob(rregisterModel.getDob());
			responcedto.setPhoneNumber(rregisterModel.getPhoneNumber());
			responcedto.setIsAuthenticated(true);
			responcedto.setMsg("Profile updated Sucussfully");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.OK);
		}catch (EmailNotFound e) {
			responcedto.setMsg("Profile Not updated ");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
	}

	@PostMapping(value="/emailVerification")
	public ResponseEntity<Responcedto> emailVerification(@RequestBody RegisterModel registerModel) {
		String token =RandomString.make(45);
		String email=registerModel.getEmail();
		String link="http://localhost:4200"+"/resetPassword?token="+ token;
		Responcedto responcedto = new Responcedto();
		try {
	
			registerService.updateToken(token, email);
			logger.info("updated the token");
			registerService.sendEmail(link, email);
			logger.info("email sent");
//			responcedto.setResponce("Successfully send email! please check");
			responcedto.setEmail(email);
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.OK);
		
		}catch (EmailNotFound e) {
//			responcedto.setResponce("email does't exists");
			responcedto.setEmail(email);
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.BAD_REQUEST);
			
		}
		
		}
	@PostMapping(value="/resetPassword")
	public ResponseEntity<Responcedto> reSettingPassword(@RequestParam("token") String token,@RequestBody RegisterModel registerModel){
	
		
		Responcedto responcedto = new Responcedto();
		try {
			
			
			registerService.reSettingPassword(token, registerModel);
			responcedto.setMsg("Succuessfully reset the password");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.OK);
		}catch (TokenNotFound e) {
			responcedto.setMsg("Bad request");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.BAD_REQUEST);
		}
	
		
	}
	
	@GetMapping(value="/UserData")
	public ResponseEntity<Responcedto> getUserData(@RequestParam("email") String email){
		
		Responcedto responcedto = new Responcedto();
		RegisterModel rregisterModel=null;
		try {
			rregisterModel=registerService.getUserData(email);
			responcedto.setEmail(rregisterModel.getEmail());
			responcedto.setEmail(rregisterModel.getEmail());
			responcedto.setFullName(rregisterModel.getFullName());
			responcedto.setGender(rregisterModel.getGender());
			responcedto.setPhoneNumber(rregisterModel.getPhoneNumber());
			responcedto.setDob(rregisterModel.getDob());
			
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.OK);	
			
			
			
		}catch (Exception e) {
			responcedto.setMsg("error in getting data");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}
	

	
	

	@PutMapping(value="/changePassword")
	public ResponseEntity<Responcedto> changePassword(@RequestParam("email") String email,@RequestBody Responcedto rresponcedto) throws PasswordMissmatch{
		Responcedto responcedto= new Responcedto();
		
		try {
			
			if(rresponcedto.getNewPassword().equals(rresponcedto.getConfirmNewPassword())) {
				registerService.changePassword(email, rresponcedto);	
				responcedto.setMsg("Your Password is Successfully changed");
				return new ResponseEntity<Responcedto>(responcedto,HttpStatus.OK);
			}else {

				throw new PasswordMissmatch("password missmatched");
				
				
			}
		}catch (EmailNotFound e) {
			responcedto.setMsg("Email Not Registered");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.BAD_REQUEST);
		}
		
		catch (PasswordNotMatch e) {
			responcedto.setMsg("The current Password you entered is wrong");
			return new ResponseEntity<Responcedto>(responcedto,HttpStatus.BAD_REQUEST);
		
	}	catch (PasswordMissmatch e) {
		responcedto.setMsg("Entered new password missmatched");
		return new ResponseEntity<Responcedto>(responcedto,HttpStatus.BAD_REQUEST);
	
}
		
		
	}

}