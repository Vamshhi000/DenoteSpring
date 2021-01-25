package ednote.com.ednote.registerController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ednote.com.ednote.registerModel.RegisterModel;
import ednote.com.ednote.registerModel.Responcedto;
import ednote.com.ednote.registerModel.TagModel;
import ednote.com.ednote.registerRepository.TagRepository;
import ednote.com.ednote.registerService.TagService;

@RestController
@RequestMapping(value="/tag")
@CrossOrigin("http://localhost:4200")
public class TagCotroller {
	@Autowired
	private TagService tagService;
	@Autowired
	private TagRepository tagRepository;
	@PostMapping(value="/{name}")
	public ResponseEntity<List<TagModel>> save(@RequestBody TagModel tagmodel,@PathVariable("name") String name,@RequestParam("email") String email){
		List<TagModel> modelll=null;
		TagModel taagmodel=null;

		try {
			if(tagRepository.findByTagNameAndRegisterModelEmailIsNull(tagmodel.getTagName())==null) {
			if(tagRepository.findByTagNameAndRegisterModelEmail(tagmodel.getTagName(),email)==null) {
				Responcedto responcedto= new Responcedto();
		
				tagService.saveTag(tagmodel, name,email);
				
//				responcedto.setMsg("tagset");
				modelll=tagRepository.findByNameAndEmailIsNullOrEmail(name, email);
				System.out.println(modelll);
				return new ResponseEntity<List<TagModel>>(modelll,HttpStatus.OK);
			}else {
				
				return new ResponseEntity<List<TagModel>>(modelll,HttpStatus.BAD_REQUEST);
			}
			}else {
				return new ResponseEntity<List<TagModel>>(modelll,HttpStatus.BAD_REQUEST);
			}
			
		}catch (Exception e) {
			return new ResponseEntity<List<TagModel>>(modelll,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value="/{name}")
	public ResponseEntity<List<TagModel>> getalltags(@PathVariable("name") String name,@RequestParam("email") String email){
		List<TagModel> modelll=null;
		modelll=tagRepository.findByNameAndEmailIsNullOrEmail(name, email);
		return new ResponseEntity<List<TagModel>>(modelll,HttpStatus.OK);
	}
	
}
