// 과제명: 영화 정보 관리 앱
// 분반: 01분반
// 학번,이름: 20211694 장우림
// 제출일: 2023.6.23 금요일

package dduwcom.mobile.finalreport

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import dduwcom.mobile.finalreport.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    lateinit var helper : MovieDBHelper
    var adapter = MovieAdapter(ArrayList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        helper = MovieDBHelper(this)

        // 레이아웃매니저 생성 및 설정
        // 그리드 레이아웃 설정
        val layoutManager = GridLayoutManager(applicationContext, 3)
        layoutManager.orientation = GridLayoutManager.HORIZONTAL

        // 리사이클러뷰에 레이아웃메니저 및 어댑터 설정
        binding.recyclerView.layoutManager = layoutManager

        val movieList = showMovies()
        adapter.setMovies(movieList)
        binding.recyclerView.adapter = adapter

        adapter.setOnItemLongClickListener(object : MovieAdapter.OnItemLongClickListner {
            override fun onItemLongClcik(view: View, position: Int) {
                val alertDialog = AlertDialog.Builder(this@MainActivity)
                alertDialog.setTitle("리뷰삭제")
                alertDialog.setMessage("${movieList[position].movieName}를 정말 삭제하시겠습니까?")
                alertDialog.setPositiveButton("삭제") { dialog, _ ->
                    //액티비티 연결안하고 바로 DB삭제
                    val movieName = movieList[position].movieName
                    helper.deleteMovie(movieName)
                    adapter.movies.removeAt(position)
                    adapter.notifyDataSetChanged()
                    dialog.dismiss()
                }
                alertDialog.setNegativeButton("취소") { dialog, _ ->
                    dialog.dismiss()
                }
                alertDialog.show()
            }
        })

        adapter.setOnItemClickListener(object: MovieAdapter.OnItemClickListner{
            override fun onItemClcik(view: View, position: Int) {
                val intent = Intent(this@MainActivity, UpdateActivity::class.java)
                intent.putExtra("dto", movieList[position])   // 클릭한 RecyclerView 항목 위치의 dto 전달
                startActivity(intent)// 수정결과를 받아오도록 Activity 호출
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            //추가옵션
            R.id.movie_add -> {
                val intent = Intent(this, AddActivity::class.java)
                startActivity(intent)
                true
            }

            /*R.id.movie_search-> {
                val intent = Intent(this, AddActivity::class.java)
            } */

            //개발자소개
            R.id.info -> {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("소개")
                builder.setMessage("안녕하세요! 저는 01분반 20211694 장우림입니다. " + " " +
                        "영화리뷰 서비스를 만들어보았습니다. 많은 이용 부탁드립니다!")

                val imageView = ImageView(this)
                imageView.setImageResource(R.drawable.ic_launcher_foreground)
                builder.setView(imageView)

                builder.setPositiveButton("확인") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.show()
                true
            }
            //종료
            R.id.cancle -> {
                AlertDialog.Builder(this)
                    .setTitle("앱 종료")
                    .setMessage("앱을 종료하시겠습니까?")
                    .setPositiveButton("종료") { _, _ ->
                        finish() // 액티비티 종료
                    }
                    .setNegativeButton("취소") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Range")
    private fun showMovies(): ArrayList<MovieDto> {
        val db = helper.readableDatabase // db의 onCreate()가 최초로 실행됨
        // 다 null인 이유 -> 모든 데이터를 다 가져올꺼기 때문에 따로 선언필요없음
        val columns = null
        val selection = null
        val selectionArgs = null
        val cursor = db.query(
            MovieDBHelper.TABLE_NAME, columns, selection, selectionArgs,
            null, null, null, null
        )

        val movieList = arrayListOf<MovieDto>()
        with(cursor) {
            while (moveToNext()) {
                val id = getInt( getColumnIndex(BaseColumns._ID) )
                val image = getInt(getColumnIndex(MovieDBHelper.COL_IMAGE))
                val name = getString(getColumnIndex(MovieDBHelper.COL_MOVIE))
                val date = getString(getColumnIndex(MovieDBHelper.COL_DATE))
                val grade = getString(getColumnIndex(MovieDBHelper.COL_GRADE))
                val ticket = getString(getColumnIndex(MovieDBHelper.COL_TICKET))
                val review = getString(getColumnIndex(MovieDBHelper.COL_REVIEW))

                //foodDto에 추가
                val dto = MovieDto(id, image, name, date, grade, ticket, review)
                movieList.add(dto)
            }
        }
        cursor.close()
        helper.close()

        return movieList
    }
}


