package com.gmail.vitaliklancer.mytestapp.Homework7

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.gmail.vitaliklancer.mytestapp.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class AddStudentActivity : Activity() {
    private lateinit var editTextName: EditText
    private lateinit var editTextSurname: EditText
    private lateinit var imageView: ImageView
    private lateinit var editTextUrl: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addstudent)
        val studentGroup: StudentGroup = StudentListFragment.getStudentGroup()
        editTextName = findViewById(R.id.editTextNameAdd)
        editTextSurname = findViewById(R.id.editTextSurnameAdd)
        editTextUrl = findViewById(R.id.editTextUrlAdd)
        imageView = findViewById(R.id.infoImageViewAdd)
        if(!editTextUrl.text.isEmpty())
        Picasso.get().load(editTextUrl.text.toString()).transform(CropCircleTransformation()).placeholder(R.drawable.progress_animation).into(imageView)
        val buttonAdd: Button = findViewById(R.id.addButton)



        buttonAdd.setOnClickListener {
            studentGroup.studentSet.add(Student(editTextName.text.toString(), editTextSurname.text.toString(), editTextUrl.text.toString(), System.currentTimeMillis()))
            Picasso.get().load(editTextUrl.text.toString()).transform(CropCircleTransformation()).placeholder(R.drawable.progress_animation).into(imageView)
            editTextUrl.setText("")
            editTextName.setText("")
            editTextSurname.setText("")
            val intent = Intent(this@AddStudentActivity, HW7MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Item added", Toast.LENGTH_LONG).show()
        }


    }
}