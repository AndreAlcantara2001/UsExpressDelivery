/**
 * 
 */
package com.Delivery.UsExpress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Delivery.UsExpress.entites.Location;

/**
 * @author Theikdi Sann
 *
 */

@Repository
public interface LocationRepo extends JpaRepository<Location, Integer>{

}
