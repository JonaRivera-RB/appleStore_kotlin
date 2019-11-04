package com.jonathanrivera.platzistore.Data.Model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBOpenHelper(context: Context): ManagedSQLiteOpenHelper(context, DB_NAME,null, DB_VERSION) {

    companion object {
        const val DB_NAME = "appStore"
        const val DB_VERSION = 2
        var instance: DBOpenHelper ?= null

        fun getInstance(context: Context) : DBOpenHelper? = if (instance == null) {
            instance = DBOpenHelper(context)
            instance
        }else
            instance
    }

    val Context.database:DBOpenHelper?
    get() = getInstance(applicationContext)


    object productTable{
        const val NAME = "product"
        const val ID = "id"
        const val PRODUCT = "name"
        const val DESCRIPTION = "description"
        const val PRICE = "price"
        const val IMAGE = "image"
    }

    object shoppingCarTable{
        const val NAME = "shoppingCar"
        const val ID = "id"
        const val IDPRODUCT = "idProduct"
        const val USERID = "userId"
    }

    override fun onCreate(p0: SQLiteDatabase?) {

        p0?.createTable(productTable.NAME, true,
        Pair(productTable.ID, INTEGER + PRIMARY_KEY + AUTOINCREMENT),
            Pair(productTable.PRODUCT, TEXT),
            Pair(productTable.DESCRIPTION, TEXT),
            Pair(productTable.PRICE, REAL),
            Pair(productTable.IMAGE, TEXT)
        )

        p0?.createTable(shoppingCarTable.NAME, true,
            Pair(shoppingCarTable.ID, INTEGER + PRIMARY_KEY + AUTOINCREMENT),
            Pair(shoppingCarTable.IDPRODUCT, INTEGER),
            Pair(shoppingCarTable.USERID, INTEGER)
        )
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.dropTable(productTable.NAME, true)
        p0?.dropTable(shoppingCarTable.NAME, true)

        onCreate(p0)
    }

    fun clearTables() {
        clearProducts()
        clearShoppingCar()
    }

    fun clearProducts(){
        writableDatabase.delete(productTable.NAME)
    }

    fun clearShoppingCar(){
        writableDatabase.delete(shoppingCarTable.NAME)
    }

    fun insertProduct(itemlanding: ProductModel) {
        writableDatabase.insert(productTable.NAME,
            Pair(productTable.PRODUCT, itemlanding.title),
            Pair(productTable.DESCRIPTION, itemlanding.title),
            Pair(productTable.PRICE, itemlanding.price),
            Pair(productTable.IMAGE, itemlanding.image)
            )
    }

    fun inserShoppingCard(shoppingCar: ShoppingCar) {
        writableDatabase.insert(shoppingCarTable.NAME,
            Pair(shoppingCarTable.IDPRODUCT, shoppingCar.idProduct),
            Pair(shoppingCarTable.USERID, shoppingCar.idUser)
        )
    }

    fun getProduct():List<ProductModel>?{
        var result:List<ProductModel>? = null
        use {
            try {
                result = select(productTable.NAME).exec {
                    parseList(classParser())
                }
                super.close()
            }catch (e: Throwable){
                super.close()
                error("Select trew exception: $e")
            }
        }
        return result
    }

    fun getShoppingCar():List<ShoppingCar>?{
        var result:List<ShoppingCar>? = null
        use {
            try {
                result = select(shoppingCarTable.NAME).exec {
                    parseList(classParser())
                }
                super.close()
            }catch (e: Throwable){
                super.close()
                error("Select trew exception: $e")
            }
        }
        return result
    }

    fun getShoppingCarID(userId: Int):List<ShoppingCar>?{
        var result:List<ShoppingCar>? = null
        use {
            try {
                result = select(shoppingCarTable.NAME).whereArgs("$userId = ${shoppingCarTable.USERID}").exec {
                    parseList(classParser())
                }
                super.close()
            }catch (e: Throwable){
                super.close()
                error("Select trew exception: $e")
            }
        }
        return result
    }

}