/**
 * 
 */
package com.Delivery.UsExpress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Delivery.UsExpress.entites.PickUpAddress;

/**
 * @author Acer
 *
 */

@Repository
public interface PickUpAddressRepo extends JpaRepository<PickUpAddress, Integer>{

}
