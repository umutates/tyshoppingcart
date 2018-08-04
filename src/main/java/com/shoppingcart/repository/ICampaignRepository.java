package com.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shoppingcart.entity.Campaign;

@Transactional
@Repository
public interface ICampaignRepository extends JpaRepository<Campaign,Long>{

}
