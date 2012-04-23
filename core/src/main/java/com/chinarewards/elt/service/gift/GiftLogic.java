package com.chinarewards.elt.service.gift;

import java.util.List;

import com.chinarewards.elt.domain.gift.Gift;
import com.chinarewards.elt.domain.user.SysUser;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.gift.GiftProcess;
import com.chinarewards.elt.model.gift.search.GiftListVo;
import com.chinarewards.elt.model.gift.search.GiftStatus;
import com.chinarewards.elt.model.user.UserContext;

public interface GiftLogic {
	/**
	 * 保存
	 * @param context
	 * @param gift
	 * @return
	 */
	public Gift save(SysUser caller, Gift gift);

	/**
	 * 查找根据ID
	 * @param id
	 * @return
	 */
	public Gift findGiftById(String id);
	/**
	 * 删除礼品根据ID
	 * @param id
	 * @return
	 */
	public String deleteGift(String id);
	/**
	 * 礼品列表
	 * @param context
	 * @param gift
	 * @return
	 */
	public PageStore<GiftListVo> giftList(SysUser caller,GiftListVo giftVo);
	
	public List<GiftListVo> exportGiftList(SysUser caller, GiftListVo giftVo);
	
	/**
	 * 上下架
	 * @param id
	 * @return
	 */
	public String updateStatus(String id,GiftStatus status);

	public List<Gift> findNotDeleteGift();

	public String createOrUpdateGift(GiftProcess sp, UserContext context);


	
	
}


