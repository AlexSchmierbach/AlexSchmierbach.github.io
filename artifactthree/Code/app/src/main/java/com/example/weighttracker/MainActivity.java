package com.example.weighttracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_SEND_SMS = 1;
    private static final int DATASET_COUNT = 31;

    public static WeightTrackerDbHelper dbHelper;
    public static WeightRecyclerAdapter adapter;

    private FloatingActionButton fabAddButton;
    private Button buttonClear;
    private Button buttonEditWeightGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        dbHelper = new WeightTrackerDbHelper(this);
        adapter = new WeightRecyclerAdapter(initDataset());

        FloatingActionButton fabExitButton = findViewById(R.id.fab_ExitApp);
        fabExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fabAddButton = findViewById(R.id.fab_AddWeight);

        fabAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();

                View weightsEntryView = inflater.inflate(R.layout.screen_add_weight_entry, null);

                builder.setView(weightsEntryView)
                        .setPositiveButton("Add Entry", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final EditText dateValue = weightsEntryView.findViewById(R.id.editTextDateValue);
                                final EditText weightValue = weightsEntryView.findViewById(R.id.editTextWeightValue);
                                long rowId = dbHelper.Create(dateValue.getText().toString(), weightValue.getText().toString());
                                TextView view = (TextView) findViewById(R.id.textViewWeightGoal);

                                if (Integer.parseInt(weightValue.getText().toString()) <= Integer.parseInt(weightValue.getText().toString())) {
                                    checkSendTextPermission();
                                }
                            }
                        })
                        .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Do nothing
                            }
                        });

                builder.show();
            }
        });

        buttonClear = findViewById(R.id.buttonClear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearUserInfo();
            }
        });

        buttonEditWeightGoal = findViewById(R.id.buttonEditGoalWeight);
        buttonEditWeightGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();

                View newWeightGoalView = inflater.inflate(R.layout.screen_new_weight_goal, null);

                builder.setView(newWeightGoalView)
                        .setPositiveButton("Update Goal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                final EditText weightValue = newWeightGoalView.findViewById(R.id.editTextWeightValue);
                                dbHelper.Update(1, weightValue.getText().toString());
                                TextView weightValueTitle = findViewById(R.id.textViewWeightGoal);
                                weightValueTitle.setText(weightValue.getText().toString() + "lbs");
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Do nothing
                            }
                        });

                builder.show();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerViewWeightNumbers);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void checkSendTextPermission() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_SEND_SMS);
        } else {
            String number = "6505551212";
            String msg = "You have reached your goal weight!";
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, msg, null, null);
        }
    }

    private ArrayList<Weight> initDataset() {
        ArrayList<Weight> weights = dbHelper.ReadRow();
        for (Weight weight : dbHelper.ReadRow()) {
            weights.add(weight);
        }
        return weights;
    }

    private void clearUserInfo() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "DELETE FROM " + WeightTrackerContract.WeightTrackerEntry.TABLE_NAME;
        db.execSQL(query);
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}
