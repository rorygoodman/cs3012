
package com.example.kevin.design;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.design.widget.Snackbar;
        import android.support.v7.app.ActionBar;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.EditText;
        import android.widget.Toast;
        import android.widget.TextView;
        import static android.R.attr.checked;


public class formEquipActivity extends AppCompatActivity {
    private EditText user;
    protected EditText project;
    private EditText equipmentName;
    CheckBox damaged;
    private EditText expiry;
    Button createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_equip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setupActionBar();
        user =(EditText) findViewById(R.id.userText);
        project = (EditText) findViewById(R.id.projectText);
        equipmentName =(EditText) findViewById(R.id.equipmentText);
        damaged = (CheckBox)findViewById(R.id.damagedCheck);
        expiry =(EditText) findViewById(R.id.expiryDate);
        createButton = (Button)findViewById(R.id.addEquipment);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (user.equals("") || equipmentName.equals("")) {
                    Toast.makeText(formEquipActivity.this, "Project Name or Equipment Name must be filled", Toast.LENGTH_LONG).show();
                    return;
                }*/
                String equipmentString = equipmentName.getText().toString();
                String userString = user.getText().toString();
                String projectString = project.getText().toString();
                String expiryString = expiry.getText().toString();
                //String containing barcode name passed from scanner activity
                String barcodeID = getIntent().getStringExtra("bID");
                //test item to add
                EquipmentItem e = new EquipmentItem(equipmentString,userString,
                        projectString,barcodeID,expiryString,damaged.isChecked());

                MainActivity.manager.addEquipment(e);
                Intent i = new Intent(formEquipActivity.this,EquipmentListActivity.class);
                i.putExtra("barcode",e.getBarcodeID());
                startActivity(i);
            }

        });
    }
    private void setupActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Show the Up button in the action bar.
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}