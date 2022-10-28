package com.example.jakewhartongithubrepositories.core.domain.usecase


interface SuspendableUseCase<I, O> {

   suspend fun execute(input: I): O
}