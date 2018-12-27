package com.packt.coolnewandroid

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class BackupWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        //backup the db
        Log.d("BackupWorker", "Doing backup now!")

        return Result.success()
    }

}


class CleanupWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        //backup the db
        Log.d("CleanupWorker", "Doing cleanup now!")

        return Result.success()
    }

}

class DeleteWorker(val context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    override fun doWork(): Result {

        //backup the db
        Log.d("DeleteWorker", "Doing delete now!")

        return Result.success()
    }

}