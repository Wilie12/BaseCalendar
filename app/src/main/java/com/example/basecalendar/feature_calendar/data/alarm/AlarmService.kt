package com.example.basecalendar.feature_calendar.data.alarm

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.basecalendar.feature_calendar.data.util.ReminderMode
import com.example.basecalendar.feature_calendar.domain.use_case.alarm_service.AlarmServiceUseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class AlarmService: Service() {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)

    private val c = Calendar.getInstance()

    @Inject
    lateinit var alarmServiceUseCases: AlarmServiceUseCases
    @Inject
    lateinit var alarmScheduler: AlarmScheduler

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("ALARM_SERVICE", "SERVICE STARTED")
        scope.launch {
            val listOfEvents = alarmServiceUseCases.getAllCalendarEventsFromCurrentYear(
                firstDayOfYear = alarmServiceUseCases.getFirstDayOfYearInMillis(c.get(Calendar.YEAR)),
                firstDayOfNextYear = alarmServiceUseCases.getFirstDayOfNextYearInMillis(c.get(Calendar.YEAR))
            )
            listOfEvents.forEach { calendarEvent ->
                if (calendarEvent.reminderMode != ReminderMode.NONE && calendarEvent.startingDate > System.currentTimeMillis()) {
                    val alarmItem = AlarmItem(
                        time = calendarEvent.startingDate,
                        title = calendarEvent.title,
                        reminderMode = calendarEvent.reminderMode
                    )
                    alarmScheduler.cancel(alarmItem)
                    alarmScheduler.schedule(alarmItem)
                }
            }
            stopSelf()
        }
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
        Log.d("ALARM_SERVICE", "SERVICE DESTROYED")

    }
}