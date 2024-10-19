package com.example.mvvmflowstateflow.network

import com.example.mvvmflowstateflow.model.Product

sealed class APIState{
    class OnLoading():APIState()
    class OnSuccess(val data:List<Product>) : APIState()
    class OnFailure(val message: Throwable): APIState()
}