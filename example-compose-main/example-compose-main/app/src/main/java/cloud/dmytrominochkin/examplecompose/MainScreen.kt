package cloud.dmytrominochkin.examplecompose

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
//import androidx.compose.material.Text
//import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import cloud.dmytrominochkin.examplecompose.model.Cat
import cloud.dmytrominochkin.examplecompose.ui.components.StatusBarColorProvider
import cloud.dmytrominochkin.examplecompose.ui.feed.Feed
import cloud.dmytrominochkin.examplecompose.ui.profile.Profile

@ExperimentalAnimationApi
@Composable
fun MainScreen() {
    StatusBarColorProvider()
    Surface(color = MaterialTheme.colors.onSurface) {
        var selectedId by rememberSaveable { mutableStateOf<String?>(null) }
        val viewModel = viewModel<UsersViewModel>()
        Crossfade(targetState = selectedId) { id ->
            if (id == null) {
                Feed(
                    viewModel.cats,
                            onSelected = { selectedId = it.id }
                )
            } else {
                Profile(viewModel.getById(id))
                BackHandler {
                    selectedId = null

                }
            }
        }
    }
}

/*private val users = mutableListOf(
    Cat(
        "1",
        "Cat-1",
        "Female",
        "/images/user/ava_1.jpg",
        "1",
        "White small cat",
        listOf("food", "fashion", "nature", "dogs", "people", "selfies", "style", "fashion", "cats"),

    ),
    Cat(
        "2",
        "Cat-2",
        "Male",
        "/images/user/ava_2.jpg",
        "2",
        "Black big cat",
        listOf("people", "selfies", "style", "fashion"),

    ),
    Cat(
        "3",
        "Cat-3",
        "Male",
        "/images/user/ava_3.jdg",
        "6",
        "Gray middle cat",
        listOf("selife", "cats", "nature", "fashion"),

    )
)

*/