package ednote.com.ednote.registerService;

import java.util.List;

import ednote.com.ednote.registerModel.ImgModel;
import ednote.com.ednote.registerModel.TagModel;

public interface TagService {

	
	public void saveTag(TagModel tagmodel,String name,String email);
	public List<TagModel> getalltags(String name);
	
	

}
