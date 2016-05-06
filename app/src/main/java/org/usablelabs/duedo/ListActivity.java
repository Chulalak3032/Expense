package org.usablelabs.duedo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListActivity extends BaseActivity {

    private static final int SHOW_TASK = 2;

    private ArrayList<Task> tasks;

    private ListView listView;
    private TextView emptyLabel;
    private TasksAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setDrawer(false);
        setTitle("รายรับ-รายจ่าย ของฉัน");



        listView = (ListView) findViewById(R.id.listView);
        emptyLabel = (TextView) findViewById(R.id.emptyLabel);



        //setView();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Bundle extras = data.getExtras();
            switch (requestCode) {
                case NEW_TASK:
                    if (extras != null && extras.getLong("id", 0) > 0)
                        setView();
                    break;
                case SHOW_TASK:
                    if (extras != null && extras.getBoolean("refreshNeeded", false))
                        setView();
                    break;
            }
        }
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
                    Intent intent = new Intent(ListActivity.this, ShowActivity.class);
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
}

class TasksAdapter extends ArrayAdapter<Task> {

    String olddate;
    private ArrayList<Task> alltasks;

    public TasksAdapter(Context context, ArrayList<Task> tasks) {
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

        TextView tv2 = (TextView) convertView.findViewById(R.id.txt_content);
        tv2.setText(alltasks.get(position).content);


        return convertView;
    }
}
