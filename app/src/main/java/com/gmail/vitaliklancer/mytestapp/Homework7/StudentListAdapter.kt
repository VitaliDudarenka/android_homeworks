package com.gmail.vitaliklancer.mytestapp.Homework7

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gmail.vitaliklancer.mytestapp.R
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import java.util.*


class StudentListAdapter : RecyclerView.Adapter<StudentListAdapter.Holder> {
    var listData: List<Student> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    private var ctx: Context? = null
    internal var onItemClickListener: OnItemClickListener? = null
    private var filterList: List<Student>? = null
    private var studlist: ArrayList<Student> = ArrayList()
    private var mInflater: LayoutInflater? = null

    constructor(context: Context, filterList: List<Student>) {
        mInflater = LayoutInflater.from(context)
        this.mInflater = LayoutInflater.from(context)
        this.filterList = filterList
        this.studlist = ArrayList<Student>()
        this.studlist.addAll(StudentListFragment.getStudentGroup().studentSet);
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): Holder {
        var view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_student, viewGroup, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = listData.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val student = listData[position]
        holder.nameTextView.text = student.name
        holder.surnameTextView.text = student.surname ?: ""
        Picasso.get().load(student.imgURL).transform(CropCircleTransformation()).placeholder(R.drawable.progress_animation).into(holder.avaImageView)
    }

    inner class Holder : RecyclerView.ViewHolder {
        var nameTextView: TextView
        var surnameTextView: TextView
        var avaImageView: ImageView

        constructor(view: View) : super(view) {
            nameTextView = view.findViewById(R.id.nameTextView)
            surnameTextView = view.findViewById(R.id.surnameTextView)
            avaImageView = view.findViewById(R.id.studImageView)
            itemView.setOnClickListener {
                val student = listData[layoutPosition]
                onItemClickListener!!.onItemClick(student)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(student: Student)
    }

    fun filter(charText: String) {
        var charText = charText
        charText = charText.toLowerCase(Locale.getDefault())
        StudentListFragment.getStudentGroup().studentSet.clear()
        if (charText.isEmpty()) {
            StudentListFragment.getStudentGroup().studentSet.addAll(studlist)
        } else {
            for (student in studlist) {
                if ((student.name.toLowerCase(Locale.getDefault()) + " " + student.surname.toLowerCase(Locale.getDefault())).contains(charText)) {
                    StudentListFragment.getStudentGroup().studentSet.add(student)
                }
            }
        }
        notifyDataSetChanged()
    }
}