package com.example.seouler.Recommend

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.seouler.PlaceLikingActivity
import com.example.seouler.R
import kotlinx.android.synthetic.main.list_content.view.*

class ContentAdapter(private val items: ArrayList<ContentItem>) :
    RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ContentAdapter.ViewHolder, position: Int) {
        val item = items[position]
        val listener = View.OnClickListener { it ->
            //Toast.makeText(it.context, "Clicked: ${item.title}", Toast.LENGTH_SHORT).show()
            //item.contentid?.let { it1 -> PlaceDetailActivity(it1) }
            val detail_intent = Intent(it.context, PlaceLikingActivity::class.java)
            detail_intent.putExtra("contentId", item.contentid)
            startActivity(it.context, detail_intent, null)
        }
        holder.apply {
            bind(listener, item)
            itemView.tag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ContentAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_content, parent, false)
        return ContentAdapter.ViewHolder(inflatedView)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        private var view: View = v

        fun bind(listener: View.OnClickListener, item: ContentItem) {
            //view.thumbnail.setImageDrawable(item.image)
            view.thumbnail.setImageBitmap(item.firstimage)
            view.title.text = item.title
            //Toast.makeText(view.context, "Content Num: ${list_content.size}", Toast.LENGTH_LONG).show() // 검색된 컨텐트의 수를 보여준다.
            view.setOnClickListener(listener)
        }
    }
}