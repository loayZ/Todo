package com.example.mvvmtodo.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
//we use '%' || to ensure that searchQuery find the best results
    @Query("SELECT * FROM task_table WHERE name LIKE '%' || :searchQuery || '%' ORDER BY important DESC" )
    fun getTasks(searchQuery: String): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(task: Task)

    @Update
     fun update(task: Task)

    @Delete
     fun delete(task: Task)
}