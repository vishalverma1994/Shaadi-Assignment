package com.assingment.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.assingment.R
import com.assingment.data.local.entities.UserEntity
import com.assingment.databinding.FragmentUserListBinding
import com.assingment.listeners.RetryListener
import com.assingment.ui.adapters.UserListAdapter
import com.assingment.ui.base.BaseFragment
import com.assingment.ui.viewmodel.MainViewModel
import com.assingment.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserListFragment : BaseFragment(), RetryListener {

    private lateinit var binding: FragmentUserListBinding
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var adapter: UserListAdapter
    private var userList = emptyList<UserEntity>()

    companion object {
        private val TAG = UserListFragment::class.simpleName
        fun newInstance(args: Bundle?) =
            UserListFragment().apply {
                arguments = args
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
    }

    override fun onRetryButtonCallBack() {
        requestUserListAPI()
    }

    private fun initComponents() {
        binding.handler = this
        setUserListAdapter()
        requestUserListAPI()
        observeUserList()
    }

    private fun setUserListAdapter() {
        binding.rvUserList.layoutManager = LinearLayoutManager(requireContext())
        adapter = UserListAdapter(this::onAcceptDeclineCallBack)
        binding.rvUserList.adapter = adapter
    }

    private fun onAcceptDeclineCallBack(position: Int, status: String) {
        userList[position].status = status
        when(status){
            DECLINE -> Utils.showToast(requireContext(), getString(R.string.member_declined_message))
            ACCEPT -> Utils.showToast(requireContext(), getString(R.string.member_accepted_message))
        }
        mainViewModel.updateUserIntoDB(userList[position])
    }

    private fun requestUserListAPI() {
        mainViewModel.requestUserListAPI(10)
    }

    private fun observeUserList() {
        mainViewModel._userListResponse.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.isProgressBarVisible = false
                    binding.isDataFetched = true
                    binding.isEmptyStateVisible = false
                    it.data?.observe(viewLifecycleOwner, { userList ->
                        this.userList = userList
                        adapter.getUserList(userList)
                        LogUtils.e(TAG, JsonUtils.jsonify(userList))
                    })
                }
                Status.LOADING -> {
                    binding.isProgressBarVisible = true
                    binding.isDataFetched = false
                    binding.isEmptyStateVisible = false
                }
                Status.ERROR -> {
                    binding.isProgressBarVisible = false
                    binding.isDataFetched = false
                    binding.isEmptyStateVisible = true
                    Utils.showToast(requireContext(), it.message)
                }
            }
        })
    }
}