package com.islamsaeed.note;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.islamsaeed.note.Adapters.NotesAdapter;
import com.islamsaeed.note.DataBase.Model.Note;
import com.islamsaeed.note.DataBase.NoteDataBase;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    List<Note> notes;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    NotesAdapter adapter;

    public static TextView descContent ;

//  CoordinatorLayout coordinatorLayout;
//    private Drawable deleteDrawable;
//    private ColorDrawable mBackground;
//    private int backgroundColor = 0;
//    private int intrinsicHeight = 0;
//    private int intrinsicWidth = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        recyclerView=findViewById(R.id.recyclerView);
//        coordinatorLayout = findViewById(R.id.coordinatorLayout);
//        deleteDrawable = ContextCompat.getDrawable(HomeActivity.this, R.drawable.ic_delete);

        //enableSwipeToDeleteAndUndo();



        setSupportActionBar(toolbar);

        layoutManager = new LinearLayoutManager(this);
        adapter = new NotesAdapter(notes);

        adapter.setOnItemClickListener(new NotesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int pos, Note noteContent) {

                /*هنا بضيف ال intent وبحط جواه putExtre  للمكان بتاع الitem
                * وللمحتوي بتاع ال item
                * وهاروح بقي استقبلهم هناك في ال NoteContentActivity */
                Intent intent = new Intent(HomeActivity.this, NoteContentActivity.class);
//                intent.putExtra("position" , pos);
//                intent.putExtra("noteContent" ,  noteContent.getDesc());
                intent.putExtra("title" , noteContent.getTitle());
                intent.putExtra("date" , noteContent.getDate());
                intent.putExtra("desc" , noteContent.getDesc());
                intent.putExtra("priority" , noteContent.getPriority());
                intent.putExtra("id" , noteContent.getId());



                //           NoteContentActivity.setNote(noteContent);

                startActivity(intent);

            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

           startActivity(new Intent(HomeActivity.this,AddNoteActivity.class));


            }
        });
//
//        /*هكتب اسم الداتابيز بتاعتي وهانادي من جواها علي getInstance
//        * ,وهانادي من جواه عالميثودز بتاعتي بقي اللي عملتها */
//
//        NoteDataBase.getInstance(this).noteDAO()
//


        /*ده عشان اعمل swipe to delete*/
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                /*عشان أمسح الNote مع ال swipe
                لازم الأول اجيب المكان بتاع النوتة اللي عايز أمسحها
                 عن طريق هاعمل فاريابل وهانادي من جواه علي ال viewHolder  اللي شايل الأماكن كلها
                 ومنه هانادي علي getAdapterPosition كده اناجبت المكان اللي عايز امسحه

                  بعد كده هاحط المكان بقي اللي انا جبته ده في ال arrayList  بتاعتي عشان تجيبه
                  وبعد كده هاطلب من ال ArrayList  انها تمسحه
                  وبعد كده هامسحه من الداتابيز عن طريق الميثود بتاعت delete اللي انا عاملها في ال NoteDAO*/
               final int swipedNote = viewHolder.getAdapterPosition();
               final Note note = notes.get(swipedNote);
                notes.remove(note);

                NoteDataBase.getInstance(HomeActivity.this)
                        .noteDAO()
                        .deleteNote(note);
                        adapter.notifyItemRemoved(swipedNote);


                   /* دي خطوة ال Undo  واني احطها في سناك بار */
                Snackbar.make(viewHolder.itemView,"Note been deleted ",Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                NoteDataBase.getInstance(HomeActivity.this)
                                        .noteDAO()
                                        .insertNote(note);
                                notes.add(swipedNote,note);
                                adapter.notifyItemRemoved(swipedNote);

                            }
                        }).show();
                adapter.notifyItemRemoved(swipedNote);

            }



