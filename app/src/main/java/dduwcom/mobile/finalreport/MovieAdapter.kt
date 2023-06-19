package dduwcom.mobile.finalreport

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dduwcom.mobile.finalreport.databinding.ListItemBinding

class MovieAdapter(val movies: ArrayList<MovieDto> )
    : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        //리스트 바인딩을 해주면서 findViewByid()로 하나씩 계속 호출안해두됨
        val itemBinding =
            ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemBinding) // 리스트 바인딩해준서 뷰홀더에 전달
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemBiding.ivPhoto.setImageResource(movies[position].photo)
        holder.itemBiding.tvMovie.text = movies[position].movieName
        holder.itemBiding.tvDate.text = movies[position].date
        holder.itemBiding.tvGrade.text = movies[position].grade
    }

    class MovieViewHolder(
        val itemBiding: ListItemBinding, // 리스트아이템바이딩 객체를 맴버변수로 지정
    ) : RecyclerView.ViewHolder(itemBiding.root) { //객체의root객체를 전달
        val TAG = "MovieViewHolder"

        init {
            // 이벤트처리
        }
    }

}