package uz.lazydevv.instagramclone.utils

import uz.lazydevv.instagramclone.R
import uz.lazydevv.instagramclone.models.MediaM
import uz.lazydevv.instagramclone.models.StoryM
import uz.lazydevv.instagramclone.models.UserM

object MockData {

    private val user1 = UserM("lazydevv", R.drawable.sample_profile_img1, false)
    private val user2 = UserM("joxa_1909", R.drawable.sample_profile_img2, true)
    private val user3 = UserM("asilbek_turayev", R.drawable.sample_profile_img3, true)
    private val user4 = UserM("nazarovakbarali61", R.drawable.sample_profile_img4, true)
    private val user5 = UserM("alimardon_2231", R.drawable.sample_profile_img5, true)

    val users = listOf(user1, user2, user3, user4, user5)

    val stories = listOf(
        StoryM(user = user1, isSeen = true, isEmpty = true),
        StoryM(user = user2, isSeen = false, isEmpty = false),
        StoryM(user = user3, isSeen = false, isEmpty = false),
        StoryM(user = user4, isSeen = false, isEmpty = false),
        StoryM(user = user5, isSeen = true, isEmpty = false),
    )

    val rowMediaItems = listOf(
        MediaM(user1, "Muhammad Ali", R.drawable.sample_img1, "Muhammad Ali is the real champion of the whole boxing story 👊😎", "2 hours ago"),
        MediaM(user2, "Stay with Palestine", R.drawable.sample_img2, "It is so important for us!", "16-aug"),
        MediaM(user3, "Uzbekistan", R.drawable.sample_img3, "Wise people not always silent, but know when to be.", "13-aug"),
        MediaM(user4, "", R.drawable.sample_img4, "So angry cat 😁😂", "11-jul, 2023"),
        MediaM(user5, "Dubai, UAE", R.drawable.sample_img5, "Favour is free :)", "29-dec, 2022"),
    )

    val searchMediaItems = rowMediaItems.asSequence().shuffled()
        .plus(rowMediaItems.shuffled())
        .plus(rowMediaItems.shuffled())
        .plus(rowMediaItems.shuffled())
        .plus(rowMediaItems.shuffled())
        .plus(rowMediaItems.shuffled())
        .chunked(5).toList()
}