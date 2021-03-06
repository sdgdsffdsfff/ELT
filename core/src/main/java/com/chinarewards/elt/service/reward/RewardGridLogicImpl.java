package com.chinarewards.elt.service.reward;

import java.util.ArrayList;
import java.util.List;
import com.chinarewards.elt.dao.reward.CandidateDao;
import com.chinarewards.elt.dao.reward.PreWinnerDao;
import com.chinarewards.elt.dao.reward.RewardItemDao;
import com.chinarewards.elt.dao.reward.WinnerDao;
import com.chinarewards.elt.domain.org.Staff;
import com.chinarewards.elt.domain.reward.base.Reward;
import com.chinarewards.elt.domain.reward.base.RewardItem;
import com.chinarewards.elt.domain.reward.person.Candidate;
import com.chinarewards.elt.domain.reward.person.Judge;
import com.chinarewards.elt.domain.reward.person.NomineeLot;
import com.chinarewards.elt.domain.reward.person.PreWinnerLot;
import com.chinarewards.elt.domain.reward.person.Winner;
import com.chinarewards.elt.domain.reward.rule.CandidateRule;
import com.chinarewards.elt.model.common.PageStore;
import com.chinarewards.elt.model.common.PaginationDetail;
import com.chinarewards.elt.model.reward.base.RewardStatus;
import com.chinarewards.elt.model.reward.search.RewardGridSearchVo;
import com.chinarewards.elt.model.reward.search.RewardItemSearchVo;
import com.chinarewards.elt.model.reward.search.RewardSearchVo;
import com.chinarewards.elt.model.reward.vo.RewardGridVo;
import com.chinarewards.elt.model.user.UserContext;
import com.chinarewards.elt.service.reward.rule.CandidateLogic;
import com.chinarewards.elt.service.reward.rule.CandidateRuleLogic;
import com.chinarewards.elt.service.reward.rule.JudgeLogic;
import com.chinarewards.elt.service.reward.rule.NomineeLogic;
import com.chinarewards.elt.service.reward.rule.WinnerLogic;
import com.chinarewards.elt.util.StringUtil;
import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

/**
 * The implementation of {@link RewardLogic}
 * 
 * 员工天地 奖励控件 获奖历史
 * 
 * @author yanrui
 * @since 1.5
 */
@Transactional
public class RewardGridLogicImpl implements RewardGridLogic {

	private WinnerDao winnerDao;
	private PreWinnerDao preWinnerDao;
	private RewardItemDao rewardItemDao;
	private CandidateDao candidateDao;
	private CandidateRuleLogic candidateRuleLogic;
	private CandidateLogic candidateLogic;
	private JudgeLogic judgeLogic;
	private NomineeLogic nomineeLogic;
	private WinnerLogic winnerLogic;

	@Inject
	public RewardGridLogicImpl(WinnerDao winnerDao, PreWinnerDao preWinnerDao,
			RewardItemDao rewardItemDao, CandidateDao candidateDao,
			CandidateRuleLogic candidateRuleLogic,
			CandidateLogic candidateLogic, JudgeLogic judgeLogic,
			NomineeLogic nomineeLogic, WinnerLogic winnerLogic) {
		this.winnerDao = winnerDao;
		this.preWinnerDao = preWinnerDao;
		this.rewardItemDao = rewardItemDao;
		this.candidateDao = candidateDao;
		this.candidateRuleLogic = candidateRuleLogic;
		this.candidateLogic = candidateLogic;
		this.judgeLogic = judgeLogic;
		this.nomineeLogic = nomineeLogic;
		this.winnerLogic = winnerLogic;
	}

