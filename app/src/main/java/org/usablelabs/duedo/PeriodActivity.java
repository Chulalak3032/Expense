package org.usablelabs.duedo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PeriodActivity extends BaseActivity {

    private static final int SHOW_TASK = 2;

    private ArrayList<Task> tasks;

    private ListView listView;
    private TextView emptyLabel;
    private TasksAdapter adapter;
    private Button btn_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_period);
        setDrawer(true);
        setTitle("รายรับ");


        btn_add = (Button)findViewById(R.id.btn_add1);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PeriodActivity.this, Form2Activity.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        emptyLabel = (TextView) findViewById(R.id.emptyLabel);
        setView();
    }

    private void setView() {
        tasks = new ArrayList<Task>(Task.getAll());
        if (tasks.isEmpty()) {
            listView.setVisibility(View.GONE);
            emptyLabel.setVisibility(View.VISIBLE);
        } else {
            emptyLabel.setVisibility(View.GONE);
            listView.setVisibility(View.VISIBLE);
            adapter = new TasksAdapter(this, tasks);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(PeriodActivity.this, ShowActivity.class);
                    intent.putExtra("id", tasks.get(position).getId());
                    startActivityForResult(intent, SHOW_TASK);
                }
            });
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.e("LOG", "onrestrat");
        tasks = new ArrayList<Task>(Task.getAll());
        Log.e("SIZE",tasks.size()+"");
        adapter = new TasksAdapter(this, tasks);
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
}
