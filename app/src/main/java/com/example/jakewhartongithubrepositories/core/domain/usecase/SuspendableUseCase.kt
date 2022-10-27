package com.example.jakewhartongithubrepositories.core.domain.usecase


interface SuspendableUseCase<I, O> {

    fun execute(input: I): O
}