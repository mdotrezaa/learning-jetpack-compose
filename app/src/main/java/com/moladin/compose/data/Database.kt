package com.moladin.compose.data

class Database private constructor() {

    companion object {
        val Instance by lazy {
            Database()
        }

        val sources by lazy {
            carPrefilled
        }
    }

    fun insert(carEntity: CarEntity) {
        val existingData = sources.find { it.name == carEntity.name }
        val currentIndex = sources.size
        if (existingData == null) {
            sources.add(CarEntity(carId = carEntity.carId, name = carEntity.name, imageUrl = carEntity.imageUrl))
        }
    }

    fun update(carEntity: CarEntity) {
        val toUpdate = sources.find { it.carId == carEntity.carId }
        val index = sources.indexOf(toUpdate)
        toUpdate?.let {
            sources[index] = it.copy(name = carEntity.name)
        }
    }

    fun delete(carId: Int) {
        val toDelete = sources.find { it.carId == carId }
        val index = sources.indexOf(toDelete)
        sources.removeAt(index)
    }

    fun getCarById(carId: Int) = sources.find { it.carId == carId }

    fun getCars(): List<CarEntity> = sources
}


private val carPrefilled = arrayListOf(
    CarEntity(1, "Mitsubishi Pajero Sport", "https://loremflickr.com/320/240/cars,sport"),
    CarEntity(2, "Suzuki Karimun","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(3, "Toyota Kijang Inova","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(4, "Mitsubishi Kuda","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(5, "Hyundai Ionic","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(6, "EV Tesla 2.0","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(7, "Suzuki Splash 2017","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(8, "Honda Jazz Rs 2018","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(9, "Suzuki Jimny 1993","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(10, "Suzuki Jimmny 2020","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(11, "Isuzu Panther","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(12, "Mazda 2","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(13, "Mercedes Benz","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(14, "Alphard","https://loremflickr.com/320/240/cars,sport"),
    CarEntity(15, "Toyota HiAce","https://loremflickr.com/320/240/cars,sport")
)