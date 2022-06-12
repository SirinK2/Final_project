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
import android.widget.Filter
import android.widget.Filterable
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
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
    private lateinit var filterBottomSheet: FilterBottomSheet

//    private val args: HomePageFragmentArgs by navArgs()


    private val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()){}

    private val refreshListener = SwipeRefreshLayout.OnRefreshListener {

        binding.swipeRefreshLayout.isRefreshing = true

        observe(5000.0f)

        binding.swipeRefreshLayout.isRefreshing = false

        Log.d(TAG, "swipe: j")

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





        binding.swipeRefreshLayout.setOnRefreshListener(refreshListener)




    }




    override fun onStart() {
        super.onStart()

        val navCon = findNavController()

        binding.filterFab.setOnClickListener { navCon.navigate(R.id.filterBottomSheet) }


        binding.homeProfileBtn.setOnClickListener { navCon.navigate(R.id.myProfileFragment) }


        binding.postFab.setOnClickListener { navCon.navigate(R.id.itemsFragment) }


        binding.homeDmBtn.setOnClickListener { navCon.navigate(R.id.directMessageFragment) }




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