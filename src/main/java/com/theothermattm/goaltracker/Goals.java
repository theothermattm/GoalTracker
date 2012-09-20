package com.theothermattm.goaltracker;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.theothermattm.goal.GoalRepositoryInMemoryImpl;
import com.theothermattm.goal.GoalService;
import com.theothermattm.goal.GoalServiceDefaultImpl;
import com.theothermattm.goal.domain.Goal;

/**
 * Main goals activity.
 * 
 * @author mattm
 * 
 */
public class Goals extends Activity {

	private static final String TAG = Goals.class.getSimpleName();

	private GoalService goalService;

	private Resources resources;
	private EditText newGoalText;
	private Button newGoalButton;
	private ListView goalListView;
	private ArrayAdapter<String> goalsAdapter;
	private List<String> goalItemList;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_goals);

		/*
		 * TODO figure out best way to inject in the right repository. right
		 * now, for getting UI working, use in memory impl.
		 */
		goalService = new GoalServiceDefaultImpl(
				new GoalRepositoryInMemoryImpl());
		newGoalText = (EditText) findViewById(R.id.text_new_goal);
		newGoalButton = (Button) findViewById(R.id.button_add_new_goal);
		goalListView = (ListView) findViewById(R.id.list_view_goals);
		resources = getResources();
		setAddNewGoalListener();
		createGoalListAdapter();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_goals, menu);
		return true;
	}

	private void createGoalListAdapter() {
		goalItemList = getAllGoalsForList();
		/*
		 * TODO investigate using Goals directly into the adapter
		 */
		goalsAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, goalItemList);
		goalListView.setAdapter(goalsAdapter);
	}

	private void refreshGoalListFromService() {

		goalsAdapter.clear();
		List<String> goalStrings = getAllGoalsForList();

		for (String s : goalStrings) {
			goalsAdapter.add(s);
		}

	}

	/**
	 * Retrieves all goals from the {@link GoalService} as a {@link List} of
	 * {@link String}s
	 * 
	 * @return
	 */
	private List<String> getAllGoalsForList() {
		List<String> stringItemList = new ArrayList<String>();

		List<Goal> goals = goalService.getGoals();

		for (Goal goal : goals) {
			stringItemList.add(goal.getName());
		}

		return stringItemList;
	}
	
	private void setAddNewGoalListener() {
		newGoalButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				String goalName = newGoalText.getText().toString().trim();
				Log.d(TAG, "Add new goal clicked with value {" + goalName + "}");

				Goal newGoal = new Goal(goalName);
				goalService.addGoal(newGoal);

				CharSequence toastText = resources
						.getString(R.string.toast_text_new_goal_added);
				Toast confirmToast = Toast.makeText(v.getContext(), toastText,
						Toast.LENGTH_SHORT);
				confirmToast.setGravity(Gravity.CENTER_VERTICAL
						| Gravity.CENTER_HORIZONTAL, 0, 0);
				confirmToast.show();

				refreshGoalListFromService();
				
				newGoalText.setText("");

			}
		});
	}
}
