package uz.lazydevv.instagramclone.models

data class MessageM(
    val user: UserM,
    val lastMessage: String,
    val timeStamp: String,
    val isSeen: Boolean = true
)
