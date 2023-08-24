package edu.gatech.seclass.jobcompare6300;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface JobInfoDao {

    @Query("select * from JobInfo")
    List<JobInfo> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertJobOffer(JobInfo jobInfo);
}