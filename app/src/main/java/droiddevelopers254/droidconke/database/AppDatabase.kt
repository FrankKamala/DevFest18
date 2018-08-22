package droiddevelopers254.droidconke.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context

import droiddevelopers254.droidconke.database.converters.Converter
import droiddevelopers254.droidconke.database.dao.SessionsDao
import droiddevelopers254.droidconke.database.dao.StarredSessionDao
import droiddevelopers254.droidconke.database.entities.StarredSessionEntity
import droiddevelopers254.droidconke.models.SessionsModel
import droiddevelopers254.droidconke.models.StarredSessionModel

@Database(entities = arrayOf(StarredSessionModel::class, SessionsModel::class), version = 3, exportSchema = false)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    //dao
    abstract fun sessionsDao(): SessionsDao

    abstract fun starredSessionDao(): StarredSessionDao

    companion object {
        //Singleton
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "droidconKE_db")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }

}
