package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.data.Task

class DetailTaskActivity : AppCompatActivity() {

    private lateinit var detailTaskViewModel: DetailTaskViewModel

    companion object {
        const val EXTRA_TASK_ID = "extra_task_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action

        val taskId = intent.getIntExtra(EXTRA_TASK_ID,0)
        detailTaskViewModel = ViewModelProvider(this)[DetailTaskViewModel::class.java]

        if (taskId != 0){
            observeTaskDetail(taskId)
        }else{
            finish()
        }
    }

    private fun observeTaskDetail(taskId: Int) {
        detailTaskViewModel.setTaskId(taskId)

        // Observe the task details and update the UI accordingly
        detailTaskViewModel.task.observe(this) { task ->
            // Example: Update your UI with the task details
            findViewById<TextView>(R.id.detail_ed_title).text = task.title
            findViewById<TextView>(R.id.detail_ed_description).text = task.description

            // Set up the delete button click listener
            findViewById<Button>(R.id.btn_delete_task).setOnClickListener {
                deleteTask(task)
            }
        }
    }

    private fun deleteTask(task: Task) {
        detailTaskViewModel.deleteTask(task)
        finish()
    }


}