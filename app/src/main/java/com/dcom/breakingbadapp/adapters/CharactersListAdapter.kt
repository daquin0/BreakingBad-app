package com.dcom.breakingbadapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dcom.breakingbadapp.R
import com.dcom.breakingbadapp.R.drawable.*
import com.dcom.breakingbadapp.databinding.CharacterListItemBinding
import com.dcom.breakingbadapp.models.Character


class CharactersListAdapter(private val characterList: List<Character>) : RecyclerView.Adapter<CharactersListAdapter.ViewHolder>(){

    lateinit var onClickItem: (Character) -> Unit

    inner class ViewHolder(private val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(characters: Character){

            binding.name.text= characters.name
            binding.nickname.text= characters.nickname

            when(characters.status){
                "Alive" ->{
                    Glide
                        .with(binding.root)
                        .load(detail_ic_alive)
                        .placeholder(ic_launcher_foreground)
                        .centerCrop()
                        .into(binding.statusIcon)
                }
                "Deceased" ->{
                    Glide
                        .with(binding.root)
                        .load(detail_ic_death)
                        .placeholder(ic_launcher_foreground)
                        .centerCrop()
                        .into(binding.statusIcon)
                }
                else->{
                    Glide
                        .with(binding.root)
                        .load(detail_ic_unknown)
                        .placeholder(ic_launcher_foreground)
                        .centerCrop()
                        .into(binding.statusIcon)
                }
            }
            Glide
                .with(binding.root)
                .load("${characters.img}")
                .centerCrop()
                .placeholder(ic_launcher_foreground)
                .into(binding.profileImage)

            Log.i("mz","URL: ${characters.img}")

            binding.root.setOnClickListener{
                if(::onClickItem.isInitialized)
                    onClickItem(characters)
                else
                    Log.i("mz", "onCharacterClick is not initialized")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CharacterListItemBinding.inflate(LayoutInflater.from(parent.context))

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.count()
    }
}
