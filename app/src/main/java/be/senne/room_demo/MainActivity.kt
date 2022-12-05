package be.senne.room_demo

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import be.senne.room_demo.ui.theme.Room_demoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Button
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {

    companion object {
        lateinit var context: Context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = applicationContext

        val entity = Entity(null, "Een interessante titel", "Een goede beschrijving")

        val db = Room.databaseBuilder(applicationContext, RoomDatabase::class.java, "mijn-database").build()
        val entityDAO = db.entityDAO()

        var entities : List<Entity> = listOf()

        CoroutineScope(Dispatchers.Main).launch {
            entities = entityDAO.getAll()
            if(entities.isEmpty()) {
                entityDAO.insert(entity)
                Toast.makeText(applicationContext, "Entity is aan database toegevoegd.", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(applicationContext, "Entity is al in database (${entities[0].description})", Toast.LENGTH_SHORT).show()
            }
            setContent {
                Room_demoTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                        drawDb(entities)
                    }
                }
        }
    }
}

@Composable
fun drawDb(entities : List<Entity>) {

    val state = rememberLazyListState()

    LazyColumn(
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        state = state
    ) {
        item {
            Button(onClick = {
                CoroutineScope(Dispatchers.Main).launch {
                    val entity = Entity(null, "Entity0", "Desc")
                    RoomDatabase.instance.value.entityDAO().insert(entity)
                }
            }) {
                Text("Add Entity")
            }
        }

        itemsIndexed(entities) { index, entity ->
            Text("$index: ${entity.title}")
        }

    }
}
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Room_demoTheme {
        Greeting("Android")
    }
}