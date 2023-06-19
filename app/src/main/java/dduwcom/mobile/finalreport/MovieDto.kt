package dduwcom.mobile.finalreport

data class MovieDto(val photo: Int, val movieName: String, val date: String, val grade: String) {
    override fun toString() = "$movieName $date $grade"
}