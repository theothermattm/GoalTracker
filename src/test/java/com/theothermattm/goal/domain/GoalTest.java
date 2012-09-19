package com.theothermattm.goal.domain;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;

import org.junit.Test;

/**
 * Unit tests for {@link Goal}
 * 
 * @author mattm
 * 
 */
public class GoalTest {

	@Test
	public void equalsConsidersEverythingButId() {
		// given
		Goal aGoal = getDummyGoal();
		String anId = "id1";
		aGoal.setId(anId);
		Goal aDifferentGoal = getDummyGoal();
		String aDifferentId = "id2";
		aDifferentGoal.setId(aDifferentId);

		// when
		boolean firstComparisonResult = aGoal.equals(aDifferentGoal);
		boolean secondComparisonResult = aDifferentGoal.equals(aGoal);

		// then
		assertThat("When only a goal's id is different, they should be equal",
				firstComparisonResult, is(true));
		assertThat("The equals method should be equal for both objects",
				secondComparisonResult, is(true));
	}

	@Test
	public void hashCodeConsidersEverythingButId() {
		// given
		Goal aGoal = getDummyGoal();
		String anId = "id1";
		aGoal.setId(anId);
		Goal aDifferentGoal = getDummyGoal();
		String aDifferentId = "id2";
		aDifferentGoal.setId(aDifferentId);

		// when
		int firstHashCode = aGoal.hashCode();
		int secondHashCode = aDifferentGoal.hashCode();
		// then

		assertThat(
				"When only a goal's id is different, the hash codes should be the same.",
				firstHashCode, is(secondHashCode));
	}

	/**
	 * Retrieves a dummy goal for testing.
	 * 
	 * @return
	 */
	private Goal getDummyGoal() {
		Goal testGoal = new Goal("dummyName");
		Date testDate = new Date();
		testGoal.setDueDate(testDate);
		String testNotes = "blah blah blah";
		testGoal.setNotes(testNotes);

		return testGoal;
	}

}
