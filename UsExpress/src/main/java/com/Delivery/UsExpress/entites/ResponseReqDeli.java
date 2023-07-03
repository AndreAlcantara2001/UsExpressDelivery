/**
 * 
 */
package com.Delivery.UsExpress.entites;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Acer
 *
 */
@NoArgsConstructor
@Data
@ToString
public class ResponseReqDeli {
	
	private int reqDeliId;
	private List<Rider> filterRiders;
}
