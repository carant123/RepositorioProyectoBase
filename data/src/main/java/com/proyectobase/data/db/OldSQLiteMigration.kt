package biz.belcorp.ffvv.data.db

import android.content.Context
import com.proyectobase.domain.Log
import com.raizlabs.android.dbflow.config.FlowManager
import java.io.File
import javax.inject.Inject


class OldSQLiteMigration @Inject constructor(private val context: Context) {

    companion object {

        const val OLD_DB = "ffvv_old.db"
        const val NEW_DB = "ffvv.db"
        const val TAG = "OldSQLiteMigration"

    }

    fun startMigration() {
        try {
            if (isOldDatabase()) {
                renameDatabase(NEW_DB, OLD_DB)
                reopenDatabase()
                Log.i("$TAG:Start", "Success")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("$TAG:Start", e.localizedMessage)
        }
    }

    fun finishMigration() {
        try {
            if (checkDatabase(OLD_DB)) {
                deleteDatabase(OLD_DB)
                Log.i("$TAG:Finish", "Success")
            }
        } catch (e: Exception) {
            Log.e("$TAG:Finish", e.localizedMessage)
        }
    }

    fun rollbackMigration() {
        try {
            if (checkDatabase(OLD_DB)) {
                deleteDatabase(NEW_DB)
                renameDatabase(OLD_DB, NEW_DB)
                reopenDatabase()
                Log.i("$TAG:Rollback", "Success")
            }
        } catch (e: Exception) {
            Log.e("$TAG:Rollback", e.localizedMessage)
        }
    }

    private fun isOldDatabase(): Boolean {
        val db = FlowManager.getDatabase(AppDatabase.NAME).writableDatabase
        val cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='Procesamiento';", null)
        return cursor.count > 0
    }

    private fun renameDatabase(fromDB: String, toDB: String) {
        if (checkDatabase(toDB)) deleteDatabase(toDB)
        val databaseFile = context.getDatabasePath(fromDB)
        val toDatabaseFile = File(databaseFile.parentFile, toDB)
        databaseFile.renameTo(toDatabaseFile)
    }

    private fun checkDatabase(dbName: String): Boolean {
        val dbFile = context.getDatabasePath(dbName)
        return dbFile != null && dbFile.exists()
    }

    private fun deleteDatabase(dbName: String) {
        context.deleteDatabase(dbName)
    }

    private fun reopenDatabase() {
        FlowManager.getDatabase(AppDatabase.NAME).reopen()
    }

}