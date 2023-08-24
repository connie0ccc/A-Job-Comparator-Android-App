package edu.gatech.seclass.jobcompare6300;

import androidx.test.espresso.*;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.CoreMatchers.anything;


@RunWith(AndroidJUnit4.class)
public class UserInterfaceTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityActivityScenarioRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void EnterCurrentJobSave() {
        //Test1: Enter a current job and check if it load correctly
        onView(withId(R.id.enterCurrentJobButton)).perform(click());
        onView(withId(R.id.titleEditText)).perform(clearText(), replaceText("title1"));
        onView(withId(R.id.companyEditText)).perform(clearText(), replaceText("company1"));
        onView(withId(R.id.cityEditText)).perform(clearText(), replaceText("city1"));
        onView(withId(R.id.stateEditText)).perform(clearText(), replaceText("state1"));
        onView(withId(R.id.salaryEditText)).perform(clearText(), replaceText("100000"));
        onView(withId(R.id.bonusEditText)).perform(clearText(), replaceText("20000"));
        onView(withId(R.id.leaveEditText)).perform(clearText(), replaceText("30"));
        onView(withId(R.id.stockEditText)).perform(clearText(), replaceText("50000"));
        onView(withId(R.id.homeEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.wellnessEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.saveBotton)).perform(click());
        onView(withId(R.id.backBotton)).perform(click());