	@Override
	public PageStore<RewardGridVo> fetchRewards_STAFF(UserContext context,
			RewardGridSearchVo criteria) {
		PageStore<RewardGridVo> pageStore = new PageStore<RewardGridVo>();

		RewardSearchVo rewardSearchVo = new RewardSearchVo();
		rewardSearchVo.setWinnerStaffName(criteria.getStaffName());
		rewardSearchVo.setRewardItemId(criteria.getRewardItemId());
		rewardSearchVo.setRewardsTime(criteria.getRewardsDate());

		
		// List<Reward> rewardList = rewardDao.searchRewardsData_staff(staffId,
		// rewardSearchVo);

		List<Winner> winnerlist = winnerDao
				.queryCurrentStaffWinRewardData(rewardSearchVo);

		int resultCount = winnerlist.size();

		pageStore.setResultList(convertToGridVoListFromWinner(winnerlist));
		pageStore.setResultCount(resultCount);

		return pageStore;
	}

	@Override
	public PageStore<RewardGridVo> fetchRewards_STAFF_GETED(
			UserContext context, RewardGridSearchVo criteria) {
		PageStore<RewardGridVo> pageStore = new PageStore<RewardGridVo>();

		RewardSearchVo searchVo = new RewardSearchVo();
		searchVo.setWinnerStaffId(criteria.getStaffId());
		searchVo.setWinnerStaffName(criteria.getStaffName());		

		List<Winner> winnerlist = winnerDao
				.queryCurrentStaffWinRewardData(searchVo);
		int resultCount = winnerlist.size();
		pageStore.setResultList(convertToGridVoListFromWinner(winnerlist));
		
		
		pageStore.setResultCount(resultCount);

		return pageStore;
	}
	
	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_STAFF_GETED(
			UserContext context, RewardGridSearchVo criteria) {
		PageStore<RewardGridVo> pageStore = new PageStore<RewardGridVo>();

		RewardSearchVo searchVo = new RewardSearchVo();
		searchVo.setWinnerStaffId(criteria.getStaffId());
		searchVo.setWinnerStaffName(criteria.getStaffName());
		
		PaginationDetail detail=new PaginationDetail();
		detail.setStart(0);
		detail.setLimit(10);
		searchVo.setPaginationDetail(detail);
		
		
		List<RewardItem> list=winnerDao.queryCurrentStaffWinRewardItemData(searchVo);
		
		
		List<RewardGridVo> rewardGridVoList = new ArrayList<RewardGridVo>();
		for (int i = 0; i < list.size(); i++) {
			RewardItem rewardItem = list.get(i);
			if (rewardItem != null) {
				RewardGridVo rewardGridVo = new RewardGridVo();
				rewardGridVo.setRewardItem(rewardItem);
				rewardGridVo.setRewardItemId(rewardItem.getId());
				rewardGridVo.setRewardItemName(rewardItem.getName());	
				rewardGridVoList.add(rewardGridVo);
			}
		}
		int resultCount = rewardGridVoList.size();
		pageStore.setResultCount(resultCount);
		pageStore.setResultList(rewardGridVoList);

		return pageStore;
	}
	
	//小控件 全部奖项
	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_COMPANY_GETED(
			UserContext context, RewardGridSearchVo criteria) {
		PageStore<RewardGridVo> pageStore = new PageStore<RewardGridVo>();

		RewardItemSearchVo rewardItemSearchVo = new RewardItemSearchVo();
		PaginationDetail pagination = new PaginationDetail();
		pagination.setStart(0);
		pagination.setLimit(50);
		rewardItemSearchVo.setPaginationDetail(pagination);
		List<RewardItem> list = winnerDao.queryCurrentCompanyWinRewardItemData(rewardItemSearchVo);
		List<RewardGridVo> rewardGridVoList = new ArrayList<RewardGridVo>();
		for (int i = 0; i < list.size(); i++) {
			RewardItem rewardItem = list.get(i);
			if (rewardItem != null) {
				RewardGridVo rewardGridVo = new RewardGridVo();
				rewardGridVo.setRewardItem(rewardItem);
				rewardGridVo.setRewardItemId(rewardItem.getId());
				rewardGridVo.setRewardItemName(rewardItem.getName());	
				rewardGridVoList.add(rewardGridVo);
			}
		}

		pageStore.setResultList(rewardGridVoList);
		pageStore.setResultCount(rewardGridVoList.size());

		return pageStore;
	}

	
	//菜单 获奖历史Rewards_STAFF_HISTORY
	@Override
	public PageStore<RewardGridVo> fetchRewards_STAFF_HISTORY(
			UserContext context, RewardGridSearchVo criteria) {
		PageStore<RewardGridVo> pageStore = new PageStore<RewardGridVo>();

		List<PreWinnerLot> winnerlist = preWinnerDao.queryRewardHistoryData(criteria);
		int resultCount = preWinnerDao.queryRewardHistoryCount(criteria);

		System.out.println("==================fetchRewards_STAFF_HISTORY=============resultCount:"+resultCount);
		
	
		List<RewardGridVo> gridVoList=convertToGridVoListFromPreWinner(winnerlist);
		pageStore.setResultList(gridVoList);
		pageStore.setResultCount(resultCount);

		return pageStore;
	}

