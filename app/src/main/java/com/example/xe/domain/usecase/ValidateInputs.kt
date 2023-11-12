package com.example.xe.domain.usecase




class ValidateInputs {

    operator fun invoke(
        title : String,
        location : String,
        isLocationValid : Boolean,

        ) : Result {


        if(title.isBlank()){
            return Result.ErrorEmptyTitle
        }

        if(location.isBlank()){
            return Result.ErrorEmptyLocation
        }

        if(!isLocationValid){
            return Result.ErrorInvalidLocation
        }
        return Result.Success
    }

    sealed class Result{
        data object Success : Result()
        data object ErrorEmptyTitle : Result()
        data object ErrorEmptyLocation : Result()
        data object ErrorInvalidLocation : Result()
    }
}