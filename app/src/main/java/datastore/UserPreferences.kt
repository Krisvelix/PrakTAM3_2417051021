package com.example.praktam_2417051021.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private val Context.dataStore by preferencesDataStore("user_pref")

class UserPreferences(
    private val context: Context
) {

    companion object {

        val SKINCARE_PROGRESS_KEY = intPreferencesKey("skincare_progress")
        val LAST_SKINCARE_DATE_KEY = stringPreferencesKey("last_skincare_date")
        val SKINCARE_HISTORY_KEY = stringPreferencesKey("skincare_history")
        val SKINCARE_WEEK_KEY = intPreferencesKey("skincare_week")

        // =========================
        // USER
        // =========================
        val USERNAME_KEY = stringPreferencesKey("username")
        val PASSWORD_KEY = stringPreferencesKey("password")
        val EMAIL_KEY = stringPreferencesKey("email")
        val PHONE_KEY = stringPreferencesKey("phone")

        // =========================
        // WORKOUT
        // =========================
        val WORKOUT_PROGRESS_KEY = intPreferencesKey("workout_progress")
        val LAST_WORKOUT_DATE_KEY = stringPreferencesKey("last_workout_date")
        val WORKOUT_HISTORY_KEY = stringPreferencesKey("workout_history")
        val WORKOUT_WEEK_KEY = intPreferencesKey("workout_week")

        // =========================
        // SELF CARE
        // =========================
        val SELFCARE_PROGRESS_KEY = intPreferencesKey("selfcare_progress")
        val LAST_SELFCARE_DATE_KEY = stringPreferencesKey("last_selfcare_date")
        val SELFCARE_HISTORY_KEY = stringPreferencesKey("selfcare_history")
        val SELFCARE_WEEK_KEY = intPreferencesKey("selfcare_week")

        // =========================
        // JOURNAL
        // =========================
        val JOURNAL_PROGRESS_KEY = intPreferencesKey("journal_progress")
        val LAST_JOURNAL_DATE_KEY = stringPreferencesKey("last_journal_date")
        val JOURNAL_HISTORY_KEY = stringPreferencesKey("journal_history")
        val JOURNAL_WEEK_KEY = intPreferencesKey("journal_week")

        // =========================
        // HYDRATION
        // =========================
        val HYDRATION_HISTORY_KEY = stringPreferencesKey("hydration_history")
        val LAST_HYDRATION_DATE_KEY = stringPreferencesKey("last_hydration_date")
        val HYDRATION_PROGRESS_KEY = intPreferencesKey("hydration_progress")
        val HYDRATION_WEEK_KEY = intPreferencesKey("hydration_week")
    }

    // =========================
    // USER
    // =========================
    suspend fun saveUser(username: String, password: String, email: String, phone: String) {
        context.dataStore.edit {
            it[USERNAME_KEY] = username
            it[PASSWORD_KEY] = password
            it[EMAIL_KEY] = email
            it[PHONE_KEY] = phone
        }
    }

    suspend fun getUsername(): String {
        return context.dataStore.data.first()[USERNAME_KEY] ?: ""
    }

    suspend fun getPassword(): String {
        return context.dataStore.data.first()[PASSWORD_KEY] ?: ""
    }

    suspend fun getEmail(): String {
        return context.dataStore.data.first()[EMAIL_KEY] ?: ""
    }

    suspend fun getPhone(): String {
        return context.dataStore.data.first()[PHONE_KEY] ?: ""
    }

    suspend fun updatePassword(newPassword: String) {
        context.dataStore.edit {
            it[PASSWORD_KEY] = newPassword
        }
    }

    // =========================
    // WORKOUT
    // =========================
    suspend fun saveWorkoutProgress(progress: Int) {
        context.dataStore.edit {
            it[WORKOUT_PROGRESS_KEY] = progress
        }
    }

    suspend fun getWorkoutProgress(): Int {
        return context.dataStore.data.first()[WORKOUT_PROGRESS_KEY] ?: 0
    }

    suspend fun saveLastWorkoutDate(date: String) {
        context.dataStore.edit {
            it[LAST_WORKOUT_DATE_KEY] = date
        }
    }

    suspend fun getLastWorkoutDate(): String {
        return context.dataStore.data.first()[LAST_WORKOUT_DATE_KEY] ?: ""
    }

    suspend fun saveWorkoutHistory(history: String) {
        context.dataStore.edit {
            it[WORKOUT_HISTORY_KEY] = history
        }
    }

    suspend fun getWorkoutHistory(): String {
        return context.dataStore.data.first()[WORKOUT_HISTORY_KEY] ?: ""
    }

    suspend fun saveWorkoutWeek(week: Int) {
        context.dataStore.edit { it[WORKOUT_WEEK_KEY] = week }
    }

    suspend fun getWorkoutWeek(): Int {
        return context.dataStore.data.first()[WORKOUT_WEEK_KEY] ?: -1
    }

    // =========================
    // SELF CARE
    // =========================
    suspend fun saveSelfCareProgress(progress: Int) {
        context.dataStore.edit {
            it[SELFCARE_PROGRESS_KEY] = progress
        }
    }

    suspend fun getSelfCareProgress(): Int {
        return context.dataStore.data.first()[SELFCARE_PROGRESS_KEY] ?: 0
    }

    suspend fun saveLastSelfCareDate(date: String) {
        context.dataStore.edit {
            it[LAST_SELFCARE_DATE_KEY] = date
        }
    }

    suspend fun getLastSelfCareDate(): String {
        return context.dataStore.data.first()[LAST_SELFCARE_DATE_KEY] ?: ""
    }

    suspend fun saveSelfCareHistory(history: String) {
        context.dataStore.edit {
            it[SELFCARE_HISTORY_KEY] = history
        }
    }

    suspend fun getSelfCareHistory(): String {
        return context.dataStore.data.first()[SELFCARE_HISTORY_KEY] ?: ""
    }

    suspend fun saveSelfCareWeek(week: Int) {
        context.dataStore.edit {
            it[SELFCARE_WEEK_KEY] = week
        }
    }

    suspend fun getSelfCareWeek(): Int {
        return context.dataStore.data.first()[SELFCARE_WEEK_KEY] ?: -1
    }

    // =========================
    // JOURNAL
    // =========================
    suspend fun saveJournalProgress(progress: Int) {
        context.dataStore.edit {
            it[JOURNAL_PROGRESS_KEY] = progress
        }
    }

    suspend fun getJournalProgress(): Int {
        return context.dataStore.data.first()[JOURNAL_PROGRESS_KEY] ?: 0
    }

    suspend fun saveLastJournalDate(date: String) {
        context.dataStore.edit {
            it[LAST_JOURNAL_DATE_KEY] = date
        }
    }

    suspend fun getLastJournalDate(): String {
        return context.dataStore.data.first()[LAST_JOURNAL_DATE_KEY] ?: ""
    }

    suspend fun saveJournalHistory(history: String) {
        context.dataStore.edit {
            it[JOURNAL_HISTORY_KEY] = history
        }
    }

    suspend fun getJournalHistory(): String {
        return context.dataStore.data.first()[JOURNAL_HISTORY_KEY] ?: ""
    }

    suspend fun saveJournalWeek(week: Int) {
        context.dataStore.edit {
            it[JOURNAL_WEEK_KEY] = week
        }
    }

    suspend fun getJournalWeek(): Int {
        return context.dataStore.data.first()[JOURNAL_WEEK_KEY] ?: -1
    }

    // =========================
    // RESET JOURNAL
    // =========================
    suspend fun resetJournal() {
        context.dataStore.edit {
            it[JOURNAL_PROGRESS_KEY] = 0
            it[JOURNAL_HISTORY_KEY] = ""
            it[LAST_JOURNAL_DATE_KEY] = ""
            it[JOURNAL_WEEK_KEY] = -1
        }
    }

    // =========================
    // SKINCARE
    // =========================

    suspend fun saveSkincareHistory(history: String) {
        context.dataStore.edit {
            it[SKINCARE_HISTORY_KEY] = history
        }
    }

    suspend fun getSkincareHistory(): String {
        return context.dataStore.data.first()[SKINCARE_HISTORY_KEY] ?: ""
    }

    suspend fun saveSkincareProgress(progress: Int) {
        context.dataStore.edit {
            it[SKINCARE_PROGRESS_KEY] = progress
        }
    }

    suspend fun getSkincareProgress(): Int {
        return context.dataStore.data.first()[SKINCARE_PROGRESS_KEY] ?: 0
    }

    suspend fun saveLastSkincareDate(date: String) {
        context.dataStore.edit {
            it[LAST_SKINCARE_DATE_KEY] = date
        }
    }

    suspend fun getLastSkincareDate(): String {
        return context.dataStore.data.first()[LAST_SKINCARE_DATE_KEY] ?: ""
    }

    suspend fun saveSkincareWeek(week: Int) {
        context.dataStore.edit {
            it[SKINCARE_WEEK_KEY] = week
        }
    }

    suspend fun getSkincareWeek(): Int {
        return context.dataStore.data.first()[SKINCARE_WEEK_KEY] ?: -1
    }

    suspend fun resetSkincare() {
        context.dataStore.edit {
            it[SKINCARE_PROGRESS_KEY] = 0
            it[LAST_SKINCARE_DATE_KEY] = ""
            it[SKINCARE_HISTORY_KEY] = ""
            it[SKINCARE_WEEK_KEY] = -1
        }
    }

    // =========================
    // HYDRATION
    // =========================
    suspend fun saveHydrationHistory(history: String) {
        context.dataStore.edit {
            it[HYDRATION_HISTORY_KEY] = history
        }
    }

    suspend fun getHydrationHistory(): String {
        return context.dataStore.data.first()[HYDRATION_HISTORY_KEY] ?: ""
    }

    suspend fun saveLastHydrationDate(date: String) {
        context.dataStore.edit {
            it[LAST_HYDRATION_DATE_KEY] = date
        }
    }

    suspend fun getLastHydrationDate(): String {
        return context.dataStore.data.first()[LAST_HYDRATION_DATE_KEY] ?: ""
    }

    suspend fun saveHydrationProgress(progress: Int) {
        context.dataStore.edit {
            it[HYDRATION_PROGRESS_KEY] = progress
        }
    }

    suspend fun getHydrationProgress(): Int {
        return context.dataStore.data.first()[HYDRATION_PROGRESS_KEY] ?: 0
    }

    suspend fun saveHydrationWeek(week: Int) {
        context.dataStore.edit { it[HYDRATION_WEEK_KEY] = week }
    }

    suspend fun getHydrationWeek(): Int {
        return context.dataStore.data.first()[HYDRATION_WEEK_KEY] ?: -1
    }

    suspend fun resetHydration() {
        context.dataStore.edit {
            it[HYDRATION_PROGRESS_KEY] = 0
            it[LAST_HYDRATION_DATE_KEY] = ""
            it[HYDRATION_HISTORY_KEY] = ""
            it[HYDRATION_WEEK_KEY] = -1
        }
    }
}