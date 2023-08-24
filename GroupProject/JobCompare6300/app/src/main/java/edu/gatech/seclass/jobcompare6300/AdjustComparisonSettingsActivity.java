package edu.gatech.seclass.jobcompare6300;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

public class AdjustComparisonSettingsActivity extends AppCompatActivity {

    private EditText wellnessFundWeight;
    private EditText homeFundWeight;
    private EditText stockWeight;
    private EditText leaveWeight;
    private EditText bonusWeight;
    private EditText salaryWeight;
    private JobInfoWeight jobInfoWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adjust_comparison_setting);
        getSupportActionBar().setTitle("Weights");

        wellnessFundWeight = findViewById(R.id.wellnessWeightEditText);
        homeFundWeight = findViewById(R.id.homeWeightEditText);
        stockWeight = findViewById(R.id.stockWeightEditText);
        leaveWeight = findViewById(R.id.leaveWeightEditText);
        bonusWeight = findViewById(R.id.bonusWeightEditText);
        salaryWeight = findViewById(R.id.salaryWeightEditText);

        try {
            jobInfoWeight = new AdjustComparisonSettingsActivity.GetWeights(
                    AdjustComparisonSettingsActivity.this).execute().get();
            // Load entity (if previously inserted), otherwise jobInfoWeight remains null
        }
        catch (InterruptedException | ExecutionException e) {
            Toast.makeText(getApplicationContext(), "Failed loading comparison settings!!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void save(){
        int salary = Integer.parseInt(salaryWeight.getText().toString());
        int bonus = Integer.parseInt(bonusWeight.getText().toString());
        int leave = Integer.parseInt(leaveWeight.getText().toString());
        int stock = Integer.parseInt(stockWeight.getText().toString());
        int homeFund = Integer.parseInt(homeFundWeight.getText().toString());
        int wellnessFund = Integer.parseInt(wellnessFundWeight.getText().toString());

        if (jobInfoWeight == null){
            jobInfoWeight = new JobInfoWeight(salary, bonus, leave,
                    stock, homeFund, wellnessFund);
            new InsertWeights(getApplicationContext(), jobInfoWeight).execute();
            // Insert new entity to database for the first time entering
        }
        else{
            jobInfoWeight.setSalaryWeight(salary);
            jobInfoWeight.setBonusWeight(bonus);
            jobInfoWeight.setLeaveWeight(leave);
            jobInfoWeight.setStockWeight(stock);
            jobInfoWeight.setHomeFundWeight(homeFund);
            jobInfoWeight.setWellnessFundWeight(wellnessFund);
            jobInfoWeight.setTotalWeight(salary+bonus+leave+stock+homeFund+wellnessFund);

            new UpdateWeights(getApplicationContext(), jobInfoWeight).execute();
            // Updated entity if previously inserted
        }
    }

    public void weightsHandleClick(View view) {
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

    static class GetWeights extends AsyncTask<Void, Void, JobInfoWeight> {
        // As room does not allow database access on the main thread
        // AsyncTasks is implemented to achieve asynchronous query executions
        // Activity context is passed to WeakReference to avoid memory leak
        // (If the Activity ends before AsyncTask)
        private WeakReference<Context> context;

        public GetWeights(Context context) {
            this.context = new WeakReference<>(context);
        } // Pass in context

        @Override
        protected JobInfoWeight doInBackground(Void... voids) {
            JobInfoDataBase db = JobInfoDataBase.getInstance(context.get());
            return db.JobInfoWeightDao().findOne();
        }// Return JobInfoWeight entity if exists

        @Override
        protected void onPostExecute(JobInfoWeight jobInfoWeight) {
            super.onPostExecute(jobInfoWeight);
            // After retrieving database
            // Fill EditTexts in UI with previously entered values if exist

            EditText salaryWeight = ((Activity) context.get()).findViewById(R.id.salaryWeightEditText);
            EditText bonusWeight = ((Activity) context.get()).findViewById(R.id.bonusWeightEditText);
            EditText leaveWeight = ((Activity) context.get()).findViewById(R.id.leaveWeightEditText);
            EditText stockWeight = ((Activity) context.get()).findViewById(R.id.stockWeightEditText);
            EditText homeFundWeight = ((Activity) context.get()).findViewById(R.id.homeWeightEditText);
            EditText wellnessFundWeight = ((Activity) context.get()).findViewById(R.id.wellnessWeightEditText);

            // Otherwise fill weights with 1 (first entry)
            if(jobInfoWeight == null){
                salaryWeight.setText("1");
                bonusWeight.setText("1");
                leaveWeight.setText("1");
                stockWeight.setText("1");
                homeFundWeight.setText("1");
                wellnessFundWeight.setText("1");
                return;
            }

            salaryWeight.setText(String.valueOf(jobInfoWeight.getSalaryWeight()));
            bonusWeight.setText(String.valueOf(jobInfoWeight.getBonusWeight()));
            leaveWeight.setText(String.valueOf(jobInfoWeight.getLeaveWeight()));
            stockWeight.setText(String.valueOf(jobInfoWeight.getStockWeight()));
            homeFundWeight.setText(String.valueOf(jobInfoWeight.getHomeFundWeight()));
            wellnessFundWeight.setText(String.valueOf(jobInfoWeight.getWellnessFundWeight()));

        }
    }

    static class InsertWeights extends AsyncTask<Void, Void, Void> {
        JobInfoWeight jobInfoWeight;
        private WeakReference<Context> context;

        public InsertWeights(Context context, JobInfoWeight jobInfoWeight) {
            this.context = new WeakReference<>(context);
            this.jobInfoWeight = jobInfoWeight;
        }// Pass in context and jobInfoWeight instance

        @Override
        protected Void doInBackground(Void... voids) {
            JobInfoDataBase db = JobInfoDataBase.getInstance(context.get());
            db.JobInfoWeightDao().insertJobInfoWeight(jobInfoWeight);
            return null;
        }// Insert jobInfoWeight instance as an entity in database

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context.get(), "Your comparison settings saved!", Toast.LENGTH_SHORT).show();
        }
    }

    static class UpdateWeights extends AsyncTask<Void, Void, Void> {
        JobInfoWeight jobInfoWeight;
        private WeakReference<Context> context;

        public UpdateWeights(Context context, JobInfoWeight jobInfoWeight) {
            this.context = new WeakReference<>(context);
            this.jobInfoWeight = jobInfoWeight;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            JobInfoDataBase db = JobInfoDataBase.getInstance(context.get());
            db.JobInfoWeightDao().updateJobInfoWeight(jobInfoWeight);
            return null;
        }// Update entity if exists

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context.get(), "Your comparison settings updated!", Toast.LENGTH_SHORT).show();
        }
    }
}