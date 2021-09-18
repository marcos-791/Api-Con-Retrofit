package com.mc.apiconretrofitkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mc.apiconretrofitkotlin.ApiAdapter.Apiservice
import com.mc.apiconretrofitkotlin.ApiAdapter.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.mc.apiconretrofitkotlin.RecyclerAdapter.MyAdapter


class MainActivity : AppCompatActivity() {
    //Url de prueba, cortesia de jsonplaceholder para probar el uso de las Api
    private val baseUrl = "https://jsonplaceholder.typicode.com/"
    private lateinit var edtPalabra : EditText
    private lateinit var btnBuscar : Button
    private lateinit var recyclerView : RecyclerView
    private lateinit var list : ArrayList<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        showMyList()

        btnBuscar.setOnClickListener {
            list.clear()
            searchFromList( edtPalabra.text.toString() )
        }
    }

    //funcion para limpiar el onCreate y mantenerse mas prolijo
    private fun initUI(){
        //cambiar el titulo de la barra
        val actionBar = supportActionBar
        actionBar!!.title = getString(R.string.retrofit)

        //Inicio las variables de la UI
        edtPalabra = findViewById(R.id.edtPalabra)
        btnBuscar = findViewById(R.id.btnBuscar)
        recyclerView = findViewById(R.id.recycler_view)

        list = arrayListOf()
    }

    //Despliega la lista completa de Retrofit
    private fun showMyList(){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
        val apiService : Apiservice = retrofit.create(Apiservice::class.java)
        val call : Call<List<Post>> = apiService.showList()

        call.enqueue( object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

                if(response.body() != null){
                    val postList = response.body()
                    if (postList != null)
                        list.addAll(postList)

                    createRecyclerList()
                }

            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {

            }
        })
    }

    //Despliega la lista de lo que se busca
    private fun searchFromList(q : String){
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()
        val apiService : Apiservice = retrofit.create(Apiservice::class.java)
        val call : Call<List<Post>> = apiService.find(q)

        call.enqueue( object : Callback<List<Post>>{
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {

                if(response.body() != null){
                    val postList = response.body()
                    if (postList != null)
                        list.addAll(postList)

                    createRecyclerList()
                }

            }

            override fun onFailure(call: Call<List<Post>>?, t: Throwable?) {
                Toast.makeText(applicationContext,"Error: "+t.toString(),Toast.LENGTH_SHORT).show()
            }
        })
    }

    //Arma la lista y muestra en RecyclerView
    private fun createRecyclerList(){
        var adapter = MyAdapter(this,list)
        var linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
    }

}