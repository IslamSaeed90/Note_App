package com.islamsaeed.note.DataBase.Model;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.widget.TextView;



/*بعد ماخلصت ال Table بتاعي ده
هاروح اعمل باكج تانية عشان اعمل عالموديل اللي انا فيه ده دلوقتي اعمل عليه ال operations  اللي هي
 * Insert , update , delete , retrieve
  * هايكون اسمهاDOAs  اختصار ل
   * Data Access Object
   * هايبقي عبارة عن interface Class  كل الميثود اللي فيه unimplemented يعني ملهاش body
   * ال DAOs  ده مسؤل عن اني أ Access ال tables بتاعتي بالكود */


@Entity
public class Note {


    /*لازم اعمل auto generate واخليها true  عشان ال id  بتاعي مايتكررش ويعمل هو واحد جديد كل مرة */
    @PrimaryKey(autoGenerate = true) /*هو by default ب false  فا لو معملتش انا true هايستني مني انا اقوله ال id   كام */
            int id;

    @ColumnInfo /* الكولمن انفو بتتحط عال attribute  اللي انت عايزها تبقي كولمن في الداتابيز*/
            String title;
    @ColumnInfo(name = "content")
    String desc;
    @ColumnInfo
    String date;
    @ColumnInfo
    int priority;

    /* لو مش عايز احط ال name  ده فالداتا بيز فا هاعمل @Ignore*/

    @Ignore
    String name;



    public Note() {
        }


   /* ماينفعش أعمل أكتر من كونستراكتور
    الرووم داتابيز هايتضايق , هايقولي لازم تحددلي استخدم انهي كونستراكتور فيهم
    * فا لو مش عايزه يستخدم واحده فيهم , هاحط قبلها Ignore@
    * فا كده بقوله Ignore  الكونستراكتور التاني واشتغل بال default*/

   @Ignore
    public Note(String title, String desc, String date, int priority) {
        this.title = title;
        this.desc = desc;
        this.date = date;
        this.priority = priority;
    }









    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}



//package com.route.note.DataBase.Model;
////
//import android.arch.persistence.room.ColumnInfo;
//import android.arch.persistence.room.Entity;
//import android.arch.persistence.room.Ignore;
//import android.arch.persistence.room.PrimaryKey;
//
///**
// * Created by Mohamed Nabil Mohamed on 5/11/2019.
// * m.nabil.fci2015@gmail.com
// */
//
//@Entity
//public class Note {
//
//    @PrimaryKey(autoGenerate = true)
//    int id;
//    @ColumnInfo
//    String title;
//    @ColumnInfo(name = "content")
//    String desc;
//    @ColumnInfo
//    String date;
//    @ColumnInfo
//    int priority;
//
//    public Note() {
//    }
//
//    @Ignore
//    public Note(String title, String desc, String date, int priority) {
//        this.title = title;
//        this.desc = desc;
//        this.date = date;
//        this.priority = priority;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public int getPriority() {
//        return priority;
//    }
//
//    public void setPriority(int priority) {
//        this.priority = priority;
//    }
//}
