package com.hieuwu.groceriesstore.presentation.authentication

import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hieuwu.groceriesstore.BR
import com.hieuwu.groceriesstore.domain.entities.User
import com.hieuwu.groceriesstore.domain.models.ProductModel
import com.hieuwu.groceriesstore.presentation.utils.ObservableViewModel

class SignUpViewModel : ObservableViewModel() {

    private var _email: String? = null
    var email: String?
        @Bindable
        get() {
            return _email
        }
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }

    private var _password: String? = null
    var password: String?
        @Bindable
        get() {
            return _password
        }
        set(value) {
            _password = value
            notifyPropertyChanged(BR.password)
        }

    private var _name: String? = null
    var name: String?
        @Bindable
        get() {
            return _name
        }
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    //Declare user as live data, set it when create success fully
    //In fragment observe it
    val user: LiveData<User>? = null


}