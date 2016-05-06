package org.usablelabs.duedo;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;


public class FormIncomeActivity extends BaseActivity {

    private Income db = null;

    private EditText titleEdit; // date
    private EditText ContentEdit; //จำนวนเงิน
    CheckBox check8,check9;

    private static final int MenuItem_SaveID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_income);
        setDrawer(true);
        setTitle("ข้อมูลรายรับ");

        titleEdit = (EditText) findViewById(R.id.dateEdit);
        ContentEdit = (EditText) findViewById(R.id.date2Edit);
        check8 = (CheckBox)findViewById(R.id.checkBox8);
        check9 = (CheckBox)findViewById(R.id.checkBox9);

    }

    public void onStart() {
        super.onStart();
        EditText dateEdit = (EditText) findViewById(R.id.dateEdit);
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
            if (db == null)
                db = new Income();
            db.title = titleEdit.getText().toString();
            db.content = ContentEdit.getText().toString();
            db.v8 = check8.isChecked();
            db.v9 = check9.isChecked();
            db.saveWithTimestamp();
            setResult(Activity.RESULT_OK, new Intent().putExtra("id", db.getId()));
            this.finish();

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

