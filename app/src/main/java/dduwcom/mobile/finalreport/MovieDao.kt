package dduwcom.mobile.finalreport

class MovieDao() {
    val movies = ArrayList<MovieDto>()

    init{
        movies.add(MovieDto(R.drawable.movie01, "범죄도시3", "2023-5-30", "3.5"))
        movies.add(MovieDto(R.drawable.movie02, "인어공주", "2023-6-3", "2.5"))
        movies.add(MovieDto(R.drawable.movie03, "엘리멘탈", "2023-6-19", "3.5"))
        movies.add(MovieDto(R.drawable.movie04, "가디언즈오브갤럭시3", "2023-5-4", "4"))
        movies.add(MovieDto(R.drawable.movie05, "분노의질주: 더 얼티메이트", "2023-5-17", "4"))
    }
}