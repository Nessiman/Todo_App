package com.dicoding.todoapp.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery

//TODO 2 : Define data access object (DAO)
@Dao
interface TaskDao {

    //mendapatkan task berdasarkan query

    fun getTasks(query: SupportSQLiteQuery): DataSource.Factory<Int, Task>

//    @Query("SELECT * FROM task WHERE isCompleted = 1 ORDER BY isCompleted")
//    fun getTasks(query: SupportSQLiteQuery): DataSource.Factory<Int, Task>

    //mendapatkan tugas berdasarkan ID
    @Query("SELECT * FROM task ORDER BY id ASC")
    fun getTaskById(taskId: Int): LiveData<Task>

    //mendapatkan tugas aktiv terdekat
    @Query("SELECT * FROM task WHERE isCompleted = 0 ORDER BY isCompleted, date ASC LIMIT 1")
    fun getNearestActiveTask(): Task


    //menambahkan task
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task): Long

    //menambahkan beberapa task
    @Insert
    fun insertAll(vararg tasks: Task)

    //menghapus
    @Delete
    suspend fun deleteTask(task: Task)

    //mengupdate tugas yang sudah dan belum
    @Update
    suspend fun updateCompleted(taskId: Int, completed: Boolean)
    
}
