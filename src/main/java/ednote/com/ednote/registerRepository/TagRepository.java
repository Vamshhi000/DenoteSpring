package ednote.com.ednote.registerRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ednote.com.ednote.registerModel.ImgModel;
import ednote.com.ednote.registerModel.TagModel;

public interface TagRepository extends JpaRepository<TagModel, Integer> {

	public List<TagModel> findByName(String name);
	
	public TagModel findByTagNameAndRegisterModelEmailIsNull(String tagName);
	public TagModel findByTagName(String name);
//	public TagModel findByEmail(String email);
	public TagModel findByTagNameAndRegisterModelEmail(String tagName,String email);
	
	@Query(value="select * from tag_table where (name=:name AND email is null) OR (name=:name AND email=:email)", nativeQuery = true)

	List<TagModel> findByNameAndEmailIsNullOrEmail(@Param("name")String name,@Param("email")String email);
}

