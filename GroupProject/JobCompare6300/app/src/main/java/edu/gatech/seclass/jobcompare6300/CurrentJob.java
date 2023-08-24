package edu.gatech.seclass.jobcompare6300;

import androidx.room.Entity;

@Entity
public class CurrentJob extends JobInfo {
    public CurrentJob(String title, String company, String city, String state,  double annualSalary,
                      double annualBonus, int leaveTime, int stockOptionOffered,
                      double homeBuyingProgFund, double wellnessFund) {
        super(title, company, city, state, annualSalary,
                annualBonus, leaveTime, stockOptionOffered, homeBuyingProgFund, wellnessFund);
        this.setIsCurrentJob(true);
    }
}
