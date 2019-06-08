package com.islamsaeed.note;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.islamsaeed.note.DataBase.Model.Note;
import com.islamsaeed.note.DataBase.NoteDataBase;

public class NoteContentActivity extends AppCompatActivity implements View.OnClickListener {


    static Note note;
    protected int id;
    protected EditText titleEdit;
    protected EditText dateEdit;
    protected EditText descriptionNote;
    protected Spinner spinner;
    protected Button updateBtn;

    public static void setNote(Note note) {
        NoteContentActivity.note = note;
    }


    String intentNoteContent;
    int intentNotePosition;
    protected TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.notecontent);


        /*هنا بقي هاستقبل اللي جالي من ال intent
         * الأول هاعمل 2 فاريابل فوق واحد لل NoteContent  بتاعتي
         * والتاني لل NotePosition  بتاعتي
         * وهاجي تحت بقي هنا هاستقبلهم ب getIntent  الأتنين كل واحد علي حدي
         *
         * قبلها بقي هاكون معرف TextView  فوق وحاطط فيه ال Attribute  اللي انا عامله في ال layout  بتاعت الأكتيفيتي اللي انا فيها دي


         *أول خطوة بعد كده findViewById لل content  عشان مايقلبش معايا في ال RunTime ب null
         *تاني خطوة هاعرف الفارايبل بتاعي اللي فوق بتاع  intentNoteContent و أحطه داخل ال intent في getExtra ولازم طبعا احط نفس ال key  اللي انا حاطه هناك في ال HomeActivity
         * تالت خطوة بقي ودي المهمة لازم أ setText  بتاع ال Attribute  اللي انا عرفته فوق كا  TextView  اللي هو ال content ,
         * لازم أحط التيكست فيه بتاع الintentNoteContent عشان يظهرلي اول ماضغط كليك عال item
         * والخطوة الأخيرة بقي هاعمل getIntent  برضو لل NotePosition  اللي انا عملته فوق بإسم intentNotePosition
         * كده المهمة اكتملت */

//        content = findViewById(R.id.description_note);
//        intentNoteContent = getIntent().getStringExtra("noteContent");
//        content.setText(intentNoteContent);
//        intentNotePosition = getIntent().getIntExtra("position", -1);

        /*علي نفس موازاة اللي فوق */

        /*دي الحاجات اللي استقبلتها من ال Intent */
        if (getIntent().getExtras() != null) {
            descriptionNote = (EditText) findViewById(R.id.description_note);
            descriptionNote.setText(getIntent().getExtras().getString("desc"));

            titleEdit = (EditText) findViewById(R.id.titleEdit);
            titleEdit.setText(getIntent().getExtras().getString("title"));

            dateEdit = (EditText) findViewById(R.id.dateEdit);
            dateEdit.setText(getIntent().getExtras().getString("date"));

            id = getIntent().getExtras().getInt("id");


        }

        initView();

    }


    /*هنا عشان اشغل ال click  بتاع ال button  ويعمل update  معاسا علي طول */
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.updateBtn) {
            String title = titleEdit.getText().toString();
            String date = dateEdit.getText().toString();
            String desc = descriptionNote.getText().toString();
            int priority= spinner.getSelectedItemPosition()+1;

            Note note = new Note (title,date,desc,priority);
            note.setId(id); /* خطوة ال id  مهمة جدا جدا عشان يعمل ال update معايا لازم احددله بالظبط انهي note  وده عن طريق ال update*/
            NoteDataBase.getInstance(this)
                    .noteDAO().updateNote(note);
            finish();


        }
    }

    private void initView() {
        titleEdit = (EditText) findViewById(R.id.titleEdit);
        dateEdit = (EditText) findViewById(R.id.dateEdit);
        descriptionNote = (EditText) findViewById(R.id.description_note);
        spinner = (Spinner) findViewById(R.id.spinner);
        updateBtn = (Button) findViewById(R.id.updateBtn);
        updateBtn.setOnClickListener(NoteContentActivity.this);
    }
}

