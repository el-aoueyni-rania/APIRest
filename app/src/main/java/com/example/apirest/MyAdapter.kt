package com.example.apirest

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyAdapter (private val listData: MutableList<Offre>, private val listener: OnItemClickListener):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    private var ps : Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ligne, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val offre: Offre = listData[position]
        holder.bind(offre)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView),
        View.OnClickListener {

        private var code: TextView = itemView.findViewById(R.id.code)
        private var intitule: TextView = itemView.findViewById(R.id.intitule)
        private var specialite: TextView = itemView.findViewById(R.id.specialite)
        private var societe: TextView = itemView.findViewById(R.id.societe)
        private var nbpostes: TextView = itemView.findViewById(R.id.nbpostes)
        private var pays: TextView = itemView.findViewById(R.id.pays)
        private var delete: Button = itemView.findViewById(R.id.delete)
        private var update: Button = itemView.findViewById(R.id.update)



        init {
            ItemView.setOnClickListener(this)
        }

        fun bind(offre: Offre) {
            code.text = "code : " + offre.code.toString()
            intitule.text = "intitulé : " + offre.intitulé
            specialite.text = "specialité : " + offre.specialité
            societe.text = "société : " + offre.société
            nbpostes.text = "nbpostes : " + offre.nbpostes.toString()
            pays.text = "pays : " + offre.pays
            delete.setOnClickListener { v ->
                val scope = CoroutineScope(Dispatchers.Main)
                scope.launch {
                    try {
                        APIClient.apiService.deleteOffre(offre.code)
                        listData.removeAt(adapterPosition)
                        notifyDataSetChanged()
                        Toast.makeText(v.context, "Item Deleted successfully ",
                            Toast.LENGTH_LONG).show();
                    } catch (e: Exception) {
                        Log.e("Error", e.message.toString())
                    }
                }
            }
            update.setOnClickListener{ v ->
                val intent = Intent(v.context, UpdateOffre::class.java)
                intent.putExtra("code", offre.code)
                intent.putExtra("intitule", offre.intitulé )
                intent.putExtra("specialite", offre.specialité )
                intent.putExtra("societe", offre.société )
                intent.putExtra("nbpostes", offre.nbpostes )
                intent.putExtra("pays", offre.pays )
                v.context.startActivity(intent)


            }
            }

        override fun onClick(p0: View?) {
            ps = adapterPosition
            if (ps != RecyclerView.NO_POSITION) {
                listener.OnItemClick(ps)
            }
        }

    }
    interface OnItemClickListener {
        fun OnItemClick(position: Int)
    }
}
