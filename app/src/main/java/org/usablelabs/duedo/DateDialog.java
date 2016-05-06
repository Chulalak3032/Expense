package org.usablelabs.duedo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

/**
 * Created by Kanokwan on 9/2/2559.
 */
public class DateDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    EditText dateEdit;

    public DateDialog(View view) {
        dateEdit = (EditText) view;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState){

    final Calendar c = Calendar.getInstance();
    int ปี = c.get(Calendar.YEAR);
    int เดือน = c.get(Calendar.MONTH);
    int วันที่ = c.get(Calendar.DAY_OF_MONTH);
    return new DatePickerDialog(getActivity(), this,ปี,เดือน,วันที่);
    }

    public void onDateSet(DatePicker view, int ปี, int เดือน, int วันที่){

        String date=วันที่+"-"+(เดือน+1)+"-"+ปี;
        dateEdit.setText(date);
    }
}


