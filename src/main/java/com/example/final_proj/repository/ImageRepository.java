package com.example.final_proj.repository;

import com.example.final_proj.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Modifying
    @Query(value="delete from image where id = ?1", nativeQuery = true)
    void deleteByNomer(int id);

//    @Modifying
//    @Query(value="update image set file_name = ?1 where id=70", nativeQuery = true)
//    List<Image> updateImageForName(String name, int id);
}
