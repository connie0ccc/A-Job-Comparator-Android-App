package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import android.content.Intent;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    private List<JobInfo> OfferList = new ArrayList<>();
    private CurrentJob currentJob;
    private int jobNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("JobCompare 6300Fall22Team092");

        try {
            currentJob = new RankJobOffersActivity.GetCurrentJob(this).execute().get();
        }
        catch (InterruptedException | ExecutionException e) {
            Toast.makeText(getApplicationContext(), "Failed loading data!",
                    Toast.LENGTH_SHORT).show();
        }
        if(currentJob != null){
            Button EnterCurrentJob = findViewById(R.id.enterCurrentJobButton);
            EnterCurrentJob.setText("Edit Current Job");
        }
        //Change "Enter Current Job" to "Edit Current Job" if current job exists
    }

    public void mainHandleClick(View view){
        switch (view.getId()){
            case R.id.enterCurrentJobButton:
                Intent enterCurrentJobButton
                        = new Intent(MainActivity.this, EnterCurrentJobActivity.class);
                startActivity(enterCurrentJobButton);
                break;

            case R.id.enterJobOffersButton:
                Intent enterJobOffersButton
                        = new Intent(MainActivity.this, EnterJobOffersActivity.class);
                startActivity(enterJobOffersButton);
                break;

            case R.id.adjustComparisonSettingsButton:
                Intent adjustComparisonSettingsButton
                        = new Intent(MainActivity.this, AdjustComparisonSettingsActivity.class);
                startActivity(adjustComparisonSettingsButton);
                break;

            case R.id.compareJobOffersButton:
                try {
                    OfferList = new RankJobOffersActivity.GetJobOffers(this).execute().get();
                }
                catch (InterruptedException | ExecutionException e) {
                    Toast.makeText(getApplicationContext(), "Failed loading data!",
                            Toast.LENGTH_SHORT).show();
                }
                //Load all jobs
                if(OfferList != null && currentJob != null){
                    jobNum = 1 + OfferList.size();
                }
                else if(OfferList != null && currentJob == null){
                    jobNum = OfferList.size();
                }
                else if(OfferList == null && currentJob != null){
                    jobNum = 1;
                }
                else{
                    jobNum = 0;
                }
                //Get total job number
                if (jobNum >= 2){
                    Intent compareJobOffersButton = new Intent(MainActivity.this, RankJobOffersActivity.class);
                    startActivity(compareJobOffersButton);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Please add at least 2 jobs!",
                            Toast.LENGTH_SHORT).show();
                }
                //Check validity: if there is at least 2 jobs
                break;
        }


    }


}