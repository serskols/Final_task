package cloud.dmytrominochkin.examplecompose

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import cloud.dmytrominochkin.examplecompose.model.User
import cloud.dmytrominochkin.examplecompose.ui.components.StatusBarColorProvider
import cloud.dmytrominochkin.examplecompose.ui.feed.Feed
import cloud.dmytrominochkin.examplecompose.ui.profile.Profile

@ExperimentalAnimationApi
@Composable
fun MainScreen() {
    StatusBarColorProvider()
    Surface(color = MaterialTheme.colors.onSurface) {
        var selectedId by rememberSaveable { mutableStateOf<String?>(null) }
        Crossfade(targetState = selectedId) { id ->
            if (id == null) {
                Feed(
                    users,
                    onSelected = { selectedId = it.id }
                )
            } else {
                Profile(users.first { it.id == id })
                BackHandler {
                    selectedId = null
                }
            }
        }
    }
}

private val users = mutableListOf(
    User(
        "1",
        "Cat-1",
        "Female",
        R.drawable.ava_1,
        "1",
        "White small cat",
        listOf("food", "fashion", "nature", "dogs", "people", "selfies", "style", "fashion", "cats"),

    ),
    User(
        "2",
        "Cat-2",
        "Male",
        R.drawable.ava_2,
        "2",
        "Black big cat",
        listOf("people", "selfies", "style", "fashion"),

    ),
    User(
        "3",
        "Cat-3",
        "Male",
        R.drawable.ava_3,
        "6",
        "Gray middle cat",
        listOf("selife", "cats", "nature", "fashion"),

    )
)