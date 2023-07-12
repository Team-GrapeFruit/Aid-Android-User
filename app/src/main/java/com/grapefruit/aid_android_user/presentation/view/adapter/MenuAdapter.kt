package com.grapefruit.aid_android_user.presentation.view.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.MenuItemListBinding
import com.grapefruit.aid_android_user.data.dto.CheckMenuData
import com.grapefruit.aid_android_user.data.dto.MenuData
import com.grapefruit.aid_android_user.presentation.view.MenuDetailPageActivity

class MenuAdapter(
    val itemList: CheckMenuData,
    val glide: RequestManager
) :
    RecyclerView.Adapter<MenuAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            MenuItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.singleMenuResponse.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val menu = itemList.singleMenuResponse[position]
        val context = holder.binding.root.context
        holder.itemView.setOnClickListener {
            val intent =
                Intent(context, MenuDetailPageActivity::class.java)
            intent.putExtra("menuId", position.plus(1))
            context.startActivity(intent)
        }
        holder.bind(menu)
    }

    inner class Holder(val binding: MenuItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // private val context = binding.root.context

        private val menuName = binding.menuName
        private val cost = binding.cost
        private val menuImg = binding.menuImage

        fun bind(menuData: MenuData) {
            menuName.text = menuData.menuName
            cost.text = menuData.cost.toString() + "원"
            glide.load(menuData.menuImgUrl).centerCrop().into(menuImg)
            Log.d("ImageUrl", menuData.menuImgUrl.toString())
        }
    }
}