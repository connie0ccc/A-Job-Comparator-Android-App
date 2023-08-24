package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import edu.gatech.seclass.jobcompare6300.RankListViewAdapter.rankListViewSubItem;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

public class RankJobOffersActivity extends AppCompatActivity{

    private List<JobInfo> OfferList = new ArrayList<>();
    private static Map<Integer, JobInfo> scoreOfferMap;
    private static Map<Integer, Integer> rankScoreMap;
    private CurrentJob currentJob;
    private static int selectedJob1Position = 0;
    private static int selectedJob2Position = 0;
    private int selectedJobNum = 0;
    private JobInfoWeight jobWeights;
    private int salaryWeight;
    private int bonusWeight;
    private int leaveWeight;
    private int stockWeight;
    private int homeWeight;
    private int wellnessWeight;
    private int totalWeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank_jobs);
        getSupportActionBar().setTitle("Compare Jobs");

        try {
            currentJob = new GetCurrentJob(this).execute().get();
            OfferList = new GetJobOffers(this).execute().get();
            jobWeights = new GetWeights(this).execute().get();
        }
        catch (InterruptedException | ExecutionException e) {
            Toast.makeText(getApplicationContext(), "Failed loading data!",
                    Toast.LENGTH_SHORT).show();
        }
        //Load all from database

        if(jobWeights != null){
            salaryWeight = jobWeights.getSalaryWeight();
            bonusWeight = jobWeights.getBonusWeight();
            leaveWeight = jobWeights.getLeaveWeight();
            stockWeight = jobWeights.getStockWeight();
            homeWeight = jobWeights.getHomeFundWeight();
            wellnessWeight = jobWeights.getWellnessFundWeight();
            totalWeight = jobWeights.getTotalWeight();
        }
        else{
            salaryWeight = 1;
            bonusWeight = 1;
            leaveWeight = 1;
            stockWeight = 1;
            homeWeight = 1;
            wellnessWeight = 1;
            totalWeight = 6;
        }
        //Set all weights to 1 by default if no weights entered
        scoreOfferMap = new TreeMap<>();
        rankScoreMap = new TreeMap<>();

        if(currentJob != null){
            scoreJob(currentJob);
            scoreOfferMap.put(currentJob.getJobRankScore(), currentJob);
        }
        //Score and rank current job if exists
        for(JobInfo offer : OfferList) {
            scoreJob(offer);
        }
        for(JobInfo offer : OfferList){
            scoreOfferMap.put(offer.getJobRankScore(), offer);
        }
        //Score and rank each offer
        for(JobInfo offer : OfferList){
            scoreOfferMap.put(offer.getJobRankScore(), offer);
        }

        int item = scoreOfferMap.size();
        for (Map.Entry<Integer, JobInfo> entry : scoreOfferMap.entrySet()){
            item--;
            rankScoreMap.put(item, entry.getKey());
        }
        //Use two map to store ranks, scores, and JobInfo instances
        //Jobs in scoreOfferMap is ascending by score, but descending in rankScoreMap by score
        //And that descending map helps to load jobs to listView from highest score to lowest

        ListView rankJobsListView = findViewById(R.id.rankJobsListView);
        RankListViewAdapter rankListViewAdapter= new RankListViewAdapter(scoreOfferMap, rankScoreMap, RankJobOffersActivity.this);
        rankJobsListView.setAdapter(rankListViewAdapter);
        rankJobsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                rankListViewSubItem subItem = (rankListViewSubItem) view.getTag();
                subItem.checkBox.toggle();
                RankListViewAdapter.checkBoxSelected.put(position, subItem.checkBox.isChecked());
                if(subItem.checkBox.isChecked()) {
                    if(selectedJobNum < 2){
                        selectedJobNum++;
                        selectedJob2Position = selectedJob1Position;
                        selectedJob1Position = position;
                        //Save positions of two jobs
                    }
                    else{
                        RankListViewAdapter.checkBoxSelected.put(position, false);
                        Toast.makeText(getApplicationContext(), "Please select no more than 2 jobs!",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    selectedJobNum--;
                }
            }
        });
    }

    public void scoreJob(JobInfo jobInfo){
        int jobScore;
        double salary = jobInfo.getAnnualSalary();
        double bonus = jobInfo.getAnnualBonus();
        double leave = jobInfo.getLeaveTime();
        double stock = jobInfo.getStockOptionOffered();
        double home = jobInfo.getHomeBuyingProgFund();
        double wellness = jobInfo.getWellnessFund();

        jobScore = (int)(salaryWeight*salary + bonusWeight*bonus + leaveWeight*salary*leave/260
        + stockWeight*stock/2 + homeWeight*home + wellnessWeight*wellness)/totalWeight;
        jobInfo.setJobRankScore(jobScore);
        //Calculate and set score for each job
    }

    public static JobInfo getSelectedJob1(){
        return scoreOfferMap.get(rankScoreMap.get(selectedJob1Position));
        //Get JobInfo instance by position selected
    }

    public static JobInfo getSelectedJob2(){
        return scoreOfferMap.get(rankScoreMap.get(selectedJob2Position));
    }

    public void compareJobOffers(){
        if(selectedJobNum == 2){
            Intent compareJobOffers
                    = new Intent(this, CompareJobOffersActivity.class);
            compareJobOffers.putExtra("Source", "Compare between offers");
            //Pass a key value pair to indicate comparison between offers
            startActivity(compareJobOffers);
        }
        else{
            Toast.makeText(getApplicationContext(), "Please select 2 jobs!",
                    Toast.LENGTH_SHORT).show();
        }

    }

    public void rankHandleClick(View view) {
        switch (view.getId()) {
            case R.id.compareJobsButton:
                compareJobOffers();
                break;
            case R.id.backBotton:
                finish();
                break;
        }
    }

    static class GetCurrentJob extends AsyncTask<Void, Void, CurrentJob> {
        // As room does not allow database access on the main thread
        // AsyncTasks is implemented to achieve asynchronous query executions
        // Activity context is passed to WeakReference to avoid memory leak
        // (If the Activity ends before AsyncTask)
        private WeakReference<Context> context;

        public GetCurrentJob(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected CurrentJob doInBackground(Void... voids) {
            JobInfoDataBase db = JobInfoDataBase.getInstance(context.get());
            return db.CurrentJobDao().findOne();
        }

        @Override
        protected void onPostExecute(CurrentJob currentJob) {
            super.onPostExecute(currentJob);
        }
    }

    static class GetJobOffers extends AsyncTask<Void, Void, List<JobInfo>> {
        private WeakReference<Context> context;

        public GetJobOffers(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected List<JobInfo> doInBackground(Void... voids) {
            JobInfoDataBase db = JobInfoDataBase.getInstance(context.get());
            return db.JobInfoDao().findAll();
        }

        @Override
        protected void onPostExecute(List<JobInfo> allOffers) {
            super.onPostExecute(allOffers);
        }
    }

    public static class GetWeights extends AsyncTask<Void, Void, JobInfoWeight> {
        private WeakReference<Context> context;

        public GetWeights(Context context) {
            this.context = new WeakReference<>(context);
        }

        @Override
        protected JobInfoWeight doInBackground(Void... voids) {
            JobInfoDataBase db = JobInfoDataBase.getInstance(context.get());
            return db.JobInfoWeightDao().findOne();
        }

        @Override
        protected void onPostExecute(JobInfoWeight jobInfoWeight) {
            super.onPostExecute(jobInfoWeight);
        }
    }
}