	@Override
	public PageStore<RewardGridVo> fetchRewards_ALL(UserContext context,
			RewardGridSearchVo criteria) {
		PageStore<RewardGridVo> pageStore = new PageStore<RewardGridVo>();

		criteria.setStaffId(null);
		criteria.setStaffName(null);
		List<PreWinnerLot> winnerlist = preWinnerDao.queryRewardHistoryData(criteria);

		int resultCount = winnerlist.size();

		pageStore.setResultList(convertToGridVoListFromPreWinner(winnerlist));
		pageStore.setResultCount(resultCount);

		return pageStore;
	}

	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_STAFF(UserContext context,
			RewardGridSearchVo criteria) {
		PageStore<RewardGridVo> pageStore = new PageStore<RewardGridVo>();

		RewardItemSearchVo rewardItemSearchVo = new RewardItemSearchVo();
		List<Winner> list = winnerDao
				.queryCurrentStaffWinRewardItemData(rewardItemSearchVo);

		List<RewardGridVo> rewardGridVoList = new ArrayList<RewardGridVo>();
		for (int i = 0; i < list.size(); i++) {
			Winner winner = list.get(i);
			if (winner != null) {

				RewardGridVo rewardGridVo = new RewardGridVo();
				Reward reward = winner.getReward();
				rewardGridVo.setReward(reward);
				rewardGridVo.setRewardId(reward.getId());
				rewardGridVo.setRewardName(reward.getName());
				rewardGridVo.setRewardsDate(reward.getAwardDate());
				rewardGridVo.setAwardAmt(reward.getAwardAmt());
				rewardGridVo.setAwardName(reward.getCreatedBy().getStaff()
						.getName());// 颁奖人

				// 提名人
				List<NomineeLot> nomineeLots = nomineeLogic
						.getNomineeLotsFromReward(reward.getId());
				rewardGridVo.setNomineeLotList(nomineeLots);

				RewardItem rewardItem = reward.getRewardItem();
				rewardGridVo.setRewardItem(rewardItem);
				rewardGridVo.setRewardItemId(rewardItem.getId());
				rewardGridVo.setRewardItemName(rewardItem.getName());
				rewardGridVo.setRewardsItemCreateBy(rewardItem.getCreatedBy()
						.getStaff().getName());// 奖项创建人

				Staff staff = winner.getStaff();
				if (staff != null) {
					Candidate candiate = candidateDao
							.findCandidateByStaffRewardId(reward.getId(),
									staff.getId());
					if (candiate != null) {
						int nominateCount = candiate.getNominatecount();
						rewardGridVo.setNominateCount(nominateCount);
					}
				}
				rewardGridVoList.add(rewardGridVo);
			}

		}

		pageStore.setResultList(rewardGridVoList);
		pageStore.setResultCount(rewardGridVoList.size());

		return pageStore;
	}

	
	//菜单 我参与的 RewardsItem_STAFF_PARTAKE
	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_STAFF_PARTAKE(
			UserContext context, RewardGridSearchVo criteria) {
		PageStore<RewardGridVo> pageStore = new PageStore<RewardGridVo>();

		String staffId = criteria.getStaffId();
		List<Candidate> list = candidateDao.findCandidateByStaffId(staffId);

		List<RewardGridVo> rewardGridVoList = new ArrayList<RewardGridVo>();
		for (int i = 0; i < list.size(); i++) {
			Candidate candidate = list.get(i);
			if (candidate != null) {

				RewardGridVo rewardGridVo = new RewardGridVo();
				Reward reward = candidate.getReward();
				rewardGridVo.setReward(reward);
				rewardGridVo.setRewardId(reward.getId());
				rewardGridVo.setRewardName(reward.getName());
				rewardGridVo.setRewardsDate(reward.getAwardDate());
				rewardGridVo.setAwardAmt(reward.getAwardAmt());
				rewardGridVo.setAwardName(reward.getCreatedBy().getStaff()
						.getName());// 颁奖人

				// 提名人
				List<NomineeLot> nomineeLots = nomineeLogic
						.getNomineeLotsFromReward(reward.getId());
				rewardGridVo.setNomineeLotList(nomineeLots);

				RewardItem rewardItem = reward.getRewardItem();
				rewardGridVo.setRewardItem(rewardItem);
				rewardGridVo.setRewardItemId(rewardItem.getId());
				rewardGridVo.setRewardItemName(rewardItem.getName());
				rewardGridVo.setRewardsItemCreateBy(rewardItem.getCreatedBy()
						.getStaff().getName());// 奖项创建人

				Staff staff = candidate.getStaff();
				if (staff != null) {
					Candidate candiate = candidateDao
							.findCandidateByStaffRewardId(reward.getId(),
									staff.getId());
					if (candiate != null) {
						int nominateCount = candiate.getNominatecount();
						rewardGridVo.setNominateCount(nominateCount);
					}
				}
				rewardGridVoList.add(rewardGridVo);
			}

		}

		pageStore.setResultList(rewardGridVoList);
		pageStore.setResultCount(rewardGridVoList.size());

		return pageStore;
	}

	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_STAFF_RUSH(
			UserContext context, RewardGridSearchVo criteria) {
		PageStore<RewardGridVo> pageStore = new PageStore<RewardGridVo>();

		String staffId = criteria.getStaffId();
		List<RewardStatus> rstatus = new ArrayList<RewardStatus>();
		rstatus.add(RewardStatus.NEW);
		rstatus.add(RewardStatus.PENDING_NOMINATE);
		rstatus.add(RewardStatus.PENDING_APPLICATION);
		List<Candidate> list = candidateDao.findCandidateByStaffIdAndStatus(
				staffId, rstatus);

		List<RewardGridVo> rewardGridVoList = new ArrayList<RewardGridVo>();
		for (int i = 0; i < list.size(); i++) {
			Candidate candidate = list.get(i);
			if (candidate != null) {

				RewardGridVo rewardGridVo = new RewardGridVo();
				Reward reward = candidate.getReward();
				rewardGridVo.setReward(reward);
				rewardGridVo.setRewardId(reward.getId());
				rewardGridVo.setRewardName(reward.getName());
				rewardGridVo.setRewardsDate(reward.getAwardDate());
				rewardGridVo.setAwardAmt(reward.getAwardAmt());
				rewardGridVo.setAwardName(reward.getCreatedBy().getStaff()
						.getName());// 颁奖人
				rewardGridVo.setRewardStatusName(reward.getStatus().getDisplayName());

				// 提名人
				List<NomineeLot> nomineeLots = nomineeLogic
						.getNomineeLotsFromReward(reward.getId());
				rewardGridVo.setNomineeLotList(nomineeLots);

				RewardItem rewardItem = reward.getRewardItem();
				rewardGridVo.setRewardItem(rewardItem);
				rewardGridVo.setRewardItemId(rewardItem.getId());
				rewardGridVo.setRewardItemName(rewardItem.getName());
				rewardGridVo.setRewardsItemCreateBy(rewardItem.getCreatedBy()
						.getStaff().getName());// 奖项创建人

				Staff staff = candidate.getStaff();
				if (staff != null) {
					Candidate candiate = candidateDao
							.findCandidateByStaffRewardId(reward.getId(),
									staff.getId());
					if (candiate != null) {
						int nominateCount = candiate.getNominatecount();
						rewardGridVo.setNominateCount(nominateCount);
					}
				}
				rewardGridVoList.add(rewardGridVo);
			}

		}

		pageStore.setResultList(rewardGridVoList);
		pageStore.setResultCount(rewardGridVoList.size());

		return pageStore;
	}

	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_COMPANY_OTHER(
			UserContext context, RewardGridSearchVo criteria) {
		PageStore<RewardGridVo> pageStore = new PageStore<RewardGridVo>();

		String staffId = criteria.getStaffId();
		List<RewardStatus> rstatus = new ArrayList<RewardStatus>();
		String thisAction = criteria.getThisAction();
		if (!StringUtil.isEmptyString(thisAction)) {
			if ("RewardsItem_ALL".equals(thisAction)
					|| "RewardsItem_COMPANY_OTHER".equals(thisAction)) {// 全部
				rstatus.add(RewardStatus.NEW);
				rstatus.add(RewardStatus.PENDING_NOMINATE);
				rstatus.add(RewardStatus.PENDING_APPLICATION);
				rstatus.add(RewardStatus.REWARDED);
				rstatus.add(RewardStatus.DENIED);
			} else if ("RewardsItem_GETED".equals(thisAction)) {// 已获得
				rstatus.add(RewardStatus.REWARDED);
			} else if ("RewardsItem_RUSH".equals(thisAction)) {// 努力冲
				rstatus.add(RewardStatus.NEW);
				rstatus.add(RewardStatus.PENDING_NOMINATE);
				rstatus.add(RewardStatus.PENDING_APPLICATION);
			} else {
				return pageStore;
			}
		} else {
			return pageStore;
		}

		List<Candidate> list = candidateDao.findCandidateByStaffIdAndStatus(
				staffId, rstatus);

		List<RewardGridVo> rewardGridVoList = new ArrayList<RewardGridVo>();
		for (int i = 0; i < list.size(); i++) {
			Candidate candidate = list.get(i);
			if (candidate != null) {

				RewardGridVo rewardGridVo = new RewardGridVo();
				Reward reward = candidate.getReward();
				rewardGridVo.setReward(reward);
				rewardGridVo.setRewardId(reward.getId());
				rewardGridVo.setRewardName(reward.getName());
				rewardGridVo.setRewardsDate(reward.getAwardDate());
				rewardGridVo.setAwardAmt(reward.getAwardAmt());
				rewardGridVo.setAwardName(reward.getCreatedBy().getStaff()
						.getName());// 颁奖人

				if (RewardStatus.NEW.equals(reward.getStatus())
						|| RewardStatus.PENDING_NOMINATE.equals(reward
								.getStatus())
						|| RewardStatus.PENDING_APPLICATION.equals(reward
								.getStatus())) {
					rewardGridVo.setRewardStatusName("努力冲奖项");
				} else if (RewardStatus.REWARDED.equals(reward.getStatus())) {
					rewardGridVo.setRewardStatusName("已获得奖项");
				} else {
					rewardGridVo.setRewardStatusName(reward.getStatus().name());
				}

				// 提名人
				List<NomineeLot> nomineeLots = nomineeLogic
						.getNomineeLotsFromReward(reward.getId());
				rewardGridVo.setNomineeLotList(nomineeLots);

				RewardItem rewardItem = reward.getRewardItem();
				rewardGridVo.setRewardItem(rewardItem);
				rewardGridVo.setRewardItemId(rewardItem.getId());
				rewardGridVo.setRewardItemName(rewardItem.getName());
				rewardGridVo.setRewardsItemCreateBy(rewardItem.getCreatedBy()
						.getStaff().getName());// 奖项创建人

				Staff staff = candidate.getStaff();
				if (staff != null) {
					Candidate candiate = candidateDao
							.findCandidateByStaffRewardId(reward.getId(),
									staff.getId());
					if (candiate != null) {
						int nominateCount = candiate.getNominatecount();
						rewardGridVo.setNominateCount(nominateCount);
					}
				}
				rewardGridVoList.add(rewardGridVo);
			}

		}

		pageStore.setResultList(rewardGridVoList);
		pageStore.setResultCount(rewardGridVoList.size());

		return pageStore;
	}

