package com.example.taghive.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taghive.R
import com.example.taghive.databinding.FragmentAllCryptoBinding
import com.example.taghive.domain.model.CryptoDataClassItem
import com.kaopiz.kprogresshud.KProgressHUD
import org.koin.android.ext.android.inject

class AllCryptoFragment : Fragment(), ItemClickListener {
    private lateinit var binding: FragmentAllCryptoBinding
    private val cryptoViewModel: CryptoViewModel by inject()
    lateinit var progressDialog: KProgressHUD
    private lateinit var cryptoAdapter: CryptoListAdapter
    private val cryptoList = mutableListOf<CryptoDataClassItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        progressDialog = KProgressHUD.create(requireActivity())
            .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
            .setLabel(getString(R.string.loading))
            .setCancellable(true)
            .setAnimationSpeed(2)
            .setDimAmount(0.5f)
        binding = FragmentAllCryptoBinding.inflate(layoutInflater, container, false)

        setRecyclerView()
        cryptoViewModel.listOfCryptos()
        cryptoViewModel.getCryptoDatResponse().observe(viewLifecycleOwner) {
            cryptoList.clear()
            cryptoList.addAll(listOf(it))


            //Notifying the adapter here
            cryptoAdapter.notifyDataSetChanged()
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setRecyclerView() {
        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.idTotalCrypto.layoutManager = layoutManager
        cryptoAdapter = CryptoListAdapter(cryptoList, this)
        binding.idTotalCrypto.adapter = cryptoAdapter
    }

    override fun onCryptoListClicked(name: String) {
        TODO("Not yet implemented")
    }


}