package com.example.inventory

import androidx.test.ext.junit.runners.AndriodJUnit4
import org.junit.runner.RunWith
import com.example.inventory.data.InventoryDatabase
import com.example.inventory.data.ItemDao
import andriod.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.Before
import org.junit.After
import java.io.IOException
import com.example.inventory.data.Item
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.jetbrains.annotations.TestOnly
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Assert.assertTrue

@RunWith(AndroidJUnit4::class)
class ItemDaoTest{
    private lateinit var itemDao:ItemDao
    private lateinit var inventoryDatabase: InventoryDatabase
    private var item1  = Item(1, "Apple", 15.0, 25)
    private var item2  = Item(2,"Bananas",5.0,50)
    @Before
    fun createDb(){
        val context: Context = ApplicationProvider.getApplicationContext()
        inventoryDatabase = Room.inMemoryDatabaseBuilder(context, InventoryDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        itemDao = inventoryDatabase.itemDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb(){
        inventoryDatabase.close()
    }

    private suspend fun addOneItemToDb(){
        itemDao.insert(item1)
    }

    private suspend fun addTwoItemToDb(){
        itemDao.insert(item1)
        itemDao.insert(item2)
    }

    @Test
    @Throws(Exception::class)
    fun daoInsert_insertItemsIntoDb() =  runBlocking{
        addOneItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
    }

    @Test
    @Throws(Exception::class)
    fun daoGetAllItems_returnAllItemsFromDb = runBlocking{
        addTwoItemToDb()
        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], item1)
        assertEquals(allItems[1], item2)
    }

    @Test
    @Throws(Exception::class)
    fun daoUpdateItems_updateItemsInDB() = runBlocking{
        addTwoItemsToDb()
        itemDao.update(Item(1, "Apple", 15.0, 25))
        itemDao.update(Item(2,"Bananas",5.0,50))

        val allItems = itemDao.getAllItems().first()
        assertEquals(allItems[0], Item(1, "Apple", 15.0, 25))
        assertEquals(allItems[1], Item(2,"Bananas",5.0,50))
    }

    @Test
    @Throws(Exception::class)
    fun daoDeleteItems_deletesAllItemsFromDB() = runningBlock{
        addTwoItemsToDb()
        itemDao.delete(item1)
        itemDao.delete(item2)
        val allItems = itemDao.getAllItems().first()
        assertTrue(allItems.isEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun daoGetItem_returnItemFromDB() = runningBlock{
        addOneItemToDb()
        val item = itemDao.getItem(1)
        assertEquals(item.first(), item1)
    }
}