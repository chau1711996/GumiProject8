package com.example.gumiproject8.ui

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gumiproject8.model.Thumbnail
import com.example.gumiproject8.model.favorite.FavoriteEntity
import com.example.gumiproject8.model.favorite.FavoriteListDetail
import com.example.gumiproject8.model.mymodel.BaseModel
import com.example.gumiproject8.model.mymodel.ProductModel
import com.example.gumiproject8.repository.BaseRepository
import com.example.gumiproject8.repository.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(private val repository: BaseRepository, private val favorite: FavoriteRepository): ViewModel() {

    val data = MutableLiveData<MutableList<BaseModel>?>()
    val progressLiveData = MutableLiveData<Boolean?>()
    val detail = MutableLiveData(BaseModel(0, "test", "test", "test", Thumbnail("a","b")))
    val listDetail = MutableLiveData<MutableList<BaseModel>?>()
    val processDetail = MutableLiveData<Boolean?>()
    val favoriteListLiveData = MutableLiveData<MutableList<FavoriteEntity>?>()
    val status = MutableLiveData<Boolean?>()
    val listFavoriteDetail = MutableLiveData<MutableList<FavoriteListDetail>?>()

    fun getData(name: String){
        progressLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            data.postValue(repository.getMyData(name).body()?.data?.results)
            progressLiveData.postValue(false)
        }
    }

    @SuppressLint("DefaultLocale")
    fun getDataDetail(productModel: ProductModel){
        progressLiveData.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val product = repository.getMyDataDetail(productModel.name, productModel.id).body()
            val resutl = product?.data?.results?.get(0)
            detail.postValue(resutl)

            resutl?.let {
                getFavoriteByIdProduct(it.id)
                progressLiveData.postValue(false)
                getMyListDetail(productModel, it.myListDetail.toLowerCase())
            }
        }
    }

    fun getMyListDetail(productModel: ProductModel, nameOfList: String){
        processDetail.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            val product = repository.getMyListDataDetail(productModel.name, productModel.id, nameOfList).body()
            val result = product?.data?.results
            listDetail.postValue(result)
            processDetail.postValue(false)
        }
    }

    fun insertFavorite(favoriteEntity: FavoriteEntity, list: MutableList<FavoriteListDetail>) {
        viewModelScope.launch(Dispatchers.IO) {
            favorite.insertFavorite(favoriteEntity)
            list.forEach {
                favorite.insertFavoriteListDetail(it)
            }
            status.postValue(true)
        }
    }

    fun deleteFavorite(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            favorite.deleteFavorite(id)
            status.postValue(false)
        }
    }

    fun getFavoriteByIdProduct(id: Int){
        status.postValue(false)
        viewModelScope.launch(Dispatchers.IO) {
            val result = favorite.getFavoriteById(id)
            if(result.size > 0){
                status.postValue(true)
            }
        }
    }

    fun getListDetailFavoriteById(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            val result = favorite.getListDetailFavoriteById(id)
            listFavoriteDetail.postValue(result)
        }
    }

    fun getAllFavoriteByName(title: String){
        viewModelScope.launch(Dispatchers.IO) {
            favoriteListLiveData.postValue(favorite.getFavoriteByName(title))
        }
    }

}