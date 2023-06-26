/**
 * 
 */
package com.Delivery.UsExpress.entites;

import java.util.List;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * @author Acer
 *
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class RiderLocationMessageResponse {
	
    private String destination;
    private List<Rider> data;

}
