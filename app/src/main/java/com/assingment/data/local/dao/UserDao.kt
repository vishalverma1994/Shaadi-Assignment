package com.assingment.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.assingment.data.local.entities.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: List<UserEntity>)

    @Update
    suspend fun updateUser(userEntity: UserEntity)

    @Query("Select * from UserEntity")
    fun getUserList(): LiveData<List<UserEntity>>

    @Query("Delete FROM UserEntity where uid =:uid")
    fun deleteUser(uid: Int)

    @Query("Delete FROM UserEntity")
    fun deleteAllUsers()
}