package com.theothermattm.goal;

import java.util.List;

import android.util.Log;

import com.theothermattm.goal.domain.Goal;

/**
 * Default implementation of {@link GoalService} which uses a
 * {@link GoalRepository} to persist data.
 * 
 * @author mattm
 * 
 */
public class GoalServiceDefaultImpl implements GoalService {

	private static final String TAG = GoalServiceDefaultImpl.class
			.getSimpleName();

	private GoalRepository goalRepository;

	public GoalServiceDefaultImpl(GoalRepository goalRepository) {
		this.goalRepository = goalRepository;
	}

	private GoalRepository getGoalRepository() {
		return this.goalRepository;
	}

	/*
	 * There's not much here now, admittedly. However, keeping the service layer
	 * here to stick to Domain Driven Design service/repository structure and to
	 * prepare for future manipulation/logic that might need to occur after
	 * pulling out of repository.
	 */

	@Override
	public void addGoal(Goal goal) {
		Log.d(TAG, "Adding goal:" + goal);
		getGoalRepository().addGoal(goal);
	}

	@Override
	public List<Goal> getGoals() {
		Log.d(TAG, "Retrieving all goals from repository.");
		List<Goal> goals = getGoalRepository().getGoals();
		return goals;
	}

}
