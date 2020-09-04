package com.hong.architecturestudy.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.repository.RepositoryDataSource
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl
import com.hong.architecturestudy.ui.GetMovieTitle as GetMovieTitle

class MovieListDialogFragment(private val getMovieTitle: GetMovieTitle) : DialogFragment() {

    private val repositoryDataSourceImpl: RepositoryDataSource by lazy {
        val remoteDataSourceImpl = RemoteDataSourceImpl()
        val localDataSourceImpl = LocalDataSourceImpl()
        RepositoryDataSourceImpl(localDataSourceImpl, remoteDataSourceImpl)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity?.layoutInflater
        val view = inflater?.inflate(R.layout.dialog_fragment_movie_list, null)
        val rvSearchItem = view?.findViewById<RecyclerView>(R.id.rv_search_list)
        val movieSearchListAdapter = MovieSearchListAdapter {}

        rvSearchItem?.adapter = movieSearchListAdapter
        rvSearchItem?.setHasFixedSize(true)

        repositoryDataSourceImpl.loadData(requireContext())
            .observe(requireActivity(), Observer {
                movieSearchListAdapter.setList(it)
            })

        movieSearchListAdapter.onClick = {
            getMovieTitle.invoke(it)
            dismiss()
        }

        val dialog = AlertDialog.Builder(requireActivity())
        dialog.setView(view)
        dialog.setTitle("최근 검색")

        return dialog.create()

    }

    companion object {
        fun newInstance(param: (String) -> Unit): MovieListDialogFragment {
            return MovieListDialogFragment(getMovieTitle = param)
        }
    }
}