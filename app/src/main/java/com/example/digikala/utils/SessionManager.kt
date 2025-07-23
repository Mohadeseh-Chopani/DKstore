import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SessionManager(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_session")

    companion object {
        val USER_ID_KEY = stringPreferencesKey("current_user_id")
    }

    // این تابع را بعد از لاگین موفق صدا بزنید
    suspend fun saveUserId(id: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = id
        }
    }

    // ViewModel از این Flow برای خواندن خودکار شناسه استفاده می‌کند
    val getUserIdFlow: Flow<String?> = context.dataStore.data
        .map { preferences ->
            preferences[USER_ID_KEY]
        }

    suspend fun clearSession() {
        context.dataStore.edit { it.clear() }
    }
}