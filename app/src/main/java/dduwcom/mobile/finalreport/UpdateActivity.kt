package dduwcom.mobile.finalreport

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.BaseColumns
import androidx.appcompat.app.AppCompatActivity
import dduwcom.mobile.finalreport.databinding.ActivityUpdateBinding

@Suppress("DEPRECATION")
class UpdateActivity : AppCompatActivity() {
    lateinit var updateBinding: ActivityUpdateBinding
    lateinit var helper: MovieDBHelper
    lateinit var dto: MovieDto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateBinding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(updateBinding.root)

        helper = MovieDBHelper(this)

        dto = (intent.getSerializableExtra("dto") as? MovieDto)!!

        updateBinding.imageView.setImageResource(dto.photo)
        updateBinding.etUpdateMovie.setText(dto.movieName)
        updateBinding.etUpdateDate.setText(dto.date)
        updateBinding.etUpdateGrade.setText(dto.grade)
        updateBinding.etUpdateMoney.setText(dto.ticket)
        updateBinding.etUpdateReview.setText(dto.review)

        updateBinding.btnUpdateMovie.setOnClickListener {
            val db = helper.writableDatabase
            val name = updateBinding.etUpdateMovie.text.toString()
            val date = updateBinding.etUpdateDate.text.toString()
            val grade = updateBinding.etUpdateGrade.text.toString()
            val ticket = updateBinding.etUpdateMoney.text.toString()
            val review = updateBinding.etUpdateReview.text.toString()

            val updateRow = ContentValues().apply {
                put(MovieDBHelper.COL_MOVIE, name)
                put(MovieDBHelper.COL_DATE, date)
                put(MovieDBHelper.COL_GRADE, grade)
                put(MovieDBHelper.COL_TICKET, ticket)
                put(MovieDBHelper.COL_REVIEW, review)
            }

            val selection = "${BaseColumns._ID} = ?"
            val selectionArgs = arrayOf(dto.id.toString())
            db.update(MovieDBHelper.TABLE_NAME, updateRow, selection, selectionArgs)

            val intent = Intent(this@UpdateActivity, MainActivity::class.java)
            startActivity(intent)
            helper.close()
        }


        updateBinding.btnUpdateCancel.setOnClickListener {
            val intent = Intent(this@UpdateActivity, MainActivity::class.java)
            startActivity(intent)
            helper.close()
        }
    }
}

