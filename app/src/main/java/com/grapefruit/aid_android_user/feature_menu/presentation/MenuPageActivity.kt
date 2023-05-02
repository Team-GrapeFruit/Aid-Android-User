package com.grapefruit.aid_android_user.feature_menu.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AbsListView.RecyclerListener
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.grapefruit.aid_android_user.MainActivity
import com.grapefruit.aid_android_user.R
import com.grapefruit.aid_android_user.databinding.ActivityMenuPageBinding
import com.grapefruit.aid_android_user.databinding.MenuItemListBinding
import com.grapefruit.aid_android_user.feature_menu.data.dto.MenuDTO
import com.grapefruit.aid_android_user.feature_menu.presentation.model.retrofit.CommunicationWork
import com.grapefruit.aid_android_user.feature_menu.presentation.viewmodel.MenuPageViewModel
import com.grapefruit.aid_android_user.view.chat.ChatAcitivity

class MenuPageActivity : AppCompatActivity() {

    lateinit var binding: ActivityMenuPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuPageBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val itemList = ArrayList<MenuDTO>()
        val menuAdapter = MenuListAdapter(itemList)
        binding.menuList.adapter = menuAdapter

        val storeId = 1L

        val menu = CommunicationWork()
        menu.menulist(storeId, this@MenuPageActivity)

        val viewModel = ViewModelProvider(this@MenuPageActivity)[MenuPageViewModel::class.java]
        val menuResponse = viewModel.menuListResponse

        binding.backBtn.setOnClickListener {
            val intent = Intent(this@MenuPageActivity, ChatAcitivity::class.java)
            startActivity(intent)
        }
    }
}

class MenuListAdapter (var itemlist: ArrayList<MenuDTO>)
    : RecyclerView.Adapter<MenuListAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =  MenuItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return itemlist.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val menu  = itemlist.get(position)
        holder.bind(menu)
    }
    class Holder(val binding : MenuItemListBinding): RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(menuDTO: MenuDTO) {

            val intent = Intent(context, R.layout.menu_detail_page::class.java)
            context.startActivity(intent)

            with(binding) {
                menuName.text = menuDTO.menuName
                menuDescription.text = menuDTO.description
                cost.text = menuDTO.cost.toString()
            }
        }
    }
}

