package uz.lazydevv.instagramclone.models

data class MediaM(
    val user: UserM,
    val secondaryLabel: String,
    val postImg: Int,
    val description: String = "",
    val postDate: String = ""
)
