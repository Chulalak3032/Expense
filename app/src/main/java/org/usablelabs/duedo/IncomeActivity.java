package org.usablelabs.duedo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class IncomeActivity extends BaseActivity {

    private static final int SHOW_TASK = 2;

    private ArrayList<Income> income;

    private ListView listView;
    private TextView emptyLabel;
    private IncomeAdapter adapter;
    private Button btn_add;
    private int index_protect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        setDrawer(true);
        setTitle("รายรับ");

        listView = (ListView) findViewById(R.id.listView);
        emptyLabel = (TextView) findViewById(R.id.emptyLabel);
        //setView();

        btn_add = (Button)findViewById(R.id.btn_add1);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IncomeActivity.this,FormIncomeActivity.class);
                startActivity(intent);
            }
        });

        setView();
    }

    private void setView() {
        income = new ArrayList<Income>(Income.getAll());
        if (income.isEmpty()) {
            listView.setVisibility(View.GONE);
            emptyLabel.setVisibility(View.VISIBLE);
        } else {
            emptyLabel.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            adapter = new IncomeAdapter(this , income);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(IncomeActivity.this, ShowIncomeActivity.class);
                    intent.putExtra("id", income.get(position).getId());
                    startActivityForResult(intent, SHOW_TASK);
                }
            });
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        index_protect = 0;
        Log.e("LOG", "onrestrat");
        income = new ArrayList<Income>(Income.getAll());
        Log.e("SIZE",income.size()+"");
        adapter = new IncomeAdapter(this , income);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    class IncomeAdapter extends ArrayAdapter<Income> {

        String olddate;
        private ArrayList<Income> alltasks;

        public IncomeAdapter(Context context, ArrayList<Income> tasks) {
            super(context, 0, tasks);
            alltasks = tasks;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Task task = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_main, parent, false);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.txt_title);
            tv.setText(alltasks.get(position).title);

            TextView tv3 = (TextView) convertView.findViewById(R.id.txt_content);
            tv3.setText(alltasks.get(position).content);

            String protect = "";



            if(alltasks.get(position).v8){
                protect += "เงินเดือน";
                index_protect++;
            }


            if(alltasks.get(position).v9){
                if(index_protect==0)
                    protect += "รายได้เสริมอื่นๆ";
                else
                    protect += ", รายได้เสริมอื่นๆ";
                index_protect++;
            }

            index_protect = 0;

            TextView tv2 = (TextView) convertView.findViewById(R.id.txt_name);
            tv2.setText(protect);

            return convertView;
        }
    }
}
