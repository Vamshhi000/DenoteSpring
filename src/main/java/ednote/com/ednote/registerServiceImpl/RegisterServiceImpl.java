package ednote.com.ednote.registerServiceImpl;

import java.util.List;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import ednote.com.ednote.GlobalExceptionHandler.EmailNotFound;
import ednote.com.ednote.GlobalExceptionHandler.PasswordNotMatch;
import ednote.com.ednote.GlobalExceptionHandler.TokenNotFound;
import ednote.com.ednote.registerController.RegisterController;
import ednote.com.ednote.registerModel.EmailServiceModel;
import ednote.com.ednote.registerModel.RegisterModel;
import ednote.com.ednote.registerModel.Responcedto;
import ednote.com.ednote.registerRepository.RegisterRepository;
import ednote.com.ednote.registerService.RegisterService;
@Service
public class RegisterServiceImpl implements RegisterService{
	
	Logger logger =LoggerFactory.getLogger(RegisterController.class); 
	@Autowired
	private JavaMailSender javaMailSender;
	@Autowired
	private RegisterRepository registerRepository;
	@Override
	public RegisterModel save(RegisterModel registerModel) {
		return registerRepository.save(registerModel);
		}
	@Override
	public Boolean findById(String email)  {
		Optional<RegisterModel> isok = registerRepository.findById(email);
		if(isok.isPresent()) {
			return true;
			
		}else {
			return false;
		}
		
	}
	@Override
	public void updateToken(String token, String email) throws EmailNotFound {
		RegisterModel registerModel = registerRepository.findByEmail(email);
	 if(registerModel!=null) {
		 registerModel.setPasstoken(token);
			registerRepository.save(registerModel);
	 }else {
		 throw new EmailNotFound("email does't exits");
	 }
			
		
		
		
		
	}
	@Override
	public void sendEmail(String link, String email) {
//		String content="<p>Hello, </p>"
//				+"<p>You have requested to reset your password.</p>"
//				+"<p>Click the link below to change your password:</p>"
//				+"<p><b><a href=\""+ link +"\">CHANGE PASSWORD</a></b></p>"
//				+"<p>Ignore this if you remembered your password</p>";
		String content="Hello, "
		+"You have requested to reset your password.\n"
		+"Click the link below to change your password:\n"
		+link
		+ "\n Ignore this if you remembered your password";
		SimpleMailMessage msg =new SimpleMailMessage();
	
		msg.setTo(email);
		msg.setSubject("Reset Password!EDNOTE");
		msg.setText(content);
		javaMailSender.send(msg);
		

	}
	@Override
	public void reSettingPassword(String token,RegisterModel registerModel) throws TokenNotFound {
		RegisterModel passtokenobj = registerRepository.findByPasstoken(token);
		if(passtokenobj!=null) {
			
			passtokenobj.setPassword(registerModel.getPassword());
			passtokenobj.setReenterPassword(registerModel.getReenterPassword());
			registerRepository.save(passtokenobj);
		}else {
			throw new TokenNotFound("token not found");
		}
		
	}
	@Override
	public RegisterModel updateProfile(String email, RegisterModel registerModel) throws EmailNotFound {
		
		RegisterModel rregisterModel = registerRepository.findByEmail(email);
		RegisterModel forupdate=null;
		
		if(rregisterModel!=null) {
			rregisterModel.setFullName(registerModel.getFullName());
			rregisterModel.setPhoneNumber(registerModel.getPhoneNumber());
			rregisterModel.setGender(registerModel.getGender());
			rregisterModel.setDob(registerModel.getDob());
			RegisterModel rrregisterModel=registerRepository.save(rregisterModel);
			 forupdate = registerRepository.findByEmail(email);
			 return forupdate;
		}else {
			throw new EmailNotFound("email not found");
		}
		
	}
	@Override
	public RegisterModel verifyUser(String email,String password ) throws EmailNotFound,PasswordNotMatch  {
	
		RegisterModel rregisterModel = registerRepository.findByEmail(email);
	
		 if(rregisterModel!=null) {

			 if(password.equals(rregisterModel.getPassword())) {
				 return rregisterModel; 
			 }
			 else {
				 throw new PasswordNotMatch("password not Match");
			 }
		 }else {
			 throw new EmailNotFound("email does't exits");
		 }	

	}
	@Override
	public RegisterModel getUserData(String email) {
		RegisterModel rregisterModel = registerRepository.findByEmail(email);
		return rregisterModel;
	}
	@Override
	public void changePassword(String email, Responcedto rresponcedto) throws EmailNotFound,PasswordNotMatch {
		RegisterModel rregisterModel = registerRepository.findByEmail(email);
		
		 if(rregisterModel!=null) {
			 
			 if(rresponcedto.getPassword().equals(rregisterModel.getPassword())) {
				 rregisterModel.setPassword(rresponcedto.getNewPassword());
				 rregisterModel.setReenterPassword(rresponcedto.getConfirmNewPassword());
				 registerRepository.save(rregisterModel); 
			 }else {
				 throw new PasswordNotMatch("password not match");
			 }
			 
		 }else {
			 throw new EmailNotFound("email does't exits"); 
		 }
	
	}




}
