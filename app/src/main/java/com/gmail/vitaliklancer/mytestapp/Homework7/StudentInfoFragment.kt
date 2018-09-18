package com.gmail.vitaliklancer.mytestapp.Homework7

import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.gmail.vitaliklancer.mytestapp.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation


class StudentInfoFragment : Fragment() {
    private lateinit var editTextName: EditText
    private lateinit var editTextSurname: EditText
    private lateinit var imageView: ImageView
    private lateinit var editTextUrl: EditText
    private var id: Long = 0

    companion object {
        const val STUDENT_ID = "STUDENT_ID"
        fun getInstance(id: Long): StudentInfoFragment {
            val fragment = StudentInfoFragment()
            val bundle = Bundle()
            bundle.putLong(STUDENT_ID, id)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_studentinfo, container, false)
        id = this.arguments!!.getLong("STUDENT_ID")
        editTextName = view.findViewById(R.id.editTextNameF)
        editTextSurname = view.findViewById(R.id.editTextSurnameF)
        editTextUrl = view.findViewById(R.id.editTextUrlF)
        imageView = view.findViewById(R.id.infoImageViewF)
        val studentGroup: StudentGroup = StudentListFragment.getStudentGroup()
        editTextName.setText(searchNameById(studentGroup, id))
        editTextSurname.setText(searchSurnameById(studentGroup, id))
        editTextUrl.setText(searchUrlById(studentGroup, id))
        Picasso.get().load(searchUrlById(studentGroup, id)).transform(CropCircleTransformation()).placeholder(R.drawable.progress_animation).into(imageView)
        val buttonEdit = view.findViewById<Button>(R.id.saveButtonF)
        val buttonDel = view.findViewById<Button>(R.id.deleteButtonF)

        buttonEdit.setOnClickListener {
            saveChanges(studentGroup, id, editTextName.text.toString(), editTextSurname.text.toString(), editTextUrl.text.toString())
            Toast.makeText(activity, "Changes saved", Toast.LENGTH_LONG).show()
        }
        buttonDel.setOnClickListener {
            deleteStudent(studentGroup, id)
            val intent = Intent(activity, HW7MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(activity, "Item deleted", Toast.LENGTH_LONG).show()
        }
        return view
    }

    private fun searchNameById(students: StudentGroup, id: Long): String {
        var name: String = ""
        for (student in students.studentSet) {
            if (student.id == id)
                name = student.name
        }
        return name
    }

    private fun searchSurnameById(students: StudentGroup, id: Long): String {
        var surname: String = ""
        for (student in students.studentSet) {
            if (student.id == id)
                surname = student.surname
        }
        return surname
    }

    private fun searchUrlById(students: StudentGroup, id: Long): String {
        var url: String = ""
        for (student in students.studentSet) {
            if (student.id == id)
                url = student.imgURL
        }
        return url
    }

    private fun saveChanges(students: StudentGroup, id: Long, name: String, surname: String, url: String) {
        for (student in students.studentSet) {
            if (student.id == id) {
                student.name = name
                student.surname = surname
                student.imgURL = url
            }
        }
    }

    private fun deleteStudent(students: StudentGroup, id: Long) {
        for (student in students.studentSet) {
            if (student.id == id) {
                StudentListFragment.getStudentGroup().studentSet.remove(student)
                break
            }
        }
    }
}