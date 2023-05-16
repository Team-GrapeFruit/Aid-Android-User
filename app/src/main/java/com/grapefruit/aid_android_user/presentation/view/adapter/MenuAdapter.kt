package com.grapefruit.aid_android_user.presentation.view.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.MenuItemListBinding
import com.grapefruit.aid_android_user.data.dto.CheckMenuDTO
import com.grapefruit.aid_android_user.data.dto.MenuDTO
import com.grapefruit.aid_android_user.presentation.view.MenuDetailPageActivity

class MenuAdapter(val itemList: CheckMenuDTO) :
    RecyclerView.Adapter<MenuAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            MenuItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        Log.d("Adapter-menu-size", itemList.singleMenuResponse.size.toString())
        return itemList.singleMenuResponse.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val menu = itemList.singleMenuResponse[position]
        val context = holder.binding.root.context
        holder.itemView.setOnClickListener {
            val intent =
                Intent(context, MenuDetailPageActivity::class.java)
            context.startActivity(intent)
        }
        holder.bind(menu)
    }

    class Holder(val binding: MenuItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        /*init {
            binding.root.setOnClickListener {
                val intent = Intent(context, MenuDetailPageActivity::class.java)
                context.startActivity(intent)
            }
        }*/

        private val menuName = itemView.findViewById<TextView>(R.id.menu_name)
        private val cost = itemView.findViewById<TextView>(R.id.cost)
        private val menuImg = itemView.findViewById<ImageView>(R.id.menu_image)

        fun bind(menuDTO: MenuDTO) {
            menuName.text = menuDTO.menuName
            cost.text = menuDTO.cost.toString() + "원"
            Glide.with(menuImg)
                .load(menuDTO.menuImgURL)
                .into(binding.menuImage)
        }
    }
}