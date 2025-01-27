package com.example.wishlist.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDAO: WishDAO) {

    suspend fun addWish(wish: Wish) {
        wishDAO.addWish(wish)
    }


    fun getWishes(): Flow<List<Wish>> {
        return wishDAO.getAllWishes()
    }

    fun getWish(id: Long): Flow<Wish> {
        return  wishDAO.getAWishById(id)
    }

    suspend fun updateWish(wish: Wish) {
        wishDAO.updateAWish(wish)
    }

    suspend fun deleteWish(wish: Wish) {
        wishDAO.deleteAWish(wish);
    }
}