package com.example.final_proj.repository;

import com.example.final_proj.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    //Поиск по названию (части названия)
    List<Product> findByTitleContainingIgnoreCase(String name);
    //Поиск по названию и диапазону цен
    @Query(value="select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE ?1% ) OR (lower(title) LIKE %?1)) and (price>=?2 and price <=?3)", nativeQuery = true)
    List<Product> findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(String title, float ot, float Do);
    //Поиск по названию, диапазону цен и сортировкой по возрастанию
    @Query(value="select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE ?1% ) OR (lower(title) LIKE %?1)) and (price>=?2 and price <=?3) order by price", nativeQuery = true)
    List<Product> findByTitleOrderByPriceAsc(String title, float ot, float Do);
    //Поиск по названию, диапазону цен и сортировкой по убыванию
    @Query(value="select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE ?1% ) OR (lower(title) LIKE %?1)) and (price>=?2 and price <=?3) order by price desc", nativeQuery = true)
    List<Product> findByTitleOrderByPriceDesc(String title, float ot, float Do);
    //Поиск по названию, диапазону цен, категории и сортировкой по возрастанию
    @Query(value="select * from product where category_id=?4 ((lower(title) LIKE %?1%) or (lower(title) LIKE ?1% ) OR (lower(title) LIKE %?1)) and (price>=?2 and price <=?3) order by price", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPriceAsc(String title, float ot, float Do, int category);

    //Поиск по названию, диапазону цен, категории и сортировкой по убыванию
    @Query(value="select * from product where category_id=?4 ((lower(title) LIKE %?1%) or (lower(title) LIKE ?1% ) OR (lower(title) LIKE %?1)) and (price>=?2 and price <=?3) order by price desc", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPriceDesc(String title, float ot, float Do, int category);

}
