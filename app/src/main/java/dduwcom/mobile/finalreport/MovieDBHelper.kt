package dduwcom.mobile.finalreport

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import android.util.Log

class MovieDBHelper(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
    val TAG = "MovieDBHelper"

    companion object {
        const val DB_NAME = "movie_db"
        const val TABLE_NAME = "movie_table"
        const val COL_IMAGE = "poster"
        const val COL_MOVIE = "movieName"
        const val COL_DATE = "date"
        const val COL_GRADE = "grade"
        const val COL_TICKET = "money"
        const val COL_REVIEW = "review"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE =
            "CREATE TABLE ${MovieDBHelper.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${MovieDBHelper.COL_IMAGE} IMAGE, " + "${MovieDBHelper.COL_MOVIE} TEXT, " +
                    "${MovieDBHelper.COL_DATE} TEXT, " + "${MovieDBHelper.COL_GRADE} TEXT, " +
                    "${MovieDBHelper.COL_TICKET} TEXT, " + "${MovieDBHelper.COL_REVIEW} TEXT)"
        Log.d(TAG, CREATE_TABLE)    // SQL 문장이 이상 없는지 Log에서 확인 필요
        db?.execSQL(CREATE_TABLE)

        /*샘플 데이터 - 필요할 경우 실행*/
        db?.execSQL("INSERT INTO ${MovieDBHelper.TABLE_NAME} VALUES (NULL, ${R.mipmap.movie01}, '범죄도시3', '2023/5/30', '3.5', '10000', '화려한 액션과 코믹의 조화')")
        db?.execSQL("INSERT INTO ${MovieDBHelper.TABLE_NAME} VALUES (NULL, ${R.mipmap.movie02}, '인어공주', '2023/6/3', '2.5', '7000', '주인공을 다시 한번만..생각해 봤으면..어땠을까?')")
        db?.execSQL("INSERT INTO ${MovieDBHelper.TABLE_NAME} VALUES (NULL, ${R.mipmap.movie03}, '엘리멘탈', '2023/6/19', '3.5', '15000', '반대에게 끌리는 이유 + 감동')")
        db?.execSQL("INSERT INTO ${MovieDBHelper.TABLE_NAME} VALUES (NULL, ${R.mipmap.movie04}, '가디언즈오브갤럭시3', '2023/5/4', '4', '6000', '특유의 가디언즈의 코믹이 가장 많이 나타난 시리즈와 사회에 던지는 메세지까지..')")
        db?.execSQL("INSERT INTO ${MovieDBHelper.TABLE_NAME} VALUES (NULL, ${R.mipmap.movie05}, '분노의질주: 더 얼티메이트', '2023/5/17', '4', '8000', '화려한 차들의 액션과 ost의 조화는 역시 분노의 질주!')")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val DROP_TABLE ="DROP TABLE IF EXISTS ${MovieDBHelper.TABLE_NAME}"
        db?.execSQL(DROP_TABLE)
        onCreate(db)
    }

    fun deleteMovie(movieName: String) {
        val db = writableDatabase
        val selection = "${MovieDBHelper.COL_MOVIE} = ?"
        val selectionArgs = arrayOf(movieName)
        db.delete(MovieDBHelper.TABLE_NAME, selection, selectionArgs)
        db.close()
    }
}