	@Override
	public PageStore<RewardGridVo> fetchRewardsItem_ALL(UserContext context,
			RewardGridSearchVo criteria) {
		PageStore<RewardGridVo> pageStore = new PageStore<RewardGridVo>();

		RewardItemSearchVo itemSearchVo = new RewardItemSearchVo();
		List<RewardItem> rewardItemList = rewardItemDao
				.fetchRewardsItems(itemSearchVo);

		int resultCount = rewardItemList.size();

		pageStore.setResultList(convertToGridVoListFromItem(rewardItemList));
		pageStore.setResultCount(resultCount);

		return pageStore;
	}

	private List<RewardGridVo> convertToGridVoListFromPreWinner(
			List<PreWinnerLot> preWinnerLotList) {
		List<RewardGridVo> gridVoList = new ArrayList<RewardGridVo>();

		if (preWinnerLotList != null) {
			for (int i = 0; i < preWinnerLotList.size(); i++) {
				PreWinnerLot preWinnerLot = preWinnerLotList.get(i);
				Reward reward = preWinnerLot.getReward();
				if (reward != null) {
					String rewardId = reward.getId();

					RewardGridVo rewardGridVo = new RewardGridVo();
					rewardGridVo.setRewardId(rewardId);
					rewardGridVo.setRewardName(reward.getName());
					rewardGridVo.setRewardsDate(preWinnerLot.getCreatedAt());
					rewardGridVo.setAwardName(preWinnerLot.getCreatedBy()
							.getStaff().getName());

					rewardGridVo.setAwardAmt(reward.getAwardAmt());

					RewardItem rewardItem = reward.getRewardItem();
					rewardGridVo.setRewardItem(rewardItem);
					rewardGridVo.setRewardItemId(rewardItem.getId());
					rewardGridVo.setRewardItemName(rewardItem.getName());
					rewardGridVo.setRewardsItemCreateBy(rewardItem
							.getCreatedBy().getStaff().getName());// 奖项创建人

					// candidate rule
					CandidateRule candidateRule = candidateRuleLogic
							.findCandidateRuleFromReward(rewardId);
					// candidate list
					List<Candidate> candidates = candidateLogic
							.getCandidatesFromReward(rewardId);
					// Judge list
					List<Judge> judges = judgeLogic
							.findJudgesFromReward(rewardId);
					// nominee lot
					List<NomineeLot> nomineeLots = nomineeLogic
							.getNomineeLotsFromReward(rewardId);
					// winner
					List<Winner> winners = winnerLogic
							.getWinnersOfReward(rewardId);

					rewardGridVo.setReward(reward);
					rewardGridVo.setCandidateRule(candidateRule);
					rewardGridVo.setCandidateList(candidates);
					rewardGridVo.setJudgeList(judges);
					rewardGridVo.setNomineeLotList(nomineeLots);
					rewardGridVo.setWinnerList(winners);

					gridVoList.add(rewardGridVo);
				}
			}
		}
		return gridVoList;
	}

