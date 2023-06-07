package com.example.externadatabase

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class ExternalDB(
    context: Context?
) : SQLiteOpenHelper(context, "Data.db", null, 1) {
    var DATABASE_NAME = "Data.db"
    var context = context
    private val databaseDir: String? = null
    var DB_PATH = "/data/data/" + context?.packageName + "/" + "databases/"
    override fun onCreate(db: SQLiteDatabase?) {
        if (!isDatabaseExist()!!) {
            copyDatabase()

        }
    }

    override fun getWritableDatabase(): SQLiteDatabase? {
        if (!isDatabaseExist()!!) copyDatabase()
        return super.getWritableDatabase()
    }

    private fun isDatabaseExist(): Boolean {
        return File(databaseDir + DATABASE_NAME).exists()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }



    private fun copyDatabase() {
        try {
            val inputStream = context?.assets?.open("$DATABASE_NAME")
            val outputStream: FileOutputStream = FileOutputStream(databaseDir + DATABASE_NAME)
            val buffer = ByteArray(8 * 1024)
            var readed: Int
            while (inputStream?.read(buffer).also { readed = it!! } != -1) {
                outputStream.write(buffer, 0, readed)
            }
            outputStream.flush()
            outputStream.close()
            inputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


  fun retrivedata(): ArrayList<Shayrimodel> {
        var list = ArrayList<Shayrimodel>()
        var db = readableDatabase
        var que = "SELECT * FROM myShayari"
         var cursor = db.rawQuery(que,null)
        cursor.moveToFirst()
        for (i in 0..cursor.count - 1) {
            var id = cursor.getInt(0)
            var Shayri = cursor.getString(1)
            var cat = cursor.getString(2)

            var model=Shayrimodel(id, Shayri, cat)
          list.add(model)
            cursor.moveToNext()
        }
return list
    }
}