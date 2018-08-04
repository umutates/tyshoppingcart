package com.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingcart.entity.Category;

/**
 * @author umutates
 * created on 2018-08-04
 */
@Transactional
@Repository
public interface ICategoryRepository extends JpaRepository<Category,Long> {
	
	@Query("select a from #{#entityName} a where a.title = ?1")
	public Category findByTitle(String title);

}