	private List<RewardGridVo> convertToGridVoListFromWinner(
			List<Winner> winnerList) {
		List<RewardGridVo> gridVoList = new ArrayList<RewardGridVo>();

		if (winnerList != null) {
			for (int i = 0; i < winnerList.size(); i++) {
				Winner winner = winnerList.get(i);
				Reward reward = winner.getReward();
				if (reward != null) {
					String rewardId = reward.getId();

					RewardGridVo rewardGridVo = new RewardGridVo();
					rewardGridVo.setRewardId(rewardId);
					rewardGridVo.setRewardName(reward.getName());
					rewardGridVo.setRewardsDate(winner.getCreatedAt());
					rewardGridVo.setAwardName(winner.getCreatedBy().getStaff()
							.getName());

					rewardGridVo.setAwardAmt(reward.getAwardAmt());

					RewardItem rewardItem = reward.getRewardItem();
					rewardGridVo.setRewardItem(rewardItem);
					rewardGridVo.setRewardItemId(rewardItem.getId());
					rewardGridVo.setRewardItemName(rewardItem.getName());
					rewardGridVo.setRewardsItemCreateBy(rewardItem
							.getCreatedBy().getStaff().getName());// 奖项创建人

					// candidate rule
					CandidateRule candidateRule = candidateRuleLogic
							.findCandidateRuleFromReward(rewardId);
					// candidate list
					List<Candidate> candidates = candidateLogic
							.getCandidatesFromReward(rewardId);
					// Judge list
					List<Judge> judges = judgeLogic
							.findJudgesFromReward(rewardId);
					// nominee lot
					List<NomineeLot> nomineeLots = nomineeLogic
							.getNomineeLotsFromReward(rewardId);
					// winner
					List<Winner> winners = winnerLogic
							.getWinnersOfReward(rewardId);

					rewardGridVo.setReward(reward);
					rewardGridVo.setCandidateRule(candidateRule);
					rewardGridVo.setCandidateList(candidates);
					rewardGridVo.setJudgeList(judges);
					rewardGridVo.setNomineeLotList(nomineeLots);
					rewardGridVo.setWinnerList(winners);

					gridVoList.add(rewardGridVo);
				}
			}
		}
		return gridVoList;
	}