        onView(withId(R.id.enterCurrentJobButton)).perform(click());
        onView(withId(R.id.titleEditText)).check(matches(withText("title1")));
        onView(withId(R.id.companyEditText)).check(matches(withText("company1")));
        onView(withId(R.id.cityEditText)).check(matches(withText("city1")));
        onView(withId(R.id.stateEditText)).check(matches(withText("state1")));
        onView(withId(R.id.salaryEditText)).check(matches(withText("100000.0")));
        onView(withId(R.id.bonusEditText)).check(matches(withText("20000.0")));
        onView(withId(R.id.leaveEditText)).check(matches(withText("30")));
        onView(withId(R.id.stockEditText)).check(matches(withText("50000")));
        onView(withId(R.id.homeEditText)).check(matches(withText("10000.0")));
        onView(withId(R.id.wellnessEditText)).check(matches(withText("10000.0")));

    }

    @Test
    public void EnterCurrentJobNoSave() {
        //Test2: Enter a current job and check if it load correctly modified the title but not saving
        onView(withId(R.id.enterCurrentJobButton)).perform(click());
        onView(withId(R.id.titleEditText)).perform(clearText(), replaceText("title1"));
        onView(withId(R.id.companyEditText)).perform(clearText(), replaceText("company1"));
        onView(withId(R.id.cityEditText)).perform(clearText(), replaceText("city1"));
        onView(withId(R.id.stateEditText)).perform(clearText(), replaceText("state1"));
        onView(withId(R.id.salaryEditText)).perform(clearText(), replaceText("100000"));
        onView(withId(R.id.bonusEditText)).perform(clearText(), replaceText("20000"));
        onView(withId(R.id.leaveEditText)).perform(clearText(), replaceText("30"));
        onView(withId(R.id.stockEditText)).perform(clearText(), replaceText("50000"));
        onView(withId(R.id.homeEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.wellnessEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.saveBotton)).perform(click());
        onView(withId(R.id.backBotton)).perform(click());

        onView(withId(R.id.enterCurrentJobButton)).perform(click());
        onView(withId(R.id.titleEditText)).perform(clearText(), replaceText("Title1Changed"));
        onView(withId(R.id.backBotton)).perform(click());

        onView(withId(R.id.enterCurrentJobButton)).perform(click());
        onView(withId(R.id.titleEditText)).check(matches(withText("title1")));
        onView(withId(R.id.companyEditText)).check(matches(withText("company1")));
        onView(withId(R.id.cityEditText)).check(matches(withText("city1")));
        onView(withId(R.id.stateEditText)).check(matches(withText("state1")));
        onView(withId(R.id.salaryEditText)).check(matches(withText("100000.0")));
        onView(withId(R.id.bonusEditText)).check(matches(withText("20000.0")));
        onView(withId(R.id.leaveEditText)).check(matches(withText("30")));
        onView(withId(R.id.stockEditText)).check(matches(withText("50000")));
        onView(withId(R.id.homeEditText)).check(matches(withText("10000.0")));
        onView(withId(R.id.wellnessEditText)).check(matches(withText("10000.0")));

    }



    @Test
    public void EnterJobOffersCompareCurrentJob(){
        //Test3: Add an offer and check if it is compared with current job correctly
        onView(withId(R.id.enterJobOffersButton)).perform(click());
        onView(withId(R.id.titleEditText)).perform(clearText(), replaceText("title2"));
        onView(withId(R.id.companyEditText)).perform(clearText(), replaceText("company2"));
        onView(withId(R.id.cityEditText)).perform(clearText(), replaceText("city2"));
        onView(withId(R.id.stateEditText)).perform(clearText(), replaceText("state2"));
        onView(withId(R.id.salaryEditText)).perform(clearText(), replaceText("120000"));
        onView(withId(R.id.bonusEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.leaveEditText)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.stockEditText)).perform(clearText(), replaceText("40000"));
        onView(withId(R.id.homeEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.wellnessEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.saveBotton)).perform(click());

        onView(withId(R.id.compareWithCurrentBotton)).perform(click());
        onView(withId(R.id.job1Title)).check(matches(withText("title1")));
        onView(withId(R.id.job1Company)).check(matches(withText("company1")));
        onView(withId(R.id.job1City)).check(matches(withText("city1")));
        onView(withId(R.id.job1State)).check(matches(withText("state1")));
        onView(withId(R.id.job1Salary)).check(matches(withText("100000.0")));
        onView(withId(R.id.job1Bonus)).check(matches(withText("20000.0")));
        onView(withId(R.id.job1Leave)).check(matches(withText("30")));
        onView(withId(R.id.job1Stock)).check(matches(withText("50000")));
        onView(withId(R.id.job1Home)).check(matches(withText("10000.0")));
        onView(withId(R.id.job1Wellness)).check(matches(withText("10000.0")));

        onView(withId(R.id.job2Title)).check(matches(withText("title2")));
        onView(withId(R.id.job2Company)).check(matches(withText("company2")));
        onView(withId(R.id.job2City)).check(matches(withText("city2")));
        onView(withId(R.id.job2State)).check(matches(withText("state2")));
        onView(withId(R.id.job2Salary)).check(matches(withText("120000.0")));
        onView(withId(R.id.job2Bonus)).check(matches(withText("10000.0")));
        onView(withId(R.id.job2Leave)).check(matches(withText("25")));
        onView(withId(R.id.job2Stock)).check(matches(withText("40000")));
        onView(withId(R.id.job2Home)).check(matches(withText("10000.0")));
        onView(withId(R.id.job2Wellness)).check(matches(withText("10000.0")));
        onView(withId(R.id.returnToMainButton)).perform(click());
    }



    @Test
    public void EnterJobOffersAddAnotherOffer(){
        //Test4: Add another offer and check if it is compared with current job correctly
        onView(withId(R.id.enterJobOffersButton)).perform(click());
        onView(withId(R.id.titleEditText)).perform(clearText(), replaceText("title2"));
        onView(withId(R.id.companyEditText)).perform(clearText(), replaceText("company2"));
        onView(withId(R.id.cityEditText)).perform(clearText(), replaceText("city2"));
        onView(withId(R.id.stateEditText)).perform(clearText(), replaceText("state2"));
        onView(withId(R.id.salaryEditText)).perform(clearText(), replaceText("120000"));
        onView(withId(R.id.bonusEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.leaveEditText)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.stockEditText)).perform(clearText(), replaceText("40000"));
        onView(withId(R.id.homeEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.wellnessEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.saveBotton)).perform(click());

        onView(withId(R.id.addOfferBotton)).perform(click());
        onView(withId(R.id.titleEditText)).perform(clearText(), replaceText("title3"));
        onView(withId(R.id.companyEditText)).perform(clearText(), replaceText("company3"));
        onView(withId(R.id.cityEditText)).perform(clearText(), replaceText("city3"));
        onView(withId(R.id.stateEditText)).perform(clearText(), replaceText("state3"));
        onView(withId(R.id.salaryEditText)).perform(clearText(), replaceText("90000"));
        onView(withId(R.id.bonusEditText)).perform(clearText(), replaceText("9000"));
        onView(withId(R.id.leaveEditText)).perform(clearText(), replaceText("25"));
        onView(withId(R.id.stockEditText)).perform(clearText(), replaceText("40000"));
        onView(withId(R.id.homeEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.wellnessEditText)).perform(clearText(), replaceText("10000"));
        onView(withId(R.id.saveBotton)).perform(click());


        onView(withId(R.id.compareWithCurrentBotton)).perform(click());
        onView(withId(R.id.job1Title)).check(matches(withText("title1")));
        onView(withId(R.id.job1Company)).check(matches(withText("company1")));
        onView(withId(R.id.job1City)).check(matches(withText("city1")));
        onView(withId(R.id.job1State)).check(matches(withText("state1")));
        onView(withId(R.id.job1Salary)).check(matches(withText("100000.0")));
        onView(withId(R.id.job1Bonus)).check(matches(withText("20000.0")));
        onView(withId(R.id.job1Leave)).check(matches(withText("30")));
        onView(withId(R.id.job1Stock)).check(matches(withText("50000")));
        onView(withId(R.id.job1Home)).check(matches(withText("10000.0")));
        onView(withId(R.id.job1Wellness)).check(matches(withText("10000.0")));

        onView(withId(R.id.job2Title)).check(matches(withText("title3")));
        onView(withId(R.id.job2Company)).check(matches(withText("company3")));
        onView(withId(R.id.job2City)).check(matches(withText("city3")));
        onView(withId(R.id.job2State)).check(matches(withText("state3")));
        onView(withId(R.id.job2Salary)).check(matches(withText("90000.0")));
        onView(withId(R.id.job2Bonus)).check(matches(withText("9000.0")));
        onView(withId(R.id.job2Leave)).check(matches(withText("25")));
        onView(withId(R.id.job2Stock)).check(matches(withText("40000")));
        onView(withId(R.id.job2Home)).check(matches(withText("10000.0")));
        onView(withId(R.id.job2Wellness)).check(matches(withText("10000.0")));
        onView(withId(R.id.returnToMainButton)).perform(click());
    }

    @Test
    public void AdjustComparisonSettings(){
        //Test5: Edit comparison weights and check if it load correctly next time editing it
        onView(withId(R.id.adjustComparisonSettingsButton)).perform(click());
        onView(withId(R.id.salaryWeightEditText)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.bonusWeightEditText)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.leaveWeightEditText)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.stockWeightEditText)).perform(clearText(), replaceText("2"));
        onView(withId(R.id.homeWeightEditText)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.wellnessWeightEditText)).perform(clearText(), replaceText("1"));
        onView(withId(R.id.saveBotton)).perform(click());
        onView(withId(R.id.backBotton)).perform(click());

        onView(withId(R.id.adjustComparisonSettingsButton)).perform(click());
        onView(withId(R.id.salaryWeightEditText)).check(matches(withText("2")));
        onView(withId(R.id.bonusWeightEditText)).check(matches(withText("2")));
        onView(withId(R.id.leaveWeightEditText)).check(matches(withText("1")));
        onView(withId(R.id.stockWeightEditText)).check(matches(withText("2")));
        onView(withId(R.id.homeWeightEditText)).check(matches(withText("1")));
        onView(withId(R.id.wellnessWeightEditText)).check(matches(withText("1")));
        onView(withId(R.id.backBotton)).perform(click());
    }

    @Test
    public void CompareJobOffers(){
        //Test6: Select two jobs and initiate compare, check if comparable items are correctly loaded
        onView(withId(R.id.compareJobOffersButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.rankJobsListView)).atPosition(0).perform(click());
        onData(anything()).inAdapterView(withId(R.id.rankJobsListView)).atPosition(1).perform(click());
        onView(withId(R.id.compareJobsButton)).perform(click());
        onView(withId(R.id.job1Title)).check(matches(withText("title1")));
        onView(withId(R.id.job1Company)).check(matches(withText("company1")));
        onView(withId(R.id.job1City)).check(matches(withText("city1")));
        onView(withId(R.id.job1State)).check(matches(withText("state1")));
        onView(withId(R.id.job1Salary)).check(matches(withText("100000.0")));
        onView(withId(R.id.job1Bonus)).check(matches(withText("20000.0")));
        onView(withId(R.id.job1Leave)).check(matches(withText("30")));
        onView(withId(R.id.job1Stock)).check(matches(withText("50000")));
        onView(withId(R.id.job1Home)).check(matches(withText("10000.0")));
        onView(withId(R.id.job1Wellness)).check(matches(withText("10000.0")));

        onView(withId(R.id.job2Title)).check(matches(withText("title2")));
        onView(withId(R.id.job2Company)).check(matches(withText("company2")));
        onView(withId(R.id.job2City)).check(matches(withText("city2")));
        onView(withId(R.id.job2State)).check(matches(withText("state2")));
        onView(withId(R.id.job2Salary)).check(matches(withText("120000.0")));
        onView(withId(R.id.job2Bonus)).check(matches(withText("10000.0")));
        onView(withId(R.id.job2Leave)).check(matches(withText("25")));
        onView(withId(R.id.job2Stock)).check(matches(withText("40000")));
        onView(withId(R.id.job2Home)).check(matches(withText("10000.0")));
        onView(withId(R.id.job2Wellness)).check(matches(withText("10000.0")));
    }

    @Test
    public void CompareJobOffersCurrentJob(){
        //Test7: Current job label exists in list view at max once
        onView(withId(R.id.compareJobOffersButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.rankJobsListView)).atPosition(1)
                .onChildView(withId(R.id.isCurrentJob)).check(matches(withText("Current Job")));
        onData(anything()).inAdapterView(withId(R.id.rankJobsListView)).atPosition(0)
                .onChildView(withId(R.id.isCurrentJob)).check(matches(withText("")));
        onData(anything()).inAdapterView(withId(R.id.rankJobsListView)).atPosition(2)
                .onChildView(withId(R.id.isCurrentJob)).check(matches(withText("")));
    }

    @Test
    public void CompareJobsRankingAlgorithmJobScore(){
        //Test8: Validate ranking calculation of job score
        onView(withId(R.id.compareJobOffersButton)).perform(click());
        onData(anything()).inAdapterView(withId(R.id.rankJobsListView)).atPosition(0)
                .onChildView(withId(R.id.score)).check(matches(withText("36837")));
        onData(anything()).inAdapterView(withId(R.id.rankJobsListView)).atPosition(1)
                .onChildView(withId(R.id.score)).check(matches(withText("35726")));
        onData(anything()).inAdapterView(withId(R.id.rankJobsListView)).atPosition(2)
                .onChildView(withId(R.id.score)).check(matches(withText("29628")));
    }

}