//            @Override
//            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//
//                View itemView = viewHolder.itemView;
//                int itemHeight = itemView.getHeight();
//
//
//
//
//                mBackground.setColor(backgroundColor);
//                mBackground.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
//                mBackground.draw(c);
//
//                int deleteIconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
//                int deleteIconMargin = (itemHeight - intrinsicHeight) / 2;
//                int deleteIconLeft = itemView.getRight() - deleteIconMargin - intrinsicWidth;
//                int deleteIconRight = itemView.getRight() - deleteIconMargin;
//                int deleteIconBottom = deleteIconTop + intrinsicHeight;
//
//
//                deleteDrawable.setBounds(deleteIconLeft, deleteIconTop, deleteIconRight, deleteIconBottom);
//                deleteDrawable.draw(c);
//
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//
//
//            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);







    }

//
//
//
//    private void enableSwipeToDeleteAndUndo() {
//        SwipeToDeleteCallback swipeToDeleteCallback = new SwipeToDeleteCallback(this) {
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
//
//
//                final int position = viewHolder.getAdapterPosition();
//                final Note item = adapter.getData().get(position);
//
//                adapter.removeItem(position);
//
//
//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
//                snackbar.setAction("UNDO", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        Note note = null;
//                        adapter.restoreItem(note ,position);
//                        recyclerView.scrollToPosition(position);
//                    }
//                });
//
//
//
//                /*عشان أمسح الNote مع ال swipe
//                لازم الأول اجيب المكان بتاع النوتة اللي عايز أمسحها
//                 عن طريق هاعمل فاريابل وهانادي من جواه علي ال viewHolder  اللي شايل الأماكن كلها
//                 ومنه هانادي علي getAdapterPosition كده اناجبت المكان اللي عايز امسحه
//
//                  بعد كده هاحط المكان بقي اللي انا جبته ده في ال arrayList  بتاعتي عشان تجيبه
//                  وبعد كده هاطلب من ال ArrayList  انها تمسحه
//                  وبعد كده هامسحه من الداتابيز عن طريق الميثود بتاعت delete اللي انا عاملها في ال NoteDAO*/
//                int swipedNote = viewHolder.getAdapterPosition();
//                Note note = notes.get(swipedNote);
//                notes.remove(note);
//                NoteDataBase.getInstance(HomeActivity.this)
//                        .noteDAO()
//                        .deleteNote(note);
//                        adapter.notifyItemRemoved(swipedNote);
//
//                snackbar.setActionTextColor(Color.YELLOW);
//                snackbar.show();
//
//            }
//        };
//        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
//        itemTouchhelper.attachToRecyclerView(recyclerView);
//    }






















        /* عشان أعرض الداتا بتاعتي هاروح عالدتابيز وهاقول getAllNotes  وبعد كده هاباصيها لل Adapter
     * انا عايز طيب الداتا يتعملها ريفريش كل مادخل عالأكتيفتي , يبقي هاحطها عند ال onStart
      * عشان وانا واقف عند Add واعمل back  يعمل لليست ريفريش
      *
       *
        * وأول حاجة هاعملها جواها اني هاضيف List  من نوع Note
        *  بعد كده هاضيف فيها ال NoteDataBase  بتاعتي وهانادي منها علي الميثود بتاعت getInstance
        * وبعد كده هانادي منها عال noteDAO وبعد كده getNotes
         * عشان انا عايزة يرجعلي الNotes  كلها اللي عندي */
    @Override
    protected void onStart() {
        super.onStart();
         notes = NoteDataBase.getInstance(this)
                .noteDAO()
                .getNotes(); /*دي بقي هاترجعلي الليسته بتاعت ال Notes  وتبعتها للأدابتر تقوله خد الداتا الجديدة اهي
                 كده معنديش حل الا اني اكريت ادابتر جديد*/


//        adapter = new NotesAdapter(notes);
//        recyclerView.setAdapter(adapter);
        /*بس انا مش عايز اعمل كده كل ماخش عالأكتيفيتي اكريت اوبجيكت جديد من الأدابتر
          *
           * يبقي هاروح عند الأدابتر بقي اعمل عنده ميثود اسمها changeData
           *
           * كل اما يحصل وادخل عال onStart  هاعمل change data*/
        adapter.changeData(notes);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
