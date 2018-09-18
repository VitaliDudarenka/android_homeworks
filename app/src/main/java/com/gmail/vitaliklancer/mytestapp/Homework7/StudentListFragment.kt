package com.gmail.vitaliklancer.mytestapp.Homework7

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import com.gmail.vitaliklancer.mytestapp.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.util.concurrent.CountDownLatch


class StudentListFragment : Fragment(), SearchView.OnQueryTextListener {
    private lateinit var studentsRecView: RecyclerView
    private lateinit var listData: List<Student>
    private lateinit var adapter: StudentListAdapter
    private var latch = CountDownLatch(1)
    private var mListener: OnFragmentInteractionListener? = null
    private val jsonListUploader = JSONListUploader("https://raw.githubusercontent.com/lansser/studentList/master/students.json")

    companion object {
        private val studentGroup = StudentGroup()
        fun getStudentGroup(): StudentGroup {
            return this.studentGroup
        }

        fun getInstance(): StudentListFragment {
            val fragment = StudentListFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_studentlist, container, false)
        val buttonAdd: FloatingActionButton = view.findViewById<FloatingActionButton>(R.id.addFabF)
        val searchView = view.findViewById<SearchView>(R.id.searchViewF)
        searchView.setOnQueryTextListener(this)
        if (studentGroup.studentSet.isEmpty()) {
            studentGroup.add(Student("aaaa", "bbbb", "https://upload.wikimedia.org/wikipedia/commons/4/4e/Macaca_nigra_self-portrait_large.jpg", 13))
            studentGroup.add(Student("sdsfg", "sfgdsfgsd", "https://upload.wikimedia.org/wikipedia/commons/4/4e/Macaca_nigra_self-portrait_large.jpg", 11))
            studentGroup.add(Student("gggg", "yyyyy", "https://upload.wikimedia.org/wikipedia/commons/4/4e/Macaca_nigra_self-portrait_large.jpg", 12))
            thread.start()
            latch.await()
        }
        studentsRecView = view.findViewById<RecyclerView>(R.id.recyclerViewF)
        listData = studentGroup.studentSet
        adapter = StudentListAdapter(this.activity!!, listData)
        adapter.listData = listData
        studentsRecView.adapter = adapter
        studentsRecView.layoutManager = LinearLayoutManager(activity)
        studentsRecView.setHasFixedSize(true)
        adapter.onItemClickListener = object : StudentListAdapter.OnItemClickListener {
            override fun onItemClick(student: Student) {
                //StudentInfoActivity.show(this@HW7MainActivity, student.id)
                Log.e("AAA", student.id.toString())
                mListener?.onFragmentInteraction(student.id)
            }
        }
        buttonAdd.setOnClickListener {
            val intent = Intent(activity, AddStudentActivity::class.java)
            startActivity(intent)
        }
        return view

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            adapter.filter(newText)
        }
        return false
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()

    }

    private var thread = Thread(Runnable {
        try {
            studentGroup.addAll(jsonListUploader.execute().studentSet)
            latch.countDown()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    })

    internal interface OnFragmentInteractionListener {
        fun onFragmentInteraction(id: Long)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mListener = context as? OnFragmentInteractionListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + "")
        }

    }
}
