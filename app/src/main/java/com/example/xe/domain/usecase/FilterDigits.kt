package com.example.xe.domain.usecase

class FilterDigits {

    operator fun invoke(number : String) : String{


        if(number.length > 1){
            if(number[0] == '0' && number[1]!= '.'){
                return "0"
            }
        }


        val filteredInput =  number.filter { it.isDigit() || it == '.'}
        val decimalCount = filteredInput.count{it == '.'}

        return if(decimalCount > 1){
            filteredInput.dropLast(1)
        }else {
            filteredInput
        }
    }

}
