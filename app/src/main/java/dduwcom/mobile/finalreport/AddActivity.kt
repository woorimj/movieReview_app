package dduwcom.mobile.finalreport

import android.app.AppComponentFactory
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dduwcom.mobile.finalreport.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity(){

    lateinit var addBinding : ActivityAddBinding
    lateinit var helper: MovieDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addBinding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(addBinding.root)

        helper = MovieDBHelper(this)

        addBinding.btnUpdateMovie.setOnClickListener{
            val name = addBinding.etUpdateMovie.text.toString()

            if(name.isEmpty()){
                Toast.makeText(this@AddActivity, "영화 이름을 입력하세요.", Toast.LENGTH_SHORT).show()
            } else{
                val db = helper.writableDatabase // SQL 데이터베이스 객체획득
                val image = R.drawable.ic_launcher_foreground
                val date = addBinding.etUpdateDate.text.toString()
                val grade = addBinding.etUpdateGrade.text.toString()
                val ticket = addBinding.etUpdateMoney.text.toString()
                val review = addBinding.etUpdateReview.text.toString()

                val newRow = ContentValues()
                newRow.put("${MovieDBHelper.COL_IMAGE}", image)
                newRow.put("${MovieDBHelper.COL_MOVIE}", name)
                newRow.put("${MovieDBHelper.COL_DATE}", date)
                newRow.put("${MovieDBHelper.COL_GRADE}", grade)
                newRow.put("${MovieDBHelper.COL_TICKET}", ticket)
                newRow.put("${MovieDBHelper.COL_REVIEW}", review)

                db.insert("${MovieDBHelper.TABLE_NAME}", null, newRow)
                val intent = Intent(this@AddActivity, MainActivity::class.java)
                startActivity(intent)
                helper.close()
            }
        }

        addBinding.btnUpdateCancel.setOnClickListener{
            // 메인으로 돌아가기
            val intent = Intent(this@AddActivity, MainActivity::class.java)
            startActivity(intent)
            helper.close()
        }

    }
}

