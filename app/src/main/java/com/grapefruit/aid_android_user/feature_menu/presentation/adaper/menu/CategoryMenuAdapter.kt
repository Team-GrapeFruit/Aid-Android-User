package com.grapefruit.aid_android_user.feature_menu.presentation.adaper.menu

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.MenuItemListBinding
import com.grapefruit.aid_android_user.feature_menu.data.dto.CategoryDTO
import com.grapefruit.aid_android_user.feature_menu.data.dto.MenuDTO
import com.grapefruit.aid_android_user.feature_menu.presentation.MenuDetailPageActivity

class CategoryMenuAdapter(val itemList: CategoryDTO) :
    RecyclerView.Adapter<CategoryMenuAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            MenuItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.categoryResponse.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val menu = itemList.categoryResponse[position]
        holder.bind(menu)
    }

    class Holder(val binding: MenuItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        init {
            binding.root.setOnClickListener {
                val intent = Intent(context, MenuDetailPageActivity::class.java)
                context.startActivity(intent)
            }
        }

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