	private List<RewardGridVo> convertToGridVoListFromReward(
			List<Reward> rewardList) {
		List<RewardGridVo> gridVoList = new ArrayList<RewardGridVo>();

		if (rewardList != null) {
			for (int i = 0; i < rewardList.size(); i++) {
				Reward reward = rewardList.get(i);
				if (reward != null) {
					String rewardId = reward.getId();

					RewardGridVo rewardGridVo = new RewardGridVo();
					rewardGridVo.setRewardId(rewardId);
					rewardGridVo.setRewardName(reward.getName());

					// candidate rule
					CandidateRule candidateRule = candidateRuleLogic
							.findCandidateRuleFromReward(rewardId);
					// candidate list
					List<Candidate> candidates = candidateLogic
							.getCandidatesFromReward(rewardId);
					// Judge list
					List<Judge> judges = judgeLogic
							.findJudgesFromReward(rewardId);
					// nominee lot
					List<NomineeLot> nomineeLots = nomineeLogic
							.getNomineeLotsFromReward(rewardId);
					// winner
					List<Winner> winners = winnerLogic
							.getWinnersOfReward(rewardId);

					rewardGridVo.setReward(reward);
					rewardGridVo.setCandidateRule(candidateRule);
					rewardGridVo.setCandidateList(candidates);
					rewardGridVo.setJudgeList(judges);
					rewardGridVo.setNomineeLotList(nomineeLots);
					rewardGridVo.setWinnerList(winners);
					// rewardGridVo.setRewardsDate(win.getCreatedAt());

					gridVoList.add(rewardGridVo);
				}
			}
		}
		return gridVoList;
	}

	private List<RewardGridVo> convertToGridVoListFromItem(
			List<RewardItem> itemList) {
		List<RewardGridVo> gridVoList = new ArrayList<RewardGridVo>();

		if (itemList != null) {
			for (int i = 0; i < itemList.size(); i++) {
				RewardItem rewardItem = itemList.get(i);
				if (rewardItem != null) {
					RewardGridVo rewardGridVo = new RewardGridVo();
					rewardGridVo.setRewardItemId(rewardItem.getId());
					rewardGridVo.setRewardItemName(rewardItem.getName());
					rewardGridVo.setAwardAmt(rewardItem.getAwardAmt());
					// rewardGridVo.setRewardItemPhoto(rewardItem.getPhoto());
					rewardGridVo.setRewardsItemCreateBy(rewardItem
							.getCreatedBy().getStaff().getName());// 奖项创建人
					gridVoList.add(rewardGridVo);

				}
			}
		}
		return gridVoList;
	}
}
