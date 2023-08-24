package edu.gatech.seclass.jobcompare6300;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface JobInfoWeightDao {
    @Query("SELECT * FROM JobInfoWeight LIMIT 1")
    JobInfoWeight findOne();

    @Update
    public void updateJobInfoWeight(JobInfoWeight jobInfoWeight);

    @Insert
    public void insertJobInfoWeight(JobInfoWeight jobInfoWeight);
}
