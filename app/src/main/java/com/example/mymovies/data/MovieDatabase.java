package com.example.mymovies.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    private final static String dbName = "movies.db";
    private static MovieDatabase database;
    private static final Object LOCK = new Object();

    public static MovieDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null)
                database = Room.databaseBuilder(context, MovieDatabase.class, dbName)
                        .build();
        }
        return database;
    }

    public abstract MovieDao movieDao();
}
