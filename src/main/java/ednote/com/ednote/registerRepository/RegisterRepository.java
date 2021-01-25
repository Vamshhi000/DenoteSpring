package ednote.com.ednote.registerRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ednote.com.ednote.registerModel.RegisterModel;

@Repository
public interface RegisterRepository extends JpaRepository<RegisterModel, String>  {
	
	public RegisterModel findByEmail(String email);
	public RegisterModel findByPasstoken(String token);

}
