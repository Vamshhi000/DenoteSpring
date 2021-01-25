package ednote.com.ednote.registerRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ednote.com.ednote.registerModel.ImgModel;
@Repository
public interface ImageRepository extends JpaRepository<ImgModel, Integer> {
//public ImgModel findByName(String name);

public ImgModel findByNameAndRegisterModelEmail(String name,String email);
public ImgModel findByNameAndRegisterModelEmailIsNull(String name);
@Query(value="select * from image where email Is null or email=:email ", nativeQuery = true)

  List<ImgModel> findByEmailIsNullOrEmail(@Param("email")String email);

public List<ImgModel> findByRegisterModelEmail(String email);

@Query(value="select * from image where email Is null", nativeQuery = true)
public List<ImgModel> findByEmailIsNull();
}


////@Query(value = "SELECT c.year AS yearComment, COUNT(c.*) AS totalComment "
//+ "FROM comment AS c GROUP BY c.year ORDER BY c.year DESC", nativeQuery = true)
//List<ICommentCount> countTotalCommentsByYearNative();