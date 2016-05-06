package org.usablelabs.duedo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;

public class Form2ExpenseActivity extends BaseActivity {

    private Expense db = null;

    private EditText titleEdit; // start
    CheckBox check1,check2,check3,check4,check5;

    private static final int MenuItem_SaveID = 1;
    private Expense task = null;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_expense);
        setDrawer(true);
        setTitle("แก้ไขข้อมูลรายจ่าย");

        id = getIntent().getLongExtra("id", 0);
        task = Expense.load(Expense.class, id);

        titleEdit = (EditText) findViewById(R.id.sexdateEdit);

        titleEdit.setText(task.date);

        check1 = (CheckBox)findViewById(R.id.checkBox1);
        check2 = (CheckBox)findViewById(R.id.checkBox2);
        check3 = (CheckBox)findViewById(R.id.checkBox3);
        check4 = (CheckBox)findViewById(R.id.checkBox4);
        check5 = (CheckBox)findViewById(R.id.checkBox5);

        Log.e("test", "gg");
        Log.e("V1",task.v1+"");
        Log.e("V1",task.v2+"");
        Log.e("V1",task.v3+"");
        Log.e("V1",task.v4+"");
        Log.e("V1", task.v5 + "");

        if(task.v1){
            check1.setChecked(true);
        }

        if(task.v2){
            check2.setChecked(true);
        }

        if(task.v3){
            check3.setChecked(true);
        }

        if(task.v4){
            check4.setChecked(true);
        }

        if(task.v5){
            check5.setChecked(true);
        }
    }

    public void onStart() {
        super.onStart();
        EditText dateEdit = (EditText) findViewById(R.id.sexdateEdit);
        dateEdit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    DateDialog dialog = new DateDialog(v);
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    dialog.show(ft, "DatePicker");
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        addMenuItem(menu, MenuItem_SaveID, R.string.save, buildDrawable(MaterialDesignIconic.Icon.gmi_save));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                break;
            case MenuItem_SaveID:
                save();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void save() {
        if (titleEdit.getText().length() > 0) {
            if (task == null)
                task = new Expense();
            task.date = titleEdit.getText().toString();
            task.v1 = check1.isChecked();
            Log.e("c1", check1.isChecked() + "");
            task.v2 = check2.isChecked();
            Log.e("c1",check2.isChecked()+"");
            task.v3 = check3.isChecked();
            Log.e("c1",check3.isChecked()+"");
            task.v4 = check4.isChecked();
            Log.e("c1",check4.isChecked()+"");
            task.v5 = check5.isChecked();
            Log.e("c1", check5.isChecked() + "");
            task.saveWithTimestamp();
            setResult(Activity.RESULT_OK, new Intent().putExtra("id", task.getId()));
            Form2ExpenseActivity.this.finish();

        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(android.R.string.dialog_alert_title);
            alert.setMessage(R.string.title_is_required);
            alert.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            alert.show();
        }
    }
}
