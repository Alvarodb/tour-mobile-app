package com.app.tours
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.recyclerview_model.view.*

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val itemTitles=arrayOf("text1", "text2", "text3")

    private val itemDetails= arrayOf("text1Des", "text2Des", "text3Des")

    private val itemImages=intArrayOf(
        R.drawable.image,
        R.drawable.image1,
        R.drawable.image2
    )

    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        var image: ImageView
        var textTitle:TextView
        var textDes: TextView

        init{
            image=itemView.findViewById(R.id.item_image)
            textTitle=itemView.findViewById(R.id.item_title)
            textDes=itemView.findViewById(R.id.item_details)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_model,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textTitle.text = itemTitles[position]
        holder.textDes.text=itemDetails[position]
        holder.image.setImageResource(itemImages [position])

        holder.itemView.setOnClickListener { v:View->
            Toast.makeText(v.context,"Clicked on the item", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return itemTitles.size
    }
}