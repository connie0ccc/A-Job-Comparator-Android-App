package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

public class EnterJobOffersActivity extends AppCompatActivity {

    private EditText offerTitle;
    private EditText offerCompany;
    private EditText offerCity;
    private EditText offerState;
    private EditText offerYS;
    private EditText offerYB;
    private EditText offerLeave;
    private EditText offerSO;
    private EditText offerHF;
    private EditText offerWF;
    private static JobInfo currentOffer;
    private static CurrentJob currentJob;
    private boolean isSaved = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_job_offers);
        getSupportActionBar().setTitle("Job Offers");

        offerTitle = findViewById(R.id.titleEditText);
        offerCompany = findViewById(R.id.companyEditText);
        offerCity = findViewById(R.id.cityEditText);
        offerState = findViewById(R.id.stateEditText);
        offerYS = findViewById(R.id.salaryEditText);
        offerYB = findViewById(R.id.bonusEditText);
        offerLeave = findViewById(R.id.leaveEditText);
        offerSO = findViewById(R.id.stockEditText);
        offerHF = findViewById(R.id.homeEditText);
        offerWF = findViewById(R.id.wellnessEditText);
    }

    public void save(){
        if (offerTitle.getText().toString().isEmpty() ||
                offerCompany.getText().toString().isEmpty() ||
                offerCity.getText().toString().isEmpty() ||
                offerState.getText().toString().isEmpty() ||
                offerYS.getText().toString().isEmpty() ||
                offerYB.getText().toString().isEmpty() ||
                offerLeave.getText().toString().isEmpty() ||
                offerSO.getText().toString().isEmpty() ||
                offerHF.getText().toString().isEmpty() ||
                offerWF.getText().toString().isEmpty()){
            Toast.makeText(this, "Please fill out all items!", Toast.LENGTH_SHORT).show();
            return;
        }

        String title = offerTitle.getText().toString();
        String company = offerCompany.getText().toString();
        String city = offerCity.getText().toString();
        String state = offerState.getText().toString();
        double annualSalary = Double.parseDouble(offerYS.getText().toString());
        double annualBonus = Double.parseDouble(offerYB.getText().toString());
        int leaveTime = Integer.parseInt(offerLeave.getText().toString());
        int stockOptionOffered = Integer.parseInt(offerSO.getText().toString());
        double homeBuyingProgFund = Double.parseDouble(offerHF.getText().toString());
        double wellnessFund = Double.parseDouble(offerWF.getText().toString());
        // Read from inputs

        currentOffer = new JobInfo(title, company, city, state, annualSalary,
                annualBonus, leaveTime, stockOptionOffered, homeBuyingProgFund, wellnessFund);
        new InsertJobOffer(this, currentOffer).execute();
        isSaved = true;
    }

    public void compareWithCurrentJob(){
        if(isSaved){
            try {
                currentJob = new EnterCurrentJobActivity.GetCurrentJob(this).execute().get();
                // Load current job entity (if previously inserted), otherwise remains null
            }
            catch (InterruptedException | ExecutionException e) {
                Toast.makeText(getApplicationContext(), "Failed loading current job!",
                        Toast.LENGTH_SHORT).show();
            }

            if(currentJob == null){
                Toast.makeText(getApplicationContext(), "Please enter your current job!",
                        Toast.LENGTH_SHORT).show();
                //Check if there is current job
            }
            else{
                Intent compareWithCurrent = new Intent(this, CompareJobOffersActivity.class);
                compareWithCurrent.putExtra("Source", "Compare offer with current job");
                //Pass a key value pair to indicate comparison between current job and current offer
                startActivity(compareWithCurrent);
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "Please save your offer!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
    }

    public void prepForAnotherOffer(){
        if(isSaved){
            offerTitle.setText("");
            offerCompany.setText("");
            offerCity.setText("");
            offerState.setText("");
            offerYS.setText("");
            offerYB.setText("");
            offerLeave.setText("");
            offerSO.setText("");
            offerHF.setText("");
            offerWF.setText("");
            isSaved = false;
            //Clear and prepare to add another offer
        }
        else{
            Toast.makeText(getApplicationContext(), "Please save your offer!", Toast.LENGTH_SHORT).show();
        }
    }

    public static CurrentJob getCurrentJob(){
        return currentJob;
    }

    public static JobInfo getCurrentOffer(){
        return currentOffer;
    }

    public void offerHandleClick(View view) {
        // Click handler: Add another offer, initiate comparison with current job, save, back to main menu
        switch (view.getId()) {
            case R.id.addOfferBotton:
                prepForAnotherOffer();
                break;
            case R.id.compareWithCurrentBotton:
                compareWithCurrentJob();
                break;
            case R.id.saveBotton:
                save();
                break;
            case R.id.backBotton:
                finish();
                break;
        }
    }

    static class InsertJobOffer extends AsyncTask<Void, Void, Void> {
        JobInfo currentOffer;
        private WeakReference<Context> context;

        public InsertJobOffer(Context context, JobInfo currentOffer) {
            this.context = new WeakReference<>(context);
            this.currentOffer = currentOffer;
        }// Pass in context and job offer instance

        @Override
        protected Void doInBackground(Void... voids) {
            JobInfoDataBase db = JobInfoDataBase.getInstance(context.get());
            db.JobInfoDao().insertJobOffer(currentOffer);
            return null;
        }// Insert offer instance as an entity in database

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(context.get(), "Your offer saved!", Toast.LENGTH_SHORT).show();
        }

    }

}