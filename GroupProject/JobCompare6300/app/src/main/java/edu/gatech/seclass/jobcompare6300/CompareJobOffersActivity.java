package edu.gatech.seclass.jobcompare6300;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class CompareJobOffersActivity extends AppCompatActivity {

    private String comparisonFlag;
    private JobInfo comparableJob1;
    private JobInfo comparableJob2;
    private TextView job1Title;
    private TextView job1Company;
    private TextView job1City;
    private TextView job1State;
    private TextView job1Salary;
    private TextView job1Bonus;
    private TextView job1Leave;
    private TextView job1Stock;
    private TextView job1Home;
    private TextView job1Wellness;
    private TextView job2Title;
    private TextView job2Company;
    private TextView job2City;
    private TextView job2State;
    private TextView job2Salary;
    private TextView job2Bonus;
    private TextView job2Leave;
    private TextView job2Stock;
    private TextView job2Home;
    private TextView job2Wellness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparison_result);
        getSupportActionBar().setTitle("Comparison Result");

        Intent intent = getIntent();
        comparisonFlag = intent.getExtras().getString("Source");
        if (comparisonFlag.equals("Compare offer with current job")) {
            comparableJob1 = EnterJobOffersActivity.getCurrentJob();
            comparableJob2 = EnterJobOffersActivity.getCurrentOffer();
        }
        else if (comparisonFlag.equals("Compare between offers")) {
            comparableJob1 = RankJobOffersActivity.getSelectedJob1();
            comparableJob2 = RankJobOffersActivity.getSelectedJob2();
        }
        //Load 2 comparables differently by entry methods (from Enter Job Offers or from Compare Job Offers)

        job1Title = findViewById(R.id.job1Title);
        job1Company = findViewById(R.id.job1Company);
        job1City = findViewById(R.id.job1City);
        job1State = findViewById(R.id.job1State);
        job1Salary = findViewById(R.id.job1Salary);
        job1Leave = findViewById(R.id.job1Leave);
        job1Bonus = findViewById(R.id.job1Bonus);
        job1Stock = findViewById(R.id.job1Stock);
        job1Home = findViewById(R.id.job1Home);
        job1Wellness = findViewById(R.id.job1Wellness);
        job2Title = findViewById(R.id.job2Title);
        job2Company = findViewById(R.id.job2Company);
        job2City = findViewById(R.id.job2City);
        job2State = findViewById(R.id.job2State);
        job2Salary = findViewById(R.id.job2Salary);
        job2Leave = findViewById(R.id.job2Leave);
        job2Bonus = findViewById(R.id.job2Bonus);
        job2Stock = findViewById(R.id.job2Stock);
        job2Home = findViewById(R.id.job2Home);
        job2Wellness = findViewById(R.id.job2Wellness);

        job1Title.setText(comparableJob1.getTitle());
        job1Company.setText(comparableJob1.getCompany());
        job1City.setText(comparableJob1.getCity());
        job1State.setText(comparableJob1.getState());
        job1Salary.setText(String.valueOf(comparableJob1.getAnnualSalary()));
        job1Leave.setText(String.valueOf(comparableJob1.getLeaveTime()));
        job1Bonus.setText(String.valueOf(comparableJob1.getAnnualBonus()));
        job1Stock.setText(String.valueOf(comparableJob1.getStockOptionOffered()));
        job1Home.setText(String.valueOf(comparableJob1.getHomeBuyingProgFund()));
        job1Wellness.setText(String.valueOf(comparableJob1.getWellnessFund()));
        job2Title.setText(comparableJob2.getTitle());
        job2Company.setText(comparableJob2.getCompany());
        job2City.setText(comparableJob2.getCity());
        job2State.setText(comparableJob2.getState());
        job2Salary.setText(String.valueOf(comparableJob2.getAnnualSalary()));
        job2Leave.setText(String.valueOf(comparableJob2.getLeaveTime()));
        job2Bonus.setText(String.valueOf(comparableJob2.getAnnualBonus()));
        job2Stock.setText(String.valueOf(comparableJob2.getStockOptionOffered()));
        job2Home.setText(String.valueOf(comparableJob2.getHomeBuyingProgFund()));
        job2Wellness.setText(String.valueOf(comparableJob2.getWellnessFund()));
        // Load attributes from 2 comparables and present on GUI
    }

    public void comparisonHandleClick(View view) {
        switch (view.getId()) {
            case R.id.newComparisonBotton:
                Intent newComparisonBotton = new Intent(this, RankJobOffersActivity.class);
                startActivity(newComparisonBotton);
                break;
            case R.id.returnToMainButton:
                Intent returnToMainButton = new Intent(this, MainActivity.class);
                startActivity(returnToMainButton);
                break;
            case R.id.backBotton:
                finish();
                break;
        }
    }
}
