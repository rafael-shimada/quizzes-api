package com.example.quizzesapi

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

data class Category(val id: UUID = UUID.randomUUID(), var name: String)

val categories = mutableListOf<Category>()

data class CategoryReq(val name: String)

@RestController
@RequestMapping("categories")
class CategoryController {

    @GetMapping
    fun index () = ResponseEntity.ok(categories)

    @PostMapping
    fun create(@RequestBody categoryReq: CategoryReq): ResponseEntity<Category> {
        if(categories.any { it.name == categoryReq.name})
            throw ResponseStatusException(HttpStatus.CONFLICT, "Category ja tem")

        val category = Category(name = categoryReq.name)
        categories.add(category)
        return ResponseEntity(category, HttpStatus.CREATED)
    }

    @GetMapping("{id}")
    fun show(@PathVariable id: UUID): ResponseEntity<Category> {
        val category = getCategory(id)

        if(category == null)
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "category not found")

        return ResponseEntity.ok(category)
    }

    @PutMapping("{id}")
    fun update(@PathVariable id: UUID, @RequestBody categoryReq: CategoryReq): ResponseEntity<Category> {
        val category = getCategory(id)
        category.name = categoryReq.name
        return ResponseEntity.ok(Category)
    }

    @DeleteMapping("{id}")
    fun delete(@PathVariable id: UUID): ResponseEntity<Category> {
        val category = getCategory(id)
        categories.remove(category)
        return  ResponseEntity.noContent().build()
    }

    private fun getCategory(id: UUID): Category {
        return categories.firstOrNull { it.id == id }
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "catetegory not found")
    }

}