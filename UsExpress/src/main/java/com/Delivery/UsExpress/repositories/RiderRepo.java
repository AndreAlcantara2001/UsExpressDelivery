/**
 * 
 */
package com.Delivery.UsExpress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Delivery.UsExpress.entites.Rider;

/**
 * @author Theikdi Sann
 *
 */
@Repository
public interface RiderRepo extends JpaRepository<Rider, Integer>{

}
