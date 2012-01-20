/**
 * 
 */
package com.chinarewards.gwt.elt.client.gift.request;

import java.util.List;

import net.customware.gwt.dispatch.shared.Result;

import com.chinarewards.elt.model.order.search.OrderVo;
import com.chinarewards.gwt.elt.client.gift.model.OrderSearchVo;

/**
 * @author lw
 * @since 2012年1月20日 19:00:32
 */
public class SearchOrderResponse implements Result {

	private List<OrderSearchVo> result;
	private int total;


	/**
	 * @return the result
	 */
	public List<OrderSearchVo> getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(List<OrderSearchVo> result) {
		this.result = result;
	}

	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
}
