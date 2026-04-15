package com.example.noteyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import com.example.noteyapp.repository.NotesRepository
import com.example.noteyapp.roomdb.Note
import com.example.noteyapp.roomdb.NotesDB
import com.example.noteyapp.screens.DisplayDialog
import com.example.noteyapp.screens.DisplayNotesList
import com.example.noteyapp.ui.theme.NoteyAppTheme
import com.example.noteyapp.viewmodel.NoteViewModel
import com.example.noteyapp.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Room DB
        val database =  NotesDB.getInstance(applicationContext)

        // Repository
        val repository = NotesRepository(database.notesDao)

        // ViewModel Factory
        val viewModelFactory = NoteViewModelFactory(repository)

        // ViewModel
        val noteViewModel = ViewModelProvider(this, viewModelFactory)[NoteViewModel::class.java]


        setContent {
            NoteyAppTheme {

                // Scaffold
                Scaffold(
                    floatingActionButton = { MyFAB(viewModel = noteViewModel) }
                ){ innerPadding ->
                    // Display All Records in ROOM DB
                    // Observing LiveData From a ViewModel
                    // & getting its state in a composable fun
                    val notes by noteViewModel
                        .allNotes.observeAsState(emptyList())

                    // .observeAsState: Converts a LiveData
                    // into a State Object that can be observed
                    // within a composable function

                    Column(modifier = Modifier.padding(innerPadding)) {
                        DisplayNotesList(notes = notes)
                    }
                }

            }
        }
    }
}


@Composable
fun MyFAB(viewModel: NoteViewModel){

    // Controlling the Dialog Appearance
    var showDialog by remember {
        mutableStateOf(false)
    }

    if (showDialog) {
        DisplayDialog(
            viewModel = viewModel,
            showDialog = showDialog) {
            showDialog = false
        }
    }


    FloatingActionButton(
        onClick = { showDialog = true },
        containerColor = Color.White

        ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Note"
        )
    }
}
