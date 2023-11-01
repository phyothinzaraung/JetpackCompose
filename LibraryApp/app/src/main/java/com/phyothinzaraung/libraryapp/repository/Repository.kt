package com.phyothinzaraung.libraryapp.repository

import androidx.room.Insert
import com.phyothinzaraung.libraryapp.room.BookDB
import com.phyothinzaraung.libraryapp.room.BookEntity

class Repository(val bookDB: BookDB) {

    suspend fun addBookToRoom(bookEntity: BookEntity){
        bookDB.bookDAO().addBook(bookEntity)
    }

    fun getBooks() = bookDB.bookDAO().getBooks()

    suspend fun deleteBookFromRoom(bookEntity: BookEntity){
        bookDB.bookDAO().deleteBook(bookEntity = bookEntity)
    }

    suspend fun updateBook(bookEntity: BookEntity){
        bookDB.bookDAO().updateBook(bookEntity = bookEntity)
    }

}