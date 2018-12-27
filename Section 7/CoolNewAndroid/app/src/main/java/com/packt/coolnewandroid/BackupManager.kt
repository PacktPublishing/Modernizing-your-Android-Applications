package com.packt.coolnewandroid

import android.os.Build
import androidx.work.*
import java.time.Duration
import java.util.*

object BackupManager {

    fun backupOnce() {

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .setRequiresStorageNotLow(false)
                .build()

        val compressionWork = OneTimeWorkRequest.Builder(BackupWorker::class.java)
                .setConstraints(constraints)
                .addTag("backup")
                .build()
        WorkManager.getInstance().enqueue(compressionWork)
    }

    fun cleanupOnce() {
        val compressionWork = OneTimeWorkRequest.Builder(CleanupWorker::class.java)
                .addTag("cleanup")
                .build()
        WorkManager.getInstance().enqueue(compressionWork)
    }

    fun deleteOnce() {
        val compressionWork = OneTimeWorkRequest.Builder(DeleteWorker::class.java)
                .addTag("cleanup")
                .addTag("delete")
                .build()
        WorkManager.getInstance().enqueue(compressionWork)
    }

    fun backupRecurring() {

        val compressionWork = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PeriodicWorkRequest.Builder(BackupWorker::class.java, Duration.ofMinutes(15))
                    .build()
        } else {
            OneTimeWorkRequest.Builder(BackupWorker::class.java).build()
        }
        WorkManager.getInstance().enqueue(compressionWork)
    }


    fun cleanup() {
        cleanupOnce()
        deleteOnce()
    }


    fun cleanupCorrectly() {

        val cleanupWorker: OneTimeWorkRequest = OneTimeWorkRequest.Builder(CleanupWorker::class.java).build()
        val cleanupWorker2: OneTimeWorkRequest = OneTimeWorkRequest.Builder(CleanupWorker::class.java).build()
        val deleteWorker: OneTimeWorkRequest = OneTimeWorkRequest.Builder(DeleteWorker::class.java).build()
        val deleteWorker2: OneTimeWorkRequest = OneTimeWorkRequest.Builder(DeleteWorker::class.java).build()

        WorkManager.getInstance()
                .beginWith(Arrays.asList(cleanupWorker, deleteWorker))
                .then(Arrays.asList(cleanupWorker2, deleteWorker2))
                .enqueue()

    }


    fun doUniqueClean() {
        val cleanupWorker: OneTimeWorkRequest = OneTimeWorkRequest.Builder(CleanupWorker::class.java).build()
        val cleanupWorker2: OneTimeWorkRequest = OneTimeWorkRequest.Builder(CleanupWorker::class.java).build()
        val deleteWorker2: OneTimeWorkRequest = OneTimeWorkRequest.Builder(DeleteWorker::class.java).build()

        WorkManager.getInstance().beginUniqueWork("uniqueCleanup",
                ExistingWorkPolicy.REPLACE, cleanupWorker)
                .then(Arrays.asList(cleanupWorker2, deleteWorker2))
                .then(Arrays.asList(cleanupWorker2, deleteWorker2))
                .then(Arrays.asList(cleanupWorker2, deleteWorker2))
                .enqueue()
    }


    fun cancelCleanup() {
        WorkManager.getInstance().cancelAllWorkByTag("cleanup")
    }


    fun cancelAllTasks() {
        WorkManager.getInstance().cancelAllWork()
    }

}