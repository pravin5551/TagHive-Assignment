package com.example.taghive.domain.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
fun FragmentActivity.replaceFragment(contanerId: Int, fragment: Fragment) {
    val manager = supportFragmentManager
    val transaction = manager.beginTransaction()
    transaction.replace(contanerId, fragment,fragment::class.java.simpleName)
    transaction.commit()
}


fun FragmentActivity.addFragment(contanerId: Int, fragment: Fragment) {
    val manager = supportFragmentManager
    val transaction = manager.beginTransaction()
    transaction.add(contanerId, fragment,fragment::class.java.simpleName).addToBackStack(fragment.javaClass.simpleName)
    transaction.commit()
}

fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(lifecycleOwner, object : Observer<T> {
        override fun onChanged(t: T?) {
            observer.onChanged(t)
            removeObserver(this)
        }
    })
}