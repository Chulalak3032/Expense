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

public class ExpenseActivity extends BaseActivity {

    private static final int SHOW_TASK = 2;

    private ArrayList<Expense> expense;

    private ListView listView;
    private TextView emptyLabel;
    private ExpenseAdapter adapter;
    private Button btn_add;
    private int index_protect = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        setDrawer(true);
        setTitle("รายจ่าย");

        listView = (ListView) findViewById(R.id.listView);
        emptyLabel = (TextView) findViewById(R.id.emptyLabel);
        //setView();

        btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExpenseActivity.this,FormExpenseActivity.class);
                startActivity(intent);
            }
        });

        setView();
    }

    private void setView() {
        expense = new ArrayList<Expense>(Expense.getAll());
        if (expense.isEmpty()) {
            listView.setVisibility(View.GONE);
            emptyLabel.setVisibility(View.VISIBLE);
        } else {
            emptyLabel.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            adapter = new ExpenseAdapter(this, expense);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ExpenseActivity.this, ShowExpenseActivity.class);
                    intent.putExtra("id", expense.get(position).getId());
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
        expense = new ArrayList<Expense>(Expense.getAll());
        Log.e("SIZE",expense.size()+"");
        adapter = new ExpenseAdapter(this, expense);
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

    class ExpenseAdapter extends ArrayAdapter<Expense> {

        String olddate;
        private ArrayList<Expense> alltasks;

        public ExpenseAdapter(Context context, ArrayList<Expense> tasks) {
            super(context, 0, tasks);
            alltasks = tasks;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Task task = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list_expense, parent, false);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.txt_date);
            tv.setText(alltasks.get(position).date);

            TextView tv3 = (TextView) convertView.findViewById(R.id.txt_total);
            tv3.setText(alltasks.get(position).v6);

            String protect = "";

            Log.e("V1",alltasks.get(position).v1+"");
            Log.e("V2",alltasks.get(position).v2+"");
            Log.e("V3",alltasks.get(position).v3+"");
            Log.e("V4",alltasks.get(position).v4+"");

            if(alltasks.get(position).v1){
                protect += "ค่าอาหาร/เครื่องดื่ม";
                index_protect++;
            }

            if(alltasks.get(position).v2){
                if(index_protect==0) {
                    protect += "เดินทาง/ท่องเที่ยว";
                }else{
                    protect += ", เดินทาง/ท่องเที่ยว";
                }
                index_protect++;
            }

            if(alltasks.get(position).v3){
                if(index_protect==0)
                    protect += "ส่วนตัว/Shopping";
                else
                    protect += ", ส่วนตัว/Shopping";
                index_protect++;
            }

            if(alltasks.get(position).v4){
                if(index_protect==0)
                    protect += "บิลต่างๆ";
                else
                    protect += ", บิลต่างๆ";
                index_protect++;
            }

            if(alltasks.get(position).v5){
                if(index_protect==0)
                    protect += "อื่นๆ";
                else
                    protect += ", อื่นๆ";
                index_protect++;
            }

            index_protect = 0;

            TextView tv2 = (TextView) convertView.findViewById(R.id.txt_protect);
            tv2.setText(protect);

            return convertView;
        }
    }
}
