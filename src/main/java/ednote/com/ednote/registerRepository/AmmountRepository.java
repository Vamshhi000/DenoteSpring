package ednote.com.ednote.registerRepository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ednote.com.ednote.registerModel.AmmountTable;




@Repository
public interface AmmountRepository extends JpaRepository<AmmountTable, Integer> {
	
	List<AmmountTable> findByNameAndRegisterModelEmail(String name, String email); 
	AmmountTable findByTagName(String tagname);
	AmmountTable findByTagNameAndRegisterModelEmail(String tagname,String email);
	List<AmmountTable> findByRegisterModelEmail(String email);
	
	@Query(value="select DISTINCT name from ammount_table where email=:email", nativeQuery = true)

	List<String> findByNameAndEmail(@Param("email")String email);
	
	
	@Query(value="select * from ammount_table where (name=:name) And (tag_name=:tagname And email=:email)", nativeQuery = true)

	AmmountTable findByNameAndTagNameAndEmail(@Param("name")String name,@Param("tagname")String tagname,@Param("email")String email);
	
	
	@Query(value="select * from ammount_table where ammount_date BETWEEN :startDate AND :endDate And (name=:name And email=:email) order by ammount_date asc", nativeQuery = true)

	List<AmmountTable> findBammountDateAndNameAndEmail(@Param("startDate")String startDate,@Param("endDate")String endDate,@Param("name")String name,@Param("email")String email);
	
	
	@Query(value="select * from ammount_table where ammount_date BETWEEN :startDate AND :endDate And (email=:email) order by ammount_date asc", nativeQuery = true)

	List<AmmountTable> findByammountDateAndEmail(@Param("startDate")String startDate,@Param("endDate")String endDate,@Param("email")String email);

}

 
