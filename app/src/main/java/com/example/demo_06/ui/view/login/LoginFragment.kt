package com.example.demo_06.ui.view.login

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.demo_06.R
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentLoginBinding
import com.example.demo_06.model.LoginInfo
import com.example.demo_06.network.RequestBuilder
import com.example.demo_06.network.api.User
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.UserLoginRes
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment: BaseFragment<FragmentLoginBinding,ViewModel>(
    FragmentLoginBinding::inflate,
    null
) {
    override fun initFragment(
        binding: FragmentLoginBinding,
        viewModel: ViewModel?,
        savedInstanceState: Bundle?
    ) {
        binding.loginBtn.setOnClickListener{
            RequestBuilder().getAPI(User::class.java).login(LoginInfo("A001","123123"))
//                .enqueue(object : Callback<BaseResponse<UserLoginRes>> {
                .enqueue(object : Callback<BaseResponse<UserLoginRes>> {
                    override fun onResponse(
                        call: Call<BaseResponse<UserLoginRes>>?,
                        response: Response<BaseResponse<UserLoginRes>>?
                    ) {
                        response?.let {
                            if(it.body().data != null) {
                                Log.e("TAG","onResponse:${it.body().data.toString()}")
                                Log.e("TAG","onResponse:${it.code()}")
                                Log.e("TAG","onResponse:${it.message()}")
                                Log.e("TAG","onResponse(code):${it.body().data?.password.toString()}")
                                Log.e("TAG","onResponse(code):${it.body().data?.account.toString()}")
                                Log.e("TAG","onResponse(message):${it.body().data?.name.toString()}")
                                Log.e("TAG","onResponse:${it.body().data}")
                                Toast.makeText(
                                    requireContext(),
                                    "${it.body().message}",
                                    Toast.LENGTH_SHORT).show()
                            }else {
                                Toast.makeText(
                                    requireContext(),
                                    "${it.body().message}",
                                    Toast.LENGTH_SHORT).show()
                            }
}
                    }

                    override fun onFailure(call: Call<BaseResponse<UserLoginRes>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                    }

                })
//            findNavController().navigate(
//                R.id.mainNavFragment,
//                null,
//                NavOptions.Builder().setPopUpTo(R.id.loginFragment,true).build())
        }
    }

}