package edu.gatech.seclass.jobcompare6300;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CurrentJobDao {
    @Query("SELECT * FROM currentjob LIMIT 1")
    CurrentJob findOne();
    // Only one entity for currentjob, as entity would only be inserted once

    @Update
    public void updateCurrentJob(CurrentJob currentJob);

    @Insert
    public void insertCurrentJob(CurrentJob currentJob);
}
