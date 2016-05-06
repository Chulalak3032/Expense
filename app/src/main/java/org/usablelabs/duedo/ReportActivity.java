package org.usablelabs.duedo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ReportActivity extends BaseActivity {

    Button btn_1, btn_2, btn_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        setTitle("รายงาน");
        setDrawer(true);


        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, BarChartActivity.class);
                startActivity(intent);
            }
        });

        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportActivity.this, LineChartActivity.class);
                startActivity(intent);
            }
        });

    }

    }


