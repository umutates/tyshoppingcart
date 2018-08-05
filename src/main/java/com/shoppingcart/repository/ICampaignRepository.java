package com.shoppingcart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingcart.entity.Campaign;

@Transactional
@Repository
public interface ICampaignRepository extends JpaRepository<Campaign,Long>{
   
	@Query("select a from #{#entityName} a where a.category.id = ?1")
	List<Campaign> findByCategoryId(Long categoryId);

}
