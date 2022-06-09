package at.ac.myplanningpal.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import at.ac.myplanningpal.models.Appointment
import at.ac.myplanningpal.models.Note

@Database(
    entities = [Note::class, Appointment::class],
    version = 1,
    exportSchema = true
)
abstract class MyPlanningPalDB: RoomDatabase() {
    abstract fun notesDao(): NotesDao
    abstract fun appointmentsDao(): AppointmentsDao

    companion object {
        private var INSTANCE: MyPlanningPalDB? = null

        fun getDatabase(context: Context): MyPlanningPalDB {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also {
                    INSTANCE = it
                }
            }
        }

        private fun buildDatabase(context: Context): MyPlanningPalDB {
            return Room.databaseBuilder(context, MyPlanningPalDB::class.java, "myplanningpal")
                .addCallback(
                    object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                        }
                    }
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}