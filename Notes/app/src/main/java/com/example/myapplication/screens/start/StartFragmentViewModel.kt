import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.R
import com.example.myapplication.database.firebase.AppFirebaseRepository
import com.example.myapplication.database.room.AppRoomDatabase
import com.example.myapplication.database.room.AppRoomRepository
import com.example.myapplication.utilits.*

class StartFragmentViewModel(application: Application): AndroidViewModel(application) {
    private val mContext = application
    val liveData = MutableLiveData<Boolean>()

//    init {
//        liveData.value = false
//    }

    fun initDatabase(type: String, onSuccess:()->Unit){
        when(type){
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(mContext).getAppRoomDao()
                REPOSITORY = AppRoomRepository(dao)
                onSuccess()
            }

            TYPE_FIREBASE -> {
                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase(
                    { onSuccess() },
                    { showToast(it) },
                )
            }
        }
    }
}