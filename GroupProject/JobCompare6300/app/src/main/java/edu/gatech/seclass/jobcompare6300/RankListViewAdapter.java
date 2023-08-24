package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.Map;
import java.util.TreeMap;

public class RankListViewAdapter extends BaseAdapter {
    private LayoutInflater rankLayoutInflater;
    private Map<Integer, JobInfo> scoreOfferMap;
    private Map<Integer, Integer> rankScoreMap;
    private Context rankContext;
    public static Map<Integer, Boolean> checkBoxSelected;

    public RankListViewAdapter(Map<Integer, JobInfo> scoreOfferMap, Map<Integer, Integer> rankScoreMap, Context context){
        this.scoreOfferMap = scoreOfferMap;
        this.rankScoreMap = rankScoreMap;
        this.rankContext = context;
        this.rankLayoutInflater = LayoutInflater.from(context);
        checkBoxSelected = new TreeMap<Integer, Boolean>();
        for(int i = 0; i < scoreOfferMap.size(); i++){
            checkBoxSelected.put(i, false);
        }
        //Load all jobs and sub item layout
    }

    public static Map<Integer, Boolean> getCheckBoxSelected(){
        return checkBoxSelected;
    }
    //Static interface to get selected checkboxes
    @Override
    public int getCount() {
        return scoreOfferMap.size();
    }

    @Override
    public Object getItem(int position) {
        return scoreOfferMap.get(rankScoreMap.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        rankListViewSubItem subItem = null;
        if(convertView == null){
            convertView = rankLayoutInflater.inflate(R.layout.rankjobs_listview_subitem, parent, false);
            subItem = new rankListViewSubItem();
            subItem.score = convertView.findViewById(R.id.score);
            subItem.title = convertView.findViewById(R.id.title);
            subItem.company = convertView.findViewById(R.id.company);
            subItem.isCurrentJob = convertView.findViewById(R.id.isCurrentJob);
            subItem.checkBox = convertView.findViewById(R.id.checkBox);
            convertView.setTag(subItem);
            //Create new subItem if not exists
        }
        else{
            subItem = (rankListViewSubItem) convertView.getTag();
            //Load previously entered subItem
        }
        subItem.title.setText(scoreOfferMap.get(rankScoreMap.get(position)).getTitle());
        subItem.company.setText(scoreOfferMap.get(rankScoreMap.get(position)).getCompany());
        if(scoreOfferMap.get(rankScoreMap.get(position)).getIsCurrentJob()){
            subItem.isCurrentJob.setText("Current Job");
        }
        else{
            subItem.isCurrentJob.setText("");
        }
        //Indicate current job
        subItem.score.setText(String.valueOf(scoreOfferMap.get(rankScoreMap.get(position)).getJobRankScore()));
        subItem.checkBox.setChecked(getCheckBoxSelected().get(position));
        //Update data
        return convertView;
    }

    static class rankListViewSubItem{
        public TextView score;
        public TextView title;
        public TextView company;
        public TextView isCurrentJob;
        public CheckBox checkBox;
    }
}
