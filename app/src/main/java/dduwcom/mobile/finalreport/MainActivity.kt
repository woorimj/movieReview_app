// 과제명: 영화 정보 관리 앱
// 분반: 01분반
// 학번,이름: 20211694 장우림
// 제출일: 2023.6.23 금요일

package dduwcom.mobile.finalreport

import android.graphics.Movie
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dduwcom.mobile.finalreport.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //데이터생성
        val movies = MovieDao().movies
        // 어댑터생성 - 데이터 관리
        val adapter = MovieAdapter(movies)

        // 레이아웃매니저 생성 및 설정
        // 그리드 레이아웃 설정
        val layoutManager = GridLayoutManager(applicationContext, 3)
        layoutManager.orientation = GridLayoutManager.HORIZONTAL

        // 리사이클러뷰에 레이아웃메니저 및 어댑터 설정
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


}

