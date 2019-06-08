package com.islamsaeed.note.DataBase;


/*هنا فال class   ده هاعمل ال  constructor private  عشان انا عايز ماخدش اي اوبجكت من الكلاس بره الكلاس ده
 * فا في حاجة اسمها Single tone pattern دي design pattern بتسمحلي اني اخد اوبجكت واحد بس من الكلاس ده داخل الكلاس , داخل ال scope  بتاع الكلاس
 * وهايفدني اني مش كل مرة اعمل اوبجيكت من الداتابيز
 * ملهاش لازمة اني اخد اوبجيكت كل شوية من الداتابيز فا هو باخد اوبجيكت واحد بس
 *
 *
 * ال Singletone pattern  دي بستخدمها اما بحتاج object  واحد بس طول ال RunTime
 * ال Single tone pattern ده بقي ليه شروط
 * لازم ال constructor  يبكون برايفت محدش يقدر يعمل اوبجيكت  قفلت عالناس كلها انها تعمل اوبجيكت
 * تاني حاجة هاجي جوه الكلاس بتاعي هاعمل اوبجيكت يكون static
 * تالت حاجة هاعمل ميثود اسمها getInstance عشان أ initialize  الأوبجيكت ده
 * وهاخليها هي اللي ترجعلي الأوبجيكت بتاع ال NoteDataBase
 *
 * لو الأوبجيكت بتاع ال dataBase ده ب null  يبقي هاتعملله create
 * لو هو مش ب null  هاتقوم مرجعاه زي ماهو*/

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.islamsaeed.note.DataBase.DAOs.NoteDAO;
import com.islamsaeed.note.DataBase.Model.Note;

/*هاحول ال class  ل abstract
 * هاضيف ال Annotation DataBase
 * وهاضيف جواها البراميترز بتوعها بتاخد مني
 * أول واحد هو الكلاسيز اللي انا عاملها tables هنا انا عندي ال Note  بس , لو عندي اكتر من table هاضيفه برضو
 * تاني حاجة هو ال version بتاع الداتابيز دي  اللي هو 1
 * وارد جدا إني أغير فالإسكيما بتاعت الداتابيز (أزود table  أمسح table  اغير داتا), فا كل ماغير في الإسكيما بتاعت الداتابيز ب أعلي ال version  بتاعت الدتابيز
 * أما ب أعلي ال version  بتاع الداتابيز هايبقي مطلوب مني حاجة اسمها migration

وبعد كده هاخلي ال class ي extend
RoomDataBase

اخر حاجة هانعملها فالكلاس ده
الكلاس ده هو اللي هايعملي ال operation بتاعتي وال operation  دي موجوده جوه ال DAO
يبقي هاخد اوبجيكت من ال DAO


 */


@Database(entities = {Note.class}, version = 1,exportSchema = false) /* الخطوة الثالثة */
public abstract class NoteDataBase extends RoomDatabase {

    /*static  يعني بيتكرييت مرة واحده بس
     * وموجود علي مستوي الكلاس كله*/

    private static final String DATABASE_NAME = "NoteDataBase" ;

    /* الخطوة الأولي*/
    /*ده ال object  الوحيد في الكلاس ده*/
    private static NoteDataBase dataBase;


/*عرفت اوبيجكت من ال DAO
* وعملته abstract
 * ال annotation processor ها ي implement الإنترفيس اللي اسمه DAO  ويعمل implementation للميثودز اللي جواه
  *  ويخلي ال object  اللي هنا اللي هو noteDAO  يشاور عليه */
    public abstract NoteDAO noteDAO ();


    /* الخطوة الثانية*/
    /* دي بقي الميثود بتاعت getInstance  اللي بترجعلي object من NoteDataBase
    * اللي هاعمل فيها initialization لل object  الوحيد اللي عملته فوق اللي هو dataBase */
    public static NoteDataBase getInstance(Context context) {
        if (dataBase == null) {
            //initialize
            /* الخطوة الرابعة

            أول حاجة هانادي علي كلاس Room
            * هانادي من جواه علي static method  اسمها databaseBuilder ودي بتاخد 3 باراميترز
            * أول حاجو كونتيكست , وانا هنا معنديش كونتيكست  , فا هاروح أحطه باراميتر فوق واخد اوبيجكت منه
            * تاني حاجة اسم كلاس الداتابيز بتاعي.كلاس
            * تالت حاجة هو اسم الداتابيز بتاعي وده لازم يبقي ثابت ماينفعش يتغير
            * عشان كده عملتله فاريابل فوق ثابت
            *
            * ,وبعد كده هانادي علي ميثود اسمها build
            *  بس قبل مانادي علي Build  لازم اقوله في حالة ال migration يتصرف ازاي
             *  اول حاجة هانادي علي ميثود اسمها fallbackToDestructiveMigration
             *  ودي معناها لو حصل ان ال version اتغيرت ارمي الداتابيز القديمة وحط داتابيز جديدة
             *
             * تاني حاجة هانادي علي ميثود اسمها allowMainThreadQueries
              * عشان افتح الداتابيز واعمل عليها operations في main Thread
              *  يعني اعمل كويري فال main Thread
               *
               *  ملحوظة : انا ماينفعش افتح الداتابيز في ال Main Thread  لازم اعمل Background Thread
               *  بس هنا عشان معملتش background thread  فاقولتله يعملها فال main
               * وبعد كده هانادي علي Build اللي هايرجعلي اوبجيكت بتاع الداتابيز اللي هو رووم داتابيز */
            dataBase = Room.databaseBuilder(context,NoteDataBase.class,DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build(); /*دي بقي هاترجع object  من DataBase*/

            /* ال builder pattern  تقدر تخليني أ build ال object  بأكتر من طريقة*/
        }
        return dataBase;

    }
}

/*
* بعد ماخلصت ده كله هاروح بقي أعمل operations عالداتابيز هاروح لل HomeActivity*/