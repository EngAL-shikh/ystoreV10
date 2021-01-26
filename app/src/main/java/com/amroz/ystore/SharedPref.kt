import android.content.Context
import android.preference.PreferenceManager

var RULE_ADMIN="getadmin"
var RULE_EMAIL="email"
var CHAT_ID="chatid"
var USER_ID="userid"
var USER_IMAGE="userimage"
var USER_NAME="username"
var USER_ADDRESS="address"
object QueryPreferences {

    fun getStoredQuery(context: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(RULE_ADMIN, "")!!
    }
    fun setStoredQuery(context: Context, query: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(RULE_ADMIN, query)
            .apply()
    }


    fun getStoredQueryEmail(context: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(RULE_EMAIL, "")!!
    }

    fun setStoredQueryEmail(context: Context, query: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(RULE_EMAIL, query)
            .apply()
    }

    fun getStoredQueryChatid(context: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(CHAT_ID, "")!!
    }

    fun setStoredQueryChatid(context: Context, query: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(CHAT_ID, query)
            .apply()
    }

    fun getStoredQueryUserid(context: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(USER_ID, "")!!
    }

    fun setStoredQueryUserid(context: Context, query: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(USER_ID, query)
            .apply()
    }

    fun getStoredQueryUserimage(context: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(USER_IMAGE, "")!!
    }

    fun setStoredQueryUserimage(context: Context, query: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(USER_IMAGE, query)
            .apply()
    }

    fun getStoredQueryUsername(context: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(USER_NAME, "")!!
    }

    fun setStoredQueryUsername(context: Context, query: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(USER_NAME, query)
            .apply()
    }

    fun getStoredQueryUseraddress(context: Context): String {
        val prefs = PreferenceManager.getDefaultSharedPreferences(context)
        return prefs.getString(USER_ADDRESS, "")!!
    }

    fun setStoredQueryUseraddress(context: Context, query: String) {
        PreferenceManager.getDefaultSharedPreferences(context)
            .edit()
            .putString(USER_ADDRESS, query)
            .apply()
    }




}