package com.rathana.recyclerveiwdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rathana.recyclerveiwdemo.model.Inbox;

public class AddItemActivity extends AppCompatActivity {


    private EditText contact,subject,content;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        contact=findViewById(R.id.contact);
        subject=findViewById(R.id.subject);
        content=findViewById(R.id.content);
        btnSave=findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Inbox inbox=new Inbox(
                    contact.getText().toString(),
                    subject.getText().toString(),
                    content.getText().toString(),
                    "1 second",
                    R.drawable.ic_star_border_black_24dp
                );

                Intent intent=new Intent();
                Bundle bundle=new Bundle();
                bundle.putParcelable("data",inbox);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();

            }
        });
    }
}
