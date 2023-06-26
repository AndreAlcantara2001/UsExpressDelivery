/**
 * 
 */
package com.Delivery.UsExpress.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Delivery.UsExpress.entites.DestinationAddress;


/**
 * @author Acer
 *
 */
@Repository
public interface DestinationAddressRepo extends JpaRepository<DestinationAddress, Integer>{

}