package com.example.quizzesapi

import java.util.*
import java.validation.constraints.*
import org.jetbrains.annotations.NotNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import java.util.*

data class Question(
    val id: UUID = UUID.randomUUID(),
    val category: Category,
    val text: String,
    val answer: String,
    val score: Int
)

val questions = mutableListOf<Question>()

data class QuestionReq(
    @field: [NotNull]
    val idCategory: UUID,
    @field:[NotNull NotBlank Size(min = 5, max = 100)]
    val text: String,
    @field:[NotNull NotBlank Size(min = 1, max = 100)]
    val answer: String,
    @field:[Min(value = 1) Max(value = 100)]
    val score: Int
)

@RestController
@RequestMapping("questions")
class QuestionController {

    fun index() = ResponseEntity.ok(questions)

}





