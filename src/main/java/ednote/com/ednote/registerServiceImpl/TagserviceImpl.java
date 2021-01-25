package ednote.com.ednote.registerServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ednote.com.ednote.registerModel.ImgModel;
import ednote.com.ednote.registerModel.RegisterModel;
import ednote.com.ednote.registerModel.TagModel;
import ednote.com.ednote.registerRepository.ImageRepository;
import ednote.com.ednote.registerRepository.RegisterRepository;
import ednote.com.ednote.registerRepository.TagRepository;
import ednote.com.ednote.registerService.TagService;
@Service
public class TagserviceImpl implements TagService {

	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private TagRepository tagRepository;
	
	@Autowired
	private RegisterRepository registerRepository;
	@Override
	public void saveTag(TagModel tagmodel, String name,String email) {
	RegisterModel registerModel = registerRepository.findByEmail(email);
//	ImgModel imgmodel=	imageRepository.findByName(name);
	TagModel ttagmodel=new TagModel();
	ttagmodel.setTagName(tagmodel.getTagName());
	ttagmodel.setName(name);
	ttagmodel.setRegisterModel(registerModel);
	tagRepository.save(ttagmodel);
	}
	@Override
	public List<TagModel> getalltags(String name) {
		
		return tagRepository.findByName(name);
	}

}
