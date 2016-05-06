package org.usablelabs.duedo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BarChartActivity extends BaseActivity {

    private ArrayList<Income> income;
    private ArrayList<Integer> month;
    ArrayList<String> xAxis;
    ArrayList<Integer> yes;
    ArrayList<Integer> no;
    ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);
        setTitle("รายงานรายรับ");
        setDrawer(true);
        BarChart chart = (BarChart) findViewById(R.id.chart);

        income = new ArrayList<Income>(Income.getAll());
        month = new ArrayList<>();
        month.add(0);
        month.add(0);
        month.add(0);
        month.add(0);
        month.add(0);
        month.add(0);
        month.add(0);
        month.add(0);
        month.add(0);
        month.add(0);
        month.add(0);
        month.add(0);

        yes = new ArrayList<>();
        yes.add(0);
        yes.add(0);
        yes.add(0);
        yes.add(0);
        yes.add(0);
        yes.add(0);
        yes.add(0);
        yes.add(0);
        yes.add(0);
        yes.add(0);
        yes.add(0);
        yes.add(0);

        no = new ArrayList<>();
        no.add(0);
        no.add(0);
        no.add(0);
        no.add(0);
        no.add(0);
        no.add(0);
        no.add(0);
        no.add(0);
        no.add(0);
        no.add(0);
        no.add(0);
        no.add(0);

        getXAxisValues();

        BarData data = new BarData(xAxis, getDataSet());
        chart.setData(data);
        chart.setDescription("");
        chart.animateXY(2000, 2000);
        chart.invalidate();
    }

    private ArrayList<IBarDataSet> getDataSet() {
        ArrayList<IBarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        int num = 0;
        for (int i=0;i<yes.size();i++){
            Log.e(i+":", yes.get(i)+" , "+no.get(i));
            if(yes.get(i)>0 || no.get(i)>0) {
                //Log.e("Yes", yes.get(i) + "");
                BarEntry v1e1 = new BarEntry(yes.get(i), num); // Jan
                valueSet1.add(v1e1);

                //Log.e("No", no.get(i) + "");
                BarEntry v2e1 = new BarEntry(no.get(i), num); // Jan
                valueSet2.add(v2e1);
                num++;
            }
        }

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "เงินเดือน");
        barDataSet1.setColor(Color.rgb(255, 0, 127));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "รายได้เสริมอื่นๆ");
        barDataSet2.setColor(Color.rgb(51, 255, 255));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private void getXAxisValues() {
        xAxis = new ArrayList<>();
        for (int i=0;i<income.size();i++){

            DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            Date startDate = null;
            try {
                startDate = df.parse(income.get(i).title);
                String newDateString = df.format(startDate);
                System.out.println(newDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            startDate.getMonth();
            month.set(startDate.getMonth(), month.get(startDate.getMonth()) + 1);
            if(income.get(i).v8){
                no.set(startDate.getMonth(),no.get(startDate.getMonth())+1);
            }else{
                yes.set(startDate.getMonth(), yes.get(startDate.getMonth()) + 1);
            }
        }

        for(int i=0;i<12;i++){
            String value = "";
            if(month.get(i)>0){
                switch (i){
                    case 0:
                        value = "JAN";
                        break;
                    case 1:
                        value = "FAB";
                        break;
                    case 2:
                        value = "MAR";
                        break;
                    case 3:
                        value = "APR";
                        break;
                    case 4:
                        value = "MAY";
                        break;
                    case 5:
                        value = "JUN";
                        break;
                    case 6:
                        value = "JUL";
                        break;
                    case 7:
                        value = "AUG";
                        break;
                    case 8:
                        value = "SEP";
                        break;
                    case 9:
                        value = "OCT";
                        break;
                    case 10:
                        value = "NOV";
                        break;
                    case 11:
                        value = "DEC";
                        break;
                }
                Log.e("VALUE",value);
                xAxis.add(value);
            }
        }

    }

}
