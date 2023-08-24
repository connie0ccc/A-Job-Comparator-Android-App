package edu.gatech.seclass.jobcompare6300;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class JobInfoWeight {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "salaryWeight")
    private int salaryWeight;
    @ColumnInfo(name = "bonusWeight")
    private int bonusWeight;
    @ColumnInfo(name = "leaveWeight")
    private int leaveWeight;
    @ColumnInfo(name = "stockWeight")
    private int stockWeight;
    @ColumnInfo(name = "homeFundWeight")
    private int homeFundWeight;
    @ColumnInfo(name = "wellnessFundWeight")
    private int wellnessFundWeight;
    @ColumnInfo(name = "totalWeight")
    private int totalWeight;

    public JobInfoWeight(int salaryWeight, int bonusWeight, int leaveWeight, int stockWeight,
                         int homeFundWeight, int wellnessFundWeight){
        this.salaryWeight = salaryWeight;
        this.bonusWeight = bonusWeight;
        this.leaveWeight = leaveWeight;
        this.stockWeight = stockWeight;
        this.homeFundWeight = homeFundWeight;
        this.wellnessFundWeight = wellnessFundWeight;
        this.totalWeight = salaryWeight + bonusWeight + leaveWeight + stockWeight + homeFundWeight + wellnessFundWeight;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }
    public void setSalaryWeight(int salaryWeight){
        this.salaryWeight = salaryWeight;
    }

    public int getSalaryWeight(){
        return salaryWeight;
    }

    public void setBonusWeight(int bonusWeight){
        this.bonusWeight = bonusWeight;
    }

    public int getBonusWeight(){
        return bonusWeight;
    }

    public void setLeaveWeight(int leaveWeight){
        this.leaveWeight = leaveWeight;
    }

    public int getLeaveWeight(){
        return leaveWeight;
    }

    public void setStockWeight(int stockWeight){
        this.stockWeight = stockWeight;
    }

    public int getStockWeight(){
        return stockWeight;
    }

    public void setHomeFundWeight(int homeFundWeight){
        this.homeFundWeight = homeFundWeight;
    }

    public int getHomeFundWeight(){
        return homeFundWeight;
    }

    public void setWellnessFundWeight(int wellnessFundWeight){
        this.wellnessFundWeight = wellnessFundWeight;
    }

    public int getWellnessFundWeight(){
        return wellnessFundWeight;
    }

    public void setTotalWeight(int totalWeight){
        this.totalWeight = totalWeight;
    }

    public int getTotalWeight(){
        return totalWeight;
    }
}
