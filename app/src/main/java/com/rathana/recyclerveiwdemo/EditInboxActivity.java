package com.rathana.recyclerveiwdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rathana.recyclerveiwdemo.model.Inbox;

public class EditInboxActivity extends AppCompatActivity {

    private EditText content,contact,subject;
    private Button btnSaveChange;

    private Inbox inbox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        initUI();

        btnSaveChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveChange();
            }
        });
    }

    private void initUI(){
        contact=findViewById(R.id.contact);
        subject=findViewById(R.id.subject);
        content=findViewById(R.id.content);
        btnSaveChange=findViewById(R.id.btnSave);

        //get data from intent
        if(getIntent()!=null){
            inbox= getIntent().getParcelableExtra("data");
            contact.setText(inbox.getContact());
            content.setText(inbox.getContent());
            subject.setText(inbox.getSubject());
        }
    }

    private void saveChange(){
        inbox.setContact(contact.getText().toString());
        inbox.setContent(content.getText().toString());
        inbox.setSubject(subject.getText().toString());

        Intent intent=new Intent();
        Bundle bundle=new Bundle();
        bundle.putParcelable("data",inbox);
        intent.putExtras(bundle);

        setResult(RESULT_OK,intent);
        finish();

    }
}
