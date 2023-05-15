package com.example.realmexamtrial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {
    EditText name,department;
    Button add,display;
    TextView show;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameid);
        department = findViewById(R.id.deptid);
        add = findViewById(R.id.addbtn);
        display = findViewById(R.id.dispbtn);
        realm = Realm.getDefaultInstance();
        show = findViewById(R.id.textView2);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Dname = name.getText().toString();
                String Ddept = department.getText().toString();
                addData(Dname,Ddept);
            }
        });
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataModel dataModel = new DataModel();
                List<DataModel> dataModelList = realm.where(DataModel.class).findAll();
                show.setText("");
                for (int i=0; i<dataModelList.size();i++)
                {
                    show.append("Name: "+dataModelList.get(i).getName() + "Dep: "+dataModelList.get(i).getDept());
                }
            }
        });
    }

    private void addData(String dname, String ddept) {
        DataModel dataModel = new DataModel();
        Number id = realm.where(DataModel.class).max("id");
        long nextId;
        if (id==null){
            nextId=1;
        }
        else {
            nextId=id.intValue()+1;
        }
        dataModel.setId(nextId);
        dataModel.setDept(dname);
        dataModel.setName(ddept);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(dataModel);
            }
        });


    }
}