package edu.gatech.seclass.jobcompare6300;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class JobInfo{
    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "company")
    private String company;
    @ColumnInfo(name = "city")
    private String city;
    @ColumnInfo(name = "state")
    private String state;
    @ColumnInfo(name = "annualSalary")
    private double annualSalary;
    @ColumnInfo(name = "annualBonus")
    private double annualBonus;
    @ColumnInfo(name = "leaveTime")
    private int leaveTime;
    @ColumnInfo(name = "stockOptionOffered")
    private int stockOptionOffered;
    @ColumnInfo(name = "homeBuyingProgFund")
    private double homeBuyingProgFund;
    @ColumnInfo(name = "wellnessFund")
    private double wellnessFund;
    @ColumnInfo(name = "jobRankScore")
    private int jobRankScore;
    @ColumnInfo(name = "isCurrentJob")
    private boolean isCurrentJob = false;

    public JobInfo(String title, String company, String city, String state,  double annualSalary,
                   double annualBonus, int leaveTime, int stockOptionOffered,
                   double homeBuyingProgFund, double wellnessFund) {
        this.title = title;
        this.company = company;
        this.city = city;
        this.state = state;
        this.annualSalary = annualSalary;
        this.annualBonus = annualBonus;
        this.leaveTime = leaveTime;
        this.stockOptionOffered = stockOptionOffered;
        this.homeBuyingProgFund = homeBuyingProgFund;
        this.wellnessFund = wellnessFund;
        this.id = title+company+city+state;
        // title+company+city+state for each job to generate unique id
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public double getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public double getAnnualBonus() {
        return annualBonus;
    }

    public void setAnnualBonus(double annualBonus) {
        this.annualBonus = annualBonus;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(int leaveTime) {
        this.leaveTime = leaveTime;
    }

    public int getStockOptionOffered() {
        return stockOptionOffered;
    }

    public void setStockOptionOffered(int stockOptionOffered) {
        this.stockOptionOffered = stockOptionOffered;
    }

    public double getHomeBuyingProgFund() {
        return homeBuyingProgFund;
    }

    public void setHomeBuyingProgFund(double homeBuyingProgFund) {
        this.homeBuyingProgFund = homeBuyingProgFund;
    }

    public double getWellnessFund() {
        return wellnessFund;
    }

    public void setWellnessFund(double wellnessFund) {
        this.wellnessFund = wellnessFund;
    }

    public int getJobRankScore() {
        return jobRankScore;
    }

    public void setJobRankScore(int jobRankScore) {
        this.jobRankScore = jobRankScore;
    }

    public boolean getIsCurrentJob() {
        return isCurrentJob;
    }

    public void setIsCurrentJob(boolean isCurrentJob) {
        this.isCurrentJob = isCurrentJob;
    }


}