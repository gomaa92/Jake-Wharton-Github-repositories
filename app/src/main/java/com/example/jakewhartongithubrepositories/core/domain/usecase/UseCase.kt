package com.example.jakewhartongithubrepositories.core.domain.usecase

interface UseCase<I, O> {
    fun execute(input: I? = null): O
}