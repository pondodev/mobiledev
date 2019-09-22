import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ImageInfo (
    var name: String,
    var location: String,
    var keywords: String,
    var date: String,
    var share: Boolean,
    var email: String,
    var rating: Float
) : Parcelable
