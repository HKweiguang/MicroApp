@file:Suppress("UNUSED")

package com.shimmer.microcore.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timerTask

object TimeUtil {

    private val dateFormat by lazy { SimpleDateFormat("yyyy-MM-dd HH:mm:ss") }
    private val dateFormat2 by lazy { SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss") }

    private const val PERIOD_DAY = 24 * 60 * 60 * 1000L
    private const val PERIOD_HOUR = 60 * 60 * 1000L
    private const val PERIOD_MIN = 60 * 1000L
    private const val PERIOD_SECOND = 1000L

    @JvmStatic
    fun formatDate(type: Int = 1) = when (type) {
        1 -> dateFormat.format(Date())
        2 -> dateFormat2.format(Date())
        else -> dateFormat.format(Date())
    }

    @JvmStatic
    val timer by lazy { Timer() }

    @JvmStatic
    val timerPool by lazy { mutableMapOf<String, TimerTask>() }

    /**
     * 获取当前时间（ms）
     */
    @JvmStatic
    fun getCurrentTime() = System.currentTimeMillis()

    /**
     * 获取日历
     */
    @JvmStatic
    fun getCalender() = Calendar.getInstance()

    /**
     * 获取当前日历
     */
    @JvmStatic
    fun getCurrentCalender() = getCalender().apply { timeInMillis = getCurrentTime() }

    /**
     * 获取日历
     */
    @JvmStatic
    fun getCalenderByDate(date: Date) = Calendar.getInstance().apply { time = date }

    /**
     * 获取当前日期
     */
    @JvmStatic
    fun getCurrentDayofWeek() = getDayofWeek(getCurrentCalender())

    /**
     * 获取日期
     */
    @JvmStatic
    fun getDayofWeek(c: Calendar) = when (c.get(Calendar.DAY_OF_WEEK)) {
        1 -> "星期日"
        2 -> "星期一"
        3 -> "星期二"
        4 -> "星期三"
        5 -> "星期四"
        6 -> "星期五"
        7 -> "星期六"
        else -> "error"
    }

    /**
     * 定时任务
     *
     * @param task 自定义任务
     * @param taskTag 任务标签
     * @param hour 小时
     * @param min 分钟
     * @param second 秒
     * @param periodType 执行间隔类型 0-秒 1-分钟 2-小时 3-天 默认0
     * @param periodNum 执行间隔数
     */
    @JvmStatic
    fun setOnTimeSchedule(
        task: () -> Unit,
        taskTag: String,
        hour: Int,
        min: Int,
        second: Int,
        periodType: Int = 0,
        periodNum: Int = 1,
        log: Boolean,
    ): String {
        var date = Calendar.getInstance().run {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, min)
            set(Calendar.SECOND, second)

            time
        }

        if (date.before(Date())) {
            date = when (periodType) {
                0 -> addSecondSchedule(date, periodNum)
                1 -> addMinSchedule(date, periodNum)
                2 -> addHourSchedule(date, periodNum)
                3 -> addDaySchedule(date, periodNum)
                else -> addSecondSchedule(date, periodNum)
            }
        }

        val timerTask = timerTask {
            if (log) {
                LogUtil.d("$taskTag : ")
            }
            try {
                task()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        timer.schedule(
            timerTask,
            date,
            when (periodType) {
                0 -> periodNum * PERIOD_SECOND
                1 -> periodNum * PERIOD_MIN
                2 -> periodNum * PERIOD_HOUR
                3 -> periodNum * PERIOD_DAY
                else -> periodNum * PERIOD_SECOND
            }
        )
        LogUtil.d("setOnTimeSchedule add")

        cancelRepeatTimeTask(taskTag)
        timerPool.put(taskTag, timerTask)

        return taskTag
    }

    /**
     * 定时任务
     */
    @JvmStatic
    fun setOnTimeScheduleOfDay(
        task: () -> Unit,
        taskTag: String = "setTimeScheduleOfDay",
        hour: Int = 0,
        min: Int = 0,
        second: Int = 0,
        periodNum: Int = 1,
        log: Boolean = true,
    ) = setOnTimeSchedule(
            task = task,
            taskTag = taskTag,
            hour = hour,
            min = min,
            second = second,
            periodType = 3,
            periodNum = periodNum,
            log = log,
        )


    /**
     * 定时任务
     */
    @JvmStatic
    fun setOnTimeScheduleOfHour(
        task: () -> Unit,
        taskTag: String = "setTimeScheduleOfHour",
        min: Int = 0,
        second: Int = 0,
        periodNum: Int = 1,
        log: Boolean = true,
    ) = setOnTimeSchedule(
        task = task,
        taskTag = taskTag,
        hour = getCurrentCalender().get(Calendar.HOUR_OF_DAY),
        min = min,
        second = second,
        periodType = 2,
        periodNum = periodNum,
        log = log,
    )


    /**
     * 定时任务
     */
    @JvmStatic
    fun setOnTimeScheduleOfMin(
        task: () -> Unit,
        taskTag: String = "setTimeScheduleOfMin",
        second: Int = 0,
        periodNum: Int = 1,
        log: Boolean = true,
    ) = setOnTimeSchedule(
            task = task,
            taskTag = taskTag,
            hour = getCurrentCalender().get(Calendar.HOUR_OF_DAY),
            min = getCurrentCalender().get(Calendar.MINUTE),
            second = second,
            periodType = 1,
            periodNum = periodNum,
            log = log,
        )


    /**
     * 定时任务
     */
    @JvmStatic
    fun setOnTimeScheduleOfSecond(
        task: () -> Unit,
        taskTag: String = "setOnTimeScheduleOfSecond",
        periodNum: Int = 1,
        log: Boolean = true,
    ) = setOnTimeSchedule(
            task = task,
            taskTag = taskTag,
            hour = getCurrentCalender().get(Calendar.HOUR_OF_DAY),
            min = getCurrentCalender().get(Calendar.MINUTE),
            second = getCurrentCalender().get(Calendar.SECOND),
            periodType = 0,
            periodNum = periodNum,
            log = log,
        )


    /**
     * 终止计时器
     */
    @JvmStatic
    fun cancelTimeSchedule() {
        timerPool.clear()
        timer.cancel()
    }

    /**
     * 终止任务
     */
    @JvmStatic
    fun cancelTimeTask(taskTag: String) {
        timerPool[taskTag]?.run {
            LogUtil.d("cancelRepeatTimeTask : $taskTag")
            cancel()
            timerPool.remove(taskTag)
            timer.purge()
        }
    }

    /**
     * 终止重复任务
     */
    @JvmStatic
    fun cancelRepeatTimeTask(taskTag: String) {
        if (timerPool.containsKey(taskTag)) {
            cancelTimeTask(taskTag)
        }
    }

    /**
     * 调度天数
     */
    @JvmStatic
    private fun addDaySchedule(date: Date, num: Int = 1) = getCalenderByDate(date).run {
        add(Calendar.DAY_OF_MONTH, num)
        time
    }

    /**
     * 调度小时数
     */
    @JvmStatic
    private fun addHourSchedule(date: Date, num: Int = 1) = getCalenderByDate(date).run {
        add(Calendar.HOUR_OF_DAY, num)
        time
    }

    /**
     * 调度分钟数
     */
    @JvmStatic
    private fun addMinSchedule(date: Date, num: Int = 1) = getCalenderByDate(date).run {
        add(Calendar.MINUTE, num)
        time
    }

    /**
     * 调度秒数
     */
    @JvmStatic
    private fun addSecondSchedule(date: Date, num: Int = 1) = getCalenderByDate(date).run {
        add(Calendar.SECOND, num)
        time
    }
}
