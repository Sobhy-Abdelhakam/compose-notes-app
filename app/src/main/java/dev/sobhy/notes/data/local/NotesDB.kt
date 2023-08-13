package dev.sobhy.notes.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.sobhy.notes.data.model.Note

@Database(entities = [Note::class], version = 1, exportSchema = true)
abstract class NotesDB: RoomDatabase(){
    abstract fun noteDao(): NotesDao

    companion object{
        @Volatile // to make other threads immediately see when a thread changes this instance
        private var instance: NotesDB? = null
        private val LOCK = Any()
        // in invoke fun whenever we create that instance of NotesDB then return the current instance
        //and if it null we will set that instance in synchronized block
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            // inside this block code can't be accessed by other threads at the same time
            // also set our instance to the result of our create database
            instance ?: createDatabase(context).also {
                instance = it
            } // then our instance of that database class will then be used to access our Note Dao
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NotesDB::class.java,
                "article_db.db"
            ).allowMainThreadQueries()
                .build()
    }
}