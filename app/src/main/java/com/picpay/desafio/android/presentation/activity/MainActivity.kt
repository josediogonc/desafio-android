package com.picpay.desafio.android.presentation.activity

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.presentation.adapter.UserListAdapter
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.presentation.viewModel.ContactListViewModel
import com.picpay.desafio.android.data.repository.remote.model.error.RequestError
import com.picpay.desafio.android.domain.model.user.User
import com.picpay.desafio.android.utils.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: ContactListViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    private val recyclerView: RecyclerView by lazy { binding.recyclerView }
    private val progressBar: ProgressBar by lazy { binding.userListProgressBar }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupObservers()
        loadContacts()
    }

    private fun loadContacts() = viewModel.loadContacts()

    private fun setupObservers() {
        contactListObserver()
        loadingObserver()
        errorDialogObserver()
    }

    private fun contactListObserver() =
        viewModel.contactList.observe(this, Observer { contactsList ->
            if (!contactsList.isNullOrEmpty()) {
                initRecyclerView(contactsList)
            }
        })

    private fun initRecyclerView(contactsList: List<User>) =
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = UserListAdapter(contactsList)
        }

    private fun loadingObserver() =
        viewModel.loading.observe(this, Observer { value ->
            toggleLoading(value)
        })

    private fun errorDialogObserver() =
        viewModel.errorDialog.observe(this, Observer { error ->
            error?.let {
                recyclerView.gone()
                showErrorDialog(error)
                toast(getString(R.string.remote_repository_connection_error_message))
            }
        })

    private fun showErrorDialog(error: RequestError) = alertDialog(error.title, error.message)

    private fun toggleLoading(value: Boolean) =
        if (value) {
            progressBar.visible()
            recyclerView.gone()
        } else {
            progressBar.gone()
            recyclerView.visible()
        }

}
