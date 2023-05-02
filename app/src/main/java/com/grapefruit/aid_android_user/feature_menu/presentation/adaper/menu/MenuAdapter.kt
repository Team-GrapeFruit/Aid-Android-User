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
import com.grapefruit.aid_android_user.feature_menu.data.dto.MenuDTO
import com.grapefruit.aid_android_user.feature_menu.presentation.MenuDetailPageActivity

class MenuAdapter(var itemlist: ArrayList<MenuDTO>) :
    RecyclerView.Adapter<MenuAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =
            MenuItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding, itemClickListener)
    }

    override fun getItemCount(): Int {
        return itemlist.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val menu = itemlist[position]
        holder.bind(menu)
    }

    class Holder(private val binding: MenuItemListBinding, listener: OnItemClickListener) :
        RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        init {
            binding.root.setOnClickListener {
                val intent = Intent(context, MenuDetailPageActivity::class.java)
                listener.onClick(adapterPosition)
                context.startActivity(intent)
            }
        }

        private val menuName = itemView.findViewById<TextView>(R.id.menu_name)
        private val menuDescription = itemView.findViewById<TextView>(R.id.menu_description)
        private val cost = itemView.findViewById<TextView>(R.id.cost)
        private val menuImg = itemView.findViewById<ImageView>(R.id.menu_image)

        fun bind(menuDTO: MenuDTO) {

            menuName.text = menuDTO.menu.menuName
            cost.text = menuDTO.cost.toString() + "원"

            Glide.with(menuImg)
                .load(menuDTO.menuImgURL)
                .into(binding.menuImage)
        }
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    fun setItemOnClickListener(onItemClickListener: OnItemClickListener) {
        this.itemClickListener = onItemClickListener
    }

    private lateinit var itemClickListener: OnItemClickListener
}