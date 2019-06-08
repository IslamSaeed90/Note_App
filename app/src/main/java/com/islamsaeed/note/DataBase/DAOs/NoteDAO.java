package com.islamsaeed.note.DataBase.DAOs;


/*بعد ماخلصت ال Table بتاعي في كلاس ال Note
هاروح اعمل باكج تانية عشان اعمل عالموديل اللي انا فيه ده دلوقتي اعمل عليه ال operations  اللي هي
 * Insert , update , delete , retrieve
  * هايكون اسمهاDOAs  اختصار ل
   * Data Access Object
   * هايبقي عبارة عن interface Class  كل الميثود اللي فيه unimplemented يعني ملهاش body
   * ال DAOs  ده مسؤل عن اني أ Access ال tables بتاعتي بالكود */



/* بما إن اي ميثود هاعملها هاتبقي Abstract  طيب مين ها ي implement  الميثودز دي ؟ وفنفس الوقت انا مش ها extend ال class ده
 * ال Anotation processor بتاع الرووم في وقت الcompile time هاياخد interface ده هايروح يعمله implementation   */


/*أول حاجة هاعملها هي إني هاحط أنوتيشن انه  DAO
 وبعد كده هاحط جواه ال operations  كلها اللي عايز اعملها علي ال Note
 ولو عندي table  تاني هاروح اعمل DAO  ليه  */

import android.annotation.SuppressLint;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.support.annotation.RequiresPermission;
import android.support.annotation.RequiresPermission.Read;
import android.support.annotation.RequiresPermission.Write;

import com.islamsaeed.note.DataBase.Model.Note;

import java.util.List;

@Dao
public interface NoteDAO {

    /*محتاجة parameters ايه عشان تشتغل , محتاجة ال Note  كلها , فا هاديلها object  كامل من نوع  Note */

    @Insert
    void insertNote(Note note);
    @Update
    void updateNote (Note note);
    @Delete
    void deleteNote (Note note);








    /*طب نفترض انا عايز ا delete ال Note  وانا مش معايا ال id  بتاعها هاعمل ايه ؟
    * هاعمل انوتيشن Query  وهاكتب كويري سترينج جوه*/

    @Query("delete from Note where title =:title")
    void deleteNoteByTitle (String title);



//    /*دي ميثود عايز بيها أرجع الداتا اللي جوه ال description  بتاع ال Note*/
//    @Query("select * from Note where content =:description")
//    void viewNotedescription (String description);






    /*دي ميثود ترجعلي الداتا كلها  ال Notes كلها
     * هاترجع الداتا كلها اللي فال Table
      * هاكتب select all from Note */

    @Query("select * from Note")
    List<Note>getNotes ();



    /*لو عايز اسيرش علي حاجة فال Note  بال title بتاعها او فال content
     * وهابعتلها باراميتر بالكويري او الكلمة اللي انا عايز اسيرش عليها
     * واشوف الكويري ده متكرر فين فالcontent
     * أو هابحث عن ال Title
    * وهاكتب select all from Note where title = q
    * هو كده هايسرش بالإسم وهايرجعلي كل ال Notes اللي فيها ال title  ده
     * لو عايز اسيرش بال content  لازم اقوله ال content = الكلمة اللي عايز اسيرش بيها
      * وهاحط كلمة like
      *select * from Note where title like :q */


//
//    @Query("select * from Note where title =:q")
//    List<Note>searchNoteByTitle(String q);



}
