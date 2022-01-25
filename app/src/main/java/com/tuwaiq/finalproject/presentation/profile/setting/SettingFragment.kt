package com.tuwaiq.finalproject.presentation.profile.setting

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.work.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.tuwaiq.finalproject.MainActivity
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.SettingFragmentBinding
import com.tuwaiq.finalproject.util.NotificationWorker
import com.tuwaiq.finalproject.util.SharedPref
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.concurrent.TimeUnit

private const val TAG = "SettingFragment"
private const val NOTIFICATION_WORKER = "notification worker"
@AndroidEntryPoint
class SettingFragment : Fragment() {



    private val settingViewModel by viewModels<SettingViewModel>()

    lateinit var binding: SettingFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = SettingFragmentBinding.inflate(layoutInflater)

        binding.logOutBtn.setOnClickListener{
            logOutDialog()
        }


        return binding.root
    }



    override fun onStart() {
        super.onStart()
        binding.updateBtn.setOnClickListener {


        }

        val isCheck = SharedPref.getNotificationState(requireContext())

        if (isCheck) {
            binding.notificationToggle.check(R.id.yes_btn)
        }else{
            binding.notificationToggle.check(R.id.no_btn)
        }

        binding.notificationToggle.addOnButtonCheckedListener { _, checkedId, isChecked ->


            if (isChecked && checkedId == R.id.yes_btn){

                val constraint = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

                val workRequest = PeriodicWorkRequest.Builder(NotificationWorker::class.java, 15,TimeUnit.MINUTES)
                    .setConstraints(constraint)
                    .build()

                WorkManager.getInstance(requireContext())
                    .enqueueUniquePeriodicWork(
                        NOTIFICATION_WORKER,
                        ExistingPeriodicWorkPolicy.KEEP,
                        workRequest
                    )

                SharedPref.saveNotificationState(requireContext(),true)
            } else if (isChecked && checkedId == R.id.no_btn){
                WorkManager.getInstance(requireContext()).cancelUniqueWork(NOTIFICATION_WORKER)
                SharedPref.saveNotificationState(requireContext(),false)
            }
        }


        val chooseLanguage = SharedPref.getLanguageState(requireContext())

        if (chooseLanguage){
            binding.languageBtn.check(R.id.Eng_btn)
        }else{
            binding.languageBtn.check(R.id.ar_btn)
        }







        binding.languageBtn.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked && checkedId == R.id.Eng_btn){
                Log.e(TAG, "onStart: Eng", )
                activity?.let {
                    SharedPref.setLanguageState(requireContext(),true,"en")
                    setLocale(it,SharedPref.getLangCode(requireContext(),"en"))

                }



            } else if(isChecked && checkedId == R.id.ar_btn){

                activity?.let {

                    SharedPref.setLanguageState(requireContext(),false,"ar")
                    setLocale(it, SharedPref.getLangCode(requireContext(),"ar"))
                }


                Log.e(TAG, "onStart: ARABIC", )

            }
        }

    }

    override fun onStop() {
        super.onStop()


    }


    private fun logOutDialog(){
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Log out")
            setIcon(R.drawable.logout)
            setMessage("Are you sure?")
            setPositiveButton("Yes"){ dialog, which ->
                Firebase.auth.signOut()
                findNavController().navigate(R.id.action_settingFragment_to_singInFragment)
            }
            setNegativeButton("No",null)
            show()
        }
    }




    private fun setLocale(activity: Activity, languageCode: String){

        val local = Locale(languageCode)
        Locale.setDefault(local)
        val resources = activity.resources
        val configuration: Configuration = resources.configuration
        configuration.setLocale(local)
        resources.updateConfiguration(configuration, resources.displayMetrics)
        startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
    }


}