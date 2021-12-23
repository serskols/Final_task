package cloud.dmytrominochkin.examplecompose.model

import kotlinx.serialization.Serializable

@Serializable

data class Cat(
    val id: String,
    val name: String,
    val sex: String,
    val avatar: String,
    val age: String,
    val Personality: String,
    val tags: List<String>,
    val photos: Map<String, List<String>>

)