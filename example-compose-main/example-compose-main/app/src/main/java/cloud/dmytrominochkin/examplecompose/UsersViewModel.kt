package cloud.dmytrominochkin.examplecompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cloud.dmytrominochkin.examplecompose.model.Cat
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UsersViewModel : ViewModel() {
    private val _cats = MutableStateFlow<List<Cat>>(emptyList())
    val cats: StateFlow<List<Cat>> = _cats

    private val client = HttpClient(OkHttp) {
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    init {
        getAll()
    }

    private fun getAll() {
        viewModelScope.launch {
            try {
                val response = client.get<List<Cat>>("$BASE_URL/dogs")
                _cats.update { response }
            } catch (e: Exception) {
                _cats.update { emptyList() }
            }
        }
    }

    fun getById(id: String) = cats.value.first { it.id == id }

    companion object {
        const val BASE_URL = "https://usersdogs-ip4weamexa-ew.a.run.app"
    }
}