package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

public class EnterCurrentJobActivity extends AppCompatActivity{

    private EditText currentTitle;
    private EditText currentCompany;
    private EditText currentCity;
    private EditText currentState;
    private EditText currentAnnualSalary;
    private EditText currentAnnualBonus;
    private EditText currentLeave;
    private EditText currentStockOption;
    private EditText currentHomeFund;
    private EditText currentWellnessFund;
    private CurrentJob currentJob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_current_job);
        getSupportActionBar().setTitle("Current Job");

        currentTitle = findViewById(R.id.titleEditText);
        currentCompany = findViewById(R.id.companyEditText);
        currentCity = findViewById(R.id.cityEditText);
        currentState = findViewById(R.id.stateEditText);
        currentAnnualSalary = findViewById(R.id.salaryEditText);
        currentAnnualBonus = findViewById(R.id.bonusEditText);
        currentLeave = findViewById(R.id.leaveEditText);
        currentStockOption = findViewById(R.id.stockEditText);
        currentHomeFund = findViewById(R.id.homeEditText);
        currentWellnessFund = findViewById(R.id.wellnessEditText);

        try {
            currentJob = new GetCurrentJob(EnterCurrentJobActivity.this).execute().get();
            // Load current job entity (if previously inserted), otherwise remains null
        }
        catch (InterruptedException | ExecutionException e) {
            Toast.makeText(getApplicationContext(), "Failed loading current job!",
                        Toast.LENGTH_SHORT).show();
        }

        if(currentJob != null){
        currentTitle.setText(currentJob.getTitle());
        currentCompany.setText(currentJob.getCompany());
        currentCity.setText(currentJob.getCity());
        currentState.setText(currentJob.getState());
        currentAnnualSalary.setText(String.valueOf(currentJob.getAnnualSalary()));
        currentAnnualBonus.setText(String.valueOf(currentJob.getAnnualBonus()));
        currentLeave.setText(String.valueOf(currentJob.getLeaveTime()));
        currentStockOption.setText(String.valueOf(currentJob.getStockOptionOffered()));
        currentHomeFund.setText(String.valueOf(currentJob.getHomeBuyingProgFund()));
        currentWellnessFund.setText(String.valueOf(currentJob.getWellnessFund()));
        }
        //Filled EditTexts with attributes from previously entered information
    }

    public void save(){
        if (currentTitle.getText().toString().isEmpty() ||
                currentCompany.getText().toString().isEmpty() ||
                currentCity.getText().toString().isEmpty() ||
                currentState.getText().toString().isEmpty() ||
                currentAnnualSalary.getText().toString().isEmpty() ||
                currentAnnualBonus.getText().toString().isEmpty() ||
                currentLeave.getText().toString().isEmpty() ||
                currentStockOption.getText().toString().isEmpty() ||
                currentHomeFund.getText().toString().isEmpty() ||
                currentWellnessFund.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill out all items!", Toast.LENGTH_SHORT).show();
            return;
        }

        String title = currentTitle.getText().toString();
        String company = currentCompany.getText().toString();
        String city = currentCity.getText().toString();
        String state = currentState.getText().toString();
        double annualSalary = Double.parseDouble(currentAnnualSalary.getText().toString());
        double annualBonus = Double.parseDouble(currentAnnualBonus.getText().toString());
        int leaveTime = Integer.parseInt(currentLeave.getText().toString());
        int stockOptionOffered = Integer.parseInt(currentStockOption.getText().toString());
        double homeBuyingProgFund = Double.parseDouble(currentHomeFund.getText().toString());
        double wellnessFund = Double.parseDouble(currentWellnessFund.getText().toString());
        // Read from inputs


        if (currentJob == null) {
            currentJob = new CurrentJob(title, company, city, state, annualSalary,
                    annualBonus, leaveTime, stockOptionOffered, homeBuyingProgFund, wellnessFund);

            new InsertCurrentJob(getApplicationContext(), currentJob).execute();

            // Insert new entity to database for the first time entering
        } else {
            currentJob.setTitle(title);
            currentJob.setCompany(company);
            currentJob.setCity(city);
            currentJob.setState(state);
            currentJob.setAnnualSalary(annualSalary);
            currentJob.setAnnualBonus(annualBonus);
            currentJob.setLeaveTime(leaveTime);
            currentJob.setStockOptionOffered(stockOptionOffered);
            currentJob.setHomeBuyingProgFund(homeBuyingProgFund);
            currentJob.setWellnessFund(wellnessFund);

            new UpdateCurrentJob(getApplicationContext(), currentJob).execute();
            // Updated entity if previously inserted
        }
    }

    public void currentHandleClick(View view) {
        // Save and Back click handler
        switch (view.getId()) {
            case R.id.saveBotton:
                save();
                break;
            case R.id.backBotton:
                finish();
                break;
        }
    }

    static class GetCurrentJob extends AsyncTask<Void, Void, CurrentJob> {
        // As room does not allow database access on the main thread
        // AsyncTasks is implemented to achieve asynchronous query executions
        // Activity context is passed to WeakReference to avoid memory leak (If the Activity ends before AsyncTask)
        private WeakReference<Context> context;

        public GetCurrentJob(Context context) {
            this.context = new WeakReference<>(context);
        } // Pass in context

        @Override
        protected CurrentJob doInBackground(Void... voids) {
            JobInfoDataBase db = JobInfoDataBase.getInstance(context.get());
            return db.CurrentJobDao().findOne();
        }// Return currentJob entity if exists
    }

    static class InsertCurrentJob extends AsyncTask<Void, Void, Void> {
        CurrentJob currentJob;
        private WeakReference<Context> context;

        public InsertCurrentJob(Context context, CurrentJob currentJob) {
            this.context = new WeakReference<>(context);
            this.currentJob = currentJob;
        }// Pass in context and currentJob instance

        @Override
        protected Void doInBackground(Void... voids) {
            JobInfoDataBase db = JobInfoDataBase.getInstance(context.get());
            db.CurrentJobDao().insertCurrentJob(currentJob);
            return null;
        }// Insert currentJob instance as an entity in database

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context.get(), "Your job saved!", Toast.LENGTH_SHORT).show();
        }
    }

    static class UpdateCurrentJob extends AsyncTask<Void, Void, Void> {
        CurrentJob currentJob;
        private WeakReference<Context> context;

        public UpdateCurrentJob(Context context, CurrentJob currentJob) {
            this.context = new WeakReference<>(context);
            this.currentJob = currentJob;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            JobInfoDataBase db = JobInfoDataBase.getInstance(context.get());
            db.CurrentJobDao().updateCurrentJob(currentJob);
            return null;
        }// Update entity if exists

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context.get(), "Your job updated!", Toast.LENGTH_SHORT).show();
        }
    }

}
