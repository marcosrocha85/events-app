package com.marcosrocha85.events.data.base

abstract class BaseMapper<TData, TModel> {
    abstract fun dataToModel(data: TData): TModel
    abstract fun modelToData(model: TModel): TData

    fun dataListToModelList(data: List<TData>): List<TModel> {
        val model = mutableListOf<TModel>()
        data.forEach {
            model.add(dataToModel(it))
        }
        return model
    }

    fun modelListToDataList(model: List<TModel>): List<TData> {
        val data = mutableListOf<TData>()
        model.forEach {
            data.add(modelToData(it))
        }
        return data
    }
}