package com.tuwaiq.finalproject.presentation.homepage

import android.Manifest
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.text.format.DateFormat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Filter
import android.widget.Filterable
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.bumptech.glide.Glide
import com.tuwaiq.finalproject.R
import com.tuwaiq.finalproject.databinding.HomePageFragmentBinding
import com.tuwaiq.finalproject.databinding.HomePageItemsBinding
import com.tuwaiq.finalproject.domain.model.Post
import com.tuwaiq.finalproject.util.Constant.dateFormat
import com.tuwaiq.finalproject.util.Constant.mAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


private const val TAG = "HomePageFragment"
@AndroidEntryPoint
class HomePageFragment : Fragment() {


    private val homePageViewModel: HomePageViewModel by viewModels()
    private lateinit var binding : HomePageFragmentBinding
    private var snapHelper: SnapHelper = LinearSnapHelper()
    private val fromBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.from_bottom_anim) }
    private val toBottom: Animation by lazy { AnimationUtils.loadAnimation(requireContext(), R.anim.to_bottom_anim) }
    private var clicked = false

    private val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()){}




    private fun onAddButtonClicked(){
        visibility(clicked)
        clickable(clicked)
        setAnimation(clicked)
        clicked = !clicked
    }


    private fun clickable(clicked: Boolean){
        if (!clicked){
            //binding.floatingActionButton2.isClickable = true
            binding.cars.isCheckable = true
            binding.furniture.isCheckable = true
            binding.electronic.isCheckable = true
            binding.clothes.isCheckable =true
            binding.realestate.isCheckable = true

        }else{
            binding.cars.isCheckable = false
            binding.furniture.isCheckable = false
            binding.electronic.isCheckable = false
            binding.clothes.isCheckable =false
            binding.realestate.isCheckable = false

        }
    }

    private fun visibility(clicked: Boolean){
        if (!clicked){

            binding.cars.visibility = View.VISIBLE
            binding.furniture.visibility = View.VISIBLE
            binding.electronic.visibility = View.VISIBLE
            binding.clothes.visibility = View.VISIBLE
            binding.realestate.visibility = View.VISIBLE

        }else{
            binding.cars.visibility = View.GONE
            binding.furniture.visibility = View.GONE
            binding.electronic.visibility = View.GONE
            binding.clothes.visibility = View.GONE
            binding.realestate.visibility = View.GONE


        }
    }

    private fun setAnimation(clicked: Boolean) {
       if (!clicked){
           binding.cars.startAnimation(fromBottom)
           binding.electronic.startAnimation(fromBottom)
           binding.furniture.startAnimation(fromBottom)
           binding.clothes.startAnimation(fromBottom)
           binding.realestate.startAnimation(fromBottom)

       }else{

           binding.cars.startAnimation(toBottom)
           binding.electronic.startAnimation(toBottom)
           binding.furniture.startAnimation(toBottom)
           binding.clothes.startAnimation(toBottom)
           binding.realestate.startAnimation(toBottom)

       }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.let {
            it.window.decorView.windowInsetsController
            ?.setSystemBarsAppearance(
                APPEARANCE_LIGHT_STATUS_BARS,
                APPEARANCE_LIGHT_STATUS_BARS)
        }

        Log.e(TAG, "onCreate: from on create", )

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = HomePageFragmentBinding.inflate(layoutInflater)

        observe(100.0f)


        binding.apply {
            viewModel = homePageViewModel
            homeRv.layoutManager = LinearLayoutManager(context)
            snapHelper.attachToRecyclerView(homeRv)
        }



        return binding.root

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        observe(100.0f)


        binding.threeKm.setOnClickListener {
            observe(3000.0f)
        }


        binding.fiveKm.setOnClickListener {
            observe(5000.0f)

        }




    }



    override fun onStart() {
        super.onStart()

        binding.floatingActionButton2.setOnClickListener {
            onAddButtonClicked()
        }

        val navCon = findNavController()
        binding.homeProfileBtn.setOnClickListener {
            navCon.navigate(R.id.myProfileFragment)
        }

        binding.floatingActionButton.setOnClickListener {
            navCon.navigate(R.id.itemsFragment)
        }

        binding.homeDmBtn.setOnClickListener {
            navCon.navigate(R.id.directMessageFragment)
        }

        binding.settingBtn.setOnClickListener {
            navCon.navigate(R.id.settingFragment)
        }


    }





    private fun observe(dis: Float){

        when (PackageManager.PERMISSION_GRANTED) {
            this.let {
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            } -> {
                launcher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                homePageViewModel.getPost(requireContext(), dis).observe(
                    viewLifecycleOwner, {
                        binding.shimmerLayout.visibility = View.GONE
                        mAdapter = HomeAdapter(it as ArrayList<Post>)

                        binding.homeRv.adapter = mAdapter
                        Log.d(TAG, "onViewCreated: $dis")
                    }
                )
            }

            else -> locationDialog()

        }

    }



    private fun locationDialog(){
        AlertDialog.Builder(requireContext()).apply {
            setTitle("Allow location")
            setIcon(R.drawable.ic_location)
            setMessage("You should allow location from setting")
            setNegativeButton("Cancel") { _, _ ->
                activity?.finish()

            }
            setPositiveButton("Go to setting"){ _, _ ->

                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                intent.data = Uri.parse("package:" + context.packageName)
                startActivity(intent)
                activity?.finish()


            }


            show()
        }
    }









    inner class HomeHolder(val binding: HomePageItemsBinding):RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        var post = Post()
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(post: Post){


            this.post = post

            binding.apply {
                homeTitleTv.text = post.title
                homeDescriptionTv.text = post.description

                post.photoUrl.forEach {
                    Glide.with(requireContext())
                        .load(it)
                        .placeholder(R.drawable.ic_person)
                        .into(homeItemIv)
                }
                dateTv.text = DateFormat.format(dateFormat,post.postDate)
            }



        }



        override fun onClick(v: View?) {





            if (v == itemView){
                val navCon1 =  findNavController()
                val action = HomePageFragmentDirections
                    .actionHomePageFragmentToPreviewFragment(post.id)

                navCon1.navigate(action)

                Log.d(TAG, "onClick: $action")
            }
        }



    }



    inner class HomeAdapter(val posts: ArrayList<Post>): RecyclerView.Adapter<HomeHolder>(), Filterable {

        val allPost: MutableList<Post> = mutableListOf()

        init {
            posts.forEach { allPost.add(it) }



        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeHolder {
            val binding = HomePageItemsBinding.inflate(
                layoutInflater,
                parent,
                false
            )

            return HomeHolder(binding)



        }

        override fun onBindViewHolder(holder: HomeHolder, position: Int) {
           val post = posts[position]
            holder.bind(post)
        }

        override fun getItemCount(): Int = posts.size


        override fun getFilter(): Filter {
            return newFilter
        }

        private val newFilter = object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filteredList: MutableList<Post> = mutableListOf()


                if (constraint == null || constraint.isEmpty()) {
                    filteredList.addAll(allPost)
                } else {
                    val filterPattern = constraint.toString().lowercase(Locale.getDefault()).trim()
                    for (item in allPost) {

                        if (item.categoryName.lowercase(Locale.getDefault()) == filterPattern) {
                            Log.e(TAG, "performFiltering: ${item.categoryName}   $filterPattern  ${item.title}")

                            filteredList.add(item)
                        }
                    }
                }

                val results = FilterResults()
                results.values = filteredList



                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                posts.clear()
                posts.addAll(results!!.values as List<Post>)
                notifyDataSetChanged()
            }

        }
    }


}