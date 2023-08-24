package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.widget.Toast;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {JobInfo.class, CurrentJob.class, JobInfoWeight.class}, version = 1, exportSchema = false)
public abstract class JobInfoDataBase extends RoomDatabase{

    private static JobInfoDataBase jobInfoDataBase; // Use singleton pattern for a global database
    public abstract JobInfoDao JobInfoDao();
    public abstract CurrentJobDao CurrentJobDao();
    public abstract JobInfoWeightDao JobInfoWeightDao();

    public static JobInfoDataBase getInstance(final Context context) {
        if (jobInfoDataBase == null) {
            jobInfoDataBase = Room.databaseBuilder(context, JobInfoDataBase.class,
                    "JobInfoDataBase").build();
        }
        return jobInfoDataBase;
    }
}