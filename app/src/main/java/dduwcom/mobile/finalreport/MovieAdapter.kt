package dduwcom.mobile.finalreport

import android.view.LayoutInflater
import android.view.View
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
        return MovieViewHolder(itemBinding, listener, listener2) // 리스트 바인딩해준서 뷰홀더에 전달
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemBiding.ivPhoto.setImageResource(movies[position].photo)
        holder.itemBiding.tvMovie.text = movies[position].movieName
        holder.itemBiding.tvDate.text = movies[position].date
        holder.itemBiding.tvGrade.text = movies[position].grade
    }

    fun setMovies(movieList: ArrayList<MovieDto>) {
        movies.clear() // 기존 데이터를 모두 제거
        movies.addAll(movieList) // 새로운 데이터로 업데이트
        notifyDataSetChanged() // 어댑터에 변경 사항 알리기
    }

    class MovieViewHolder(
        val itemBiding: ListItemBinding, // 리스트아이템바이딩 객체를 맴버변수로 지정
        listener: OnItemLongClickListner, listener2: OnItemClickListner
    ) : RecyclerView.ViewHolder(itemBiding.root) { //객체의root객체를 전달
        val TAG = "MovieViewHolder"

        init {
            //리스트 아이템 뷰를 구성한는 각각의 뷰에 설정하는거임
            itemBiding.root.setOnLongClickListener(object: View.OnLongClickListener {
                override fun onLongClick(v: View?): Boolean {
                    listener.onItemLongClcik(itemView, adapterPosition)
                    return true
                }
            })

            itemBiding.root.setOnClickListener (object: View.OnClickListener {
                override fun onClick(v: View?) {
                    listener2.onItemClcik(itemView, adapterPosition)
                }
            })
        }
    }

    //커스텀 리스너
    //1.이벤트 리스너 인터페이스 선언
    interface OnItemLongClickListner{
        fun onItemLongClcik(view: View, position: Int)
    }

    // 2. 전달받은 리스너를 보관한 맴버변수설정
    lateinit var listener: OnItemLongClickListner
    // 3. 외부에서 구현한 리스너를 전달받아서 멤버변수에 저장
    fun setOnItemLongClickListener(listener: OnItemLongClickListner) {
        this.listener = listener
    }

    interface OnItemClickListner{
        fun onItemClcik(view: View, position: Int)
    }
    // 2. 전달받은 리스너를 보관한 맴버변수설정
    lateinit var listener2: OnItemClickListner
    // 3. 외부에서 구현한 리스너를 전달받아서 멤버변수에 저장
    fun setOnItemClickListener(listener: OnItemClickListner){
        this.listener2 = listener
    }



}