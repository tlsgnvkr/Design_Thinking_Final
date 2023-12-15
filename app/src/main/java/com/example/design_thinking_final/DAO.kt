package com.example.design_thinking_final

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DAO {
    @Query("SELECT * from entity")
    fun getAll(): List<Entity>

    @Query("SELECT * FROM entity WHERE id = :id")
    fun getDataById(id: Int) : Entity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: Entity)

    @Delete
    fun delete(entity: Entity)
}