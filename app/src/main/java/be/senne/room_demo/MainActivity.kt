package be.senne.room_demo

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import be.senne.room_demo.ui.theme.Room_demoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val entity = Entity(null, "Een interessante titel", "Een goede beschrijving")

        val db = Room.databaseBuilder(applicationContext, RoomDatabase::class.java, "mijn-database").allowMainThreadQueries().build()
        val entityDAO = db.entityDAO()
        val entities = entityDAO.getAll()

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
                    Greeting("Android")
                }
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