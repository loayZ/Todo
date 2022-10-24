package com.example.mvvmtodo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.mvvmtodo.di.AppModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Task::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao

    class Callback @Inject constructor(
        private val database: Provider<TaskDatabase>,
        @AppModule.ApplicationScope private val applicationScope: CoroutineScope

    ) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().taskDao()

            applicationScope.launch {

                dao.insert(Task("Wash the dishes"))
                dao.insert(Task("Clean the floor"))
                dao.insert(Task("Do laundry", important = true))
                dao.insert(Task("Go shopping", completed = true))
                dao.insert(Task("Eat breakfast", completed = true))
                dao.insert(Task("Wash car"))
                dao.insert(Task("Do homework"))
                dao.insert(Task("Call John"))

            }




        }

    }
}
