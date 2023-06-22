package dduwcom.mobile.finalreport

import java.io.Serializable

data class MovieDto(val id: Int, val photo: Int, val movieName: String, val date: String, val grade: String,  val ticket: String?,
                    val review: String?) : Serializable {
    override fun toString() = " $id $movieName $date $grade $ticket $review"
}