package com.theothermattm.goal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import android.util.Log;

import com.theothermattm.goal.domain.Goal;

/**
 * In memory implementation of {@link GoalRepository} which can be used for
 * <em>testing purposes</em>. The thread safety of this repository is sketchy at
 * best.
 * 
 * @author mattm
 * 
 */
public class GoalRepositoryInMemoryImpl implements GoalRepository {

	private static final String TAG = GoalRepositoryInMemoryImpl.class
			.getSimpleName();

	private Set<Goal> goals;

	public GoalRepositoryInMemoryImpl() {
		this.goals = new HashSet<Goal>();
	}

	@Override
	public synchronized void addGoal(Goal goal) {
		Log.w(TAG, "Adding goal to in memory repository:" + goal);
		this.goals.add(goal);

	}

	@Override
	public List<Goal> getGoals() {
		Log.w(TAG, "Retrieving all goals from in memory repository...");

		List<Goal> goalList = new ArrayList<Goal>();

		goalList.addAll(this.goals);

		return Collections.unmodifiableList(goalList);
	}

}
