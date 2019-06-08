package com.islamsaeed.note;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.islamsaeed.note.DataBase.Model.Note;
import com.islamsaeed.note.DataBase.NoteDataBase;

public class AddNoteActivity extends AppCompatActivity implements View.OnClickListener {

    protected EditText title;
    protected EditText date;
    protected EditText details;
    protected Button addButton;
    protected Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_add_note);
        initView();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addButton) {
            String sTitle = title.getText().toString();
            String sDate = date.getText().toString();
            String sDetails = details.getText().toString();
            int priority = spinner.getSelectedItemPosition()+1;

            Note note = new Note(sTitle,sDetails,sDate,priority);
            NoteDataBase.getInstance(this)
                    .noteDAO()
                    .insertNote(note);
            finish();


        }


    }

    private void initView() {
        title = (EditText) findViewById(R.id.title);
        date = (EditText) findViewById(R.id.date);
        details = (EditText) findViewById(R.id.details);
        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(AddNoteActivity.this);
        spinner = (Spinner) findViewById(R.id.spinner);
    }
}
