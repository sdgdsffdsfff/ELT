package com.chinarewards.elt.service.reward;

import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.reward.search.RewardGridSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardGridVo;
import com.chinarewards.elt.model.user.UserContext;

/**
 * The interface is to provide Reward Grid services.
 * 
 * @author yanrui
 * @since 1.5
 */
public interface RewardGridLogic {

	/**
	 * 我的奖励列表
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardGridVo> fetchRewards_STAFF(UserContext context,
			RewardGridSearchVo criteria);

	/**
	 * STAFF获奖历史栏目
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardGridVo> fetchRewards_STAFF_HISTORY(
			UserContext context, RewardGridSearchVo criteria);

	/**
	 * 我获得的奖励
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardGridVo> fetchRewards_STAFF_GETED(
			UserContext context, RewardGridSearchVo criteria);

	/**
	 * 全部奖励列表
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardGridVo> fetchRewards_ALL(UserContext context,
			RewardGridSearchVo criteria);

	/**
	 * 努力冲奖项
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardGridVo> fetchRewardsItem_STAFF(UserContext context,
			RewardGridSearchVo criteria);

	/**
	 * 公司全部奖项
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardGridVo> fetchRewardsItem_ALL(UserContext context,
			RewardGridSearchVo criteria);

	/**
	 * 我参与的奖项
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardGridVo> fetchRewardsItem_STAFF_PARTAKE(
			UserContext context, RewardGridSearchVo criteria);

	/**
	 * 努力冲奖项
	 * 
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardGridVo> fetchRewardsItem_STAFF_RUSH(
			UserContext context, RewardGridSearchVo criteria);

	/**
	 * 公司其他奖项栏目
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardGridVo> fetchRewardsItem_COMPANY_OTHER(UserContext context,
			RewardGridSearchVo criteria);

	/**
	 * 公司已颁发奖项栏目
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardGridVo> fetchRewardsItem_COMPANY_GETED(UserContext context,
			RewardGridSearchVo criteria);
	
	/**
	 * 控件 我获得的奖项
	 * @param context
	 * @param criteria
	 * @return
	 */
	public PageStore<RewardGridVo> fetchRewardsItem_STAFF_GETED(UserContext context,
			RewardGridSearchVo criteria);

}
