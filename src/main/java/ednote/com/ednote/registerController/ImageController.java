package ednote.com.ednote.registerController;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import ednote.com.ednote.registerModel.ImgModel;
import ednote.com.ednote.registerModel.RegisterModel;
import ednote.com.ednote.registerModel.Responcedto;
import ednote.com.ednote.registerRepository.ImageRepository;

import ednote.com.ednote.registerRepository.RegisterRepository;
import ednote.com.ednote.registerService.RegisterService;

@RestController
@RequestMapping(value="/image")
@CrossOrigin("http://localhost:4200")
public class ImageController {
	

	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private RegisterRepository registerRepository;
	@PostMapping(value="/update/{email}")
	public ResponseEntity<Responcedto> uplaodImage(@RequestParam("imageFile") MultipartFile file,@PathVariable("email") String email) throws IOException {
		Responcedto responcedto= new Responcedto();
		try {
			ImgModel imgModel=imageRepository.findByNameAndRegisterModelEmail(file.getOriginalFilename(),email);
			if(imageRepository.findByNameAndRegisterModelEmail(file.getOriginalFilename(),email)==null) {
				RegisterModel registerModel = registerRepository.findByEmail(email);
//				if(registerModel!=null) {
					ImgModel img = new ImgModel(file.getOriginalFilename(), file.getContentType(),file.getBytes());
					img.setRegisterModel(registerModel);
					imageRepository.save(img);

					responcedto.setMsg("New Catagory succesfully created");
					return new ResponseEntity<Responcedto>(responcedto, HttpStatus.OK);
//				}

			}
			responcedto.setMsg("This title already existed");
			return new ResponseEntity<Responcedto>(responcedto, HttpStatus.BAD_REQUEST);

	}catch (Exception e) {
		responcedto.setMsg("Some Internal error occured ");
		return new ResponseEntity<Responcedto>(responcedto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}
	
//	
//	@PostMapping(value="/update/{email}")
//	public ResponseEntity<Responcedto> uplaodImage(@RequestParam("imageFile") MultipartFile file,@PathVariable("email") String email) throws IOException {
//		Responcedto responcedto= new Responcedto();
//		try {
//
//			ImgModel img = new ImgModel(file.getOriginalFilename(), file.getContentType(),file.getBytes());
//		
//			imageRepository.save(img);
//					responcedto.setMsg("New Catagory succesfully created");
//					return new ResponseEntity<Responcedto>(responcedto, HttpStatus.OK);
//				}catch (Exception e) {
//					return new ResponseEntity<Responcedto>(responcedto, HttpStatus.OK);
//				}
//
//
//	}
	
	
	@PostMapping(value="/{name}/{email}")
	public ResponseEntity<Responcedto> editCatagory(@RequestParam("imageFile") MultipartFile file,@PathVariable("name") String name,@PathVariable("email") String email) throws IOException {
		Responcedto responcedto= new Responcedto();

		try {
			

			if(imageRepository.findByNameAndRegisterModelEmail(file.getOriginalFilename(),email)==null) {
				ImgModel img=imageRepository.findByNameAndRegisterModelEmail(name,email);
				if(!file.getOriginalFilename().equals(img.getName())) {
					img.setName(file.getOriginalFilename());
					img.setPicByte(file.getBytes());
					img.setType(file.getContentType());
//					 img = new ImgModel(file.getOriginalFilename(), file.getContentType(),
//							file.getBytes());
					
					imageRepository.save(img);
					responcedto.setMsg(" Catagory Updated succesfully created");
					return new ResponseEntity<Responcedto>(responcedto, HttpStatus.OK);
				}else {
					responcedto.setMsg("This title already existed");
					return new ResponseEntity<Responcedto>(responcedto, HttpStatus.BAD_REQUEST);
				}
			

			}
			responcedto.setMsg("Item is already created");
			return new ResponseEntity<Responcedto>(responcedto, HttpStatus.BAD_REQUEST);

	}catch (Exception e) {
		responcedto.setMsg("Some Internal error occured ");
		return new ResponseEntity<Responcedto>(responcedto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	}

	
	
	
	
	
	
	
	
	
	


@GetMapping(value="/{email}")
public ResponseEntity< List<ImgModel>> getAllImages(@PathVariable("email") String email){
	
	List<ImgModel> imglist =imageRepository.findByRegisterModelEmail(email);
	return new ResponseEntity< List<ImgModel>>(imglist,HttpStatus.OK);
	
}
}
