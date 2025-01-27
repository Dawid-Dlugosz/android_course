package com.example.wishlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("wish-table")
data class Wish (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @ColumnInfo(name = "wish-title")
    val title: String = "",
    @ColumnInfo(name = "wish-desc")
    val description: String = "",
)


object DummyWish {
    val wishList = listOf<Wish>(
        Wish(
            title = "test 1",
            description = "dsfsfsadfsd fds sf sad",
        ),
        Wish(
            title = "test 2",
            description = "pojpokopj'o jkp'k ' pmpo",
        ),
        Wish(
            title = "test 3",
            description = "oiouhoi j0rjej p3 jpo ",
        ),
        Wish(
            title = "test 4",
            description = "pok k dasmpdmde ewa =-",
        ),
    )
}