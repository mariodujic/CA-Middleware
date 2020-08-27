package com.groundzero.camw.quizzes.controller

import com.groundzero.camw.common.DistributorRepository
import com.groundzero.camw.network.NetworkResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/quizzes")
class QuizController(private val distributorRepository: DistributorRepository) {

    @GetMapping
    fun getQuizzes(): NetworkResponse {
        distributorRepository.getQuizzes()?.let {
            return NetworkResponse.Success(200, "Success", it)
        }
        return NetworkResponse.Error(404, "Error")
    }
}