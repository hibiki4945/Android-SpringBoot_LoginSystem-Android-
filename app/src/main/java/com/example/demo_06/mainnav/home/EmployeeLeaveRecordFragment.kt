package com.example.demo_06.mainnav.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.LinearLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.demo_06.base.BaseFragment
import com.example.demo_06.databinding.FragmentEmployeeLeaveRecordBinding
import com.example.demo_06.databinding.FragmentManageLeaveRecordBinding
import com.example.demo_06.mainnav.accountPublic0
import com.example.demo_06.model.HolidayRecordReq
import com.example.demo_06.network.RequestBuilder
import com.example.demo_06.network.api.User
import com.example.demo_06.network.res.BaseResponse
import com.example.demo_06.network.res.HolidayAcquire
import com.example.mvvm_learning.setruth.mvvmlearn.viewmodeled.PublicViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EmployeeLeaveRecordFragment: BaseFragment<FragmentEmployeeLeaveRecordBinding, PublicViewModel>(
    FragmentEmployeeLeaveRecordBinding::inflate,
    PublicViewModel::class.java,
    true
){

//    //  詳細画面
//    private fun showAlertDialog(start: String, end: String, update: String) {
//        val alertDialog = AlertDialog.Builder(requireContext()).create()
//
////      タイトルを設定
//        alertDialog.setTitle("詳細")
////      リニアレイアウトと設定
//        val linearLayout = LinearLayout(requireContext())
//        linearLayout.orientation = LinearLayout.VERTICAL
//        linearLayout.setPadding(20, 20, 20, 20)
////      リニアレイアウトの縦と横を設定
//        val params = LinearLayout.LayoutParams(
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        )
////      開始時刻の内容を設定
//        val startText = SpannableString("開始時刻     : $start\n")
//        startText.setSpan(ForegroundColorSpan(Color.BLACK), 0, startText.length, 0)
////      開始時刻を設定
//        val startTextView = TextView(requireContext())
//        startTextView.textSize = 20F
//        startTextView.text = startText
//        linearLayout.addView(startTextView, params)
////      終了時刻の内容を設定
//        val endText = SpannableString("終了時刻     : $end\n")
//        endText.setSpan(ForegroundColorSpan(Color.BLACK), 0, endText.length, 0)
////      終了時刻を設定
//        val endTextView = TextView(requireContext())
//        endTextView.textSize = 20F
//        endTextView.text = endText
//        linearLayout.addView(endTextView, params)
//////      ステータスの内容を設定
////        val statusText = SpannableString("ステータス : $status\n")
////        statusText.setSpan(ForegroundColorSpan(Color.BLACK), 0, statusText.length, 0)
//////      ステータスを設定
////        val statusTextView = TextView(requireContext())
////        statusTextView.textSize = 20F
////        statusTextView.text = statusText
////        linearLayout.addView(statusTextView, params)
////      更新時刻の内容を設定
//        val updateText = SpannableString("更新時刻     : $update")
//        updateText.setSpan(ForegroundColorSpan(Color.BLACK), 0, updateText.length, 0)
////      更新時刻を設定
//        val updateTextView = TextView(requireContext())
//        updateTextView.textSize = 20F
//        updateTextView.text = updateText
//        linearLayout.addView(updateTextView, params)
////      詳細画面にlinearLayoutと設定
//        alertDialog.setView(linearLayout)
////      詳細画面を閉める用のボタンを設定
//        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK") { dialog, _ ->
//            dialog.dismiss()
//        }
////      詳細画面を表示
//        alertDialog.show()
//    }



    override fun initFragment(
        binding: FragmentEmployeeLeaveRecordBinding,
        viewModel: PublicViewModel?,
        savedInstanceState: Bundle?
    ) {

//      休暇記録画面
        binding.tableLayout1.let { it ->
//          テーブルの各列を画面幅に均等に分割
            it.isStretchAllColumns = true
//          ユーザーのアカウントを取得
            val regAuthor = accountPublic0
//          APIから休暇記録を取得
            RequestBuilder().getAPI(User::class.java).HolidayRecord(HolidayRecordReq(regAuthor))
                .enqueue(object : Callback<BaseResponse<List<HolidayAcquire>>> {
                    @SuppressLint("ResourceType", "SetTextI18n")
                    override fun onResponse(
                        call: Call<BaseResponse<List<HolidayAcquire>>>?,
                        response: Response<BaseResponse<List<HolidayAcquire>>>?
                    ) {
//                      検索結果を確認
                        response?.let {
                            if(it.body().data != null) {
                                if(it.body().status == "200"){
//                                  検索結果ありの場合
                                    if(it.body().data != null) {
                                        it.body().data.let {
                                            it?.forEach {item ->
                                                val row = TableRow(requireContext())
                                                row.setPadding(0, 40, 0, 40)
//                                              開始時刻をテーブルに加入
                                                val member1 = TextView(requireContext())
                                                member1.textSize = 20F
                                                member1.width = 90
                                                member1.text = item.startYear.substring(2) + "/" + item.startMonth + "/" + item.startDay + "\n" + item.startTime.substring(0, 2) + ":" + item.startTime.substring(2)
                                                row.addView(member1)
//                                              終了時刻をテーブルに加入
                                                val member2 = TextView(requireContext())
                                                member2.textSize = 20F
                                                member2.width = 90
                                                member2.text = item.endYear.substring(2) + "/" + item.endMonth + "/" + item.endDay + "\n" + item.endTime.substring(0, 2) + ":" + item.endTime.substring(2)
                                                row.addView(member2)
////                                              ステータスをテーブルに加入
//                                                val member3 = TextView(requireContext())
//                                                member3.textSize = 20F
//                                                member3.width = 90
//                                                member3.text = "?"
//                                                row.addView(member3)
//                                              更新時刻をテーブルに加入
                                                val member4 = TextView(requireContext())
                                                member4.textSize = 20F
                                                member4.width = 90
                                                member4.text = item.uptYear.substring(2) + "/" + item.uptMonth + "/" + item.uptDay
                                                row.addView(member4)
////                                              検索結果のクリックで詳細画面を表示
//                                                member1.setOnClickListener {
//                                                    showAlertDialog(
//                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay,
//                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay,
//                                                        item.uptYear + "/" + item.uptMonth + "/" + item.uptDay
//                                                    )
//                                                }
//                                                member2.setOnClickListener {
//                                                    showAlertDialog(
//                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay,
//                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay,
//                                                        item.uptYear + "/" + item.uptMonth + "/" + item.uptDay
//                                                    )
//                                                }
////                                                member3.setOnClickListener {
////                                                    showAlertDialog(
////                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay,
////                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay,
////                                                        item.uptYear + "/" + item.uptMonth + "/" + item.uptDay
////                                                    )
////                                                }
//                                                member4.setOnClickListener {
//                                                    showAlertDialog(
//                                                        item.startYear + "/" + item.startMonth + "/" + item.startDay,
//                                                        item.endYear + "/" + item.endMonth + "/" + item.endDay,
//                                                        item.uptYear + "/" + item.uptMonth + "/" + item.uptDay
//                                                    )
//                                                }
//                                              テーブルを休暇記録の画面に加入
                                                binding.tableLayout1.addView(row)
                                            }

                                        }
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

                    override fun onFailure(call: Call<BaseResponse<List<HolidayAcquire>>>?, t: Throwable?) {
//                        Log.e("TAG","NetWorkErr!")
                    }

                })

        }

    }

}