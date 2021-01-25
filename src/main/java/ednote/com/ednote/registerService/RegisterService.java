package ednote.com.ednote.registerService;

import java.util.List;

import ednote.com.ednote.GlobalExceptionHandler.EmailNotFound;
import ednote.com.ednote.GlobalExceptionHandler.PasswordNotMatch;
import ednote.com.ednote.GlobalExceptionHandler.TokenNotFound;
import ednote.com.ednote.registerModel.EmailServiceModel;
import ednote.com.ednote.registerModel.RegisterModel;
import ednote.com.ednote.registerModel.Responcedto;

public interface RegisterService {

	
	public RegisterModel save(RegisterModel registerModel);
	public Boolean findById(String email) throws EmailNotFound;
//	public void sendEmail(EmailServiceModel emailServiceModel);
	public void updateToken(String token,String email)  throws EmailNotFound;
	public void sendEmail(String link,String email);
	public void reSettingPassword(String token,RegisterModel registerModel) throws TokenNotFound;
	public RegisterModel updateProfile(String email,RegisterModel registerModel) throws EmailNotFound;
	
	public RegisterModel verifyUser(String email,String password ) throws EmailNotFound,PasswordNotMatch ;
	public RegisterModel getUserData(String email);
	public void changePassword(String email,Responcedto rresponcedto) throws EmailNotFound,PasswordNotMatch;
}

