package me.kariot.todoapp

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.FrameLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import me.kariot.todoapp.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    val PREF_NAME = "taskPref"
    val PREF_ARRAY = "prefArray"

    private lateinit var binding: ActivityMainBinding
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initUI()
    }

    private fun initUI() {
        getAllTasks();

        binding.fabAddItem.setOnClickListener {
            showInputAlert()
        }

    }

    private fun showInputAlert() {
        //create an alert dialog object
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Enter your task.")

        //create a frame layout object
        val layout = FrameLayout(this)
        layout.setPaddingRelative(45, 15, 45, 15)

        //create an edittext object
        val edittext = EditText(this)
        edittext.hint = "Enter Task"
        edittext.maxLines = 1

        //add the view to the layout
        layout.addView(edittext)

        //set the layout to the alert dialog
        alert.setView(layout)

        //function to set the title and click action of positive button
        alert.setPositiveButton("Save") { _, _ ->
            //store the input in a variable
            val task = edittext.text.toString()

            //if it has value, call the saveTask function
            if (task.isNotEmpty()) {
                saveTask(task)
            }
        }

        //function to set the title and click action of positive button
        alert.setNegativeButton("Cancel") { _, _ ->

        }

        //display the alert
        alert.show()
    }

    private fun getAllTasks() {
//        val sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        val existingArray = sharedPref.getString(PREF_ARRAY, "[]") ?: "[]"
//        val array = JSONArray(existingArray)

        //instantiate TodoAdapter
        todoAdapter = TodoAdapter(mutableListOf())
        //set the layout manager of recycler view
        binding.recyclerTasks.layoutManager = LinearLayoutManager(this)
        //set the adapter of recycler view
        binding.recyclerTasks.adapter = todoAdapter
    }

    private fun saveTask(task: String) {

        val todo = Todo(task, false, UUID.randomUUID().toString())
        todoAdapter.addTodo(todo)

//        //JSONObject is a modifiable set of name/value mappings.
//        val jsonObject = JSONObject()
//        //put() Maps name to value, clobbering any existing name/value mapping with the same name.
//        jsonObject.put("task", task)
//        jsonObject.put("isCompleted", false)
//        jsonObject.put("id", UUID.randomUUID().toString().replace("-", "").uppercase())
//
//        //Shared Preferences allow the activities or applications to store and retrieve data
//        // in the form of key and value. The data stored in the application remains to persist
//        // even if the app is closed until it has deleted or cleared.
//
//        //create a new shared preference file or access an existing one
//        val sharedPref = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        //getString() retrieves string values from a shared preferences file
//        val existingArray = sharedPref.getString(PREF_ARRAY, "[]") ?: "[]"
//        //converting string to JSONArray
//        val array = JSONArray(existingArray)
//        //put() add element to the end of the array
//        array.put(todo)
//
//        //create a SharedPreferences.Editor to write to a SP file
//        val editor = sharedPref.edit()
//        //putString() writes string values to a shared preferences file
//        editor.putString(PREF_ARRAY, array.toString())
//        //apply() saves the changes to the shared preference file
//        editor.apply()


    }
}