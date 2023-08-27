package uz.lazydevv.instagramclone.models

data class StoryM(
    val user: UserM,
    val isSeen: Boolean,
    val isEmpty: Boolean = false
)
