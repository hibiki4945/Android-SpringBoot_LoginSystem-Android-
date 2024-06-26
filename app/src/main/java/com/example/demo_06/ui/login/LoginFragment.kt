package com.example.demo_06.ui.login

import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.demo_06.R
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentLoginBinding
import com.example.demo_06.model.LoginReq
import com.example.demo_06.network.RequestBuilder
import com.example.demo_06.network.api.User
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.UserLoginRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.regex.Pattern


class LoginFragment: BaseFragment<FragmentLoginBinding,ViewModel>(
    FragmentLoginBinding::inflate,
    null
) {
    override fun initFragment(
        binding: FragmentLoginBinding,
        viewModel: ViewModel?,
        savedInstanceState: Bundle?
    ) {

        binding.accountInput.filters = arrayOf<InputFilter>(Filter,InputFilter.LengthFilter(9))
        binding.passwordInput.filters = arrayOf<InputFilter>(Filter,InputFilter.LengthFilter(20))

        binding.loginBtn.setOnClickListener{
            val account = binding.accountInput.text.toString()
            val password = binding.passwordInput.text.toString()

            RequestBuilder().getAPI(User::class.java).Login(LoginReq(account,password))
                .enqueue(object : Callback<BaseResponse<UserLoginRes>> {
                    override fun onResponse(
                        call: Call<BaseResponse<UserLoginRes>>?,
                        response: Response<BaseResponse<UserLoginRes>>?
                    ) {
                        response?.let {
                            if(it.body().data != null) {
                                Log.e("TAG","onResponse:${it.body().data.toString()}")
                                Log.e("TAG","onResponse:${it.code()}")
                                Toast.makeText(
                                    requireContext(),
                                    it.body().message,
                                    Toast.LENGTH_SHORT).show()

                                if(it.body().status == "200"){
//                                  アカウントを次のページに伝達用
                                    val bundle = Bundle()
                                    bundle.putString("account", it.body().data?.personalNo)
                                    bundle.putString("appAuthority", it.body().data?.appAuthority.toString())

                                    if(it.body().data?.appAuthority == 1) {

                                        findNavController().navigate(
                                            R.id.employeeNavFragment,
                                            bundle,
                                            NavOptions.Builder().setPopUpTo(R.id.loginFragment,true).build())
                                    }
                                    else if(it.body().data?.appAuthority == 2 || it.body().data?.appAuthority == 10) {
                                        findNavController().navigate(
                                            R.id.manageNavFragment,
                                            bundle,
                                            NavOptions.Builder().setPopUpTo(R.id.loginFragment,true).build())
                                    }
                                }

                            }else {
                                Toast.makeText(
                                    requireContext(),
                                    it.body().message,
                                    Toast.LENGTH_SHORT).show()
                            }
                        }
                    }

                    override fun onFailure(call: Call<BaseResponse<UserLoginRes>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                    }

                })

        }

    }

//  入力制限（英語と数字しか入力できる）
    private val Filter = InputFilter { source, start, end, _, _, _ ->
        val p = Pattern.compile("[0-9a-zA-Z]+")

//      デリートの判断
        val isDeleting = start > 0 && end == 0 && source?.isEmpty() == true
//      デリートの場合は、操作を許可する
        if (isDeleting) {
            return@InputFilter null
        }

//      英語と数字しか入力できません
        val m = p.matcher(source.toString())
        if (!m.matches()) {
//            Toast.makeText(
//                requireContext(),
//                "英語と数字しか入力できません",
//                Toast.LENGTH_SHORT
//            ).show()
            return@InputFilter ""
        }

//      操作を許可する
        null
    }

}